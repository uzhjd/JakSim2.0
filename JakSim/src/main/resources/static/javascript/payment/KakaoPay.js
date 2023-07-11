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
            console.log(response);

            if (response.data.status === 500) {
                alert("Payment with Kakao Pay failed.");
            } else {
                var box = response.next_redirect_pc_url;
                // window.open(box); // open a new window
                location.href = box;
            }
        })
        .catch(error => {
            console.error(error);
        });
}