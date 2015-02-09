import javax.swing.*;
import java.util.ArrayList;

/*
* Clase Partida: Contiene todos los m�todos que analizan el estado de la partida y dan las pautas l�gicas para que se pueda jugar.
*/ 
  
public class Partida{

	//Datos miembro  .

	public int fila;
	public int columna;
	public int[][] mTablero;
  	public int turno;
  	public int nivel;
	public int fichas;
	private String usuario1;
	private String usuario2;
	public boolean cuatroRaya;
	public boolean empate;
	private Minimax minimax;

	//Definimos algunas variables que usaremos.
	private final static int rojas=1;
  	private final static int amarillas=2;
	private final static int hueco=0;
	
	/*
	* Constructor de la clase partida.
	* @param filas
	* @param columnas
	* @param niveles
	* @param usuario1
	* @param usuario2
	*/
	public Partida(int filas, int columnas, int niveles, String usuario1, String usuario2){

		//Indicamos la dimensi�n de la partida.
		fila=filas;
		columna=columnas;
		fichas=0;

		//Indicamos el nivel de dificultad.
		nivel=niveles;
		//Se indica el usuario que est� jugando.
		this.usuario1=usuario1;
		this.usuario2=usuario2;

		//Indicamos que el juego es nuevo y que empiezan las fichas rojas.
		turno=rojas;
		cuatroRaya=false;
		empate=false;

   		//Se crea un array de n filas por m columnas
		mTablero=new int[filas][columnas];
		//Marcamos las casillas a 0. Es decir, todavia no tiene ninguna ficha.
		for(int i=0;i<fila;i++){
      			for(int j=0; j<columna;j++){
      				mTablero[i][j]=hueco;
			}
		}


	}
		
	/*
	* M�todo que comprueba si estado del tablero. Devuelve true si no puede colocarse la ficha 
	* ya sea porque est� llena la columna o porque el juego ha terminado.
	* @param estaColumna
	* @return boolean
	*/
	public boolean tableroLleno(int estaColumna){
		
		//Si existe hueco en la columna seleccionada y no se ha terminado ya el juego.
		if(cuatroRaya==false && mTablero[0][estaColumna]==hueco) return false;
		else return true;
	}

	/*
	* M�todo para devolver la �ltima fila de una columna donde se dejar� caer la ficha.
	* @param estaColumna
	* @return fil
	*/
	public int ultimaFila(int estaColumna){
		int fil=fila;
		for(int i=fila-1;i>=0;i--){
			//En la primera fila vac�a que encontremos rompemos el bucle.
			if(mTablero[i][estaColumna]==hueco){
				fil=i; 
				break;
			}
		}
		return fil;
	}

	/*
	* M�todo que calcula las posibles jugadas a poder realizar
	* return jugadas
	*/

	public ArrayList<Integer> calcularJugadas(){
		//Creamos un arrayList de enteros y recorremos columna a columna si es posble colocar una ficha. 
		ArrayList<Integer> jugadas=new ArrayList<Integer>();
		for(int i=0; i<columna; i++){
			if(tableroLleno(i)==false){
				//Si es posible a�adimos dicha columna al array
				jugadas.add(i);
			}
		}
		if(jugadas==null){
			System.out.println("No hay mas jugadas");
		}
		return jugadas;
	}

	/*
	* M�todo que marca las casillas dependiendo del turno en el que nos encontremos
	* @param fila
	* @param columna
	*/
	public void marcarCasilla(int fila, int columna){
		mTablero[fila][columna]=turno;
	}
	
	/*
	* M�todo que desmarca las casillas dependiendo del turno en el que nos encontremos
	* @param fila
	* @param columna
	*/
	public void desmarcarCasilla(int fila, int columna){
		mTablero[fila][columna]=0;
	}
	
	
	/*
	* M�todo para cambiar el turno del jugador.
	*/
	public void cambiarTurno(){
		if (turno==rojas) turno=amarillas;
		else turno=rojas;
	}

