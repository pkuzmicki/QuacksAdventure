package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject {
    GamePanel gamePanel;
    public OBJ_Heart(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
            image = utilityTool.scaledImage(image, gamePanel.tileSize, gamePanel.tileSize);
            image2 = utilityTool.scaledImage(image2, gamePanel.tileSize, gamePanel.tileSize);
            image3 = utilityTool.scaledImage(image3, gamePanel.tileSize, gamePanel.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
