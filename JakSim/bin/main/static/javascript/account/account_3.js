var answerCode = '';
var buttonCnt = 0;
var timeSpan, emailCheckInput, checkButton, time, intervalId, nextButton;
var isEmail = false, isPhone = false;

window.onload = function(){
    var emailCheckButton = document.getElementById('account_email_button');
    var phoneCheckButton = document.getElementById('account_phone_button');
    nextButton = document.getElementById('account_3_next');

    nextButton.addEventListener('click', () => {window.location.href='/account/4'});
    emailCheckButton.addEventListener('click', function(){
        sendMail(emailCheckButton); });
    phoneCheckButton.addEventListener('click', checkPhone);
};

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

  if(!intervalId){
    intervalId = setInterval(updateTimeSpan, 1000);
  }
}

function updateTimeSpan(){
    var min = Math.floor(time / 60);
    var seconds = time % 60;
    seconds = seconds.toString().padStart(2, '0');
    timeSpan.innerHTML = `${min} : ${seconds}`;
    if (time === 0) {
        clearInterval();
    } else {
        time--;
    }
}

function checkCode(){
    (answerCode === emailCheckInput.value) && (time !== 0) ? isEmail = true : isEmail = false;
    console.log(isEmail + " ;;; " + isPhone);
    isEmail && isPhone ? nextButton.disabled = false : nextButton.disabled = true;
}

function checkPhone(){
    var data = {tel : document.getElementById('account_tel').value};
    var phoneSpan = document.getElementById('account_phone_span');
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