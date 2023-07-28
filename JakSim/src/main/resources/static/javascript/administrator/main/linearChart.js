var linearChart, newLinearChart;

function linearTypeSelect(){
    var radioButtons = document.querySelectorAll('input[name="linear-range"]');
    radioButtons.forEach((button) => {
        button.addEventListener('change', () => {
            if(button.checked){
                switchLinearType(button.value);
            }
        });
    });
}

function switchLinearType(linearType){
    var start = '';
    var end = '';
    switch(linearType){
        case 'tweek':
            start = `${year}-${month}-${day-13}`;
            break;
        case 'amon':
            start = `${year}-${month-1}-${day}`;
            break;
        case 'tmon':
            start = `${year}-${month-3}-${day}`;
            break;
        default:
            console.log('뭘 고른거여?');
    }
    getAccessDataForLinear(start, end);
}

function isStartUndefined(item){
    if(item === undefined)
        item = `${year}-${month}-${day-13}`;
    return item;
}

function isEndUndefined(item){
    return '';
}

function getAccessDataForLinear(start, end){
    var startDate=isStartUndefined(start);
    var endDate=isEndUndefined(end);

    axios.get(`/man/api/visit/date?start=${startDate}&end=${endDate}`)
        .then(response => {
            makeLinearChart(response.data);
        }).catch(error => {
            console.error(error);
        });
}

function avgData(list){
    return (list.reduce((acc, cur) => acc + cur, 0))/list.length;
}

function makeLinearChart(chartData){
    if(newLinearChart){
        newLinearChart.destroy();
    }

    linearChart = document.getElementById('man_main_linearChart');

    var labelList = chartData.map(data => data['date']);
    var dataList = chartData.map(data => data['amount']);
    var avg = avgData(dataList);
    var avgList = Array(dataList.length).fill(avg);

    document.getElementById('man_section_linearHead').innerHTML = `${labelList[0]} ~ ${labelList[labelList.length-1]}`;

    newLinearChart = new Chart(linearChart, {
        type: 'line',
        data: {
          labels: labelList,
          datasets: [
              {
                label: '로그인 수',
                data: dataList,
                borderWidth: 1
              },
             {
               label: '일평균',
               data: avgList,
               borderWidth: 3,
               pointRadius:0
             }
          ]
        },
        options: {
          maintainAspectRatio:false,
          aspectRatio: 3,
          y:{
            beginAtZero:false
          }
        }
    });
}