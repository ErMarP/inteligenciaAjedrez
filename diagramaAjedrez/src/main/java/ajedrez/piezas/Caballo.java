package ajedrez.piezas;

import java.util.List;
import java.util.LinkedList;
import ajedrez.Tablero;

public class Caballo extends Pieza {
    public Caballo(Color color, Posicion posicion) {
        super(color, posicion);
    }

    @Override
    public List<Posicion> obtenerJugadasLegales() {
        LinkedList<Posicion> jugadas = new LinkedList<>();
        Tablero tablero = Tablero.obtenerInstancia();
        int fila = obtenerPosicion().obtenerFila(),
            columna = obtenerPosicion().obtenerColumna();
        if (fila - 2 >= 0 && columna + 1 <= 10) {
            f(fila - 2, columna + 1, jugadas);
        }
        if (fila - 2 >= 0 && columna - 1 >= 3) {
            f(fila - 2, columna - 1, jugadas);
        }
        if (fila + 2 <= 7 && columna + 1 <= 10) {
            f(fila + 2, columna + 1, jugadas);
        }
        if (fila + 2 <= 7 && columna - 1 >= 3) {
            f(fila + 2, columna - 1, jugadas);
        }
        if (fila - 1 >= 0 && columna + 2 <= 10) {
            f(fila - 1, columna + 2, jugadas);
        }
        if (fila - 1 >= 0 && columna - 2 >= 3) {
            f(fila - 1, columna - 2, jugadas);
        }
        if (fila + 1 <= 7 && columna + 2 <= 10) {
            f(fila + 1, columna + 2, jugadas);
        }
        if (fila + 1 <= 7 && columna - 2 >= 3) {
            f(fila + 1, columna - 2, jugadas);
        }
        return jugadas;
    }

    public Pieza copia(){
        return new Caballo(this.obtenerColor(), this.obtenerPosicion());
    }
    
    private boolean f(int i, int j, List<Posicion> lista) {
        Tablero tablero = Tablero.obtenerInstancia();
        if (tablero.obtenerPieza(i, j) == null || obtenerColor() != tablero.obtenerPieza(i, j).obtenerColor()) {
            lista.add(new Posicion(i, j));
        }
        return tablero.obtenerPieza(i, j) != null;
    }
}
        