window.addEventListener("load", updatePlanterSelection, false);
var asynchrequest;

function updatePlanterSelection(){
    if(document.getElementById("square").checked){
        clearContent();
        getContent("square.html");
    }else if(document.getElementById("cylinder").checked){
        clearContent();
        getContent("cylinder.html");
    }else if(document.getElementById("sphere").checked){
        clearContent();
        getContent("sphere.html");
    }else{
        clearContent();
        getContent("cone.html");
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
    if(document.getElementById("square").checked){
        if(document.getElementById("length").value.length==0){
            alert("Please Enter Length!");
            return false;
        }
        if(document.getElementById("width").value.length==0){
            alert("Please Enter Width");
            return false;
        }if(document.getElementById("height").value.length==0){
            alert("Please Enter Height");
            return false;
        }
    }
    else if(document.getElementById("cone").checked){
        if(document.getElementById("tradius").value.length==0){
            alert("Please Enter Top Radius");
            return false;
        }
        if(document.getElementById("bradius").value.length==0){
            alert("Please Enter Bottom Radius");
            return false;
        }
        if(document.getElementById("height").value.length==0){
            alert("Plase enter Height");
            return false;
        }
    }else if(document.getElementById("cylinder").checked){
        if(document.getElementById("radius").value.length==0){
            alert("Please Enter Radius");
            return false;
        }
        if(document.getElementById("height").value.length==0){
            alert("Plase enter Height");
            return false;
        }
    }else if(document.getElementById("sphere").checked){
        if(document.getElementById("radius").value.length==0){
            alert("Please Enter Radius");
            return false;
        }
    }
    return true;
}
function displayInvoice(){
    var CustomerInfo=document.getElementById("fName").value+" "+document.getElementById("lName").value+"<br/>"+document.getElementById("address").value+"<br/>"+document.getElementById("pCode").value+"<br/>"+document.getElementById("phone").value+"<br/>"+document.getElementById("email").value

    document.getElementById("Customer_Info").innerHTML=CustomerInfo;

    if(document.getElementById("square").checked){
        var table = document.getElementById("Invoice_Table");
        var row = table.insertRow(-1);
        var length = document.getElementById("length").value;
        var width = document.getElementById("width").value;
        var height = document.getElementById("height").value;
        var volume= length*width*height;
        var costOfPlanter = (volume * 0.001);
        var cellItem = row.insertCell(0);
        var cellTotal= row.insertCell(1);
        cellItem.innerHTML = "Square Planter "+length+" X "+width+" X"+height;
        cellTotal.innerHTML = "$"+ ((costOfPlanter).toFixed(2));
    }else if(document.getElementById("cone").checked){
        var table = document.getElementById("Invoice_Table");
        var row = table.insertRow(-1);
        var bradius = document.getElementById("bradius").value;
        var tradius = document.getElementById("tradius").value;
        var height = document.getElementById("height").value;
        var volume = (1/3)*3.14*(bradius*bradius+(bradius*tradius)+tradius*tradius)*height;
        var costOfPlanter = (volume * 0.002);
        var cellItem = row.insertCell(0);
        var cellTotal= row.insertCell(1);
        cellItem.innerHTML = "Top Radius "+tradius+" Bottom Radius "+bradius+" Height "+height;
        cellTotal.innerHTML = "$"+ ((costOfPlanter).toFixed(2));
    }else if(document.getElementById("cylinder").checked){
        var table = document.getElementById("Invoice_Table");
        var row = table.insertRow(-1);
        var radius = document.getElementById("radius").value;
        var height = document.getElementById("height").value;
        var volume = 3.14*radius*radius*height;
        var costOfPlanter = (volume * 0.0012);
        var cellItem = row.insertCell(0);
        var cellTotal= row.insertCell(1);
        cellItem.innerHTML = "Cyinder Height "+height+" with radius of "+radius;
        cellTotal.innerHTML = "$"+ ((costOfPlanter).toFixed(2));
    }else if(document.getElementById("sphere").checked){
        var table = document.getElementById("Invoice_Table");
        var row = table.insertRow(-1);
        var radius = document.getElementById("radius").value;
        var volume = 0.5*((4/3)*3.14*radius*radius*radius);
        var costOfPlanter = (volume * 0.0015);
        var cellItem = row.insertCell(0);
        var cellTotal= row.insertCell(1);
        cellItem.innerHTML = "Half sphere with radius of "+radius;
        cellTotal.innerHTML = "$"+ ((costOfPlanter).toFixed(2));
    }
    document.getElementById("Order_Page").style.display="none";
    document.getElementById("Invoice_Page").style.display="block";
}


function getContent(planterpage) {

    asynchrequest= new XMLHttpRequest();
    asynchrequest.onreadystatechange = function() {
if (asynchrequest.readyState == 4 && asynchrequest.status == 200) {
  document.getElementById("Planter_Form").innerHTML = asynchrequest.responseText;
}
};
asynchrequest.open("GET", planterpage, true);
asynchrequest.send();
}


function clearContent() {

document.getElementById("Planter_Form").innerHTML="";

}

function calc(){
    if(verifyPlanterForm()){
        if(document.getElementById("square").checked){
            var length = document.getElementById("length").value;
            var width = document.getElementById("width").value;
            var height = document.getElementById("height").value;
            var volume= length*width*height;
            var costOfPlanter = (volume * 0.001).toFixed(2);
        }else if(document.getElementById("cylinder").checked){
            var radius = document.getElementById("radius").value;
            var height = document.getElementById("height").value;
            var volume = 3.14*radius*radius*height;
            var costOfPlanter = (volume * 0.0012).toFixed(2);
        }else if(document.getElementById("sphere").checked){
            var radius = document.getElementById("radius").value;
            var volume = 0.5*((4/3)*3.14*radius*radius*radius);
            var costOfPlanter = (volume * 0.0015).toFixed(2);
        }else if(document.getElementById("cone")){
            var bradius = document.getElementById("bradius").value;
            var tradius = document.getElementById("tradius").value;
            var height = document.getElementById("height").value;
            var volume = (1/3)*3.14*(bradius*bradius+(bradius*tradius)+tradius*tradius)*height;
            var costOfPlanter = (volume * 0.002).toFixed(2);
        }
        document.getElementById("Curent_Volume").innerHTML="Volume of Planter in cubic centimers: "+volume;
        document.getElementById("Current_Total").innerHTML="Cost of Planter: "+costOfPlanter;
    }else{
        document.getElementById("Curent_Volume").innerHTML="Volume of Planter in cubic centimers: ";
        document.getElementById("Current_Total").innerHTML="Cost of Planter: ";
    }
}


function verifyPlanterForm(){
    if(document.getElementById("square").checked){
        if(document.getElementById("length").value.length==0){
            return false;
        }
        if(document.getElementById("width").value.length==0){
            return false;
        }
        if(document.getElementById("height").value.length==0){
            return false;
        }
    }else if(document.getElementById("sphere").checked){
        if(document.getElementById("radius").value.length==0){
            return false;
        }
    }else if(document.getElementById("cylinder").checked){
        if(document.getElementById("height").value.length==0){
            return false;
        }
        if(document.getElementById("radius").value.length==0){
            return false;
        }
    }else if(document.getElementById("cone").checked){
        if(document.getElementById("height").value.length==0){
            return false;
        }
        if(document.getElementById("bradius").value.length==0){
            return false;
        }if(document.getElementById("tradius").value.length==0){
            return false;
        }
    }
    return true;
}