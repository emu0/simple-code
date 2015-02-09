import java.io.*;
public class Usuario implements Serializable{


	//Datos miembro.
	private String nombre;
	private int partidasGanadasF;
	private int partidasPerdidasF;
	private int partidasEmpatadasF;
	private int partidasGanadasM;
	private int partidasPerdidasM;
	private int partidasEmpatadasM;
	private int partidasGanadasD;
	private int partidasPerdidasD;
	private int partidasEmpatadasD;
	private Partida partida;
	

	//Constructor
	public Usuario(String nombre){
		this.nombre=nombre;
		partidasGanadasF=0;
		partidasPerdidasF=0;
		partidasEmpatadasF=0;
		partidasGanadasM=0;
		partidasPerdidasM=0;
		partidasEmpatadasM=0;
		partidasGanadasD=0;
		partidasPerdidasD=0;
		partidasEmpatadasD=0;
	}
	
	/*
	* Método que recupera el nombre del jugador
	* @retunr String
	*/
	public String getNombre(){
		return nombre;
	}

	/*
   	* Metodo estático para guardar los usuarios mediante serialización.
	* @param jugador
   	*/
  	public static void guardarUsuario(Usuario jugador){

		String nombreArchivo=jugador.nombre;
		try{
			//Abrimos flujo
			FileOutputStream archivo=new FileOutputStream(nombreArchivo);
			ObjectOutputStream flujo=new ObjectOutputStream(archivo);
			//Escribimos el objeto
			flujo.writeObject(jugador);
			//Cerramos flujo
			flujo.close();
			archivo.close();
		}
		catch(Exception e){
			System.out.println("Ha ocurrido un error y el programa debe cerrarse.");
			System.exit(0);
      		}
  	}

