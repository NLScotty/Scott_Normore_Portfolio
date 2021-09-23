// stuff related to the player Character goes into this file.

//The player character entity. It contatins the logic for the player character.
function playerChar(x,y){
    // The player character's stored stuff
// the players info
    var id="player";
    var layer=2;
    var type="motion";
    var tags=["player"];
    Entity.call(this,x,y,id,layer,type,tags);
// stuff that pertains to the players state
    this.facing="right";
    this.width = 61;
    this.height = 61;
    this.yVel=0;
    this.hp=10;
    this.iframes=0;
    this.cooldown=0;
// players selected powers
    this.selected1="magic missle";
    this.selected2="staff hit";
// players inventory
    this.inventory=["med kit", 2 ,"bomb", 2];
    this.powers=["staff hit", "magic missle"];
// used for animating
    this.tickCount = 0;
    this.ticksPerFrame = 6;

    this.runFrames = 8;
    this.runFrameIndex = 0;
    this.idleFrames = 4;
    this.idleFrameIndex = 0;
    this.jumpFrames = 1;
    this.jumpFrameIndex = 0;

    this.falling = false;
// players update function. It updates the players above state.
    this.update = function(){
// checks if falling. Used to draw the parachute
        if(this.yVel > 20){
            this.falling=true;
        }
        else{
            this.falling = false;
        }
// used to check if the player is facing left or right. Used to determine if it should be drawn
//  left or right, aswell as fire direction if another direction is not held
        if(pressingLeft) {this.facing="left"}
        if(pressingRight) {this.facing="right"}
// moves the player if not pressing halt
        if(!pressingHalt){
            if(pressingDown) this.y = this.y+12;
            if(pressingUp) this.y = this.y-20;
            if(pressingLeft) this.x = this.x-12;
            if(pressingRight) this.x = this.x+12;
        }
// Updates the players gravity.
        this.yVel=this.yVel+1
        if(this.yVel>30){
            (this.yVel=30);
        }
        this.y=this.y+this.yVel;
// If the player ions invicible, decreases the Iframes by one
        if(this.iframes>0){
            this.iframes=this.iframes-1;
        }
// If the cooldown is 0 (player is ready to use an ability), checks for the following
        if(this.cooldown==0){
        // We priortize the power on button one over button two.
            if(pressingPower1==true){
            //We pass the direction, aswell as the current power, to the player projectile function.
                if(pressingLeft){
                    this.addToList(new playerProjectile(this.x,this.y+this.height/2,"left",this.selected1));
                    this.cooldown=this.cooldown+getProjectileCooldown(this.selected1);
                    playProjectileSound(this.selected1);
                }
                else if(pressingRight){
                    this.addToList(new playerProjectile(this.x+61,this.y+this.height/2,"right",this.selected1));
                    this.cooldown=this.cooldown+getProjectileCooldown(this.selected1);
                    playProjectileSound(this.selected1);
                }
                else if(pressingDown){
                    this.addToList(new playerProjectile(this.x+this.width/2,this.y+61,"down",this.selected1));
                    this.cooldown=this.cooldown+getProjectileCooldown(this.selected1);
                    playProjectileSound(this.selected1);
                }
                else if(pressingUp){
                    this.addToList(new playerProjectile(this.x+this.width/2,this.y,"up",this.selected1));
                    this.cooldown=this.cooldown+getProjectileCooldown(this.selected1);
                    playProjectileSound(this.selected1);
                }
                // If no direction is held, we check the direction facing rto determine where it should fire from
                else{
                    if(this.facing=="right"){
                        this.addToList(new playerProjectile(this.x+61,this.y+this.height/2,"right",this.selected1));
                        this.cooldown=this.cooldown+getProjectileCooldown(this.selected1);  
                        playProjectileSound(this.selected1);
                    }
                    else{
                        this.addToList(new playerProjectile(this.x,this.y+this.height/2,"left",this.selected1));
                        this.cooldown=this.cooldown+getProjectileCooldown(this.selected1);  
                        playProjectileSound(this.selected1);
                    }
                }
            }
            // same as above, but with the other button
            else if(pressingPower2==true){
                if(pressingLeft){
                    this.addToList(new playerProjectile(this.x,this.y+this.height/2,"left",this.selected2));
                    this.cooldown=this.cooldown+getProjectileCooldown(this.selected2);
                    playProjectileSound(this.selected2);
                }
                else if(pressingRight){
                    this.addToList(new playerProjectile(this.x+61,this.y+this.height/2,"right",this.selected2));
                    this.cooldown=this.cooldown+getProjectileCooldown(this.selected2);
                    playProjectileSound(this.selected2);
                }
                else if(pressingDown){
                    this.addToList(new playerProjectile(this.x+this.width/2,this.y+61,"down",this.selected2));
                    this.cooldown=this.cooldown+getProjectileCooldown(this.selected2);
                    playProjectileSound(this.selected2);
                }
                else if(pressingUp){
                    this.addToList(new playerProjectile(this.x+this.width/2,this.y,"up",this.selected2));
                    this.cooldown=this.cooldown+getProjectileCooldown(this.selected2);
                    playProjectileSound(this.selected2);
                }
                else{
                    if(this.facing=="right"){
                        this.addToList(new playerProjectile(this.x+61,this.y+this.height/2,"right",this.selected2));
                        this.cooldown=this.cooldown+getProjectileCooldown(this.selected1); 
                        playProjectileSound(this.selected2); 
                    }
                    else{
                        this.addToList(new playerProjectile(this.x,this.y+this.height/2,"left",this.selected2));
                        this.cooldown=this.cooldown+getProjectileCooldown(this.selected2);  
                        playProjectileSound(this.selected2);
                    }
                }
            }
    //If not ready to use an ability(on cooldwon), lower the cooldown counter
        }else{
            this.cooldown=this.cooldown-1
        }
    }
    //the draw function for the player Uses the indexes in the playerChar variables to draw and 
    //  properly animate the player character. It chooses the image to drawn based on these indexes
    //  and state
    this.draw = function(){
        this.tickCount += 1;
        if(this.tickCount > this.ticksPerFrame){
            this.runFrameIndex++;
            this.idleFrameIndex++;
            this.jumpFrameIndex++;
            this.tickCount = 0;
            if(this.runFrameIndex >= this.runFrames)
                this.runFrameIndex = 0;
            if(this.idleFrameIndex >= this.idleFrames)
                this.idleFrameIndex = 0;
            if(this.jumpFrameIndex >= this.jumpFrames)
                this.jumpFrameIndex = 0;
        }

        if(this.iframes%2==0){
            var img = ImageAtlas[playerAnimKey];

            if(pressingUp){  //JUMPING

                offset = 8*64+64;
                if(this.falling){
                    var paraImg = ImageAtlas[parachuteKey];
                    ctx.drawImage(
                        paraImg, // image
                        600+5,  // target x
                        330-35, // target y
                        45, // target width
                        45 // target height
                    );
                  //Deploy parachute
                }
                
                //Idle animation
                if(this.facing == "right"){
                    ctx.drawImage(
                        img, // image
                        64*this.jumpFrameIndex + offset,//sx 	Source x 	
                        64, //sy 	Source y 	
                        64,//sw 	Source width 	Frame width
                        64,//sh 	Source height 	Frame height
                        600,  // target x
                        330, // target y
                        61, // target width
                        61 // target height
                    );
                }
                else if(this.facing == "left"){
                    ctx.drawImage(
                        img, // image
                        ((this.jumpFrames*2)*64-64) - (64*this.jumpFrameIndex) + offset,//sx 	Source x 	
                        64, //sy 	Source y 	
                        64,//sw 	Source width 	Frame width
                        64,//sh 	Source height 	Frame height
                        600,  // target x
                        330, // target y
                        61, // target width
                        61 // target height
                    );
                }
            }
            else if(pressingLeft){
                //Moving Left
                //Starts of the far right of sprite sheet
                ctx.drawImage(
                    img, // image
                    (img.width-64) - (64*this.runFrameIndex),//sx 	Source x 	
                    0, //sy 	Source y 	
                    64,//sw 	Source width 	Frame width
                    64,//sh 	Source height 	Frame height
                    600,  // target x
                    330, // target y
                    61, // target width
                    61 // target height
                );
            }
            else if(pressingRight){
                //Moving Right 
                ctx.drawImage(
                    img, // image
                    64*this.runFrameIndex,//sx 	Source x 	
                    0, //sy 	Source y 	
                    64,//sw 	Source width 	Frame width
                    64,//sh 	Source height 	Frame height
                    600,  // target x
                    330, // target y
                    61, // target width
                    61 // target height
                );
            }
            else{
                //Idle animation
                if(this.facing == "right"){
                    ctx.drawImage(
                        img, // image
                        64*this.idleFrameIndex,//sx 	Source x 	
                        64, //sy 	Source y 	
                        64,//sw 	Source width 	Frame width
                        64,//sh 	Source height 	Frame height
                        600,  // target x
                        330, // target y
                        61, // target width
                        61 // target height
                    );
                }
                else if(this.facing == "left"){
                    ctx.drawImage(
                        img, // image
                        ((this.idleFrames*2)*64-64) - (64*this.idleFrameIndex),//sx 	Source x 	
                        64, //sy 	Source y 	
                        64,//sw 	Source width 	Frame width
                        64,//sh 	Source height 	Frame height
                        600,  // target x
                        330, // target y
                        61, // target width
                        61 // target height
                    );
                } 
            }
        }
    }

// The functions for player colliosion. When the player collides with an object, will select the 
//  appropiate collision function based on the parameter.
    this.collision = function(entityC){
        if(entityC.tags.includes("block")){
            sideColided=playercheckSide(this , entityC)
            if(sideColided=="bottom"){
                this.y=entityC.y-60;
                this.yVel=0;
            }
            if(sideColided=="top"){
                this.y=entityC.y+60;
            }
            if(sideColided=="left"){
                this.x=entityC.x+60;
            }
            if(sideColided=="right"){
                this.x=entityC.x-60;
            }
        }
        if(entityC.tags.includes("damaging")){
            if(this.iframes==0){
                this.hp=this.hp-1;
                console.log(this.hp);
                this.iframes=80;
                playASound("soundEffects/damageTaken.mp3");
            }
        }
    }
} 
playerChar.prototype=Object.create(Entity.prototype);
playerChar.prototype.constructor = playerChar;

