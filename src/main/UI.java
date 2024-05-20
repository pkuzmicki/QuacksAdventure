package main;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font default_font;
    //  BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int charactersSelectNum = 1;
    public int titleScreenState = 0;

//    double playTime;
//    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        try {
            InputStream inputStream = getClass().getResourceAsStream("/font/joystix monospace.otf");
            default_font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        OBJ_Key key = new OBJ_Key(gamePanel);
//        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(default_font);
       //g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);

        //TITLE STATE
        if (gamePanel.gameState == gamePanel.titleState){
            drawTitleScreen();
        }

        //PLAY STATE
        if  (gamePanel.gameState == gamePanel.playState){

        }

        //PAUSE STATE
        if (gamePanel.gameState == gamePanel.pauseState){
            drawPauseScreen();
        }

        //DIALOGUE STATE
        if (gamePanel.gameState == gamePanel.dialogueState){
            drawDialogueScreen();
        }
    }

    public void drawTitleScreen(){

        if (titleScreenState == 0){
            g2.setColor(new Color(66, 135, 245));
            g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
            String text = "Quacks Adventure";
            int x = getXForCenteredText(text);
            int y = gamePanel.tileSize * 3;

            //SHADOW
            g2.setColor(Color.BLACK);
            g2.drawString(text, x + 5, y + 5);

            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);

            //DUCK IMAGE
            x = gamePanel.screenWidth/2 - gamePanel.tileSize * 3;
            y += gamePanel.tileSize;
            g2.drawImage(gamePanel.player.down1, x, y, gamePanel.tileSize * 6, gamePanel.tileSize * 2, null );

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));

            text = "Wanna Play? Let's play!";
            x = getXForCenteredText(text);
            y += gamePanel.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0){
                //g2.drawString(">", x - gamePanel.tileSize, y);
                g2.drawImage(gamePanel.player.right1, x - gamePanel.tileSize, y - gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize/2, null);
                g2.drawImage(gamePanel.player.left1, x + ((gamePanel.tileSize/4) * 39), y - gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize/2, null);
            }

            text = "Play that again MR. play that again";
            x = getXForCenteredText(text);
            y += gamePanel.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1){
                g2.drawString(">", x - gamePanel.tileSize / 2, y);
            }


            text = "We need more options!";
            x = getXForCenteredText(text);
            y += gamePanel.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2){
                g2.drawString(">", x - gamePanel.tileSize / 2, y);
            }

            text = "Get out of car!!!";
            x = getXForCenteredText(text);
            y += gamePanel.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 3){
                g2.drawString(">", x - gamePanel.tileSize / 2, y);
            }
        } else if (titleScreenState == 1) {
            g2.setColor(new Color(66, 135, 245));
            g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

            //CHARACTER SELECTION TITLE
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 65F));
            String text = "Pick you degenerate";
            int x = getXForCenteredText(text);
            int y = gamePanel.tileSize * 3;

            //SHADOW
            g2.setColor(Color.BLACK);
            g2.drawString(text, x + 5, y + 5);

            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);

            x = gamePanel.screenWidth/2 - gamePanel.tileSize * 2;
            y = gamePanel.tileSize * 4;




            if (commandNum == 0) {

                //DRAW CHARACTERS
                if (charactersSelectNum == 0) {
                    g2.drawImage(gamePanel.player.left1, x - gamePanel.tileSize * 5, y, gamePanel.tileSize * 5, gamePanel.tileSize * 5, null);
                    g2.drawImage(gamePanel.player.left1, x, y, gamePanel.tileSize * 4, gamePanel.tileSize * 4, null);
                    g2.drawImage(gamePanel.player.left1, x + gamePanel.tileSize * 5, y, gamePanel.tileSize * 4, gamePanel.tileSize * 4, null);
                }
                if (charactersSelectNum == 1) {
                    g2.drawImage(gamePanel.player.left1, x, y, gamePanel.tileSize * 5, gamePanel.tileSize * 5, null);
                    g2.drawImage(gamePanel.player.left1, x - gamePanel.tileSize * 5, y, gamePanel.tileSize * 4, gamePanel.tileSize * 4, null);
                    g2.drawImage(gamePanel.player.left1, x + gamePanel.tileSize * 5, y, gamePanel.tileSize * 4, gamePanel.tileSize * 4, null);
                }
                if (charactersSelectNum == 2) {
                    g2.drawImage(gamePanel.player.left1, x + gamePanel.tileSize * 5, y, gamePanel.tileSize * 5, gamePanel.tileSize * 5, null);
                    g2.drawImage(gamePanel.player.left1, x - gamePanel.tileSize * 5, y, gamePanel.tileSize * 4, gamePanel.tileSize * 4, null);
                    g2.drawImage(gamePanel.player.left1, x, y, gamePanel.tileSize * 4, gamePanel.tileSize * 4, null);
                }
            }

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
            text = "We have to go back";
            x = getXForCenteredText(text);
            y = gamePanel.tileSize * 11;
            g2.drawString(text, x, y);

            if (commandNum == 1){
                g2.drawString(">", x - gamePanel.tileSize / 2, y);
                x = gamePanel.screenWidth/2 - gamePanel.tileSize * 2;
                y = gamePanel.tileSize * 4;
                g2.drawImage(gamePanel.player.left1, x - gamePanel.tileSize * 5, y, gamePanel.tileSize * 4, gamePanel.tileSize * 4, null);
                g2.drawImage(gamePanel.player.left1, x, y, gamePanel.tileSize * 4, gamePanel.tileSize * 4, null);
                g2.drawImage(gamePanel.player.left1, x + gamePanel.tileSize * 5, y, gamePanel.tileSize * 4, gamePanel.tileSize * 4, null);
            }

        }

    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
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

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,27F));
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

