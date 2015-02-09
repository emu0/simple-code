import java.awt.*;
import java.awt.event.*;


public class GestorRatonPanelTablero implements MouseListener{
	
	//DATOS MIEMBRO.
	//Panel controlado.
	private PanelTablero panelControlado;
	//Partida asociada al panel.
	private Partida partida;

	/**
	* Constructor de la clase
	*/
	public GestorRatonPanelTablero(PanelTablero panelControlado){
		this.panelControlado=panelControlado;
		this.partida=panelControlado.devolverPartida();
	}

	/**
	* Metodos a implementar por obligacion del contrato con MouseListener
	* @param MouseEvent evento
	*/
	public void mouseClicked(MouseEvent evento){
	
		// Determinamos donde se ha pulsado
		int posx=evento.getX();
		int posy=evento.getY();
		int fila=posy/PanelTablero.dimensionCasilla;
		int columna=posx/PanelTablero.dimensionCasilla;
		

		//Se comprueba que sucede en la casilla clicada y se actua en consecuencia
		//Si la partida es de dos jugadores.
		if(partida.devolverNivel()==0){
			
			//Se comprueba si puede ponerse la ficha en la columna seleccionada.
			boolean lleno=partida.tableroLleno(columna);

			//Añadimos la ficha en la última fila libre de la columna seleccionada.
			if (lleno==false){
				int filaCorrespondida=partida.ultimaFila(columna);
				
				//Marcamos la casilla en la que se añadirá la casilla
				partida.marcarCasilla(filaCorrespondida, columna);

				//Comprobamos el estado de la partida despues del último movimiento.
				partida.comprobarEstadoPartida(filaCorrespondida, columna);

				// Se llama al metodo agregarCirculo del panel
				panelControlado.agregarCirculo(filaCorrespondida,columna);
			}
		}
		//Si es de un solo jugador.
		else{
			//Si es turno del jugador humano se sigue el procedimiento normal.

			if(partida.devolverTurno()==1){
				//Se comprueba si puede ponerse la ficha en la columna seleccionada.
				boolean lleno=partida.tableroLleno(columna);
	
				//Añadimos la ficha en la última fila libre de la columna seleccionada.
				if (lleno==false){
					int filaCorrespondida=partida.ultimaFila(columna);

					//Marcamos la casilla en la que se añadirá la casilla
					partida.marcarCasilla(filaCorrespondida, columna);

					//Comprobamos el estado de la partida despues del último movimiento.
					partida.comprobarEstadoPartida(filaCorrespondida, columna);

					// Se llama al metodo agregarCirculo del panel
					panelControlado.agregarCirculo(filaCorrespondida,columna);
				}
			}
			//Si es turno de la máquina no se calcula su movimiento hasta que se clicka el panel.
			else {
				//Se calcula la col correspondida 
				int colCorrespondida=partida.minimax(partida);
				//Se comprueba si puede ponerse la ficha en la columna seleccionada.
				boolean lleno=partida.tableroLleno(colCorrespondida);
	
				//Añadimos la ficha en la última fila libre de la columna seleccionada.
				if (lleno==false){
					int filaCorrespondida=partida.ultimaFila(colCorrespondida);
				
					//Marcamos la casilla en la que se añadirá la casilla
					partida.marcarCasilla(filaCorrespondida, colCorrespondida);

					//Comprobamos el estado de la partida despues del último movimiento.
					partida.comprobarEstadoPartida(filaCorrespondida, colCorrespondida);

					// Se llama al metodo agregarCirculo del panel
					panelControlado.agregarCirculo(filaCorrespondida,colCorrespondida);
				}	
			}
		}
  	}

	public void mouseEntered(MouseEvent evento){}
	public void mouseExited(MouseEvent evento){}
	public void mousePressed(MouseEvent evento){}
	public void mouseReleased(MouseEvent evento){}
}
