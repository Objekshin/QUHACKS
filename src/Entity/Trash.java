/*
 * Decompiled with CFR 0_118.
 */
package Entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Trash {
    private Image sprite;
    private int x;
    private int y;

    public Trash(Image sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
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

    public Image getSprite() {
        return this.sprite;
    }

    public void draw(Graphics g) {
        g.drawImage(this.sprite, this.x, this.y, null);
    }

    public void draw(Graphics g, double degree) {
        BufferedImage image = (BufferedImage)this.sprite;
        double rotationRequired = Math.toRadians(degree);
        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, 2);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(op.filter(image, null), this.x, this.y, null);
    }
}

