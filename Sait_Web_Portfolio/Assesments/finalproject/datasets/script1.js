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
    xhr.open("GET", "https://data.calgary.ca/resource/2kp2-hsy7.json", true);
    xhr.send();
  
  
}

function search(inputValue,inputType){
    if(inputType == "title"){
      searchByTitle(inputValue);
    }
    else if(inputType =="id"){
      searchByID(inputValue);
    }
    else if(inputType =="address"){
      searchByAddress(inputValue);
    }
    else if(inputType =="artist"){
      searchByArtist(inputValue);
    }
}

function searchByTitle(inputValue){
    var tableBody=document.getElementById("tbody");
    var output="";
	  for(var i=0; i<data.length; i++){
		    var obj=data[i];
		    var title=obj.title;
		    if(title.includes(inputValue)){	
					  output+="<tr><td>"
					  output+=obj.art_id;
					  output+="</td><td>"
					  output+=obj.title;
					  output+="</td><td>"
            output+=obj.artist;
            output+="</td><td>"
					  output+=obj.address;
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
      var ID=obj.art_id;
      if(ID.startsWith(inputValue)){	
         output+="<tr><td>"
         output+=obj.art_id;
         output+="</td><td>"
         output+=obj.title;
         output+="</td><td>"
         output+=obj.artist;
         output+="</td><td>"
         output+=obj.address;
         output+="</td><td>"
         var location = "https://www.google.com/maps/place/"+obj.longitude +","+obj.latitude;
         output+="<a href ='"+location+"'>Map Location</a>"
         output+="</td></tr>";
      }
  }
  tableBody.innerHTML=output;
}

function searchByArtist(inputValue){
  var tableBody=document.getElementById("tbody");
  var output="";
  for(var i=0; i<data.length; i++){
      var obj=data[i];
      var artist=obj.artist;
      if(artist.startsWith(inputValue)){	
         output+="<tr><td>"
         output+=obj.art_id;
         output+="</td><td>"
         output+=obj.title;
         output+="</td><td>"
         output+=obj.artist;
         output+="</td><td>"
         output+=obj.address;
         output+="</td><td>"
         var location = "https://www.google.com/maps/place/"+obj.longitude +","+obj.latitude;
         output+="<a href ='"+location+"'>Map Location</a>"
         output+="</td></tr>";
      }
  }
  tableBody.innerHTML=output;
}

function searchByAddress(inputValue){
  var tableBody=document.getElementById("tbody");
  var output="";
  for(var i=0; i<data.length; i++){
      var obj=data[i];
      var address=obj.address;
      if(address.startsWith(inputValue)){	
         output+="<tr><td>"
         output+=obj.art_id;
         output+="</td><td>"
         output+=obj.title;
         output+="</td><td>"
         output+=obj.artist;
         output+="</td><td>"
         output+=obj.address;
         output+="</td><td>"
         var location = "https://www.google.com/maps/place/"+obj.longitude +","+obj.latitude;
         output+="<a href ='"+location+"'>Map Location</a>"
         output+="</td></tr>";
      }
  }
  tableBody.innerHTML=output;
}