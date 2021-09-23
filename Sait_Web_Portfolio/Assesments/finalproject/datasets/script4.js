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
    xhr.open("GET", "https://data.calgary.ca/resource/35ra-9556.json", true);
    xhr.send();
  
  
}

function search(inputValue,inputType){
    if(inputType == "info"){
      searchByInfo(inputValue);
    }
    else if(inputType =="id"){
      searchByID(inputValue);
    }
    else if(inputType =="description"){
      searchByDesc(inputValue);
    }
    else if(inputType =="date"){
      searchByDate(inputValue);
    }
}

function searchByInfo(inputValue){
    var tableBody=document.getElementById("tbody");
    var output="";
	  for(var i=0; i<data.length; i++){
		    var obj=data[i];
		    var info=obj.incident_info;
		    if(info.includes(inputValue)){	
          output+="<tr><td>"
          output+=obj.id;
          output+="</td><td>"
          output+=obj.incident_info;
          output+="</td><td>"
          output+=obj.start_dt;
          output+="</td><td>"
          output+=obj.description;
          output+="</td><td>"
          var location = "https://www.google.com/maps/place/"+obj.longitude +","+obj.latitude;
          output+="<a href ='"+location+"'>Map Location</a>"
          output+="</td></tr>";
			  }
	  }
    tableBody.innerHTML=output;
}

function searchByID(inputValue){
  var tableBody=document.getElementById("tbody");
  var output="";
  for(var i=0; i<data.length; i++){
      var obj=data[i];
      var ID=obj.id;
      if(ID.startsWith(inputValue)){	
         output+="<tr><td>"
         output+=obj.id;
         output+="</td><td>"
         output+=obj.incident_info;
         output+="</td><td>"
         output+=obj.start_dt;
         output+="</td><td>"
         output+=obj.description;
         output+="</td><td>"
         var location = "https://www.google.com/maps/place/"+obj.longitude +","+obj.latitude;
         output+="<a href ='"+location+"'>Map Location</a>"
         output+="</td></tr>";
      }
  }
  tableBody.innerHTML=output;
}

function searchByDesc(inputValue){
  var tableBody=document.getElementById("tbody");
  var output="";
  for(var i=0; i<data.length; i++){
      var obj=data[i];
      var description=obj.description;
      if(description.startsWith(inputValue)){	
        output+="<tr><td>"
        output+=obj.id;
        output+="</td><td>"
        output+=obj.incident_info;
        output+="</td><td>"
        output+=obj.start_dt;
        output+="</td><td>"
        output+=obj.description;
        output+="</td><td>"
        var location = "https://www.google.com/maps/place/"+obj.longitude +","+obj.latitude;
        output+="<a href ='"+location+"'>Map Location</a>"
        output+="</td></tr>";
      }
  }
  tableBody.innerHTML=output;
}

function searchByDate(inputValue){
  var tableBody=document.getElementById("tbody");
  var output="";
  for(var i=0; i<data.length; i++){
      var obj=data[i];
      var date=obj.start_dt;
      if(date.startsWith(inputValue)){	
        output+="<tr><td>"
        output+=obj.id;
        output+="</td><td>"
        output+=obj.incident_info;
        output+="</td><td>"
        output+=obj.start_dt;
        output+="</td><td>"
        output+=obj.description;
        output+="</td><td>"
        var location = "https://www.google.com/maps/place/"+obj.longitude +","+obj.latitude;
        output+="<a href ='"+location+"'>Map Location</a>"
        output+="</td></tr>";
      }
  }
  tableBody.innerHTML=output;
}