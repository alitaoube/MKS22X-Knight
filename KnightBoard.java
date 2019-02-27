import java.util.*; //ArrayList, Collections

public class KnightBoard{
  public static void main(String[] args) {
    KnightBoard q = new KnightBoard(8, 8);
    System.out.println(q.solve(0, 0));
  }



  private int[][] board;
  private int[][] moveboard;
  private int rows, cols;
  private static int[][]moves = {{2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-1, 2}, {2, 1}, {1, 2}, {-2, 1}} ;

  public KnightBoard(int startingRows,int startingCols){

    if (startingRows < 0 || startingCols < 0){
      throw new IllegalArgumentException();
    }

    rows = startingRows;
    cols = startingCols;

    board = new int[startingRows][startingCols];
    moveboard = new int[startingRows][startingCols];
    initMoveBoard();

    for (int[] x: board){
      for (int y: x){
        y = 0;
      }
    }
  }

  private void initMoveBoard(){
    for (int x = 0; x < board.length; x++){
      for (int y = 0; y < board[x].length; y++){
        int sum = 0;
        for (int[] move : moves){
          if (x + move[0] >= 0 && x + move[0] < board.length && y + move[1] >= 0 && y + move[1] < board[0].length) sum++;
        }
        moveboard[x][y] = sum;
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
    if (!isValid(startingRow, startingCol)) throw new IllegalArgumentException();
    try{
      if (checker()) throw new IllegalStateException();
    }
    catch(IllegalStateException e){
      e.printStackTrace();
      return false;
    }
    return oSolveH(startingRow, startingCol, 1);
  }
  //
  // @throws IllegalStateException when the board contains non-zero values.
  // @throws IllegalArgumentException when either parameter is negative
  //  or out of bounds.
  // public int countSolutions(int startingRow, int startingCol){
  //
  // }

  private boolean isValid(int row, int col){
    return (row >= 0 && col >= 0 && row < board.length && col < board[row].length && board[row][col] == 0);
  }

  private boolean checker(){
    for (int x = 0; x < board.length; x++){
      for (int y = 0; y < board[x].length; y++){
        if (board[x][y] == 0){
          // System.out.println("HERE");
          return false;
        }
      }
    }
    return true;
  }
  //
  // private initNewBoard(){
  //   moveboard = new int[board.length][board[0].length];
  //
  //   for (int x = 0; x < moveboard.length; x++){
  //     for (int y = 0; y < moveboard[x].length; y++){
  //       for (int z = 0; z < moves.length; z+=2){
  //
  //         if (row+moves[x], col + moves[(x+1)] , level+1))
  //       }
  //     }
  //   }
  // }

  private boolean addKnight(int row, int col, int level){
    if (row < 0 || row >= board.length || col < 0 || col >= board[row].length){
      // System.out.println(toString());
      // System.out.println("HERE1");
      return checker();
      // return false;
    }
    if (board[row][col] != 0) return false;
    board[row][col] = level;
  return true;
}

  private boolean removeKnight(int row, int col){
    if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) return false;

    if (board[row][col] == 0) return false;

    board[row][col] = 0;
    return true;
  }

  // Suggestion:
  private boolean solveH(int row ,int col, int level){

    if (level == board.length * board[0].length + 1) return true; // Has to be greater so +1

    System.out.println(toString());

    if (addKnight(row, col, level)){
      for (int x = 0; x < moves.length; x+=2){
          if ((solveH(row+moves[x][0], col + moves[x][(x+1)] , level+1))) {
            return true;
        }
    }
  }
  return false;

}

  private boolean oSolveH(int row, int col, int level){
    board[row][col] = level;

    System.out.print(toString());

    if (level == board.length * board[0].length + 1) return true;

    ArrayList<Tile> poss = new ArrayList<Tile>();
    for (int x = 0; x < moves.length; x++){
      rows = row + moves[x][0];
      cols = col + moves[x][1]; // You use these values 3 times, worth storing them
      if (isValid(rows, cols)){
        poss.add(new Tile(rows, cols, moveboard[rows][cols]));
      }
    }
    Collections.sort(poss); // Collections uses compareTo to sort, which was suggested in class (I listen!)

    for (Tile x : poss){
      moveboard[x.getRow()][x.getCol()]--;
    }

    for (Tile x : poss){
      int temp = moveboard[x.getRow()][x.getCol()]; // Storing previous value
      if (oSolveH(x.getRow(), x.getCol(), level+1)){
        return true;            // Same as before
      }
      moveboard[x.getRow()][x.getCol()] = temp; // Reset it
    }
    board[row][col] = 0; // Backtrack
    return false;
  }

  private class Tile implements Comparable<Tile> {
    int num, row, col;

    public Tile(int r, int c, int n){
      row = r;
      col = c;
      num = n;
    }

    public int compareTo(Tile x){
      return this.num - x.num;
    }


    // ACCESSOR METHODS
    public int getRow(){
      return row;
    }

    public int getCol(){
      return col;
    }

    public int getNum(){
      return num;
    }
  }
}
