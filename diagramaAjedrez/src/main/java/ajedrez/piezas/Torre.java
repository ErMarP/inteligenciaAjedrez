package ajedrez.piezas;

import java.util.List;
import java.util.LinkedList;

/**
 * Clase Torre que extiende de la clase Pieza y modela el comportamiento
 * de una Pieza(Torre)
 * 
 * @author Erick Martinez Piza
 * @version 1.0
 */
public class Torre extends Pieza {

    public Torre(Color color, Posicion posicion) {
        super(color, posicion);
    }

    @Override
    public List<Posicion> obtenerJugadasLegales() {
        LinkedList<Posicion> jugadas = new LinkedList<>();
        int fila = obtenerPosicion().obtenerFila(),
            columna = obtenerPosicion().obtenerColumna();
        for (int j = columna - 1; j >= 3; j--) {
            if (posicionOcupada(fila, j, jugadas)) {
                break;
            }
        }
        for (int j = columna + 1; j <= 10; j++) {
            if (posicionOcupada(fila, j, jugadas)) {
                break;
            }   
        }
        for (int i = fila - 1; i >= 0; i--) {
            if (posicionOcupada(i, columna, jugadas)) {
                break;
            }   
        }
        for (int i = fila + 1; i <= 7; i++) {
            if (posicionOcupada(i, columna, jugadas)) {
                break;
            }   
        }
        return jugadas;
    }

    @Override
    public Pieza copia(){
        return new Torre(this.obtenerColor(), this.obtenerPosicion());
    }
}