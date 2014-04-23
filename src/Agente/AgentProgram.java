package Agente;
import unalcol.agents.Action;
import unalcol.agents.Percept;
import unalcol.types.collection.vector.*;

/**
 * <p>Title: AgentProgram </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gómez
 * @version 1.0
 */
public interface AgentProgram {
  /**
   * Determines the action to be executed by the agent according to its knowledge
   * and current perception (can be a multi agent system itself)
   * @param percept Current Perception
   * @return Action to be executed by the agent
   */
    
  public abstract int accion(boolean PF, boolean PD, boolean PA, boolean PI, boolean AF, boolean AD, boolean AA, boolean AI, boolean MT );
  
  public Action compute( Percept p );

  public void init();

}
