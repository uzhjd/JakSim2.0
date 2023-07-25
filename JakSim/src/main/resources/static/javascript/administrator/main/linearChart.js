var linearChart;

function makeLinearChart(){
    new Chart(linearChart, {
        type: 'line',
        data: {
          labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
          datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
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