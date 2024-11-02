package main;

import java.awt.*;

public class Hitbox {
    GamePanel gamePanel;

    public int col, row;
    public Hitbox(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.GREEN);
        g2.fillRect(gamePanel.player.screenX + gamePanel.player.solidArea.x, gamePanel.player.screenY + gamePanel.player.solidArea.y,
                gamePanel.player.solidArea.width, gamePanel.player.solidArea.height);

        g2.setColor(Color.GRAY);
        for (int i = 0; i < gamePanel.object.length; i++){
            if (gamePanel.object[i] != null){
                g2.fillRect((gamePanel.object[i].worldX - gamePanel.player.worldX + gamePanel.player.screenX) + gamePanel.object[i].solidArea.x,
                        (gamePanel.object[i].worldY - gamePanel.player.worldY + gamePanel.player.screenY) + gamePanel.object[i].solidArea.y,
                        gamePanel.object[i].solidArea.width, gamePanel.object[i].solidArea.height);
            }

        }

        g2.setColor(Color.YELLOW);
        for (int i = 0; i < gamePanel.object.length; i++){
            if (gamePanel.npc[i] != null){
                g2.fillRect((gamePanel.npc[i].worldX - gamePanel.player.worldX + gamePanel.player.screenX) + gamePanel.npc[i].solidArea.x,
                        (gamePanel.npc[i].worldY - gamePanel.player.worldY + gamePanel.player.screenY) + gamePanel.npc[i].solidArea.y,
                        gamePanel.npc[i].solidArea.width, gamePanel.npc[i].solidArea.height);
            }

        }

        g2.setColor(Color.RED);
        for (int i = 0; i < gamePanel.monster.length; i++){
            if (gamePanel.monster[i] != null){
                g2.fillRect((gamePanel.monster[i].worldX - gamePanel.player.worldX + gamePanel.player.screenX) + gamePanel.monster[i].solidArea.x,
                        (gamePanel.monster[i].worldY - gamePanel.player.worldY + gamePanel.player.screenY) + gamePanel.monster[i].solidArea.y,
                        gamePanel.monster[i].solidArea.width, gamePanel.monster[i].solidArea.height);
            }

        }

//        g2.setColor(Color.BLUE);
//            g2.fillRect(col * gamePanel.tileSize + gamePanel.eventHandler.eventRect[col][row].x,
//                    row * gamePanel.tileSize + gamePanel.eventHandler.eventRect[col][row].y,
//                    gamePanel.eventHandler.eventRect[col][row].width, gamePanel.eventHandler.eventRect[col][row].height);

    }

//    public void getEventPosition(int col, int row) {
//        col = this.col;
//        row = this.row;
//    }
}
