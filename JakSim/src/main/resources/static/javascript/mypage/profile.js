window.onload = function(){
    emailInput = document.getElementById('profile_emailInput');
    var checkFileButton = document.getElementById('checkFile');
    checkFileButton.addEventListener('click', checking);
}

function checking(){
    var fileInput = document.getElementById('profile_imgInput');
    var file = fileInput.files[0];

    var formData = new FormData();
    formData.append('file', file);
    formData.append('email', emailInput.value);

    axios.put('/mypage/api/profile/update', formData, {headers: {'Content-Type' : 'multipart/form-data'}})
        .then(response => {
            console.log(response.data);
        })
        .catch(error => {
            console.error(error);
        })
}

function readURL(input){
    if(input.files && input.files[0]){
        var reader = new FileReader();
        reader.onload = function(e){
            document.getElementById('profile_preview').src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    }else{
        document.getElementById('profile_preview').src = '';
    }
}

