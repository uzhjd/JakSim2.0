var data = [];
var selectOption, chart;
var isFirst = true;

window.onload = function(){
    if(isFirst){
        getWeights('weight');
        isFirst = false;
    }
    selectOption = document.getElementById('inbody_selector');
    selectOption.addEventListener('change', (event) => {
        var selectedValue = event.target.value;
        getWeights(selectedValue);
    });


}

function getWeights(option){
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
