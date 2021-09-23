const imgurl = ['images/slideshow/stjohns_downtown.jpg','images/slideshow/stjohns_harbor.jpg','images/slideshow/signal_hill.jpg','images/slideshow/signal_hill_foggy.jpg','images/slideshow/quidi_vidi_lake_seal.jpg','images/slideshow/newfoundland_housing.jpg','images/slideshow/newfoundland_housing2.jpg','images/slideshow/moose_off_deerlake.jpg','images/slideshow/fish_and_chips.jpg','images/slideshow/craft_brewery.jpg','images/slideshow/bonivista_abandoned_building.jpg'];
const caption = ["Bird-eye view of St.John's","Harbor in the Downtown area of St.Johns","Newfoundland's Signal Hill, St.John's","Signal Hill on a foggy day","A picture of a seal spotted at Quidi Vidi Lake","Traditional Style Newfoundland Housing","Note the shared walls and vibrant colors",'A moose sighted close to my home area; Deerlake','Newfoundland is famous for their fish and chips','The Craft Breweries picked up alot of traction in Newfoundland','Abandoned building; Bonivista.'];
var index=0;
var test=0;

function onLoad(){
    auto = setInterval(function() {onRightButtonClick()}, 3000);
}

function onLeftButtonClick(){
    if(index>0){
        index--;
    }
    else{
        index=10;
    }
    document.getElementById('slideshow').style.opacity=0;
    document.getElementById('slideshow').src=imgurl[index];
    document.getElementById('caption').innerHTML=caption[index];
    setTimeout(function(){document.getElementById('slideshow').style.opacity=1}, 400);
    clearInterval(auto);
    auto = setInterval(function() {onRightButtonClick()}, 3000);
    
}

function onRightButtonClick(){
    if(index<imgurl.length-1){
        index++;
    }else{
        index=0;
    }
    document.getElementById('slideshow').style.opacity=0;
    document.getElementById('slideshow').src=imgurl[index];
    document.getElementById('caption').innerHTML=caption[index];
    setTimeout(function(){document.getElementById('slideshow').style.opacity=1}, 400);
    clearInterval(auto);
    auto = setInterval(function() {onRightButtonClick()}, 3000);

}