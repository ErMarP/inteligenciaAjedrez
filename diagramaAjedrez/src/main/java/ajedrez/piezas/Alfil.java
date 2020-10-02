package ajedrez.piezas;

import java.util.List;
import java.util.LinkedList;

/**
 * Clase Alfil que extiende de la clase Pieza y modela el comportamiento
 * de una Pieza(Alfil)
 * 
 * @author Erick Martinez Piza
 * @version 1.0
 */
public class Alfil extends Pieza {

    public Alfil(Color color, Posicion posicion) {
        super(color, posicion);
    }

    @Override
    public List<Posicion> obtenerJugadasLegales() {
        LinkedList<Posicion> jugadas = new LinkedList<>();
        int fila = obtenerPosicion().obtenerFila(),
            columna = obtenerPosicion().obtenerColumna();
        for (int i = fila - 1, j = columna + 1; i >= 0 && j <= 10; i--, j++) {
            if (posicionOcupada(i, j, jugadas)) {
                break;
            }   
        }
        for (int i = fila - 1, j = columna - 1; i >= 0 && j >= 3; i--, j--) {
            if (posicionOcupada(i, j, jugadas)) {
                break;
            }   
        }
        for (int i = fila + 1, j = columna + 1; i <= 7 && j <= 10; i++, j++) {
            if (posicionOcupada(i, j, jugadas)) {
                break;
            }   
        }
        for (int i = fila + 1, j = columna - 1; i <= 7 && j >= 3; i++, j--) {
            if (posicionOcupada(i, j, jugadas)) {
                break;
            }   
        }    
        return jugadas;
    }
    
    @Override
    public Pieza copia(){
        return new Alfil(this.obtenerColor(), this.obtenerPosicion());
    }
}
        