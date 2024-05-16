package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[50];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        getTileImage();
        loadMap("/maps/world02.txt");
    }

    public void getTileImage(){
        setup(0, "missing_texture", false);
        setup(1, "missing_texture", false);
        setup(2, "missing_texture", false);
        setup(3, "missing_texture", false);
        setup(4, "missing_texture", false);
        setup(5, "missing_texture", false);
        setup(6, "missing_texture", false);
        setup(7, "missing_texture", false);
        setup(8, "missing_texture", false);
        setup(9, "missing_texture", false);
        setup(10, "grass", false);
        setup(11, "grass_with_flowers", false);
        setup(12, "dirt", false);
        setup(13, "gravel", false);
        setup(14, "sand", false);
        setup(15, "water", true);
        setup(16, "water_down", true);
        setup(17, "water_down_left_in", true);
        setup(18, "water_down_left_out", true);
        setup(19, "water_down_right_in", true);
        setup(20, "water_down_right_out", true);
        setup(21, "water_left", true);
        setup(22, "water_right", true);
        setup(23, "water_top", true);
        setup(24, "water_top_left_in", true);
        setup(25, "water_top_left_out", true);
        setup(26, "water_top_right_in", true);
        setup(27, "water_top_right_out", true);
        setup(28, "bush", true);
        setup(29, "wood", false);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool utilityTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName +".png"));
            tile[index].image = utilityTool.scaledImage(tile[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tile[index].collision = collision;

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath){
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow){
                String line = bufferedReader.readLine();

                while (col < gamePanel.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.maxWorldCol){
                    col = 0;
                    row++;
                }

            }
            bufferedReader.close();
        } catch (Exception e){

        }
    }
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;

            if (worldCol == gamePanel.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }

            }
        }
    }
