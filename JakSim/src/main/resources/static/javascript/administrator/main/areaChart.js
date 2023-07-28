var areaChart, newAreaChart;

function areaTypeSelect(){
    var radioButtons = document.querySelectorAll('input[name="area-range"]');
    radioButtons.forEach((button) => {
        button.addEventListener('change', () => {
            if(button.checked){
                switchAreaType(button.value);
            }
        });
    });
}

function switchAreaType(areaType){
    var start = '';
    var end = '';

    switch(areaType){
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
    getAccessDataForArea(start, end);
}

function getAccessDataForArea(start, end){
    var startDate = isStartUndefined(start);
    var endDate = isEndUndefined(end);

    axios.get(`/man/api/visit/date?start=${startDate}&end=${endDate}`)
        .then(response => {
            makeAreaChart(response.data);
        }).catch(error => {
            console.error(error);
        })
}

function makeAreaChart(chartData){
    if(newAreaChart){
        newAreaChart.destroy();
    }
    areaChart = document.getElementById('man_main_areaChart');

    var labelList = chartData.map(data => data['date']);
    var dataList = chartData.map(data => data['amount']);
    var cumulativeSum = dataList.reduce((acc, cur) => {
        (acc.length > 0) ? acc.push(acc[acc.length-1]+cur) : acc.push(cur);
        return acc;
    }, []);

    document.getElementById('man_section_areaHead').innerHTML = `${labelList[0]} ~ ${labelList[labelList.length-1]}`;

    newAreaChart = new Chart(areaChart, {
        type: 'line', // Area Chart는 line 타입으로 그립니다.
        data: {
            labels: labelList,
            datasets: [{
                label: `접속자 누적 수`,
                data: cumulativeSum, // 데이터 배열 설정
                backgroundColor: 'rgba(75, 192, 192, 0.2)', // 면적의 배경색
                borderColor: 'rgba(75, 192, 192, 1)', // 선의 색상
                borderWidth: 1, // 선의 두께
                fill: true // 영역을 채우도록 설정
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false, // 차트의 높이를 조절하기 위해 false로 설정
            aspectRatio: 3
        }
    });
}