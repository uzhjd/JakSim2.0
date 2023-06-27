window.onload = function(){
    var emailCheckButton = document.getElementById('account_email_button');
    

    emailCheckButton.addEventListener('click', function(){
        sendMail();
        countDown(emailCheckButton);
    });
};

function sendMail(){
    var email = {email: document.getElementById('account_email').value};
    axios.post('/account/checkemail', email)
        .then(response => {
            console.log('Communicate Success');
            console.log(response.data);
            console.log(email);
        })
        .catch(error => {
            console.error(error);
        })
        .finally(() => {
            console.log('Fin Axios');
        });
}

function countDown(button){
    button.disabled=true;
    let cnt = 100;
    setInterval(() => document.getElementById('account_email_counter').innerHTML = cnt--, 1000);
}

