/*
 * Decompiled with CFR 0_118.
 */
package Entity;

import Entity.Trash;
import Main.PlayerName;
import Tools.ImgLoader;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Player {
    private int x;
    private int y;
    private boolean hasTrash;
    private boolean image;
    private Image sprite;
    private Image sprite1;
    private Image sprite2;
    private Image sprite3;
    private Image current;
    private Trash trash;
    private int speed;

    /*public Player(int x, int y, String Sprite) {
        this.x = x;
        this.y = y;
        this.image = true;
        this.hasTrash = false;
        this.sprite = ImgLoader.loadImage("crab1.png");
        this.sprite1 = ImgLoader.loadImage("crab2.png");
        this.sprite2 = ImgLoader.loadImage("crab1_reverse.png");
        this.sprite3 = ImgLoader.loadImage("crab2_reverse.png");
        this.current = this.sprite;
    }*/
    
    public Player(int x, int y, String Sprite, String mode) {
        this.x = x;
        this.y = y;
        this.image = true;
        this.hasTrash = false;
        this.sprite = ImgLoader.loadImage("crab1.png");
        this.sprite1 = ImgLoader.loadImage("crab2.png");
        this.sprite2 = ImgLoader.loadImage("crab1_reverse.png");
        this.sprite3 = ImgLoader.loadImage("crab2_reverse.png");
        this.current = this.sprite;
        setSpeed(mode);
    }
    
    private void setSpeed(String mode){
    	switch(mode){
    	case PlayerName.EASY: speed = 7;
    	break;
    	
    	case PlayerName.HYPER: speed = 50;
    	break;
    	
    	case PlayerName.HARD: speed = 4;
    	break;
    	
    	case PlayerName.MASTER: speed = 2;
    	break;
    	
    	default: speed = 4;
    	}
    	
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void move(boolean[] keys) {
        boolean right;
        boolean left = keys[37] || keys[65];
        boolean bl = right = keys[39] || keys[68];
        if (right) {
            this.x += speed;
            if (this.image) {
                this.image = false;
                this.current = this.sprite;
            } else {
                this.image = true;
                this.current = this.sprite1;
            }
        }
        if (left) {
            this.x -= speed;
            if (this.image) {
                this.image = false;
                this.current = this.sprite2;
            } else {
                this.image = true;
                this.current = this.sprite3;
            }
        }
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.x > 1499) {
            this.x = 1499;
        }
        if (this.trash != null) {
            this.trash.setX(this.x);
            this.trash.setY(this.y - 80);
        }
    }

    public void setTrash(Trash trash) {
        this.trash = trash;
    }

    public Trash getTrash() {
        return this.trash;
    }

    public void setHasTrash(boolean hasTrash) {
        this.hasTrash = hasTrash;
    }

    public boolean hasTrash() {
        return this.hasTrash;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Graphics g) {
        g.drawImage(this.current, this.x, this.y, null);
    }

    public void draw(Graphics g, double degree) {
        BufferedImage image = (BufferedImage)this.current;
        double rotationRequired = Math.toRadians(degree);
        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, 2);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(op.filter(image, null), this.x, this.y, null);
    }
}

