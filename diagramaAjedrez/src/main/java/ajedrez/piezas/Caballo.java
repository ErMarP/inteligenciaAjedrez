package ajedrez.piezas;

import java.util.List;
import java.util.LinkedList;

/**
 * Clase Caballo que extiende de la clase Pieza y modela el comportamiento
 * de una Pieza(Caballo)
 * 
 * @author Erick Martinez Piza
 * @version 1.0
 */
public class Caballo extends Pieza {

    public Caballo(Color color, Posicion posicion) {
        super(color, posicion);
    }

    @Override
    public List<Posicion> obtenerJugadasLegales() {
        LinkedList<Posicion> jugadas = new LinkedList<>();
        int fila = obtenerPosicion().obtenerFila(),
            columna = obtenerPosicion().obtenerColumna();
        if (fila - 2 >= 0 && columna + 1 <= 10) {
            posicionOcupada(fila - 2, columna + 1, jugadas);
        }
        if (fila - 2 >= 0 && columna - 1 >= 3) {
            posicionOcupada(fila - 2, columna - 1, jugadas);
        }
        if (fila + 2 <= 7 && columna + 1 <= 10) {
            posicionOcupada(fila + 2, columna + 1, jugadas);
        }
        if (fila + 2 <= 7 && columna - 1 >= 3) {
            posicionOcupada(fila + 2, columna - 1, jugadas);
        }
        if (fila - 1 >= 0 && columna + 2 <= 10) {
            posicionOcupada(fila - 1, columna + 2, jugadas);
        }
        if (fila - 1 >= 0 && columna - 2 >= 3) {
            posicionOcupada(fila - 1, columna - 2, jugadas);
        }
        if (fila + 1 <= 7 && columna + 2 <= 10) {
            posicionOcupada(fila + 1, columna + 2, jugadas);
        }
        if (fila + 1 <= 7 && columna - 2 >= 3) {
            posicionOcupada(fila + 1, columna - 2, jugadas);
        }
        return jugadas;
    }

    @Override
    public Pieza copia(){
        return new Caballo(this.obtenerColor(), this.obtenerPosicion());
    }
}
        