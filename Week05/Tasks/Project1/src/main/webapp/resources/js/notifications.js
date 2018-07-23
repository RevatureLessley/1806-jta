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
                '$' + parseFloat(jsonObject[record].reimbursement.reimbursement).toFixed(2),
                jsonObject[record].reimbursement['date'].monthValue + "/" + jsonObject[record].reimbursement['date'].dayOfMonth + "/" + jsonObject[record].reimbursement['date'].year,
                jsonObject[record].reimbursement.eventType.description,
                jsonObject[record].reimbursement.description,
                jsonObject[record].reimbursement.location,
                jsonObject[record].reimbursement.gradingFormat
                ];

                let tr = document.createElement('tr');
                for (var i = 0; i < 8; i++){    //TODO: Hardcode
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
                a.innerHTML = "Preview Attachment";
                let a2 = document.createElement('a');
                a2.setAttribute("download", jsonObject[record].reimbursement['fileName']);
                a2.setAttribute("href", jsonObject[record].reimbursement['fileUrl']);
                a2.innerHTML = "Download";
                let div = document.createElement('div');
                div.setAttribute('class', 'file-dropdown');
                let button = document.createElement('button');
                button.setAttribute("class", "file-dropbtn");
                button.innerHTML = jsonObject[record].reimbursement['fileName'];
                let innerDiv = document.createElement('div');
                innerDiv.setAttribute("class", "file-dropdown-content");
                innerDiv.setAttribute("style", "text:align:center");
                innerDiv.appendChild(a);
                innerDiv.appendChild(a2);
                div.appendChild(button);
                div.appendChild(innerDiv);
                if (jsonObject[record].reimbursement['fileName']){td.appendChild(div);}
                else {
                    let p = document.createElement('p');
                    p.setAttribute("style", "font-style:italic;");
                    p.innerHTML = "No file uploaded"
                    td.appendChild(p);
                }
                tr.appendChild(td);

                let td2 = document.createElement('td');
                td2.setAttribute("style", "text-align:center");
                let but1 = document.createElement('button');
                let but2 = document.createElement('button');
                but1.setAttribute('type', 'button');
                but1.setAttribute('id', 'button'+record+'A');
                but1.setAttribute('onclick', 'approveButton('+record+')');
                but1.setAttribute("style", "margin:5px");
                but1.innerHTML = "Approve";
                but2.setAttribute('type', 'button');
                but2.setAttribute('id', 'button'+record+'B');
                but2.setAttribute('onclick', 'denyButton('+record+')');
                but2.setAttribute("style", "margin:5px");
                but2.innerHTML = "Deny";

                let divDrop = document.createElement('div');
                divDrop.setAttribute('class', 'add-drop');
                let but3 = document.createElement('button');
                but3.setAttribute('class', 'add-drop-button');
                but3.setAttribute('onclick', 'showDrop(true)');
                but3.setAttribute('id', 'button'+record+'C');
                but3.innerHTML = "More Info";
                let dropDiv = document.createElement('div');
                dropDiv.setAttribute('class', 'add-dropdown-content');
                dropDiv.setAttribute('id', 'add-dropdown-content');
                let textArea = document.createElement('textarea');
                textArea.setAttribute('id', 'textArea');
                textArea.setAttribute('rows', 4);
                textArea.setAttribute('cols', 40);
                let but4 = document.createElement('button');
                but4.setAttribute('type', 'button');
                if (jsonObject[record].atSupervisor || jsonObject[record].atDeptHead || jsonObject[record].atBenCo) but4.setAttribute('onclick', "requestMoreInfo(false, jsonObject[record], textArea.value)");
                else but4.setAttribute('onclick', "requestMoreInfo(true, jsonObject[record], textArea2.value)");
                but4.setAttribute('style', 'margin:5px');
                but4.setAttribute('id', 'button'+record+'E');
                but4.innerHTML = "Submit";
                let but5 = document.createElement('button');
                but5.setAttribute('type', 'button');
                but5.setAttribute('onclick', 'showDrop(false)');
                but5.setAttribute('id', 'button'+record+'F');
                but5.innerHTML = "Cancel";
                dropDiv.appendChild(textArea);
                dropDiv.appendChild(but4);
                dropDiv.appendChild(but5);
                divDrop.appendChild(but3);
                divDrop.appendChild(dropDiv);

                let divDrop2 = document.createElement('div');
                divDrop2.setAttribute('class', 'add-drop');
                let but6 = document.createElement('button');
                but6.setAttribute('class', 'add-drop-button');
                but6.setAttribute('onclick', 'showDrop2(true)');
                but6.setAttribute('id', 'button'+record+'D');
                but6.innerHTML = "See Details";
                let dropDiv2 = document.createElement('div');
                dropDiv2.setAttribute('class', 'add-dropdown-content');
                dropDiv2.setAttribute('id', 'add-dropdown-content2');
                let textArea1Copy = document.createElement('textarea');
                textArea1Copy.setAttribute('id', 'textArea1Copy');
                textArea1Copy.setAttribute('rows', 4);
                textArea1Copy.setAttribute('cols', 40);
                textArea1Copy.setAttribute('readonly', true);
                textArea1Copy.setAttribute('style', "background-color:lightgray");
                textArea1Copy.value = jsonObject[record].additionalInfo;
                let textArea2 = document.createElement('textarea');
                textArea2.setAttribute('id', 'textArea2');
                textArea2.setAttribute('rows', 4);
                textArea2.setAttribute('cols', 40);
                dropDiv2.appendChild(textArea1Copy);
                dropDiv2.appendChild(textArea2);
                divDrop2.appendChild(but6);
                divDrop2.appendChild(dropDiv2);
                console.log(jsonObject[record]);
                if (jsonObject[record].atSupervisor || jsonObject[record].atDeptHead || jsonObject[record].atBenCo) {
                    dropDiv.appendChild(but4);
                    dropDiv.appendChild(but5);
                    td2.appendChild(but1);
                    td2.appendChild(but2);
                    td2.appendChild(divDrop);
                }
                else {
                    dropDiv2.appendChild(but4);
                    dropDiv2.appendChild(but5);
                    td2.appendChild(but6);
                    td2.appendChild(divDrop2);
                }
                tr.appendChild(td2);
                table.appendChild(tr);
                }
        }
    }
    xhr.open("GET", "../Notification.Servlet");
    xhr.send();
    //Cross-check
}

