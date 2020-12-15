public class WinUtil
{
	private String board[][];
	static int winner=0;
	WinUtil()
	{
		board=null;
	}
	WinUtil(String board[][])
	{
		this.board=board;
	}
	String[][] getBoard()
	{
		return this.board;
	}
	void setBoard(String[][] board)
	{
		this.board=board;
	}
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