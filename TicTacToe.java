import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe extends JFrame implements ActionListener
{
    private Button buttons[][]; 
    private int alternate = 0;
    public TicTacToe(String title,int n)
    {
        super(title);
        buttons=new Button[n][n];
        setLayout(new GridLayout(n,n));
        initialize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,n*60,n*60);
        setVisible(true);
    }
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