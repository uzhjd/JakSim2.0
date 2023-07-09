function setTrainerBrief(trainerId) {
    var gender;
    var trainerData;
    var url = '/trainer/briefInfo/' + trainerId;

    axios.get(url)
        .then((response) => {
            trainerData = response.data;

            if(trainerData['gender'] == "1")
                gender = "여자";
            else
                gender = "남자";

            // if(trainerData['expert1'])

            document.getElementById("trainerId").innerText = trainerData['userId'];
            document.getElementById("trainerName").innerText = "[ " + trainerData['userName'] + " 트레이너 ]";
            document.getElementById("gender").innerText = gender;
            document.getElementById("insta").href = trainerData['insta'];
            document.getElementById("intro").innerText = trainerData['intro'];
            document.getElementById("gym").innerText = trainerData['gym'];
            document.getElementById("expert").innerText = trainerData['expert1'] + ", " + trainerData['expert2'];
        })
        .catch(error => {
            console.error(error);
        });
}