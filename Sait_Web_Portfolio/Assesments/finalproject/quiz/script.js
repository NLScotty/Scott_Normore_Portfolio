window.onload=loaddata;
var data;
var answers;
function loaddata() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4 && xhr.status == 200) {
        makeQuiz(xhr);
    }
    };
    xhr.open("GET", "FinalQuiz.xml", true);
    xhr.send();
}
function makeQuiz(xml){
    var xmlDoc;
    xmlDoc = xml.responseXML;
    data = xmlDoc.getElementsByTagName("finalquiz");
    finalquiz = data[0];
    var txt = "";
    for (var i = 0; i < finalquiz.childNodes.length; i++){
        if(finalquiz.childNodes[i].nodeName=="#text"){
            //do nothing
        }
        else if(finalquiz.childNodes[i].nodeName=="question"){
            question=finalquiz.childNodes[i];
            var qnum;
            for (var j = 0; j < finalquiz.childNodes.length; j++){
                if(question.childNodes[j].nodeName=="#text"){
                    //do nothing
                }
                else{
                    if(question.childNodes[j].nodeName=="qnumber"){
                        txt += "Question "+question.childNodes[j].childNodes[0].nodeValue +"<br><br>";
                        qnum=question.childNodes[j].childNodes[0].nodeValue;
                    }
                    else if(question.childNodes[j].nodeName=="qtitle"){
                        txt += question.childNodes[j].childNodes[0].nodeValue +"<br><br>";
                    }
                    else{
                        txt +="<input type='radio' name='"+qnum+"' value='"+question.childNodes[j].nodeName+"'>"
                        txt += question.childNodes[j].nodeName +") "+question.childNodes[j].childNodes[0].nodeValue +"<br>";
                    }
                }
            }
            txt+="<br>";
        }
        else if(finalquiz.childNodes[i].nodeName=="rightanswers"){
            answers= finalquiz.childNodes[i].childNodes[0].nodeValue.split(",");
        }
    }
    document.getElementById("questions").innerHTML =txt;
}
function checkAnswers(){
    var count=0;
    var incomplete = false;
    try{
        for(var i=0;i<answers.length;i++){
            if(document.querySelector("input[name='"+(i+1)+"']:checked").value == answers[i]){
                count++;
            }
        }
        var txt = "<h1> You Scored... "+count+"/"+answers.length+"</h1>";
        if(count==answers.length){
            txt+="<h1> A perfect Score! </h1>";
        }
        document.getElementById("result").innerHTML =txt;
        document.getElementById("form").style.display='none';
    }catch(err){
        alert("Quiz Incomplete! Please fill out all questions!");
    }
}