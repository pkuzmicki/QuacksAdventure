package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_Slime_Toad extends Entity {

    GamePanel gamePanel;
    public MON_Slime_Toad(GamePanel gamePanel) {
        super(gamePanel);

        name = "Slime Toad";
        speed = 1;
        maxLife = 5;
        life = maxLife;
        type = 2;

        solidArea.x = 10;
        solidArea.y = 10;
        solidArea.width = 45;
        solidArea.height = 45;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/toad_up");
        up2 = setup("/monster/toad_up");
        up3 = setup("/monster/toad_up");
        down1 = setup("/monster/toad_down");
        down2 = setup("/monster/toad_down");
        down3 = setup("/monster/toad_down");
        left1 = setup("/monster/toad_left");
        left2 = setup("/monster/toad_left");
        left3 = setup("/monster/toad_left");
        right1 = setup("/monster/toad_right");
        right2 = setup("/monster/toad_right");
        right3 = setup("/monster/toad_right");
    }

    public void setAction(){
        actionLockCounter++;

        if (actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50){
                direction = "down";
            }
            if (i > 50 && i <= 75){
                direction = "left";
            }
            if (i > 75){
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

}
