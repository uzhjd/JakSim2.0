var userData = {};
window.onload = function() {
    var checkTel = document.getElementById('find_checkTel');
    var checkAnswer = document.getElementById('find_checkAnswer');
    var changePwButton = document.getElementById('find_pwButton');
    var confirmPwButton = document.getElementById('find_checkPwButton');

    checkTel.addEventListener('click', telListener);
    checkAnswer.addEventListener('click', answerListener);
    changePwButton.addEventListener('click', changePassword);
    confirmPwButton.addEventListener('click', confirmPassword);
}

function telListener(){
    var data = {tel : document.getElementById('find_tel').value};
    var result = document.getElementById('find_tel_result');
    var question = document.getElementById('find_question');

    JSON.stringify(data);

    axios.post('/account/findtel', data)
        .then(response => {
            userData = response.data;
            if(response.data !== null){
                var questionSpan = document.getElementById('find_question');
                questionSpan.innerHTML = switchQuestion(userData['question']);
                questionSpan.style.color = 'black';
            }
        })
        .catch(error => {
            console.error(error);
        });
}

var confirmPassword = function(){
    var password = document.getElementById('find_password').value;
    var confirmPassword = document.getElementById('find_confirmPassword').value;

    if(password === confirmPassword){
        console.log('비밀번호 확인이 완료되었습니다.');
    }
}

var changePassword = function(){
    var pwData = {
        id : userData['id'],
        pw : document.getElementById('find_password').value
    };

    axios.put('/account/changepw', pwData)
        .then(response => {
            if(response.data > 0){
                console.log('비밀번호 저장 성공');
                alert('비밀번호 변경이 성공했습니다.\n다시 로그인을 시도해주세요.');
                window.location.href="/login";
            }
        })
        .catch(error => {
            console.error(error);
        })
}

function answerListener(){
    var answer = document.getElementById('find_inputAnswer');
    var userId = document.getElementById('find_userId');
    console.log(userData['answer']);
    if(userData['answer'] === answer.value){
        console.log('정답 맞네예');
        userId.innerHTML = userData['id'];
    }else{
        console.log('아이라는데?');
    }
}

var switchQuestion = function(question){
    var result = '';
    switch(question){
        case 0:
            result = '당신이 태어난 장소는?';
            break;
        case 1:
            result = '졸업한 초등학교 이름은?';
            break;
        case 2:
            result = '가장 소중한 보물 1호는?'
            break;
        case 3:
            result = '당신의 첫 해외여행지는?';
            break;
        case 4:
            result = '가장 존경하는 인물은?';
            break;
        default:
            break;
    }
    return result;
}

