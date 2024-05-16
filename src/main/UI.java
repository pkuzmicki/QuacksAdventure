package main;

import java.awt.*;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font comic_50;
    //  BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";

//    double playTime;
//    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        comic_50 = new Font("Comic Sans", Font.PLAIN, 50);
//        OBJ_Key key = new OBJ_Key(gamePanel);
//        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(comic_50);
        g2.setColor(Color.WHITE);

        //PLAY-STATE
        if  (gamePanel.gameState == gamePanel.playState){

        }

        //PAUSE-STATE
        if (gamePanel.gameState == gamePanel.pauseState){
            drawPauseScreen();
        }

        //DIALOGUE-STATE
        if (gamePanel.gameState == gamePanel.dialogueState){
            drawDialogueScreen();
        }
    }

    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gamePanel.screenHeight/2 - gamePanel.tileSize;
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){
        //WINDOW
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize * 8;
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 4);
        int height = (gamePanel.tileSize * 4) - gamePanel.tileSize/2;
        drawSubWindow(x, y, width, height);

        x += gamePanel.tileSize;
        y += gamePanel.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color color = new Color(0, 0, 0, 210);
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height,45, 45);

        color = new Color(255,255,255, 210);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x + 8, y + 8, width - 16, height - 16, 35, 35);
    }

    public int getXForCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gamePanel.screenWidth/2 - length/2;
    }
}





//    public void draw(Graphics2D g2) {
//
//        if(gameFinished){
//            g2.setFont(comic_50);
//            g2.setColor(Color.WHITE);
//
//            String text;
//            int textLength;
//            int x;
//            int y;
//
//            text = "You won!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gamePanel.screenWidth/2 - textLength/2;
//            y = gamePanel.screenHeight/2 - (gamePanel.tileSize * 3);
//            g2.drawString(text, x, y);
//
//            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
//            g2.setColor(Color.YELLOW);
//
//            text = "Congratulations!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gamePanel.screenWidth/2 - textLength/2;
//            y = gamePanel.screenHeight/2 + (gamePanel.tileSize * 2);
//            g2.drawString(text, x, y);
//
//            text = "Your time is: " + decimalFormat.format(playTime);
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gamePanel.screenWidth/2 - textLength/2;
//            y = gamePanel.screenHeight/2 + (gamePanel.tileSize * 4);
//            g2.drawString(text, x, y);
//
//            gamePanel.gameThread = null;
//        } else {
//
//            g2.setFont(comic_50);
//            g2.setColor(Color.WHITE);
//            g2.drawImage(keyImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2, gamePanel.tileSize, gamePanel.tileSize, null);
//            g2.drawString(" = " + gamePanel.player.hasKey, 90, 80);
//
//            playTime += (double) 1/60;
//            g2.drawString("Time: " + decimalFormat.format(playTime), gamePanel.tileSize * 11, 80);
//
//            if (messageOn) {
//                g2.setFont(g2.getFont().deriveFont(20F));
//                g2.drawString(message, gamePanel.tileSize, gamePanel.tileSize * 2);
//
//                messageCounter++;
//                if (messageCounter > 120) {
//                    messageCounter = 0;
//                    messageOn = false;
//                }
//            }
//        }
//    }

