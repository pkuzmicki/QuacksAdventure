package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyHandler;

    public /*final*/ int screenX;
    public /*final*/ int screenY;

    public boolean invincible = false;
    int invincibleCounter = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth /2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight /2 - (gamePanel.tileSize/2);

        solidArea = new Rectangle(14, 36, 34, 24);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gamePanel.tileSize * 50;
        worldY = gamePanel.tileSize * 50;
        speed = 10;
        direction = "down";

        //PLAYER STATUS
        maxLife = 10;
        life = maxLife;
    }

    public void getPlayerImage(){
            up1 = setup("/player/ducker_up_1");
            up2 = setup("/player/ducker_up_2");
            up3 = setup("/player/ducker_up_3");
            down1 = setup("/player/ducker_down_1");
            down2 = setup("/player/ducker_down_2");
            down3 = setup("/player/ducker_down_3");
            left1 = setup("/player/ducker_left_1");
            left2 = setup("/player/ducker_left_2");
            left3 = setup("/player/ducker_left_3");
            right1 = setup("/player/ducker_right_1");
            right2 = setup("/player/ducker_right_2");
            right3 = setup("/player/ducker_right_3");
    }


    public void update(){

        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed){
            if(keyHandler.upPressed){
                direction = "up";
            }
            else if(keyHandler.downPressed){
                direction = "down";
            }
            else if(keyHandler.leftPressed){
                direction = "left";
            }
            else if(keyHandler.rightPressed){
                direction = "right";
            }


            //CHECK TILE COLLISION
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objectIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            //CHECK NPC COLLISION
            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
            interactMonster(monsterIndex);


            //CHECK EVENT
            gamePanel.eventHandler.checkEvent();

            gamePanel.keyHandler.ePressed = false;

            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 8) {
                if (spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2){
                    spriteNum = 3;
                }
                else if (spriteNum == 3){
                    spriteNum =4;
                }
                else if (spriteNum == 4){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if (invincible){
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }

    public void pickUpObject(int i){
        if (i != 999){

        }

    }

    public void interactNPC(int i){
        if (i != 999){
            if (gamePanel.keyHandler.ePressed){
                gamePanel.gameState = gamePanel.dialogueState;
                gamePanel.npc[i].speak();
            }
        }
    }

    public void interactMonster(int i){
        if (i != 999){
            if (invincible == false){
                life -= 1;
                invincible = true;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch(direction) {
            case "up":
                if (spriteNum == 1 || spriteNum == 3) {
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                if (spriteNum == 4){
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1 || spriteNum == 3) {
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                if (spriteNum == 4){
                    image = down3;
                }
                break;
            case "left":
                if (spriteNum == 1 || spriteNum == 3) {
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                if (spriteNum == 4){
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 1 || spriteNum == 3) {
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                if (spriteNum == 4){
                    image = right3;
                }
                break;
        }

        //g2.drawImage(image, screenX, screenY,null);

        //DEBUG
//        g2.setFont(new Font("Comic Sans", Font.BOLD, 30));
//        g2.setColor(Color.WHITE);
//        g2.drawString("Invincible:" + invincibleCounter, 10, 150);

        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, screenX, screenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}




//    public void pickUpObject(int i){
//        if (i != 999){
//            String objectName = gamePanel.object[i].name;
//
//            switch (objectName) {
//                case "Copper Key":
//                    //gamePanel.playSE(2);
//                    gamePanel.object[i] = null;
//                    hasKey++;
//                    gamePanel.ui.showMessage("You got a copper key!");
//
//                    break;
//                case "Door":
//                    if (hasKey > 0){
//                        //gamePanel.playSE(4);
//                        gamePanel.object[i] = null;
//                        hasKey--;
//                        gamePanel.ui.showMessage("You opened a door!");
//                    } else {
//                        gamePanel.ui.showMessage("You don't have a key!");
//                    }
//                    break;
//                case "Normal human shit":
//                    gamePanel.object[i] = null;
//                    //gamePanel.playSE(1);
//                    gamePanel.ui.showMessage("Did you just ate a shit?");
//                    speed += 25;
//                    Timer timer = new Timer();
//                    timer.schedule(new TimerTask() {
//                        @Override
//                        public void run() {
//                            speed -= 25;
//                        }
//                    }, 5000);
//                    break;
//                case "Copper chest":
//                    //gamePanel.playSE(3);
//                    gamePanel.ui.gameFinished = true;
//                   // gamePanel.stopMusic();
//                    break;
//
//            }
//        }
//    }