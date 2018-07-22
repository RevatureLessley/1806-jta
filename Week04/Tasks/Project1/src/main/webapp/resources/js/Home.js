window.onload = function(){
    getUserInfo();
    Notification.requestPermission().then(function(result) {
      console.log(result);
    });
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
                td1.setAttribute("style", "text-align:center");
                td4.setAttribute("style", "text-align:center");
                let a = document.createElement('a');
                let td1t = document.createTextNode(jsonObject[record]['id']);
                let td2t = document.createTextNode(jsonObject[record]['eventType']['description']);
                let td3t = document.createTextNode('$' + jsonObject[record]['reimbursement']);
                let value = jsonObject[record]['status'];
                let string = "Pending";
                if (value == 1) string = "Yes";
                else if (value == 2) string = "No";
                let td4t = document.createTextNode(string);
                let td5t = document.createTextNode(jsonObject[record]['fileUrl']);
                a.setAttribute("class", "nav-link");
                arrayPic[record] = jsonObject[record]['fileUrl'];
                a.setAttribute("onclick", "displayModal("+record+");return false;");
                a.setAttribute("href", "#");
                a.innerHTML = "View Attachment";
                td1.appendChild(td1t);
                td2.appendChild(td2t);
                td3.appendChild(td3t);
                td4.appendChild(td4t);
                td5.appendChild(a);
                row.appendChild(td1);
                row.appendChild(td2);
                row.appendChild(td3);
                row.appendChild(td4);
                row.appendChild(td5);
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
                                    //NOTE: Hardcode, try to return the Employee itself via servlet
                                    let li = document.createElement('li');
                                    li.setAttribute("style", "white-space:pre");
                                    let part1 = document.createTextNode('Name');
                                    let part2 = document.createTextNode(':\t\t\t\t\t');
                                    let part3 = document.createTextNode(jsonObject[0].employee['firstName'] + ' ');
                                    let part4 = document.createTextNode(jsonObject[0].employee['lastName']);
                                    li.appendChild(part1);
                                    li.appendChild(part2);
                                    li.appendChild(part3);
                                    li.appendChild(part4);
                                    userInfoList.appendChild(li);

                                    li = document.createElement('li');
                                    li.setAttribute("style", "white-space:pre");
                                    part1 = document.createTextNode('Employee ID');
                                    part2 = document.createTextNode(':\t\t\t\t');
                                    part3 = document.createTextNode(jsonObject[0].employee['id']);
                                    li.appendChild(part1);
                                    li.appendChild(part2);
                                    li.appendChild(part3);
                                    userInfoList.appendChild(li);

                                    li = document.createElement('li');
                                    li.setAttribute("style", "white-space:pre");
                                    part1 = document.createTextNode('Email');
                                    part2 = document.createTextNode(':\t\t\t\t\t');
                                    part3 = document.createTextNode(jsonObject[0].employee['email']);
                                    li.appendChild(part1);
                                    li.appendChild(part2);
                                    li.appendChild(part3);
                                    userInfoList.appendChild(li);

                                    li = document.createElement('li');
                                    li.setAttribute("style", "white-space:pre");
                                    part1 = document.createTextNode('Phone Number');
                                    part2 = document.createTextNode(':\t\t\t');
                                    part3 = document.createTextNode(jsonObject[0].employee['phone']);
                                    li.appendChild(part1);
                                    li.appendChild(part2);
                                    li.appendChild(part3);
                                    userInfoList.appendChild(li);


                                    li = document.createElement('li');
                                    li.setAttribute("style", "white-space:pre");
                                    part1 = document.createTextNode('Awarded Reimbursements');
                                    part2 = document.createTextNode(':\t$');
                                    part3 = document.createTextNode(jsonObject[0].employee['awardedReimbursements']);
                                    li.appendChild(part1);
                                    li.appendChild(part2);
                                    li.appendChild(part3);
                                    userInfoList.appendChild(li);

                                    xhr4 = new XMLHttpRequest();
                                    xhr4.onreadystatechange = function(){
                                        if (xhr4.readyState == 4){
                                            let employees = JSON.parse(xhr4.response);
                                            for (emp in employees){
                                                if (employees[emp].id == jsonObject[0].employee['supervisorId']){
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
                                    xhr4.open("GET", "../GetEmployees.Servlet");
                                    xhr4.send();
                                    
                                }
                            }
                            xhr3.open("GET", "../GetReimbursements.Servlet");
                            xhr3.send();
                        }
                    }
                }
            }
            xhr2.open("GET", "../GetEmployees.Servlet");
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