/*
* Calse MemoriaVirtual: Guarda y modifica los datos para ser usados mientras la aplicaci�n sigue abierta.
*/

public class MemoriaVirtual{

	//Datos miembro.
	private int filas;
	private int columnas;
	private int nivel;
	private int jugadores;
	static String usuario1;
	static String usuario2;
	
	/*
	* Constructor de la clase.
	*/

	public MemoriaVirtual(){
	
		//Dadas por defecto;
		filas=7;
		columnas=8;
		nivel=4;
		usuario1="Invitado Rojizo";
		usuario2="M�quina";
	}
	
	/*
	* M�todo que cambia a nivel f�cil el estado de la memoria
	*/
	public void facil(){
		nivel=2;
		usuario2="M�quina";
	}

	/*
	* M�todo que cambia a nivel normal el estado de la memoria
	*/
	public void normal(){
		nivel=4;
		usuario2="M�quina";
	}

	/*
	* M�todo que cambia a dificil el estado de la memoria
	*/
	public void dificil(){
		nivel=6;
		usuario2="M�quina";
	}

	/*
	* M�todo que marca como partida de dos jugadores
	*/
	public void noNivel(){
		nivel=0;
	}

	/*

	* M�todo que cambia el tama�ao de tablero al tipo3 
	*/
	public void tam1(){
		filas=6;
		columnas=7;
	}

	/*

	* M�todo que cambia el tama�ao de tablero al tipo3
	*/
	public void tam2(){
		filas=7;
		columnas=8;
	}

	/*
	* M�todo que cambia el tama�ao de tablero al tipo3
	*/
	public void tam3(){
		filas=7;
		columnas=9;
	}

	/*
	* M�todo que cambia el tama�o del tablero al tipo4
	*/
	public void tam4(){
		filas=7;
		columnas=10;
	}

	/*
	* M�todo que cambia el tama�o personalizado
	* @param col
	* @param fil
	*/
	public void pers(int col, int fil){
		filas=fil;
		columnas=col;
	}

	/*
	* M�todo que cambia el usuario1
	* @param cambioUsuario
	*/
	public void usuario1(String cambioUsuario){
		usuario1=cambioUsuario;
	}
	
	/*
	* M�todo que cambia el usuario 2
	* @param CambioUsuario
	*/
	public void usuario2(String cambioUsuario){
		usuario2=cambioUsuario;
	}

	/*
	* M�todo que devulve el nivel
	* @return int
	*/
	public int devolverNivel(){
		return nivel;
	}
	
	/*
	* M�todo que devuelve el n�mero de filas
	* @return int
	*/
	public int devolverFilas(){
		return filas;
	}

	/*
	* M�todo que devuelve el n�mero de columnas
	* @return int
	*/
	public int devolverColumnas(){
		return columnas;
	}

	/*
	* M�todo est�tico que devuelve el valor del usuario1
	* @return String
	*/
	public static String devolverUsuario1(){
		return usuario1;
	}

	/*
	* M�todo est�tico que devuelve el valor del usuario2
	* @return String
	*/
	public static String devolverUsuario2(){
		return usuario2;
	}
}