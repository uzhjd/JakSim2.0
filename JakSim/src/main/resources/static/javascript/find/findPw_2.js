var codeInput, checkButton, userEmail, failSpan, changeDiv;
var code;
var requestId;
var timeout;

window.onload = function(){
    codeInput = document.getElementById('find_pw_validInput');
    checkButton = document.getElementById('find_pw_validButton');
    userEmail = sessionStorage.getItem('userEmail');
    failSpan = document.getElementById('find_pw_validFail');
    changeDiv = document.getElementById('find_pw_changePwDiv');

    sendEmail();
    showValidTime();

    checkButton.addEventListener('click', checkCode);
};

function createTags(){
    var pwSpan = document.createElement('span');
    var pwInput = document.createElement('input');
    var confirmPwInput = document.createElement('input');
    var pwCheckButton = document.createElement('button');
    var failSpan = document.createElement('span');

    pwSpan.innerHTML = '비밀번호';

    pwInput.type='password';

    confirmPwInput.type='password';

    pwCheckButton.textContent = '비밀번호 확인';
    pwCheckButton.classList.add('jaksim_btn');
    pwCheckButton.addEventListener('click', function(){
        if(pwInput.value === confirmPwInput.value){
            var sendData = {id: sessionStorage.getItem('userId'), pw: pwInput.value}
            JSON.stringify(sendData);
            axios.put('/account/changepw', sendData)
                .then(response => {
                    if(response.data > 0){
                        alert('비밀번호가 변경되었습니다.');
                        window.location.href = '/login';
                    }
                })
                .catch(error => {
                    console.error(error);
                })
        }else{
            failSpan.innerHTML = '비밀번호를 다시 확인해주세요';
        }
    });

    changeDiv.appendChild(pwSpan);
    changeDiv.appendChild(pwInput);
    changeDiv.appendChild(confirmPwInput);
    changeDiv.appendChild(pwCheckButton);
    changeDiv.appendChild(failSpan);
}

function checkCode(){
    if((code === codeInput.value) && !timeout){
        sessionStorage.removeItem('userEmail');
        stopTimer();
        createTags();
    }else{
        failSpan.innerHTML = '인증시간이 만료되었습니다.';
    }
}

function showValidTime(){
    validateTime = document.getElementById('find_pw_validTime');
    timeout = false;

    var startTime = Date.now();
    var endTime = startTime + 600 * 1000;

    function update(){
        var currentTime = Date.now();
        var remainTime = Math.max(0, endTime - currentTime);

        var seconds = Math.floor(remainTime/1000);
        var min = Math.floor(seconds/60);
        seconds %= 60;

        min = min.toString().padStart(2, '0');
        seconds = seconds.toString().padStart(2, '0');

        validateTime.innerHTML = `${min} : ${seconds}`;

        if(currentTime < endTime){
            failSpan.innerHTML = '';
            requestId = requestAnimationFrame(update);
        }else{
            timeout = true;
            failSpan.innerHTML = '인증시간이 만료되었습니다.';
        }
    }
    update();
}

function stopTimer(){
    cancelAnimationFrame(requestId);
}

function sendEmail(){
    axios.post('/find/api/email/action', {email: userEmail})
        .then(response => {
            code = response.data;
        })
        .catch(error => {
            console.error(error);
        })
}

