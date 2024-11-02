package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_Ribbith extends Entity {
    GamePanel gamePanel;
    public MON_Ribbith(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        name = "Ribbith the Frog";
        speed = 1;
        maxLife = 20;
        life = maxLife;
        type = 2;

        solidArea.x = 10;
        solidArea.y = 20;
        solidArea.width = 44;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/ribbith_up");
        up2 = setup("/monster/ribbith_up");
        up3 = setup("/monster/ribbith_up");
        down1 = setup("/monster/ribbith_down");
        down2 = setup("/monster/ribbith_down");
        down3 = setup("/monster/ribbith_down");
        left1 = setup("/monster/ribbith_left");
        left2 = setup("/monster/ribbith_left");
        left3 = setup("/monster/ribbith_left");
        right1 = setup("/monster/ribbith_right");
        right2 = setup("/monster/ribbith_right");
        right3 = setup("/monster/ribbith_right");
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
