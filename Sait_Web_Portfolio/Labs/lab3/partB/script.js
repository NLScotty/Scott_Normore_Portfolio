function onFormSubmit(){
    if(document.getElementById("date").value.length==0){

    }
    else if(document.getElementById("fName").value.length==0){

    }
    else if(document.getElementById("lName").value.length==0){

    }
    else if(document.getElementById("address").value.length==0){

    }
    else if(document.getElementById("pCode").value.length==0){

    }
    else if(document.getElementById("phone").value.length==0){

    }
    else if(document.getElementById("phone").value.length==0){

    }
    else if(document.getElementById("age").value.length==0 || document.getElementById("age").value.length==0 < 18 ){

    }
    else{
        var container = document.createElement("div");
        var para = document.createElement("p");
        var text = "TESTING";
        para.appendChild(text);
        container.appendChild(para);
        document.getElementById("listArea").appendChild(container);
    }
}