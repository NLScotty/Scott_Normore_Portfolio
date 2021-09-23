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
    else if(inputType =="id"){
      searchByID(inputValue);
    }
    else if(inputType =="type"){
      searchByType(inputValue);
    }
    else if(inputType =="psecond"){
      searchByPSecondary(inputValue);
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
					  output+=obj.structure_id;
					  output+="</td><td>"
					  output+=obj.name;
					  output+="</td><td>"
            output+=obj.type;
            output+="</td><td>"
					  output+=obj.postsecond;
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
      var ID=obj.structure_id;
      if(ID.startsWith(inputValue)){	
        output+="<tr><td>"
        output+=obj.structure_id;
        output+="</td><td>"
        output+=obj.name;
        output+="</td><td>"
        output+=obj.type;
        output+="</td><td>"
        output+=obj.postsecond;
        output+="</td><td>"
         var location = "https://www.google.com/maps/place/"+obj.longitude +","+obj.latitude;
         output+="<a href ='"+location+"'>Map Location</a>"
         output+="</td></tr>";
      }
  }
  tableBody.innerHTML=output;
}

function searchByType(inputValue){
  var tableBody=document.getElementById("tbody");
  var output="";
  for(var i=0; i<data.length; i++){
      var obj=data[i];
      var type=obj.type;
      if(type.startsWith(inputValue)){	
        output+="<tr><td>"
        output+=obj.structure_id;
        output+="</td><td>"
        output+=obj.name;
        output+="</td><td>"
        output+=obj.type;
        output+="</td><td>"
        output+=obj.postsecond;
        output+="</td><td>"
         var location = "https://www.google.com/maps/place/"+obj.longitude +","+obj.latitude;
         output+="<a href ='"+location+"'>Map Location</a>"
         output+="</td></tr>";
      }
  }
  tableBody.innerHTML=output;
}

function searchByPSecondary(inputValue){
  var tableBody=document.getElementById("tbody");
  var output="";
  for(var i=0; i<data.length; i++){
    var obj=data[i];
    var pSecond=obj.postsecond;
    if(pSecond.startsWith(inputValue)){	
        output+="<tr><td>"
        output+=obj.structure_id;
        output+="</td><td>"
        output+=obj.name;
        output+="</td><td>"
        output+=obj.type;
        output+="</td><td>"
        output+=obj.postsecond;
        output+="</td><td>"
         var location = "https://www.google.com/maps/place/"+obj.longitude +","+obj.latitude;
         output+="<a href ='"+location+"'>Map Location</a>"
         output+="</td></tr>";
      }
  }
  tableBody.innerHTML=output;
}