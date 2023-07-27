var currentDate, year, month, day;

window.onload = function(){
    getNowDate();

    asideProcess();
    chartProcess();
}

function chartProcess(){
    //makeLinearChart();
    getAccessDataForLinear();
    makeAreaChart();
}

function asideProcess(){
    getVisitCntNow();

    setYesterday();
    getVisitCntDay();
}

function getNowDate(){
    currentDate = new Date();
    year = currentDate.getFullYear();
    month = currentDate.getMonth()+1;
    day = currentDate.getDate();
}