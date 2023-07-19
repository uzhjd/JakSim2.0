var isEmail = false, isPhone = false;
var emailCheckButton;


window.onload = function(){
    emailCheckButton = document.getElementById('account_email_button');
    var phoneCheckButton = document.getElementById('account_phone_button');
    nextButton = document.getElementById('account_3_next');

    nextButton.addEventListener('click', nextPage);
    phoneCheckButton.addEventListener('click', checkPhone);

    emailInit();

    emailCheckButton.addEventListener('click', function(){
        if(checkEmailFormat()){
            isDuplicated();
        }
    });
};

function afterSend(){
    createTags();
}

function emailInit(){
    emailInput = document.getElementById('account_email');
    emailDupSpan = document.getElementById('account_check_dupmail');
    parentDiv = document.getElementById('account_code_container');
    time = 180;
}

function nextPage(){
    sessionStorage.setItem('email', document.getElementById('account_email').value);
    sessionStorage.setItem('tel', document.getElementById('account_tel').value);
    window.location.href='/account/4';
}

function afterConfirm(emailResult){
    (emailResult) && (!timeout) ? isEmail = true : isEmail = false;
    console.log(emailResult);
    console.log(emailResult && !timeout);
    isEmail && isPhone ? nextButton.disabled = false : nextButton.disabled = true;
}

function checkPhone(){
    var data = {tel : document.getElementById('account_tel').value};
    phoneSpan = document.getElementById('account_phone_span');
    var pattern = /^[0-9]+$/;

    console.log(data['tel'] + " ::: " + data['tel'].length);

    if(data['tel'].length < 6 || !pattern.test(data['tel'])){
        phoneSpan.innerHTML = '전화번호를 다시 확인해주세요'
        phoneSpan.style.color = 'red';
    }else{
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
}