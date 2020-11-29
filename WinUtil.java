public class WinUtil
{
	private String board[][];
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
	public int checkForWinRowWise()
    {
        String temp;
        for(int i=0;i<board.length;i++)
        {
            boolean isWin=true;
            temp=board[i][0];
            if(!temp.equals(""))
            {
                for(int j=1;j<board.length;j++)
                {
                    if(board[i][j].equals(""))
                    {
                        isWin=false;
                        break;
                    }
                    if(!temp.equals(board[i][j]))
                    {
                        isWin=false;
                        break;
                    }
                }
                if(isWin)
                {
                	if(temp.equals(TicTacToeDriver.x))
                	{
                		return 10;
                	}
                	else
                	{
                		return -10;
                	}
                }
            }
        }
        return 0;
    }
    public int checkForWinColWise()
    {
        String temp;
        for(int i=0;i<board.length;i++)
        {
            boolean isWin=true;
            temp=board[0][i];
            if(!temp.equals(""))
            {
                for(int j=1;j<board.length;j++)
                {
                    if(board[j][i].equals(""))
                    {
                        isWin=false;
                        break;
                    }
                    if(!temp.equals(board[j][i]))
                    {
                        isWin=false;
                        break;
                    }
                }
                if(isWin)
                {
                	if(temp.equals(TicTacToeDriver.x))
                	{
                		return 10;
                	}
                	else
                	{
                		return -10;
                	}
                }
            }
        }
        return 0;
    }
    public int checkForWinDiagonal()
    {
        String temp=board[0][0];
        if(temp.equals(""))
        {
            return 0;
        }
        else
        {
            for(int i=1;i<board.length;i++)
            {
                if(!temp.equals(board[i][i]))
                {
                    return 0;
                }
            }
        }
        if(temp.equals(TicTacToeDriver.x))
        {
        	return 10;
        }
        else
        {
        	return -10;
        }
    }
    public int checkForWinSecondaryDiagonal()
    {
        String temp=board[0][board.length-1];
        if(temp.equals(""))
        {
            return 0;
        }
        else
        {
            for(int i=1;i<board.length;i++)
            {
                if(!temp.equals(board[i][board.length-i-1]))
                {
                    return 0;
                }
            }
        }
        if(temp.equals(TicTacToeDriver.x))
        {
        	return 10;
        }
        else
        {
        	return -10;
        }
    }
    public boolean checkForWin()
    {
        boolean isDiagonalWin=checkForWinDiagonal()!=0;
        boolean isSecondaryDiagonalWin=checkForWinSecondaryDiagonal()!=0;
        boolean isRowWin=checkForWinRowWise()!=0;
        boolean isColWin=checkForWinColWise()!=0;
        return isDiagonalWin||isRowWin||isColWin||isSecondaryDiagonalWin;  
    }
}