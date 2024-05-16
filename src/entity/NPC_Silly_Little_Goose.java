package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Silly_Little_Goose extends Entity {
    public NPC_Silly_Little_Goose(GamePanel gamePanel) {
        super(gamePanel);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage(){
        up1 = setup("/player/ducker_up_1");
        up2 = setup("/player/ducker_up_2");
        up3 = setup("/player/ducker_up_3");
        down1 = setup("/player/ducker_down_1");
        down2 = setup("/player/ducker_down_2");
        down3 = setup("/player/ducker_down_3");
        left1 = setup("/player/ducker_left_1");
        left2 = setup("/player/ducker_left_2");
        left3 = setup("/player/ducker_left_3");
        right1 = setup("/player/ducker_right_1");
        right2 = setup("/player/ducker_right_2");
        right3 = setup("/player/ducker_right_3");
    }

    public void setDialogue(){
        dialogues[0] = "Amonra taki gosc siedzisz sobie \nw lokalu Amonra z 10, 20 kolegami...";
        dialogues[1] = "Odpierdol sie odemnie cwelu";
        dialogues[2] = "NA CHUJ MI TYLE JABŁEK!!!";
        dialogues[3] = "jak ktoś tu jest to jest kurwą";
        dialogues[4] = "Pierdolsie!";
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

    public void speak() {
        super.speak();
    }

}
