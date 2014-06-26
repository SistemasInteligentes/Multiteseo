/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othelloPAI;

import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.agents.examples.reversi.Reversi;

/**
 *
 * @author samsung
 */
public class Othello implements AgentProgram {

    protected String color;
    
        int count = 0;
        ReversiBoard board;
        Move move;
        int n;
        int win;
        int j;
        int drawn;
        int tamano ;

    public Othello(String color) {
        this.color = color;
    }

    @Override
    public Action compute(Percept p) {
        
        
            tamano = (int) p.getAttribute(Reversi.SIZE);
            board = new ReversiBoard(tamano);
            board.println();
            move = new Move();
            n = 1;
            win = 0;
            j = 0;
            drawn = 0;
            count++;
        

//        long time = (long) (200 * Math.random());
//        try {
//            Thread.sleep(time);
//        } catch (Exception e) {
//        }

        
        if (p.getAttribute(Reversi.TURN).equals(color)){
            TKind colorPieza;
            if (color.equals("black")) {
                colorPieza = TKind.black;
            } else if (color.equals("white")) {
                colorPieza = TKind.white;
            } else {
                colorPieza = TKind.space;
            }

            while (board.userCanMove(colorPieza)) {
                j++;
                convertirTablero(p);
                if (board.findMove(colorPieza, 2, move)) {
                    board.move(move, colorPieza);
                }
                System.out.println("despues de mover "+color +" en: "+move.i+" : " + move.j);
                board.println();
                return new Action(move.i + ":" + move.j + ":" + color);
            }

            System.out.println("Total#=" + n + "  Win#=" + win + "  Drawn#=" + drawn);
        }


        return new Action(Reversi.PASS);
    }

    @Override
    public void init() {
    }

    public ReversiBoard convertirTablero(Percept p) {
        board = new ReversiBoard(tamano);

        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                if (p.getAttribute(i + ":" + j).equals(Reversi.BLACK)) {
                    board.board[i][j] = TKind.black;
                }
                if (p.getAttribute(i + ":" + j).equals(Reversi.WHITE)) {
                    board.board[i][j] = TKind.white;
                }
                if (p.getAttribute(i + ":" + j).equals(Reversi.SPACE)) {
                    board.board[i][j] = TKind.space;
                }
            }
        }

        return board;
    }
}
