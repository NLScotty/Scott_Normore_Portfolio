window.addEventListener("load", updateCakeSelection, false);
var asynchrequest;

function updateCakeSelection(){
    if(document.getElementById("sheet").checked){
        clearContent();
        getContent("rectangle.html");
    }else{
        clearContent();
        getContent("round.html");
    }
}

function onSubmit(){
    if(isComplete()){
        displayInvoice();
    }
}

function isComplete(){
    if(document.getElementById("fName").value.length==0){
        alert("Please Enter First Name!");
        return false;
    }
    if(document.getElementById("lName").value.length==0){
        alert("Please Enter Last Name");
        return false;
    }
    if(document.getElementById("pCode").value.length==0){
        alert("Please Enter Postal Code!");
        return false;
    }
    if(document.getElementById("phone").value.length==0){
        alert("Please Enter Phone Number");
        return false;
    }
    if(document.getElementById("address").value.length==0){
        alert("Please Enter Address");
        return false;
    }
    if(document.getElementById("email").value.length==0){
        alert("Please Enter Email!");
        return false;
    }
    if(document.getElementById("sheet").checked){
        if(document.getElementById("length").value.length==0){
            alert("Please Enter Length!");
            return false;
        }
        if(document.getElementById("width").value.length==0){
            alert("Please Enter Width");
            return false;
        }
    }
    else{
        if(document.getElementById("radius").value.length==0){
            alert("Please Enter Radius");
            return false;
        }
        if(document.getElementById("layers").value.length==0){
            alert("Plase enter Layers");
            return false;
        }
    }
    return true;
}
function displayInvoice(){Customer
    var Info=document.getElementById("fName").value+" "+document.getElementById("lName").value+"<br/>"+document.getElementById("address").value+"<br/>"+document.getElementById("prov").value+"<br/>"+document.getElementById("phone").value+"<br/>"+document.getElementById("email").value



    document.getElementById("Customer_Info").innerHTML=CustomerInfo;

    var dailyPrice=0;
    var table = document.getElementById("Invoice_Table");
    

    document.getElementById("Order_Page").style.display="none";
    document.getElementById("Invoice_Page").style.display="block";
}


function getContent(cakepage) {

    asynchrequest= new XMLHttpRequest();
    asynchrequest.onreadystatechange = function() {
if (asynchrequest.readyState == 4 && asynchrequest.status == 200) {
  document.getElementById("Cake_Form").innerHTML = asynchrequest.responseText;
}
};
asynchrequest.open("GET", cakepage, true);
asynchrequest.send();
}


function clearContent() {

document.getElementById("Cake_Form").innerHTML="";

}