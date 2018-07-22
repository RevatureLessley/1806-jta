
function onTypeSelected(cost){
    let thing = document.getElementById("type");

    xhr = new XMLHttpRequest();
    let type = document.getElementById("type").selectedIndex;

    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            let json = JSON.parse(xhr.response);
            let expected = json.percent * cost / 100.0;
            let output = document.getElementById('expected-return');
            output.value = expected.toFixed(2);
        }
    }


    xhr.open("POST", "../GetReimbursementTypes.Servlet");
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send('&type='+type);
}

function onSubmitClicked(date, location, description, cost, file){
    let xhr = {};
    function sendForm(data){
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4){
                document.write(xhr.response);
            }
        }
        let type = document.getElementById("type").selectedIndex;
        let format = document.getElementById("format").selectedIndex;
        xhr.open("POST", "../Reimbursement.Servlet");
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.send('date=' +date+ '&' +
            'location=' +location+ '&' +
            'description=' +description+ '&' +
            'cost=' +cost+ '&' +
            'type=' +type+ '&' +
            'format=' +format+ '&' +
            'picUrl='+data);
    }
    if (file) {
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function (evt){
            let data = evt.target.result;
            sendForm(data)
        }
    }
    else {
        sendForm('');
    }
    let xhr2 = new XMLHttpRequest();
    xhr2.onreadystatechange = function(){
        if (Notification.permission === "granted") {
            if (xhr2.readyState == 4){
                let jsonObject1 = JSON.parse(xhr2.response);
                let xhr3 = new XMLHttpRequest();
                xhr3.onreadystatechange = function(){
                    if (xhr3.readyState == 4){
                        let jsonObject2 = JSON.parse(xhr3.response);
                        for (employee in jsonObject2){
                            if (jsonObject2[employee].id == jsonObject1['data'][0].employeeId){
                                var notification = new Notification('Snailsforce', { body: 'New reimbursement request from '+ jsonObject2[employee].firstName + ' ' + jsonObject2[employee].lastName,
                                    icon: '../resources/icons/info_icon.png'});
                                setTimeout(notification.close.bind(notification), 8000);
                            }
                        }
                    }
                }
                xhr3.open("GET", "../GetAllEmployees.Servlet");
                xhr3.send();
            }
        }
    }
    xhr2.open("GET", "../Session.Servlet");
    xhr2.send();


}


function displayModal(file){
    if (file) {
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function (evt) {
            let data = evt.target.result;

            img1 = document.createElement('img');
            img1.setAttribute('id', 'myImg');
            img1.setAttribute('src', data);
            img1.setAttribute('alt', 'pic');
            img1.setAttribute('style', 'width:100%;max-width:300px');

            div = document.createElement('div');
            div.setAttribute('id', 'myModal');
            div.setAttribute('class', 'modal');

            span = document.createElement('span');
            span.setAttribute('class', 'close');
            span.setAttribute('onclick', "div.style.display='none';img1.src=''");
            span.innerHTML = '&times;';

            img2 = document.createElement('img');
            img2.setAttribute('class', 'modal-content');
            img2.setAttribute('id', 'img01');

            div.appendChild(span);
            div.appendChild(img2);

            div.style.display = "block";
            img2.src = img1.src;

            output = document.getElementById("output");
            output.appendChild(div);
        }
    }
}
