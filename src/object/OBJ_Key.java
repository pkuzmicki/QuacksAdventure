package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject {
    GamePanel gamePanel;
    public OBJ_Key(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        name = "Copper Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/copper_key.png"));
            utilityTool.scaledImage(image, gamePanel.tileSize, gamePanel.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
