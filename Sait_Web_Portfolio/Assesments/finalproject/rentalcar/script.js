var xhr = new XMLHttpRequest();
var data;
window.onload=loaddata;
function loaddata() {

	document.getElementById("lname_search").addEventListener("keyup", function (){ search(this.value);},false);
    document.getElementById("Client_List").addEventListener("click", function (){ fillForm(this.value);},false);
	
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4 && xhr.status == 200) {
        data = JSON.parse(xhr.responseText);
        search("");
    }
    };
    xhr.open("GET", "rentalclients.json", true);
    xhr.send();
}

function fillForm(i){
    var obj=data[i];
    document.getElementById("fname").value=obj.first_name;
    document.getElementById("lname").value=obj.last_name;
    document.getElementById("pnumber").value=obj.phone;
    document.getElementById("address").value=obj.address;
    document.getElementById("prov").value=obj.state_prov;
    document.getElementById("email").value=obj.email;
    fields = document.getElementsByClassName("client_field");
    for(var j = 0;j<fields.length;j++){
        fields[j].removeAttribute('disabled');
    }
}

function search(inputValue){
    var tableBody=document.getElementById("tbody");
    var output="";
    for(var i=0; i<data.length; i++){
        var obj=data[i];
        var lname=obj.last_name;
        if(lname.startsWith(inputValue)){
            output+="<option value='"+i+ "'> "+obj.last_name+" "+obj.first_name+"</option>";
        }
    }
    document.getElementById("Client_List").innerHTML=output;
}

