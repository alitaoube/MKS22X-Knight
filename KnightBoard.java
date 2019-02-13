//@throws IllegalArgumentException when either parameter is negative.

public class KnightBoard{
  public static void main(String[] args) {
    KnightBoard q = new KnightBoard(5, 5);
    System.out.println(q.toString());
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
          output += " " + y;
        }
        else if (y == 0){
          output += " _";
        }
        else{
          output += y;
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

    return solveH(board.length, board[startingRow].length, 1);
  }
  //
  // @throws IllegalStateException when the board contains non-zero values.
  // @throws IllegalArgumentException when either parameter is negative
  //  or out of bounds.
  // public int countSolutions(int startingRow, int startingCol){
  //
  // }

  // Suggestion:
  private boolean solveH(int row ,int col, int level){
    if (level == row * col) return true;


  }
  // level is the # of the knight

}
