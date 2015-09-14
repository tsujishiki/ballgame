
import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TsuJi-ShiKi
 */
public class BallGameCanvas extends GameCanvas implements Runnable, CommandListener {

    private Sprite sprBall;
    private Sprite sprBoard;
    private Graphics g;
    private boolean isDown;
    private int spinAngle;
    private Main main;
    private Command cmdBack;

    public BallGameCanvas(Main main) {
        super(true);
        g = this.getGraphics();
        isDown = true;
        this.main = main;
        try {
            Image imgBall = Image.createImage("/picture/ball.png");
            Image imgBoard = Image.createImage("/picture/board.gif");
            sprBall = new Sprite(imgBall);
            sprBoard = new Sprite(imgBoard);
            sprBall.defineReferencePixel(sprBall.getWidth() / 2, sprBall.getHeight() / 2);
            sprBall.setPosition(this.getWidth() / 2 - sprBall.getWidth() / 2, 0);
            sprBoard.setPosition(this.getWidth() / 2 - sprBoard.getWidth() / 2, (int) (this.getHeight() * 0.8));
            sprBall.paint(g);
            sprBoard.paint(g);
            this.flushGraphics();
            cmdBack = new Command("Exit", Command.EXIT, 1);
            this.addCommand(cmdBack);
            this.setCommandListener(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        new Thread(this).start();
    }

    public void run() {
        while (true) {
            if (isDown) {
                if (sprBall.getY() < this.getHeight() - sprBall.getHeight()) {
                    sprBall.move(0, 3);
                }
            } else {
                sprBall.move(0, -3);
            }
            if (this.getKeyStates() == GameCanvas.LEFT_PRESSED) {
                if (sprBoard.getX() > 0) {
                    sprBoard.move(-2, 0);
                }
            }
            if (this.getKeyStates() == GameCanvas.RIGHT_PRESSED) {
                if (sprBoard.getX() < this.getWidth() - sprBoard.getWidth()) {
                    sprBoard.move(2, 0);
                }
            }
            sprBall.setTransform(this.spinAngle);
            switch (this.spinAngle) {
                case Sprite.TRANS_NONE:
                    this.spinAngle = Sprite.TRANS_ROT90;
                    break;
                case Sprite.TRANS_ROT90:
                    this.spinAngle = Sprite.TRANS_ROT180;
                    break;
                case Sprite.TRANS_ROT180:
                    this.spinAngle = Sprite.TRANS_ROT270;
                    break;
                case Sprite.TRANS_ROT270:
                    this.spinAngle = Sprite.TRANS_NONE;
                    break;
            }
            if (sprBall.collidesWith(sprBoard, true)) {
                isDown = false;
            }
            if (sprBall.getY() == 0) {
                isDown = true;
            }
            this.paint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            if (sprBall.getY() > sprBoard.getY()) {
                try {
                    g.setColor(0, 0, 255);
                    g.drawString("Game Over", this.getWidth() / 2, this.getHeight() / 2, Graphics.HCENTER | Graphics.TOP);
                    this.flushGraphics();
                    Thread.sleep(100000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void paint() {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        sprBall.paint(g);
        sprBoard.paint(g);
        this.flushGraphics();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            main.destroyApp(false);
            main.notifyDestroyed();
        }
    }
}
