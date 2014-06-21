package unalcol.agents.examples.labyrinth.multeseo.simple;

import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.agents.examples.labyrinth.multeseo.simple.busqueda.BusquedaBasica;
import unalcol.agents.examples.labyrinth.multeseo.simple.matriz.ListaDispersa;
import unalcol.agents.simulate.util.SimpleLanguage;
import unalcol.types.collection.vector.*;

public class StudentsSimpleTeseoAgentProgram implements AgentProgram {

    protected SimpleLanguage language;
    protected Vector<String> cmd = new Vector<>();
    protected Nodo nodoActual = new Nodo();
    protected ListaDispersa<Nodo> nodosDispersa = new ListaDispersa<>();
    short direccion = 0;
    short FRENTE;
    short ATRAS;
    short DERECHA;
    short IZQUIERDA;
    short LLEGADA;
    short FRENTEAGENTE;
    short ATRASAGENTE;
    short DERECHAAGENTE;
    short IZQUIERDAAGENTE;
    short x = 0;
    short y = 0;

    public StudentsSimpleTeseoAgentProgram(SimpleLanguage _language) {
        language = _language;
        nodoActual.setVisitado(true);
        insertarEnMatriz(nodoActual.getX(), nodoActual.getY(), nodoActual);
        FRENTE = (short) language.getPerceptIndex("front");
        ATRAS = (short) language.getPerceptIndex("back");
        DERECHA = (short) language.getPerceptIndex("right");
        IZQUIERDA = (short) language.getPerceptIndex("left");
        LLEGADA = (short) language.getPerceptIndex("exit");
        FRENTEAGENTE = (short) language.getPerceptIndex("afront");
        ATRASAGENTE = (short) language.getPerceptIndex("aback");
        DERECHAAGENTE = (short) language.getPerceptIndex("aright");
        IZQUIERDAAGENTE = (short) language.getPerceptIndex("aleft");
        direccion = 0;
    }

    public void setLanguage(SimpleLanguage _language) {
        language = _language;
    }

    @Override
    public void init() {
        cmd.clear();
        nodoActual = new Nodo();
        nodoActual.setVisitado(true);
        insertarEnMatriz(nodoActual.getX(), nodoActual.getY(), nodoActual);
        FRENTE = (short) language.getPerceptIndex("front");
        ATRAS = (short) language.getPerceptIndex("back");
        DERECHA = (short) language.getPerceptIndex("right");
        IZQUIERDA = (short) language.getPerceptIndex("left");
        LLEGADA = (short) language.getPerceptIndex("exit");
        FRENTEAGENTE = (short) language.getPerceptIndex("aFront");
        ATRASAGENTE = (short) language.getPerceptIndex("aBack");
        DERECHAAGENTE = (short) language.getPerceptIndex("aRight");
        IZQUIERDAAGENTE = (short) language.getPerceptIndex("aLeft");
        direccion = 0;
        x = 0;
        y = 0;
    }

    public int accion(boolean MT, boolean[] percepciones, boolean[] percepcionesAgente) {
        if (MT) {
            return 4;
        }
        boolean[] percepcionesGlobal = new boolean[4];
        for (int x = 0; x < 4; x++) {
            percepcionesGlobal[(x + direccion) % 4] = percepciones[x];
        }


        if (!percepcionesGlobal[0]) {
            nodoActual.getHijos()[0] = buscarEnMatriz(nodoActual.getX(), nodoActual.getY() - 1); //Nodo Norte
        }
        if (!percepcionesGlobal[1]) {
            nodoActual.getHijos()[1] = buscarEnMatriz(nodoActual.getX() + 1, nodoActual.getY()); // Nodo Oriente
        }
        if (!percepcionesGlobal[2]) {
            nodoActual.getHijos()[2] = buscarEnMatriz(nodoActual.getX(), nodoActual.getY() + 1); // Nodo Sur
        }
        if (!percepcionesGlobal[3]) {
            nodoActual.getHijos()[3] = buscarEnMatriz(nodoActual.getX() - 1, nodoActual.getY()); // Nodo Occidente
        }

        // Agregar Nuevos Nodos
        for (int x = 0; x < 4; x++) {
            if (!percepciones[x] && nodoActual.getHijos()[(x + direccion) % 4] == null) {
                int pos = (x + direccion) % 4;
                nodoActual.insertarHijo(pos);
                insertarEnMatriz(nodoActual.getHijos()[pos].getX(), nodoActual.getHijos()[pos].getY(), nodoActual.getHijos()[pos]);
            }
        }

        // CUAL SELECCIONAR ALGORITMO DE BUSQUEDA
        for (int x = 0; x < 4; x++) {
            if (nodoActual.getHijos()[(x + direccion) % 4] != null && !percepcionesAgente[x]) {
                if (!nodoActual.getHijos()[(x + direccion) % 4].isVisitado()) {
                    return (x) % 4;
                }
            }
        }

        // Si no Existe un vecino no visitado
        return 5;

    }

