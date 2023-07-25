var areaChart;

function makeAreaChart(){
    new Chart(areaChart, {
                type: 'line', // Area Chart는 line 타입으로 그립니다.
                data: {
                    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
                    datasets: [{
                        label: 'Data Set',
                        data: [10, 20, 30, 25, 35, 40, 30], // 데이터 배열 설정
                        backgroundColor: 'rgba(75, 192, 192, 0.2)', // 면적의 배경색
                        borderColor: 'rgba(75, 192, 192, 1)', // 선의 색상
                        borderWidth: 1, // 선의 두께
                        fill: true // 영역을 채우도록 설정
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false // 차트의 높이를 조절하기 위해 false로 설정
                }
            });
}