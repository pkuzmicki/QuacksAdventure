package main;

public class EventHandler {
    GamePanel gamePanel;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    public EventHandler (GamePanel gamePanel){
        this.gamePanel = gamePanel;

        eventRect = new EventRect[gamePanel.maxWorldCol][gamePanel.maxWorldCol];

        int col = 0;
        int row = 0;
        while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if (col == gamePanel.maxWorldCol) {
                col = 0;
                row++;
            }
        }


    }

    public void checkEvent() {

        //CHECK IF PLAYER IS AWAY FROM LAST EVENT
        int xDistance = Math.abs(gamePanel.player.worldX - previousEventX);
        int yDistance = Math.abs(gamePanel.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gamePanel.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent){
            if (hit(50,51, "left" )){
                damagePit(gamePanel.dialogueState,50,51);
            }
            if (hit(45,51, "left" )){
                healingPool(gamePanel.dialogueState,50,51);
            }
            if (hit(55,51, "left" )){
                teleport(gamePanel.dialogueState,50,51);
            }
        }


    }

    public boolean hit(int eventCol, int eventRow, String regDirection) {
        boolean hit = false;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;
        eventRect[eventCol][eventRow].x  = eventCol * gamePanel.tileSize + eventRect[eventCol][eventRow].x;
        eventRect[eventCol][eventRow].y  = eventRow * gamePanel.tileSize + eventRect[eventCol][eventRow].y;

        if (gamePanel.player.solidArea.intersects(eventRect[eventCol][eventRow]) && eventRect[eventCol][eventRow].eventDone == false){
            if (gamePanel.player.direction.contentEquals(regDirection) || regDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gamePanel.player.worldX;
                previousEventY = gamePanel.player.worldY;
            }
        }

        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRect[eventCol][eventRow].x = eventRect[eventCol][eventRow].eventRectDefaultX;
        eventRect[eventCol][eventRow].y = eventRect[eventCol][eventRow].eventRectDefaultY;
        return hit;
    }

    public void teleport(int gameState, int col, int row) {
        gamePanel.gameState = gameState;
        gamePanel.player.worldX = gamePanel.tileSize * 50;
        gamePanel.player.worldY = gamePanel.tileSize * 10;
    }

    public void damagePit(int gameState, int col, int row){

        gamePanel.gameState = gameState;
        gamePanel.ui.currentDialogue = "Wjebałeś sie do dziury";
        gamePanel.player.life -= 1;
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }

    public void healingPool(int gameState, int col, int row){
        if (gamePanel.keyHandler.ePressed){
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "Napiles sie wody z kalurzy";
            gamePanel.player.life += 1;
        }
    }
}
