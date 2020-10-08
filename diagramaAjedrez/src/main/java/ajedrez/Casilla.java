package ajedrez;

import java.util.LinkedList;
import ajedrez.piezas.Pieza;
import ajedrez.piezas.Posicion;
/**
 * Clase Casilla que modela el comportamiento de cada
 * Casilla del Tablero 
 * 
 * @author Erick Martinez Piza
 * @version 1.0
 */
public class Casilla{

    private Pieza pieza;
    private int color;
    private int colorFlecha; 
    private LinkedList<Casilla> flechas;
    private Posicion pos;

    /**
     * Cosntructor por parámetros de una Casilla
     * 
     * @param i -- la fila de la Casilla
     * @param j -- la columna de la Casilla
     */
    public Casilla(int i, int j){
        pieza = null;
        color = 0;
        colorFlecha = 0;
        flechas = new java.util.LinkedList<>();;
        pos = new Posicion(i, j);
    }

    /**
     * Metodo que regresa la posicion de la Pieza
     * 
     * @return Posicion -- la posicion de la Pieza
     */
    public Posicion obtenerPosicion() {
        return pos;
    }

    /**
     * Metodo que devuelve la Pieza de la Casilla
     * 
     * @return Pieza -- Pieza en la casilla 
     */
    public Pieza obtenerPieza() {
        return pieza;
    }

    /**
     * Metodo que asigna una Pieza a una Casilla
     * 
     * @param pieza -- la pieza que se va a asignar
     */
    public void asignarPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    /**
     * Metodo para obtener el color de la casilla
     * 
     * @return int -- el color de la casilla
     */
    public int obtenerColor(){
        return color;
    }

    /**
     * Metodo que asigna el color la casilla
     * 
     * @return int -- Color de la casilla: 0 - sin color
     */
    public void asignarColor(int color){
        this.color = color;
    }

    /**
     * Metodo para obtener el color de la flecha de la casilla
     * 
     * @return int -- el color de la casilla
     */
    public int obtenerColorFlecha(){
        return colorFlecha;
    }

    /**
     * Metodo que asigna el color de la flecha a la casilla
     * 
     * @return int -- Color de la casilla: 0 - sin color
     */
    public void asignarColorFlecha(int color){
        this.colorFlecha = color;
    }


    /**
     * Metodo que regresa la lista de casillas asignadas con flecha
     * 
     * @return LinkedListst<Casilla> -- la lista de casillas
     */
    public LinkedList<Casilla> obtenerFlecha(){
        return flechas;
    }

    /**
     * Metodo que asigna flechas a las casillas
     * 
     * @param fin -- Casilla donde termina la flecha
     */
    public void asignarFlecha(Casilla fin){
        flechas.add(fin);
    }
}