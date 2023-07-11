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
            var timetable = document.createElement("div");
            var radioBox = document.createElement("input");
            console.log(response.data);
            var childDivs = timetableList.querySelectorAll("div");

            childDivs.forEach(function (childDiv) {
                timetableList.removeChild(childDiv);
            });

            if(response.data.length == 0) {
                timetable.textContent = "※ 시간표 정보가 없습니다.";
                button.style.display = 'none';
                timetableList.appendChild(timetable);
            } else {
                for(var i = 0; i < response.data.length; i++) {
                    timetable.textContent = " " + response.data[i]['tstartT'] + " - " + response.data[i]['tendT'] + " ( " + type[response.data[i]['ttype']] + " ) ";

                    if(response.data[i]['ttype'] == 2) {
                        timetable.textContent += response.data[i]['tpeolple'];
                    }

                    button.style.display = 'inline-block';

                    radioBox.setAttribute("type", "radio");
                    radioBox.setAttribute("name", ("timetable_" + i + 1));
                    // 인원수 체크
                    // 타입 체크
                    radioBox.setAttribute("value", pIdx);
                    // radioBox.setAttribute("disabled", "false");
                    timetable.insertBefore(radioBox, timetable.firstChild);
console.log(timetable.textContent);
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