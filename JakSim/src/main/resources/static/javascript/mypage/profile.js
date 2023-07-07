var changeEmailButton;
var emailInput;
var validateDiv;
var requestId;
var timeSpan, validateFail, codeInput;
var timeout;
var code;

window.onload = function(){
    emailInput = document.getElementById('profile_emailInput');
    var checkFileButton = document.getElementById('checkFile');
    changeEmailButton = document.getElementById('profile_change_email');
    validateDiv = document.getElementById('profile_validate_div');

    checkFileButton.addEventListener('click', changeProfileImage);
    changeEmailButton.addEventListener('click', checkEmail);
}

function showValidateTime(){
    var startTime = Date.now();
    var endTime = startTime + 10 * 1000;

    function update(){
        var currentTime = Date.now();
        var remainTime = Math.max(0, endTime - currentTime);

        var seconds = Math.floor(remainTime/1000);
        var min = Math.floor(seconds/60);
        seconds %= 60;

        min = min.toString().padStart(2, '0');
        seconds = seconds.toString().padStart(2, '0');

        timeSpan.innerHTML = `${min} : ${seconds}`;

        if(currentTime < endTime){
            requestId = requestAnimationFrame(update);
            validateFail.innerHTML = '';
        }else{
            timeout = true;
            timeSpan.innerHTML = '';
            validateFail.innerHTML = '인증코드를 다시 확인해주세요';
        }
    }
    update();
}

function stopTimer(){
    cancelAnimationFrame(requestId);
}

function checkEmail(){
    console.log(emailInput.value);
    axios.post('/mypage/api/email/get', {email: emailInput.value})
        .then(response=>{
            if(response.data){
                document.getElementById('profile_dup_span').innerHTML = '';
                validateEmail();
            }else{
                document.getElementById('profile_dup_span').innerHTML = '이미 등록된 이메일입니다.';
            }
        })
        .catch(error =>{
            console.error(error);
        })
}

function validateEmail(){
    codeInput = document.createElement('input');
    var codeCheckButton = document.createElement('button');
    validateFail = document.createElement('span');
    timeSpan = document.createElement('span');
    timeout = false;

    codeCheckButton.classList.add('jaksim_btn');
    codeCheckButton.textContent = '확인';
    codeCheckButton.addEventListener('click', codeCheck);
    timeSpan.style.color = 'red';

    validateFail.style.color = 'red';

    axios.post('/mypage/api/email/check', {email: emailInput.value})
        .then(response => {
            code = response.data;
        })
        .catch(error => {
            console.error(error);
    });

    validateDiv.appendChild(codeInput);
    validateDiv.appendChild(codeCheckButton);
    validateDiv.appendChild(timeSpan);
    validateDiv.appendChild(validateFail);

    showValidateTime();
}

function codeCheck(){
    if(code === codeInput.value && !timeout){
        updateEmail();
    }else{
        validateFail.innerHTML = '인증번호를 다시 확인해주세요';
    }
}

function updateEmail(){
    axios.put('/mypage/api/profile/update/email', {email: emailInput.value})
        .then(response => {
            if(response.data){
                window.location.href='/';
                alert('이메일이 변경되었습니다.');
            }else{
                alert('어라?');
            }
        })
        .catch(error => {
            console.error(error);
        })
}

function changeProfileImage(){
    var fileInput = document.getElementById('profile_imgInput');
    var file = fileInput.files[0];

    var formData = new FormData();
    formData.append('file', file);

    axios.put('/mypage/api/profile/update', formData, {headers: {'Content-Type' : 'multipart/form-data'}})
        .then(response => {
            if(response.data){
                window.location.href='/';
            }
        })
        .catch(error => {
            console.error(error);
        })
}

function readURL(input){
    if(input.files && input.files[0]){
        var reader = new FileReader();
        reader.onload = function(e){
            document.getElementById('profile_preview').src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    }else{
        document.getElementById('profile_preview').src = '';
    }
}