/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author TsuJi-ShiKi
 */
public class Main extends MIDlet {

    private Display dis;
    private BallGameCanvas ballGameCanvas;
    private GameList gameList;

    public Main() {
        dis = Display.getDisplay(this);
        ballGameCanvas = new BallGameCanvas(this);
        gameList = new GameList(this);
    }

    public void startApp() {
        dis.setCurrent(gameList);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void change(String uiName) {
        if (uiName.equals("ball")) {
            dis.setCurrent(ballGameCanvas);
        }
    }
}