// The "playerProjectile" function is mislabled a bit. It is actually used to handle all the players powers, not just projectiles. 
//  We use this function to have the player be able to use a variety of different powers and abilites.

function playerProjectile(x,y,direction,projectile){
    id=projectile;
    type="motion";
    layer=2;
    this.yVel=0;
    var tags=[];
    Entity.call(this,x,y,id,layer,type,tags);
    this.width = getProjectileWidth(projectile);
    this.height = getProjectileHeight(projectile);
    this.direction=direction;
    this.framesUp=0;
    this.enemyFlash = function(entity){
        entity.flashFrames=40;
    }
    this.directionAdjustment=function(){
        if(this.id=="staff hit"){
            if(this.direction=="up" || this.direction=="down"){
                temp=this.width;
                this.width=this.height;
                this.height=temp;
            }
        }
        else{

        }
    }
    this.directionAdjustment();
    this.update = function(){
        if(this.id=="magic missle"){
            if(this.direction=="down") this.y = this.y+15;
            else if(this.direction=="up") this.y = this.y-15;
            else if(this.direction=="left") this.x = this.x-15;
            else if(this.direction=="right") this.x = this.x+15;
        }
        if(this.id=="fire ball"){
            if(this.direction=="down") this.y = this.y+12;
            else if(this.direction=="up") this.y = this.y-12;
            else if(this.direction=="left") this.x = this.x-12;
            else if(this.direction=="right") this.x = this.x+12;
        }
        if(this.id=="staff hit"){
            if(this.direction=="down"){
                this.y=game.currentLevelData.playerRef.y + game.currentLevelData.playerRef.height+2;
                this.x=game.currentLevelData.playerRef.x + game.currentLevelData.playerRef.width/2;
            }
            else if(this.direction=="up"){
                this.y=game.currentLevelData.playerRef.y-2 - this.height;
                this.x=game.currentLevelData.playerRef.x + game.currentLevelData.playerRef.width/2;
            }
            else if(this.direction=="left") {
                this.y=game.currentLevelData.playerRef.y+ game.currentLevelData.playerRef.height/2;
                this.x=game.currentLevelData.playerRef.x-2 -this.width;
            }
            else if(this.direction=="right"){
                this.y=game.currentLevelData.playerRef.y+ game.currentLevelData.playerRef.height/2;
                this.x=game.currentLevelData.playerRef.x + game.currentLevelData.playerRef.width+2;

            }
            this.framesUp=this.framesUp+1;
            if(this.framesUp==30){
                this.remove();
            }
        }
        if(this.id=="med kit"){
            if(game.currentLevelData.playerRef.inventory[1]>0){
                game.currentLevelData.playerRef.inventory[1]=game.currentLevelData.playerRef.inventory[1]-1
                game.currentLevelData.playerRef.hp=10;
                this.remove();
                playASound("soundEffects/medkitUse.mp3");
            }
            else{
                this.remove();
            }
        }
        if(this.id=="bomb"){
            if(game.currentLevelData.playerRef.inventory[3]>0){
                game.currentLevelData.playerRef.inventory[3]=game.currentLevelData.playerRef.inventory[3]-1;
                console.log(game.currentLevelData.playerRef.inventory[3]);
                this.id="bomb2";
            }
            else{
                this.remove();
            }
        }
        if(this.id=="bomb2"){
            if(this.framesUp==120){
                this.id="explosion";
                this.tags.push("damaging");
                this.height=256;
                this.width=256;
                this.y=this.y-128;
                this.x=this.x-128;
                playASound("soundEffects/explosion.mp3");
            }
            else{
                if(this.yVel<6){
                    this.yVel=this.yVel+1;
                }
                this.y=this.y+this.yVel;
                this.framesUp=this.framesUp+1;
            }
        }
        if(this.id=="explosion"){
            if(this.framesUp==126){
                this.remove();
            }
            this.framesUp=this.framesUp+1;
        }
        if(this.id=="fire ball2"){
            if(this.framesUp==5){
                this.remove()
            }
            else{
                this.framesUp=this.framesUp+1;
            }
        }
    }
    this.draw = function(){
        if(this.id=="magic missle"){
            var img = ImageAtlas[this.id];
            ctx.drawImage(
                img, // image
                600+(this.x-player.x),  // target x
                330+(this.y-player.y), // target y
                16, // target width
                16 // target height
            );
        }
        else if(this.id=="staff hit"){
            var img = ImageAtlas[slashKey];
            var deltaX = 0;
            var deltaY = -20;
            if(this.direction == "left"){
                deltaX = 10;
            }
            else if (this.direction == "right"){
                deltaX = -10;
            }
            ctx.drawImage(
                img, // image
                600+(this.x-player.x) + deltaX,  // target x
                330+(this.y-player.y) + deltaY, // target y
                8, // target width
                32 // target height
            );
        }
        else if(this.id=="bomb2"){
            ctx.fillStyle="#00008B";
            ctx.fillRect(600+(this.x-player.x),330+(this.y-player.y),this.width,this.height);
        }
        else if(this.id=="explosion"){
            ctx.fillStyle="#FF4500";
            ctx.fillRect(600+(this.x-player.x),330+(this.y-player.y),this.width,this.height);
        }
        else if(this.id=="fire ball"){
            var img = ImageAtlas[fireballKey];
            ctx.drawImage(
                img, // image
                600+(this.x-player.x),  // target x
                330+(this.y-player.y), // target y
                32, // target width
                32 // target height
            );
        }
        else if(this.id=="fire ball2"){
            ctx.fillStyle="#FF7F50"
            ctx.fillRect(600+(this.x-player.x),330+(this.y-player.y),this.width,this.height);
        }
    }
    this.collision = function(entityC){
        if(this.id=="magic missle"){
            if(entityC.tags.includes("enemy") || entityC.tags.includes("block")){
                playASound("soundEffects/magicMissleImpact.mp3")
                entityC.hp=entityC.hp-30;
                this.enemyFlash(entityC);
                this.remove();
            }
        }
        if(this.id=="staff hit"){
            if(entityC.tags.includes("enemy")){
                entityC.hp=entityC.hp-4;
                this.enemyFlash(entityC);
            }
        }
        if(this.id=="bomb2"){
            if(entityC.tags.includes("block")){
                    this.y=entityC.y-20;
                    this.yVel=0;
            }
        }
        if(this.id=="explosion"){
            if(entityC.tags.includes("enemy")){
                entityC.hp=entityC.hp-25;
                this.enemyFlash(entityC);
            }
        }
        if(this.id=="fire ball"){
            if(entityC.tags.includes("enemy")){
                entityC.hp=entityC.hp-10;
                this.id="fire ball2";
                this.height=64;
                this.width=64;
                this.x=this.x-33
                this.y=this.y-28
                this.enemyFlash(entityC);
                playASound("soundEffects/fireBallImpact.mp3");
            }
            else if(entityC.tags.includes("block")){
                this.id="fire ball2";
                this.height=64;
                this.width=64;
                this.y=this.y-33
                this.x=this.x-28
                playASound("soundEffects/fireBallImpact.mp3");
            }
        }
        if(this.id=="fire ball2"){
            if(entityC.tags.includes("enemy")){
                this.enemyFlash(entityC);
                entityC.hp=entityC.hp-10;
            }
        }
    }
}

