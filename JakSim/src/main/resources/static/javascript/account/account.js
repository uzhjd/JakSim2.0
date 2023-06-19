var registerData = {};

window.onload = function(){
    var button = document.getElementById('myButton');
    var passwordCheckButton = document.getElementById('account_checkPw');
    var idCheckButton = document.getElementById('account_checkId');
    var telCheckButton = document.getElementById('account_checkTel');

    button.addEventListener('click', handleClick);
    passwordCheckButton.addEventListener('click', checkPassword);
    idCheckButton.addEventListener('click', idDupId);
    telCheckButton.addEventListener('click', isDupTel)
};

function isDupTel(){
    var data = {tel : document.getElementById('account_tel').value};
    var result = document.getElementById('account_confirm_tel');
    JSON.stringify(data);
    axios.post('/account/checktel', data)
        .then(response => {
            if(response.data !== 0){
                result.style.color='red';
                result.innerHTML = '이미 등록된 전화번호입니다.';
            }else{
                result.style.color='black';
                result.innerHTML='전화번호가 확인되었습니다.';
            }
        })
        .catch(error =>{
            console.error(error);
        });
}

function idDupId(){
    var data = {id : document.getElementById('account_id').value};
    var result = document.getElementById('account_confirm_id');
    JSON.stringify(data);
    axios.post('/account/checkid', data)
        .then(response => {
            if(response.data !== 0){
                result.style.color='red';
                result.innerHTML = '아이디가 중복입니다.';
            }else{
                result.style.color='black';
                result.innerHTML='사용가능한 아이디입니다.';
            }
        })
        .catch(error =>{
            console.error(error);
        });
}

function handleClick(){
    UserToJSON();
    axios.post('/account/action', registerData)
        .then(response => {
            console.log('Result: ' + response.data);
            if(response.data === 0){
                alert('회원가입이 진행되지 않았습니다.\n다시 시도해주시기 바랍니다.');
            }else if(response.data === -1){
                alert('데이터 작성을 완료해주세요');
            }else if(response.data === 1){
                window.location.href='/';
            }
        })
        .catch(error => {
            console.error(error);
        });
}
function checkPassword(){
    pwd = document.getElementById('account_pw').value;
    confirmPwd = document.getElementById('account_confirm').value;
    var text = document.getElementById('account_confirm_span');
    if(pwd !== confirmPwd){
        text.innerHTML = '비밀번호가 맞지 않습니다.';
        text.style.color = 'red';
    }else{
        text.innerHTML = '비밀번호가 확인되었습니다.';
        text.style.color='black';
    }
}

var UserToJSON = function(){
    registerData.id = document.getElementById('account_id').value
    registerData.pw = document.getElementById('account_pw').value
    registerData.name = document.getElementById('account_name').value
    registerData.gender = document.getElementById('account_gender').value
    registerData.tel = document.getElementById('account_tel').value
    registerData.question = document.getElementById('account_question').value
    registerData.answer = document.getElementById('account_answer').value
    registerData.birth = document.getElementById('account_birth').value
    registerData.role = document.getElementById('account_role').value

    JSON.stringify(registerData);

    console.log(registerData)//data to Json
}