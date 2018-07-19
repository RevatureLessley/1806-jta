
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
    //TODO: change this conditional. The file uploading is optional!
    if (file) {
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function (evt) {
            let data = evt.target.result;
            let xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                if (xhr.readyState == 4){
                    document.write(xhr.response);
                }
            }
            let type = document.getElementById("type").selectedIndex;
            xhr.open("POST", "../Reimbursement.Servlet");
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhr.send('date=' +date+ '&' +
                'location=' +location+ '&' +
                'description=' +description+ '&' +
                'cost=' +cost+ '&' +
                'type=' +type+ '&' +
                'picUrl='+data);
        }
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