    @Override
    public Action compute(Percept p) {        
        if (cmd.size() == 0) {
            boolean percepcionesPared[] = new boolean[4];
            boolean percepcionesOtroAgente[] = new boolean[4];
            // 0:Arriba 1:Derecha 2:Atras 3:Izquierda
            boolean MT = ((Boolean) p.getAttribute(language.getPercept(LLEGADA))).booleanValue();
            percepcionesPared[0] = ((Boolean) p.getAttribute(language.getPercept(FRENTE))).booleanValue();
            percepcionesPared[1] = ((Boolean) p.getAttribute(language.getPercept(DERECHA))).booleanValue();
            percepcionesPared[2] = ((Boolean) p.getAttribute(language.getPercept(ATRAS))).booleanValue();
            percepcionesPared[3] = ((Boolean) p.getAttribute(language.getPercept(IZQUIERDA))).booleanValue();
            percepcionesOtroAgente[0] = ((Boolean) p.getAttribute(language.getPercept(FRENTEAGENTE))).booleanValue();
            percepcionesOtroAgente[1] = ((Boolean) p.getAttribute(language.getPercept(DERECHAAGENTE))).booleanValue();
            percepcionesOtroAgente[2] = ((Boolean) p.getAttribute(language.getPercept(ATRASAGENTE))).booleanValue();
            percepcionesOtroAgente[3] = ((Boolean) p.getAttribute(language.getPercept(IZQUIERDAAGENTE))).booleanValue();



            int d = accion(MT, percepcionesPared, percepcionesOtroAgente);

            

            if (d == 5) {



                BusquedaBasica busquedaBasica = new BusquedaBasica();
                Vector<Nodo> nodos = busquedaBasica.busquedaYCamino(nodoActual, false);
                // Desde inicio hasta final  nodos [0] = inicio nodos[size-1] = final               
                int size = nodos.size();
                nodos.get(0).getX();
                  for (int x = 1; x < size; x++) {
                    Nodo siguiente = nodos.get(x);
                    //nodos.remove(1);// Suponiendo q esta en la posicion siguiente a la cabeza el nodo siguiente al inicio;
                    int hijo = -1;
                    for (int y = 0; y < 4; y++) {
                        if (nodoActual.getHijos()[y] != null) {
                            if (siguiente.getX() == nodoActual.getHijos()[y].getX() && siguiente.getY() == nodoActual.getHijos()[y].getY()) {
                                hijo = y;

                            }
                        }
                    }
                    if (hijo >= 0) {
                        int rotaciones = (4 + (hijo - direccion) % 4) % 4;
                        for (int y = 1; y <= rotaciones; y++) {
                            cmd.add(language.getAction(3)); // rotate
                        }
                        cmd.add(language.getAction(2));// advance

                        nodoActual = nodoActual.getHijos()[hijo];
                        nodoActual.setVisitado(true);

                        direccion = (short) ((rotaciones + direccion) % 4);
                        insertarEnMatriz(nodoActual.getX(), nodoActual.getY(), nodoActual);

                    }
                }
            } else {
                if (0 <= d && d < 4) {
                    nodoActual = nodoActual.getHijos()[(d + direccion) % 4];
                    for (int i = 1; i <= d; i++) {
                        cmd.add(language.getAction(3));//rotate                                        
                    }
                    direccion = (short) ((direccion + d) % 4);
                    cmd.add(language.getAction(2));// advance
                    nodoActual.setVisitado(true);
                    insertarEnMatriz(nodoActual.getX(), nodoActual.getY(), nodoActual);

                } else {
                    cmd.add(language.getAction(0)); // die
                }
            }

        }
        String x = cmd.get(0);
        cmd.remove(0);
        return new Action(x);
    }

    private Nodo buscarEnMatriz(int x, int y) {

        return nodosDispersa.leer((short) x, (short) y);
    }

    private void insertarEnMatriz(int x, int y, Nodo nodo) {

        nodosDispersa.insertar(nodo, (short) x, (short) y);
    }

    boolean isImportante(boolean[] data) {
        int c = 0;


        for (int x = 0; x < data.length; x++) {

            c = !data[0] ? c + 1 : c;
        }

        return c > 2 ? true : false;
    }
}
