window.onload = function(){
    var pwButton = document.getElementById('pwButton');
    pwButton.addEventListener('click', checkPassword);
}

var checkPassword = function(){
    var data = {pw : document.getElementById('pwInput').value}
    var span = document.getElementById('mypage_failureSpan');

    axios.post('/mypage/api/auth', data)
        .then(response => {
            if(response.data !== ''){
                window.location.href='/mypage/' + response.data;
            }else{
                span.innerHTML = '비밀번호를 다시 확인해주세요';
                span.style.color='red';
            }
        })
        .catch(error => {
            console.error(error);
        });
}
