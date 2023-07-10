function setTimetable(id, date, tType) {
    var formattedDate = date.split(". ").join("-");

    const data = {
        trainerId: id,
        dt: formattedDate,
        type: tType
    }

    axios.post('/timetable/details', data)
        .then((response) => {
            console.log("response.data");
            console.log(response.data);
        })
        .catch(error => {
            console.error(error);
        });
}