function setTimetable(trainerId, date, tType) {
    var formattedDate = date.split(". ").join("-");

    const data = {
        trainerId: trainerId,
        dt: formattedDate,
        tType: tType
    }
console.log(data);
    axios.post('/timetable/details', data)
        .then((response) => {
            console.log(response.data);
        })
        .catch(error => {
            console.error(error);
        });
}