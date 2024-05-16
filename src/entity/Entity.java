package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    GamePanel gamePanel;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(-8, -8 , 56, 56);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    String[] dialogues = new String[20];
    int dialogueIndex = 0;

    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setAction(){}

    public void speak(){

        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }

        gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gamePanel.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update(){
        setAction();
        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkPlayer(this);

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

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){

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

            g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

    //GETTING IMAGE
    public BufferedImage setup(String imagePath) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            scaledImage = utilityTool.scaledImage(scaledImage, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e){
            e.printStackTrace();
        }
        return scaledImage;
    }

}
