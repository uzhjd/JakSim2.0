var time;
var requestId;
var validateTime;
var timeout;
var code;

window.onload = function(){
    time = 600;

    showValidTime();
    sendEmail();

    var checkButton = document.getElementById('validate_button');
    checkButton.addEventListener('click', checkCode);
}

function showValidTime(){
    validateTime = document.getElementById('find_validate_time');
    timeout = false;

    var startTime = Date.now();
    var endTime = startTime + time*1000;

    function update(){
        var currentTime = Date.now();
        var remainTime = Math.max(0, endTime - currentTime);

        var seconds = Math.floor(remainTime/1000);
        var min = Math.floor(seconds / 60);
        seconds = seconds%60;

        min = min.toString().padStart(2, '0');
        seconds = seconds.toString().padStart(2, '0');

        validateTime.innerHTML = `${min} : ${seconds}`;

        if(currentTime < endTime){
            requestId = requestAnimationFrame(update);
        }else{
            timeout = true;
            alert('시간이 초과되었습니다.');
        }
    }
    update();
}

function sendEmail(){
    var codeInput = document.getElementById('find_validate_input');
    axios.post('/find/api/email/action', {email : sessionStorage.getItem('userEmail')})
        .then(response => {
            code = response.data;
        })
        .catch(error => {
            console.error(error);
        })
}

function stopTimer(){
    cancelAnimationFrame(requestId);
}

function checkCode(){
    var userCode = document.getElementById('find_validate_input');
    console.log(timeout + " ;;; " + userCode.value);
    console.log(userCode.value === code);
    console.log(!timeout);
    if(userCode.value === code && !timeout){
        axios.post('/find/api/email/get', {email:sessionStorage.getItem('userEmail')})
            .then(response => {
                document.getElementById('find_validate_success').innerHTML = response.data['id'];
            })
            .catch(error => {
                document.getElementById('find_validate_fail').innerHTML = '데이터 호출에 실패했습니다.';
            })
    }else{
        document.getElementById('find_validate_fail').innerHTML = '인증에 실패했습니다.';
    }
}