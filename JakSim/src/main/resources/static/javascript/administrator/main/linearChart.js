var linearChart, chart;
var startDate, endDate;

function getAccessDataForLinear(){
    axios.get(`/man/api/visit/date`)
        .then(response => {
            makeLinearChart(response.data);
        }).catch(error => {
            console.error(error);
        });
}

function makeLinearChart(chartData){
    if(chart){
        chart.destroy();
    }

    linearChart = document.getElementById('man_main_linearChart');

    var labelList = chartData.map(data => data['date']);
    var dataList = chartData.map(data => data['amount']);

    console.log('before chart');
    console.log(labelList);
    console.log(dataList);

    chart = new Chart(linearChart, {
        type: 'line',
        data: {
          labels: labelList,
          datasets: [{
            label: '로그인 수',
            data: dataList,
            borderWidth: 1
          }]
        },
        options: {
          maintainAspectRatio:false,
          aspectRatio: 3,
          scales: {
            y: {
              beginAtZero: true
            }
          }
        }
      });
}