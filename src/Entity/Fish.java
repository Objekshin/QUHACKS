/*
 * Decompiled with CFR 0_118.
 */
package Entity;

import Tools.ImgLoader;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Fish {
    private int x;
    private int y;
    private Image sprite;
    private Image sprite2;
    private boolean left;

    public Fish(int x, int y, int type, boolean left) {
        this.x = x;
        this.y = y;
        this.sprite = ImgLoader.loadImage("fish" + type + ".png");
        this.sprite2 = ImgLoader.loadImage("fish" + type + "_reverse.png");
        this.left = left;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move() {
        if (this.x >= 1300 || this.x <= 200) {
            boolean bl = this.left = !this.left;
        }
        this.x = this.left ? (this.x += 3) : (this.x -= 3);
    }

    public void draw(Graphics g) {
        if (!this.left) {
            g.drawImage(this.sprite, this.x, this.y, null);
        } else {
            g.drawImage(this.sprite2, this.x, this.y, null);
        }
    }
}

