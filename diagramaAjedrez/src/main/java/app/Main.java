package app;

import java.util.HashMap;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;
import ajedrez.Tablero;
import ajedrez.Casilla;
import ajedrez.piezas.*;
import jdk.jfr.Event;

/**
 * Clase App que genera la interfaz gráfica y modela el comportamiento
 * de la aplicacion de ajedrez.
 * 
 * @author Erick Martinez Piza
 * @version 1.0
 */
public class Main extends PApplet{

    private Tablero tablero;
    private HashMap<String, PImage> imagenes;
    private Pieza piezaSeleccionada;
	private Pieza nueva;
	private boolean seleccionandoJugada;
	private boolean dibujar = false;
	private boolean inicio = false;
	private Posicion cero;
	private int a, b, c, d;
	private int color = 1;
	
	/**
     * Metodo main que corre la Aplicacion
     * 
     * @param args -- argumentos(no se toman en cuenta.)
     */
    public static void main(String[] args) {
        PApplet.main("app.Main");
    }

    @Override
    public void settings() {

		//Espacio que determina el tamaño de la ventana del programa

		size(1400, 800);
	}
	
	@Override
    public void setup() {
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
    }

    @Override
	public void draw() {
		cursor(ARROW);
		//Espacio que especifica el color del fondo

		background(0xff804800);

		//Espacio que especifica el grosor de las lineas y el color de los cuadrados de los botones

		strokeWeight(3);
		fill(150, 100);

		//Espacio donde se crean los cuadrados de los botones de las piezas

		for (int i = 100; i < 700; i += 100){
			for (int j = 0; j < 2; j ++){
				if(j == 0){
					rect(60, i, 75, 75);
				 }else{
					rect(160, i, 75, 75);
				 }
			}
		}

		//Espacio que carga las imagenes de las piezas en su respectivo boton

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

		//Espacio donde se crean los rectangulos de los botones de las opciones

		fill(255);
		for (int i = 150; i < 700; i += 100){
			rect(1150, i, 200, 70);
		}

		//Espacio donde se crea el rectangulo para el boton de "borrar"

		fill(255);
		rect(104, 700, 87, 75);

		//Espacio donde se especifica el color, tamaño, ubicacion del texto que aparece en pantalla

		textSize(50);
		fill(0xFFFFA500);
		text("AJEDREZ", 39, 50);
		text("CHESS", 1165, 50);

		textSize(30);
		fill(0xFFE3E4E5);
		text("PIEZAS", 97, 90);
		text("OPCIONES", 1170, 130);

		textSize(17);
		fill(0);
		text("BORRAR", 112, 743);
		text("NUEVA POSICION", 1178, 190);
		text("GUARDAR POSICIÓN", 1165, 290);
		text("LEER POSICIÓN", 1187 , 390);
		text("CAPTURAR POSICIÓN", 1163 , 490);
		text("JUGAR PARTIDA", 1187 , 590);
		text("PARAR PARTIDA", 1187 , 690);

		//Espacio donde se crea el efecto del boton de los cuadros de las piezas

		for (int i = 100; i < 700; i += 100){
			for (int j = 0; j < 2; j ++){
				if(j == 0){
					if(mouseX >	 60 && mouseY > i && mouseX < 135 && mouseY < i + 75){
						cursor(HAND);
						strokeWeight(6);
						fill(0, 100);
						rect(60, i, 75, 75);
					}
				 }else{
					if(mouseX >	 160 && mouseY > i && mouseX < 235 && mouseY < i + 75){
						cursor(HAND);
						strokeWeight(6);
						fill(0, 100);
						rect(160, i, 75, 75);
					}
				 }
			}
		}

		//Espacio donde se crea el efecto del boton del boton de "borrar"

		if(mouseX >	 104 && mouseY > 700 && mouseX < 191 && mouseY < 775){
			cursor(HAND);
			strokeWeight(6);
			fill(0, 100);
			rect(104, 700, 87, 75);
		}

		//Espacio donde se crea el efecto del boton de las opciones
		
		for (int i = 150; i < 700; i += 100){
			if(mouseX >	 1150 && mouseY > i && mouseX < 1350 && mouseY < i + 70){
				cursor(HAND);
				strokeWeight(6);
				fill(100, 100);
				rect(1150, i, 200, 70);
			}
		}

		//Espacio donde se insertan las imagenes de las casillas blancas y negreas del tablero

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

		//Espacio donde se insertan las imagenes de las piezas donde les 
		//correponde en el tablero dependiendo de la forma en que se usa el programa

		for (int i = 0; i < 8; i++) {
			for (int j = 3; j < 11; j++) {	
				Pieza pieza = tablero.obtenerPieza(i, j);
				if (pieza != null) {
					 image(imagenes.get(pieza.getClass().getSimpleName() + pieza.obtenerColor() + ((i + j)%2 == 0 ? 1 : 2)),
						  j * 100, i * 100, 100, 100);
				}
			}
		}

		//Espacio donde se colorean las casillas del color seleccionado

		for (int i = 0; i < 8; i++) {
			for (int j = 3; j < 11; j++) {	
				if(tablero.obtenerColor(i, j) == 1){
					strokeWeight(0);
					fill(0x80dd2424);
					rect(j * 100, i * 100, 100, 100);
				}		
				if(tablero.obtenerColor(i, j) == 2){
					strokeWeight(0);
					fill(0x805bc615);
					rect(j * 100, i * 100, 100, 100);
				}
				if(tablero.obtenerColor(i, j) == 3){
					strokeWeight(0);
					fill(0x8016b0c6);
					rect(j * 100, i * 100, 100, 100);
				}
			}
		}

		//Espacio donde se crean las flechas

		for (int i = 0; i < 8; i ++){
			for (int j = 3; j < 11; j ++){
				if(!tablero.obtenerFlecha(i, j).isEmpty()){
						for(Casilla f : tablero.obtenerFlecha(i, j)){
							int fila = (f.obtenerPosicion().obtenerFila() * 100) + 50,
								columna = (f.obtenerPosicion().obtenerColumna() * 100) + 50;
							if(tablero.obtenerCasilla(i, j).obtenerColorFlecha() == 1){
								flecha((j * 100) + 50, (i * 100) + 50, columna, fila, 0xffdd2424);
							}
							if(tablero.obtenerCasilla(i, j).obtenerColorFlecha() == 2){
								flecha((j * 100) + 50, (i * 100) + 50, columna, fila, 0xff5bc615);
							}
							if(tablero.obtenerCasilla(i, j).obtenerColorFlecha() == 3){
								flecha((j * 100) + 50, (i * 100) + 50, columna, fila, 0xff16b0c6);
							}
					}
				}
			}
		}

		//Espacio donde se especifica como se marcan las jugadas legales de una pieza en el tablero y que pieza esta seleccionada
		//unicamente ocurre cuando se empieza la partida

		if (piezaSeleccionada != null) {
			int fila = piezaSeleccionada.obtenerPosicion().obtenerFila(),
				columna = piezaSeleccionada.obtenerPosicion().obtenerColumna();
			stroke(0xffAD329F);
			strokeWeight(0);					
			for (Posicion pos: piezaSeleccionada.obtenerJugadasLegales()) {
				fill((pos.obtenerFila() + pos.obtenerColumna()) % 2 == 0 ? 0x60FF0000 : 0x60FF0000);
				circle((pos.obtenerColumna() * 100) + 50, (pos.obtenerFila() * 100) + 50, 45);	
			}
			stroke(0xff0000FF);
			strokeWeight(6);
			fill((fila + columna) % 2 == 0 ? 0x010000ff : 0x01000000);
			rect(columna * 100, fila * 100, 100, 100);
			stroke(0);
			strokeWeight(1);
		}

		/*if(dibujar){
			if(a != c || b != d){
				stroke(0xffdd2424);
				strokeWeight(15);
				line(b, a, d, c);
				fill(0xffdd2424);
				triangle(d, c, d, c, d, c);
				stroke(0);
				strokeWeight(1);
			}
		}*/
	}

