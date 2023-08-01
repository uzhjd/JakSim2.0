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

    //pIdx = document.getElementById('pay_detail_idx').innerHTML;

    init();
}

function init(){
    price1 = document.getElementById('pay_detail_price1');
    price1.innerHTML = numFormat(price1.innerHTML);
    price2 = document.getElementById('pay_detail_price2');
    price2.innerHTML = numFormat(price2.innerHTML);

    payment_type();
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
    var trainerId = document.getElementById('pay_detail_trainerId').innerHTML;
    window.location.href=`/registerReview/${trainerId}`;
}

function rePay(){
    var typeAndPeriodSpan = document.getElementById('pay_detail_type_period').innerHTML;
    var divideData = typeAndPeriodSpan.split('/');

    var data = {
       ptTitle: document.getElementById('pay_detail_ptTitle').innerHTML,
       tpIdx: document.getElementById('pay_detail_tpIdx').innerHTML,
       ptPrice: document.getElementById('pay_detail_price').innerHTML,
       ptTimes: divideData[0].trim(),
       ptPeriod: divideData[1].trim().replace('일','')
    }
    var jsonData = JSON.stringify(data);
    sessionStorage.setItem('refundData', jsonData);

    //사용법은 이전과 같습니다.
}