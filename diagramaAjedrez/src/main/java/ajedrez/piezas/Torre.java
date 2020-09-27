package ajedrez.piezas;

import java.util.List;
import java.util.LinkedList;
import ajedrez.Tablero;

public class Torre extends Pieza {
    public Torre(Color color, Posicion posicion) {
        super(color, posicion);
    }

    @Override
    public List<Posicion> obtenerJugadasLegales() {
        LinkedList<Posicion> jugadas = new LinkedList<>();
        Tablero tablero = Tablero.obtenerInstancia();
        int fila = obtenerPosicion().obtenerFila(),
            columna = obtenerPosicion().obtenerColumna();
        for (int j = columna - 1; j >= 3; j--) {
            if (f(fila, j, jugadas)) {
                break;
            }
        }
        for (int j = columna + 1; j <= 10; j++) {
            if (f(fila, j, jugadas)) {
                break;
            }   
        }
        for (int i = fila - 1; i >= 0; i--) {
            if (f(i, columna, jugadas)) {
                break;
            }   
        }
        for (int i = fila + 1; i <= 7; i++) {
            if (f(i, columna, jugadas)) {
                break;
            }   
        }
        return jugadas;
    }

    public Pieza copia(){
        return new Torre(this.obtenerColor(), this.obtenerPosicion());
    }

    private boolean f(int i, int j, List<Posicion> lista) {
        Tablero tablero = Tablero.obtenerInstancia();
        if (tablero.obtenerPieza(i, j) == null || obtenerColor() != tablero.obtenerPieza(i, j).obtenerColor()) {
            lista.add(new Posicion(i, j));
        }
        return tablero.obtenerPieza(i, j) != null;
    }
}