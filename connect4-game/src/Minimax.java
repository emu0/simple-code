import java.util.ArrayList;
import java.util.Random;

/*
*Clase Minimax: Comprueba que jugada es la mejor para la máquina a aprtir de la situación actual de la partida.
*/ 
public class Minimax{

	//Datos miembro.

	public Partida partidaAsoc;
	int fila;
	int columna;
	int profundidadMaxima;

	public Minimax(Partida partida, int profundidad){

		profundidadMaxima=profundidad;
		partidaAsoc=partida;  
	}

	/*
	* Método que calcula cual es la mejor jugada para que gane la máquina. Utiliza un método auxiliar
	* @return int
	*/
	public int mejorJugada(){

		//Creamos variable locales
		Partida partidaCopiada;
		int mejorJugada=-1;
		int fil;
		int puntuacion;
		int mejorPuntuacion=Integer.MIN_VALUE;
		ArrayList<Integer> jugadas;

		//Calculamos las posibles jugadas
		jugadas=partidaAsoc.calcularJugadas();
		int profundidad=0;
		//Si va a ser la primera ficha es mejor situarla en el centro
		if (partidaAsoc.fichas==1){
			int val=partidaAsoc.fila;
			return val/2;
		}
		else{
			for(int jugada:jugadas){
				//Recorremos las jugadas posibles
				partidaCopiada=partidaAsoc.copiarPartida();
				partidaCopiada.turno=2;
				fil=partidaCopiada.ultimaFila(jugada);
				partidaCopiada.marcarCasilla(fil, jugada);
				
				//Llamamos al método auxiliar
				puntuacion=mejorJugadaApoyo(partidaCopiada, profundidad + 1);
				if(puntuacion>=mejorPuntuacion){
					mejorJugada=jugada;
					mejorPuntuacion=puntuacion;
				}
			}
			return mejorJugada;
		}
	}

	/**
	* Metodo recursivo de apoyo que devuelve la puntuacion de cada partida pasada com argumento
	* @param Partida estaPartida
	* @param int profundidad
	* @return puntuacion
	*/
	private int mejorJugadaApoyo(Partida laPartida, int estaProfundidad){

		//Caso Base
		if(estaProfundidad==profundidadMaxima){
			return laPartida.funcionEvaluacion(laPartida);
			
		}
		else{
			ArrayList<Integer> jugadas;
			Partida partidaCopia;
			jugadas=laPartida.calcularJugadas();
			int puntuacion;
			int puntuacionMin=Integer.MAX_VALUE;
			int puntuacionMax=Integer.MIN_VALUE;
			int porfundidad=estaProfundidad;
			int fil;
			
			//MIN
			if(estaProfundidad%2==0){
				for (int jugada:jugadas){
					partidaCopia=laPartida.copiarPartida();
					partidaCopia.turno=1;
					fil=partidaCopia.ultimaFila(jugada);
					partidaCopia.marcarCasilla(fil, jugada);
					partidaCopia.fichas++;
					puntuacion=mejorJugadaApoyo(partidaCopia, estaProfundidad+1);
                    			if (puntuacion<puntuacionMin){
						puntuacionMin=puntuacion;
					}
				}
				return puntuacionMin;
			}

			//MAX
			else{
				for(int jugada:jugadas){
					partidaCopia=laPartida.copiarPartida();
					laPartida.turno=2;
					fil=partidaCopia.ultimaFila(jugada);
					partidaCopia.marcarCasilla(fil, jugada);
					partidaCopia.fichas++;
					puntuacion=mejorJugadaApoyo(partidaCopia, estaProfundidad+1);
					if(puntuacion>puntuacionMax){
						puntuacionMax=puntuacion;
					}
					//laPartida.desmarcarCasilla(fil, jugada);
					//laPartida.fichas--;
				}
				return puntuacionMax;
			}
		}
	}

}