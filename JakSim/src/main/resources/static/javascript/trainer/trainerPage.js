//window.addEventListener('load', function(){
//
//    if(window.location.href==='http://localhost:8080/trainerRegister/' || window.location.href==='http://localhost:8080/trainerUpdate/'){
//        document.getElementById('add-cert').onclick = function(){
//            const certInput = document.getElementById('cert');
//            const displayCert = document.getElementById('display-cert');
//            const create_cert_div = document.getElementById('create_cert_div');
//            const cert_span = document.createElement('span');
//
//            if(certInput.value){
//                displayCert.value += `${certInput.value}/`;
//                cert_span.textContent = certInput.value;
//                cert_span.setAttribute('class', 'btn btn-outline-primary');
//                cert_span.style.margin = '3px';
//
//                create_cert_div.appendChild(cert_span);
//                certInput.value = '';
//            }
//        }
//
//        document.getElementById('add-career').onclick = function(){
//            const careerInput = document.getElementById('career');
//            const displayCareer = document.getElementById('display-career');
//            const create_career_div = document.getElementById('create_career_div');
//            const career_span = document.createElement('span');
//
//            if(careerInput.value){
//                displayCareer.value += ` ${careerInput.value}/`; //', '로 split 하시면 될 듯
//                career_span.textContent = careerDate.value+' '+careerInput.value;
//                career_span.setAttribute('class', 'btn btn-outline-primary');
//                career_span.style.margin = '3px';
//
//                create_career_div.appendChild(career_span);
//                careerInput.value = '';
//            }
//        }
//        document.getElementById('add-pt').onclick = function(){
//            const displayPrice = document.getElementById('display-price');
//            const type = document.querySelector('input[name="type"]:checked').value;
//            const times = document.getElementById('pt-times').value;
//            const price = document.getElementById('pt-price').value;
//            const create_pt_div = document.getElementById('create_pt_div');
//            const pt_span = document.createElement('span');
//
//            let displayText = '';
//
//            if(type === '개인')
//                displayText += '개인 ';
//            else
//                displayText += '단체 ';
//
//            displayText += times +' ' + price;
//            console.log(displayText);
//            displayPrice.value += displayText + '/';
//
//            pt_span.textContent = times +"회 " + price+"원("+type+")";
//            pt_span.setAttribute('class', 'btn btn-outline-primary');
//            pt_span.style.margin = '3px';
//
//            create_pt_div.appendChild(pt_span);
//
//            times = '';
//            price='';
//        }
//    }
//});
//
//}