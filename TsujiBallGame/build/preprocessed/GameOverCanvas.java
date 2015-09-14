/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author TsuJi-ShiKi
 */
public class GameOverCanvas extends Canvas {

    protected void paint(Graphics g) {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(255, 0, 0);
        g.drawString("Game Over!", this.getWidth() / 2, this.getHeight() / 2, Graphics.HCENTER | Graphics.VCENTER);
    }
}
