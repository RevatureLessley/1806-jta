// https://plainjs.com/javascript/ajax/send-ajax-get-and-post-requests-47/

function ajaxPost(url, params, callback) {
  let xhr = new XMLHttpRequest();
  xhr.open("POST", url);

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      console.log("success");
      callback(xhr.responseText);
    }
  };
  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhr.send(params);
}

function ajaxGet(url, callback) {
  let xhr = new XMLHttpRequest();
  xhr.open("GET", url);

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      console.log("success");
      callback(xhr.responseText);
    }
  };
  xhr.send();
}

function getPendingRequests() {
  ajaxPost("/PendingRequestsServlet", null, displayPendingRequests);
}

function getActiveRequests() {
  ajaxPost("/ActiveRequestsServlet", null,  displayRequests);
}

function getClosedRequests() {
  ajaxPost("/ClosedRequestsServlet", null, displayRequests);
}

function displayRequests(response) {
  let json = JSON.parse(response);

  let th1 = document.createElement('th');
  let th2 = document.createElement('th');
  let th3 = document.createElement('th');
  let th4 = document.createElement('th');
  let th5 = document.createElement('th');

  let tr = document.createElement('tr');

  let th1t = document.createTextNode('Event');
  let th2t = document.createTextNode('Location');
  let th3t = document.createTextNode('Type ID');
  let th4t = document.createTextNode('Cost');
  let th5t = document.createTextNode('Employee ID');

  let table = document.createElement('table');

  // construct the table
  th1.appendChild(th1t);
  th2.appendChild(th2t);
  th3.appendChild(th3t);
  th4.appendChild(th4t);
  th5.appendChild(th5t);
  tr.appendChild(th1);
  tr.appendChild(th2);
  tr.appendChild(th3);
  tr.appendChild(th4);
  tr.appendChild(th5);
  table.appendChild(tr);

  // loop through data appending each row
  for (let index = 0; index < json.length; ++index) {
    let data = json[index];

    let request_id = document.createTextNode(data["id"]);
    let employee = document.createTextNode(data["employee"]);
    let name = document.createTextNode(data["name"]);
    let location = document.createTextNode(data["location"]);
    let event_time = document.createTextNode(new Date(data["eventTime"]).toLocaleDateString());
    let cost = document.createTextNode(data["eventCost"]);
    let event_type = document.createTextNode(data["eventType"]);
    let grade_format = document.createTextNode(data["gradeFormat"]);
    let request_time = document.createTextNode(new Date(data["requestTime"]).toLocaleDateString());
    let justification = document.createTextNode(data["justification"]);
    let amount = document.createTextNode(data["amountReimbursed"]);
    let id = data["id"];
    let detail = data["id"] + "-detail";

    tr = document.createElement('tr');
    tr.setAttribute('id', id);

    let td1 = document.createElement('td');
    let td2 = document.createElement('td');
    let td3 = document.createElement('td');
    let td4 = document.createElement('td');
    let td5 = document.createElement('td');

    td1.appendChild(document.createTextNode(data["name"]));
    td2.appendChild(document.createTextNode(data["location"]));
    td3.appendChild(document.createTextNode(data["eventType"]));
    td4.appendChild(document.createTextNode(data["eventCost"]));
    td5.appendChild(document.createTextNode(data["employee"]));

    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);
    tr.appendChild(td5);

    let btn = document.createElement('button');
    let btnText = document.createTextNode("View");
    btn.appendChild(btnText);
    btn.addEventListener("click", function () {
      let row = document.getElementById(detail);
      row.style.display = (row.style.display === '') ? 'none' : '';
    });
    tr.appendChild(btn);
    table.appendChild(tr);

    tr = document.createElement('tr');

    td1 = document.createElement('td');
    td2 = document.createElement('td');
    td3 = document.createElement('td');
    td4 = document.createElement('td');
    td5 = document.createElement('td');
    let td6 = document.createElement('td');
    let td7 = document.createElement('td');
    let td8 = document.createElement('td');
    let td9 = document.createElement('td');
    let td10 = document.createElement('td');
    let td11 = document.createElement('td');

    let th1 = document.createElement('th');
    let th2 = document.createElement('th');
    let th3 = document.createElement('th');
    let th4 = document.createElement('th');
    let th5 = document.createElement('th');
    let th6 = document.createElement('th');
    let th7 = document.createElement('th');
    let th8 = document.createElement('th');
    let th9 = document.createElement('th');
    let th10 = document.createElement('th');
    let th11 = document.createElement('th');

    let th1t = document.createTextNode('REQUEST ID');
    let th2t = document.createTextNode('EMPLOYEE ID');
    let th3t = document.createTextNode('EVENT NAME');
    let th4t = document.createTextNode('EVENT LOCATION');
    let th5t = document.createTextNode('EVENT TIME');
    let th6t = document.createTextNode('EVENT COST');
    let th7t = document.createTextNode('EVENT TYPE');
    let th8t = document.createTextNode('GRADE FORMAT');
    let th9t = document.createTextNode('REQUESTED TIME');
    let th10t = document.createTextNode('JUSTIFICATION');
    let th11t = document.createTextNode('AMOUNT REIMBURSED');

    let tr1 = document.createElement('tr');
    let tr2 = document.createElement('tr');
    let tr3 = document.createElement('tr');
    let tr4 = document.createElement('tr');
    let tr5 = document.createElement('tr');
    let tr6 = document.createElement('tr');
    let tr7 = document.createElement('tr');
    let tr8 = document.createElement('tr');
    let tr9 = document.createElement('tr');
    let tr10 = document.createElement('tr');
    let tr11 = document.createElement('tr');

    let table2 = document.createElement('table');

    td1.appendChild(request_id);
    td2.appendChild(employee);
    td3.appendChild(name);
    td4.appendChild(location);
    td5.appendChild(event_time);
    td6.appendChild(cost);
    td7.appendChild(event_type);
    td8.appendChild(grade_format);
    td9.appendChild(request_time);
    td10.appendChild(justification);
    td11.appendChild(amount);

    th1.appendChild(th1t);
    th2.appendChild(th2t);
    th3.appendChild(th3t);
    th4.appendChild(th4t);
    th5.appendChild(th5t);
    th6.appendChild(th6t);
    th7.appendChild(th7t);
    th8.appendChild(th8t);
    th9.appendChild(th9t);
    th10.appendChild(th10t);
    th11.appendChild(th11t);

    tr1.appendChild(th1);
    tr1.appendChild(td1);
    tr2.appendChild(th2);
    tr2.appendChild(td2);
    tr3.appendChild(th3);
    tr3.appendChild(td3);
    tr4.appendChild(th4);
    tr4.appendChild(td4);
    tr5.appendChild(th5);
    tr5.appendChild(td5);
    tr6.appendChild(th6);
    tr6.appendChild(td6);
    tr7.appendChild(th7);
    tr7.appendChild(td7);
    tr8.appendChild(th8);
    tr8.appendChild(td8);
    tr9.appendChild(th9);
    tr9.appendChild(td9);
    tr10.appendChild(th10);
    tr10.appendChild(td10);
    tr11.appendChild(th11);
    tr11.appendChild(td11);

    table2.appendChild(tr1);
    table2.appendChild(tr2);
    table2.appendChild(tr3);
    table2.appendChild(tr4);
    table2.appendChild(tr5);
    table2.appendChild(tr6);
    table2.appendChild(tr7);
    table2.appendChild(tr8);
    table2.appendChild(tr9);
    table2.appendChild(tr10);
    table2.appendChild(tr11);
    tr.appendChild(table2);
    tr.setAttribute('id', detail);
    tr.style.display = 'none';
    table.appendChild(tr);
  }
  table.setAttribute("style", "width:100%");
  document.getElementById('data').innerText = "";
  document.getElementById('data').appendChild(table);
}

