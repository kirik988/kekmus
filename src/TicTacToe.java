import java.util.Scanner;
public class TicTacToe {
    static int[][] gameBoard = new int[3][3];
    static Scanner in = new Scanner(System.in);
    static int row, col, turncount = 0, hardmode, countplayer;
    public  static void main(String[] args) {
        Entry();
    }
    private static void NewTicTacToe2(){
        initGameBoard();
        System.out.println("Выберете ник для первого игрока");
        String playerX = in.next();
        System.out.println("Выберете ник для второго игрока, отличный от первого");
        String playerC = in.next();
        String currentPlayer = playerX;
        while (true){
            drawGameBoard();
            System.out.println("Ход игрока " + currentPlayer);
            while (CheckItem()) {}
            gameBoard[row][col] = currentPlayer.equalsIgnoreCase(playerX) ? 1 : 2;
            if (Check()) {
                drawGameBoard();
                System.out.println("Победил игрок " + currentPlayer);
                break;
            }
            currentPlayer = currentPlayer.equals(playerX) ? playerC : playerX;
            turncount +=1;
        }
    }
    private static void NewTicTacToe1 () {
        initGameBoard();
        int result[];
        System.out.println("Выберете себеб ник");
        String player = in.next(), PC = "ЭВМ";
        System.out.println("Выберете себе сложность(от 1 до 3)");
        hardmode = in.nextInt();
        String currentPlayer;
        currentPlayer = player;
        while (true) {
            drawGameBoard();
            if (currentPlayer.equals(player)) {
                while (CheckItem()) {
                }
                gameBoard[row][col] = 1;
            } else {
                result = AI();
                gameBoard[result[0]][result[1]] = 2;
            }
            if (Check()) {
                System.out.println();
                drawGameBoard();
                System.out.println("Победил игрок " + currentPlayer);
                break;
            }
            currentPlayer = currentPlayer.equals(player) ? PC : player;
            turncount +=1;
        }

    }
    private static int[] AI() {
        int t = -1, t1 = -1;
        switch (hardmode) {
            case 1:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (gameBoard[i][j] == 0) {
                            t = i;
                            t1 = j;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (gameBoard[i][j] == 0) {
                            gameBoard[i][j] = 1;
                            if (CheckAI()) {
                                t = i;
                                t1 = j;
                            }
                            gameBoard[i][j] = 0;
                        }
                    }
                }
                if (t == -1) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (gameBoard[i][j] == 0) {
                                t = i;
                                t1 = j;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (gameBoard[i][j] == 0) {
                            gameBoard[i][j] = 2;
                            if (Check()) {
                                t = i;
                                t1 = j;
                            }
                            gameBoard[i][j] = 0;
                        }
                    }
                }
                if (t == -1) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (gameBoard[i][j] == 0) {
                                gameBoard[i][j] = 1;
                                if (CheckAI()) {
                                    t = i;
                                    t1 = j;
                                }
                                gameBoard[i][j] = 0;
                            }
                        }
                    }
                }
                if (t == -1) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (gameBoard[i][j] == 0) {
                                t = i;
                                t1 = j;
                            }
                        }
                    }
                }
                break;
        }
        return new int[] {t, t1};
    }
    private static void initGameBoard(){
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[row].length ; col++) {
                gameBoard[row][col] = 0;
            }
        }
    }
    private  static  boolean CheckItem () {
        System.out.println("Выбери строку: ");
        row = in.nextInt();
        System.out.println("Выбери столбец: ");
        col = in.nextInt();
        if (gameBoard[row][col] != 0) {
            System.out.println("Эта клетка уже заполненна, но никто не запрещает вам выбрать другую:)");
            System.out.println();
            return true;
        } else {
            return  false;
        }
    }
    private static void Entry() {
        System.out.println("Поскольку мы программисты, то нумеровать столбцы и строки будем с нуля)"); //мне просто лень, так-то там немного с индексами поиграть и всё
        System.out.println("Выберете кол-во игроков(от 1 до 2)");
        countplayer = in.nextInt();
        switch (countplayer){
            case 1:
                NewTicTacToe1();
                break;
            case 2:
                NewTicTacToe2();
                break;
        }
    }
    private static boolean  CheckLeft() {
        boolean time = true;
        int sm;
        if (turncount % 2 == 0){
            sm = 1;
        } else {
            sm = 2;
        }
        for (int  i = 0; i < 3; i ++) {
            time = time & (gameBoard[i][i] == sm);
        }
        if (time) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean  CheckRight() {
        boolean time = true;
        int sm;
        if (turncount % 2 == 0){
            sm = 1;
        } else {
            sm = 2;
        }
        for (int  i = 0; i < 3; i ++) {
            time = time & (gameBoard[i][2 - i] == sm);
        }
        if (time) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean  CheckCol() {
        boolean time = true;
        int sm;
        if (turncount % 2 == 0){
            sm = 1;
        } else {
            sm = 2;
        }
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                time = time & (gameBoard[i][j] == sm);
            }
            if (time) {
                return true;
            }
            time = true;
        }
        return false;
    }
    private static boolean  CheckRow() {
        boolean time = true;
        int sm;
        if (turncount % 2 == 0){
            sm = 1;
        } else {
            sm = 2;
        }
        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard.length; j++) {
                time = time & (gameBoard[j][i] == sm);
            }
            if (time) {
                return true;
            }
            time = true;
        }
        return false;
    }
    private  static boolean Check () {
        if (CheckCol() || CheckLeft() || CheckRight() || CheckRow()){
            return true;
        } else {
            return false;
        }
    }
    private static void drawGameBoard(){
        for (int row = 0; row < gameBoard.length; row++){
            for (int col = 0; col < gameBoard[row].length; col++){
                switch (gameBoard[row][col]){
                    case 0:
                        System.out.print("   ");
                        break;
                    case 1:
                        System.out.print(" X ");
                        break;
                    case 2:
                        System.out.print(" O ");
                        break;
                } if (col != gameBoard.length - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if(row < gameBoard.length - 1) {
                System.out.println("---------------");
            }
        }
    }
    private  static boolean CheckAI () {
        if (CheckColAi() || CheckLeftAi() || CheckRightAi() || CheckRowAi()){
            return true;
        } else {
            return false;
        }
    }
    private static boolean  CheckLeftAi() {
        boolean time = true;
        int sm;
        sm = 1;
        for (int  i = 0; i < 3; i ++) {
            time = time & (gameBoard[i][i] == sm);
        }
        if (time) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean  CheckRightAi() {
        boolean time = true;
        int sm;
        sm = 1;
        for (int  i = 0; i < 3; i ++) {
            time = time & (gameBoard[i][2 - i] == sm);
        }
        if (time) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean  CheckColAi() {
        boolean time = true;
        int sm;
        sm = 1;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                time = time & (gameBoard[i][j] == sm);
            }
            if (time) {
                return true;
            }
            time = true;
        }
        return false;
    }
    private static boolean  CheckRowAi() {
        boolean time = true;
        int sm;
        sm = 1;
        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard.length; j++) {
                time = time & (gameBoard[j][i] == sm);
            }
            if (time) {
                return true;
            }
            time = true;
        }
        return false;
    }
}

