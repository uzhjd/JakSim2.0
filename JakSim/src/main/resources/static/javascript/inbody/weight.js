var data = [];
var selectOption, chart;

window.onload = function(){
    selectOption = document.getElementById('inbody_selector');
    selectOption.addEventListener('change', (event) => {
        var selectedValue = event.target.value;
        getWeights(selectedValue);
    })

}

function getWeights(option){
    axios.get('/mypage/api/inbody/data')
        .then(response => {
            console.log(response.data);
            response.data.forEach(function(data){
                console.log(data[option]);
                var chartData = response.data.map(data => data[option]);
                var chartLabel = response.data.map(data => data['c_dt']);
                showChart(chartData, chartLabel, option);
            })
        })
        .catch(error => {
            console.error(error);
        });
}

function showChart(data, label, option){
    if(chart){
        chart.destroy();
    }

    var canvas = document.getElementById('inbodyChart').getContext('2d');
    chart = new Chart(canvas, {
        type: 'line',
        data: {
            labels: label,
            datasets: [{
                label: option,
                data: data,
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y:{
                    beginAtZero: true
                }
            }
        }
    });
}
