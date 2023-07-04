var code;

window.onload = function() {
    var email = document.getElementById('find_email_input');
    var emailButton = document.getElementById('find_email_certButton');
    var compareButton = document.getElementById('find_compare_answer');

    emailButton.addEventListener('click', function(){sendMail(email.value)});
    compareButton.addEventListener('click', compareAnswer);
}

function sendMail(email){
    var data = {email: email}
    console.log(data);
    axios.post('/account/emailaction', data)
        .then(response => {
            code = response.data;
            countDown();
        })
        .catch(error => {
            console.error(error);
        });
}

function countDown(){
    var time = 300;

}

function compareAnswer(){
    var answer = document.getElementById('find_codeAnswer');
    if(code === answer.value){
        alert('정답이다 연금술사!');
    }else{
        alert('방금 머라 캤노?');
        console.log(`code: ${code} ::: answer: ${answer.value}`);
    }
}

