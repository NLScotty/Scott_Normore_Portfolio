var count=0;
const customerData=[];
var selected=0;
var editMode=false;

function addListeners(){
    //document.getElementById("date").addEventListener("mouseover", function() {showHelp("dateHelp")});
    //document.getElementById("date").addEventListener("mouseout", function() {hideHelp("dateHelp")});

    document.getElementById("fName").addEventListener("mouseover", function() {showHelp("fNameHelp")});
    document.getElementById("fName").addEventListener("mouseout", function() {hideHelp("fNameHelp")});

    document.getElementById("lName").addEventListener("mouseover", function() {showHelp("lNameHelp")});
    document.getElementById("lName").addEventListener("mouseout", function() {hideHelp("lNameHelp")});

    document.getElementById("address").addEventListener("mouseover", function() {showHelp("addressHelp")});
    document.getElementById("address").addEventListener("mouseout", function() {hideHelp("addressHelp")});

    document.getElementById("pCode").addEventListener("mouseover", function() {showHelp("pCodeHelp")});
    document.getElementById("pCode").addEventListener("mouseout", function() {hideHelp("pCodeHelp")});

    document.getElementById("phone").addEventListener("mouseover", function() {showHelp("phoneHelp")});
    document.getElementById("phone").addEventListener("mouseout", function() {hideHelp("phoneHelp")});

    document.getElementById("email").addEventListener("mouseover", function() {showHelp("emailHelp")});
    document.getElementById("email").addEventListener("mouseout", function() {hideHelp("emailHelp")});

    document.getElementById("age").addEventListener("mouseover", function() {showHelp("ageHelp")});
    document.getElementById("age").addEventListener("mouseout", function() {hideHelp("ageHelp")});

    document.getElementById("income").addEventListener("mouseover", function() {showHelp("incomeHelp")});
    document.getElementById("income").addEventListener("mouseout", function() {hideHelp("incomeHelp")});

    document.getElementById(id="clientLabel").addEventListener("click", function() {toggleHelp("clientHelp")});
    document.getElementById(id="genderLabel").addEventListener("click", function() {toggleHelp("genderHelp")});
    document.getElementById(id="ownerLabel").addEventListener("click", function() {toggleHelp("ownerHelp")});
    document.getElementById(id="itemLabel").addEventListener("click", function() {toggleHelp("itemHelp")});

    document.getElementById(id="vehicleLabel").addEventListener("click", function() {toggleHelp("vehicleHelp")});
    document.getElementById(id="rrspLabel").addEventListener("click", function() {toggleHelp("rrspHelp")});
    document.getElementById(id="tfsaLabel").addEventListener("click", function() {toggleHelp("tfsaHelp")});
    document.getElementById(id="rrifLabel").addEventListener("click", function() {toggleHelp("rrifHelp")});


}

function showHelp(param){
    document.getElementById(param).style.visibility="visible";
}
function hideHelp(param){
    document.getElementById(param).style.visibility="hidden";
}
function toggleHelp(param){
    if(document.getElementById(param).style.visibility=="hidden"){
        document.getElementById(param).style.visibility="visible";
    }else{
        document.getElementById(param).style.visibility="hidden";
    }
}

function onFormSubmit(){
    if(formCheck()){
        createCustomer();
    }
    document.getElementById("customerForm").reset()
}
 function onFormEdit(){
     if(formCheck()){
         updateEntry();
     }
    document.getElementById("customerForm").reset()
 }

//used to check the form
function formCheck(){
    if(document.getElementById("date").value.length==0){
        alert("Enter A Date!!");
        return false;
    }
    else if(document.getElementById("fName").value.length==0){
        alert("Enter your First Name!!");
        return false;
    }
    else if(document.getElementById("lName").value.length==0){
        alert("Enter your Last Name!!");
        return false;
    }
    else if(document.getElementById("address").value.length==0){
        alert("Enter your address!!");
        return false;
    }
    else if(document.getElementById("pCode").value.length==0){
        alert("Enter your Postal Code!!");
        return false;
    }
    else if(document.getElementById("phone").value.length==0){
        alert("Enter your Phone Number!!");
        return false;
    }
    else if(document.getElementById("age").value.length==0 || document.getElementById("age").value.length==0 < 18 ){
        alert("Age must be > than 18!!");
        return false;
    }
    else{
        return true;
    }
}

