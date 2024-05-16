package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed;
    //DEBUG
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //PLAY-STATE
        if (gamePanel.gameState == gamePanel.playState){
            if (code == KeyEvent.VK_W){
                upPressed = true;
            }
            if (code == KeyEvent.VK_S){
                downPressed = true;
            }
            if (code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE){
                gamePanel.gameState = gamePanel.pauseState;
            }
            if (code == KeyEvent.VK_E){
                ePressed = true;
            }
        }

        //PAUSE-STATE
        else if (gamePanel.gameState == gamePanel.pauseState){
            if (code == KeyEvent.VK_ESCAPE){
                gamePanel.gameState = gamePanel.playState;
            }
        }

        //DIALOGUE-STATE
        else if (gamePanel.gameState == gamePanel.dialogueState){
            if (code == KeyEvent.VK_ESCAPE){
                gamePanel.gameState = gamePanel.playState;
            }
        }

        //DEBUG
        if (code == KeyEvent.VK_T){
            if (!checkDrawTime){
                checkDrawTime = true;
            } else if (checkDrawTime) {
                checkDrawTime = false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
