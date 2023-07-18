var time;
var requestId;
var validateTime;
var timeout;
var code;

window.onload = function(){
    emailInit();
    emailProcess();

    var checkButton = document.getElementById('validate_button');
    checkButton.addEventListener('click', checkCode);

    var resendButton = document.getElementById('resend_button');
    resendButton.addEventListener('click', emailProcess);
}

function emailProcess(){
    showValidTime();
    sendMail();
}

function emailInit(){
    time = 600;
    timeSpan = document.getElementById('find_validate_time');
    emailResult = document.getElementById('find_validate_fail');
    sessionEmail = sessionStorage.getItem('userEmail');
    code = document.getElementById('find_validate_input');
}

function next(result){
    if(result === false){
        return ;
    }
    axios.post('/find/api/email/get', {email:sessionStorage.getItem('userEmail')})
        .then(response => {
            sessionStorage.clear();
            document.getElementById('find_validate_success').innerHTML = response.data['id'];
        })
        .catch(error => {
            emailResult.innerHTML = '데이터 호출에 실패했습니다.';
        })
}

function afterSend(){
    console.log('이메일 전송 완료');
}
