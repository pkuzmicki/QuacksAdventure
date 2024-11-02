package main;

import entity.NPC_Silly_Little_Goose;
import monster.MON_Ribbith;
import monster.MON_Slime_Toad;
import object.OBJ_Poop;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject(){
//        gamePanel.object[0] = new OBJ_Poop(gamePanel);
//        gamePanel.object[0].worldX = 50 * gamePanel.tileSize;
//        gamePanel.object[0].worldY = 51 * gamePanel.tileSize;

        gamePanel.object[1] = new OBJ_Poop(gamePanel);
        gamePanel.object[1].worldX = 55 * gamePanel.tileSize;
        gamePanel.object[1].worldY = 51 * gamePanel.tileSize;

        gamePanel.object[2] = new OBJ_Poop(gamePanel);
        gamePanel.object[2].worldX = 45 * gamePanel.tileSize;
        gamePanel.object[2].worldY = 51 * gamePanel.tileSize;
    }

    public void setNPC(){
        gamePanel.npc[0] = new NPC_Silly_Little_Goose(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 52;
        gamePanel.npc[0].worldY = gamePanel.tileSize * 50;

        gamePanel.npc[1] = new NPC_Silly_Little_Goose(gamePanel);
        gamePanel.npc[1].worldX = gamePanel.tileSize * 53;
        gamePanel.npc[1].worldY = gamePanel.tileSize * 50;
    }

    public void setMonster() {
        gamePanel.monster[0] = new MON_Ribbith(gamePanel);
        gamePanel.monster[0].worldX = gamePanel.tileSize * 50;
        gamePanel.monster[0].worldY = gamePanel.tileSize * 55;

        gamePanel.monster[1] = new MON_Slime_Toad(gamePanel);
        gamePanel.monster[1].worldX = gamePanel.tileSize * 55;
        gamePanel.monster[1].worldY = gamePanel.tileSize * 55;
    }
}
