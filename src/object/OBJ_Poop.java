package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Poop extends Entity {
    public OBJ_Poop(GamePanel gamePanel){
        super(gamePanel);
        name = "Normal human shit";
        down1 = setup("/objects/poop");
        collision = true;

        solidArea.x = 10;
        solidArea.y = 20;
        solidArea.width = 42;
        solidArea.height = 36;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
