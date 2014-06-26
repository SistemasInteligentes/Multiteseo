/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othelloPAI;

/**
 *
 * @author samsung
 */
public class Funciones {

    ReversiBoard board ;
    int gameLevel = 0;
    String gameTheme;
    Move hint = null;
    boolean inputEnabled, active;
    
    public void setBoard(ReversiBoard b){
        board=b;
    }

    public void game() {

        if (board.gameEnd()) {
            System.out.println("Juego Terminado");
            return;
        }
        Move move = new Move();
        if (board.findMove(TKind.white, gameLevel, move)) {
            board.move(move, TKind.white);
            System.out.println("score_black " + board.getCounter(TKind.black));
            System.out.println("score_white" + board.getCounter(TKind.white));
            if (board.gameEnd()) {
                showWinner();
            } else if (!board.userCanMove(TKind.black)) {
                System.out.println("You pass...");
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        computerMove();
                    }
                });
            }
        } else if (board.userCanMove(TKind.black)) {
            System.out.println("I pass...");
        } else {
            showWinner();
        }

    }

    public void showWinner() {
        inputEnabled = false;
        active = false;
        if (board.counter[0] > board.counter[1]) {
            System.out.println("You win!");
        } else if (board.counter[0] < board.counter[1]) {
            System.out.println("I win!");
        } else {
            System.out.println("Drawn!");
        }
    }

    public void computerMove() {
        if (board.gameEnd()) {
            if (board.gameEnd()) {
                if (board.counter[0] > board.counter[1]) {
                    System.out.println("You win!");
                } else if (board.counter[0] < board.counter[1]) {
                    System.out.println("I win!");
                } else {
                    System.out.println("Drawn!");
                }
            }
            return;
        }
        Move move = new Move();
        if (board.findMove(TKind.white, gameLevel, move)) {
            board.move(move, TKind.white);
            System.out.println("score_black " + board.getCounter(TKind.black));
            System.out.println("score_white" + board.getCounter(TKind.white));
            if (board.gameEnd()) {
                if (board.counter[0] > board.counter[1]) {
                    System.out.println("You win!");
                } else if (board.counter[0] < board.counter[1]) {
                    System.out.println("I win!");
                } else {
                    System.out.println("Drawn!");
                }
            }
            if (board.gameEnd()) {
                showWinner();
            } else if (!board.userCanMove(TKind.black)) {
                System.out.println("You pass...");
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        computerMove();
                    }
                });
            }
        } else if (board.userCanMove(TKind.black)) {
            System.out.println("I pass...");
        } else {
            showWinner();
        }
    }

}
