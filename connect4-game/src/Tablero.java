import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
* Clase Tablero: Permite montar el panel en una ventana. Acoplar� la barra del men� y el pie de ventana. Contiene el metodo main.
* Deriva de la clase JFrame para que se comporte como un marco de aplicacion
*/
class Tablero extends JFrame{

	//DATOS MIEMBRO.
	//Constantes para indicar el offset horizontal y vertical del panel contenido en el marco.
	final static int offsetHorizontal=10;
	final static int offsetVertical=30;
	//Panel sobre el que se pintar�.
	private PanelTablero panelTablero;
	//objeto de la calse MemoriaVirtual que almacenar� los datos de la partida actual.
	private MemoriaVirtual memoriaVirt;
	//objeto de la clase InformacionPArtida que indica datos de inter�s de la partida.
	private InformacionPartida info;
	//objeto de la clase Usuario
	private Usuario usuario;
	/**
	* Constructor de la clase: permite especificar la dimension.
	* @param filas
	* @param columnas
	*/
	public Tablero(int fila, int columna){

		//Se genera un panel de tablero con valores de dificultad y jugadores por defecto.
		panelTablero=new PanelTablero(fila, columna, 4, "Invitado Rojizo", "M�quina");
		//Se asocia la informaci�n de ese panel al tablero.
		info=panelTablero.devolverInfo();
		//Se crea el archivo de memoria Virtual.
		memoriaVirt=new MemoriaVirtual();		

		// Se agrega al marco el panel y su informaci�n.
		getContentPane().add(panelTablero);
		getContentPane().add(info, BorderLayout.SOUTH);
		info.refrescar();
		
		//Se crea la barra de men�.
		inicializarMenu();

		// Se fija el color de fondo
		//setBackground(Color.CYAN);
		// Se empaqueta todo.
		pack();
		// Se evita el que pueda ser redimensionada.
		setResizable(false);
		// Se hace visible
		setVisible(true);
		// Se hace que la aplicacion finalice al cerrar.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		

	/**
	* M�todo que crea la barra de men� y sus elementos.
	*/
	private void inicializarMenu(){   
		//Barra del men�. 
		MenuBar mbarra=new MenuBar();
    
	//Submen� Juego. Permite cambiar las formas del juego y otras opciones.
    
		Menu juego=new Menu("Juego");

    		//A�adimos la opci�n de nuevo juego.
		MenuItem nuevoJuego=juego.add(new MenuItem("Nuevo"));
		nuevoJuego.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				nuevoJuegoMenu(evt);
			}
		});
	
		//A�adimos la opci�n guardar la partida.
		MenuItem guardarJuego=juego.add(new MenuItem("Guardar partida"));
		guardarJuego.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				guardarJuegoMenu(evt);
			}
		});

		
		//A�adimos la opci�n reanudar la partida.
		MenuItem reanudarJuego=juego.add(new MenuItem("Reanudar partida"));
		reanudarJuego.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				reanudarJuegoMenu(evt);
			}
		});
	
		//Creamos una separaci�n.
		juego.addSeparator();

		//A�adimos la dificultad f�cil.
		MenuItem facil=juego.add(new MenuItem("F�cil"));
		facil.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				facilMenu(evt);
			}
		});
               
		//A�adimos la dificultad Normal (Usada por defecto).
		MenuItem normal=juego.add(new MenuItem("Normal"));
		normal.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				normalMenu(evt);
			}
		});      
	
		//A�adimos la dificultad dif�cil.
		MenuItem dificil=juego.add(new MenuItem("Dif�cil"));
		dificil.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				dificilMenu(evt);
			}
		});
	
		juego.addSeparator();

		//Tablero de tama�o 7x6.
		MenuItem tam1=juego.add(new MenuItem("7x6"));
		tam1.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				tam1Menu(evt);
			}
		});
	
		//Tablero de tama�o 8x7(usada por defecto).
		MenuItem tam2=juego.add(new MenuItem("8x7"));
		tam2.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				tam2Menu(evt);
			}
		});
	
		//Tablero de tama�o 9x7.
		MenuItem tam3=juego.add(new MenuItem("9x7"));
		tam3.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				tam3Menu(evt);
			}
		});
	
		//Tablero de tama�o 10x7.
		MenuItem tam4=juego.add(new MenuItem("10x7"));
		tam4.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				tam4Menu(evt);
			}
		});

		//Tablero de tama�o personalizado.
		MenuItem pers=juego.add(new MenuItem("Personalizado"));
		pers.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				persMenu(evt);
			}
		});
	
		juego.addSeparator();
		//Un jugador contra la m�quina (Usado por defecto).
		MenuItem unJugador=juego.add(new MenuItem("Un jugador"));
		unJugador.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent evt){
					unJugadorMenu(evt);
				}
			});    
	
		//Dos jugadores.
		MenuItem dosJugadores=juego.add( new MenuItem("Dos jugadores"));
		dosJugadores.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				dosJugadoresMenu(evt);
			}
		});
	
		juego.addSeparator();

		//A�adimos la opci�n de salir..
		MenuItem salir=juego.add(new MenuItem("Salir"));
		salir.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				salirMenu(evt);
			}
		});
	
		//A�adimos el submen� a la barra.
		mbarra.add(juego);
 
	//submen� Jugador. Permite gestionar las opciones de los jugadores.
		
		Menu jugador=new Menu("Jugador");
	
		//Cambiar jugador.
		MenuItem cambioJugador=jugador.add(new MenuItem("Cambio de jugador"));
		cambioJugador.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				cambioJugadorMenu(evt);
			}
		});
	
		jugador.addSeparator();
		
		//Nuevo jugador.
		MenuItem nuevoJugador=jugador.add(new MenuItem("Nuevo jugador"));
		nuevoJugador.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				nuevoJugadorMenu(evt);
			}
		});

		//Borrar jugador.
		MenuItem borrarJugador=jugador.add(new MenuItem("Eliminar jugador"));
		borrarJugador.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				eliminaJugadorMenu(evt);
			}
		});
	
		//Set jugador.
		MenuItem resetJugador=jugador.add(new MenuItem("Reset jugador"));
		resetJugador.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				resetJugadorMenu(evt);
			}
		});
	
		jugador.addSeparator();

		//Estad�sticas de jugador.
		MenuItem estadisticaJugador=jugador.add(new MenuItem("Mostrar estad�stica."));
		estadisticaJugador.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				estadisticaMenu(evt);
			}
		});
	
		//A�adimos el men� modo a la barra de men�s.
		mbarra.add(jugador);
		

	//Men� ayuda.Muestra la informaci�n referente al juego.
		
		Menu ayuda=new Menu("Ayuda");

		//Informaci�n de como se juega a Connect4.
		MenuItem temasAyuda=ayuda.add(new MenuItem("Temas de ayuda"));
		temasAyuda.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				temasAyudaMenu(evt);
			}
		});
	
		//Informaci�n de Connect4
		MenuItem acercaDe=ayuda.add(new MenuItem("Acerca de Connect4..."));
		acercaDe.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				acercaDeMenu(evt);
			}
		});
	
		mbarra.add(ayuda);

   
		setMenuBar(mbarra); 
		setTitle("Connect4");
	}

	/**
	* M�todo para iniciar un nuevo juego. 
	*/
	public void nuevoJuego(){
		
		//Tomamos el valor de la nueva partida a partir de los datos guardados en memoria.
		int f=memoriaVirt.devolverFilas();
		int c=memoriaVirt.devolverColumnas();
		int n=memoriaVirt.devolverNivel();
		String u1=memoriaVirt.devolverUsuario1();
		String u2=memoriaVirt.devolverUsuario2();
		
		//Eliminamos del Tablero el panel montado.
		getContentPane().remove(panelTablero);
		getContentPane().remove(info);
		pack();

		//Creamos un nuevo Panel (y con �l, una nueva partida).
		PanelTablero nuevoPanelTablero=new PanelTablero(f,c,n,u1,u2);
		info=nuevoPanelTablero.devolverInfo();

		//Asociamos al tablero el nuevo panel y su informaci�n y lo montamos.
		panelTablero=nuevoPanelTablero;
		getContentPane().add(panelTablero);
		getContentPane().add(info, BorderLayout.SOUTH);
		info.refrescar();
		pack();	
	}

	/**
	* M�todo para que se inicie un nuevo juego. No se cambia ninguna varible de memoria.
	*/
	private void nuevoJuegoMenu(java.awt.event.ActionEvent evt){	
		nuevoJuego();
	}

	/**
	* M�todo para que se guarde el juego actual. 
	*/
	private void guardarJuegoMenu(java.awt.event.ActionEvent evt){	

		//Comprobamos si existe una partida guardada
		boolean existe=PanelTablero.comprobarPanel();
		if (existe==true){
			//Pedimos confirmaci�n de sobreescritura si ya existe una
			int opc1=JOptionPane.showConfirmDialog(null, "Ya existe otra partida guardada �Desea sobreescribirla?", "Confirmaci�n de sobreescritura...", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opc1==0){
				//copiamos el objeto
				PanelTablero copiaPanelTablero=null;
				copiaPanelTablero=panelTablero;
				PanelTablero.guardarPanel(copiaPanelTablero);
			}
			//Si se cancela se sale.
			else return;
		}
		else{
			PanelTablero copiaPanelTablero=null;
			copiaPanelTablero=panelTablero;
			PanelTablero.guardarPanel(copiaPanelTablero);
		}
		int opc2=JOptionPane.showConfirmDialog(null,"Partida Guardada\n�Desea salir del juego?", "Partida Guardada", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		//Si eligen si
		if (opc2==0) {
			System.exit(0);
		}
	}
	
	/**
	* M�todo para que se reanude el juego guardado. 
	*/
	private void reanudarJuegoMenu(java.awt.event.ActionEvent evt){	
		PanelTablero nuevoPanelTablero;
		boolean existe=panelTablero.comprobarPanel();
		if (existe==false){
			JOptionPane.showMessageDialog(null, "No hay ninguna partida guardada.", null, JOptionPane.WARNING_MESSAGE);
			return;
		}
		else{
			nuevoPanelTablero=PanelTablero.leerPanel();
					
			//Eliminamos del Tablero el panel montado.
			getContentPane().remove(panelTablero);
			getContentPane().remove(info);
			pack();

			//Asignamos la tablero recuperado
			info=nuevoPanelTablero.devolverInfo();
	
			//Asociamos al tablero el nuevo panel y su informaci�n y lo montamos.
			panelTablero=nuevoPanelTablero;
			getContentPane().add(panelTablero);
			getContentPane().add(info, BorderLayout.SOUTH);
			info.refrescar();
			pack();	
		}
	}

	/**
	* M�todo para que se inicie un nuevo juego en modo f�cil.
	*/
	private void facilMenu(java.awt.event.ActionEvent evt){	
		memoriaVirt.facil();
		nuevoJuego();
	}

	/**
	* M�todo para que se inicie un nuevo juego en modo normal.
	*/
	private void normalMenu(java.awt.event.ActionEvent evt){	
		memoriaVirt.normal();
		nuevoJuego();
	}

	/**
	* M�todo para que se inicie un nuevo juego en modo dif�cil.
	*/

	private void dificilMenu(java.awt.event.ActionEvent evt){
		memoriaVirt.dificil();
		nuevoJuego();
	}

	/**
	* M�todo para que se inicie un nuevo juego en tama�o 7x6.
	*/
	private void tam1Menu(java.awt.event.ActionEvent evt){	
		memoriaVirt.tam1();
		nuevoJuego();
	}

	/**
	* M�todo para que se inicie un nuevo juego en tama�o 8x7.
	*/
	private void tam2Menu(java.awt.event.ActionEvent evt){	
		memoriaVirt.tam2();
		nuevoJuego();
	}

	/**
	* M�todo para que se inicie un nuevo juego en tama�o 9x7.
	*/
	private void tam3Menu(java.awt.event.ActionEvent evt){	
		memoriaVirt.tam3();
		nuevoJuego();
	}

	/**
	* M�todo para que se inicie un nuevo juego en tama�o 10x7.
	*/
	private void tam4Menu(java.awt.event.ActionEvent evt){	
		memoriaVirt.tam4();
		nuevoJuego();
	}

	/*
	* M�todo para que se inicie un nuevo juego en tama�o personalizado.
	*/

	private void persMenu(java.awt.event.ActionEvent evt){
		int col;
		int fil;
		//Existen algunas condiciones para el tama�o del tablero.
		//El tama�o m�ximo, por cuestiones de ajuste de pantalla ser� 15 FILAS x 25 COLUMNAS
		col=leerValorEntero("Introduzca el n�mero de columnas del tablero.");
		
		//Si existe alg�n error en la lectura se pide de nuevo el valor.
		if(col==-2 || col>25){
			do{
				col=leerValorEntero("Introduzca el n�mero de columnas del tablero. ENTERO MAYOR QUE CERO, MENOR QUE VEINTICINCO");
			}while(col==-2 || col>25);
		}
		//Si se cancela.
		if(col==-1) return;
		
		//Si el numero de columnas es menor a 4, obligatoriamente el tama�o de filas ha de ser 4 o mayor para poder jugar.
		if(col<4){
			do{
				fil=leerValorEntero("Introduzca el n�mero de filas del tablero. Este valor debe ser un entero entre cuatro y catorce.");
				//El bucle seguir� mientras el valor sea menor de 4, mayor que catorce o exista un error en la lectura
				}while(col==-2 || !(fil>4 && fil<14));
		}

		//Si no, el n�mero de filas puede ser tan peque�o como se quiera.
		else{
			fil=leerValorEntero("Introduzca el n�mero de filas del tablero.");
		}
		if(fil>14 || fil==-2){
			do{
				fil=leerValorEntero("Introduzca el n�mero de filas del tablero. ENTERO MAYOR QUE CERO, MENOR QUE CATORCE.");
				//El bucle seguir� mientras el valor sea mayor que 14 o exista un error en la lectura
			}while(fil==-2 || fil>14);
		}
		//Si se cancela.
		if(fil==-1) return;
		//Una vez tengamos los valores correctamente.
		else memoriaVirt.pers(col, fil);
		nuevoJuego();
	}

	/**
	* M�todo para que se inicie un nuevo juego contra la m�quina.
	*/
	private void unJugadorMenu(java.awt.event.ActionEvent evt){	
		memoriaVirt.normal();	
		nuevoJuego();
	}

	/**
	* M�todo para que se inicie un nuevo juego de dos jugadores.
	*/
	private void dosJugadoresMenu(java.awt.event.ActionEvent evt){	
		memoriaVirt.noNivel();
		memoriaVirt.usuario2("Invitado amarillento");
		nuevoJuego();
	}

	/*
	* M�todo para que se abandone el juego.
	*/
	private void salirMenu(java.awt.event.ActionEvent evt){                                           
		System.exit(0);
	}  
	

	/**
	* M�todo para cambiar el jugador. 
	*/
	private void cambioJugadorMenu(java.awt.event.ActionEvent evt){	
		String cadena1;
		String cadena2;
		
		//Identificamos si estamos ante una partida contra m�quina o no.
		if (memoriaVirt.devolverNivel()>0){
			
			//Pedidmos el nombre del jugador.
			do{
				cadena1=JOptionPane.showInputDialog("Introduzca el nombre del jugador.");
				//Si se cancela no se hace nada.
				if (cadena1==null) return;
				//Para evitar problemas no se aceptar� un campo vac�o.
			}while(cadena1.length()==0);

			memoriaVirt.usuario1(cadena1);
			//Se comprueba que el usuario exista.
			boolean existe=Usuario.comprobarUsuario(cadena1);
			//Si no existe, se da la posibilidad de crearlo.
			if(existe==false){
				int opc=JOptionPane.showConfirmDialog(null,"Jugador no encontrado en la base de datos �Desea a�adirlo?", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				//Si eligen crear el nuevo jugador se hace la llamada al m�todo que lo realiza.
				if (opc==0) {
					nuevoJugador(cadena1);	
					return;
				}
				else if(opc==1){
					JOptionPane.showMessageDialog(null, "El jugador "+cadena1+" tendr� calidad de invitado.\nLa partida se reiniciar�.", null, JOptionPane.INFORMATION_MESSAGE);
					nuevoJuego();
					return;
				}
				else return;
			}
		}
		//Si se juega en una partida dos personas.
		else{
			do{
				cadena1=JOptionPane.showInputDialog("Introduzca el nombre del jugador de fichas rojas.");
				//Si se cancela no se hace nada.
				if (cadena1==null) break;
				//No se aceptan campos vac�os.
			}while(cadena1.length()==0);
		
			do{
				cadena2=JOptionPane.showInputDialog("Introduzca el nombre del jugador de fichas amarillas.");
				//Si se cancela no se hace nada.
				if (cadena2==null) break;
				//No se aceptan campos vac�os.
			}while(cadena2.length()==0);
			
			//Si ambos se cancelan no pasar� nada. Si se cancela uno de ellos, se tomar� el usuario anterior que hubiera.
			if (cadena1==null && cadena2==null) return;
			if (cadena1==null) cadena1=memoriaVirt.devolverUsuario1();
			if (cadena2==null) cadena2=memoriaVirt.devolverUsuario2();
			//Se cambia.
			memoriaVirt.usuario1(cadena1);
			memoriaVirt.usuario2(cadena2);
			
		}

		//Se lanza el nuevo juego.
		JOptionPane.showMessageDialog(null, "La partida se reiniciar�.", null, JOptionPane.INFORMATION_MESSAGE);
		nuevoJuego();
		
	}

	/*
	* M�todo para crear un nuevo jugador y a�adirlo a la base de datos.
	* @param String nombre
	*/
	private void nuevoJugador(String nombre){
		
		//Comprobamos si existe en la base de datos
		boolean existe=Usuario.comprobarUsuario(nombre);
		//Si el jugador ya existe se manda un mensaje
		if (existe==true){
			JOptionPane.showMessageDialog(null, "Ya existe un jugador guardado con el nombre "+nombre+".", "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}
		usuario=new Usuario(nombre);
		Usuario.guardarUsuario(usuario);
		int opc=JOptionPane.showConfirmDialog(null,"Jugador "+nombre+" almacenado en la base de datos satisfactoriamente.\n�Desea empezar la partida con este jugador?(La partida se reiniciar�)", "Jugador almacenado.", JOptionPane.ERROR_MESSAGE,3);
		//Si eligen si
		if (opc==0) {
			//Se cambia el usuario que juega en memoria Virtual
			memoriaVirt.usuario1(nombre);
			//Se lanza el nuevo juego.
			nuevoJuego();
		}
	}
	
	/**
	* M�todo para crear un nuevo jugador.
	*/
	private void nuevoJugadorMenu(java.awt.event.ActionEvent evt){
		String cadena;
		do{
			cadena=JOptionPane.showInputDialog("Introduzca el nombre del jugador a crear.");
			//No se aceptan campos vac�os.
			//Si se cancela no se hace nada.
			if (cadena==null) return;
		}while(cadena.length()==0);
		nuevoJugador(cadena);
	}

	/*
	* M�tdo para borrar a un jugador de memoria
	*/
	private void eliminaJugadorMenu(java.awt.event.ActionEvent evt){
		String nombre;
		do{
			nombre=JOptionPane.showInputDialog("Introduzca el nombre del jugador a eliminar.");
			//No se aceptan campos vac�os.
			//Si se cancela no se hace nada.
			if (nombre==null) return;
		}while(nombre.length()==0);

		//Comprobamos que el jugador existe.
		boolean existe=Usuario.comprobarUsuario(nombre);
		//Si el jugador ya existe se manda un mensaje de confirmaci�n
		if (existe==true){
			int opc=JOptionPane.showConfirmDialog(null, "�Est� seguro de querer eliminar al jugador "+nombre+" ?", "Mensaje de confirmaci�n.", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
			if (opc==0){
				Usuario.borrarUsuario(nombre);
				JOptionPane.showMessageDialog(null, "Jugador "+nombre+" eliminado", null, JOptionPane.INFORMATION_MESSAGE);
			}
			//Si se elige no resetear.
			else return;
		}
		else JOptionPane.showMessageDialog(null, "Jugador "+nombre+" no encontrado.", null,JOptionPane.WARNING_MESSAGE);

	}

	/**
	* M�todo para resetear a un jugador.
	*/
	private void resetJugadorMenu(java.awt.event.ActionEvent evt){
		
		//Pedimos el nombre del usuario a resetear
		String nombre;
		do{
			nombre=JOptionPane.showInputDialog("Introduzca el nombre del jugador a resetear.");
			//No se aceptan campos vac�os.
			//Si se cancela no se hace nada.
			if (nombre==null) return;
		}while(nombre.length()==0);

		//Comprobamos que el jugador existe.
		boolean existe=Usuario.comprobarUsuario(nombre);
		//Si el jugador ya existe se manda un mensaje de confirmaci�n
		if (existe==true){
			int opc=JOptionPane.showConfirmDialog(null, "�Est� seguro de querer resetear los contadores de "+nombre+" ?", "Mensaje de confirmaci�n.", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
			if (opc==0){
				Usuario.resetUsuario(nombre);
				JOptionPane.showMessageDialog(null, "Contadores de "+nombre+" reseteados", null, JOptionPane.INFORMATION_MESSAGE);
			}
			//Si se elige no resetear.
			else return;
		}
		else JOptionPane.showMessageDialog(null, "Jugador "+nombre+" no encontrado.", null,JOptionPane.WARNING_MESSAGE);
	}

	/**
	* M�todo para mostrar las estad�sticas del jugador.
	*/
	private void estadisticaMenu(java.awt.event.ActionEvent evt){
		
		//Pedimos el nombre del usuario del cual queremos las estad�sticas
		String nombre;
		do{
			nombre=JOptionPane.showInputDialog("Introduzca el nombre del jugador cual quiere ver sus estad�sticas.");
			//No se aceptan campos vac�os.
			//Si se cancela no se hace nada.
			if (nombre==null) return;
		}while(nombre.length()==0);

		//Comprobamos que el jugador existe.
		boolean existe=Usuario.comprobarUsuario(nombre);
		//Si el jugador ya existe se manda un mensaje de confirmaci�n
		if (existe==true){
			String mensaje=Usuario.devolverEstadisticas(nombre);
			int opc=JOptionPane.showConfirmDialog(null, mensaje+"\n�Ver estad�sticas detalladas?", "Estad�sticas de "+nombre, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(opc==0){
				mensaje=Usuario.devolverEstadisticasDetalle(nombre);
				JOptionPane.showMessageDialog(null, mensaje, "Estad�sticas detalladas de "+nombre, JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else JOptionPane.showMessageDialog(null, "Jugador "+nombre+" no encontrado.", null,JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	* M�todo para lanzar la ayuda del juego.
	*/
	private void temasAyudaMenu(java.awt.event.ActionEvent evt){                              
		int opt=JOptionPane.showConfirmDialog(null, "Esto es un cuatro en raya de los de toda la vida de Dios."
		+"\nPara ganar la partida consiga crear una l�nea recta en cualquier"
		+"\ndirecci�n de 4 fichas del mismo color antes que su contrincante."
		+"\n\n\t�Suerte en la partida! :)\n\n�Desea ayuda detallada?","Ayuda",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if(opt==0){

			int opt2=JOptionPane.showConfirmDialog(null,
			"Usted podr� jugar un nuevo juego si selecciona /Juego/Nuevo"
			+"\nCon esta opci�n mantendr� el jugador, modo, nivel de dificultad"
			+"\ny tama�o del tablero de la �ltima partida jugada."
			+"\n\nPodr� guardar una partida en /Juego/Guardar_Partida y volver a"
			+"\n reanudarla exactamente igual en /Juego/Reanudar_Partida"
			+"\n\nEl siguiente tema de ayuda es referente Modos de juego. \n�Desea continuar?","Ayuda de Nuevo juego",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(opt2==0){

				int opt3=JOptionPane.showConfirmDialog(null, 
				"Existen 2 modos de juego, contra m�quina o dos usuarios humanos."
				+"\nEl juego contra m�quina dispone de tres niveles de dificultad: F�cil, normal y dif�cil."
				+"\nPodr� seleccionar tanto el modo de juego como la dificultad en la pesta�a /Juego"
				+"\nTambi�n podr� seleccionar el tama�o del tablero que desee."
				+"\nDesde un tama�o est�ndar, a un tama�o como m�ximo 14x25."
				+"\n\nEl siguiente tema de ayuda referente al Jugador\n�Desea continuar?","Ayuda de Modos de juego",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(opt3==0){
					JOptionPane.showMessageDialog(null,
				"Podr� crear su propia base de jugadores desde el menu Jugador."
				+"\nSiga las instrucciones en cada caso.", "Ayuda de jugador", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

	/**
	* M�todo para lanzar la la info de Connect4.
	*/
	private void acercaDeMenu(java.awt.event.ActionEvent evt){                                           
		JOptionPane.showMessageDialog(null, "Connect4 developed by Emu"+"\n(C) Granada 010.","Acerca de CONNECT4",JOptionPane.INFORMATION_MESSAGE);

	}

	/*
	* M�todo para leer un valor entero introducido en el sistema.
	* @param String mensaje
	* @return int valor
	*/
	private int leerValorEntero(String mensaje){
		int valor;
		String cadena=JOptionPane.showInputDialog(mensaje);
		//Si el valor introducido es null devuelve un valor codificado
		if(cadena==null) {valor=-1; return valor;}
		try{
			valor=Integer.parseInt(cadena);
		}
		catch(Exception e){
			//Si existe un problema el entero toma un valor codificado para indicar el error
			valor=-2;
		}
		//Queremos devolver un valor positivo, si el valor introducido es menor o igual que cero devolvemos error
		if (valor<1) return -2;
		return valor;
	}
	
	/**
	* M�todo main.
	*/
	public static void main(String args[]){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Tablero tablero=new Tablero(7,8);
			}
		});
	}
  
}
