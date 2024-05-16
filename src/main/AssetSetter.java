package main;

import entity.NPC_Silly_Little_Goose;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject(){
//        gamePanel.object[0] = new OBJ_Key(gamePanel);
//        gamePanel.object[0].worldX = 5 * gamePanel.tileSize;
//        gamePanel.object[0].worldY = 8 * gamePanel.tileSize;


    }

    public void setNPC(){
        gamePanel.npc[0] = new NPC_Silly_Little_Goose(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 52;
        gamePanel.npc[0].worldY = gamePanel.tileSize * 50;

        gamePanel.npc[1] = new NPC_Silly_Little_Goose(gamePanel);
        gamePanel.npc[1].worldX = gamePanel.tileSize * 53;
        gamePanel.npc[1].worldY = gamePanel.tileSize * 50;
    }
}
