var answerCode = '';
var buttonCnt = 0;
var timeSpan, emailCheckInput, checkButton, time, intervalId, nextButton, phoneSpan;
var isEmail = false, isPhone = false;
var emailCheckButton;
var timeout;
var requestId;

window.onload = function(){
    emailCheckButton = document.getElementById('account_email_button');
    var phoneCheckButton = document.getElementById('account_phone_button');
    nextButton = document.getElementById('account_3_next');

    nextButton.addEventListener('click', nextPage);
    emailCheckButton.addEventListener('click', function(){
        dupMail();
    });
    phoneCheckButton.addEventListener('click', checkPhone);
};

function dupMail(){
    axios.post('/account/checkemail', {email : document.getElementById('account_email').value})
        .then(response => {
            if(response.data){
                alert('이메일이 전송되었습니다.');
                document.getElementById('account_check_dupmail').innerHTML = '';
                sendMail(emailCheckButton);
            }else{
                document.getElementById('account_check_dupmail').innerHTML = '이미 등록된 이메일입니다.';
                document.getElementById('account_check_dupmail').style.color = 'red';
            }
        })
        .catch(error => {
            console.error(error);
        });
}

function nextPage(){
    sessionStorage.setItem('email', document.getElementById('account_email').value);
    sessionStorage.setItem('tel', document.getElementById('account_tel').value);
    window.location.href='/account/4';
}

function sendMail(){
    var email = {email: document.getElementById('account_email').value};
    axios.post('/account/emailaction', email)
        .then(response => {
            createCheckDiv(document.getElementById('account_code_container'));
            answerCode = response.data;
        })
        .catch(error => {
            console.error(error);
        })
        .finally(() => {
            console.log('Fin Axios');
        });
    buttonCnt ++;
}

function createCheckDiv(container) {
  if (!emailCheckInput) {
    emailCheckInput = document.createElement('input');
    emailCheckInput.classList.add('account_input');
    container.appendChild(emailCheckInput);
  }

  if (!checkButton) {
    checkButton = document.createElement('button');
    checkButton.classList.add('account_button');
    checkButton.textContent = '확인';
    checkButton.style.marginLeft = '10px';
    checkButton.addEventListener('click', function () {
      checkCode();
    });
    container.appendChild(checkButton);
  }

  if (!timeSpan) {
    timeSpan = document.createElement('span');
    timeSpan.style.color = 'red';
    timeSpan.style.marginLeft = '10px';
    container.appendChild(timeSpan);
  }

  time = 180;

  showValidTime();
}

function showValidTime(){
    timeout = false;

    var startTime = Date.now();
    var endTime = startTime + time*1000;

    function update(){
        var currentTime = Date.now();
        var remainTime = Math.max(0, endTime - currentTime);

        var seconds = Math.floor(remainTime/1000);
        var min = Math.floor(seconds/60);
        seconds = seconds%60;

        min = min.toString().padStart(2, '0');
        seconds = seconds.toString().padStart(2, '0');

        timeSpan.innerHTML = `${min} : ${seconds}`;

        if(currentTime < endTime){
            requestId = requestAnimationFrame(update);
        }else{
            timeout = true;
            alert('시간이 초과되었습니다.');
        }
    }
    update();
}

function stopTimer(){
    cancelAnimationFrame(requestId);
}


function checkCode(){
    (answerCode === emailCheckInput.value) && (time !== 0) ? isEmail = true : isEmail = false;
    if(!isEmail || timeout){
        document.getElementById('account_codeCheck_fail').innerHTML = '인증번호를 다시 확인해주세요';
    }
    else{
        document.getElementById('account_codeCheck_fail').innerHTML = '';
    }
    isEmail && isPhone ? nextButton.disabled = false : nextButton.disabled = true;
}

function checkPhone(){
    var data = {tel : document.getElementById('account_tel').value};
    phoneSpan = document.getElementById('account_phone_span');
    axios.post('/account/checktel', data)
        .then(response => {
            console.log(response.data);
            if(response.data === 0){
                isPhone = true;
                phoneSpan.innerHTML = '확인 되었습니다.';
                phoneSpan.style.color='black';
                isEmail && isPhone ? nextButton.disabled = false : nextButton.disabled = true;
            }else{
                phoneSpan.innerHTML = '등록된 번호 입니다.';
                phoneSpan.style.color='red';
                isPhone = false;
                isEmail && isPhone ? nextButton.disabled = false : nextButton.disabled = true; 
            }
        })
        .catch(error => {console.error(error)})
}