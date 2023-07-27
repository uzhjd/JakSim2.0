var rePayButton, reviewButton, refundButton;
var pIdx;

window.onload = function(){
    refundStatus();

    rePayButton = document.getElementById('pay_detail_repaybutton');
    rePayButton.addEventListener('click', rePay);

    reviewButton = document.getElementById('pay_detail_reviewbutton');
    reviewButton.addEventListener('click', goReview);

    refundButton = document.getElementById('pay_detail_refundbutton');
    refundButton.addEventListener('click', doRefund);

    pIdx = document.getElementById('pay_detail_idx').innerHTML;
}

function refundStatus(){
    var status = document.getElementById('pay_detail_refundstatus');
    switch(status.innerHTML){
        case '0':
            status.innerHTML = '구매완료';
            break;
        case '1':
            status.innerHTML = '환불완료';
            break;
    }
}

function doRefund(){
    var data = {
       tid: document.getElementById('pay_detail_tid').innerHTML,
       price: document.getElementById('pay_detail_price').innerHTML
    }
    var jsonData = JSON.stringify(data);
    sessionStorage.setItem('data', jsonData);

    /*
    해당코드 사용시,
    var jsonData = sessionStorage.getItem('data');
    var data = JSON.parse(jsonData);
    console.log(data.tid);
    console.log(data.price);
    이런식으로 사용하면 됩니다.
    */

    /*
    session 사용 완료 시,
    sessionStorage.removeItem('data');
    작성해주세요
    */
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