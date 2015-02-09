import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/*
* Clase InformacionPartida: Nos indicará la información del transcurso del juego a tiempo real.
*/
public class InformacionPartida extends JPanel {
	
	//Datos miembro
	JTextArea turno;
	JTextArea modo;
	JTextArea nivel;
	JTextArea usuario;
	Partida partidaInfo;

	/**
	* Constructor de la clase que recibe como argumento el tablero.
	* @param esteTablero
	*/
	public InformacionPartida(Partida estaPartidaInfo){
      
        	//Inicializamos el tablero y las diferentes datos miembro como áreas de texto.
        	partidaInfo=estaPartidaInfo;
        	turno=new JTextArea();
		nivel=new JTextArea();
		usuario=new JTextArea();

        	//Indicamos la fuente a usar paracada variable.
        	turno.setFont(getFont().deriveFont(Font.BOLD));
        	turno.setEditable(false);
		nivel.setFont(getFont().deriveFont(Font.BOLD));
        	nivel.setEditable(false);
		usuario.setFont(getFont().deriveFont(Font.BOLD));
        	usuario.setEditable(false);

        	this.setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
   
		//Indicamos el lugar donde queremos que se situe el texto.
        	c.gridx=0;
        	c.gridy=0;
		this.add(turno, c);
     		
        	c.gridx=0;
		c.gridy=1;
		this.add(usuario, c);
		
		c.gridx=0;
		c.gridy=3;
		this.add(nivel, c);
		
	}

	/**
	* Metodo que actualiza la informacion que se muestra por pantalla.
	*/
    
	public void refrescar(){

		//Actualizamos la información y evolución de la partida.
		if(partidaInfo.devolverEmpate()==true) turno.setText("EMPATE");
		else{
			if(partidaInfo.devolverCuatroRaya()==true && partidaInfo.devolverTurno()== 1) turno.setText("¡Cuatro en Raya! ¡Gana "+partidaInfo.devolverUsuario1()+"!");
			if(partidaInfo.devolverCuatroRaya()==true && partidaInfo.devolverTurno()== 2) turno.setText("¡Cuatro en Raya! ¡Gana "+partidaInfo.devolverUsuario2()+"!");
			if(partidaInfo.devolverCuatroRaya()==false && partidaInfo.devolverTurno()== 1) turno.setText("Es turno de "+partidaInfo.devolverUsuario1());
			if(partidaInfo.devolverCuatroRaya()==false && partidaInfo.devolverTurno()== 2) turno.setText("Es turno de "+partidaInfo.devolverUsuario2());
		}

		nivel.setText(partidaInfo.devolverNivelInformacion());
		usuario.setText("Rojas: "+partidaInfo.devolverUsuario1()+"  Amarillas: "+partidaInfo.devolverUsuario2());
	}
}
