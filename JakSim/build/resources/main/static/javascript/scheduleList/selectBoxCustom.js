/* 화살표 함수 */
const selected = document.querySelector('.selected');
const options = document.querySelectorAll('.optionItem');

// 클릭한 옵션의 텍스트를 라벨 안에 넣음
const handleSelect = (item) => {
    selected.parentNode.classList.remove('active');
    selected.innerHTML = item.textContent;
}
// 옵션 클릭시 클릭한 옵션을 넘김
options.forEach(option => {
    option.addEventListener('click', () => handleSelect(option))
})

// 라벨을 클릭시 옵션 목록이 열림/닫힘
selected.addEventListener('click', () => {
    if(selected.parentNode.classList.contains('active')) {
        selected.parentNode.classList.remove('active');
    } else {
        selected.parentNode.classList.add('active');
    }
})

/* 일반함수 */
const selected = document.querySelector('.selected');
const options = document.querySelectorAll('.optionItem');
// 클릭한 옵션의 텍스트를 라벨 안에 넣음
const handleSelect = function(item) {
    selected.innerHTML = item.textContent;
    selected.parentNode.classList.remove('active');
}
// 옵션 클릭시 클릭한 옵션을 넘김
options.forEach(function(option){
    option.addEventListener('click', function(){handleSelect(option)})
})
// 라벨을 클릭시 옵션 목록이 열림/닫힘
selected.addEventListener('click', function(){
    if(selected.parentNode.classList.contains('active')) {
        selected.parentNode.classList.remove('active');
    } else {
        selected.parentNode.classList.add('active');
    }
});