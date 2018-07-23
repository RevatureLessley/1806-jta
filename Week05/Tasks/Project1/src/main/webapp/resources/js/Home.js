window.onload = function(){
    getUserInfo();
    getNotificationCount();
    Notification.requestPermission().then(function(result) {
      console.log(result);
    });
}

function getNotificationCount(){

    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            jsonObject = JSON.parse(xhr.response);
            document.getElementById('badge').innerHTML = jsonObject.length; //TODO: variable
        }
    }
    xhr.open("GET", "../Notification.Servlet");
    xhr.send();
}

function setWelcomeTag(username){
    document.getElementById("welcome-tag-innermost").innerHTML += (username + '!');

}
arrayPic = [];
function getReimbursementRequests(userInfo){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            let jsonObject = JSON.parse(xhr.response);
            let table = document.getElementById('reimbursement-table');
            for(record in jsonObject){
                let row = document.createElement('tr');
                let td1 = document.createElement('td');
                let td2 = document.createElement('td');
                let td3 = document.createElement('td');
                let td4 = document.createElement('td');
                let td5 = document.createElement('td');
                let td6 = document.createElement('td');
                let td7 = document.createElement('td');
                let td8 = document.createElement('td');
                td1.setAttribute("style", "text-align:center");
                td2.setAttribute("style", "text-align:center");
                td3.setAttribute("style", "text-align:center");
                td4.setAttribute("style", "text-align:center");
                td5.setAttribute("style", "text-align:center");
                td6.setAttribute("style", "text-align:center");
                td7.setAttribute("style", "text-align:center");
                td8.setAttribute("style", "text-align:center");
                let a1 = document.createElement('a');
                let td1t = document.createTextNode(jsonObject[record]['id']);
                let td2t = document.createTextNode(jsonObject[record]['eventType']['description']);
                let td3t = document.createTextNode(jsonObject[record]['description']);
                let td4t = document.createTextNode(jsonObject[record]['location']);
                let td5t = document.createTextNode(jsonObject[record]['gradingFormat']);
                let td6t = document.createTextNode('$' + parseFloat(jsonObject[record]['reimbursement']).toFixed(2));
                let value = jsonObject[record]['status'];
                let string = "Pending";
                if (value == 1) string = "Yes";
                else if (value == 2) string = "No";
                let td7t = document.createTextNode(string);
                let td8t = document.createTextNode(jsonObject[record]['fileUrl']);
                a1.setAttribute("class", "nav-link");
                arrayPic[record] = jsonObject[record]['fileUrl'];
                a1.setAttribute("onclick", "displayModal("+record+");return false;");
                a1.setAttribute("href", "#");
                a1.innerHTML = "Preview Attachment";
                let a2 = document.createElement('a');
                a2.setAttribute("download", jsonObject[record]['fileName']);
                a2.setAttribute("href", jsonObject[record]['fileUrl']);
                a2.innerHTML = "Download";
                let div = document.createElement('div');
                div.setAttribute('class', 'file-dropdown');
                let button = document.createElement('button');
                button.setAttribute("class", "file-dropbtn");
                button.innerHTML = jsonObject[record]['fileName'];
                let innerDiv = document.createElement('div');
                innerDiv.setAttribute("class", "file-dropdown-content");
                innerDiv.setAttribute("style", "text:align:center");
                innerDiv.appendChild(a1);
                innerDiv.appendChild(a2);
                div.appendChild(button);
                div.appendChild(innerDiv);
                td1.appendChild(td1t);
                td2.appendChild(td2t);
                td3.appendChild(td3t);
                td4.appendChild(td4t);
                td5.appendChild(td5t);
                td6.appendChild(td6t);
                td7.appendChild(td7t);
                if (jsonObject[record]['fileName']){td8.appendChild(div);}
                else {
                    let p = document.createElement('p');
                    p.setAttribute("style", "font-style:italic;");
                    p.innerHTML = "No file uploaded"
                    td8.appendChild(p);
                }
                row.appendChild(td1);
                row.appendChild(td2);
                row.appendChild(td3);
                row.appendChild(td4);
                row.appendChild(td5);
                row.appendChild(td6);
                row.appendChild(td7);
                row.appendChild(td8);
                table.appendChild(row);
            }
        }
    }

    xhr.open("GET", "../GetReimbursements.Servlet");
    xhr.send();
}

