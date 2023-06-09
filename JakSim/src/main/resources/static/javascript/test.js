const data = {
    message:'Hello world'
};

window.onload = function(){
    function handleClick(){
        axios.post('/index', data)
            .then(response => {
                console.log(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    }
    var button = document.getElementById('myButton');
    button.addEventListener('click', handleClick);
};