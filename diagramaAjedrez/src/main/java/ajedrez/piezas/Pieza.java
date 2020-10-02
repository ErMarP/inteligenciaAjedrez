package ajedrez.piezas;

import java.util.List;
import ajedrez.Tablero;

/**
 * Clase abstracta Pieza que modela el comportamiento de 
 * una Pieza
 * 
 * @author Erick Martinez Piza
 * @version 1.0
 */
public abstract class Pieza{

    private Color color;
    private Posicion posicion;

    /**
     * Constructor por parámetros de una Pieza
     * 
     * @param color -- el color dado a la pieza
     * @param posicion -- la posicion inicial de la pieza
     */
    public Pieza(Color color, Posicion posicion) {
        this.color = color;
        this.posicion = posicion;
    }
    
    /**
     * Metodo que obtiene las jugadas legales de una Pieza
     * 
     * @return List<Posicion> -- Lista de jugadas legales(posiciones)
     */
    public abstract List<Posicion> obtenerJugadasLegales();

    /**
     * Metodo que regresa una copia de la pieza
     * 
     * @return Pieza -- la nueva copia
     */
    public abstract Pieza copia();

    /**
     * Metodo que verifica si una jugada esta en la lista
     * de jugadas legales de una Pieza
     * 
     * @param fila -- fila de la casilla a revisar
     * @param columna -- columna de la casilla a revisar
     * 
     * @return boolean -- True si es una jugada legal
     */
    public boolean esJugadaLegal(int fila, int columna) {
        return obtenerJugadasLegales().contains(new Posicion(fila, columna));
    }

    /**
     * Metodo que regresa el color de la Pieza
     * 
     * @return Color -- el color de la Pieza
     */
    public Color obtenerColor() {
        return color;
    }

    /**
     * Metodo que regresa la posicion de la Pieza
     * 
     * @return Posicion -- la posicion de la Pieza
     */
    public Posicion obtenerPosicion() {
        return posicion;
    }

    /**
     * Metodo que asigan una posicion a una Pieza
     * 
     * @param posicion -- la nueva posicion para la Pieza
     */
    public void asignarPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    /**
     * Método que verifica si hay una Pieza en la posicion dada 
     * y agrega esa posicion a una lista si no hay Pieza.
     * 
     * @param fila -- fila de la casilla a revisar
     * @param columna -- columna de la casilla a revisar
     * @param lista -- la lista en donde se va a agragar la posicion
     * 
     * @return boolean -- True si hay una pieza en la posicion
     */
    public boolean posicionOcupada(int fila, int columna, List<Posicion> lista) {
        Tablero tablero = Tablero.obtenerInstancia();
        if (tablero.obtenerPieza(fila, columna) == null || obtenerColor() != tablero.obtenerPieza(fila, columna).obtenerColor()) {
            lista.add(new Posicion(fila, columna));
        }
        return tablero.obtenerPieza(fila, columna) != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pieza otra = (Pieza) obj;
        return color == otra.color && posicion.equals(otra.obtenerPosicion());
    }

}