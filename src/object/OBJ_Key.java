package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {
    public OBJ_Key(GamePanel gamePanel){
        super(gamePanel);
        name = "Copper key";
        down1 = setup("/objects/copper_key");
    }

}
