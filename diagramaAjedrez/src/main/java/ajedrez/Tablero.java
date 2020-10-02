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

/**
 * Clase Tablero que modela el comportamiento de un 
 * Tablero
 * 
 * @author Erick Martinez Piza
 * @version 1.0
 */
public class Tablero{

    /**
     * Clase Casilla que modela el comportamiento de cada
     * Casilla del Tablero 
     * 
     * @author Erick Martinez Piza
     * @version 1.0
     */
    private class Casilla{

        private Pieza pieza;

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
         * Metodo que verifica si la Casilla no tiene 
         * ninguna Pieza
         * 
         * @return boolean -- True si no hay Pieza en la Casilla
         */
        public boolean esVacio() {
            return pieza == null;
        }

    }

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
                casillas[i][j] = new Casilla();
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
     * Metodo que obtiene el turno de la partida
     * 
     * @return Color -- el Color que tiene el turno siguiente
     */
    public Color obtenerTurno() {
        return turno;
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