

/*
 * Decompiled with CFR 0_118.
 */
package Main;

import Entity.Fish;
import Entity.Player;
import Entity.Trash;
import Graphics.Camera;
import Tools.ImgLoader;
import Tools.Keyboard;
import Tools.SoundLoader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Game
extends JPanel
implements Runnable {
    //private static final long serialVersionUID = -3427361626363632819L;
    private Camera cam;
    private Player player;
    private Thread runThread;
    private Keyboard keyboard;
    private Polygon firstSlope;
    private Polygon secondSlope;
    private int[] ground = new int[1500];
    private ArrayList<Trash> trash;
    private Trash throwingTrash = null;
    private int rotate = 0;
    private int track = 0;
    private float alpha = 0.0f;
    private int length;
    private boolean isBeginning = true;
    private int score = 0;
    private int toWait = 0;
    private int tick = 0;
    private int times = -10;
    private int time = 0;
    private boolean toDisplay = false;
    private Fish fish1;
    private Fish fish2;
    private boolean isOver;
    private String name;
    private String gameMode;

    public Game(String playerName, String mode) {
        this.setBackground(new Color(0, 191, 255));
        this.trash = new ArrayList();
        this.cam = new Camera(0, 0);
        this.keyboard = new Keyboard();
        this.addKeyListener(this.keyboard);
        this.player = new Player(0, 0, "crab1.png", mode);
        name = playerName;
        this.fish1 = new Fish(700, 200, 1, true);
        this.fish2 = new Fish(700, 400, 2, false);
        this.isOver = false;
        int x = 0;
        int i = 0;
        while (i < 1500) {
            if (i < 200) {
                this.ground[i] = 500 - (400 - i * 2);
            } else if (i >= 200 && i <= 1300) {
                this.ground[i] = 500;
            } else {
                this.ground[i] = this.ground[200 - x];
                ++x;
            }
            ++i;
        }
        int[] xpoints = new int[]{-10, -10, 230};
        int[] arrn = new int[3];
        arrn[1] = 500;
        arrn[2] = 500;
        int[] ypoints = arrn;
        this.firstSlope = new Polygon(xpoints, ypoints, 3);
        xpoints = new int[]{1650, 1700, 1400};
        int[] arrn2 = new int[3];
        arrn2[1] = 500;
        arrn2[2] = 500;
        ypoints = arrn2;
        this.secondSlope = new Polygon(xpoints, ypoints, 3);
        this.runThread = new Thread(this);
        this.runThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.BLACK);
        g.setFont(new Font("SANS_SERIF", 1, 28));
        if (this.score == 0) {
            g.drawString("0000" + this.score, 750, 40);
        } else if (this.score < 1000) {
            g.drawString("00" + this.score, 750, 40);
        } else if (this.score < 10000) {
            g.drawString("0" + this.score, 750, 40);
        } else if (this.score < 100000) {
            g.drawString("" + this.score, 750, 40);
        }
        g.drawString("Trash: " + this.trash.size(), 400, 40);
        g2d.translate(this.cam.getX(), this.cam.getY());
        this.renderBackground(g2d);
        if (this.player.getX() < 200) {
            this.player.draw(g, 60.0);
        } else if (this.player.getX() > 1300) {
            this.player.draw(g, -60.0);
        } else {
            this.player.draw(g);
        }
        this.renderForeground(g);
        g2d.translate(- this.cam.getX(), - this.cam.getY());
        if (this.isOver && !this.toDisplay) {
            g.setColor(new Color(0, 0, 0, this.time));
            g.fillRect(-1000, -1000, 9000, 9000);
            //if (this.time > 250) {
            	g.drawImage(ImgLoader.loadImage("harbor.jpg"), 0, 0, 1600, 800, null);
            //}
            g.setColor(new Color(255, 0, 0, this.time));
            g.drawString("The trash has filled the chesapeake bay, ", 50, 300);
            g.drawString("killing the wild life", 50, 340);
            g.drawString("You Could have fixed this by recycling", 280, 650);
        } else if (this.isOver && this.toDisplay) {
        	 //g.setColor(new Color(0, 0, 0, this.time));
             //g.fillRect(-1000, -1000, 9000, 9000);
             //if (this.time > 250) {
             	g.drawImage(ImgLoader.loadImage("harbor.jpg"), 0, 0, 1600, 800, null);
             //}
             //g.setColor(new Color(255, 0, 0, this.time));
             g.drawString("The trash has filled the chesapeake bay, ", 50, 300);
             g.drawString("killing the wild life", 50, 340);
             g.drawString("You Could have fixed this by recycling", 280, 650);
            /*g.setColor(new Color(0, 0, 0));
            g.fillRect(-1000, -1000, 9000, 9000);*/
            /*g.setColor(new Color(255, 0, 0, this.time));
            g.drawString("The trash has filled the chesapeake bay, ", 50, 300);
            g.drawString("killing the wild life", 50, 340);
            g.drawString("You Could have fixed this by recycling", 280, 650);*/
            /*if (this.time > 250) {
            	g.drawImage(ImgLoader.loadImage("Screenshot.png"), 0, 0, 1600, 800, null);
            }*/
        } /*else if (this.isOver && this.toDisplay && this.time > 250) {
            g.setColor(new Color(0, 0, 0));
            g.fillRect(-1000, -1000, 9000, 9000);
            g.drawImage(ImgLoader.loadImage("Screenshot.png"), 0, 0, 1600, 800, null);
            g.setColor(new Color(255, 0, 0, this.time));
            g.drawString("The trash has filled the chesapeake bay, ", 50, 300);
            g.drawString("killing the wild life", 50, 340);
            g.drawString("You Could have fixed this by recycling", 280, 650);
        } */else if (this.isBeginning) {
            g.setColor(Color.BLACK);
            g.drawString("Collect Trash and Bring it", 350, 180);
            g.drawString("to either side", 400, 240);
            g.drawString("Use A and D to move", 380, 440);
            ++this.length;
        }
        if (this.length >= 100) {
            this.isBeginning = false;
        }
    }

    public void renderForeground(Graphics g) {
        g.drawImage(ImgLoader.loadImage("seaweed.png"), 300, 380, null);
        g.drawImage(ImgLoader.loadImage("seaweed_tall.png"), 440, 255, null);
        for (Trash trash : this.trash) {
            trash.draw(g);
        }
        g.drawImage(ImgLoader.loadImage("recycling_can.png"), -130, -230, null);
        g.drawImage(ImgLoader.loadImage("recycling_can_reverse.png"), 1650, -230, null);
        g.drawImage(ImgLoader.loadImage("cloud.png"), -130, -530, null);
        g.drawImage(ImgLoader.loadImage("cloud.png"), 30, -510, null);
        g.drawImage(ImgLoader.loadImage("cloud.png"), 50, -480, null);
        g.drawImage(ImgLoader.loadImage("cloud.png"), 220, -560, null);
        g.drawImage(ImgLoader.loadImage("cloud.png"), 530, -530, null);
        g.drawImage(ImgLoader.loadImage("cloud.png"), 630, -510, null);
        g.drawImage(ImgLoader.loadImage("cloud.png"), 750, -480, null);
        g.drawImage(ImgLoader.loadImage("cloud.png"), 840, -560, null);
        g.drawImage(ImgLoader.loadImage("cloud.png"), 1500, -530, null);
        //g.drawImage(ImgLoader.loadImage("cloud.png"), 1300, -410, null);
        g.drawImage(ImgLoader.loadImage("cloud.png"), 1579, -480, null);
        g.drawImage(ImgLoader.loadImage("cloud.png"), 1700, -600, null);
        g.drawImage(ImgLoader.loadImage("cloud.png"), 1200, -530, null);
      //  g.drawImage(ImgLoader.loadImage("cloud.png"), 1250, -410, null);
        g.drawImage(ImgLoader.loadImage("cloud.png"), 1879, -480, null);
        g.drawImage(ImgLoader.loadImage("cloud.png"), 1800, -420, null);
        g.drawImage(ImgLoader.loadImage("starfish.png"), 1800, 320, null);
        g.drawImage(ImgLoader.loadImage("seashell.png"), 1700, 280, null);
        this.fish1.draw(g);
    }

    public void renderBackground(Graphics2D g2d) {
        int red = 20 * this.trash.size() < 255 ? 20 * this.trash.size() : 255;
        int green = 255 - 30 * this.trash.size() > 0 ? 255 - 30 * this.trash.size() : 0;
        g2d.setColor(new Color(red, 255, green));
        g2d.fillRect(-300, 0, 3000, 600);//Color of Sea
        g2d.setColor(new Color(244, 164, 96));
        g2d.fillRect(-100, 500, 1800, 300);
        g2d.fillPolygon(this.firstSlope);
        g2d.fillPolygon(this.secondSlope);
        g2d.fillRect(-3000, 10, 2990, 1000);
        g2d.fillRect(1650, 10, 5000, 1000);
        g2d.setColor(new Color(0, 255, 33));
        g2d.fillRect(-3000, 0, 2990, 10);//Grass on both sides. Could add more grass pngs
        g2d.fillRect(1650, 0, 2900, 10);
        g2d.drawImage(ImgLoader.loadImage("tree.png"), -410, -460, 256, 512, null);
        g2d.drawImage(ImgLoader.loadImage("sign.png"), -400, -228, null);
        g2d.drawImage(ImgLoader.loadImage("grass.png"), -210, -118, 110, 128, null);
        g2d.drawImage(ImgLoader.loadImage("seaweed.png"), 900, 380, null);
        g2d.drawImage(ImgLoader.loadImage("seashell.png"), 600, 480, null);
        g2d.drawImage(ImgLoader.loadImage("tree.png"), 1710, -460, 256, 512, null);
        g2d.drawImage(ImgLoader.loadImage("grass.png"), 1700, -118, null);
        g2d.drawImage(ImgLoader.loadImage("grass.png"), 1828, -118, null);
        g2d.drawImage(ImgLoader.loadImage("grass.png"), 1956, -118, null);
        g2d.drawImage(ImgLoader.loadImage("seaweed_tall.png"), 640, 255, null);
        g2d.drawImage(ImgLoader.loadImage("seaweed_tall.png"), 1028, 255, null);
        g2d.drawImage(ImgLoader.loadImage("seashell.png"), 0, 280, null);
		g2d.drawImage(ImgLoader.loadImage("sand1.png"), -700, 385, null);
		g2d.drawImage(ImgLoader.loadImage("sand2.png"), -200, 385, null);
		
		

		g2d.drawImage(ImgLoader.loadImage("sandy.png"), 700, 585, null);
		g2d.drawImage(ImgLoader.loadImage("sandy.png"), 200, 485, null);

		g2d.drawImage(ImgLoader.loadImage("sandy.png"), 1700, 385, null);
		g2d.drawImage(ImgLoader.loadImage("sandy.png"), 1200, 685, null);

        this.fish2.draw(g2d);
    }

    @Override
    public void run() {
        try {
            do {
                this.cam.tick(this.player);
                this.player.move(this.keyboard.key);
                this.player.setY(this.ground[this.player.getX()] - 120);
                this.fish1.move();
                this.fish2.move();
                for (Trash trash : this.trash) {
                    if (trash.getY() <= this.ground[trash.getX()] - 100) {
                        trash.setY(trash.getY() + 1);
                    }
                    if (trash.getY() < this.player.getY() - 100 || trash.getX() < this.player.getX() - 100 || trash.getX() >= this.player.getX() + 100) continue;
                    if (!this.player.hasTrash()) {
                        SoundLoader.loadSound("Galaga Coin.wav").start();
                    }
                    this.player.setHasTrash(true);
                    this.player.setTrash(trash);
                }
                if (this.tick == this.toWait) {
                    this.spawnTrash();
                    this.times += 10;
                    this.toWait = (int)(Math.random() * (double)(400 - this.times)) + 100;
                    this.tick = 0;
                } else {
                    ++this.tick;
                }
                if ((this.player.getX() == 0 || this.player.getX() == 1499) && this.player.hasTrash()) {
                    this.score += 100;
                    SoundLoader.loadSound("Trash.wav").start();
                    this.player.setHasTrash(false);
                    this.throwingTrash = this.player.getTrash();
                    this.trash.remove(this.throwingTrash);
                    this.player.setTrash(null);
                }
                if (this.trash.size() >= 10) break;
                this.repaint();
                Thread.sleep(20);
            } while (true);
            this.isOver = true;
            double i = 0.0;
            while (i < 255.0) {
                this.time = (int)i;
                this.repaint();
                i += 1.0E-5;
            }
            this.toDisplay = true;
            this.time = 0;
            i = 0.0;
            while (i < 255.0) {
                this.time = (int)i;
                this.repaint();
                i += 1.0E-5;
            }
        }
        catch (Exception i) {
            // empty catch block
        }
    }

    public void spawnTrash() {
        int toTrash = (int)(Math.random() * 3.0);
        if (toTrash == 0) {
            this.trash.add(new Trash(ImgLoader.loadImage("plastic_bag.png"), (int)(Math.random() * 1000.0) + 300, -100));
        } else if (toTrash == 1) {
            this.trash.add(new Trash(ImgLoader.loadImage("soda_can.png"), (int)(Math.random() * 1000.0) + 300, -100));
        } else if (toTrash == 2) {
            this.trash.add(new Trash(ImgLoader.loadImage("soda_bottle.png"), (int)(Math.random() * 1000.0) + 300, -100));
        }
    }

    public void drawTrashAnimate(Graphics g) {
        if (this.throwingTrash != null) {
            if (this.track < 500) {
                this.rotate += 30;
                ++this.track;
                if (this.track / 100 == 0) {
                    this.throwingTrash.setX(this.throwingTrash.getX() - 1);
                }
                this.throwingTrash.draw(g, this.rotate);
            } else {
                this.trash.remove(this.throwingTrash);
                this.throwingTrash = null;
                this.track = 0;
                this.rotate = 0;
            }
        }
    }
}


