import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TicTacToeDriver
{
	static boolean isAi=false;
	static final String x="X",o="O";
	static public boolean isMovesLeft(String board[][])
    {
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board.length;j++)
            {
                if(board[i][j].equals(""))
                {
                    return true;
                }
            }
        }
        return false;
    }
    static Move findBestMove(String board[][]) 
	{ 
	    int bestVal=Integer.MIN_VALUE;
	    Move bestMove=new Move();
	    bestMove.setR(-1);
	    bestMove.setC(-1);
	    for(int i=0;i<board.length;i++) 
	    { 
	        for(int j=0;j<board.length;j++) 
	        { 
	            if(board[i][j].equals("")) 
	            { 
	                board[i][j]=x;
	                int moveVal=minimax(board, 0, false, Integer.MIN_VALUE,Integer.MAX_VALUE);
	                board[i][j]="";
	                if(moveVal > bestVal) 
	                { 
	                    bestMove.setR(i);
	                    bestMove.setC(j);
	                    bestVal=moveVal;
	                } 
	            } 
	        } 
	    } 
	    return bestMove;
	}
    static int evaluate(String b[][]) 
	{ 
        WinUtil w=new WinUtil(b);
        int score=w.checkForWinRowWise();
        if(score!=0)return score;
        score=w.checkForWinColWise();
        if(score!=0)return score;
        score=w.checkForWinDiagonal();
        if(score!=0)return score;
        score=w.checkForWinSecondaryDiagonal();
        if(score!=0)return score;
	    return 0;
	}
	static int minimax ( String board[][], int depth, boolean isMax )
    {
        int score=evaluate(board);
        if(score==10)
            return score;
        if(score==-10)
            return score;
        if(isMovesLeft(board)==false)
            return 0;
        if(isMax)
        {
            int best=Integer.MIN_VALUE;
            for(int i=0;i<board.length;i++)
            {
                for(int j=0;j<board.length;j++)
                {
                    if(board[i][j].equals(""))
                    {
                        board[i][j]=x;
                        best=Math.max( best, minimax ( board, depth+1, !isMax ) );
                        board[i][j]="" ;
                    }
                }
            }
            return best;
        }
        else
        {
            int best=Integer.MAX_VALUE;
            for(int i=0;i<board.length;i++)
            {
                for(int j=0;j<board.length;j++)
                {
                    if(board[i][j].equals(""))
                    {
                        board[i][j]=o;
                        best=Math.min(best, minimax(board, depth+1, isMax));
                        board[i][j]="";
                    }
                }
            }
            return best;
        }
    }
    static int minimax ( String board[][], int depth, boolean isMax, int alpha, int beta)
    {
        System.out.println(depth);
        int score=evaluate(board);
        if(score==10)
            return score;
        if(score==-10)
            return score;
        if(isMovesLeft(board)==false)
            return 0;
        if(isMax)
        {
            int best=Integer.MIN_VALUE;
            for(int i=0;i<board.length;i++)
            {
                boolean toBreak=false;
                for(int j=0;j<board.length;j++)
                {
                    if(board[i][j].equals(""))
                    {
                        board[i][j]=x;
                        int val=minimax ( board, depth+1, !isMax, alpha,beta);
                        best=Math.max( best, val);
                        board[i][j]="" ;
                        alpha=Math.max(alpha,best);
                        if(beta<=alpha)
                        {
                            toBreak=true;
                            break;
                        }
                    }
                }
                if(toBreak)break;
            }
            System.out.println(alpha+" "+beta);
            return best;
        }
        else
        {
            int best=Integer.MAX_VALUE;
            for(int i=0;i<board.length;i++)
            {
                boolean toBreak=false;
                for(int j=0;j<board.length;j++)
                {
                    if(board[i][j].equals(""))
                    {
                        board[i][j]=o;
                        int val=minimax(board, depth+1, isMax, alpha,beta);
                        best=Math.min(best, val);
                        board[i][j]="";
                        beta=Math.min(beta,val);
                        if(beta<=alpha)
                        {
                            toBreak=true;
                            break;
                        }
                    }
                }
                if(toBreak)break;
            }
            System.out.println(alpha+" "+beta);
            return best;
        }
    }
    public static void main(String[] args) 
    {
    	TicTacToe window; 
        if(args[0].equalsIgnoreCase("ai"))
    	{
    		isAi=true;
            window=new TicTacToe("TicTacToe Player VS AI",3`);
    	}
    	else
    	{
    		window=new TicTacToe("TicTacToe Player vs Player",Integer.parseInt(args[0]));	
    	}
    }
}