/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.reversi;

import othelloPAI.Othello;
import unalcol.agents.Agent;
import unalcol.agents.examples.sudoku.naive.NaiveSudokuAgentProgram;
import unalcol.reflect.service.ServiceProvider;
import unalcol.reflect.util.ReflectUtil;

/**
 *
 * @author Jonatan
 */
public class ReversiMain {
  public static void main( String[] argv ){
    // Reflection
    ServiceProvider provider = ReflectUtil.getProvider("services/");
    //    Agent agent = new Agent( new InteractiveAgentProgram( getLanguage() ) );
    Agent w_agent = new Agent( new Othello(Reversi.WHITE) );
    Agent b_agent = new Agent( new Othello(Reversi.BLACK) );
    ReversiMainFrame frame = new ReversiMainFrame( w_agent, b_agent );
    frame.setVisible(true);
  }
    
}
