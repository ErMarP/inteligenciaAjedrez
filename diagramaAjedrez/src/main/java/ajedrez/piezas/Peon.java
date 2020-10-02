package ajedrez.piezas;

import java.util.List;
import java.util.LinkedList;
import ajedrez.Tablero;

/**
 * Clase Peon que extiende de la clase Pieza y modela el comportamiento
 * de una Pieza(Peon)
 * 
 * @author Erick Martinez Piza
 * @version 1.0
 */
public class Peon extends Pieza {

    public Peon(Color color, Posicion posicion) {
        super(color, posicion);
    }

    @Override
    public List<Posicion> obtenerJugadasLegales() {
        var jugadas = new LinkedList<Posicion>();
        Tablero tablero = Tablero.obtenerInstancia();
        int fila = obtenerPosicion().obtenerFila(),
            columna = obtenerPosicion().obtenerColumna();
        if (obtenerColor() == Color.BLANCO) {
            if (fila == 6 && fila - 2 >= 0) {
                if (tablero.obtenerPieza(fila - 2, columna) == null && tablero.obtenerPieza(fila - 1, columna) == null) {
                    jugadas.add(new Posicion(fila - 2, columna));
                }
            }
            if (fila - 1 >= 0) {
                if (tablero.obtenerPieza(fila - 1, columna) == null) {
                    jugadas.add(new Posicion(fila - 1, columna));
                }
            }
            if (fila - 1 >= 0 && columna - 1 >= 3) {
                if (tablero.obtenerPieza(fila - 1, columna - 1) != null && tablero.obtenerPieza(fila - 1, columna - 1).obtenerColor() != obtenerColor()) {
                    jugadas.add(new Posicion(fila - 1, columna - 1));
                }
            }
            if (fila - 1 >= 0 && columna + 1 <= 10) {
                if (tablero.obtenerPieza(fila - 1, columna + 1) != null && tablero.obtenerPieza(fila - 1, columna + 1).obtenerColor() != obtenerColor()) {
                    jugadas.add(new Posicion(fila - 1, columna + 1));
                }
            }
        } else {
            if (fila == 1 && fila + 2 <= 7) {
                if (tablero.obtenerPieza(fila + 2, columna) == null && tablero.obtenerPieza(fila + 1, columna) == null) {
                    jugadas.add(new Posicion(fila + 2, columna));
                }
            }
            if (fila + 1 <= 7) {
                if (tablero.obtenerPieza(fila + 1, columna) == null) {
                    jugadas.add(new Posicion(fila + 1, columna));
                }
            }
            if (fila + 1 <= 7 && columna - 1 >= 3) {
                if (tablero.obtenerPieza(fila + 1, columna - 1) != null && tablero.obtenerPieza(fila + 1, columna - 1).obtenerColor() != obtenerColor()) {
                    jugadas.add(new Posicion(fila + 1, columna - 1));
                }
            }
            if (fila + 1 <= 7 && columna + 1 <= 10) {
                if (tablero.obtenerPieza(fila + 1, columna + 1) != null && tablero.obtenerPieza(fila + 1, columna + 1).obtenerColor() != obtenerColor()) {
                    jugadas.add(new Posicion(fila + 1, columna + 1));
                }
            }
        }
        return jugadas;
    }

    @Override
    public Pieza copia(){
        return new Peon(this.obtenerColor(), this.obtenerPosicion());
    }

}