import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.*;

/**
* Clase PanelTablero: Clase que crea el panel de juego y coloca las fichas.
* Deriva de la clase JPanel.
*/
class PanelTablero extends JPanel implements Serializable{

	//DATOS MIEMBRO
	//Constante para indicar el tamanno de la casilla (en puntos)
	public final static int dimensionCasilla=50;

	//Dato miembro para almacenar las filas y columnas del tablero
	public int filas;
	public int columnas;
	public int circulos4;

	//Array con las lineas de delimitacion de las cuadriculas
	private ArrayList<Line2D.Float> lineasDelimitadoras;
	
	//Array con los circulos
	private ArrayList<Ellipse2D.Float> circulos;

	//Información de partida y partida asociada.
	private InformacionPartida info;
	//Partida asociada al PanelTablero
	private Partida partida;

	//Escuchador de eventos para el panel del tablero
	private GestorRatonPanelTablero gestorEventosRaton;

	/**
	* Constructor de la clase
	* @param filas
	* @param columnas
	*/
	public PanelTablero(int filas, int columnas, int nivel, String usuario1, String usuario2){
		
		this.filas=filas;
		this.columnas=columnas;
		circulos4=0;
		
		//Creamos una partida asociada al panel y la información de la partida.
		partida=new Partida(filas, columnas, nivel, usuario1, usuario2);
		info=new InformacionPartida(partida);
		
		// Se crea el array de lineas.
		lineasDelimitadoras=new ArrayList<Line2D.Float>();
		// Se crea el array de circulos.
		circulos=new ArrayList<Ellipse2D.Float>();

		// Se fija la dimension.
		Dimension dimension=new Dimension(columnas*dimensionCasilla,filas*dimensionCasilla);
		setPreferredSize(dimension);

		// Se crean las lineas delimitadoras.
		crearLineasDelimitadoras();

		// Se crea el gestor de eventos.
		gestorEventosRaton=new GestorRatonPanelTablero(this);
		// Se agrega el gestor al panel.
		addMouseListener(gestorEventosRaton);
		
	}

	/**
	* Metodo para agregar circulo en una casilla
	* @param fila
	* @param columna
	*/
	public void agregarCirculo(int fila, int columna){
		
		Ellipse2D.Float circulo;
		// Se determina la coordenada superior izquierda en que se encuadrara el circulo.
		int xSupIzq=columna*dimensionCasilla+5;
		int ySupIzq=fila*dimensionCasilla+5;

		// Se crea el circulo
		circulo=new Ellipse2D.Float(xSupIzq,ySupIzq,dimensionCasilla-10,dimensionCasilla-10);

		// El circulo se agrega al array de circulos
		circulos.add(circulo);
		//Si esta última ficha provoca que un jugador gane o exista empate
		if (partida.devolverCuatroRaya()==true) {agregarCirculos4(); repaint(); mensajeGanadorFin(); return;}
		if(partida.devolverEmpate()==true) {repaint(); mensajeEmpateFin(); return;}
		// Se fuerza repintado
		repaint();
		
	}

	/*
	* Método que agrega 4 círculos de color diferente al array de círculos una vez se ha creado el 4 en raya.
	*/
	public void agregarCirculos4(){
		int v;
		//Recorremos todo el tablero en busca de las casillas marcadas
		for(int i=0; i<filas; i++){
			for(int j=0; j<columnas; j++){
				v=partida.devolverValorCasilla(i,j);
				if(v==3){
					//Cuando encontramos una casilla marcada, añadimos un circulo al array de círculos.
					Ellipse2D.Float circulo;
					int xSupIzq=j*dimensionCasilla+5;
					int ySupIzq=i*dimensionCasilla+5;
					circulo=new Ellipse2D.Float(xSupIzq+5,ySupIzq+5,dimensionCasilla-20,dimensionCasilla-20);
					circulos.add(circulo);
					circulos4++;
				 }
			}
		}
	}

	/**
	* Metodo paint para hacer el pintado
	* @param g
	*/
	public void paint(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(Color.BLUE);

		// Pintado de lineas delimitadoras.
		for(Line2D.Float linea: lineasDelimitadoras){
			g2.draw(linea);
		}
		
		// Se pintan los circulos
		Ellipse2D.Float circulo;

		//Dependiendo de la posición de los circulos en el Array corresponderán del Jugador Rojo o del jugador Amarillo.
		for(int i=0; i < circulos.size(); i++){
		
			//Jugador Rojo (Primer turno).
			if(i%2==0){
				g2.setColor(Color.RED);
				circulo=circulos.get(i);
				g2.fill(circulo);
			}
			//Jugador Amarillo (Segundo turno).
			else{
				g2.setColor(Color.YELLOW);
				circulo=circulos.get(i);
				g2.fill(circulo);
			}
			
		}
		if(partida.devolverCuatroRaya()==true){
			for(int i=1; i<=circulos4; i++){
				g2.setColor(Color.GREEN);
				circulo=circulos.get(circulos.size()-i);
				g2.fill(circulo);
			}		
		}
		info.refrescar();
	}

