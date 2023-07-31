function setTimetable(selectedDate) {
    var type = ['상담', '1:1', '단체'];
    var timetableList = document.getElementById("timetableList");

    axios.get('/timetable/details/' + selectedDate)
        .then((response) => {
            var childDivs = timetableList.querySelectorAll("div");

            childDivs.forEach(function (childDiv) {
                timetableList.removeChild(childDiv);
            });

            if(response.data.length == 0) {
                var timetable = document.createElement("div");

                timetable.innerHTML = "※ 시간표 정보가 없습니다.";
                timetableList.appendChild(timetable);
            } else {
                for(var i = 0; i < response.data.length; i++) {
                    console.log(response.data);
                    var timetable = document.createElement("div");

                    timetable.innerHTML = " " + response.data[i]['tstartT'].substr(0, 5) + " - " + response.data[i]['tendT'].substr(0, 5)
                        + " ( " + type[response.data[i]['ttype']] + "_" + response.data[i]['tpeople'] + "명)";

                    timetableList.appendChild(timetable);

                    // timetable.onclick = setMyReservation(timetable.);
                }
            }
            // setMyReservation(1);
        }).catch(error => {
            console.error(error);
        });
}