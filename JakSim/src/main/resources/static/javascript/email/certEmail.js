var emailInput; //이메일 입력한 곳
var emailDupSpan; //중복 여부 나타내주는 span
var emailCheckButton; //'인증' 버튼
var answerCode; // 발송했던 '인증번호'
var parentDiv; // 생성할 태그들 넣을 div
var timeout, time, timeSpan; //타임아웃 여부, 시간값, 화면에 나타낼 시간값
var emailResult;

function checkEmailFormat(){
    var pattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    return pattern.test(emailInput.value);
}

function isDuplicated(){
    axios.post('/account/checkemail', {email:emailInput.value})
        .then(response => {
            //return boolean
            if(response.data){
                emailDupSpan.innerHTML = '';
                sendMail()
            }else{
                emailDupSpan.innerHTML = '이미 등록된 이메일입니다.'
                emailDupSpan.style.color = 'red';
            }
        })
        .catch(error => {
            console.error(error);
        })
}

function sendMail(){
    alert('인증번호를 전송했습니다.');
    axios.post('/account/emailaction', {email: emailInput.value})
        .then(response => {
            answerCode = response.data;
            createTags();
        })
        .catch(error => {
            console.error(error);
        })
}

function createTags(){
    parentDiv = document.getElementById('account_code_container');
    time = 180;
    var html = '';
    html += '<input id="email_input" class="email_input" >';
    html += '<button class="jaksim_btn" onclick="sendMail()">재전송</button>';
    html += '<button class="jaksim_btn" onclick="checkCode()" style="margin: 10px;">확인</button>';
    html += '<span class="email_time_font" id="timeSpan"></span>';
    html += '<div><p id="email_result"></div>';
    parentDiv.innerHTML = html;

    timeSpan = document.getElementById('timeSpan');
    emailResult = document.getElementById('email_result');

    showValidTime();
}

function showValidTime(){
    timeout = false;

    var startTime = Date.now();
    var endTime = startTime + time * 1000;

    function update(){
        var currentTime = Date.now();
        var remainTime = Math.max(0, endTime - currentTime);

        var seconds = Math.floor(remainTime / 1000);
        var min = Math.floor(seconds/60);
        seconds %= 60;

        min = min.toString().padStart(2, '0');
        seconds = seconds.toString().padStart(2, '0');

        timeSpan.innerHTML = `${min} : ${seconds}`;

        if(currentTime < endTime){
            requestId = requestAnimationFrame(update);
        }else{
            timeout = true;
            emailResult.innerHTML = '시간이 초과되었습니다.'
            emailResult.style.color = 'red';
        }
    }
    update();
}

function stopTimer(){
    cancelAnimationFrame(requestId);
}

function checkCode(){
    var code = document.getElementById('email_input');
    var result;
    if(answerCode === code.value){
        stopTimer();
        emailResult.innerHTML = '이메일이 확인되었습니다.';
        emailResult.style.color='blue';
        result = true;
    }else{
        emailResult.innerHTML = '인증번호를 다시 확인해주세요';
        emailResult.style.color = 'red';
        result = false;
    }

    next(result);
}