function displayPendingRequests(response) {
  let json = JSON.parse(response);

  let th1 = document.createElement('th');
  let th2 = document.createElement('th');
  let th3 = document.createElement('th');
  let th4 = document.createElement('th');
  let th5 = document.createElement('th');

  let tr = document.createElement('tr');

  let th1t = document.createTextNode('Event');
  let th2t = document.createTextNode('Location');
  let th3t = document.createTextNode('Type ID');
  let th4t = document.createTextNode('Cost');
  let th5t = document.createTextNode('Employee ID');

  let table = document.createElement('table');

  // construct the table
  th1.appendChild(th1t);
  th2.appendChild(th2t);
  th3.appendChild(th3t);
  th4.appendChild(th4t);
  th5.appendChild(th5t);
  tr.appendChild(th1);
  tr.appendChild(th2);
  tr.appendChild(th3);
  tr.appendChild(th4);
  tr.appendChild(th5);
  table.appendChild(tr);

  // loop through data appending each row
  for (let index = 0; index < json.length; ++index) {
    let data = json[index];

    let request_id = document.createTextNode(data["id"]);
    let employee = document.createTextNode(data["employee"]);
    let name = document.createTextNode(data["name"]);
    let location = document.createTextNode(data["location"]);
    let event_time = document.createTextNode(new Date(data["eventTime"]).toLocaleDateString());
    let cost = document.createTextNode(data["eventCost"]);
    let event_type = document.createTextNode(data["eventType"]);
    let grade_format = document.createTextNode(data["gradeFormat"]);
    let request_time = document.createTextNode(new Date(data["requestTime"]).toLocaleDateString());
    let justification = document.createTextNode(data["justification"]);
    let amount = document.createTextNode(data["amountReimbursed"]);
    let id = data["id"];
    let detail = data["id"] + "-detail";

    tr = document.createElement('tr');
    tr.setAttribute('id', id);

    let td1 = document.createElement('td');
    let td2 = document.createElement('td');
    let td3 = document.createElement('td');
    let td4 = document.createElement('td');
    let td5 = document.createElement('td');

    td1.appendChild(document.createTextNode(data["name"]));
    td2.appendChild(document.createTextNode(data["location"]));
    td3.appendChild(document.createTextNode(data["eventType"]));
    td4.appendChild(document.createTextNode(data["eventCost"]));
    td5.appendChild(document.createTextNode(data["employee"]));

    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);
    tr.appendChild(td5);

    let btn = document.createElement('button');
    let btnText = document.createTextNode("View");
    btn.appendChild(btnText);
    btn.addEventListener("click", function () {
      let row = document.getElementById(detail);
      row.style.display = (row.style.display === '') ? 'none' : '';
    });
    tr.appendChild(btn);
    table.appendChild(tr);

    tr = document.createElement('tr');

    td1 = document.createElement('td');
    td2 = document.createElement('td');
    td3 = document.createElement('td');
    td4 = document.createElement('td');
    td5 = document.createElement('td');
    let td6 = document.createElement('td');
    let td7 = document.createElement('td');
    let td8 = document.createElement('td');
    let td9 = document.createElement('td');
    let td10 = document.createElement('td');
    let td11 = document.createElement('td');

    let th1 = document.createElement('th');
    let th2 = document.createElement('th');
    let th3 = document.createElement('th');
    let th4 = document.createElement('th');
    let th5 = document.createElement('th');
    let th6 = document.createElement('th');
    let th7 = document.createElement('th');
    let th8 = document.createElement('th');
    let th9 = document.createElement('th');
    let th10 = document.createElement('th');
    let th11 = document.createElement('th');

    let th1t = document.createTextNode('REQUEST ID');
    let th2t = document.createTextNode('EMPLOYEE ID');
    let th3t = document.createTextNode('EVENT NAME');
    let th4t = document.createTextNode('EVENT LOCATION');
    let th5t = document.createTextNode('EVENT TIME');
    let th6t = document.createTextNode('EVENT COST');
    let th7t = document.createTextNode('EVENT TYPE');
    let th8t = document.createTextNode('GRADE FORMAT');
    let th9t = document.createTextNode('REQUESTED TIME');
    let th10t = document.createTextNode('JUSTIFICATION');
    let th11t = document.createTextNode('AMOUNT REIMBURSED');

    let tr1 = document.createElement('tr');
    let tr2 = document.createElement('tr');
    let tr3 = document.createElement('tr');
    let tr4 = document.createElement('tr');
    let tr5 = document.createElement('tr');
    let tr6 = document.createElement('tr');
    let tr7 = document.createElement('tr');
    let tr8 = document.createElement('tr');
    let tr9 = document.createElement('tr');
    let tr10 = document.createElement('tr');
    let tr11 = document.createElement('tr');

    let table2 = document.createElement('table');

    td1.appendChild(request_id);
    td2.appendChild(employee);
    td3.appendChild(name);
    td4.appendChild(location);
    td5.appendChild(event_time);
    td6.appendChild(cost);
    td7.appendChild(event_type);
    td8.appendChild(grade_format);
    td9.appendChild(request_time);
    td10.appendChild(justification);
    td11.appendChild(amount);

    th1.appendChild(th1t);
    th2.appendChild(th2t);
    th3.appendChild(th3t);
    th4.appendChild(th4t);
    th5.appendChild(th5t);
    th6.appendChild(th6t);
    th7.appendChild(th7t);
    th8.appendChild(th8t);
    th9.appendChild(th9t);
    th10.appendChild(th10t);
    th11.appendChild(th11t);

    tr1.appendChild(th1);
    tr1.appendChild(td1);
    tr2.appendChild(th2);
    tr2.appendChild(td2);
    tr3.appendChild(th3);
    tr3.appendChild(td3);
    tr4.appendChild(th4);
    tr4.appendChild(td4);
    tr5.appendChild(th5);
    tr5.appendChild(td5);
    tr6.appendChild(th6);
    tr6.appendChild(td6);
    tr7.appendChild(th7);
    tr7.appendChild(td7);
    tr8.appendChild(th8);
    tr8.appendChild(td8);
    tr9.appendChild(th9);
    tr9.appendChild(td9);
    tr10.appendChild(th10);
    tr10.appendChild(td10);
    tr11.appendChild(th11);
    tr11.appendChild(td11);

    table2.appendChild(tr1);
    table2.appendChild(tr2);
    table2.appendChild(tr3);
    table2.appendChild(tr4);
    table2.appendChild(tr5);
    table2.appendChild(tr6);
    table2.appendChild(tr7);
    table2.appendChild(tr8);
    table2.appendChild(tr9);
    table2.appendChild(tr10);
    table2.appendChild(tr11);

    let approve = document.createElement('button');
    approve.appendChild(document.createTextNode("Approve"));
    approve.addEventListener('click', function() {
      ajaxPost('/ApprovalServlet', 'id=' + id + '&approval=1', processApproval);
    });

    let disapprove =document.createElement('button');
    disapprove.appendChild(document.createTextNode("Disapprove"));
    approve.addEventListener('click', function() {
      ajaxPost('/ApprovalServlet', 'id=' + id + '&approval=0', processApproval);
    });

    table2.appendChild(approve);
    table2.appendChild(disapprove);

    tr.appendChild(table2);
    tr.setAttribute('id', detail);
    tr.style.display = 'none';
    table.appendChild(tr);
  }
  table.setAttribute("style", "width:100%");
  document.getElementById('data').innerText = "";
  document.getElementById('data').appendChild(table);
}