	/**
	* Metodo para crear las lineas a pintar
	*/
	private void crearLineasDelimitadoras(){
		Line2D.Float linea;
		int xp,yp,xl,yl;

		// Se crean las lineas verticales: tantas como columnas +1
		for(int i=0; i < columnas+1; i++){
			// Posicion de partida: xp valdra tanto como i*dimensionCasilla
			// yp valdra siempre 0
			// Posicion de llegada: xl vale igual que xp
			// yl valdra siempre numeroFilas*dimensionCasilla 
			xp=i*dimensionCasilla;
			yp=0;
			xl=xp;
			yl=filas*dimensionCasilla;
			linea=new Line2D.Float(xp,yp,xl,yl);

			// Se agrega la linea al array de lineas
			lineasDelimitadoras.add(linea);
		}
	}

	/*
	* Método que proporciona la información de la partida
	*/
	public InformacionPartida devolverInfo(){
		return info;
	}

	/*
	* Método que proporciona la partida
	*/
	public Partida devolverPartida(){
		return partida;
	}
	
	/*
	* Método para lanzar el mensaje de fin de juego por empate y asigna el empate al jugador.
	*/
	public void mensajeEmpateFin(){
		JOptionPane.showMessageDialog(null, "JUEGO EMPATADO.", null,JOptionPane.INFORMATION_MESSAGE);
		//Agrega la victoria al jugador.
		//Se comprueba que exista el jugador
		if(Usuario.comprobarUsuario(partida.devolverUsuario1())==true){
				if(partida.devolverNivel()==2) Usuario.masUnaEmpatadaFacil(partida.devolverUsuario1());
				else if(partida.devolverNivel()==4) Usuario.masUnaEmpatadaMedio(partida.devolverUsuario1());
				else if(partida.devolverNivel()==8) Usuario.masUnaEmpatadaDificil(partida.devolverUsuario1());
		}
	}

	/*
	* Método para lanzar el mensaje de fin de juego por victoria y asigna la victoria o derrota del jugador.
	*/
	public void mensajeGanadorFin(){
		//Comprueba el jugador que ha ganado.
		if(partida.devolverTurno()==1){
			//Gana jugador 1
			JOptionPane.showMessageDialog(null, "¡Cuatro en raya! ¡Gana "+partida.devolverUsuario1()+"!", "¡VICTORIA!", JOptionPane.INFORMATION_MESSAGE);
			//Agrega la victoria al jugador.
			//Se comprueba que exista el jugador
			if(Usuario.comprobarUsuario(partida.devolverUsuario1())==true){
				if(partida.devolverNivel()==2) Usuario.masUnaGanadaFacil(partida.devolverUsuario1());
				else if(partida.devolverNivel()==4) Usuario.masUnaGanadaMedio(partida.devolverUsuario1());
				else if(partida.devolverNivel()==8) Usuario.masUnaGanadaDificil(partida.devolverUsuario1());
			}
		}
		else{
			//Gana jugador 2 o máquina.
			if (partida.devolverNivel()==0) JOptionPane.showMessageDialog(null, "¡Cuatro en raya! ¡Gana "+partida.devolverUsuario2()+"!", "¡VICTORIA!", JOptionPane.INFORMATION_MESSAGE);
			else {
				JOptionPane.showMessageDialog(null, "¡Cuatro en raya! ¡Gana la máquina!");
				//Agrega la derrota al jugador
				//Se comprueba que exista el jugador
				if(Usuario.comprobarUsuario(partida.devolverUsuario1())==true){
					if(partida.devolverNivel()==2) Usuario.masUnaPerdidaFacil(partida.devolverUsuario1());
					else if(partida.devolverNivel()==4) Usuario.masUnaPerdidaMedio(partida.devolverUsuario1());
					else if(partida.devolverNivel()==8) Usuario.masUnaPerdidaDificil(partida.devolverUsuario1());
				}
			}
		}
	}

	/**
   	* Metodo estatico para guardar el PanelTablero mediante serialización.
	* @param PanelTablero panel
   	*/
  	public static void guardarPanel(PanelTablero panel){

		String nombreArchivo=new String("partidaguardada");
		try{
			FileOutputStream archivo=new FileOutputStream(nombreArchivo);
			ObjectOutputStream flujo=new ObjectOutputStream(archivo);
			flujo.writeObject(panel);
			flujo.close();
			archivo.close();
		}
		catch(Exception e){
			
      		}
  	}

	/**
	* Metodo estatico para leer objetos de disco
	* @return PanelTablero objetoLeido
	*/
	public static PanelTablero leerPanel(){
		String nombreArchivo=new String("partidaguardada");
		PanelTablero objetoLeido=null;
		try{
			FileInputStream archivo=new FileInputStream(nombreArchivo);
			ObjectInputStream flujo=new ObjectInputStream(archivo);
			objetoLeido=(PanelTablero)flujo.readObject();
			flujo.close();
  			archivo.close();
		}
		catch(Exception e){
			System.out.println("Ha ocurrido un error y el programa debe cerrarse.");
			System.exit(0);
		}
		return objetoLeido;
	}
	
	/**
	* Método stático para comprueba si existe un panel guardado.
	* @return boolean existe
	*/
	public static boolean comprobarPanel(){
		boolean existe;
		String nombreArchivo=new String("partidaguardada");
		PanelTablero objetoLeido=null;
		//Intenta leer el archivo con el nombre que sea pasado. Si puede leerlo es porque existe.
		try{
			FileInputStream archivo=new FileInputStream(nombreArchivo);
			ObjectInputStream flujo=new ObjectInputStream(archivo);
			objetoLeido=(PanelTablero)flujo.readObject();
			flujo.close();
  			archivo.close();
			existe=true;
		}
		//Si no puede leerlo significa que no está creado.
		catch(Exception e){
			existe=false;
		}
		return existe;
	}

}