	@Override
	public void mouseClicked(MouseEvent event){
		if(mouseButton == LEFT) {
			//Zona que permite insertar un rey blanco en el Tablero

			if(mouseX >	 60 && mouseY > 100 && mouseX < 135 && mouseY < 175){
				nueva = new Rey(Color.BLANCO, cero);
			}

			//Zona que permite insertar un rey negro en el Tablero

			if(mouseX >	 160 && mouseY > 100 && mouseX < 235 && mouseY < 175){
				nueva = new Rey(Color.NEGRO, cero);
			}

			//Zona que permite insertar una dama blanca en el Tablero

			if(mouseX >	 60 && mouseY > 200 && mouseX < 135 && mouseY < 275){
				nueva = new Dama(Color.BLANCO, cero);
			}

			//Zona que permite insertar una dama negra en el Tablero

			if(mouseX >	 160 && mouseY > 200 && mouseX < 235 && mouseY < 275){
				nueva = new Dama(Color.NEGRO, cero);
			}

			//Zona que permite insertar una torre blanca en el Tablero

			if(mouseX >	 60 && mouseY > 300 && mouseX < 135 && mouseY < 375){
				nueva = new Torre(Color.BLANCO, cero);
			}

			//Zona que permite insertar una torre negra en el Tablero

			if(mouseX >	 160 && mouseY > 300 && mouseX < 235 && mouseY < 375){
				nueva = new Torre(Color.NEGRO, cero);
			}

			//Zona que permite insertar un alfil blanco en el Tablero

			if(mouseX >	 60 && mouseY > 400 && mouseX < 135 && mouseY < 475){
				nueva = new Alfil(Color.BLANCO, cero);
			}

			//Zona que permite insertar un alfil negro en el Tablero

			if(mouseX >	 160 && mouseY > 400 && mouseX < 235 && mouseY < 475){
				nueva = new Alfil(Color.NEGRO, cero);
			}

			//Zona que permite insertar un caballo blanco en el Tablero

			if(mouseX >	 60 && mouseY > 500 && mouseX < 135 && mouseY < 575){
				nueva = new Caballo(Color.BLANCO, cero);
			}

			//Zona que permite insertar un caballo negro en el Tablero

			if(mouseX >	 160 && mouseY > 500 && mouseX < 235 && mouseY < 575){
				nueva = new Caballo(Color.NEGRO, cero);
			}

			//Zona que permite insertar un peon blanco en el Tablero

			if(mouseX >	 60 && mouseY > 600 && mouseX < 135 && mouseY < 675){
				nueva = new Peon(Color.BLANCO, cero);
			}

			//Zona que permite insertar un peon negro en el Tablero

			if(mouseX >	 160 && mouseY > 600 && mouseX < 235 && mouseY < 675){
				nueva = new Peon(Color.NEGRO, cero);
			}

			//Zona que permite borrar una pieza del Tablero

			if(mouseX >	 104 && mouseY > 700 && mouseX < 191 && mouseY < 775){
				nueva = null;
			}

			//Zona donde se quitan todas las piezas del tablero

			if(mouseX > 1150 && mouseY > 150 && mouseX < 1350 && mouseY < 220 && !inicio){
				for (int i = 0; i < 8; i ++){
					for (int j = 3; j < 11; j ++){
						try{
							tablero.asignarPieza(null, i, j);
						}catch(Exception e){}
					}
				}
			}

			//Zona donde se guarda el tablero en un archivo

			if(mouseX > 1150 && mouseY > 250 && mouseX < 1350 && mouseY < 320){
				guardarPosicion();
			}

			//Zona donde se lee un archivo y se representa en el tablero

			if(mouseX > 1150 && mouseY > 350 && mouseX < 1350 && mouseY < 420){
				lecturaPosicion();
			}
		
			//Zona donde se captura el tablero en una imagen

			if(mouseX > 1150 && mouseY > 450 && mouseX < 1350 && mouseY < 520){
				capturaPantalla();
			}

			//Zona donde se empieza la partida

			if(mouseX > 1150 && mouseY > 550 && mouseX < 1350 && mouseY < 620){
				inicio = true;
			}
		
			//Zona donde se detiene la partida

			if(mouseX > 1150 && mouseY > 650 && mouseX < 1350 && mouseY < 720){
				inicio = false;
			}

			//Zona donde se controla el tablero

			if(mouseX > 300 && mouseY > 0 && mouseX < 1100 && mouseY < 800){
				int fila = event.getY() / 100;
    			int columna = event.getX() / 100;	
				if(!inicio){
					try{
						tablero.asignarPieza(null, fila, columna);
						tablero.asignarPieza(nueva.copia(), fila, columna);
					}catch(Exception e){}
				}

				if(inicio){
					if (seleccionandoJugada) {
						tablero.moverPieza(piezaSeleccionada, fila, columna);
						seleccionandoJugada = false;
						piezaSeleccionada = null;
					} else {
						piezaSeleccionada = tablero.obtenerPieza(fila, columna);
						if (piezaSeleccionada == null || tablero.obtenerTurno() != piezaSeleccionada.obtenerColor()) {
							piezaSeleccionada = null;
						}
						seleccionandoJugada = piezaSeleccionada != null;
					}
				}
			}
		}else if(mouseButton == RIGHT){

			//Zona donde se cambia el color a una casilla
			
			if(mouseX > 300 && mouseY > 0 && mouseX < 1100 && mouseY < 800){
				int fila = event.getY() / 100;
				int columna = event.getX() / 100;
				if(tablero.obtenerColor(fila, columna) == color){
					tablero.asignarColor(fila, columna, 0);
				}else{
					tablero.asignarColor(fila, columna, color);	
				}			
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent event){
		if(mouseButton == RIGHT){
			int fila = event.getY() / 100;
			int columna = event.getX() / 100;
			a = fila;
			b = columna;
		}
	}

	@Override
	public void keyPressed(){
		if(key == 'b' || key == 'B')
			color = 0;	
		if(key == 'r' || key == 'R')
			color = 1;
		if(key == 'v' || key == 'V')
			color = 2;
		if(key == 'a' || key == 'A')
			color = 3;
	}

	@Override
	public void mouseReleased(MouseEvent event){
		if(mouseButton == RIGHT){

			if(mouseX > 300 && mouseY > 0 && mouseX < 1100 && mouseY < 800){
				int fila = event.getY() / 100;
				int columna = event.getX() / 100;
				if(a != fila || b != columna){
					if(tablero.obtenerFlecha(a, b).contains(tablero.obtenerCasilla(fila, columna))){
						int remover = tablero.obtenerFlecha(a, b).lastIndexOf(tablero.obtenerCasilla(fila, columna));
						tablero.obtenerFlecha(a, b).remove(remover);
					}else{
						tablero.asignarFlecha(a, b, tablero.obtenerCasilla(fila, columna));
						tablero.obtenerCasilla(a, b).asignarColorFlecha(color);
					}
				}
			}
		}
	}

	/**
	 * Metodo que guarda la Posicion del Tablero en un archivo txt
	 * en la parte asignada por el Usuario
	 */
	public void guardarPosicion(){
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

	/**
	 * Metodo que lee un arhivo txt con una Posicon valida, y la 
	 * dibuja en el Tablero del programa
	 */
	public void lecturaPosicion(){
		JFileChooser leer = new JFileChooser();
		leer.showOpenDialog(null);
		leer.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    	try {
			File archivo = leer.getSelectedFile();
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

	public void flecha(int x0,int y0,int x1,int y1, int color){
		double alfa = Math.atan2(y1 - y0, x1 - x0);
		stroke(color);
		strokeWeight(15);
		line(x0, y0, x1, y1);
		int k = 15;
		int xa =(int)(x1 - (k * Math.cos(alfa+1)));
		int ya =(int)(y1 - (k * Math.sin(alfa+1)));
		// Se dibuja un extremo de la dirección de la flecha.
		int xb =(int)(x1 - (k * Math.cos(alfa-1)));
		int yb =(int)(y1 - (k * Math.sin(alfa-1)));
		// Se dibuja el otro extremo de la dirección de la flecha.
		fill(color);
		triangle(x1, y1, xa, ya, xb, yb);
		//line(xa, ya, x1, y1);
		//line(xb, yb, x1, y1);
		//line(xb, yb, xa, ya);
		stroke(0);
		strokeWeight(1);
		}

	/**
	 * Metodo que captura el Tablero en una imagen png
	 */
	public void capturaPantalla(){
		JFileChooser guardar = new JFileChooser();
			guardar.showSaveDialog(null);
			guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			PImage captura = get(300, 0, 800, 800);
			Image image = captura.getImage();		
			try {
				File archivo = new File(guardar.getSelectedFile().getAbsolutePath());
				BufferedImage imagen = new BufferedImage(800, 800,BufferedImage.TYPE_INT_RGB);
				Graphics lienzo = imagen.getGraphics();
				lienzo.drawImage(image, 0, 0, null);
				ImageIO.write(imagen, "jpg", archivo);
			} catch (IOException e) {

			} catch (NullPointerException e){}
	}
	/**
	 * Metodo que identifica las Piezas y su Color con una letra
	 */
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

	/**
	 * Metodo que traduce un caracter a una Pieza con una Poscion y Color
	 */
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
