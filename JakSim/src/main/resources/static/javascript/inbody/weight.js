var data = [];

window.onload = function(){
    getWeights();
}

function getWeights(){
    axios.get('/mypage/api/inbody/data')
        .then(response => {
            console.log(response.data);
        })
        .catch(error => {
            console.error(error);
        });
}
