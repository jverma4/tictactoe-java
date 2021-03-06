package trptcolin.ui;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import trptcolin.boards.Board3x3;
import trptcolin.players.PlayerFactoryImpl;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 30, 2009
 * Time: 1:55:29 PM
 */
public class ConsoleViewTest extends Assert
{
    private Board3x3 board;
    private ConsoleView consoleView;

    @Before
    public void setup()
    {
        ControllerImpl controller = new ControllerImpl(new Board3x3());
        board = (Board3x3) controller.board;
        PlayerFactoryImpl playerFactory = new PlayerFactoryImpl();
        consoleView = new ConsoleView(controller, playerFactory, board);
    }

    @Test
    public void shouldBeEmpty() throws Exception
    {
        assertEquals(   "   |   |   \n" +
                        "-----------\n" +
                        "   |   |   \n" +
                        "-----------\n" +
                        "   |   |   \n",
                        consoleView.boardToString());
    }

    @Test
    public void shouldPrintFullBoard() throws Exception
    {
        board.populate('X', 0, 0);
        board.populate('O', 1, 1);
        board.populate('X', 2, 2);
        board.populate('O', 0, 2);
        board.populate('X', 2, 0);
        board.populate('O', 1, 0);
        board.populate('X', 2, 1);

        assertEquals(
            " X |   | O \n" +
            "-----------\n" +
            " O | O |   \n" +
            "-----------\n" +
            " X | X | X \n",
            consoleView.boardToString());
    }
}
