var yesterday;

function setYesterday(){
    yesterday =`${year}-${month}-${day-1}`;
    document.getElementById('man_aside_Yesterday').innerHTML = yesterday;
}

function getVisitCntNow(){
    var cnt = document.getElementById('man_main_aside_loginUsers');
    var startDate = `${year}-${month}-${day}`;

    axios.get(`/man/api/visit/date?start=${startDate}`)
        .then(response => {
            cnt.innerHTML = numFormat(response.data[0]['amount']);
        }).catch(error => {
            console.error(error);
        });
}

function getVisitCntDay(){
    var cnt = document.getElementById('man_main_aside_visitCntYesterday');
    var startDate = yesterday;
    var endDate = yesterday;

    axios.get(`/man/api/visit/date?start=${startDate}&end=${endDate}`)
        .then(response => {
            cnt.innerHTML = numFormat(response.data[0]['amount']);
        }).catch(error => {
            console.error(error);
    });
}