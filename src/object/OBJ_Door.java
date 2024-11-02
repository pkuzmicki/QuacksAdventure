package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {
    public OBJ_Door(GamePanel gamePanel){
        super(gamePanel);
        name = "Door";
        down1 = setup("/objects/door");
        collision = true;

        solidArea.y = 0;
        solidArea.x = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
