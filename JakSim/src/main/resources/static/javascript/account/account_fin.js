window.onload = function(){
    axiosAction();
}

function axiosAction(){
    axios.post('/account/action', createJSON())
        .then(response => {
            console.log(response.data);
            sessionStorage.clear();
            localStorage.clear();
            alert('회원가입이 완료되었습니다.');
            window.location.href='/login';
        })
        .catch(error => {
            console.error(error);
        })
}

function createJSON(){
    return {
        id : sessionStorage.getItem('id'),
        pw : sessionStorage.getItem('pw'),
        name: sessionStorage.getItem('name'),
        birth: sessionStorage.getItem('birth'),
        gender: sessionStorage.getItem('gender'),
        tel : sessionStorage.getItem('tel'),
        email : sessionStorage.getItem('email')
    };
}