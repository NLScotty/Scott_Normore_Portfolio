var count=0;
function onFormSubmit(){
    if(document.getElementById("date").value.length==0){
        alert("Enter A Date!!");
    }
    else if(document.getElementById("fName").value.length==0){
        alert("Enter your First Name!!");
    }
    else if(document.getElementById("lName").value.length==0){
        alert("Enter your Last Name!!");
    }
    else if(document.getElementById("address").value.length==0){
        alert("Enter your address!!");
    }
    else if(document.getElementById("pCode").value.length==0){
        alert("Enter your Postal Code!!");
    }
    else if(document.getElementById("phone").value.length==0){
        alert("Enter your Phone Number!!");
    }
    else if(document.getElementById("age").value.length==0 || document.getElementById("age").value.length==0 < 18 ){
        alert("Age must be > than 18!!");
    }
    else{
        var localid=count;
        var container = document.createElement("div");
        var prevClient="";
        var gender="";
        var owner="";
        var items="";

        if(document.getElementById("yes").checked){
            prevClient="YES";
        }else{
            prevClient="NO";
        }

        if(document.getElementById("male").checked){
            gender="Male";
        }else{
            gender="Female";
        }

        if(document.getElementById("owner").checked){
            prevClient="Owner";
        }else{
            prevClient="Renter";
        }

        if(document.getElementById("vehicle").checked){
            items=items+"Vehicle";
        }
        if(document.getElementById("rrsp").checked){
            if(items.length>0){
                items=items+", "
            }
            items=items+"RRSP";
        }
        if(document.getElementById("tfsa").checked){
            if(items.length>0){
                items=items+", "
            }
            items=items+"TFSA";
        }
        if(document.getElementById("rrif").checked){
            if(items.length>0){
                items=items+", "
            }
            items=items+"RRIF";
        }
        if(items.length==0){
            items="None"
        }
        var constructedString="Date: "+document.getElementById("date").value+" First Name: "+document.getElementById("fName").value+" Last Name: "+document.getElementById("lName").value+" Address: "+document.getElementById("address").value+" PostalCode: "+document.getElementById("pCode").value+" Phone: "+document.getElementById("phone").value+" Previous Client: "+prevClient+" Gender: "+gender+" Age: "+document.getElementById("age").value+" Ownertype: "+owner+" Annual Income: "+ document.getElementById("income").value+" Items Owned: "+items;
        container.id=localid;

        var para = document.createElement("p");
        var text = document.createTextNode(constructedString);
        para.appendChild(text);

        var eButton = document.createElement("button"); 
        var buttonLabel = document.createTextNode("DELETE");
        eButton.appendChild(buttonLabel);
        eButton.onclick=function(){deleteThis(localid)};
        

        container.appendChild(para);
        container.appendChild(eButton);


        document.getElementById("ListArea").appendChild(container);

        count++;
    }
}
function deleteThis(param){
    document.getElementById(param).remove();
}