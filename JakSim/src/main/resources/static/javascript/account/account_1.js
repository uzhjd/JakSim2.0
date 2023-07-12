var accountNextButton;
var isLength = false, isCheck = false;

window.onload = function(){
    var idCheckButton = document.getElementById('account_id_check');
    accountNextButton = document.getElementById('account_1_next');

    idCheckButton.addEventListener('click', idCheck);
};

function idCheck(){
    var userId = document.getElementById('account_userid');
    var data = {id : userId.value};
    var result = document.getElementById('account_id_result')
    console.log(userId);

    axios.post('/account/checkid', data)
        .then(response => {
            if(response.data !== 0){
                result.style.color='red';
                result.innerHTML = '아이디가 중복입니다.';
            }else{
                result.innerHTML = '';
                sessionStorage.setItem('id', document.getElementById('account_userid').value);
                passwordDiv(document.getElementById('account_span_container'), document.getElementById('account_input_container'));
            }
        })
        .catch(error => {
            console.error(error);
        })
};

function passwordDiv(spanContainer, inputContainer){
    var passwordSpan = document.createElement('span');
    var passwordInput = document.createElement('input')
    var confirmPasswordInput = document.createElement('input');
    var failMessage = document.createElement('span');
    var failDiv = document.getElementById('account_span_message_container');

    passwordSpan.innerHTML = '비밀번호';
    passwordSpan.classList.add('account_span');

    passwordInput.classList.add('account_input');
    passwordInput.type = 'password';

    confirmPasswordInput.classList.add('account_input');
    confirmPasswordInput.type = 'password';

    failMessage.style.color = 'red';

    passwordInput.addEventListener('input', function(event){
        password = event.target.value;
        if(password.length < 8){
            failMessage.innerHTML = '8자 이상 작성해주세요';
            failMessage.style.color = 'red';
            isLength = false;
        }else{
            failMessage.innerHTML = '';
            isLength = true;
        }
        isNext();
    })
    confirmPasswordInput.addEventListener('input', function(event){
        confirm = event.target.value;
        if(confirm === passwordInput.value){
            isCheck = true;
            sessionStorage.setItem('pw', confirm);
            failMessage.innerHTML = '비밀번호 확인이 완료되었습니다.';
            failMessage.style.color = 'blue';
        }else{
            isCheck = false;
            failMessage.innerHTML = '비밀번호를 확인해주세요';
            failMessage.style.color = 'red';
        }
        isNext();
    })

    spanContainer.appendChild(passwordSpan);
    inputContainer.appendChild(passwordInput);
    inputContainer.appendChild(confirmPasswordInput);
    failDiv.appendChild(failMessage);
}

function isNext(){
    if(isCheck && isLength){
        accountNextButton.disabled = false;
    }else{
        accountNextButton.disabled = true;
    }
}

function checkPassowrd(pw, confirmPwd){
    return pw === confirmPwd;
}
