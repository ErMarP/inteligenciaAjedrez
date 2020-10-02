package ajedrez.piezas;

import java.util.List;
import java.util.LinkedList;

/**
 * Clase Rey que extiende de la clase Pieza y modela el comportamiento
 * de una Pieza(Rey)
 * 
 * @author Erick Martinez Piza
 * @version 1.0
 */
public class Rey extends Pieza {

    public Rey(Color color, Posicion posicion) {
        super(color, posicion);
    }

    @Override
    public List<Posicion> obtenerJugadasLegales() {
            LinkedList<Posicion> jugadas = new LinkedList<>();
            int fila = obtenerPosicion().obtenerFila(),
                columna = obtenerPosicion().obtenerColumna();
            if (fila - 1 >= 0 && columna - 1 >= 3) {
                posicionOcupada(fila - 1, columna - 1, jugadas);
            }
            if (fila - 1 >= 0 && columna + 1 <= 10) {
                posicionOcupada(fila - 1, columna + 1, jugadas);
            }
            if (fila + 1 <= 7 && columna + 1 <= 10) {
                posicionOcupada(fila + 1, columna + 1, jugadas);
            }
            if (fila + 1 <= 7 && columna - 1 >= 3) {
                posicionOcupada(fila + 1, columna - 1, jugadas);
            }
            if (columna + 1 <= 10) {
                posicionOcupada(fila, columna + 1, jugadas);
            }
            if (fila + 1 <= 7) {
                posicionOcupada(fila + 1, columna, jugadas);
            }
            if (columna - 1 >= 3) {
                posicionOcupada(fila, columna - 1, jugadas);
            }
            if (fila - 1 >= 0) {
                posicionOcupada(fila - 1, columna, jugadas);
            }
            return jugadas;
    }
    
    @Override
    public Pieza copia(){
        return new Rey(this.obtenerColor(), this.obtenerPosicion());
    }
}
