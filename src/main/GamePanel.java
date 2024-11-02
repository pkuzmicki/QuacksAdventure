package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 4;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 18;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    public final int maxWorldCol = 110;
    public final int maxWorldRow = 100;

    int FPS = 60;

    //SYSTEM
    TileManager tileManager = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    Thread gameThread;
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eventHandler = new EventHandler(this);

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyHandler);
    public Entity[] object = new Entity[10];
    public Entity[] npc = new Entity[10];
    public Entity[] monster = new Entity[40];
    ArrayList<Entity> entityList = new ArrayList<>();

    //DEBUG
    Hitbox hitbox = new Hitbox(this);

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        assetSetter.setMonster();
        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 /FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        double delta = 0;
        long lastTime = System.nanoTime();
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){
            long currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta > 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer > 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }

//inna metoda rysowania
//            update();
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000;
//
//                if (remainingTime < 0){
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long)remainingTime);
//
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

        }
    }
    public void update(){
        if (gameState == playState){
            player.update();

            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }

            for (Entity entity : monster) {
                if (entity != null) {
                    entity.update();
                }
            }
        }
        if (gameState == pauseState){

        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //DEBUG
        long drawStart = 0;
        if (keyHandler.checkDrawTime) {
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN
        if (gameState == titleState){
            ui.draw(g2);
        }
        //OTHERS
        else{
            //DRAWING TILE
            tileManager.draw(g2);

            //ADDING ENTITIES TO LIST
            entityList.add(player);

            for (Entity npcs : npc) {
                if (npcs != null) {
                    entityList.add(npcs);
                }
            }

            for (Entity objects : object) {
                if (objects != null) {
                    entityList.add(objects);
                }
            }

            for (Entity monsters : monster) {
                if (monsters != null) {
                    entityList.add(monsters);
                }
            }
            
            //SORT
            entityList.sort(new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    return Integer.compare(e1.worldY, e2.worldY);
                }
            });

            //DRAW ENTITIES
            for (Entity entity : entityList) {
                entity.draw(g2);
            }

            //RESET ENTITY LIST
            entityList.clear();

            //DRAWING UI
            ui.draw(g2);

            //DRAW HITBOX
            if (keyHandler.showHitbox){
                hitbox.draw(g2);
            }

        }


        //DEBUG
        if (keyHandler.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw time: " + passed, 10, 400);
            System.out.println("Draw time: " + passed);
        }



        g2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        soundEffect.setFile(i);
        soundEffect.play();
    }
}
