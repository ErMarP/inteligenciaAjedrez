package ajedrez.piezas;

/**
 * Clase Posicon que modela la Posicion de una pieza en el tablero
 * 
 * @author Erick Martinez Piza
 * @version 1.0
 */
public class Posicion{

    private int fila;
    private int columna;

    /**
     * Constructor por parámetros de una Posicion
     * 
     * @param fila -- la fila de la posicion
     * @param columna -- la columna de la posicion
     */
    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Metodo que regresa la fila de posicion
     */
    public int obtenerFila() {
        return fila;
    }

    /**
     * Metodo que regresa la columna de posicion
     */
    public int obtenerColumna() {
        return columna;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Posicion otra = (Posicion) obj;
        return fila == otra.fila && columna == otra.columna;
    }

    @Override
    public String toString() {
        return "(" + fila + ", " + columna + ")";
    }

}

