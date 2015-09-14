
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class GameList extends List implements CommandListener{

    private Command cmdSelect;
    private Main main;

    public GameList(Main main) {
        super("小球碰撞游戏菜单", Choice.EXCLUSIVE);
        this.main = main;
        this.append("游戏开始", null);
        cmdSelect = new Command("选择", Command.SCREEN, 1);
        this.addCommand(cmdSelect);
        this.setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if(c == cmdSelect){
            int selectedIndex = this.getSelectedIndex();
            if(selectedIndex == 0){
                main.change("ball");
            }
        }
    }
}
