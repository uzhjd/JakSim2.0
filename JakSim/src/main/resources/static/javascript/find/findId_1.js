var checkButton;
var checkSpan;
window.onload = function(){
    checkButton = document.getElementById('find_valid_useridCheck');
    checkSpan = document.getElementById('find_valid_span');

    checkButton.addEventListener('click', checkEmail);
}

function checkEmail(){
    var emailData = document.getElementById('find_valid_email').value;
    axios.post('/find/api/email', {email: emailData})
        .then(response => {
            if(response.data === false){
                checkSpan.innerHTML = '등록되지 않은 이메일입니다.';
                checkSpan.style.color = 'red';
            }else{
                sessionStorage.setItem('userEmail', emailData);
                window.location.href = '/find/userid/cert';
            }
            console.log(response.data);
        })
        .catch(error => {
            console.error(error);
        });
}