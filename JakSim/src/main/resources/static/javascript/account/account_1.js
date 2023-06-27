window.onload = function(){
    var idCheckButton = document.getElementById('account_id_check');
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
                passwordDiv(document.getElementById('account_span_container'), document.getElementById('account_password_container'));
            }
        })
        .catch(error => {
            console.error(error);
        })

        //why?
};

function passwordDiv(spanContainer, inputContainer){
    var passwordSpan = document.createElement('span');
    var passwordInput = document.createElement('input')
    var confirmPasswordInput = document.createElement('input');
    var passwordCheckButton = document.createElement('button');

    passwordSpan.innerHTML = '비밀번호';
    passwordSpan.classList.add('account_span');

    passwordInput.classList.add('account_input');
    passwordInput.type = 'password';

    confirmPasswordInput.classList.add('account_input');
    confirmPasswordInput.type = 'password';

    passwordCheckButton.textContent = '비밀번호 확인';
    passwordCheckButton.classList.add('account_button');
    passwordCheckButton.addEventListener('click', function(){
        if(checkPassowrd(passwordInput.value, confirmPasswordInput.value)){
            document.getElementById('account_1_next').disabled=false;
        }else{
            document.getElementById('account_1_next').disabled=true;
        }
    });

    spanContainer.appendChild(passwordSpan);
    inputContainer.appendChild(passwordInput);
    inputContainer.appendChild(confirmPasswordInput);
    inputContainer.appendChild(passwordCheckButton);
}

function checkPassowrd(pw, confirmPwd){
    return pw === confirmPwd;
}