window.onload = function(){
    populateTable();
}
arrayPic = [];
function populateTable(){
    //Create Table to store notifications?

    //pull all requests (UGH.) Pull all Employees.
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            jsonObject = JSON.parse(xhr.response);

            let table = document.getElementById('notification-table');

            for(record in jsonObject){
                let data = [
                jsonObject[record].id,
                jsonObject[record].reimbursement.employee.firstName +" "+ jsonObject[record].reimbursement.employee.lastName,
                '$' + jsonObject[record].reimbursement.reimbursement,
                jsonObject[record].reimbursement.eventType.description,
                jsonObject[record].reimbursement.description,
                jsonObject[record].reimbursement.location,
                jsonObject[record].reimbursement.gradingFormat
                ];

                let tr = document.createElement('tr');
                for (var i = 0; i < 6; i++){
                    let td = document.createElement('td');
                    td.setAttribute("style", "text-align:center");
                    let element = document.createTextNode(data[i]);
                    td.appendChild(element);
                    tr.appendChild(td);
                }
                let td = document.createElement('td');
                td.setAttribute("style", "text-align:center");
                let a = document.createElement('a');
                a.setAttribute("class", "nav-link");
                arrayPic[record] = jsonObject[record].reimbursement.fileUrl;
                a.setAttribute("onclick", "displayModal("+record+");return false;");
                a.setAttribute("href", "#");
                a.innerHTML = "View Attachment";
                td.appendChild(a);
                tr.appendChild(td);

                let td2 = document.createElement('td');
                td2.setAttribute("style", "text-align:center");
                let but1 = document.createElement('button');
                let but2 = document.createElement('button');
                but1.setAttribute('type', 'button');
                but1.setAttribute('id', 'button'+record+'A');
                but1.setAttribute('onclick', 'approveButton('+record+')');
                but1.innerHTML = "Approve";
                but2.setAttribute('type', 'button');
                but2.setAttribute('id', 'button'+record+'B');
                but2.setAttribute('onclick', 'denyButton('+record+')');
                but2.innerHTML = "Deny";
                td2.appendChild(but1);
                td2.appendChild(but2);
                tr.appendChild(td2);
                table.appendChild(tr);
                }
        }
    }
    xhr.open("GET", "../Notification.Servlet");
    xhr.send();
    //Cross-check
}

function approveButton(record){
    let button1 = document.getElementById('button'+record+'A');
    let button2 = document.getElementById('button'+record+'B');
    button1.setAttribute("disabled", true);
    button2.setAttribute("disabled", true);
    var approves = jsonObject[record].approvalCount;
    var iconImg = (approves < 2) ? '../resources/icons/info_icon.png' : '../resources/icons/approved_icon.png'
    var message = (approves < 2)
        ? 'New reimbursement request from '+ jsonObject[record].reimbursement.employee.firstName + ' ' + jsonObject[record].reimbursement.employee.lastName + '\n' +
          '(' + jsonObject[record].notifiee.firstName + ' ' + jsonObject[record].notifiee.lastName + ' just gave their approval)';
        : 'Request '+ jsonObject[record].id +' Appoved!'
    var notification = new Notification('Snailsforce', { body: message, icon: iconImg});
    setTimeout(notification.close.bind(notification), 8000);
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){

            //TODO: Do something with the response (HINT: It's currently ""
        }
    }
    xhr.open("POST", "../ApprovalRequest.Servlet");
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send('notificationId=' + jsonObject[record].id);
}

function denyButton(record){
    let button1 = document.getElementById('button'+record+'A');
    let button2 = document.getElementById('button'+record+'B');
    button1.setAttribute("disabled", true);
    button2.setAttribute("disabled", true);
    var notification = new Notification('Snailsforce', { body: 'Reimbursement request denied by '+ jsonObject[record].notifiee.firstName + ' ' + jsonObject[record].notifiee.lastName,
        icon: '../resources/icons/denied_icon.png'});
    setTimeout(notification.close.bind(notification), 8000);
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            //TODO: Do something with this response.
        }
    }
    xhr.open("POST", "../DenialRequest.Servlet");
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send('notificationId=' + jsonObject[record].id);
}

function displayModal(input){
    let data = arrayPic[input];
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