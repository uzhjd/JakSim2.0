var data = [];
var selectOption, chart;
var isFirst = true;
var totalPage;
var page;
var nextButton, prevButton;

window.onload = function(){
    totalPage = document.getElementById('inbody_totalPage').innerHTML;
    pageNumber = document.getElementById('inbody_pagenumber');

    page = parseInt(pageNumber.innerHTML);

    if(isFirst){
        getChart('weight');
        getData();
        isFirst = false;
    }

    selectOption = document.getElementById('inbody_selector');
    selectOption.addEventListener('change', (event) => {
        var selectedValue = event.target.value;
        getChart(selectedValue);
    });

    nextButton = document.getElementById('inbody_nextbutton');
    nextButton.addEventListener('click', nextButtonListener);

    prevButton = document.getElementById('inbody_prevbutton');
    prevButton.addEventListener('click', prevButtonListener);

    checkNextPrevButton();
}

function checkNextPrevButton(){
    if(page >= totalPage){
        nextButton.disabled = true;
    }else if(page <= 1){
        prevButton.disabled = true;
    }else{
        nextButton.disabled = false;
        prevButton.disabled = false;
    }
}

function prevButtonListener(){
    page-=1;
    pageNumber.innerHTML = page;
    getData();
    checkNextPrevButton();
}

function nextButtonListener(){
    page+=1;
    pageNumber.innerHTML = page;
    getData();
    checkNextPrevButton();
}

function getData(){
    axios.get(`/mypage/api/inbody/${page}`)
        .then(response => {
            var html = '';
            var tbody = document.getElementById('table_tbody');
            response.data.forEach(item => {
                html += '<tr>';
                html += '<td>' + item.weight + '</td>';
                html += '<td>' + item.score + '</td>';
                html += '<td>' + item.fat + '</td>';
                html += '<td>' + item.muscle + '</td>';
                html += '<td>' + item.c_dt + '</td>';
                html += '</tr>';
            });
            tbody.innerHTML = html;
        })
        .catch(error => {
            console.error(error);
        })
}

function getChart(option){
    axios.get('/mypage/api/inbody/data')
        .then(response => {
            response.data.forEach(function(data){
                showChart(response.data, response.data.map(data => data['c_dt']), option);
            })
        })
        .catch(error => {
            console.error(error);
        });
}

function showChart(chartData, label, option){
    if(chart){chart.destroy();}

    var data = chartData.map(data => data[option]);
    var maxItem = Math.max(...data);
    var minItem = Math.min(...data);

    var canvas = document.getElementById('inbodyChart').getContext('2d');
    if(option === 'weight'){
        var bmiData = data.map(function(weight, index){
            var height = chartData.map(data => data['height'])[index];
            height /= 100;
            var bmi = weight / (height*height);
            return bmi.toFixed(2);
        });
        chart = new Chart(canvas, {
                type: 'line',
                data: {
                    labels: label,
                    datasets: [
                        {
                            label: option,
                            yAxisID: 'weight-y-axis',
                            data: data,
                            borderWidth: 2,
                            tension: 0.4
                        },
                        {
                            label: 'BMI',
                            yAxisID: 'bmi-y-axis',
                            data: bmiData,
                            borderWidth: 2,
                            tension: 0.4,
                            fill: false
                        },
                    ]
                },
                options: {
                    scales: {
                        'weight-y-axis':{
                            type: 'linear',
                            position: 'left',
                            beginAtZero: false
                        },
                        'bmi-y-axis':{
                            type: 'linear',
                            position: 'right',
                            beginAtZero: false
                        }
                    }
                }
            });
    }else{
        chart = new Chart(canvas, {
        type: 'line',
        data: {
            labels: label,
            datasets: [{
                label: option,
                data: data,
                borderWidth: 2,
                tension: 0.4
            }]
        },
        options: {
            scales: {
                y:{
                    beginAtZero: false
                }
            }
        }
    });
    }
}
