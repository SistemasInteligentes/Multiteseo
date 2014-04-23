/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Agente;

import java.util.ArrayList;
import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.agents.examples.labyrinth.LabyrinthPercept;
import unalcol.agents.simulate.util.Language;
import unalcol.agents.simulate.util.SimpleLanguage;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author ian
 */
public class TesoCai extends Agente.SimpleTeseoAgentProgram{
    
    protected Language language;
    protected Vector<String> cmd = new Vector<String>();
    
    ArrayList<String> coor = new ArrayList<>();
    ArrayList<Integer> ac = new ArrayList<>();
    ArrayList<Integer> giros = new ArrayList<>();
    int giro = 0;
        
    public TesoCai(){
        coor.add("0,0");
        giros.add(0);
    }
    @Override
    public void setLanguage(  SimpleLanguage _language ){
        language = _language;
    }
    
    @Override
    public void init() {
        this.cmd.clear();
    }    
    /**
     * execute
     *
     * @param perception Perception
     * @return Action[]
     */
    @Override     
    public Action compute(Percept perception) {
        
        if( cmd.size() == 0 ){
            
            boolean PF = ((Boolean) perception.getAttribute(language.getPercept(language.getPerceptIndex("front"))));
            boolean PD = ((Boolean) perception.getAttribute(language.getPercept(language.getPerceptIndex("right"))));
            boolean PA = ((Boolean) perception.getAttribute(language.getPercept(language.getPerceptIndex("back"))));
            boolean PI = ((Boolean) perception.getAttribute(language.getPercept(language.getPerceptIndex("left"))));
            
            boolean AF = ((Boolean) perception.getAttribute(language.getPercept(language.getPerceptIndex("afront"))));
            boolean AD = ((Boolean) perception.getAttribute(language.getPercept(language.getPerceptIndex("aright"))));
            boolean AA = ((Boolean) perception.getAttribute(language.getPercept(language.getPerceptIndex("aback"))));
            boolean AI = ((Boolean) perception.getAttribute(language.getPercept(language.getPerceptIndex("aleft"))));
            
            boolean MT = ((Boolean) perception.getAttribute(language.getPercept(language.getPerceptIndex("exit"))));
                  
            int d = accion(PF, PD, PA, PI, AF, AD, AA, AI, MT);
            
            if (0 <= d && d < 4) {
                for (int i = 1; i <= d; i++) {
                    cmd.add(language.getAction(3)); //rotate
                }
                cmd.add(language.getAction(2)); // advance
            }
            else if(d == -1){
                cmd.add(language.getAction(0)); // no_op
            }
            else{
                cmd.add(language.getAction(1)); // die
            }
        }
        String x = cmd.get(0);
        cmd.remove(0);
        
        return new Action(x);
    }

    @Override
    public int accion(boolean PF, boolean PD, boolean PA, boolean PI, boolean AF, boolean AD, boolean AA, boolean AI, boolean MT) {
        int pos_rep = memoria();
        if (pos_rep == -1) {
            if (MT) {
                return 4;
            }
            if (!PD) {
                actualizar(1);
                return 1;
            }
            if (!PF) {
                actualizar(0);
                return 0;
            }
            if (!PI) {
                actualizar(3);
                return 3;
            }

            actualizar(2);
            return 2;

        } else {

            int aca = ac.get(pos_rep);
            switch (aca) {
                case 0:
                    if (MT) {
                        return 4;
                    }
                    if (!PD) {
                        actualizar(1);
                        return 1;
                    }
                    if (!PI) {
                        actualizar(3);
                        return 3;
                    }
                    if (!PA) {
                        actualizar(2);
                        return 2;
                    }
                    if (!PF) {
                        actualizar(0);
                        return 0;
                    }
                    break;

                case 1:
                    if (MT) {
                        return 4;
                    }
                    if (!PF) {
                        actualizar(0);
                        return 0;
                    }
                    if (!PI) {
                        actualizar(3);
                        return 3;
                    }
                    if (!PA) {
                        actualizar(2);
                        return 2;
                    }
                    if (!PD) {
                        actualizar(1);
                        return 1;
                    }
                    break;

                case 2:
                    if (MT) {
                        return 4;
                    }
                    if (!PD) {
                        actualizar(1);
                        return 1;
                    }
                    if (!PF) {
                        actualizar(0);
                        return 0;
                    }
                    if (!PI) {
                        actualizar(3);
                        return 3;
                    }
                    if (!PA) {
                        actualizar(2);
                        return 2;
                    }
                    break;

                case 3:
                    if (MT) {
                        return 4;
                    }
                    if (!PD) {
                        actualizar(1);
                        return 1;
                    }
                    if (!PF) {
                        actualizar(0);
                        return 0;
                    }
                    if (!PA) {
                        actualizar(2);
                        return 2;
                    }
                    if (!PI) {
                        actualizar(3);
                        return 3;
                    }
                    break;
            }
        }

        return -2;
    }
    
    public int memoria() {        
        //for (int i = 0; i < coor.size() - 1; i++) {
        for (int i = coor.size()-2; i >= 0 ; i--) {
            if ((coor.get(i).equals(coor.get(coor.size()-1)))  && (giros.get(i) == giro)) {
                return i;
            }
        }
        return -1;
    }

    public void actualizar(int decision) {
        giro = (giro + decision) % 4;
        giros.add(giro);
        ac.add(decision);
        String coordenada = coor.get(coor.size()-1);
        String[] xy =coordenada.split(",");
        int coor_act_x = Integer.parseInt(xy[0]);
        int coor_act_y = Integer.parseInt(xy[1]);
        if (giro == 0) {
            coor_act_y++;
        } else if (giro == 1) {
            coor_act_x++;
        } else if (giro == 2) {
            coor_act_y--;
        } else if (giro == 3) {
            coor_act_x--;
        }
         coordenada = Integer.toString(coor_act_x).concat(",").concat(Integer.toString(coor_act_y));
         //for(int j = 0 ;;j++)
         coor.add(coordenada);
         //ac.add(decision);

    }
    
    public boolean goalAchieved( Percept p ){
        return (((Boolean)p.getAttribute(language.getPercept(4))).booleanValue());
    }
   
    
}
