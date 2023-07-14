function setTimetable(pIdx, id, date, tType) {
    var formattedDate = date.split(". ").join("-");
    var timetableList = document.getElementById("timetableList");
    var button = document.getElementById('reservationBtn');
    var type = ['상담', '1:1', '단체'];

    const data = {
        trainerId: id,
        dt: formattedDate,
        type: tType
    }

    axios.post('/timetable/details', data)
        .then((response) => {
            var childDivs = timetableList.querySelectorAll("div");

            childDivs.forEach(function (childDiv) {
                timetableList.removeChild(childDiv);
            });
            console.log(response.data);

            if(response.data.length == 0) {
                var timetable = document.createElement("div");
                timetable.innerHTML = "※ 시간표 정보가 없습니다.";
                timetableList.appendChild(timetable);

                button.style.display = 'none';
            } else {
                for(var i = 0; i < response.data.length; i++) {
                    var radioBox = document.createElement("input");
                    var timetable = document.createElement("div");

                    timetable.innerHTML = " " + response.data[i]['tstartT'].substr(0, 5) + " - " + response.data[i]['tendT'].substr(0, 5) + " ( " + type[response.data[i]['ttype']] + " ) ";

                    if(response.data[i]['ttype'] == 2) {
                        timetable.innerHTML += response.data[i]['tpeolple'];
                    }

                    button.style.display = 'inline-block';

                    radioBox.setAttribute("type", "radio");
                    radioBox.setAttribute("name", ("timetable_" + (i + 1)));
                    radioBox.setAttribute("value", response.data[i]['tidx']);
                    // radioBox.setAttribute("disabled", "false"); // 인원수 체크
                    timetable.insertBefore(radioBox, timetable.firstChild);

                    timetableList.appendChild(timetable);
                }
            }

            // tIdx넘겨줄때 라디오 버튼이 클릭된 애로 넘겨주기!
            button.addEventListener('click', (event) => resRegister(event, pIdx, response.data[0]['tidx'], id, formattedDate));
        })
        .catch(error => {
            console.error(error);
        });
}