	/*
	* M�todo para devolver el turno del jugador.
	* @return int
	*/
	public int devolverTurno(){
		return turno;
	}

	/*
	* M�todo para devolver el nombre del jugador1.
	* @return String
	*/
	public String devolverUsuario1(){
		return usuario1;
	}

	/*
	* M�todo para devolver el nombre del jugador2.
	* @return String
	*/
	public String devolverUsuario2(){
		return usuario2;
	}

	/*
	* M�todo para devolver el nivel.
	* @return int
	*/
	public int devolverNivel(){
		return nivel;
	}

	/*
	* M�todo para devolver, en modo string el nivel.
	* @return String
	*/
	public String devolverNivelInformacion(){
		if(nivel==0) return"Partida de dos jugadores";
		if(nivel==2) return "F�cil";
		if(nivel==4) return "Normal";
		else return "Dificil";
	}

	/*
	* M�todo para devolver el n�mero de filas.
	* @return int
	*/
	public int devolverFila(){
		return fila;
	}

	/*
	* M�todo para devolver el n�mero de columnas.
	* @return columna
	*/
	public int devolverColumna(){
		return columna;
	}

	/*
	* M�todo para comprobar si hay cuatro en raya, empate o nada.
	* @param fila
	* @param columna
	*/
	public void comprobarEstadoPartida(int fila,int columna){
		
		//aumentamos el valor de fichas colocadas +1
		fichas++;
		//Si no hay cuatro en raya seguimos jugando;
		if(compruebaCuatroRaya(fila,columna)==true) cuatroRaya();
		//Comprobamos si esa ficha provoca un lleno en el tablero sin haber 4 en raya (empate)
		else if(this.fila*this.columna==fichas) empate();
		else cambiarTurno();
	}

