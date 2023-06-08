var registerData = {};

window.onload = function(){
    var button = document.getElementById('myButton');
    button.addEventListener('click', handleClick);
};

function handleClick(){
        makeJSON();
        axios.post('/index', registerData)
            .then(response => {
                if(response.data == true){
                    window.location.href='/';
                }else
                    alert('데이터를 다시 확인해주세요');
            })
            .catch(error => {
                console.error(error);
            });
    }

var makeJSON = function(){
    registerData.name = document.getElementById('account_name').value
    registerData.number = document.getElementById('account_number').value;

    JSON.stringify(registerData); //data to Json
}