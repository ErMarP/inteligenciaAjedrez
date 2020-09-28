package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;
import java.util.HashMap;
import ajedrez.Tablero;
import ajedrez.piezas.Color;
import ajedrez.piezas.Posicion;
import ajedrez.piezas.Pieza;
import ajedrez.piezas.Peon;
import ajedrez.piezas.Dama;
import ajedrez.piezas.Alfil;
import ajedrez.piezas.Rey;
import ajedrez.piezas.Torre;
import jdk.jfr.Event;
import ajedrez.piezas.Caballo;

public class Main extends PApplet{

    private Tablero tablero;
    private HashMap<String, PImage> imagenes;
    private Pieza piezaSeleccionada;
	private boolean seleccionandoJugada;
	private boolean fin = false;
	private Pieza nueva;
	private Posicion cero;
    
    public static void main(String[] args) {
        PApplet.main("app.Main");
    }

    @Override
    public void settings() {
		size(1400, 800);
	}
	
	@Override
    public void setup() {
		try (var in = new ObjectInputStream(new FileInputStream("juego"))) {
			tablero = (Tablero) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			tablero = Tablero.obtenerInstancia();
		}
		tablero = Tablero.obtenerInstancia();
		cero = new Posicion(0, 3);
        imagenes = new HashMap<>();
        imagenes.put("PeonBLANCO1", loadImage(getClass().getResource("/Pbb.jpg").getPath()));
        imagenes.put("PeonNEGRO1", loadImage(getClass().getResource("/Pnb.jpg").getPath()));
        imagenes.put("PeonBLANCO2", loadImage(getClass().getResource("/Pbn.jpg").getPath()));
        imagenes.put("PeonNEGRO2", loadImage(getClass().getResource("/Pnn.jpg").getPath()));
        imagenes.put("DamaBLANCO1", loadImage(getClass().getResource("/Dbb.jpg").getPath()));
        imagenes.put("DamaNEGRO1", loadImage(getClass().getResource("/Dnb.jpg").getPath()));
        imagenes.put("DamaBLANCO2", loadImage(getClass().getResource("/Dbn.jpg").getPath()));
        imagenes.put("DamaNEGRO2", loadImage(getClass().getResource("/Dnn.jpg").getPath()));
        imagenes.put("ReyBLANCO1", loadImage(getClass().getResource("/Rbb.jpg").getPath()));
        imagenes.put("ReyNEGRO1", loadImage(getClass().getResource("/Rnb.jpg").getPath()));
        imagenes.put("ReyBLANCO2", loadImage(getClass().getResource("/Rbn.jpg").getPath()));
        imagenes.put("ReyNEGRO2", loadImage(getClass().getResource("/Rnn.jpg").getPath()));
        imagenes.put("CaballoBLANCO1", loadImage(getClass().getResource("/Cbb.jpg").getPath()));
        imagenes.put("CaballoNEGRO1", loadImage(getClass().getResource("/Cnb.jpg").getPath()));
        imagenes.put("CaballoBLANCO2", loadImage(getClass().getResource("/Cbn.jpg").getPath()));
        imagenes.put("CaballoNEGRO2", loadImage(getClass().getResource("/Cnn.jpg").getPath()));
        imagenes.put("AlfilBLANCO1", loadImage(getClass().getResource("/Abb.jpg").getPath()));
        imagenes.put("AlfilNEGRO1", loadImage(getClass().getResource("/Anb.jpg").getPath()));
        imagenes.put("AlfilBLANCO2", loadImage(getClass().getResource("/Abn.jpg").getPath()));
        imagenes.put("AlfilNEGRO2", loadImage(getClass().getResource("/Ann.jpg").getPath()));
        imagenes.put("TorreBLANCO1", loadImage(getClass().getResource("/Tbb.jpg").getPath()));
        imagenes.put("TorreNEGRO1", loadImage(getClass().getResource("/Tnb.jpg").getPath()));
        imagenes.put("TorreBLANCO2", loadImage(getClass().getResource("/Tbn.jpg").getPath()));
        imagenes.put("TorreNEGRO2", loadImage(getClass().getResource("/Tnn.jpg").getPath()));
		background(0xCCCCCC);
    }

