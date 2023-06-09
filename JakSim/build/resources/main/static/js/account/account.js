var registerData = {};

window.onload = function(){
    var button = document.getElementById('myButton');
    button.addEventListener('click', handleClick);
};

function handleClick(){
        UserToJSON();
        axios.post('/account/action', registerData)
            .then(response => {
                if(response.data === 0){
                    window.location.href='/';
                }else if(response.data === -1){
                    alert('데이터 작성을 완료해주세요');
                }else if(response.data === 1){
                    alert('회원가입이 진행되지 않았습니다.\n다시 시도해주시기 바랍니다.');
                }
            })
            .catch(error => {
                console.error(error);
            });
    }

var UserToJSON = function(){
    registerData.name = document.getElementById('account_name').value
    registerData.number = document.getElementById('account_number').value;

    JSON.stringify(registerData); //data to Json
}