function showDrop(show){
    let div = document.getElementById('add-dropdown-content');
    if (show) div.setAttribute('style', 'display: block');
    else div.setAttribute('style', 'display: none');
}

function showDrop2(show){
    let div = document.getElementById('add-dropdown-content2');
    if (show)div.setAttribute('style', 'display: block');
    else div.setAttribute('style', 'display: none');
}

function disableButtons(record){
    let button1 = document.getElementById('button'+record+'A');
    let button2 = document.getElementById('button'+record+'B');
    let button3 = document.getElementById('button'+record+'C');
    let button4 = document.getElementById('button'+record+'D');
    let button5 = document.getElementById('button'+record+'E');
    if (button1) button1.setAttribute("disabled", true);
    if (button2) button2.setAttribute("disabled", true);
    if (button3) button3.setAttribute("disabled", true);
    if (button4) button4.setAttribute("disabled", true);
    if (button5) button5.setAttribute("disabled", true);

}

function requestMoreInfo(isResponse, record, text){
    if (!isResponse) showDrop(false);
    else showDrop2(false);
    disableButtons(record);
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            if (Notification.permission === "granted") {
                let message = "";

                if (!isResponse) message = record.notifiee.firstName + ' ' + record.notifiee.lastName + ' has asked you to supply more information on one of your reimbursement requests.';
                else  message = record.notifiee.firstName + ' ' + record.notifiee.lastName + ' has updated their reimbursement request.';
                var notification = new Notification('Snailsforce', {
                body: message,
                icon: '../resources/images/info_icon.png'});
                setTimeout(notification.close.bind(notification), 4000);
            }
        }
    }
    if(!isResponse) xhr.open("POST", "../RequestMoreInfo.Servlet");
    else xhr.open("POST", "../SubmitAdditionalInfo.Servlet");
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send('information='+text+'&notificationId='+record.id);
}

function approveButton(record){
    disableButtons(record);
    var approves = jsonObject[record].approvalCount;
    var iconImg = (approves <= 3) ? '../resources/images/info_icon.png' : '../resources/images/approved_icon.png'
    var message = (approves <= 3)
        ? 'New reimbursement request from '+ jsonObject[record].reimbursement.employee.firstName + ' ' + jsonObject[record].reimbursement.employee.lastName + '\n' +
          '(' + jsonObject[record].notifiee.firstName + ' ' + jsonObject[record].notifiee.lastName + ' just gave their approval)'
        : 'Request '+ jsonObject[record].id +' Approved!'
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
    disableButtons(record);
    var notification = new Notification('Snailsforce', { body: 'Reimbursement request denied by '+ jsonObject[record].notifiee.firstName + ' ' + jsonObject[record].notifiee.lastName,
        icon: '../resources/images/denied_icon.png'});
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