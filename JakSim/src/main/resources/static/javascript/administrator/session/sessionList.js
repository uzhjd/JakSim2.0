var sessionId, username;

window.onload = function(){
    sessionId = document.getElementById('session_id').innerHTML;
}

function removeExpire(){
axios.post('/session/expire', {sessionId:sessionId})
    .then(response => {
        window.location.reload();
    })
    .catch(error => {
        console.error(error);
    });
}