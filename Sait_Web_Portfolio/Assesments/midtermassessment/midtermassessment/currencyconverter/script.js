const currList=['euro','uDollar','cDollar','bCoin','eCoin'];
const ratios=[0.84,1,1.23,0.000029,0.00048];

//ratios are in regard to the us dollar

function updateCur1Update(){
    cur1Amnt= document.getElementById("cur1_amount").value;

    cur1Type = document.getElementById("cur1_type").value;
    cur2Type = document.getElementById("cur2_type").value;

    var index1 = currList.indexOf(cur1Type);
    var index2 = currList.indexOf(cur2Type);
    var amnt = ((ratios[index2] / ratios[index1]) * cur1Amnt);

    if(amnt>0.01 && amnt<999999){
        amnt=amnt.toFixed(2);
    }
    else{
        amnt=amnt.toPrecision(2);
    }
    document.getElementById("cur2_amount").value=amnt;

    updateLabels();
}

function updateCur2Update(){
    cur2Amnt= document.getElementById("cur2_amount").value;

    cur1Type = document.getElementById("cur1_type").value;
    cur2Type = document.getElementById("cur2_type").value;

    var index1 = currList.indexOf(cur2Type);
    var index2 = currList.indexOf(cur1Type);
    var amnt = ((ratios[index2] / ratios[index1]) * cur2Amnt);

    if(amnt>0.01 && amnt<999999){
        amnt=amnt.toFixed(2);
    }
    else{
        amnt=amnt.toPrecision(2);
    }

    document.getElementById("cur1_amount").value=amnt;

    updateLabels();
}

function updateLabels(){
    if(document.getElementById('cur1_type').value=='euro'){
        document.getElementById('cur1_label').innerHTML='Euros';
    }
    else if(document.getElementById('cur1_type').value=='uDollar'){
        document.getElementById('cur1_label').innerHTML='US Dollars';
    }
    else if(document.getElementById('cur1_type').value=='cDollar'){
        document.getElementById('cur1_label').innerHTML='Canadian Dollars';
    }
    else if(document.getElementById('cur1_type').value=='bCoin'){
        document.getElementById('cur1_label').innerHTML='Bit Coin';
    }
    else if(document.getElementById('cur1_type').value=='eCoin'){
        document.getElementById('cur1_label').innerHTML='Ethereum';
    }

    if(document.getElementById('cur2_type').value=='euro'){
        document.getElementById('cur2_label').innerHTML='Euros';
    }
    else if(document.getElementById('cur2_type').value=='uDollar'){
        document.getElementById('cur2_label').innerHTML='US Dollars';
    }
    else if(document.getElementById('cur2_type').value=='cDollar'){
        document.getElementById('cur2_label').innerHTML='Canadian Dollars';
    }
    else if(document.getElementById('cur2_type').value=='bCoin'){
        document.getElementById('cur2_label').innerHTML='Bit Coin';
    }
    else if(document.getElementById('cur2_type').value=='eCoin'){
        document.getElementById('cur2_label').innerHTML='Ethereum';
    }


}