	/*
	* M�todo que identifica si la �ltima ficha en juego crea un cuatro en raya o no.
	* @param estaFila;
	* @param estacolumna;
	* @return boolean;
	*/
	public boolean compruebaCuatroRaya(int estaFila, int estaColumna){
		//Iniciamos los contadores a 0.	
		int izd=0;
		int dch=0;
		int abj=0;
		int arbi=0;
		int arbd=0;
		int abji=0;
		int abjd=0;
		//Iniciamos el n�mero de desplazamientos a 3.
		int desplaz=3;
		
		//Contamos cuantas fichas del mismo color hay seguidas a la que acabamos de colocar hay a la IZQUIERDA de la misma.
		//Evitamos salirnos del tablero por la izquierda.
		if(estaColumna<3) desplaz=estaColumna;
		//Evitamos la comprobaci�n en el borde izquierdo del tablero.
		if (estaColumna>0) {
			for(int i=1;i<=desplaz;i++){
				//Si hay una ficha del mismo color a la izquierda aumenta el contador izquierda un punto.
				//Si hubiera una ficha de diferente color que la original en el camino se interrumpe en bucle.
				if(mTablero[estaFila][estaColumna]!=mTablero[estaFila][estaColumna-i]) break;
				if(mTablero[estaFila][estaColumna]==mTablero[estaFila][estaColumna-i] && mTablero[estaFila][estaColumna-(i-1)]==mTablero[estaFila][estaColumna-i]) izd++;
			}
		}
	
		//Contamos cuantas fichas del mismo color hay seguidas a la que acabamos de colocar hay a la DERECHA de la misma.
		desplaz=3;
		//Evitamos salirnos del tablero por la derecha.
		if(estaColumna>(columna-4)) desplaz=(columna-1)-estaColumna;
		//Evitamos la comprobaci�n en el borde derecho del tablero.
		if(estaColumna<columna-1) {
			for(int i=1;i<=desplaz; i++){
				//Si hay una ficha del mismo color a la derecha aumenta el contador derecha un punto.
				if(mTablero[estaFila][estaColumna]!=mTablero[estaFila][estaColumna+i]) break;
				if(mTablero[estaFila][estaColumna]==mTablero[estaFila][estaColumna+i] && mTablero[estaFila][estaColumna+(i-1)]==mTablero[estaFila][estaColumna+i]) dch++;
			}
		
		}

		//Contamos cuantas fichas del mismo color hay seguidas a la que acabamos de colocar hay a la ABAJO de la misma.
		desplaz=3;
		//Evitamos salirnos del tablero por abajo.
		if(estaFila>fila-4) desplaz=(fila-1)-estaFila;
		//Evitamos la comprobaci�n en el borde inferior del tablero.
		if(estaFila<fila-1) {
			for(int i=1;i<=desplaz; i++){
				//Si hay una ficha del mismo colorabajo el contador abajo aumenta un punto.
				if(mTablero[estaFila][estaColumna]!=mTablero[estaFila+i][estaColumna]) break;
				if(mTablero[estaFila][estaColumna]==mTablero[estaFila+i][estaColumna] && mTablero[estaFila+(i-1)][estaColumna]==mTablero[estaFila+i][estaColumna]) abj++;
			}
		
		}

		//Contamos cuantas fichas del mismo color seguidas se situan en la diagonal ARRIBA IZQUIERDA de la ficha.
		desplaz=3;
		//Evitamos salirnos del tablero por arriba y por la izquierda.
		if(estaFila<3 || estaColumna<3){
			if(estaFila<estaColumna) desplaz=estaFila;
			else desplaz=estaColumna;
		}
		//Evitamos la comprobaci�n si nos encontramos en el borde superior o izquierdo del tablero.
		if(estaFila>0 && estaColumna>0) {
			for(int i=1; i<=desplaz; i++){
				//El contador arbi aumenta un punto si existe una ficha en su posici�n sup izq.
				if(mTablero[estaFila][estaColumna]!=mTablero[estaFila-i][estaColumna-i]) break;
				if(mTablero[estaFila][estaColumna]==mTablero[estaFila-i][estaColumna-i] && mTablero[estaFila-i+1][estaColumna-i+1]==mTablero[estaFila-i][estaColumna-i]) arbi++;
			}
		}

		//Contamos cuantas fichas del mismo color seguidas se situan en la diagonal ARRIBA DERECHA de la ficha.
		desplaz=3;
		//Evitamos salirnos del tablero por arriba y por la derecha.
		if(estaFila<3 || estaColumna>(columna-4)){
			if(estaFila<((columna-1)-estaColumna)) desplaz=estaFila;
			else desplaz=(columna-1)-estaColumna;
		}
		//Evitamos la comprobaci�n si nos encontramos en el borde superior o derecho del tablero.
		if(estaFila>0 && estaColumna<(columna-1)){
			for(int i=1; i<=desplaz; i++){
				//El contador arbd aumenta un punto si existe una ficha en su posici�n sup dch.
				if(mTablero[estaFila][estaColumna]!=mTablero[estaFila-i][estaColumna+i]) break;
				if(mTablero[estaFila][estaColumna]==mTablero[estaFila-i][estaColumna+i] && mTablero[estaFila-i+1][estaColumna+i-1]==mTablero[estaFila-i][estaColumna+i]) arbd++;
			}
		}
		//Contamos cuantas fichas del mismo color seguidas se situan en la diagonal ABAJO IZQUIERDA del la ficha.
		desplaz=3;
		//Evitamos salirnos del tablero por abajo y por la izquierda.
		if(estaFila>(fila-4) || estaColumna<3 ){
			if((fila-1)-estaFila<estaColumna) desplaz=(fila-1)-estaFila;
			else desplaz=estaColumna;
		}
		//Evitamos la comprobaci�n si nos encontramos en el borde inferior o izquierdo del tablero.
		if(estaFila<(fila-1) && estaColumna>0){		
			for(int i=1; i<=desplaz; i++){
				//El contador abji aumenta un punto si existe una ficha en su posici�n inf izq.
				if(mTablero[estaFila][estaColumna]!=mTablero[estaFila+i][estaColumna-i]) break;
				if(mTablero[estaFila][estaColumna]==mTablero[estaFila+i][estaColumna-i] && mTablero[estaFila+i-1][estaColumna-i+1]==mTablero[estaFila+i][estaColumna-i]) abji++;
			}
		}
		//Contamos cuantas fichas del mismo color seguidas se situan en la diagonal ABAJO DERECHA de la ficha.
		desplaz=3;
		//Evitamos salirnos del tablero por abajo y por la derecha.
		if(estaFila>(fila-4) || estaColumna>(columna-4) ){
			if((fila-1)-estaFila<((columna-1)-estaColumna)) desplaz=(fila-1)-estaFila;
			else desplaz=(columna-1)-estaColumna;
		}
		//Evitamos la comprobaci�n si nos encontramos en el borde inferior o derecho del tablero.
		if(estaFila<(fila-1) && estaColumna<(columna-1)){		
			for(int i=1; i<=desplaz; i++){
				//El contador abji aumenta un punto si existe una ficha en su posici�n inf izq.
				if(mTablero[estaFila][estaColumna]!=mTablero[estaFila+i][estaColumna+i]) break;
				if(mTablero[estaFila][estaColumna]==mTablero[estaFila+i][estaColumna+i] && mTablero[estaFila+i-1][estaColumna+i-1]==mTablero[estaFila+i][estaColumna+i]) abjd++;
			}
		}
		
		//Si se cumplen algunas de las siguientes condiciones tendremos un cuatro en raya. Marcamos las fichas y devolvemos el resultado.
		if(izd+dch>=3) {marcarCuatro(estaFila, estaColumna, izd, dch, 1); return true;}
		if(abj==3) {marcarCuatro(estaFila, estaColumna, abj, 0, 2); return true;}
		if(arbi+abjd>=3) {marcarCuatro(estaFila, estaColumna, arbi, abjd, 3); return true;}
		if(arbd+abji>=3) {marcarCuatro(estaFila, estaColumna, arbd, abji, 4); return true;}
		return false;
	}

