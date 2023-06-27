window.onload = function(){
    var emailCheckButton = document.getElementById('account_email_button');

    emailCheckButton.addEventListener('click', function(){
        emailCheck(emailCheckButton)
    });
};

function emailCheck(button){
    button.disabled=true;
    let cnt = 100;
    setInterval(() => document.getElementById('account_email_counter').innerHTML = cnt--, 1000);
}

