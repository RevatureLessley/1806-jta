window.onload = function(){
    getUserInfo();
}

function setWelcomeTag(username){
    document.getElementById("welcome-tag").innerHTML += username;

}

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
                let td1t = document.createTextNode(jsonObject[record]['id']);
                let td2t = document.createTextNode(jsonObject[record]['eventType']['description']);
                let td3t = document.createTextNode('$' + jsonObject[record]['reimbursement']);
                let td4t = document.createTextNode(jsonObject[record]['approved'] ? 'Yes' : 'No');
                td1.appendChild(td1t);
                td2.appendChild(td2t);
                td3.appendChild(td3t);
                td4.appendChild(td4t);
                row.appendChild(td1);
                row.appendChild(td2);
                row.appendChild(td3);
                row.appendChild(td4);
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
            let userInfoBlock = document.getElementById("user-info-block");
            xhr2.onreadystatechange = function(){
                if (xhr2.readyState == 4){
                    let json2 = JSON.parse(xhr2.response);
                    for (element in json2){
                        if (json2[element].id == userId){
                            userInfo = json2[element];
                            setWelcomeTag(json2[element].firstName);
                            getReimbursementRequests(userInfo);
                            console.log(userInfo);
                            //TODO: This is where you left off. Trying to create a User Information Box. (Refactor and get all this down to one Ajax call?)
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