	/*
	* M�todo que en el caso que exista 4 en raya crea otros c�rculos de color diferente y los situa en el mismo lugar donde debe estar dicha l�nea.
	* Toma la ficha que a cerrado el cuatro en raya y las dos posibles direcciones que puede tener la l�nea dependiendo de la forma que tenga �sta.
	* Forma indica si la linea es horizontal (1), vertical (2), obl�cua1 (3) u obl�cua2(4).
	* @param estaFila;
	* @param estaColumna;
	* @param dir1;
	* @param dir2;
	* @param forma;
	*/
	public void marcarCuatro(int estaFila, int estaColumna, int dir1, int dir2, int forma){

		//Forma 1. Horizontal. dir1 es hacia la izquierda y dir2 hacia la derecha.
		//Marcamos la ficha inicial, despues tantas fichas que est�n a su derecha y a su izquierda como indiquen la direcci�n.
		if (forma==1){
			for(int i=dir1; i>=0; i--){
				mTablero[estaFila][estaColumna-i]=3;
			}
			for(int i=dir2; i>0; i--){
				mTablero[estaFila][estaColumna+i]=3;
			}
		 }
		
		//Forma 2. Vertical. dir1 es hacia abajo. y dir2 es nula puesto que arriba no puede tener fichas.
		//Marcamos la ficha inicial y las que est�n situadas abajo.
		if (forma==2){
			for(int i=dir1; i>=0; i--){
				mTablero[estaFila+i][estaColumna]=3;
			}
		}

		//Forma 3. Obl�cua1. dir1 es arriba izquierda y dir2 abajo derecha.
		//Marcamos la ficha inicial y las que cumplan la direcci�n dada.
		if (forma==3){
			for(int i=dir1; i>=0; i--){
				mTablero[estaFila-i][estaColumna-i]=3;
			}
			for(int i=dir2; i>0; i--){
				mTablero[estaFila+i][estaColumna+i]=3;
			}
		}
		
		//Forma 4. Obl�cua2. dir1 es arriba derecha y dir2 abajo izquierda.
		//Marcamos la ficha inicial y las que cumplan la direcci�n dada.
		if(forma==4){
			for(int i=dir1; i>=0; i--){
				mTablero[estaFila-i][estaColumna+i]=3;
			}
			for(int i=dir2; i>0; i--){
				mTablero[estaFila+i][estaColumna-i]=3;
			}
		}
	}

