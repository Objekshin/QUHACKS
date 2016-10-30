
package Tools;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard
implements KeyListener {
    public boolean[] key = new boolean[68836];

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode > 0 && keyCode < this.key.length) {
            this.key[keyCode] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode > 0 && keyCode < this.key.length) {
            this.key[keyCode] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public boolean isKeyDown(int Key2) {
        return this.key[Key2];
    }
}

