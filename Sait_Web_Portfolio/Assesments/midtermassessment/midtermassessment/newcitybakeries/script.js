function updateCakeSelection(){
    if(document.getElementById("sheet").checked){
        document.getElementById("Sheet_Cake").style.display="block";
        document.getElementById("Circle_Cake").style.display="none";
    }else{
        document.getElementById("Sheet_Cake").style.display="none";
        document.getElementById("Circle_Cake").style.display="block";
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
        if(document.getElementById("layers").value.length==0){
            alert("Plase enter Layers");
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
function displayInvoice(){
    var CustomerInfo=document.getElementById("fName").value+" "+document.getElementById("lName").value+"<br/>"+document.getElementById("address").value+"<br/>"+document.getElementById("pCode").value+"<br/>"+document.getElementById("phone").value+"<br/>"+document.getElementById("email").value

    document.getElementById("Customer_Info").innerHTML=CustomerInfo;

    var sum=0;
    if(document.getElementById("sheet").checked){
        var table = document.getElementById("Invoice_Table");
        var row = table.insertRow(-1);
        var length = document.getElementById("length").value;
        var width = document.getElementById("width").value;
        var layers = document.getElementById("layers").value;
        var costOfCake = (18 + (((length * width) - 900) * 0.02)) * (1+(0.5*(layers-1)));
        sum += costOfCake;
        var cellItem = row.insertCell(0);
        var cellTotal= row.insertCell(1);
        cellItem.innerHTML = "Sheet Cake "+length+"cm length "+width+"cm width "+layers+" layers";
        cellTotal.innerHTML = "$"+ (costOfCake.toFixed(2));
    }else{
        var table = document.getElementById("Invoice_Table");
        var row = table.insertRow(-1);
        var radius = document.getElementById("radius").value;
        var layers = document.getElementById("layers").value;
        var costOfCake = (15 + (((radius * radius *3.14) - 706.5) * 0.02)) * (1+(0.5*(layers-1)));
        sum += costOfCake;
        var cellItem = row.insertCell(0);
        var cellTotal= row.insertCell(1);
        cellItem.innerHTML = "Round Cake "+radius+"cm Radius "+layers+" layers";
        cellTotal.innerHTML = "$"+ (costOfCake.toFixed(2));

    }
    if(document.getElementById("icing").checked){
        sum += 5;
        var table = document.getElementById("Invoice_Table");
        var row = table.insertRow(-1);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = "Cream Cheese Icing";
        cellTotal.innerHTML = "$"+ 5;
    }
    if(document.getElementById("toppings").checked){
        sum += 7;
        var table = document.getElementById("Invoice_Table");
        var row = table.insertRow(-1);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = "Fruit and Almond Topping";
        cellTotal.innerHTML = "$"+ 7;
    }
    if(document.getElementById("filling").checked){
        sum += 4;
        var table = document.getElementById("Invoice_Table");
        var row = table.insertRow(-1);
        var cellItem = row.insertCell(0);
        var cellTotal = row.insertCell(1);
        cellItem.innerHTML = "Jam Filling";
        cellTotal.innerHTML = "$"+ 4;
    }
    var table = document.getElementById("Invoice_Table");
    var row = table.insertRow(-1);
    var cellItem = row.insertCell(0);
    var cellTotal = row.insertCell(1);
    cellItem.innerHTML = "Total Cost";
    cellTotal.innerHTML = "$"+ (sum.toFixed(2));

    document.getElementById("Order_Page").style.display="none";
    document.getElementById("Invoice_Page").style.display="block";
}