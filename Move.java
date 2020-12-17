public class Move 
{ 
    private int r;
    private int c;
    /**
    @return Returns the row of the move or cell in the grid
    <br>This is getter for row.
    **/
    public int getR()
    {
    	return this.r;
    }
    /**
    @return Returns the column of the move or cell in the grid
    <br>This is getter for column.
    **/
    public int getC()
    {
    	return this.c;
    }
    /**
    @param r This method accepts an integer corresponding to the row value of the cell in the grid
    <br>This is setter for row. This method sets the row with the parameter passed
    **/
    public void setR(int r)
    {
    	this.r=r;
    }
    /**
    @param c This method accepts an integer corresponding to the col value of the cell in the grid
    <br>This is setter for col. This method sets the col with the parameter passed
    **/
    public void setC(int c)
    {
    	this.c=c;
    }
}