function displayOrder(){
    if(formCheck()){
        var info=document.getElementById("fname").value+" "+document.getElementById("lname").value+"<br/>"+document.getElementById("address").value+", "+document.getElementById("prov").value+"<br/>Phone: "+document.getElementById("pnumber").value+"<br/>Email: "+document.getElementById("email").value
        document.getElementById("Customer_Information").innerHTML=info;

        var table = document.getElementById("Result_Table");
        var total = 0;
        if(document.getElementById("compact").checked){
            var row = table.insertRow(-1);
            var dayRentalCost = 25;
            total += dayRentalCost * document.getElementById("duration").value;
            var cellItem = row.insertCell(0);
            var cellDayCost= row.insertCell(1);
            var cellTotal= row.insertCell(2);
            cellItem.innerHTML = "Compact Car";
            cellDayCost.innerHTML = "$"+dayRentalCost;
            cellTotal.innerHTML = "$"+ (dayRentalCost+ " * "+document.getElementById("duration").value +" = $"+((dayRentalCost*document.getElementById("duration").value).toFixed(2)));
        }
        else if(document.getElementById("midsize").checked){
            var row = table.insertRow(-1);
            var dayRentalCost = 30;
            total += dayRentalCost * document.getElementById("duration").value;
            var cellItem = row.insertCell(0);
            var cellDayCost= row.insertCell(1);
            var cellTotal= row.insertCell(2);
            cellItem.innerHTML = "Midsize Car";
            cellDayCost.innerHTML = "$"+dayRentalCost;
            cellTotal.innerHTML = "$"+ (dayRentalCost+ " * "+document.getElementById("duration").value +" = $"+((dayRentalCost*document.getElementById("duration").value).toFixed(2)));
        }
        else if(document.getElementById("luxury").checked){
            var row = table.insertRow(-1);
            var dayRentalCost = 35;
            total += dayRentalCost * document.getElementById("duration").value;
            var cellItem = row.insertCell(0);
            var cellDayCost= row.insertCell(1);
            var cellTotal= row.insertCell(2);
            cellItem.innerHTML = "Luxury Car";
            cellDayCost.innerHTML = "$"+dayRentalCost;
            cellTotal.innerHTML = "$"+ (dayRentalCost+ " * "+document.getElementById("duration").value +" = $"+((dayRentalCost*document.getElementById("duration").value).toFixed(2)));
        }
        else{
            var row = table.insertRow(-1);
            var dayRentalCost = 40;
            total += dayRentalCost * document.getElementById("duration").value;
            var cellItem = row.insertCell(0);
            var cellDayCost= row.insertCell(1);
            var cellTotal= row.insertCell(2);
            cellItem.innerHTML = "Van/Truck";
            cellDayCost.innerHTML = "$"+dayRentalCost;
            cellTotal.innerHTML = (dayRentalCost+ " * "+document.getElementById("duration").value +" = $"+((dayRentalCost*document.getElementById("duration").value).toFixed(2)));
        }
        if(document.getElementById("gps").checked){
            var row = table.insertRow(-1);
            var dayRentalCost = 10;
            total += dayRentalCost * document.getElementById("duration").value;
            var cellItem = row.insertCell(0);
            var cellDayCost= row.insertCell(1);
            var cellTotal= row.insertCell(2);
            cellItem.innerHTML = "GPS";
            cellDayCost.innerHTML = "$"+dayRentalCost;
            cellTotal.innerHTML = "$"+ (dayRentalCost+ " * "+document.getElementById("duration").value +" = $"+((dayRentalCost*document.getElementById("duration").value).toFixed(2)));
        }
        if(document.getElementById("roofrack").checked){
            var row = table.insertRow(-1);
            var dayRentalCost = 5;
            total += dayRentalCost * document.getElementById("duration").value;
            var cellItem = row.insertCell(0);
            var cellDayCost= row.insertCell(1);
            var cellTotal= row.insertCell(2);
            cellItem.innerHTML = "Roof Rack";
            cellDayCost.innerHTML = "$"+dayRentalCost;
            cellTotal.innerHTML = "$"+ (dayRentalCost+ " * "+document.getElementById("duration").value +" = $"+((dayRentalCost*document.getElementById("duration").value).toFixed(2)));
        }
        if(document.getElementById("bikerack").checked){
            var row = table.insertRow(-1);
            var dayRentalCost = 5;
            total += dayRentalCost * document.getElementById("duration").value;
            var cellItem = row.insertCell(0);
            var cellDayCost= row.insertCell(1);
            var cellTotal= row.insertCell(2);
            cellItem.innerHTML = "Bicycle Rack";
            cellDayCost.innerHTML = "$"+dayRentalCost;
            cellTotal.innerHTML = "$"+ (dayRentalCost+ " * "+document.getElementById("duration").value +" = $"+((dayRentalCost*document.getElementById("duration").value).toFixed(2)));
        }
        if(document.getElementById("childseat").checked){
            var row = table.insertRow(-1);
            total += dayRentalCost * document.getElementById("duration").value;
            var cellItem = row.insertCell(0);
            var cellDayCost= row.insertCell(1);
            var cellTotal= row.insertCell(2);
            cellItem.innerHTML = "Child Seat";
            cellDayCost.innerHTML = "Free";
            cellTotal.innerHTML = "Free";
        }
        var row = table.insertRow(-1);
        var cell1 = row.insertCell(0);
        var cell2= row.insertCell(1);
        var cell3= row.insertCell(2);
        cell1.innerHTML="";
        cell2.innerHTML="Total Cost:";
        cell3.innerHTML="$"+total.toFixed(2);

        document.getElementById("Client_Box").style.display="none";
        document.getElementById("Form_Box").style.display="none";
    }

}

function formCheck(){
    if(document.getElementById("fname").value.length==0){
        alert("Enter your First Name!!");
        return false;
    }
    else if(document.getElementById("lname").value.length==0){
        alert("Enter your Last Name!!");
        return false;
    }
    else if(document.getElementById("pnumber").value.length==0){
        alert("Enter your Phone Number!!");
        return false;
    }
    else if(document.getElementById("address").value.length==0){
        alert("Enter your address!!");
        return false;
    }
    else if(document.getElementById("prov").value.length==0){
        alert("Please enter State or Province!!");
        return false;
    }
    else if(document.getElementById("email").value.length==0){
        alert("Enter your Email!!");
        return false;
    }
    else{
        try{
            document.querySelector("input[name='car']:checked").value;
        }
        catch(err){
            alert("Please Select a Car Model!!");
            return false;
        }
        return true;
    }
}