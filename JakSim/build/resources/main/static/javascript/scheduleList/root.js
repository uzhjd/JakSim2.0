var resBtn, canBtn;
var selectedPIdx = -1;
var selectedRIdx = -1;
var selectedTType = -1;
var radioBox;

window.onload = function () {
    resBtn = document.getElementById('reservationBtn');
    resBtn.addEventListener('click', () => resRegister(formattedDate));
    canBtn = document.getElementById('resCancleBtn');
    canBtn.addEventListener('click', () => resCancle());

    // button.addEventListener('click', () => resRegister(pIdx, selectedTIdx, trainerId, formattedDate, ptCnt, tType));
    setPtList();
}