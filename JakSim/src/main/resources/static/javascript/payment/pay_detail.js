var rePayButton, reviewButton, refundButton;
var pIdx;

window.onload = function(){
    refundStatus();

    rePayButton = document.getElementById('pay_detail_repayButton');
    rePayButton.addEventListener('click', rePay);

    reviewButton = document.getElementById('pay_detail_reviewButton');
    reviewButton.addEventListener('click', goReview);

    refundButton = document.getElementById('pay_detail_refundButton');
    refundButton.addEventListener('click', doRefund);

    init();
}

function init(){
    price1 = document.getElementById('pay_detail_price1');
    price1.innerHTML = numFormat(price1.innerHTML);
    price2 = document.getElementById('pay_detail_price2');
    price2.innerHTML = numFormat(price2.innerHTML);

    payment_type();
    trainerExpert();
    genderType();
}

function genderType(){
    var gender = document.getElementById('pay_detail_trainerGender');
    switch(gender.innerHTML){
        case '0':
            gender.innerHTML = '남';
            break;
        case '1':
            gender.innerHTML = '여';
            break;
    }
}

function trainerExpert(){
    var expert1 = document.getElementById('pay_detail_trainerExpert1')
    var expert2 = document.getElementById('pay_detail_trainerExpert2');

    function expertFormat(item){
        switch(item.innerHTML){
            case '0':
                item.innerHTML = '바디프로필';
                break;
            case '1':
                item.innerHTML = '일반회원';
                break;
            case '2':
                item.innerHTML = '다이어트';
                break;
            case '3':
                item.innerHTML = '재활운동';
                break;
            case '4':
                item.innerHTML = '자세교정';
                break;
            case '5':
                item.innerHTML = '컨디셔닝';
                break;
        }
    }

    expertFormat(expert1);
    expertFormat(expert2);
}

function refundStatus(){
    var status = document.getElementById('pay_detail_refundStatus');
    switch(status.innerHTML){
        case '0':
            status.innerHTML = '구매완료';
            break;
        case '1':
            status.innerHTML = '환불완료';
            break;
        case 'CANCEL_PAYMENT':
            status.innerHTML = '구매취소';
            break;
    }
}

function payment_type(){
    var method = document.getElementById('pay_detail_paymentMethod');
    switch(method.innerHTML){
        case 'MONEY':
            method.innerHTML = '현금 결제';
            break;
        case 'CARD':
            method.innerHTML = '카드 결제';
            break;
    }
}

function doRefund(){
    var data = {
       tid: document.getElementById('pay_detail_tid').innerHTML,
       price: document.getElementById('pay_detail_price1').innerHTML.replaceAll(',','')
    }
    var jsonData = JSON.stringify(data);
    sessionStorage.setItem('data', jsonData);
}

function goReview(){
    var trainerId = document.getElementById('pay_detail_utIdx').innerHTML;
    window.location.href=`/registerReview/${trainerId}`;
}

function rePay(){
    var data = {
       ptTitle: document.getElementById('pay_detail_ptTitle').innerHTML,
       tpIdx: document.getElementById('pay_detail_tid').innerHTML, //tid?
       ptPrice: document.getElementById('pay_detail_price1').innerHTML,
       ptTimes: document.getElementById('pay_detaill_ptTimes').innerHTML,
       ptPeriod: document.getElementById('pay_detail_ptPeriod').innerHTML
    }
    var jsonData = JSON.stringify(data);
    sessionStorage.setItem('refundData', jsonData);
}