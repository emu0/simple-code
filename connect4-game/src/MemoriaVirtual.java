/*
* Calse MemoriaVirtual: Guarda y modifica los datos para ser usados mientras la aplicación sigue abierta.
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
		usuario2="Máquina";
	}
	
	/*
	* Método que cambia a nivel fácil el estado de la memoria
	*/
	public void facil(){
		nivel=2;
		usuario2="Máquina";
	}

	/*
	* Método que cambia a nivel normal el estado de la memoria
	*/
	public void normal(){
		nivel=4;
		usuario2="Máquina";
	}

	/*
	* Método que cambia a dificil el estado de la memoria
	*/
	public void dificil(){
		nivel=6;
		usuario2="Máquina";
	}

	/*
	* Método que marca como partida de dos jugadores
	*/
	public void noNivel(){
		nivel=0;
	}

	/*

	* Método que cambia el tamañao de tablero al tipo3 
	*/
	public void tam1(){
		filas=6;
		columnas=7;
	}

	/*

	* Método que cambia el tamañao de tablero al tipo3
	*/
	public void tam2(){
		filas=7;
		columnas=8;
	}

	/*
	* Método que cambia el tamañao de tablero al tipo3
	*/
	public void tam3(){
		filas=7;
		columnas=9;
	}

	/*
	* Método que cambia el tamaño del tablero al tipo4
	*/
	public void tam4(){
		filas=7;
		columnas=10;
	}

	/*
	* Método que cambia el tamaño personalizado
	* @param col
	* @param fil
	*/
	public void pers(int col, int fil){
		filas=fil;
		columnas=col;
	}

	/*
	* Método que cambia el usuario1
	* @param cambioUsuario
	*/
	public void usuario1(String cambioUsuario){
		usuario1=cambioUsuario;
	}
	
	/*
	* Método que cambia el usuario 2
	* @param CambioUsuario
	*/
	public void usuario2(String cambioUsuario){
		usuario2=cambioUsuario;
	}

	/*
	* Método que devulve el nivel
	* @return int
	*/
	public int devolverNivel(){
		return nivel;
	}
	
	/*
	* Método que devuelve el número de filas
	* @return int
	*/
	public int devolverFilas(){
		return filas;
	}

	/*
	* Método que devuelve el número de columnas
	* @return int
	*/
	public int devolverColumnas(){
		return columnas;
	}

	/*
	* Método estático que devuelve el valor del usuario1
	* @return String
	*/
	public static String devolverUsuario1(){
		return usuario1;
	}

	/*
	* Método estático que devuelve el valor del usuario2
	* @return String
	*/
	public static String devolverUsuario2(){
		return usuario2;
	}
}