function processApproval(response) {
  let json = JSON.parse(response);
  if (json['status'] === "success") {
    document.getElementById(json['id']).remove();
    document.getElementById(json['id']+'-detail').remove();
  }

}

function login() {
  let email = document.getElementById('email').value;
  let password = document.getElementById('password').value;
  ajaxPost("/Auth", "email=" + email + "&password=" + password, processLoginForm);
}

function processLoginForm(response) {
  let json = JSON.parse(response);
  if (json.status === "failure") {
    document.getElementById('login_message').innerText = "Username and password combination is incorrect!";
  } else {
    window.location.href = "/user";
  }
}

function logout() {
  let xhr = new XMLHttpRequest();
  xhr.open("POST", '/Logout');

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      console.log("success");
      window.location.href = "/";
    }
  };
  xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhr.send();
}

function newRequest() {
  ajaxPost("/NewRequestServlet", null, displayDetails);
}

function getEventTypes() {
  ajaxPost("/EventTypeServlet", null, processEventTypes);
}

function getGradeFormats() {
  ajaxPost("/GradeFormatServlet", null, processGradeFormats);
}

function processEventTypes(response) {
  let json = JSON.parse(response);
  let menu = document.getElementById('event_type');

  for (let index = 0; index < json.length; ++index) {
    let text = document.createTextNode(json[index]['name']);
    let option = document.createElement('option');
    option.setAttribute('value', json[index]['id']);
    option.appendChild(text);
    menu.appendChild(option);
  }
}

function processGradeFormats(response) {
  let json = JSON.parse(response);
  let menu = document.getElementById('grade_format');

  for (let index = 0; index < json.length; ++index) {
    let text = document.createTextNode(json[index]['desc']);
    let option = document.createElement('option');
    option.setAttribute('value', json[index]['id']);
    option.appendChild(text);
    menu.appendChild(option);
  }
}