    @Override
	public void draw() {
		image(loadImage(getClass().getResource("/Rbb.jpg").getPath()), 60, 100, 75, 75);
		image(loadImage(getClass().getResource("/Rnb.jpg").getPath()), 160, 100, 75, 75);
		image(loadImage(getClass().getResource("/Dbb.jpg").getPath()), 60, 200, 75, 75);
		image(loadImage(getClass().getResource("/Dnb.jpg").getPath()), 160, 200, 75, 75);
		image(loadImage(getClass().getResource("/Tbb.jpg").getPath()), 60, 300, 75, 75);
		image(loadImage(getClass().getResource("/Tnb.jpg").getPath()), 160, 300, 75, 75);
		image(loadImage(getClass().getResource("/Abb.jpg").getPath()), 60, 400, 75, 75);
		image(loadImage(getClass().getResource("/Anb.jpg").getPath()), 160, 400, 75, 75);
		image(loadImage(getClass().getResource("/Cbb.jpg").getPath()), 60, 500, 75, 75);
		image(loadImage(getClass().getResource("/Cnb.jpg").getPath()), 160, 500, 75, 75);
		image(loadImage(getClass().getResource("/Pbb.jpg").getPath()), 60, 600, 75, 75);
		image(loadImage(getClass().getResource("/Pnb.jpg").getPath()), 160, 600, 75, 75);

		fill(150, 100);
		for (int i = 100; i < 700; i += 100){
			for (int j = 0; j < 2; j ++){
				if(j == 0){
					rect(60, i, 75, 75);
				 }else{
					rect(160, i, 75, 75);
				 }
			}
		}

		fill(200, 100);
		for (int i = 150; i < 600; i += 100){
			rect(1150, i, 200, 70);
		}

		fill(200, 100);
		rect(60, 700, 75, 75);
		rect(160, 700, 75, 75);

		textSize(17);
		fill(0);
		text("BORRAR", 62, 743);
		text("REINICIO", 160, 743);
		text("GUARDAR POSICIÓN", 1165, 190);
		text("LEER POSICIÓN", 1180	, 290);

		for (int i = 100; i < 700; i += 100){
			for (int j = 0; j < 2; j ++){
				if(j == 0){
					if(mouseX >	 60 && mouseY > i && mouseX < 135 && mouseY < i + 75){
						fill(0, 100);
						rect(60, i, 75, 75);
					}
				 }else{
					if(mouseX >	 160 && mouseY > i && mouseX < 235 && mouseY < i + 75){
						fill(0, 100);
						rect(160, i, 75, 75);
					}
				 }
			}
		}
		for (int i = 150; i < 600; i += 100){
			if(mouseX >	 1150 && mouseY > i && mouseX < 1350 && mouseY < i + 70){
				fill(100, 100);
				rect(1150, i, 200, 70);
			}
		}


		if(mouseX >	 160 && mouseY > 700 && mouseX < 235 && mouseY < 775){
			fill(100, 100);
			rect(160, 700, 75, 75);
		}
		if(mouseX >	 60 && mouseY > 700 && mouseX < 135 && mouseY < 775){
			fill(100, 100);
			rect(60, 700, 75, 75);
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 3; j < 11; j++) {
				if ((i + j) % 2 == 0) {
					image(loadImage(getClass().getResource("/b.jpg").getPath()), j * (height / 8), i * (height / 8), 
					height / 8, height / 8);
				} else {
					image(loadImage(getClass().getResource("/n.jpg").getPath()), j * (height / 8), i * (height / 8), 
					height / 8, height / 8);
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 3; j < 11; j++) {	
				Pieza pieza = tablero.obtenerPieza(i, j);
				if (pieza != null) {
					 image(imagenes.get(pieza.getClass().getSimpleName() + pieza.obtenerColor() + ((i + j)%2 == 0 ? 1 : 2)),
						  j * (height / 8), i * (height / 8), 
						  height / 8, height / 8);
				}
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent event){
		if(mouseX >	 60 && mouseY > 0 && mouseX < 235 && mouseY < 75){
			
		}

		if(mouseX >	 60 && mouseY > 100 && mouseX < 135 && mouseY < 175){
			nueva = new Rey(Color.BLANCO, cero);
		}

		if(mouseX >	 160 && mouseY > 100 && mouseX < 235 && mouseY < 175){
			nueva = new Rey(Color.NEGRO, cero);
		}

		if(mouseX >	 60 && mouseY > 200 && mouseX < 135 && mouseY < 275){
			nueva = new Dama(Color.BLANCO, cero);
		}

		if(mouseX >	 160 && mouseY > 200 && mouseX < 235 && mouseY < 275){
			nueva = new Dama(Color.NEGRO, cero);
		}

		if(mouseX >	 60 && mouseY > 300 && mouseX < 135 && mouseY < 375){
			nueva = new Torre(Color.BLANCO, cero);
		}

		if(mouseX >	 160 && mouseY > 300 && mouseX < 235 && mouseY < 375){
			nueva = new Torre(Color.NEGRO, cero);
		}

		if(mouseX >	 60 && mouseY > 400 && mouseX < 135 && mouseY < 475){
			nueva = new Alfil(Color.BLANCO, cero);
		}

		if(mouseX >	 160 && mouseY > 400 && mouseX < 235 && mouseY < 475){
			nueva = new Alfil(Color.NEGRO, cero);
		}

		if(mouseX >	 60 && mouseY > 500 && mouseX < 135 && mouseY < 575){
			nueva = new Caballo(Color.BLANCO, cero);
		}

		if(mouseX >	 160 && mouseY > 500 && mouseX < 235 && mouseY < 575){
			nueva = new Caballo(Color.NEGRO, cero);
		}

		if(mouseX >	 60 && mouseY > 600 && mouseX < 135 && mouseY < 675){
			nueva = new Peon(Color.BLANCO, cero);
		}

		if(mouseX >	 160 && mouseY > 600 && mouseX < 235 && mouseY < 675){
			nueva = new Peon(Color.NEGRO, cero);
		}

		if(mouseX >	 60 && mouseY > 700 && mouseX < 135 && mouseY < 775){
			nueva = null;
		}

		if(mouseX >	 160 && mouseY > 700 && mouseX < 235 && mouseY < 775){
			for (int i = 0; i < 8; i ++){
				for (int j = 3; j < 11; j ++){
					try{
						tablero.asignarPieza(null, i, j);
					}catch(Exception e){}
				}
			}
		}

		if(mouseX > 1150 && mouseY > 150 && mouseX < 1350 && mouseY < 220){
			JFileChooser guardar = new JFileChooser();
			guardar.showSaveDialog(null);
			guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    		try {
				File archivo = guardar.getSelectedFile();
				FileWriter escribir = new FileWriter(archivo, true);
				escribir.write("!\"\"\"\"\"\"\"\"#\n");
				for (int i = 0; i < 8; i ++){
					escribir.write("$");
					for (int j = 3; j < 11; j ++){
						Pieza letra = tablero.obtenerPieza(i, j);
						if(letra == null){
							escribir.write(((i+j) % 2 == 0) ? " " : "+");
						}else{
							escribir.write(identificador(letra));
						}
						if(j == 10)
							escribir.write("%");
					}
					escribir.write("\n");
				}
				escribir.write("/(((((((()");
				escribir.close();
			} catch (NullPointerException ex) {
    		} catch (IOException ex) {
        		System.out.println("Error al guardar, en la salida");
    		}
		}

		if(mouseX > 1150 && mouseY > 250 && mouseX < 1350 && mouseY < 320){
			JFileChooser guardar = new JFileChooser();
			guardar.showOpenDialog(null);
			guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    		try {
				File archivo = guardar.getSelectedFile();
				FileReader fr = new FileReader(archivo);
				BufferedReader lectura = new BufferedReader(fr);
				lectura.readLine();
				for (int i = 0; i < 8; i ++){
					String linea = lectura.readLine().toLowerCase();
					linea = linea.substring(1);
					for (int j = 3; j < 11; j ++){
						char letra = linea.charAt(j - 3);
						tablero.asignarPieza(traductor(letra), i, j);
					}
				}
				lectura.close();
			} catch (NullPointerException ex) {
    		} catch (IOException ex) {
        		System.out.println("Error al leer");
    		}
		}

		if(mouseX > 300 && mouseY > 0 && mouseX < 1100 && mouseY < 800){
			int fila = event.getY() / 100;
    		int columna = event.getX() / 100;
			try{
				tablero.asignarPieza(null, fila, columna);
				tablero.asignarPieza(nueva.copia(), fila, columna);
			}catch(Exception e){}
		}
		redraw();
	}

	public String identificador(Pieza pieza){
		String id = "";
		if((pieza.obtenerPosicion().obtenerColumna() + pieza.obtenerPosicion().obtenerFila()) % 2 == 0){
			if(pieza.obtenerColor() == Color.BLANCO){
				if(pieza instanceof Rey){
					id += "k";
				}
				if(pieza instanceof Dama){
					id += "q";
				}
				if(pieza instanceof Torre){
					id += "r";
				}
				if(pieza instanceof Alfil){
					id += "b";
				}
				if(pieza instanceof Caballo){
					id += "n";
				}
				if(pieza instanceof Peon){
					id += "p";
				}
			}else{
				if(pieza instanceof Rey){
					id += "l";
				}
				if(pieza instanceof Dama){
					id += "w";
				}
				if(pieza instanceof Torre){
					id += "t";
				}
				if(pieza instanceof Alfil){
					id += "v";
				}
				if(pieza instanceof Caballo){
					id += "m";
				}
				if(pieza instanceof Peon){
					id += "o";
				}
			}
		}else{
			if(pieza.obtenerColor() == Color.BLANCO){
				if(pieza instanceof Rey){
					id += "K";
				}
				if(pieza instanceof Dama){
					id += "Q";
				}
				if(pieza instanceof Torre){
					id += "R";
				}
				if(pieza instanceof Alfil){
					id += "B";
				}
				if(pieza instanceof Caballo){
					id += "N";
				}
				if(pieza instanceof Peon){
					id += "P";
				}
			}else{
				if(pieza instanceof Rey){
					id += "L";
				}
				if(pieza instanceof Dama){
					id += "W";
				}
				if(pieza instanceof Torre){
					id += "T";
				}
				if(pieza instanceof Alfil){
					id += "V";
				}
				if(pieza instanceof Caballo){
					id += "M";
				}
				if(pieza instanceof Peon){
					id += "O";
				}
			}
		}
		return id;
	}

	public Pieza traductor(char id){
		Pieza traducida = null;
		if(id == 'k'){
			traducida = new Rey(Color.BLANCO, cero); 
		}
		if(id == 'l'){
			traducida = new Rey(Color.NEGRO, cero); 
		}
		if(id == 'q'){
			traducida = new Dama(Color.BLANCO, cero); 
		}
		if(id == 'w'){
			traducida = new Dama(Color.NEGRO, cero); 
		}
		if(id == 'r'){
			traducida = new Torre(Color.BLANCO, cero); 
		}
		if(id == 't'){
			traducida = new Torre(Color.NEGRO, cero); 
		}
		if(id == 'b'){
			traducida = new Alfil(Color.BLANCO, cero); 
		}
		if(id == 'v'){
			traducida = new Alfil(Color.NEGRO, cero); 
		}
		if(id == 'n'){
			traducida = new Caballo(Color.BLANCO, cero); 
		}
		if(id == 'm'){
			traducida = new Caballo(Color.NEGRO, cero); 
		}
		if(id == 'p'){
			traducida = new Peon(Color.BLANCO, cero); 
		}
		if(id == 'o'){
			traducida = new Peon(Color.NEGRO, cero); 
		}
		return traducida;
	}
}