function createCustomer(){
    //customer object
    const customer={
        date:document.getElementById("date").value,
        fName:document.getElementById("fName").value,
        lName:document.getElementById("lName").value,
        address:document.getElementById("address").value,
        pCode:document.getElementById("pCode").value,
        phone:document.getElementById("phone").value,
        email:document.getElementById("email").value,
        clientInfo:[],
        clientAssets:[]
    };
    //fetching data from checkboxes
    if(document.getElementById("yes").checked){
        customer.clientInfo.push("yes");
    }else{
        customer.clientInfo.push("no");
    }
    if(document.getElementById("male").checked){
        customer.clientInfo.push("male");
    }else{
        customer.clientInfo.push("female");
    }

    customer.clientInfo.push(document.getElementById("age").value);

    if(document.getElementById("owner").checked){
        customer.clientInfo.push("owner");
    }else{
        customer.clientInfo.push("renter");
    }

    customer.clientInfo.push(document.getElementById("income").value);

    if(document.getElementById("vehicle").checked){
        customer.clientAssets.push("vehicle");
    }

    if(document.getElementById("rrsp").checked){
        customer.clientAssets.push("rrsp");
    }

    if(document.getElementById("tfsa").checked){
        customer.clientAssets.push("tfsa");
    }

    if(document.getElementById("rrif").checked){
        customer.clientAssets.push("rrif");
    }

    items="";
    if(customer.clientAssets.length==0){
        items="None"
    }


    var constructedString=constructString(customer);

    var localid=count;
    var container = document.createElement("div");
    

    var para = document.createElement("p");
    para.id=localid;
    var text = document.createTextNode(constructedString);
    para.appendChild(text);

    var eButton = document.createElement("button"); 
    var buttonLabel = document.createTextNode("Edit");
    eButton.appendChild(buttonLabel);
    eButton.onclick=function(){editThis(localid)};
    

    container.appendChild(para);
    container.appendChild(eButton);


    document.getElementById("ListArea").appendChild(container);
    
    customerData.push(customer);

    count++;
}

function constructString(customer){
    return "Date: "+customer.date+" First Name: "+customer.fName+" Last Name: "+customer.lName+" Address: "+customer.address+" Postal Code: "+customer.pCode+" Phone Number: "+customer.phone+  " Email: "+customer.email +
        " Previous Client: "+ customer.clientInfo[0]+" Gender: "+customer.clientInfo[1]+" Age: "+customer.clientInfo[2]+" Landtype: "+customer.clientInfo[3]+" Income: "+ customer.clientInfo[4] +" Client Assets: "+customer.clientAssets.toString(); 
}

function editThis(param){
    document.getElementById("submit").disabled = true;
    document.getElementById("edit").disabled = false;
    editMode=true;
    selected=param;
    fillForm();
}

function fillForm(){
    localCustomer = customerData[selected];
    document.getElementById('date').value=localCustomer.date;
    document.getElementById('fName').value=localCustomer.fName;
    document.getElementById('lName').value=localCustomer.lName;
    document.getElementById('address').value=localCustomer.address;
    document.getElementById('pCode').value=localCustomer.pCode;
    document.getElementById('phone').value=localCustomer.phone;
    document.getElementById('email').value=localCustomer.email;
    document.getElementById('age').value=localCustomer.clientInfo[2];
    document.getElementById('income').value=localCustomer.clientInfo[4];

    if(localCustomer.clientInfo[1]="yes"){
        document.getElementById("yes").checked=true;
    }else{
        document.getElementById("no").checked=true;
    }

    if(localCustomer.clientInfo[1]="male"){
        document.getElementById("male").checked=true;
    }else{
        document.getElementById("female").checked=true;
    }

    if(localCustomer.clientInfo[1]="owner"){
        document.getElementById("owner").checked=true;
    }else{
        document.getElementById("renter").checked=true;
    }

    document.getElementById("vehicle").checked=false;
    document.getElementById("rrsp").checked=false;
    document.getElementById("tfsa").checked=false;
    document.getElementById("rrif").checked=false;

    if(localCustomer.clientAssets.length>0){
        for(let i = 0; i < localCustomer.clientAssets.length; i++){
            if(localCustomer.clientAssets[i] == "vehicle"){
                document.getElementById("vehicle").checked=true;
            }
            if(localCustomer.clientAssets[i] == "rrsp"){
                document.getElementById("rrsp").checked=true;
            }
            if(localCustomer.clientAssets[i] == "tfsa"){
                document.getElementById("tfsa").checked=true;
            }
            if(localCustomer.clientAssets[i] == "rrif"){
                document.getElementById("rrif").checked=true;
            }
        }
    }

}

function updateEntry(){

        //customer object
    const customer={
        date:document.getElementById("date").value,
        fName:document.getElementById("fName").value,
        lName:document.getElementById("lName").value,
        address:document.getElementById("address").value,
        pCode:document.getElementById("pCode").value,
        phone:document.getElementById("phone").value,
        email:document.getElementById("email").value,
        clientInfo:[],
        clientAssets:[]
    };
    //fetching data from checkboxes
    if(document.getElementById("yes").checked){
        customer.clientInfo.push("yes");
    }else{
        customer.clientInfo.push("no");
    }

    if(document.getElementById("male").checked){
        customer.clientInfo.push("male");
    }else{
        customer.clientInfo.push("female");
    }

    customer.clientInfo.push(document.getElementById("age").value);

    if(document.getElementById("owner").checked){
        customer.clientInfo.push("owner");
    }else{
        customer.clientInfo.push("renter");
    }

    customer.clientInfo.push(document.getElementById("income").value);

    if(document.getElementById("vehicle").checked){
        customer.clientAssets.push("vehicle");
    }

    if(document.getElementById("rrsp").checked){
        customer.clientAssets.push("rrsp");
    }

    if(document.getElementById("tfsa").checked){
        customer.clientAssets.push("tfsa");
    }

    if(document.getElementById("rrif").checked){
        customer.clientAssets.push("rrif");
    }
    items="";
    if(customer.clientAssets.length==0){
        items="None"
    }


    var constructedString=constructString(customer);

    document.getElementById(selected).innerHTML=constructedString;

    customerData[selected]=customer;

    document.getElementById("submit").disabled = false;
    document.getElementById("edit").disabled = true;
    editMode=false;
}