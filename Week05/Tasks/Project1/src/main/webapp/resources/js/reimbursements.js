rejectRequest = false;

function onTypeSelected(cost){
    let e = null;
    if( e = document.getElementById("atCap")) e.remove();
    let thing = document.getElementById("type");
    xhr = new XMLHttpRequest();
    let type = document.getElementById("type").selectedIndex;

    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            let json = JSON.parse(xhr.response);
            xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if (xhr.readyState == 4){
                    let jsonObject = JSON.parse(xhr.response);
                    let expected = json.percent * cost / 100.0;
                    let cap = 1000 - (parseFloat(jsonObject.pendingReimbursements) + parseFloat(jsonObject.awardedReimbursements));
                    if (cap == 0) rejectRequest = true;
                    if (expected > cap) {
                        expected = cap;
                        let text = document.createTextNode("You are at your reimbursement cap of $1000.");
                        let div = document.createElement('div');
                        div.setAttribute("class", "alert alert-warning");
                        div.setAttribute("id", "atCap");
                        div.appendChild(text);
                        document.getElementById("list-item-amounts").appendChild(div);
                    }
                    let output = document.getElementById('expected-return');
                    output.value = expected.toFixed(2);
                }
            }
            xhr.open("GET", "../GetEmployee.Servlet");
            xhr.send();
        }
    }
    xhr.open("POST", "../GetReimbursementTypes.Servlet");
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send('&type='+type);
}

function onSubmitClicked(date, location, description, cost, file, filename){
    let xhr = {};
    function sendForm(data){
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(){
            if (xhr.readyState == 4){
                document.write(xhr.response);
                xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function(){
                    if (xhr.readyState == 4){
                        let json = JSON.parse(xhr.response);
                        sendNotification(json.firstName + ' ' + json.lastName);
                    }
                }
                xhr.open("GET", "../GetEmployee.Servlet");
                xhr.send();
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
            'picUrl='+data+ '&' +
            'fileName='+filename);
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

}

function sendNotification(from){
    if (Notification.permission === "granted") {
        var notification = new Notification('Snailsforce', {
        body: 'New reimbursement request from '+ from,
        icon: '../resources/icons/info_icon.png'});
        setTimeout(notification.close.bind(notification), 4000);
    }
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
