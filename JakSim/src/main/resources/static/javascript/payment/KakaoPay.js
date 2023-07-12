window.onload = function () {
    var payBtn = document.getElementById("kakaopayBtn");
    payBtn.addEventListener('click', kakaopay);
}

function kakaopay() {
    console.log("cllclcickk");

     var data = {
         productName: "남유정 트레이너 1달 사용권",
         ptPrice: 10000
     };

    axios.post('/payment/ready', data)
        .then((response) => {
            var httpStatus = response.status;

            console.log(response);

            // if (response.data.status === 500) {
            // } else {
            //     var box = response.next_redirect_pc_url;
            //     // window.open(box); // open a new window
            //     location.href = box;
            // }
            if(httpStatus == 500) {
                alert("Payment with Kakao Pay failed.");

            } else {
                console.log("연결은 우선 성공!!!!");
                var box = response.data.next_redirect_pc_url;
                console.log("success");
                // window.open(box); // 새창으로 열기 (모달로 열어버릴까?)
                location.href = box;
            }
        })
        .catch(error => {
            console.error(error);
        });
}