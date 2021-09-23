var xhr = new XMLHttpRequest();
var data;
window.onload=loaddata;
function loaddata() {

	  document.getElementById("input").addEventListener("keyup", function (){ search(this.value,document.getElementById("searchOption").value);},false);
	
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4 && xhr.status == 200) {
        data = JSON.parse(xhr.responseText);
    }
    };
    xhr.open("GET", "https://data.calgary.ca/resource/fd9t-tdn2.json", true);
    xhr.send();
  
  
}

function search(inputValue,inputType){
    if(inputType == "name"){
      searchByName(inputValue);
    }
    else if(inputType =="category"){
      searchByCategory(inputValue);
    }
}

function searchByName(inputValue){
  var tableBody=document.getElementById("tbody");
  var output="";
  for(var i=0; i<data.length; i++){
      var obj=data[i];
      var name=obj.name;
      if(name.includes(inputValue)){	
          output+="<tr><td>"
          output+=obj.name;
          output+="</td><td>"
          output+=obj.type;
          output+="</td><td>";
          output+=obj.address_ab;
          output+="</td></tr>";
      }
  }
  tableBody.innerHTML=output;
}

function searchByCategory(inputValue){
  var tableBody=document.getElementById("tbody");
  var output="";
  for(var i=0; i<data.length; i++){
    var obj=data[i];
    var type=obj.type;
    if(type.contains(inputValue)){	
        output+="<tr><td>"
        output+=obj.name;
        output+="</td><td>"
        output+=obj.type;
        output+="</td><td>";
        output+=obj.address_ab;
        output+="</td></tr>";
    }
  }
  tableBody.innerHTML=output;
}