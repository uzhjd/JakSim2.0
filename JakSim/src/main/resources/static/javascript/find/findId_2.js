var code;
var time;
var requestId;
var validateTime;

window.onload = function(){
    time = 600;

    showValidTime();
    sendEmail();
}

function showValidTime(){
    validateTime = document.getElementById('find_validate_time');

    var startTime = Date.now();
    var endTime = startTime + time*1000;

    function update(){
        var currentTime = Date.now();
        var remainTime = Math.max(0, endTime - currentTime);

        var seconds = Math.floor(remainTime/1000);
        var min = Math.floor(seconds / 60);
        seconds = seconds%60;

        min = min.toString().padStart(2, '0');
        seconds = seconds.toString().padStart(2, '0');

        validateTime.innerHTML = `${min} : ${seconds}`;

        if(currentTime < endTime){
            requestId = requestAnimationFrame(update);
        }else{
            alert('시간이 초과되었습니다.');
        }
    }
    update();
}

function sendEmail(){
    var codeInput = document.getElementById('find_validate_input')
    axios.post('/find/api/email/action', {email : sessionStorage.getItem('userEmail')})
        .then(response => {
            code = response.data;
            if(code === codeInput.value)
                window.location.href='/login';
        })
        .catch(error => {
            console.error(error);
        })
}

function stopTimer(){
    cancelAnimationFrame(requestId);
}