//@throws IllegalArgumentException when either parameter is negative.

public class KnightBoard{
  public static void main(String[] args) {
    KnightBoard q = new KnightBoard(5, 5);
    System.out.println(q.solve(0, 0));
  }



  private int[][] board;
  private int rows, cols;
  private static int[]moves = {2, -1, 1, -2, -1, -2, -2, -1, -1, 2, 2, 1, 1, 2, -2, 1};

  public KnightBoard(int startingRows,int startingCols){

    if (startingRows < 0 || startingCols < 0){
      throw new IllegalArgumentException();
    }

    rows = startingRows;
    cols = startingCols;

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
          output += " _ ";
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

    return solveH(startingRow, startingCol, 1);
  }
  //
  // @throws IllegalStateException when the board contains non-zero values.
  // @throws IllegalArgumentException when either parameter is negative
  //  or out of bounds.
  // public int countSolutions(int startingRow, int startingCol){
  //
  // }

  private boolean checker(){
    for (int x = 0; x < board.length; x++){
      for (int y = 0; y < board[x].length; y++){
        if (board[x][y] == 0){
          return false;
        }
      }
    }
    return true;
  }

  // Suggestion:
  private boolean solveH(int row ,int col, int level){
    // try{

    if (row < 0 || col < 0 || row >= rows || col >= cols){
      return false;
    }

    if (level > rows * cols){
      if (checker()){
        return true;
      }
      return false;
    }
    else{

      if (board[row][col] != 0){
        return false;
      }
      else{
        board[row][col] = level;
      }


      System.out.println(this.toString());


        for (int x = 0; x < moves.length; x+=2){
          if ((solveH(row+moves[x], col + moves[(x+1)] , level+1))) {
            return true;
          }

        System.out.println(this.toString());
        System.out.println("1");
      }

      board[row][col] = 0;
      return false;
    }
  }

  // level is the # of the knight

}
