window.onload = function(){
    console.log(sessionStorage.getItem('name'));
    console.log(sessionStorage.getItem('tel') + ' ::: ' + sessionStorage.getItem('email'));

    var nextButton = document.getElementById('account_4_next');
    nextButton.addEventListener('click', nextPage);
}

function nextPage(){
    var selectElement = document.getElementById('account_question');
    var answerInput = document.getElementById('account_answer');

    var selectedValue = selectElement.value;

    sessionStorage.setItem('question', selectedValue);
    sessionStorage.setItem('answer', answerInput.value);

    window.location.href='/account/5';
}