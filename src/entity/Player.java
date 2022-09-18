package entity;

import main.GamePanel;
import main.KeyHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GamePanel   gp;
    KeyHandler  keyH;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage () {

        try {

            spirites[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            spirites[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            spirites[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            spirites[3] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            spirites[4] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            spirites[5] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            spirites[6] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            spirites[7] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
                y -= speed;
            }
            else if (keyH.downPressed) {
                direction = "down";
                y += speed;
            }
            else if (keyH.rightPressed) {
                direction = "right";
                x += speed;
            } else {
                direction = "left";
                x -= speed;
            }

            spriteCounter++;
            if (spriteCounter > 16) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = spirites[0];
                } else if (spriteNum == 2) {
                    image = spirites[1];
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = spirites[2];
                } else if (spriteNum == 2) {
                    image = spirites[3];
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = spirites[4];
                } else if (spriteNum == 2) {
                    image = spirites[5];
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = spirites[6];
                } else if (spriteNum == 2) {
                    image = spirites[7];
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
