function setDate(date) {
    document.getElementById("reservation_date").innerText = date;

    setMyReservation(date);
}

function setMyReservation(date) {
    var reservation = document.getElementById('reservation_list');
    var ptList = document.getElementById('trainer_list');
    var button = document.getElementById('resCancle');
    var trainerId = JSON.parse(ptList.options[ptList.selectedIndex].value).trainerId;
    var formattedDate = date.split(". ").join("-");
    var type;

    const data = {
        trainerId: trainerId,
        dt: formattedDate
    };

    axios.post('/reservation/search', data)
        .then((response) => {
            if(response.data['tstartT'] != null) {
                if(response.data['ttype'] == 2) {
                    type = "단체"

                    reservation.textContent = response.data['tstartT'] + " - " + response.data['tendT'] + " ( " + type + " " + response.data['tpeolple'] +" )";
                } else {
                    if(response.data['ttype'] == 0) type = "상담"
                    else if(response.data['ttype'] == 1) type = "1:1"

                    reservation.textContent = response.data['tstartT'] + " - " + response.data['tendT'] + " ( " + type + " )";
                }

                button.style.display = 'inline-block';

                button.addEventListener('click', () => resCancle(response.data['pidx'], response.data['ridx'], type));
            } else {
                reservation.textContent = "▶ 예약 정보가 없습니다.";
                button.style.display = 'none';
            }
        })
        .catch(error => {
            console.error(error);
        });
}

function resRegister() {

}

function resCancle(pIdx, rIdx, type) {
    var url = '/reservation/cancle/' + pIdx + '/' + rIdx;
console.log(" click " + rIdx);
    axios.get(url)
        .then((response) => {
            if(response.data) {
                alert(type + ' 예약이 취소되었습니다!');
                setMyReservation(document.getElementById("reservation_date").innerText);
            } else {
                alert('예약이 취소되지 않았습니다!');
            }
        })
        .catch(error => {
            console.error(error);
        });
}