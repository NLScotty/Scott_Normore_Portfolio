window.onload=initfunction;

var firstName;
var lastName;
var phoneNumber;
var desiredTime;
var delivery_address;
var current_datetime;

function initfunction(){
	current_datetime = new Date();
	var formatted_date = current_datetime.getDate() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getFullYear();

	document.getElementById("dtField").innerHTML = "Today's Date: " + formatted_date;
}
//same as previous assignment
function formSubmission(){
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
    //add some logic here for checking address if needed
    else if(document.getElementById("dPlace").value.length==0 && document.getElementById("orderType").value=="delivery"){
        document.getElementById("Error_Handler").style.display="block";
        document.getElementById("Error_Handler").innerHTML="Address: Empty Field!";
    }
    else{
        document.getElementById("Error_Handler").style.display="none";
        firstName=document.getElementById("fName").value;
        lastName=document.getElementById("lName").value;
        phoneNumber=document.getElementById("pNumber").value;
        desiredTime=document.getElementById("dTime").value;
        if(document.getElementById("orderType").value=="delivery"){
            delivery_address=document.getElementById("dPlace").value;
        }
        document.getElementById("Order_Information_Form").style.display="none";
        document.getElementById("Receipt").style.display="block";
        displayOrder();
    }
}

function updateDelivery(){
    if(document.getElementById("orderType").value=="delivery"){
        document.getElementById("delivery").style.visibility="visible";
        document.getElementById("timeLabel").innerHTML="Desired Delivery Time"

    }
    else{
        document.getElementById("delivery").style.visibility="hidden";
        document.getElementById("timeLabel").innerHTML="Desired Pickup Time"
    }
}

function toCheckout(){
    if(document.getElementById("vegetableNumber").value==0 && document.getElementById("fruitNumber").value==0 && document.getElementById("chickenNumber").value==0 && document.getElementById("porkNumber").value==0){
        alert("Cart is empty! Please Buy one or more Products!");
    }
    else{
        alert("Please enter information for product pickup/delivery");
        document.getElementById("Order_Information_Form").style.display="Block";
        document.getElementById("Order_Shopping").style.display="none";
    }
}

//to be changed:
// need to readjust prices, seletors, and how data is saved.
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
    var orderTypeString="";
    if(document.getElementById("orderType").value=="delivery"){
        orderTypeString="Place to be Delivered: "+delivery_address+"<br/>"+"Date to be delivered: ";
    }
    else{
        orderTypeString= "<br/>"+"Date to be picked up: ";
    }
    document.getElementById("Receipt_Details").innerHTML = "First Name: "+firstName + "<br/>" + "Last Name: "+lastName + "<br/>" + "Phone: "+phoneNumber + "<br/>" + orderTypeString + formatted_date;
    alert("Order has been placed, thankyou!");
    if(document.getElementById("vegetableNumber").value>0){
        sum += 30.00* document.getElementById("vegetableNumber").value;
        var table = document.getElementById("Receipt_Table");
        var row = table.insertRow(0);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = document.getElementById("vegetableNumber").value+" "+"Vegetable Hampers";
        cellTotal.innerHTML = "$"+ (30.00* document.getElementById("vegetableNumber").value).toFixed(2);
    }
    if(document.getElementById("fruitNumber").value>0){
        sum += 12.50* document.getElementById("fruitNumber").value;
        var table = document.getElementById("Receipt_Table");
        var row = table.insertRow(0);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = document.getElementById("fruitNumber").value+" "+"Fruit Hampers";
        cellTotal.innerHTML = "$"+ (20.00* document.getElementById("fruitNumber").value).toFixed(2);
    }
    if(document.getElementById("chickenNumber").value>0){
        sum += 8.50* document.getElementById("chickenNumber").value;
        var table = document.getElementById("Receipt_Table");
        var row = table.insertRow(0);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = document.getElementById("chickenNumber").value+" "+"Wole Chickens";
        cellTotal.innerHTML = "$"+ (4.00*2.50* document.getElementById("chickenNumber").value).toFixed(2);
    }
    if(document.getElementById("porkNumber").value>0){
        sum += 9.50* document.getElementById("porkNumber").value;
        var table = document.getElementById("Receipt_Table");
        var row = table.insertRow(0);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = document.getElementById("porkNumber").value+" "+"Kg of pork";
        cellTotal.innerHTML = "$"+ (5.00* document.getElementById("porkNumber").value).toFixed(2);
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
