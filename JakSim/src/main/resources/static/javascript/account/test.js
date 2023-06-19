window.onload = function(){
axios.get('/account/user-info')
    .then(response => {
        console.log(response.data);
    })
    .catch(error => {
        console.error(error);
    })
}