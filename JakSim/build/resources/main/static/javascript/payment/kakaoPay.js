//window.onload = function () {
    //var payBtn = document.getElementById("kakaopayBtn");

    // 세은님께 데이터 받기
    //payBtn.addEventListener('click', () => kakaoPay("남유정 트레이너 10회 사용권", 1, 10, 10, 90));
//}

function kakaoPay(ptTitle, tpIdx, ptPrice, ptTimes, ptPeriod) {
     var data = {
         ptTitle: ptTitle,
         tpIdx: tpIdx,
         ptPrice: ptPrice,
         ptTimes: ptTimes,
         ptPeriod: ptPeriod
     };
console.log("hhehhehehe");
console.log("data");
console.log(data);
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