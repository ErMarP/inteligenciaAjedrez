package ajedrez.piezas;

import java.util.List;
import java.util.LinkedList;

/**
 * Clase Dama que extiende de la clase Pieza y modela el comportamiento
 * de una Pieza(Dama)
 * 
 * @author Erick Martinez Piza
 * @version 1.0
 */
public class Dama extends Pieza {

    public Dama(Color color, Posicion posicion) {
        super(color, posicion);
    }

    @Override
    public List<Posicion> obtenerJugadasLegales() {
        var jugadas = new LinkedList<Posicion>();
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
        return new Dama(this.obtenerColor(), this.obtenerPosicion());
    }

}