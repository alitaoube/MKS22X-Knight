//@throws IllegalArgumentException when either parameter is negative.

public class KnightBoard{
  public static void main(String[] args) {
    KnightBoard q = new KnightBoard(8, 8);
    System.out.println(q.solve(2, 2));
  }

  private int[][] board;

  public KnightBoard(int startingRows,int startingCols){
    if (startingRows < 0 || startingCols < 0){
      throw new IllegalArgumentException();
    }

    board = new int[startingRows][startingCols];

    for (int[] x: board){
      for (int y: x){
        y = 0;
      }
    }
  }

    //  Initialize the board to the correct size and make them all 0's


  public String toString(){
    String output = "";
    for (int[] x: board){
      for (int y: x){
        if ( y > 0 && y < 10){
          output += "  " + y;
        }
        else if (y == 0){
          output += " _";
        }
        else{
          output += " " + y;
        }
      }
      output += '\n';
    }
    return output;
  }

  // see format for toString below
  // blank boards display 0's as underscores
  // you get a blank board if you never called solve or
  // when there is no solution
  //
  // @throws IllegalStateException when the board contains non-zero values.
  // @throws IllegalArgumentException when either parameter is negative
  //  or out of bounds.
  public boolean solve(int startingRow, int startingCol){
    board[startingRow][startingCol] = 1;

    return solveH(board.length - 1, board[startingRow].length - 1, 2);
  }
  //
  // @throws IllegalStateException when the board contains non-zero values.
  // @throws IllegalArgumentException when either parameter is negative
  //  or out of bounds.
  // public int countSolutions(int startingRow, int startingCol){
  //
  // }

  // private int numMoves(int row, int col){
  //   int count = 0;
  //   if (row + 2 < board.length && row + 2 <board[row + 2][col - 1] == 0) count++;
  //   if (board[row - 1][col + 2] == 0) count++
  // }

  // Suggestion:
  private boolean solveH(int row ,int col, int level){
    // throw new IllegalArgumentException();
    if (row < 0 || col < 0 || row >= board.length || col >= board[row].length) return false;

    System.out.println(row + ", " + col);
    if (level == row * col) return true;

      if (board[row][col] == 0) {
        board[row][col] = level;
      }
      else{
        // System.out.println("")
        return false;
      }

      System.out.println(this.toString());

    // try{
      return (solveH(row+2, col - 1, level+1)||
      solveH(row + 1, col - 2, level+1)||
      solveH(row - 1, col - 2, level+1)||
      solveH(row - 2, col - 1, level+1)||
      solveH(row - 1, col + 2, level+1)||
      solveH(row + 2, col + 1, level+1)||
      solveH(row +1, col + 2, level+1)||
      solveH(row - 2, col + 1, level+1));
    // }
    // catch(IllegalArgumentException|IllegalStateException e){
    //   System.out.println("Test1");
    //   return false;
    // }
  }

  // level is the # of the knight

}