	/**
	* Metodo estático para leer objetos de disco
	* @param nombre
	* @return Usuario
	*/
	public static Usuario leerUsuario(String nombre){
		String nombreArchivo=nombre;
		Usuario objetoLeido=null;
		try{
			FileInputStream archivo=new FileInputStream(nombreArchivo);
			ObjectInputStream flujo=new ObjectInputStream(archivo);
			objetoLeido=(Usuario)flujo.readObject();
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
	* Método estático para comprueba si existe un usuario.
	* @param String
	* @return boolean
	*/
	public static boolean comprobarUsuario(String nombre){
		boolean existe;
		String nombreArchivo=nombre;
		Usuario objetoLeido=null;
		//Intenta leer el archivo con el nombre que sea pasado. Si puede leerlo es porque existe.
		try{
			FileInputStream archivo=new FileInputStream(nombreArchivo);
			ObjectInputStream flujo=new ObjectInputStream(archivo);
			objetoLeido=(Usuario)flujo.readObject();
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

	/*
	* Método estático que elimina un usuario guardado
	* @param nombre
	*/
	public static void borrarUsuario(String nombre){
			File archivo=new File(nombre);
			archivo.delete();
	}
	
	/*
	* Método estático que resetea los valores de un usuario
	* @param jugador
	*/
	public static void resetUsuario(String jugador){
		//Podemos tomar y inicializar uno a uno los valores de las partidas del jugador o podemos simplemente crear un usuario nuevo
		//y volver a guardarlo con el mismo nombre dado que el constructor crea las variables a cero por defecto.
		Usuario setJugador=new Usuario(jugador);
		guardarUsuario(setJugador);
	}

	/*
	* Método que devuelve un String con las estadísticas detalladas del jugador.
	* @param nombre
	* @retunr String
	*/
	public static String devolverEstadisticas(String nombre){
		//Leemos el objeto deseado
		Usuario jugadorGuardado=leerUsuario(nombre);
		
		//Tomamos los valores de las partidas para usarlos posteriormente en el mensaje
		int gan=jugadorGuardado.partidasGanadasF+jugadorGuardado.partidasGanadasM+jugadorGuardado.partidasGanadasD;
		int per=jugadorGuardado.partidasPerdidasF+jugadorGuardado.partidasPerdidasM+jugadorGuardado.partidasPerdidasD;
		int emp=jugadorGuardado.partidasEmpatadasF+jugadorGuardado.partidasEmpatadasF+jugadorGuardado.partidasEmpatadasF;
		int total=gan+per+emp;
		String mensaje=null;

		//Creamos lo mensajes
		if(total==0){mensaje=new String(nombre+" aún no ha jugado ninguna partida contra máquina.");}
		else {
			mensaje=new String("Partidas totales jugadas por "+nombre+": "+total+"\n"+gan+" victorias ("+(gan*100/total)+"%)\n"+per+" derrotas ("+(per*100/total)+"%)\n"+emp+" empates ("+(emp*100/total)+"%)");
		}

		return mensaje;
	}
	
	/*
	* Método que devuelve un String con las estadísticas detalladas del jugador.
	* @param nombre
	* @return String
	*/
	public static String devolverEstadisticasDetalle(String nombre){
		//Leemos el objeto deseado
		Usuario jugadorGuardado=leerUsuario(nombre);
		
		//Tomamos los valores de las partidas para usarlos posteriormente en el mensaje
		int ganF=jugadorGuardado.partidasGanadasF;
		int perF=jugadorGuardado.partidasPerdidasF;
		int empF=jugadorGuardado.partidasEmpatadasF;

		int ganM=jugadorGuardado.partidasGanadasM;
		int perM=jugadorGuardado.partidasPerdidasM;
		int empM=jugadorGuardado.partidasEmpatadasM;

		int ganD=jugadorGuardado.partidasGanadasD;
		int perD=jugadorGuardado.partidasPerdidasD;
		int empD=jugadorGuardado.partidasEmpatadasD;

		int totalF=ganF+perF+empF;
		int totalM=ganM+perM+empM;
		int totalD=ganD+perD+empD;

		String mensaje=null;
		String mensajeF=null;
		String mensajeM=null;
		String mensajeD=null;

		//Creamos un mensaje por cada nivel de dificultad
		if(totalF==0){mensajeF=new String("No se han jugado partidas en modo fácil.");}
		else{
			mensajeF=new String("Partidas jugadas en modo fácil: "+totalF+"\n"+ganF+" victorias ("+(ganF*100/totalF)+"%)\n"+perF+" derrotas ("+(perF*100/totalF)+"%)\n"+empF+" empates ("+(empF*100/totalF)+"%)");
		}

		if(totalM==0){mensajeM=new String("No se han jugado partidas en modo medio.");}
		else{
			mensajeM=new String("Partidas jugadas en modo medio: "+totalM+"\n"+ganM+" victorias ("+(ganM*100/totalM)+"%)\n"+perM+" derrotas ("+(perM*100/totalM)+"%)\n"+empM+" empates ("+(empM*100/totalM)+"%)");
		}

		if(totalD==0){mensajeD=new String("No se han jugado partidas en modo difícil.");}
		else{
			mensajeD=new String("Partidas jugadas en modo difícil: "+totalD+"\n"+ganD+" victorias ("+(ganD*100/totalD)+"%)\n"+perD+" derrotas ("+(perD*100/totalD)+"%)\n"+empD+" empates ("+(empD*100/totalD)+"%)");
		}
		
		//Creamos el mensaje definitivo
		mensaje=new String(mensajeF+"\n"+mensajeM+"\n"+mensajeD);
		return mensaje;
	}

	/*
	* Método para sumar una partida ganada en modo facil de un jugador 
	* @param nombre
	*/
	public static void masUnaGanadaFacil(String nombre){
		//Tomamos el usuario deseado, modificamos el valor y lo guardamos de nuevo.
		Usuario jugadorGuardado=leerUsuario(nombre);
		jugadorGuardado.partidasGanadasF++;
		guardarUsuario(jugadorGuardado);
	}

	/*
	* Método para sumar una partida perdida en modo facil de un jugador
	* @param nombre
	*/
	public static void masUnaPerdidaFacil(String nombre){
		//Tomamos el usuario deseado, modificamos el valor y lo guardamos de nuevo.
		Usuario jugadorGuardado=leerUsuario(nombre);
		jugadorGuardado.partidasPerdidasF++;
		guardarUsuario(jugadorGuardado);
	}

	/*
	* Método para sumar una partida empatada en modo facil de un jugador
	* @param nombre
	*/
	public static void masUnaEmpatadaFacil(String nombre){
		//Tomamos el usuario deseado, modificamos el valor y lo guardamos de nuevo.
		Usuario jugadorGuardado=leerUsuario(nombre);
		jugadorGuardado.partidasEmpatadasF++;
		guardarUsuario(jugadorGuardado);
	}

	/*
	* Método para sumar una partida ganada en modo medio de un jugador
	* @param nombre
	*/
	public static void masUnaGanadaMedio(String nombre){
		//Tomamos el usuario deseado, modificamos el valor y lo guardamos de nuevo.
		Usuario jugadorGuardado=leerUsuario(nombre);
		jugadorGuardado.partidasGanadasM++;
		guardarUsuario(jugadorGuardado);
	}

	/*
	* Método para sumar una partida perdida en modo medio de un jugador
	* @param nombre
	*/
	public static void masUnaPerdidaMedio(String nombre){
		//Tomamos el usuario deseado, modificamos el valor y lo guardamos de nuevo.
		Usuario jugadorGuardado=leerUsuario(nombre);
		jugadorGuardado.partidasPerdidasM++;
		guardarUsuario(jugadorGuardado);
	}

	/*
	* Método para sumar una partida empatada en modo medio de un jugador
	* @param nombre
	*/
	public static void masUnaEmpatadaMedio(String nombre){
		//Tomamos el usuario deseado, modificamos el valor y lo guardamos de nuevo.
		Usuario jugadorGuardado=leerUsuario(nombre);
		jugadorGuardado.partidasEmpatadasM++;
		guardarUsuario(jugadorGuardado);
	}

	/*
	* Método para sumar una partida ganada en modo dificil de un jugador
	* @param nombre
	*/
	public static void masUnaGanadaDificil(String nombre){
		//Tomamos el usuario deseado, modificamos el valor y lo guardamos de nuevo.
		Usuario jugadorGuardado=leerUsuario(nombre);
		jugadorGuardado.partidasGanadasD++;
		guardarUsuario(jugadorGuardado);
	}

	/*
	* Método para sumar una partida perdida en modo dificil de un jugador
		* @param nombre
	*/
	public static void masUnaPerdidaDificil(String nombre){
		//Tomamos el usuario deseado, modificamos el valor y lo guardamos de nuevo.
		Usuario jugadorGuardado=leerUsuario(nombre);
		jugadorGuardado.partidasPerdidasD++;
		guardarUsuario(jugadorGuardado);
	}

	/*
	* Método para sumar una partida empatada en modo dificil de un jugador
	* @nombre
	*/
	public static void masUnaEmpatadaDificil(String nombre){
		//Tomamos el usuario deseado, modificamos el valor y lo guardamos de nuevo.
		Usuario jugadorGuardado=leerUsuario(nombre);
		jugadorGuardado.partidasEmpatadasD++;
		guardarUsuario(jugadorGuardado);
	}

}