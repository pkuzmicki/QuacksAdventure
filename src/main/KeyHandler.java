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

        //TITLE STATE
        if (gamePanel.gameState == gamePanel.titleState){
            
            if (gamePanel.ui.titleScreenState == 0){
                if (code == KeyEvent.VK_UP){
                    gamePanel.ui.commandNum--;
                    if (gamePanel.ui.commandNum < 0){
                        gamePanel.ui.commandNum = 3;
                    }
                }
                if (code == KeyEvent.VK_DOWN){
                    gamePanel.ui.commandNum++;
                    if (gamePanel.ui.commandNum > 3){
                        gamePanel.ui.commandNum = 0;
                    }
                }

                if (code == KeyEvent.VK_ENTER){
                    if (gamePanel.ui.commandNum == 0){
                        gamePanel.ui.titleScreenState = 1;
                    }
                    if (gamePanel.ui.commandNum == 1){
                        //load game
                    }
                    if (gamePanel.ui.commandNum == 2){
                        //options
                    }
                    if (gamePanel.ui.commandNum == 3){
                        System.exit(0);
                    }
                }

            } else if (gamePanel.ui.titleScreenState == 1) {
                    if (code == KeyEvent.VK_UP){
                        gamePanel.ui.commandNum--;
                        if (gamePanel.ui.commandNum < 0){
                            gamePanel.ui.commandNum = 1;
                        }
                    }
                    if (code == KeyEvent.VK_DOWN){
                        gamePanel.ui.commandNum++;
                        gamePanel.ui.charactersSelectNum = 1;
                        if (gamePanel.ui.commandNum > 1){
                            gamePanel.ui.commandNum = 0;
                        }
                    }
                    if (code == KeyEvent.VK_LEFT){
                        gamePanel.ui.charactersSelectNum--;
                        if (gamePanel.ui.charactersSelectNum < 0){
                            gamePanel.ui.charactersSelectNum = 2;
                        }
                    }
                    if (code == KeyEvent.VK_RIGHT){
                        gamePanel.ui.charactersSelectNum++;
                        if (gamePanel.ui.charactersSelectNum > 2){
                            gamePanel.ui.charactersSelectNum = 0;
                        }
                    }

                if (code == KeyEvent.VK_ENTER){
                    if (gamePanel.ui.commandNum == 0) {
                        if (gamePanel.ui.charactersSelectNum == 0) {
                            //select character 1
                            gamePanel.gameState = gamePanel.playState;
                            gamePanel.playMusic(1);
                        }
                        if (gamePanel.ui.charactersSelectNum == 1) {
                            //select character 2
                            gamePanel.gameState = gamePanel.playState;
                            //gamePanel.playMusic(1);
                        }
                        if (gamePanel.ui.charactersSelectNum == 2) {
                            //select character 3
                            gamePanel.gameState = gamePanel.playState;
                            gamePanel.playMusic(1);
                        }
                    } else {
                        gamePanel.ui.titleScreenState = 0;
                    }
                }

            }


        }



        //PLAY STATE
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

        //PAUSE STATE
        else if (gamePanel.gameState == gamePanel.pauseState){
            if (code == KeyEvent.VK_ESCAPE){
                gamePanel.gameState = gamePanel.playState;
            }
        }

        //DIALOGUE STATE
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