function getUserInfo() {

let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(xhr.readyState==4){
            let json = JSON.parse(xhr.response);
            let userId = json.data[0].employeeId;

            let xhr2 = new XMLHttpRequest();
            let userInfoList = document.getElementById("user-info-list");
            xhr2.onreadystatechange = function(){
                if (xhr2.readyState == 4){
                    let json2 = JSON.parse(xhr2.response);
                    for (element in json2){
                        if (json2[element].id == userId){
                            userInfo = json2[element];
                            setWelcomeTag(json2[element].firstName);
                            getReimbursementRequests(userInfo);
                            //TODO: This is where you left off. Trying to create a User Information Box. (Refactor and get all this down to one Ajax call?)
                            let xhr3 = new XMLHttpRequest();
                            xhr3.onreadystatechange = function(){
                                if (xhr3.readyState == 4){
                                    let jsonObject = JSON.parse(xhr3.response);
                                    let li = document.createElement('li');
                                    li.setAttribute("style", "white-space:pre");
                                    let part1 = document.createTextNode('Name');
                                    let part2 = document.createTextNode(':\t\t\t\t\t');
                                    let part3 = document.createTextNode(jsonObject['firstName'] + ' ');
                                    let part4 = document.createTextNode(jsonObject['lastName']);
                                    li.appendChild(part1);
                                    li.appendChild(part2);
                                    li.appendChild(part3);
                                    li.appendChild(part4);
                                    userInfoList.appendChild(li);

                                    li = document.createElement('li');
                                    li.setAttribute("style", "white-space:pre");
                                    part1 = document.createTextNode('Employee ID');
                                    part2 = document.createTextNode(':\t\t\t\t');
                                    part3 = document.createTextNode(jsonObject['id']);
                                    li.appendChild(part1);
                                    li.appendChild(part2);
                                    li.appendChild(part3);
                                    userInfoList.appendChild(li);

                                    li = document.createElement('li');
                                    li.setAttribute("style", "white-space:pre");
                                    part1 = document.createTextNode('Email');
                                    part2 = document.createTextNode(':\t\t\t\t\t');
                                    part3 = document.createTextNode(jsonObject['email']);
                                    li.appendChild(part1);
                                    li.appendChild(part2);
                                    li.appendChild(part3);
                                    userInfoList.appendChild(li);

                                    li = document.createElement('li');
                                    li.setAttribute("style", "white-space:pre");
                                    part1 = document.createTextNode('Phone Number');
                                    part2 = document.createTextNode(':\t\t\t');
                                    part3 = document.createTextNode(jsonObject['phone']);
                                    li.appendChild(part1);
                                    li.appendChild(part2);
                                    li.appendChild(part3);
                                    userInfoList.appendChild(li);


                                    li = document.createElement('li');
                                    li.setAttribute("style", "white-space:pre");
                                    part1 = document.createTextNode('Awarded Reimbursements');
                                    part2 = document.createTextNode(':\t$');
                                    part3 = document.createTextNode(jsonObject['awardedReimbursements']);
                                    li.appendChild(part1);
                                    li.appendChild(part2);
                                    li.appendChild(part3);
                                    userInfoList.appendChild(li);

                                    xhr4 = new XMLHttpRequest();
                                    xhr4.onreadystatechange = function(){
                                        if (xhr4.readyState == 4){
                                            let employees = JSON.parse(xhr4.response);
                                            for (emp in employees){
                                                if (employees[emp].id == jsonObject['supervisorId']){
                                                    li = document.createElement('li');
                                                    li.setAttribute("style", "white-space:pre");
                                                    part1 = document.createTextNode('Supervisor');
                                                    part2 = document.createTextNode(':\t\t\t\t');
                                                    part3 = document.createTextNode(employees[emp].firstName + ' ' + employees[emp].lastName);
                                                    li.appendChild(part1);
                                                    li.appendChild(part2);
                                                    li.appendChild(part3);
                                                    userInfoList.appendChild(li);
                                                }
                                            }
                                        }
                                    }
                                    xhr4.open("GET", "../GetAllEmployees.Servlet");
                                    xhr4.send();
                                    
                                }
                            }
                            xhr3.open("GET", "../GetEmployee.Servlet");
                            xhr3.send();
                        }
                    }
                }
            }
            xhr2.open("GET", "../GetAllEmployees.Servlet");
            xhr2.send();
        }
    }
    xhr.open("GET", "../Session.Servlet");
    xhr.send();
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