package trptcolin.main;

import trptcolin.boards.Board3By3;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 24, 2009
 * Time: 11:22:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class MockBoard extends Board3By3
{
    public boolean populateCalled = false;
    public char populatedMark = 0;
    public int populatedPosition = -1;

    public boolean gameOverCalled = false;

    public MockBoard()
    {
        squares = new char[9];
        winner = 0;
        if(winSets == null)
        {
            winSets = new int[][]{
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6} };
        }
    }
    public void populate(char mark, int position) throws Exception
    {
        populateCalled = true;
        populatedMark = mark;
        populatedPosition = position;

        super.populate(mark, position);
    }

    public boolean gameOver()
    {
        gameOverCalled = true;
        return super.gameOver();
    }
}
