package ajedrez.piezas;

import java.util.List;
import java.util.LinkedList;
import ajedrez.Tablero;


public class Rey extends Pieza {

    public Rey(Color color, Posicion posicion) {
        super(color, posicion);
    }

    @Override
    public List<Posicion> obtenerJugadasLegales() {
            LinkedList<Posicion> jugadas = new LinkedList<>();
            Tablero tablero = Tablero.obtenerInstancia();
            int fila = obtenerPosicion().obtenerFila(),
                columna = obtenerPosicion().obtenerColumna();
            if (fila - 1 >= 0 && columna - 1 >= 0) {
                if ((tablero.obtenerPieza(fila - 1, columna - 1) != null && tablero.obtenerPieza(fila - 1, columna - 1).obtenerColor() != obtenerColor()) || tablero.obtenerPieza(fila - 1, columna - 1) == null) {
                    jugadas.add(new Posicion(fila - 1, columna - 1));
                }
            }
            if (fila - 1 >= 0 && columna + 1 <= 10) {
                if ((tablero.obtenerPieza(fila - 1, columna + 1) != null && tablero.obtenerPieza(fila - 1, columna + 1).obtenerColor() != obtenerColor()) || tablero.obtenerPieza(fila - 1, columna + 1) == null) {
                    jugadas.add(new Posicion(fila - 1, columna + 1));
                }
            }
            if (fila + 1 <= 7 && columna + 1 <= 10) {
                if ((tablero.obtenerPieza(fila + 1, columna + 1) != null && tablero.obtenerPieza(fila + 1, columna + 1).obtenerColor() != obtenerColor()) || tablero.obtenerPieza(fila + 1, columna + 1) == null) {
                    jugadas.add(new Posicion(fila + 1, columna + 1));
                }
            }
            if (fila + 1 <= 7 && columna - 1 >= 3) {
                if ((tablero.obtenerPieza(fila + 1, columna - 1) != null && tablero.obtenerPieza(fila + 1, columna - 1).obtenerColor() != obtenerColor()) || tablero.obtenerPieza(fila + 1, columna - 1) == null) {
                    jugadas.add(new Posicion(fila + 1, columna - 1));
                }
            }
            if (columna + 1 <= 10) {
                if ((tablero.obtenerPieza(fila, columna + 1) != null && tablero.obtenerPieza(fila, columna + 1).obtenerColor() != obtenerColor()) || tablero.obtenerPieza(fila, columna + 1) == null) {
                    jugadas.add(new Posicion(fila, columna + 1));
                }
            }
            if (fila + 1 <= 7) {
                if ((tablero.obtenerPieza(fila + 1, columna) != null && tablero.obtenerPieza(fila + 1, columna).obtenerColor() != obtenerColor()) || tablero.obtenerPieza(fila + 1, columna) == null) {
                    jugadas.add(new Posicion(fila + 1, columna));
                }
            }
            if (columna - 1 >= 3) {
                if ((tablero.obtenerPieza(fila, columna - 1) != null && tablero.obtenerPieza(fila, columna - 1).obtenerColor() != obtenerColor()) || tablero.obtenerPieza(fila, columna - 1) == null) {
                    jugadas.add(new Posicion(fila, columna - 1));
                }
            }
            if (fila - 1 >= 0) {
                if ((tablero.obtenerPieza(fila - 1, columna) != null && tablero.obtenerPieza(fila - 1, columna).obtenerColor() != obtenerColor()) || tablero.obtenerPieza(fila - 1, columna) == null) {
                    jugadas.add(new Posicion(fila - 1, columna));
                }
            }
            return jugadas;
    }
    
    public Pieza copia(){
        return new Rey(this.obtenerColor(), this.obtenerPosicion());
    }
}
