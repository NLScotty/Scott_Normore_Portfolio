
window.addEventListener("load", registerListeners, false);
var asynchrequest;

function registerListeners() {
	
	var img;
	img=document.getElementById("caption1");
	img.addEventListener("mouseover", function () { getContent("photo1.html");}, false);
	img.addEventListener("mouseout", clearContent, false);
	img=document.getElementById("caption2");
	img.addEventListener("mouseover", function () { getContent("photo2.html");}, false);
	img.addEventListener("mouseout", clearContent, false);
	img=document.getElementById("caption3");
	img.addEventListener("mouseover", function () { getContent("photo3.html");}, false);
	img.addEventListener("mouseout", clearContent, false);
	img=document.getElementById("caption4");
	img.addEventListener("mouseover", function () { getContent("photo4.html");}, false);
	img.addEventListener("mouseout", clearContent, false);
	img=document.getElementById("caption5");
	img.addEventListener("mouseover", function () { getContent("photo5.html");}, false);
	img.addEventListener("mouseout", clearContent, false);
	img=document.getElementById("caption6");
	img.addEventListener("mouseover", function () { getContent("photo6.html");}, false);
	img.addEventListener("mouseout", clearContent, false);
}

function getContent(photopage) {

		asynchrequest= new XMLHttpRequest();
		asynchrequest.onreadystatechange = function() {
    if (asynchrequest.readyState == 4 && asynchrequest.status == 200) {
      document.getElementById("Photo_Content").innerHTML = asynchrequest.responseText;
    }
  };
  asynchrequest.open("GET", photopage, true);
  asynchrequest.send();
}


function clearContent() {
	
	document.getElementById("Photo_Content").innerHTML="";

}