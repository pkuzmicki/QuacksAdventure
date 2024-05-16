package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject {
    GamePanel gamePanel;
    public OBJ_Chest(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        name = "Copper chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/copper_chest.png"));
            utilityTool.scaledImage(image, gamePanel.tileSize, gamePanel.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
