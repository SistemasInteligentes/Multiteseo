
package unalcol.agents.examples.labyrinth.multeseo.simple;

public class Nodo {

    public final int NORTE = 0;
    public final int ORIENTE = 1;
    public final int SUR = 2;
    public final int OCCIDENTE = 3;
    private int x;
    private int y;
    private boolean visitado = false;
    private Nodo hijos[] = new Nodo[4];
    private Nodo predecesor;

    public Nodo() {
        this(0, 0);
    }

    public Nodo(int _x, int _y) {
        x = _x;
        y = _y;
        inicializar();
    }

    private void inicializar() {
        for (int x = 0; x < 4; x++) {
            getHijos()[x] = null;
        }
    }

    public void insertarHijo(int pos) {
        getHijos()[pos] = new Nodo(getX(), getY());
        if (pos == NORTE) {
            hijos[pos].setY(hijos[pos].getY() - 1);
        }
        if (pos == ORIENTE) {
            hijos[pos].setX(hijos[pos].getX() + 1);
        }
        if (pos == SUR) {
            hijos[pos].setY(hijos[pos].getY() + 1);
        }
        if (pos == OCCIDENTE) {
            hijos[pos].setX(hijos[pos].getX() - 1);
        }

    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the visitado
     */
    public boolean isVisitado() {
        return visitado;
    }

    /**
     * @param visitado the visitado to set
     */
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    /**
     * @return the hijos
     */
    public Nodo[] getHijos() {
        return hijos;
    }

    /**
     * @param hijos the hijos to set
     */
    public void setHijos(Nodo[] hijos) {
        this.hijos = hijos;
    }

    /**
     * @return the predecesor
     */
    public Nodo getPredecesor() {
        return predecesor;
    }

    /**
     * @param predecesor the predecesor to set
     */
    public void setPredecesor(Nodo predecesor) {
        this.predecesor = predecesor;
    }
   
}
