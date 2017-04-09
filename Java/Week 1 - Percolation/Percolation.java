import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int OPEN = 1;
    private static final int BLOCK = 0;
    
    private int [][] grid;
    private int gridSize;
    private WeightedQuickUnionUF weightedUF;
    private int numberOfOpenSites;
    
    public Percolation(int n) {
         if (n <= 0) {
            throw new IllegalArgumentException();
        }
        gridSize = n;
        weightedUF = new WeightedQuickUnionUF(gridSize*gridSize + 2);
        grid = new int[gridSize][gridSize];
        numberOfOpenSites = 0;
    }
    
    public void open(int row, int col) {
        checkWithinRange(row, col);
        if (isOpen(row, col)) return;
        grid[row-1][col-1] = 1;
        numberOfOpenSites++;
        int indexIn1DArray = convertFrom2Dto1D(row, col);
        
        // check if first row, then connect to top root
        if (row == 1) {
            weightedUF.union(0, indexIn1DArray);
        }

        // check if bottom row, then connect to bottom root
        if (row == gridSize) {
           weightedUF.union(gridSize*gridSize + 1, indexIn1DArray);
        }
        
        // checking top adjacent 
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            weightedUF.union(convertFrom2Dto1D(row - 1, col), indexIn1DArray);
        }
        
        // checking bottom adjacent 
        if (row + 1 <= gridSize && isOpen(row + 1, col)) {
            weightedUF.union(convertFrom2Dto1D(row + 1, col), indexIn1DArray);
        } 
        
        // checking left adjacent
        if (col - 1 > 0 && isOpen(row, col - 1)) {
             weightedUF.union(convertFrom2Dto1D(row, col - 1), indexIn1DArray);     
        }
        
        // checking right adjacent
        if (col + 1 <= gridSize && isOpen(row, col + 1)) {
             weightedUF.union(convertFrom2Dto1D(row, col + 1), indexIn1DArray);
        }
    }
    
    private int convertFrom2Dto1D(int row, int col) {
         return gridSize * (row - 1) + (col - 1) + 1;
    }
    
    public boolean isOpen(int row, int col) {
        checkWithinRange(row, col);
        return grid[row-1][col-1] == OPEN;
    }
   
    public boolean isFull(int row, int col) {
        checkWithinRange(row, col);
        return (isOpen(row, col)) && weightedUF.connected(0, convertFrom2Dto1D(row, col));
    }
    
    private void checkWithinRange(int row, int col) {
        if (row < 1 || row > gridSize) {
            throw new IndexOutOfBoundsException("row out of bounds");
        }
        if (col < 1 || col > gridSize) {
            throw new IndexOutOfBoundsException("col out of bounds");
        }
    }
    
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }
    
    public boolean percolates() {
        return weightedUF.connected(0, gridSize*gridSize + 1);
    }
    
    public static void main(String[] args) {
    }
}

