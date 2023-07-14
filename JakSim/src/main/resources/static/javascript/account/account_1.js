var accountNextButton;
var isLength = false, isCheck = false, isUsername = false, isUsernameLength = false;
var userId;
var divCnt=0, isDiv=false;
var spanContainer, inputContainer, passwordSpan, passwordInput, confirmPasswordInput, failMessage, failDiv;

window.onload = function(){
    accountNextButton = document.getElementById('account_1_next');
    spanContainer = document.getElementById('account_span_container');
    inputContainer = document.getElementById('account_input_container');

    userId = document.getElementById('account_userid');
    userId.addEventListener('input', idCheck);
};

function idCheck(){
    var result = document.getElementById('account_id_result');
    var pattern =  /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$/;
    if(userId.value.length > 4){
        if(pattern.test(userId.value)){
            isUsernameLength = true;
            axios.post('/account/checkid', {id : userId.value})
                .then(response => {checkId(response.data, result);})
                .catch(error => {console.error(error);});
        }else{
            result.innerHTML = '영문과 숫자를 포함해서 작성해주세요';
            result.style.color = 'red';
        }
    }else{
        result.innerHTML = '5자 이상 작성해주세요';
        result.style.color = 'red';
        isUsernameLength = false;
        isNext();
    }
};

function checkId(res, text){
    if(res === 1){
        text.style.color = 'red';
        text.innerHTML = '해당 아이디는 사용할 수 없습니다.';
        divCnt = 0;
        if(isDiv){
            removePasswordDiv();
        }
        isUsername = false;
    }else{
        text.innerHTML = '아이디가 확인되었습니다.';
        text.style.color='blue';
        sessionStorage.setItem('id', document.getElementById('account_userid').value);
        if(divCnt < 1){
            passwordDiv();
        }
        divCnt += 1;
        isUsername = true;
    }

    isNext();
}

function passwordDiv(){
    passwordSpan = document.createElement('span');
    passwordInput = document.createElement('input')
    confirmPasswordInput = document.createElement('input');
    failMessage = document.createElement('span');
    failDiv = document.getElementById('account_span_message_container');

    passwordSpan.innerHTML = '비밀번호';
    passwordSpan.classList.add('account_span');
    passwordSpan.classList.add('jaksim_font');

    passwordInput.classList.add('account_input');
    passwordInput.type = 'password';
    passwordInput.placeholder = '비밀번호를 입력해주세요';

    confirmPasswordInput.classList.add('account_input');
    confirmPasswordInput.type = 'password';
    confirmPasswordInput.placeholder = '비밀번호 확인';

    failMessage.style.color = 'red';

    passwordInput.addEventListener('input', function(event){
        password = event.target.value;
        if(password.length < 7){
            checkPassword(failMessage);
            isLength = false;
        }else{
            checkPassword(failMessage);
            isLength = true;
        }
        isNext();
    })
    confirmPasswordInput.addEventListener('input', function(event){
        confirm = event.target.value;
        if(confirm === passwordInput.value){
            isCheck = true;
            sessionStorage.setItem('pw', confirm);
            checkPassword(failMessage);
        }else{
            isCheck = false;
            checkPassword(failMessage);
        }
        isNext();
    })

    spanContainer.appendChild(passwordSpan);
    inputContainer.appendChild(passwordInput);
    inputContainer.appendChild(confirmPasswordInput);
    failDiv.appendChild(failMessage);

    isDiv = true;
}

function removePasswordDiv(){
    spanContainer.removeChild(passwordSpan);
    inputContainer.removeChild(passwordInput);
    inputContainer.removeChild(confirmPasswordInput);
    failDiv.removeChild(failMessage);

    isDiv = false;
}

function isNext(){
    if(isCheck && isLength && isUsername && isUsernameLength){
        accountNextButton.disabled = false;
    }else{
        accountNextButton.disabled = true;
    }
}

function checkPassword(fail){
    if(isLength && isCheck){
        fail.innerHTML = '비밀번호 확인이 완료되었습니다.';
        fail.style.color = 'blue';
    }else if(!isLength){
         fail.innerHTML = '비밀번호는 8자 이상이어야 합니다.';
         fail.style.color = 'red';
     }else if(!isCheck){
        fail.innerHTML = '비밀번호가 일치하지 않습니다.';
        fail.style.color = 'red';
    }else{
        fail.innerHTML = '';
    }
}
