package ajedrez.piezas;

import java.util.List;
import java.util.LinkedList;
import ajedrez.Tablero;

public class Alfil extends Pieza {
    public Alfil(Color color, Posicion posicion) {
        super(color, posicion);
    }

    @Override
    public List<Posicion> obtenerJugadasLegales() {
        LinkedList<Posicion> jugadas = new LinkedList<>();
        Tablero tablero = Tablero.obtenerInstancia();
        int fila = obtenerPosicion().obtenerFila(),
            columna = obtenerPosicion().obtenerColumna();
        for (int i = fila - 1, j = columna + 1; i >= 0 && j <= 10; i--, j++) {
            if (f(i, j, jugadas)) {
                break;
            }   
        }
        for (int i = fila - 1, j = columna - 1; i >= 0 && j >= 3; i--, j--) {
            if (f(i, j, jugadas)) {
                break;
            }   
        }
        for (int i = fila + 1, j = columna + 1; i <= 7 && j <= 10; i++, j++) {
            if (f(i, j, jugadas)) {
                break;
            }   
        }
        for (int i = fila + 1, j = columna - 1; i <= 7 && j >= 3; i++, j--) {
            if (f(i, j, jugadas)) {
                break;
            }   
        }    
        return jugadas;
    }
    
    private boolean f(int i, int j, List<Posicion> lista) {
        Tablero tablero = Tablero.obtenerInstancia();
        if (tablero.obtenerPieza(i, j) == null || obtenerColor() != tablero.obtenerPieza(i, j).obtenerColor()) {
            lista.add(new Posicion(i, j));
        }
        return tablero.obtenerPieza(i, j) != null;
    }
}
        