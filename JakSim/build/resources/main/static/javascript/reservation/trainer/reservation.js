var gender = ['남성', '여성'];

function setMyReservation(selectedTIdx) {
    var reservation = document.getElementById('reservation_list');

    console.log(selectedTIdx);
    axios.get('/reservation/search/' + selectedTIdx)
        .then((response) => {
            console.log(response.data);
            if(response.data.length != 0) {
                    reservation.textContent = response.data['name'] + "회원" + response.data['id']
                                                                                    + gender[response.data['gender']];
            } else {
                reservation.textContent = "▶ 예약 정보가 없습니다.";
            }
        })
        .catch(error => {
            console.error(error);
        });
}