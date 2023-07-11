function setDate(date) {
    document.getElementById("reservation_date").innerText = date;

    setMyReservation(date);
}

function setMyReservation(date) {
    var reservation = document.getElementById('reservation_list');
    var ptList = document.getElementById('trainer_list');
    var button = document.getElementById('resCancleBtn');
    var trainerId = JSON.parse(ptList.options[ptList.selectedIndex].value).trainerId;
    var formattedDate = date.split(". ").join("-");
    var type = ['상담', '1:1', '단체'];

    const data = {
        trainerId: trainerId,
        dt: formattedDate
    };

    axios.post('/reservation/search', data)
        .then((response) => {
            if(response.data['tstartT'] != null) {
                if(response.data['ttype'] == 2) {
                    reservation.textContent = response.data['tstartT'] + " - " + response.data['tendT'] + " ( " + type[response.data['ttype']] + " " + response.data['tpeolple'] +" )";
                } else {
                    reservation.textContent = response.data['tstartT'] + " - " + response.data['tendT'] + " ( " + type[response.data['ttype']] + " )";
                }

                button.style.display = 'inline-block';

                button.addEventListener('click', () => resCancle(response.data['pidx'], response.data['ridx'], type[response.data['ttype']]));
            } else {
                reservation.textContent = "▶ 예약 정보가 없습니다.";
                button.style.display = 'none';
            }
        })
        .catch(error => {
            console.error(error);
        });
}

function resRegister(event, pIdx, tIdx, id, formattedDate) {
    data = {
        pIdx: pIdx,
        tIdx: tIdx,
        trainerId: id,
        tDate:formattedDate
    };
console.log("event");
console.log(event);
    axios.post('/reservation/register')
        .then((response) => {
            console.log("response.data");
            console.log(response.data);
            // if(response.data) {
            //     alert(type + ' 예약이 취소되었습니다!');
            //     setMyReservation(document.getElementById("reservation_date").innerText);
            // } else {
            //     alert('예약이 취소되지 않았습니다!');
            // }
        })
        .catch(error => {
            console.error(error);
        });
}

function resCancle(pIdx, rIdx, type) {
    var url = '/reservation/cancle/' + pIdx + '/' + rIdx;

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