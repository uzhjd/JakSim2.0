function setPtList() {
    var ptList = document.getElementById('trainer_list');
    ptList.addEventListener("change", () => changePtList(ptList.options[ptList.selectedIndex], 1));

    axios.get('/product/myPt')
        .then((response) => {
            var ptData = response.data;
            var ptCnt = ptData[0]['pptCnt'];

            for(var i = 0; i < ptData.length; i++) {
                var option = document.createElement("option");

                option.text = ptData[i]['trainerName'] + " 트레이너";
                option.value = JSON.stringify({pptCnt : ptData[i]['pptCnt'], trainerId : ptData[i]['trainerId']});

                ptList.appendChild(option);
            }

            changePtList(ptData[0], 0);
        })
        .catch(error => {
            console.error(error);
        });

}

// pt권을 변경하는 코드
function changePtList(selectedPt, type) {
    // console.log(selectedPt);

    if(type === 0){
        setPtCnt(selectedPt['pptCnt']);
        setTrainerBrief(selectedPt['trainerId']);
    }
    else if (type === 1) {
        // console.log(JSON.parse(selectedPt.value).pptCnt);
        var cnt = JSON.parse(selectedPt.value).pptCnt;
        var trainerId = JSON.parse(selectedPt.value).trainerId;
        console.log(typeof(selectedPt));

        setPtCnt(cnt);
        setTrainerBrief(trainerId);
    }
    buildCalendar();

}



function setPtCnt(ptCnt) {
    var cnt = document.getElementById("pt_cnt");

    cnt.innerHTML = "남은 PT 횟수 : " + ptCnt + "회";
}



