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
            var result = response.data[0]['amount'];
            if(result === 'undefined')
                result = 0;
            cnt.innerHTML = numFormat(response.data[0]['amount']);
        }).catch(error => {
            console.error(error);
    });
}

function getAccountCntDay(){
    var cnt = document.getElementById('man_main_aside_visitCntToday');
    var startDate = yesterday;
    //var endDate = `${year}-${month}-${day}`;
    var endDate = yesterday;

    axios.get(`/man/api/account/amount?start=${startDate}&end=${endDate}`)
        .then(response => {
            if(response.data[0] === null || response.data[0] === undefined){
                response.data[0]= {amount : 0};
            }
            cnt.innerHTML = numFormat(response.data[0]['amount']);
        }).catch(error => {
            console.error(error);
        });
}

function getRatioTrainer(){
    var ratio = document.getElementById('man_main_aside_ratioTrainer');
    axios.get(`/man/api/account/ratio`)
        .then(response => {
            ratio.innerHTML = `${(getRatio(response.data) * 100).toFixed(2)} %`;
        }).catch(error => {
            console.error(error);
        });
}

function getRegistered(){
    var cnt = document.getElementById('man_main_aside_registeredAccounts');
    axios.get(`/man/api/account/amount?start=2023-07-01`)
        .then(response => {
            if(response.data[0] === null || response.data[0] === undefined){
                response.data[0]= {amount : 0};
            }
            console.log(response.data[0]['amount']);
            cnt.innerHTML = numFormat(getSum(response.data));
        }).catch(error => {
            console.error(error);
        });
}

function getSum(dataList){
    var sum = 0;
    dataList.forEach((item) => {
        console.log(item['amount']);
        sum += item['amount'];
    });
    return sum;
}

function getRatio(dataList){
    return dataList[2]['amount'] / getSum(dataList);
}