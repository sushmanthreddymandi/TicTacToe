import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TicTacToeDriver
{
	static boolean isAi=false;
	static final String x="X";
    static final String o="O";

    /**
    @param board This is a 2D grid representing the present state of the board
    @return This method returns the best move that the computer has to take against human by calling min-max algorithm.
    <br>This function returns cell position as move object that minimizes the human score
    **/
    static public Move findBestMove(String board[][]) 
	{ 
	    long bestVal=Long.MIN_VALUE;
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
	                long moveVal=minimax(board, 0, false, Integer.MIN_VALUE,Integer.MAX_VALUE);
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

    /**
    @param board This is a 2D grid representing the present state of the board
    @return This method returns true when all the cells are occupied by either "O" or "X"
    <br>This function returns true when all the cells are not filled else returns false
    **/
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

    /**
    @param board This is a 2d grid representing the present state of the board
    @return This method returns the score corresponding to the present state by evaluating all rows, columns, diagonal and secondary diagonal
    <br>This function computes score corresponding to the state of the given tictactoe grid
    **/
    static public long evaluate(String board[][]) 
	{ 
        WinUtil w=new WinUtil(board);
        long score=w.getScoreRowWise();
        score+=w.getScoreColWise();
        score+=w.getScoreDiagonal();
        score+=w.getScoreSecondaryDiagonal();
	    return score;
	}

    /**
    @param board This is a 2d grid representing the present state of the board
    @param depth This is the depth of recursion happening while computing the solutions
    @param isMax This represents weather this method is invoked for max or min state
    @return This method returns the score corresponding to the permuted state
    <br>This function computes score corresponding to the state of the given tictactoe grid using <b>min-max algorithm</b>. This fails when the size of grid is more than 3.
    **/
	static public long minimax(String board[][],int depth,boolean isMax )
    {
        long score=evaluate(board);
        if(score==10)
        {
            return score;
        }
        else if(score==-10)
        {
            return score;
        }
        else if(isMovesLeft(board)==false)
        {
            return 0;
        }
        if(isMax)
        {
            long best=Integer.MIN_VALUE;
            for(int i=0;i<board.length;i++)
            {
                for(int j=0;j<board.length;j++)
                {
                    if(board[i][j].equals(""))
                    {
                        board[i][j]=x;
                        best=Math.max( best, minimax(board,depth+1,!isMax));
                        board[i][j]="" ;
                    }
                }
            }
            return best;
        }
        else
        {
            long best=Integer.MAX_VALUE;
            for(int i=0;i<board.length;i++)
            {
                for(int j=0;j<board.length;j++)
                {
                    if(board[i][j].equals(""))
                    {
                        board[i][j]=o;
                        best=Math.min(best,minimax(board,depth+1,isMax));
                        board[i][j]="";
                    }
                }
            }
            return best;
        }
    }

    /**
    @param board This is a 2d grid representing the present state of the board
    @param depth This is the depth of recursion happening while computing the solutions
    @param isMax This represents weather this method is invoked for max or min state
    @param alpha The value corresponding to alpha
    @param beta The value corresponding to beta
    @return This method returns the score corresponding to the permuted state or partial score when the depth of recursion tree exceeds a threshold
    <br>This function computes score corresponding to the state of the given tictactoe grid using <b>min-max algorithm with alpha beta pruning</b>. This scales till the size of grid is less than 5 for a pruned depth 5.
    **/
    static public long minimax(String board[][],int depth,boolean isMax,long alpha,long beta)
    {
        long score=evaluate(board);
        if(depth>=5&&score!=0)
        {
            return score;
        }
        else if(isMovesLeft(board)==false)
        {
            return 0;
        }
        if(isMax)
        {
            long best=Integer.MIN_VALUE;
            for(int i=0;i<board.length;i++)
            {
                boolean toBreak=false;
                for(int j=0;j<board.length;j++)
                {
                    if(board[i][j].equals(""))
                    {
                        board[i][j]=x;
                        long val=minimax(board,depth+1,!isMax,alpha,beta);
                        best=Math.max(best,val);
                        board[i][j]="";
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
            return best;
        }
        else
        {
            long best=Integer.MAX_VALUE;
            for(int i=0;i<board.length;i++)
            {
                boolean toBreak=false;
                for(int j=0;j<board.length;j++)
                {
                    if(board[i][j].equals(""))
                    {
                        board[i][j]=o;
                        long val=minimax(board, depth+1, isMax, alpha,beta);
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
            return best;
        }
    }

    /**
    @param args command line arguments
    <br>Can be invoked through 2 ways. <br>1. <b>java TicTacToeDriver n</b><br>2. <b>java TicTacToeDriver ai n</b><br> where n is an integer representing the size of the grid 
    **/
    public static void main(String[] args) 
    {
    	TicTacToe window;
        try
        {
            if(args[0].equalsIgnoreCase("ai"))
            {
                if(Integer.parseInt(args[1])<3)
                {
                    throw new RuntimeException();
                }
                isAi=true;
                window=new TicTacToe("TicTacToe Player VS AI",Integer.parseInt(args[1]));
            }
            else
            {
                if(Integer.parseInt(args[1])<3)
                {
                    throw new RuntimeException();
                }
                window=new TicTacToe("TicTacToe Player vs Player",Integer.parseInt(args[0]));   
            }
        } 
        catch(NumberFormatException e)
        {
            System.out.println("Grid size should be a valid positive integer >2.");
            System.out.println("Usage: java TicTacToeDriver [ai] n.");
            System.exit(0);
        }
        catch(RuntimeException e)
        {
            System.out.println("Grid size should be a valid positive integer >2.");
            System.out.println("Usage: java TicTacToeDriver [ai] n.");
            System.exit(0);
        }
        catch(Exception e)
        {
            System.out.println("Usage: java TicTacToeDriver [ai] n.");
            System.exit(0);
        }
    }
}