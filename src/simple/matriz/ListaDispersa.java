package unalcol.agents.examples.labyrinth.multeseo.simple.matriz;

import java.io.Serializable;
import java.util.List;
import java.util.TreeMap;

public class ListaDispersa<T> implements Serializable {

    TreeMap<String, T> mapa = new TreeMap<>();

    public void insertar(T valor, short x, short y) {

        String a = x + "" + y;
        mapa.remove(a);
        mapa.put(a, valor);
    }

    public T leer(short x, short y) {
        String a = x + "" + y;
        T valor = (T) (mapa.get(a));
        return valor;
    }

    public boolean borrar(short x, short y) {

        String a = x + "" + y;
        T valor = (T) mapa.remove(a);
        return valor == null ? false : true;
    }
    /*
    
     private List filas;

     public ListaDispersa(int lado) {
     filas = new ArrayList(lado);

     for (int i = 0; i < lado; i++) {
     filas.add(new TreeMap());
     }
     }

     public void insertar(T valor, int x, int y) {

     ((TreeMap) filas.get(y)).put(new Integer(x), valor);
     }

     public T leer(int x, int y) {
     T valor = (T) ((TreeMap) filas.get(y)).get(new Integer(x));
     return valor;
     }

     public boolean borrar(int x, int y) {
     T valor = (T) ((TreeMap) filas.get(y)).remove(new Integer(x));
     return valor == null ? false : true;
     }*/
}
