import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe extends JFrame implements ActionListener
{
    private Button buttons[][]; 
    private int alternate;
    /**
    @param title Title of the GUI frame
    @param n The size of the Tictactoe grid
    <br>This constructor initialises the GUI frame and set its layout to grid layout. Further, calls initialize method to add buttons to the layout. 
    **/
    public TicTacToe(String title , int n)
    {
        super(title);
        alternate=0;
        buttons=new Button[n][n];
        setLayout(new GridLayout(n,n));
        initialize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,n*60,n*60);
        setVisible(true);
    }
    /**
    <br>This method adds n<sup>2</sup> buttons to the grid of dimension nxn and register the button click events to the event listener 
    **/
    public void initialize()
    {
        for(int i = 0; i < buttons.length; i++)
        {
            for(int j = 0;j < buttons[i].length; j++)
            {
                buttons[i][j] = new Button();
                buttons[i][j].setLabel("");
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }
    /**
    <br>This method is invoked when the game ends. This method resets the grid state and other initial variables
    **/
    public void reset()
    {
        alternate = 0;
        for(int i = 0; i < buttons.length; i++)
        {
            for(int j = 0; j < buttons[i].length; j++)
            {
                buttons[i][j].setLabel("");
            }
        }
    }
    /**
    @param e The wrapper object that contains information regarding the button click event.
    <br>This method is invoked by listener when the button click is performed. This method updates the state of the grid based on game type: player vs player or player vs computer(invokes findBestMove in this case). Then notified using a dialog when the game is finised.
    **/
    public void actionPerformed(ActionEvent e) 
    {
        Button buttonClicked = (Button)e.getSource();
        if(buttonClicked.getLabel().equals(""))
        {
            if(!TicTacToeDriver.isAi)
            {
                if(alternate%2 == 0)
                {
                    buttonClicked.setLabel(TicTacToeDriver.x);
                }
                else
                {
                    buttonClicked.setLabel(TicTacToeDriver.o);
                }
            }
            else
            {
                buttonClicked.setLabel(TicTacToeDriver.x);
                Move m=TicTacToeDriver.findBestMove(getBoard());
                if(m.getR()!=-1&&m.getC()!=-1)
                {
                    buttons[m.getR()][m.getC()].setLabel(TicTacToeDriver.o);
                }
            }
            String[][] board=getBoard();
            boolean isAllElementsFilled=!TicTacToeDriver.isMovesLeft(board);
            WinUtil w=new WinUtil(board);
            boolean isWin=w.checkForWin();
            if(isAllElementsFilled&&!isWin)
            {
                JOptionPane.showMessageDialog(null, "Game Over. Its a draw");
                reset();
            }
            else if(isWin)
            {
                JOptionPane.showMessageDialog(null, "Game Over. "+(WinUtil.winner==1?TicTacToeDriver.o:TicTacToeDriver.x)+" won.");
                reset();
            }
            else if(!TicTacToeDriver.isAi)
            {
                alternate++;
            }
        }
    }
    /**
    @return Returns the state of the grid in the UI as 2D String Array using the labels on the buttons 
    **/
    public String[][] getBoard()
    {
        String s[][]=new String[buttons.length][buttons.length];
        for(int i=0;i<buttons.length;i++)
        {
            for(int j=0;j<buttons.length;j++)
            {
                s[i][j]=buttons[i][j].getLabel();
            }
        }
        return s;
    }
}