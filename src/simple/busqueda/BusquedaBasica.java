/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.labyrinth.multeseo.simple.busqueda;

import java.util.ArrayList;
import unalcol.agents.examples.labyrinth.multeseo.simple.Nodo;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author Familia Forero
 */
public class BusquedaBasica {

    ArrayList Camino = new ArrayList();
    Vector<Nodo> nodosARevisar = new Vector<>();
    Vector<Nodo> nodosRevisados = new Vector<>();

    public Vector<Nodo> busquedaYCamino(Nodo nodo, boolean isVisitado) {

        Nodo inicial = nodo;
        nodosARevisar.add(nodo);
        while (nodo.isVisitado() != isVisitado) {          
            
            if (nodosARevisar.size()==0) {return null;}
            nodosRevisados.add(nodo);
            nodo = nodosARevisar.get(0);
            nodosARevisar.remove(0);            
                      
            for (int i = 0; i < nodo.getHijos().length; i++) {
                if (nodo.getHijos()[i] != null) {
                    if (!nodosRevisados.contains(nodo.getHijos()[i]) && !nodosARevisar.contains(nodo.getHijos()[i])) {
                        nodo.getHijos()[i].setPredecesor(nodo);
                        nodosARevisar.add(nodo.getHijos()[i]);
                    }
                }

            }

           

        }

        return camino(inicial, nodo);
    }

    private Vector<Nodo> camino(Nodo nodoInicial, Nodo nodoFinal) {
        Vector<Nodo> n = new Vector<>();
        n.add(nodoFinal);

        while (!(nodoFinal.getX() == nodoInicial.getX() && nodoFinal.getY() == nodoInicial.getY())) {

            nodoFinal = nodoFinal.getPredecesor();
            n.add(0, nodoFinal);

        }

        return n;
    }
}
