function setDate(date) {
    document.getElementById("reservation_date").innerText = date;

    setMyReservation(date);
}

function setMyReservation(date) {
    var reservation = document.getElementById('reservation_list');
    var ptList = document.getElementById('trainer_list');
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

                    reservation.textContent = response.data['tstartT'] + " - " + response.data['tstartT'] + " ( " + type + " " + response.data['tpeolple'] +" )";
                } else {
                    if(response.data['ttype'] == 0) type = "상담"
                    else if(response.data['ttype'] == 1) type = "1:1"

                    reservation.textContent = response.data['tstartT'] + " - " + response.data['tstartT'] + " ( " + type + " )";
                }
            } else reservation.textContent = "";



            console.log(response.data);
        })
        .catch(error => {
            console.error(error);
        });
}