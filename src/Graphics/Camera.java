/*
 * Decompiled with CFR 0_118.
 */
package Graphics;

import Entity.Player;

public class Camera {
    private int x;
    private int y;

    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void tick(Player player) {
        this.x = - player.getX() + 85 + 450;
        this.y = - player.getY() - 83 + 450;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

