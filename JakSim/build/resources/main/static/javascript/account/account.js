var registerData = {};

window.onload = function(){
    var button = document.getElementById('myButton');
    button.addEventListener('click', handleClick);
};

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