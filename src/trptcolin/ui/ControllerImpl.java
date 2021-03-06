package trptcolin.ui;

import trptcolin.baseGame.Board;
import trptcolin.baseGame.Controller;
import trptcolin.baseGame.View;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 27, 2009
 * Time: 9:12:49 AM
 */
public class ControllerImpl implements Controller
{
    public Board board;
    protected View view;
    
    public int lastMove = -1;
    protected int gameType = -1;
    public boolean playAgain = false;
    public boolean waitingForInput = false;

    public ControllerImpl(Board board)
    {
        this.board = board;
    }

    public char charAt(int spot)
    {
        return board.charAt(spot);
    }

    public void setUI(View view)
    {
        this.view = view;
    }
    public void updateDisplay()
    {
        view.redraw();
    }

    public void printInitialBoard()
    {
        view.clear();
        view.buildBoard();
        updateDisplay();
    }

    public void printFinalBoard()
    {
        view.stopListening();
        view.addFinalMessage();
        updateDisplay();
    }

    public String boardToString()
    {
        return "";
    }

    public int requestUserMove(char mark)
    {
        return requestUserInput(mark);
    }

    public int requestGameType()
    {
        view.clear();
        view.buildGameTypeChoices();

        waitingForInput = true;
        while(waitingForInput)
        {
            try
            {
                Thread.sleep(100);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return gameType;
    }
    
    protected int requestUserInput(char mark)
    {
        waitingForInput = true;

        boolean gameOver = board.gameOver();

        view.getUserMove(mark);
        
        while(waitingForInput && !gameOver)
        {
            try
            {
                Thread.sleep(100);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return lastMove;
    }

    public boolean shouldPlayAgain()
    {
        waitingForInput = true;
        while(waitingForInput)
        {
            try
            {
                Thread.sleep(100);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        if(playAgain)
        {
            try
            {
                board.clear();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return playAgain;
    }

    public void squareChosen(int square)
    {
        waitingForInput = false;
        lastMove = square;
    }


    public void playAgain(boolean b)
    {
        waitingForInput = false;
        playAgain = b;
    }

    public void setWaitingForInput(boolean b)
    {
        waitingForInput = b;
    }

    public void setPlayAgain(boolean b)
    {
        playAgain = b;
    }

    public void setGameType(int gameType)
    {
        this.gameType = gameType;
    }

    public void setLastMove(int move)
    {
        lastMove = move;
    }

    public void gameTypeChosen(int gameType)
    {
        waitingForInput = false;
        this.gameType = gameType;
    }

}

