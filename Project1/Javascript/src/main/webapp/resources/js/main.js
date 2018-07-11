/*
    JavaScript block comment
 */

// JavaScript line comment

function writeToPage() {
    document.write("<h1>Document.write!</h1>");
}

function changeSection() {
    let el = document.getElementById("magicSection");
    let random = Math.floor(Math.random()*4);

    switch (random) {
        case 0:
            el.setAttribute('style', 'background-color:red');
            break;
        case 1:
            el.setAttribute('style', 'background-color:blue');
            break;
        case 2:
            el.setAttribute('style', 'background-color:green');
            break;
        case 3:
            el.setAttribute('style', 'background-color:purple');
            break;
    }
}
npcId = 0;

function addNPC() {
    // Store input values first
    let npcName = document.getElementById("NPCName").value;
    let npcClass = document.getElementById("NPCClass").value;
    // .value grabs any written text dor an input element

    // Creat nodes for the TEXT to go in
    let idText = document.createTextNode(++npcId);
    let nameText = document.createTextNode(npcName);
    let classText = document.createTextNode(npcClass);
    let del = document.createTextNode('X');

    let td1 = document.createElement('td'); // <td></td>
    let td2 = document.createElement('td');
    let td3 = document.createElement('td');
    let td4 = document.createElement('td');

    let delBut = document.createElement('button') // <button></button>
    delBut.setAttribute('onclick', 'removeRow(' + npcId + ')');
    delBut.setAttribute('style', 'color:red');
    delBut.appendChild(del);

    td1.appendChild(idText);
    td2.appendChild(nameText);
    td3.appendChild(classText);
    td4.appendChild(delBut);

    let row = document.createElement('tr');
    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    row.appendChild(td4);

    row.setAttribute('id', npcId);
    document.getElementById('theTable').appendChild(row);
    document.getElementById('NPCName').value = '';
    document.getElementById('NPCClass').value = '';
}

function removeRow(x) {
    document.getElementById(x).remove();
}
