
import java.util.Scanner;
 class player {
     private String name;
     private char symbol;

     public player(String name, char symbol) {
         setName(name);
         setSymbol(symbol);
     }

     public char getSymbol() {
         return symbol;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         if (!name.isEmpty())
         this.name = name;
     }

     public void setSymbol(char symbol) {
         if(symbol != '\0'){
             this.symbol = symbol;
         }
//         this.symbol = symbol;
     }
 }
 class Board{
     private char board[][];
      private int boardsize =3;
   private char p1symbol;
     private char p2symbol;
      private int count;
      public final static int player_1_wins = 1;
     public final static int player_2_wins = 2;
     public final static int Draw = 3;
     public final static int Incomplete = 4;
     public final static int Invalid = 5;



      public Board(char p1symbol, char p2symbol){
          board = new char[boardsize][boardsize];
          for (int i=0; i<boardsize;i++){
              for (int j=0; j<boardsize;j++){
                  board[i][j] = ' ';
              }
          }
          this.p1symbol=p1symbol;
          this.p2symbol=p2symbol;
      }

      public int move(char symbol, int x, int y){
          if (x<0 || x>=boardsize || y<0 || y>=boardsize ||board[x][y]!=' '){
              return Invalid;
          }
          board[x][y] = symbol;
          count++;
//         check row
          if (board[x][0]==board[x][1] && board[x][0]==board[x][2] ){
              return symbol == p1symbol? player_1_wins : player_2_wins;
          }
//          check column
          if (board[0][y]==board[1][y] && board[0][y]==board[2][y] ){
              return symbol == p1symbol? player_1_wins : player_2_wins;
          }
//      check diagonal
          if ( board[0][0] !=' ' && board[0][0]==board[1][1] && board[0][0]==board[2][2] ){
              return symbol == p1symbol? player_1_wins : player_2_wins;
          }
//          check diagonal2
          if ( board[0][0] !=' ' && board[0][2]==board[1][1] && board[0][2]==board[2][0] ){
              return symbol == p1symbol? player_1_wins : player_2_wins;
          }
          if (count == boardsize*boardsize){
              return Draw;
          }
          return Incomplete;
      }

      public void print() {
          System.out.println("-----------");
          for (int i = 0; i < boardsize; i++) {
              for (int j = 0; j < boardsize; j++) {
                  System.out.print("| " + board[i][j] + " |");
              }
              System.out.println();
          }
          System.out.println();
          System.out.println("-----------");
      }
 }

  public class TicTacToe {
     private player player1, player2;
     private Board board;
     public static void main(String[] args) {
     TicTacToe t = new TicTacToe();
     t.startGame();
     }

     public void startGame() {
         Scanner sc = new Scanner(System.in);
//         player input
         player1 = takePlayerInput(1);
         player2 = takePlayerInput(2);
         while (player1.getSymbol() == player2.getSymbol()) {
             System.out.println("Pick another Symbol!!");
             char symbol = sc.next().charAt(0);
             player2.setSymbol(symbol);
         }
//         create board
         board = new Board(player1.getSymbol(), player2.getSymbol());
//         conduct the game
         boolean player1Turn = true;
         int status = Board.Incomplete;
         while (status == Board.Incomplete || status == Board.Invalid) {
             if (player1Turn) {
                 System.out.println("player " + player1.getName() + "'s turn");
                 System.out.println("Eter X");
                 int x = sc.nextInt();
                 System.out.println("Eter Y");
                 int y = sc.nextInt();
                 status = board.move(player1.getSymbol(), x, y);
                 if (status != Board.Invalid) {
                     player1Turn = false;
                     board.print();
                 } else {
                     System.out.println("Invalid move! please Try again!");
                 }

             } else {
                 System.out.println("player " + player2.getName() + "'s turn");
                 System.out.println("Eter X");
                 int x = sc.nextInt();
                 System.out.println("Eter Y");
                 int y = sc.nextInt();
                 status = board.move(player2.getSymbol(), x, y);
                 if (status != Board.Invalid) {
                     player1Turn = true;
                     board.print();
                 } else {
                     System.out.println("Invalid move! please Try again!");
                 }
             }
         }
         if (status == Board.player_1_wins) {
             System.out.println("player " + player1.getName() + " wins");
         }else  if (status == Board.player_2_wins) {
             System.out.println("player " + player2.getName() + " wins");
         }else{
             System.out.println("Draw");
         }
     }

     private player takePlayerInput(int num){
         Scanner sc = new Scanner(System.in);
         System.out.println("Enter player"+ num+" name");
         String name = sc.nextLine();
         System.out.println("Enter player"+ num+"Symbol");
         char symbol = sc.next().charAt(0);
         player p = new player(name, symbol);
         return p;

     }
 }
