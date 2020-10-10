package ajedrez;

import java.util.List;
import java.util.LinkedList;
import ajedrez.piezas.Color;
import ajedrez.piezas.Posicion;
import ajedrez.piezas.Pieza;
import ajedrez.piezas.Dama;
import ajedrez.piezas.Peon;
import ajedrez.piezas.Torre;
import ajedrez.piezas.Rey;
import ajedrez.piezas.Alfil;
import ajedrez.piezas.Caballo;
import ajedrez.Casilla;

/**
 * Clase Tablero que modela el comportamiento de un 
 * Tablero
 * 
 * @author Erick Martinez Piza
 * @version 1.0
 */
public class Tablero{

    private static final Tablero INSTANCIA = new Tablero();

    private Casilla[][] casillas;
    private List<Pieza> piezasBlancasVivas;
    private List<Pieza> piezasNegrasVivas;
    private Color turno;

    /**
     * Constructor por omision de un Tablero de ajedrez 
     */
    private Tablero() {
        casillas = new Casilla[8][11];
        piezasBlancasVivas = new java.util.LinkedList<>();
        piezasNegrasVivas = new java.util.LinkedList<>();
        turno = Color.BLANCO;
        for (int i = 0; i < 8; i++) {
            for (int j = 3; j < 11; j++) {
                casillas[i][j] = new Casilla(i, j);
                if (i >= 2 && i <= 5) continue; // Casillas vacías
                
                Posicion posicion = new Posicion(i, j);
                Color color = (i >= 6) ? Color.BLANCO : Color.NEGRO;
                Pieza pieza = null;
                if (i == 1 || i == 6) {
                    pieza = new Peon(color, posicion);
                }                                       
                if (i == 0 && j == 6) {
                    pieza = new Dama(color, posicion);
                }
                if (i == 7 && j == 6) {
                    pieza = new Dama(color, posicion);
                }
                if (i == 0 && j == 7) {
                    pieza = new Rey(color, posicion);
                }
                if (i == 7 && j == 7) {
                    pieza = new Rey(color, posicion);
                }
                if (i == 7 && (j == 5 || j == 8)) {
                    pieza = new Alfil(color, posicion);
                }
                if (i == 0 && (j == 5 || j == 8)) {
                    pieza = new Alfil(color, posicion);
                }
                if (i == 7 && (j == 3 || j == 10)) {
                    pieza = new Torre(color, posicion);
                }
                if (i == 0 && (j == 3 || j == 10)) {
                    pieza = new Torre(color, posicion);
                }
                if (i == 7 && (j == 4 || j == 9)) {
                    pieza = new Caballo(color, posicion);
                }
                if (i == 0 && (j == 4 || j == 9)) {
                    pieza = new Caballo(color, posicion);
                }
                
                casillas[i][j].asignarPieza(pieza);
                (color == Color.BLANCO ? piezasBlancasVivas : piezasNegrasVivas).add(pieza);
            }
        }
    }

    /**
     * Metodo que devuelve una instancia de un Tablero
     * 
     * @return Tablero -- el Tablero inicial
     */
    public static Tablero obtenerInstancia() {
        return INSTANCIA;
    }

    /**
     * Metodo que devuelve el color de la casilla
     * 
     * @param i -- la fila de la Casilla
     * @param j -- la columna de la Casilla
     * 
     * @return int -- el color de la casilla: 
     */
    public int obtenerColor(int i, int j){
        return casillas[i][j].obtenerColor();
    }
    /**
     * Metodo que asigna el color de una Casilla
     * 
     * @param i -- la fila de la Casilla
     * @param j -- la columna de la Casilla
     * @param color -- el color de la casilla
     * 
     */
    public void asignarColor(int i, int j, int color) {
        casillas[i][j].asignarColor(color);
    }

    /**
     * Metodo que devuelve la lista de la casillas con flecha
     * 
     * @param i -- la fila de la Casilla
     * @param j -- la columna de la Casilla
     * 
     * @return LinkedList<Casilla> -- la lista de las casilla: 
     */
    public LinkedList<Casilla> obtenerFlecha(int i, int j){
        return casillas[i][j].obtenerFlecha();
    }
    /**
     * Metodo que asigna una casilla a la lista de flechas
     * 
     * @param i -- la fila de la Casilla
     * @param j -- la columna de la Casilla
     * @param fin -- la casilla a asignar
     * 
     */
    public void asignarFlecha(int i, int j, Casilla fin) {
        casillas[i][j].asignarFlecha(fin);
    }

    /**
     * Metodo que borra las flechas de una casilla
     * 
     * @param i -- la fila de la Casilla
     * @param j -- la columna de la Casilla
     */
    public void borrarFlechas(int i, int j){
        casillas[i][j].reiniciarFlecha();
    }

    /**
     * Metodo que obtiene el turno de la partida
     * 
     * @return Color -- el Color que tiene el turno siguiente
     */
    public Color obtenerTurno() {
        return turno;
    }

    /**
     * Metodo para obtener la casilla del tablero
     * 
     * @param i -- la fila de la Casilla
     * @param j -- la columna de la Casilla
     * 
     * @return Casilla -- la casilla que se solicitó
     */
    public Casilla obtenerCasilla(int i, int j){
        return casillas[i][j];
    }

    /**
     * Metodo que obtiene la Pieza de una Casilla
     * 
     * @param i -- la fila de la Casilla
     * @param j -- la columna de la Casilla
     * 
     * @return Pieza -- la Pieza en la Casilla dada, si no hay,
     * regresa null
     */
    public Pieza obtenerPieza(int i, int j) {
        return casillas[i][j].obtenerPieza();
    }

    /**
     * Metodo que asigna una pieza en el Tablero
     * 
     * @param pieza -- la Pieza a la que se le asigna la Posicion en el Tablero
     * @param fila -- la fila de la Casilla que se le va a asignar
     * @param columna -- la columna de la Casilla que se le va a asignar
     */
    public void asignarPieza(Pieza pieza, int fila, int columna) {
        if (pieza != null) {
            pieza.asignarPosicion(new Posicion(fila, columna));
        }
        casillas[fila][columna].asignarPieza(pieza);
    }

    /**
     * Metodo para mover una Pieza en el Tablero y cambio de turno
     * 
     * @param pieza -- la Pieza que seva a mover
     * @param fila -- la fila de la Casilla a la que la Pieza se va a mover
     * @param columna -- la columna de la Casilla a la que la Pieza se va a mover
     */
    public void moverPieza(Pieza pieza, int fila, int columna) {
        if (pieza == null
                || (fila == pieza.obtenerPosicion().obtenerFila()    
                        && columna == pieza.obtenerPosicion().obtenerColumna())
                || turno != pieza.obtenerColor()
                || !pieza.esJugadaLegal(fila, columna)) {
            return;
        }
        Pieza capturada = obtenerPieza(fila, columna);
        asignarPieza(null, pieza.obtenerPosicion().obtenerFila(), 
                     pieza.obtenerPosicion().obtenerColumna());
        asignarPieza(pieza, fila, columna);
        if (turno == Color.BLANCO) {
            piezasNegrasVivas.remove(capturada);
            turno = Color.NEGRO;
        } else {
            piezasBlancasVivas.remove(capturada);
            turno = Color.BLANCO;
        }
    }
}