package test;

// a position class for row, col
public class Grid{
	public int row;
	public int col;
	
	public Grid(int row, int col){
		this.row=row;
		this.col=col;
	}
			
	public boolean equals(Grid g){
		return row==g.row && col==g.col;
	}
	
	@Override
	public int hashCode(){
		return (row+","+col).hashCode();
	}
	
	@Override
	public String toString(){
		return (row+","+col); 
	}
}