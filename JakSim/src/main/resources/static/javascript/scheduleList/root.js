var button;
var selectedTIdx = -1;


window.onload = function () {
    button = document.getElementById('reservationBtn');
    button.addEventListener('click', () => resRegister(selectedTIdx, formattedDate));
    // button.addEventListener('click', () => resRegister(pIdx, selectedTIdx, trainerId, formattedDate, ptCnt, tType));
    setPtList();
}