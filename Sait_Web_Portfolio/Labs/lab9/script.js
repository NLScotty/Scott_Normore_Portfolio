var xhr = new XMLHttpRequest();
var data;
window.onload=loaddata;
function loaddata() {

	  document.getElementById("input").addEventListener("keyup", function (){ search(this.value,document.getElementById("searchOption").value);},false);
	
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            data = JSON.parse(xhr.responseText);
        }
    };
    xhr.open("GET", "ClientData.json", true);
    xhr.send();
}

function search(inputValue,inputType){
    console.log("It works!");
    if(inputType == "id"){
      searchById(inputValue);
    }
    else if(inputType =="lastName"){
      searchByLastName(inputValue);
    }else{
      searchByPhone(inputValue);
    }
}

function searchById(inputValue){
    var tableBody=document.getElementById("tbody");
    var output="";
	  for(var i=0; i<data.length; i++){
		    var obj=data[i];
		    var clientID=obj.id;
		    if(clientID == inputValue){	
					  output+="<tr><td>"
					  output+=obj.id;
					  output+="</td><td>"
					  output+=obj.firstName
					  output+="</td><td>"
					  output+=obj.lastName
					  output+="</td><td>"
					  output+=obj.address
					  output+="</td><td>"
					  output+=obj.postalCode
            output+="</td><td>"
            output+=obj.phone
            output+="</td><td>"
            output+=obj.type
					  output+="</td></tr>";
			  }
	  }
    tableBody.innerHTML=output;
}

function searchByLastName(inputValue){
  var tableBody=document.getElementById("tbody");
  var output="";
  for(var i=0; i<data.length; i++){
      var obj=data[i];
      var clientLast=obj.lastName;
      if(clientLast.startsWith(inputValue)){	
          output+="<tr><td>"
          output+=obj.id;
          output+="</td><td>"
          output+=obj.firstName
          output+="</td><td>"
          output+=obj.lastName
          output+="</td><td>"
          output+=obj.address
          output+="</td><td>"
          output+=obj.postalCode
          output+="</td><td>"
          output+=obj.phone
          output+="</td><td>"
          output+=obj.type
          output+="</td></tr>";
      }
  }
  tableBody.innerHTML=output;
}

function searchByPhone(inputValue){
  var tableBody=document.getElementById("tbody");
  var output="";
  for(var i=0; i<data.length; i++){
      var obj=data[i];
      var clientPhone=obj.phone;
      if(clientPhone.startsWith(inputValue)){	
          output+="<tr><td>"
          output+=obj.id;
          output+="</td><td>"
          output+=obj.firstName
          output+="</td><td>"
          output+=obj.lastName
          output+="</td><td>"
          output+=obj.address
          output+="</td><td>"
          output+=obj.postalCode
          output+="</td><td>"
          output+=obj.phone
          output+="</td><td>"
          output+=obj.type
          output+="</td></tr>";
      }
  }
  tableBody.innerHTML=output;
}