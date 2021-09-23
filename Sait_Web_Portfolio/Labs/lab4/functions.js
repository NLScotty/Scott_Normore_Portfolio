window.onload=initfunction;

var firstName;
var lastName;
var phoneNumber;
var desiredTime;
var current_datetime;
function initfunction(){
	current_datetime = new Date();
	var formatted_date = current_datetime.getDate() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getFullYear();

	document.getElementById("dtField").innerHTML = "Today's Date: " + formatted_date;
}

function saveData(){
    if(document.getElementById("fName").value.length==0){
        document.getElementById("Error_Handler").style.display="block";
        document.getElementById("Error_Handler").innerHTML="FirstName: Empty Input";
    }
    else if(document.getElementById("lName").value.length==0){
        document.getElementById("Error_Handler").style.display="block";
        document.getElementById("Error_Handler").innerHTML="LastName: Empty Input";
    }
    else if(document.getElementById("pNumber").value.length==0){
        document.getElementById("Error_Handler").style.display="block";
        document.getElementById("Error_Handler").innerHTML="Phone Number: Empty Input";
    }
    else{
        document.getElementById("Error_Handler").style.display="none";
        firstName=document.getElementById("fName").value;
        lastName=document.getElementById("lName").value;
        phoneNumber=document.getElementById("pNumber").value;
        desiredTime=document.getElementById("dTime").value;
        alert("Information Entered Successfully! Select items to place your order!");
        document.getElementById("Order_Information_Form").style.display="none";
        document.getElementById("Order_Shopping").style.display="block";
    }
}
function displayOrder(){
    var sum=0;
    var hours=parseInt(desiredTime.substring(11,13));
    var m="AM";
    if(hours>=12){
        if(hours==12){

        }
        else{
            hours=hours-12;
        }
        m="PM";
    }
    var formatted_date = desiredTime.substring(0,4)+"/"+desiredTime.substring(5,7)+"/"+desiredTime.substring(8,10)+" "+hours+desiredTime.substring(13,16)+m;
    document.getElementById("Receipt_Details").innerHTML = firstName + "<br/>" + lastName + "<br/>" + phoneNumber + "<br/>" + formatted_date;
    alert("Order has been placed, thankyou!");

    if(document.getElementById("margSelected").checked){
        sum += 12.00* document.getElementById("margNumber").value;
        var table = document.getElementById("Receipt_Table");
        var row = table.insertRow(0);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = document.getElementById("margNumber").value+" "+"Margherita";
        cellTotal.innerHTML = "$"+ (12.00* document.getElementById("margNumber").value).toFixed(2);
    }
    if(document.getElementById("quatSelected").checked){
        sum += 12.50* document.getElementById("quatNumber").value;
        var table = document.getElementById("Receipt_Table");
        var row = table.insertRow(0);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = document.getElementById("quatNumber").value+" "+"Quattro Formaggi";
        cellTotal.innerHTML = "$"+ (12.50* document.getElementById("quatNumber").value).toFixed(2);
    }
    if(document.getElementById("caprSelected").checked){
        sum += 13.00* document.getElementById("caprNumber").value;
        var table = document.getElementById("Receipt_Table");
        var row = table.insertRow(0);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = document.getElementById("caprNumber").value+" "+"Capricciosa Sandwich";
        cellTotal.innerHTML = "$"+ (13.00* document.getElementById("caprNumber").value).toFixed(2);
    }
    if(document.getElementById("schnSelected").checked){
        sum += 8.50* document.getElementById("schnNumber").value;
        var table = document.getElementById("Receipt_Table");
        var row = table.insertRow(0);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = document.getElementById("schnNumber").value+" "+"Schnitzel Sandwich";
        cellTotal.innerHTML = "$"+ (8.50* document.getElementById("schnNumber").value).toFixed(2);
    }
    if(document.getElementById("grilSelected").checked){
        sum += 9.50* document.getElementById("grilNumber").value;
        var table = document.getElementById("Receipt_Table");
        var row = table.insertRow(0);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = document.getElementById("grilNumber").value+" "+"Mixed Grill Sandwich";
        cellTotal.innerHTML = "$"+ (9.50* document.getElementById("grilNumber").value).toFixed(2);
    }
    if(document.getElementById("beefSelected").checked){
        sum += 10.00* document.getElementById("beefNumber").value;
        var table = document.getElementById("Receipt_Table");
        var row = table.insertRow(0);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = document.getElementById("beefNumber").value+" "+"Beef Sandwich";
        cellTotal.innerHTML = "$"+ (10.00* document.getElementById("beefNumber").value).toFixed(2);
    }
    if(document.getElementById("cofeSelected").checked){
        sum += 2.00* document.getElementById("cofeNumber").value;
        var table = document.getElementById("Receipt_Table");
        var row = table.insertRow(0);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = document.getElementById("cofeNumber").value+" "+"Coffee";
        cellTotal.innerHTML = "$"+ (2.00* document.getElementById("cofeNumber").value).toFixed(2);
    }
    if(document.getElementById("lateSelected").checked){
        sum += 2.50* document.getElementById("lateNumber").value;
        var table = document.getElementById("Receipt_Table");
        var row = table.insertRow(0);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = document.getElementById("lateNumber").value+" "+"Latte";
        cellTotal.innerHTML = "$"+ (2.50* document.getElementById("lateNumber").value).toFixed(2);
    }
    if(document.getElementById("sodaSelected").checked){
        sum += 1.75* document.getElementById("sodaNumber").value;
        var table = document.getElementById("Receipt_Table");
        var row = table.insertRow(0);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = document.getElementById("sodaNumber").value+" "+"Soft Drink";
        cellTotal.innerHTML = "$"+ (1.75* document.getElementById("sodaNumber").value).toFixed(2);
    }
    var table = document.getElementById("Receipt_Total");
    var row = table.insertRow(0);
    var label = row.insertCell(0);
    var value = row.insertCell(1);
    label.innerHTML="Total Cost:"
    value.innerHTML="$"+sum.toFixed(2);
    
    document.getElementById("Order_Shopping").style.display="none";
    document.getElementById("Receipt").style.display="block";
}
