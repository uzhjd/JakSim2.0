var rePayButton, reviewButton, refundButton;
window.onload = function(){
    refundStatus();

    rePayButton = document.getElementById('pay_detail_repaybutton');
    rePayButton.addEventListener('click', rePay);

    reviewButton = document.getElementById('pay_detail_reviewbutton');
    reviewButton.addEventListener('click', goReview);

    refundButton = document.getElementById('pay_detail_refundbutton');
    refundButton.addEventListener('click', doRefund);
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
    sessionStorage.setItem('pIdx', document.getElementById('pay_detail_idx').innerHTML);
}

function goReview(){
    sessionStorage.setItem('pIdx', document.getElementById('pay_detail_idx').innerHTML);
}

function rePay(){
    sessionStorage.setItem('pIdx', document.getElementById('pay_detail_idx').innerHTML);
}