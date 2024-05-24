package main;

import java.awt.*;

public class EventHandler {
    GamePanel gamePanel;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;
    public EventHandler (GamePanel gamePanel){
        this.gamePanel = gamePanel;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        if (hit(50,51, "left" )){
            damagePit(gamePanel.dialogueState);
        }
        if (hit(45,51, "left" )){
            healingPool(gamePanel.dialogueState);
        }
        if (hit(55,51, "left" )){
            teleport(gamePanel.dialogueState);
        }
    }

    public boolean hit(int eventCol, int eventRow, String regDirection) {
        boolean hit = false;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;
        eventRect.x  = eventCol * gamePanel.tileSize + eventRect.x;
        eventRect.y  = eventRow * gamePanel.tileSize + eventRect.y;

        if (gamePanel.player.solidArea.intersects(eventRect)){
            if (gamePanel.player.direction.contentEquals(regDirection) || regDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        return hit;
    }

    public void teleport(int gameState) {
        gamePanel.gameState = gameState;
        gamePanel.player.worldX = gamePanel.tileSize * 50;
        gamePanel.player.worldY = gamePanel.tileSize * 10;
    }

    public void damagePit(int gameState){
        gamePanel.gameState = gameState;
        gamePanel.ui.currentDialogue = "Wjebałeś sie do dziury";
        gamePanel.player.life -= 1;
    }

    public void healingPool(int gameState){
        if (gamePanel.keyHandler.ePressed){
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "Napiles sie wody z kalurzy";
            gamePanel.player.life += 1;
        }
    }

}
