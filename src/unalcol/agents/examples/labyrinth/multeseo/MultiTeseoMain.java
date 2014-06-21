/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.labyrinth.multeseo;

import Agente.TesoCai;
import sinergia.LabyrinthAgentProgram;
import unalcol.agents.Agent;
import unalcol.agents.examples.labyrinth.Labyrinth;
import unalcol.agents.examples.labyrinth.LabyrinthDrawer;
import unalcol.agents.examples.labyrinth.multeseo.perceptron.SimpleTeseoAgentProgramPerceptron;
import unalcol.agents.examples.labyrinth.teseo.simple.TeseoSimple;
import unalcol.agents.simulate.util.SimpleLanguage;
import unalcol.types.collection.vector.Vector;


public class MultiTeseoMain {
  private static SimpleLanguage getLanguage(){
    return  new SimpleLanguage( new String[]{"front", "right", "back", "left", "exit",
        "afront", "aright", "aback", "aleft"},
                                   new String[]{"no_op", "die", "advance", "rotate"}
                                   );
  }

  public static void main( String[] argv ){
    //  InteractiveAgentProgram p = new InteractiveAgentProgram( getLanguage() );
    //SimpleTeseoAgentProgramPerceptron p1=new SimpleTeseoAgentProgramPerceptron();
    //SimpleTeseoAgentProgramPerceptron p2=new SimpleTeseoAgentProgramPerceptron();
    
    //TheAgentLosRolos p2 = new TheAgentLosRolos(getLanguage());
    //MyTeseo p1 = new MyTeseo();
    //SimpleTeseoAgentProgram p1=new SimpleTeseoAgentProgram ();
    //TeseoPhanterAgent p2 = new TeseoPhanterAgent();
     //AgentProgram p3 = new unalcol.agents.examples.labyrinth.multeseo.simple.SimpleTeseoAgentProgram(getLanguage());
              
      
    //((MyTeseo)p1).setLanguage(getLanguage());
    
    LabyrinthDrawer.DRAW_AREA_SIZE = 600;
    LabyrinthDrawer.CELL_SIZE = 40;
    Labyrinth.DEFAULT_SIZE = 15;
    
    
    SimpleTeseoAgentProgramPerceptron p1= new SimpleTeseoAgentProgramPerceptron();
    TesoCai p2 = new TesoCai();
    LabyrinthAgentProgram p3 = new LabyrinthAgentProgram(getLanguage());
        
    p1.setLanguage(getLanguage());
    p2.setLanguage(getLanguage());
    
    Agent agent1 = new Agent(p1);    
    Agent agent2 = new Agent(p2);  
    Agent agent3 = new Agent(p3);
    
    
    
    //Agent agent3 = new Agent(p3);
    Vector<Agent> agent = new Vector();
    agent.add(agent1);
    agent.add(agent2);
    agent.add(agent3);
//    Agent agent = new Agent( new RandomReflexTeseoAgentProgram( getLanguage() ) );
    MultiAgentLabyrinthMainFrame frame = new MultiAgentLabyrinthMainFrame( agent, getLanguage() );
    frame.setVisible(true); 
  }
}
