package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Poop extends SuperObject {
    GamePanel gamePanel;
    public OBJ_Poop(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        name = "Normal human shit";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/poop.png"));
            utilityTool.scaledImage(image, gamePanel.tileSize, gamePanel.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
