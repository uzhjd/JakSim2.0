window.onload = function () {
    // setPayment();
    var btn = document.getElementById("paymentDetailsBtn");

    // btn.addEventListener('click', () => paymentDetails());
}

function paymentCheck(price){
    price.innerHTML = price.innerHTML.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function paymentMethodFormat(method){
    switch(method){
        case 'card':
            method.innerHTML = '카드결제';
            break;
        case 'money':
            method.innerHTML = '현금결제';
            break;
        default:
            method.innerHTML = '결제수단 오류';
    }
}