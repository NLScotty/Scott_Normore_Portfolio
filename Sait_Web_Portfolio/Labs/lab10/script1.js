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
    xhr.open("GET", "https://data.calgary.ca/resource/k7p9-kppz.json", true);
    xhr.send();
  
  
}

function search(inputValue,inputType){
    console.log("It works!");
    if(inputType == "number"){
      searchByCameraNumber(inputValue);
    }
    else if(inputType =="quadrant"){
      searchByQuadrant(inputValue);
    }
}

function searchByCameraNumber(inputValue){
    var tableBody=document.getElementById("tbody");
    var output="";
	  for(var i=0; i<data.length; i++){
		    var obj=data[i];
		    var camera_url=obj.camera_url;
        var description=camera_url.description;
		    if(description.split(" ")[1]==(inputValue)){	
					  output+="<tr><td>"
					  output+=camera=obj.camera_url.description;
					  output+="</td><td>"
					  output+=obj.quadrant;
					  output+="</td><td>"
					  output+=obj.camera_location
					  output+="</td></tr>";
			  }
	  }
    tableBody.innerHTML=output;
}

function searchByQuadrant(inputValue){
  var tableBody=document.getElementById("tbody");
  var output="";
  for(var i=0; i<data.length; i++){
      var obj=data[i];
      var quadrant=obj.quadrant;
      if(quadrant.includes(inputValue)){	
          output+="<tr><td>"
          output+=camera=obj.camera_url.description;
          output+="</td><td>"
          output+=obj.quadrant;
          output+="</td><td>"
          output+=obj.camera_location
          output+="</td></tr>";
      }
  }
  tableBody.innerHTML=output;
}