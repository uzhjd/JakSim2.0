var formattedDate;

function setDate(date) {
    document.getElementById("reservation_date").innerText = date;

    setMyReservation(date);
}

function setMyReservation(date) {
    var reservation = document.getElementById('reservation_list');
    var ptList = document.getElementById('trainer_list');
    var button = document.getElementById('resCancleBtn');
    var trainerId = JSON.parse(ptList.options[ptList.selectedIndex].value).trainerId;
    formattedDate = date.split(". ").join("-");
    var type = ['상담', '1:1', '단체'];

    const data = {
        trainerId: trainerId,
        dt: formattedDate
    };

    axios.post('/reservation/search', data)
        .then((response) => {
            console.log("reservation 확인");
            console.log(response.data);
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

function resRegister(tIdx, formattedDate) {
    var warn = ["지난 날에 대한 예약은 할 수 없습니다.",
                "이미 등록된 예약이 있습니다.",
                "이미 등록된 예약이 있습니다.",
                "해당 시간표는 존재하지 않습니다.",
                "예약이 올바르게 되지 않았습니다.",
                "예약이 올바르게 되었습니다."
                ];

    const data = {
        p_idx: pIdx,
        t_idx: tIdx,
        trainerId: trainerId,
        ptCnt: ptCnt,
        date: formattedDate
    };

    console.log(data);

    axios.post('/reservation/register', data)
        .then((response) => {
            alert(warn[response.data]);

            if(response.data == 5) {
                setMyReservation(formattedDate);
            }
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