var resBtn, canBtn;
var selectedTIdx = -1;
var selectedPIdx = -1;
var selectedRIdx = -1;
var selectedTType = -1;


window.onload = function () {
    resBtn = document.getElementById('reservationBtn');
    resBtn.addEventListener('click', () => resRegister(selectedTIdx, formattedDate));
    canBtn = document.getElementById('resCancleBtn');
    canBtn.addEventListener('click', () => resCancle(selectedPIdx, selectedRIdx, selectedTType));

    // button.addEventListener('click', () => resRegister(pIdx, selectedTIdx, trainerId, formattedDate, ptCnt, tType));
    setPtList();
}