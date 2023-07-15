window.addEventListener('load', function(){
    if(window.location.href==='http://localhost:8080/trainer/search/' ||
    window.location.href==='http://localhost:8080/trainer/search/0/0'||window.location.href==='http://localhost:8080/trainer/search/0/1'||window.location.href==='http://localhost:8080/trainer/search/0/2'||window.location.href==='http://localhost:8080/trainer/search/0/3'||window.location.href==='http://localhost:8080/trainer/search/0/4'||window.location.href==='http://localhost:8080/trainer/search/0/5'){
        button_init(); //특정 url에서만 사용되는게 아니어서 이렇게 작성함
        const checkedBtnValue = window.location.pathname.slice(-1);
        const checkedBtn = document.querySelector(`input[name="toggle"][value="${checkedBtnValue}"]`);
        if (checkedBtn) {
            checkedBtn.checked = true;
        }//라디오버튼은 기본적으로 href가 없기 때문에 이렇게하는 수 밖에 없었다아
    }

    if(window.location.href==='http://localhost:8080/trainer/reservation'){
        console.log('This is trainer Reservation');
    }

    if(window.location.href==='http://localhost:8080/trainer/create/' || window.location.href==='http://localhost:8080/trainer/change/'){
        document.getElementById('add-cert').onclick = function(){
            const certInput = document.getElementById('cert');
            const displayCert = document.getElementById('display-cert');
            const create_cert_div = document.getElementById('create_cert_div');
            const cert_span = document.createElement('span');

            if(certInput.value){
                displayCert.value += `${certInput.value}/`;
                cert_span.textContent = certInput.value;
                cert_span.setAttribute('class', 'btn btn-outline-primary');
                cert_span.style.margin = '3px';

                create_cert_div.appendChild(cert_span);
                certInput.value = '';
            }
        }

        document.getElementById('add-career').onclick = function(){
            const careerDate = document.getElementById('career_date');
            const careerInput = document.getElementById('career');
            const displayCareer = document.getElementById('display-career');
            const create_career_div = document.getElementById('create_career_div');
            const career_span = document.createElement('span');

            if(careerInput.value){
                displayCareer.value += `${career_date.value}`+` ${careerInput.value}/`; //', '로 split 하시면 될 듯
                career_span.textContent = careerDate.value+' '+careerInput.value;
                career_span.setAttribute('class', 'btn btn-outline-primary');
                career_span.style.margin = '3px';

                create_career_div.appendChild(career_span);
                careerInput.value = '';
            }
        }
        document.getElementById('add-pt').onclick = function(){
            const displayPrice = document.getElementById('display-price');
            const type = document.querySelector('input[name="type"]:checked').value;
            const times = document.getElementById('pt-times').value;
            const months = document.getElementById('pt-months').value
            const price = document.getElementById('pt-price').value;
            const create_pt_div = document.getElementById('create_pt_div');
            const pt_span = document.createElement('span');

            let displayText = '';

            if(type === '개인')
                displayText += '개인 ';
            else
                displayText += '단체 ';

            displayText += months + ' '+ times +' ' + price;
            console.log(displayText);
            displayPrice.value += displayText + '/';

            pt_span.textContent = months+'개월 ' + times +"회 " + price+"원("+type+")";
            pt_span.setAttribute('class', 'btn btn-outline-primary');
            pt_span.style.margin = '3px';

            create_pt_div.appendChild(pt_span);

            times = '';
            months = '';
            price='';
        }
    }
});


var button_init = function(){
   const container = document.getElementById('button_container');
   console.log('window; ' + window.location.pathname.slice(-1));
   container.addEventListener('click', (event) => {
   if (event.target.type === 'radio') {
           const checkedBtn = document.querySelector('input[name="toggle"]:checked');
           checkedBtn.onclick = function(){
                console.log(checkedBtn.value);
                var url = 'http://localhost:8080/trainer/search/0/'+checkedBtn.value;
                console.log('url' + url);
                location.href=url;
           }
           const uncheckedBtns = Array.from(document.querySelectorAll('input[name="toggle"]:not(:checked)'));
           uncheckedBtns.forEach((btn) => {
               btn.checked = false;
           });
           console.log(checkedBtn.value);
       }
   });

}