// If the projectile has a height, fetches it. Else, the ability does not use or initially use height
function getProjectileHeight(projectile){
    if(projectile=="magic missle"){
       return 8;
    }
    else if(projectile=="staff hit"){
        return 8;
    }
    else if(projectile=="bomb"){
        return 20;
    }
    else if(projectile=="fire ball"){
        return 12;
    }
    else{
        return 0;
    }
}
// same as above, but for width
function getProjectileWidth(projectile){
    if(projectile=="magic missle"){
        return 8;
    }
    else if(projectile=="staff hit"){
        return 20;
    }
    else if(projectile=="bomb"){
        return 20;
    }
    else if(projectile=="fire ball"){
        return 12;
    }
    else{
        return 0;
    }
}
// Used to determine the wait time before being able to use another ability. Stronger abilites are limited
// either through inventory or cooldown
function getProjectileCooldown(projectile){
    if(projectile=="magic missle"){
        return 60;
    }
    else if(projectile=="staff hit"){
        return 30;
    }
    else if(projectile=="med kit"){
        return 10;
    }
    else if(projectile=="bomb"){
        return 60;
    }
    else if(projectile=="fire ball"){
        return 120;
    }
}
// retreives the sound for when a power is cast, if such a sound is required. Not used for all powers
function playProjectileSound(projectile){
    if(projectile=="magic missle"){
        playASound("soundEffects/magicMissle.mp3");
    }
    if(projectile=="staff hit"){
        playASound("soundEffects/staffHit.mp3");
    }
    if(projectile=="fire ball"){
        playASound("soundEffects/fireBallCast.mp3")
    }
    else{

    }
}

playerProjectile.prototype=Object.create(Entity.prototype);
playerProjectile.prototype.constructor = playerProjectile;