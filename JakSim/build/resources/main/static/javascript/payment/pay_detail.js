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

    selectedPIdx = document.getElementById('pay_detail_idx').innerHTML;
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

function doRefund() {
    console.log("refund");

    var data = {
        tid: document.getElementById('pay_detail_tid').innerHTML,
        cancel_amount: document.getElementById('pay_detail_price').innerHTML
    }

    axios.post('/payment/refund', data)
        .then((response) => {
            var httpStatus = response.status;
            console.log(response.data);

            if(httpStatus == 500) {
                alert("500: Payment with Kakao Pay failed.");
            } else {
                console.log("refundSuccess");
                window.location.href=`http://localhost:8080/payment/refundSuccess/${response.data.tid}`;

            }
        })
        .catch(error => {
            console.error(error);
        });

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
    };

    axios.post('/payment/ready', data)
        .then((response) => {
            var httpStatus = response.status;

            console.log(response);

            if(httpStatus == 500) {
                alert("500: Payment with Kakao Pay failed.");
            } else {
                var box = response.data.next_redirect_pc_url;

                window.open(box, "", "width=500, height=800");
            }
        })
        .catch(error => {
            console.error(error);
        });
}