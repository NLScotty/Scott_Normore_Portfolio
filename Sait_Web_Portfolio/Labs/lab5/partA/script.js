var currentSelection;

function measurementChange(){
    currentSelection=document.getElementById("measurement").value
    if(currentSelection == "area"){
        document.getElementById("metricUnit").innerHTML="meter squared (km^2)";
        document.getElementById("imperialUnit").innerHTML="perch";
    }
    else if(currentSelection == "length"){
        document.getElementById("metricUnit").innerHTML="meter (m)";
        document.getElementById("imperialUnit").innerHTML="yard (yd)";       
    }
    else if(currentSelection == "mass"){
        document.getElementById("metricUnit").innerHTML="kilogram (kg)";
        document.getElementById("imperialUnit").innerHTML="pound (lb)";  
    }
    else{
        document.getElementById("metricUnit").innerHTML="litre (li)";
        document.getElementById("imperialUnit").innerHTML="gallon (gal)"; 
    }
    document.getElementById("metric").value=0;
    document.getElementById("imperial").value=0;

}
function updateOnMetric(){
    if(currentSelection == "area"){
        document.getElementById("imperial").value= (document.getElementById("metric").value * 0.0395369).toFixed(2);
    }
    else if(currentSelection == "length"){
        document.getElementById("imperial").value= (document.getElementById("metric").value * 1.09361).toFixed(2);
    }
    else if(currentSelection == "mass"){
        document.getElementById("imperial").value= (document.getElementById("metric").value * 2.20462).toFixed(2);
    }
    else{
        document.getElementById("imperial").value= (document.getElementById("metric").value * 0.264172).toFixed(2);
    }    
}
function updateOnImperial(){
    if(currentSelection == "area"){
        document.getElementById("metric").value= (document.getElementById("imperial").value * 25.2929).toFixed(2);
    }
    else if(currentSelection == "length"){
        document.getElementById("metric").value= (document.getElementById("imperial").value * 0.9144).toFixed(2);
    }
    else if(currentSelection == "mass"){
        document.getElementById("metric").value= (document.getElementById("imperial").value * 0.453592).toFixed(2);
    }
    else{
        document.getElementById("metric").value= (document.getElementById("imperial").value * 3.78541).toFixed(2);
    }    
}