import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: 8thlight
 * Date: Mar 23, 2009
 * Time: 3:39:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameDisplay
{
    private Board board;

    public GameDisplay(Board board)
    {
        this.board = board;
    }

    private char charAt(int position)
    {
        char mark = board.charAt(position);
        if(mark == 0)
            mark = ' ';
        return mark;
    }

    public String boardToString()
    {
        String boardDisplay = " " + charAt(0) + " | " + charAt(1) + " | " + charAt(2) + " \n" +
                              "-----------\n" +
                              " " + charAt(3) + " | " + charAt(4) + " | " + charAt(5) + " \n" +
                              "-----------\n" +
                              " " + charAt(6) + " | " + charAt(7) + " | " + charAt(8) + " \n";
        return boardDisplay;
    }

    public void print()
    {
        System.out.println(boardToString());
    }

    public void printInitialBoard()
    {
        try
        {
            board.clear();
            for(int i = 0; i < 9; i++)
            {
                board.populate(Integer.toString(i + 1).charAt(0), i);
            }
            print();
            board.clear();
        }
        catch(Exception e)
        {
        }
    }

    public void printFinalBoard()
    {
        String endMessage = "";
        if(board.isWon())
            endMessage += "Player " + board.getWinner() + " was the winner!";
        else
            endMessage +="It was a tie!";

        endMessage += "  Final board position below\n===\n";
        
        System.out.println(endMessage);

        print();
    }

    public int requestUserMove(char mark)
    {
        System.out.print("Player " + mark + ", make your move (1-9): ");

        return requestUserInput() - 1;
    }

    private int requestUserInput()
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String moveInput = "";
        int move = -1;

        try
        {
            moveInput = br.readLine();
            move = Integer.parseInt(moveInput);
        }
        catch(Exception e)
        {
            System.out.print("Invalid input! Try again: ");
            move = requestUserInput();
        }

        return move;
    }

    public int requestGameType()
    {
        String choices = "";

        for(PlayerFactory.GameType gameType : PlayerFactory.GameType.values())
        {
            // relies on naming convention _V_ between player types
            String[] playerNames = gameType.toString().split("_V_");
            choices += "\t" + (gameType.ordinal() + 1) +" - " + playerNames[0] + " (X) vs. " +
                    playerNames[1] + " (O)\n";
        }

        System.out.println( "What kind of game would you like to play?\n" +
                choices );
        
        return requestUserInput();
    }
}
