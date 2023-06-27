window.onload = function() {
    var deleteButton = document.getElementById('mypage_delete');

    deleteButton.addEventListener('click', deleteUser);
}

var deleteUser = function(){
    var data = {id : document.getElementById('navi_username').innerHTML};
    console.log(data);

    axios.delete('/account/delete')
        .then(response => {
            if(response.data === 1){
                alert('회원정보가 삭제되었습니다.');
                window.location.href='/account/delprocess';
            }else{
                alert('????? 와 안되누?');
            }
        })
        .catch(error => {
            console.error(error);
        });
}