	/*
	* M�todo que devuelve el valor de la casilla pedida.
	* 1=Ficha roja. 2=Ficha Amarilla. 3=Ficha que forma un cuatro en raya.
	* @param estaFila
	* @param estaColumna
	* @return mTablero[i][j]
	*/
	public int devolverValorCasilla(int estaFila, int estaColumna){
		return mTablero[estaFila][estaColumna];
	}
	
	/*
	* M�todo que devuelve el valor de cuatro en raya.
	* @return cuatroRaya;
	*/
	public boolean devolverCuatroRaya(){
		return cuatroRaya;
	}

	/*
	* M�todo que cambia el valor del cuatro en raya a verdadero.
	*/
	public void cuatroRaya(){
		cuatroRaya=true;
	}

	/*
	* M�todo que cambia el valor del empate a verdadero.
	*/
	public void empate(){
		empate=true;
	}

	/*
	* M�todo que devuelve el valor del empate
	* @return boolean empate
	*/
	public boolean devolverEmpate(){
		 return empate;
	}

	/*
	* metodo que nos copia el tablero para que no se vean reflejados los cambios en el tablero original
	* @return partidaCopia
	*/
	public Partida copiarPartida(){
		Partida partidaCopia;
		partidaCopia=new Partida(fila, columna, nivel, "uno", "dos");
		for(int i=0;i<fila;i++){
			for(int j=0; j<columna;j++){
				partidaCopia.mTablero[i][j]=mTablero[i][j];
			}
		}
		return partidaCopia;
	}

	/*
	* M�todo que llama al m�todo minimax de la clase Minimax.
	* @param estaParatida
	* @return int
	*/
	public int minimax(Partida estaPartida){
		minimax=new Minimax(estaPartida, estaPartida.devolverNivel());
		int min=minimax.mejorJugada();
		return min;
	}

	/*
	* M�todo de funci�n de evaluaci�n de partida
	* @param estaPartida
	* @return int
	*/
	public int funcionEvaluacion(Partida estaPartida){
		int punt1=estaPartida.analizarTablero(1);
		int punt2=estaPartida.analizarTablero(2);
		int punt=punt2+punt1;
		return punt;
	}

	/*
	* M�todo que examina el tablero y devuelve el valor estimado 
	* @param esteTurno
	* @return int
	*/
	public int analizarTablero(int esteTurno){
		int h4=0; 
		int v4=0;
		int ob4a=0;
		int ob4b=0;
		int total=h4+v4+ob4a+ob4b;
		int auxh=0;
		int auxv=0;
		int auxob=0;

		//Hace el recuento de la cantidad de cuatro en raya del turno requerido que existe en el tablero
		//Horizontales
		for(int j=0; j<fila; j++){
			for(int i=0; i<columna-4; i++){
				//Si hay una ficha del mismo color a la derecha aumenta el contador horizontal aumenta un punto.
				if(mTablero[j][i]!=mTablero[j][i+1]) auxh=0;
				if(mTablero[j][i]==mTablero[i][i+i] && mTablero[j][i]==esteTurno) auxh++;
				if(auxh==4) h4++;
			}
		}
		//Verticales
		for(int j=columna-1; j>0; j--){
			for(int i=0; i<fila; i++){
				//Si hay una ficha del mismo color arriba de la nuestra el contador vertical aumenta un punto
				if(mTablero[i][j]!=mTablero[i][j-1]) auxv=0;
				if(mTablero[i][j]==mTablero[i][j-1] && mTablero[i][j]==esteTurno) auxv++;
				if(auxv==4) v4++;
			}
		}
		return total;
	}

}