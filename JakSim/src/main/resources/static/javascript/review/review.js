window.onload = function(){
    showReviews();
}

function showReviews(){
    axios.get('/review/api/1')
        .then(response => {
            console.log(response.data);
        })
        .catch(error => {
            console.error(error);
        })
}