public class WinUtil
{
	private String board[][];
	static int winner=0;
	/**
	Default constructor. Initializes board to a null
    **/
	WinUtil()
	{
		board=null;
	}
	/**
	Parameterized constructor that accepts 2d array for board configuration as parameter and assigns that to member board 
	**/
	WinUtil(String board[][])
	{
		this.board=board;
	}
	/**
	@return retuns instance member board. 
	<br>Getter for board.
	**/
	public String[][] getBoard()
	{
		return this.board;
	}
	/**
	@param board Sets instance member board
	<br>Setter for board.
	**/
	public void setBoard(String[][] board)
	{
		this.board=board;
	}
	/**
	@param nx number of xs
	@param no number of os
	@param ns number of spaces
	@return score corresponding to the board configuration
	<br>Score is computed as 10^nx when there are only os and spaces. Similarly, Score is computed as -10^no when there are only xs and spaces. Score is Integer max value when x win and Integer min value when o win. Score is 0 for other cases 
	**/
	public long getScore(int nx,int no,int ns)
	{
		if(nx==0&&no==0)
		{
			return 0L;
		}
		else if(nx==0&&ns==0)
		{
			return Integer.MIN_VALUE;
		}
		else if(no==0&&ns==0)
		{
			return Integer.MAX_VALUE;
		}
		else if(no==0)
		{
			return (long)Math.pow(10L,nx);
		}
		else if(nx==0)
		{
			return -(long)Math.pow(10L,no);
		}
		else 
		{
			return 0L;
		}
	}
	/**
	@return returns score corresponding to row wise computation
	<br>Computes score for 1D array scanned rowwise by keeping track of number of xs,os and spaces in each row and invoking getScore
	**/
	public long getScoreRowWise()
    {
        long toRet=0L;
        for(int i=0;i<board.length;i++)
        {
        	int ns=0,nx=0,no=0;
            for(int j=0;j<board.length;j++)
            {
            	if(board[i][j].equals(""))
            	{
            		ns++;
            	}
            	else if(board[i][j].equalsIgnoreCase(TicTacToeDriver.x))
            	{
            		nx++;
            	}
            	else
            	{
            		no++;
            	}
            }
            toRet+=getScore(nx,no,ns);
        }
        return toRet;
    }
   /**
	@return returns score corresponding to column wise computation
	<br>Computes score for 1D array scanned column wise by keeping track of number of xs,os and spaces in each column and invoking getScore
	**/
    public long getScoreColWise()
    {
        long toRet=0L;
        for(int i=0;i<board.length;i++)
        {
        	int ns=0,nx=0,no=0;
            for(int j=0;j<board.length;j++)
            {
            	if(board[j][i].equals(""))
            	{
            		ns++;
            	}
            	else if(board[j][i].equalsIgnoreCase(TicTacToeDriver.x))
            	{
            		nx++;
            	}
            	else
            	{
            		no++;
            	}
            }
            toRet+=getScore(nx,no,ns);
        }
        return toRet;
    }
    /**
	@return returns score corresponding to primary diagonal
	<br>Computes score for 1D array scanned along primary diagonal and then invoking getScore
	**/
    public long getScoreDiagonal()
    {
    	int ns=0,nx=0,no=0;
        for(int i=0;i<board.length;i++)
        {
			if(board[i][i].equals(""))
            {
            	ns++;
            }
            else if(board[i][i].equalsIgnoreCase(TicTacToeDriver.x))
            {
            	nx++;
            }
            else
            {
            	no++;
            }
        }
        return getScore(nx,no,ns);
    }
    /**
	@return returns score corresponding to secondary diagonal
	<br>Computes score for 1D array scanned along secondary diagonal and then invoking getScore
	**/
    public long getScoreSecondaryDiagonal()
    {
    	int ns=0,nx=0,no=0;
        for(int i=0;i<board.length;i++)
        {
			if(board[i][board.length-i-1].equals(""))
            {
            	ns++;
            }
            else if(board[i][board.length-i-1].equalsIgnoreCase(TicTacToeDriver.x))
            {
            	nx++;
            }
            else
            {
            	no++;
            }
        }
        return getScore(nx,no,ns);
    }
    /**
    @return true when the state is a win else false
    <br>Computes the score of the board configuration along row, column wise and through primary and secondary diagonals. When the score is grater than 10^board length, then the state is considered as win state. This method also updates the winner of the game. 
    **/
    public boolean checkForWin()
    {
    	long diagonalScore=getScoreDiagonal();
    	long secondaryDiagonalScore=getScoreSecondaryDiagonal();
    	long rowScore=getScoreRowWise();
    	long colScore=getScoreColWise();
    	boolean isDiagonalWin=Math.abs(diagonalScore)>=Math.abs(Math.pow(10,board.length));
        boolean isSecondaryDiagonalWin=Math.abs(secondaryDiagonalScore)>=Math.abs(Math.pow(10,board.length));
        boolean isRowWin=Math.abs(rowScore)>=Math.abs(Math.pow(10,board.length));
        boolean isColWin=Math.abs(colScore)>=Math.abs(Math.pow(10,board.length));
        if(isDiagonalWin||isRowWin||isColWin||isSecondaryDiagonalWin)
        {
        	if(rowScore+colScore+diagonalScore+secondaryDiagonalScore<0)
        	{
        		winner=1;
        	}
        	else
        	{
        		winner=0;
        	}
        }
        return isDiagonalWin||isRowWin||isColWin||isSecondaryDiagonalWin;  
    }
}