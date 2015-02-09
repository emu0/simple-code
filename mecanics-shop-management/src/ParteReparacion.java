import java.util.Random;

/**
* Clase ParteReparacion.
* @author Emilio Mart�nez P�rez.
*/

public class ParteReparacion{
  
	//Los acentos y caracteres especiales en las impresiones por pantalla han sido omitidos.
	
	/**
	* Datos miembro del parte de reparaci�n.
	*/ 
  
	private int codigo;
	private Cliente cliente;
	private Vehiculo vehiculo;
	private boolean finalizado;
	private int diaFechaEntrada, mesFechaEntrada, anyoFechaEntrada, diaFechaSalida, mesFechaSalida, anyoFechaSalida;
	private int horasEstimada, horasReal;
	private int precioReparacion;
	private String descripcionAveria;
	
  
	/**
	* Constructor.
	*/

	public ParteReparacion(Cliente elCliente, Vehiculo elVehiculo, int lasHorasEstimada, int elCodigo, int elAnyoFechaEntrada, int elMesFechaEntrada, int elDiaFechaEntrada, String laDescripcionAveria){
		
		cliente=elCliente;
		vehiculo=elVehiculo;
		horasEstimada=lasHorasEstimada;
		codigo=elCodigo;
		anyoFechaEntrada=elAnyoFechaEntrada;
		mesFechaEntrada=elMesFechaEntrada;
		diaFechaEntrada=elDiaFechaEntrada;
		finalizado=false;
		descripcionAveria=laDescripcionAveria;
     	}
  
	/**M�todo para calcular la fecha de salida a partir de las horas de trabajo empleadas para la reparaci�n.
	* @param horasReal int
	*/
	
	public void calcularFechaSalida(int horasReal){

		//Suponemos todos los meses tienen 30 d�as y que no nos encontramos en un a�o bisiesto.
		//La fecha de salida ser� la fecha de entrada + d�as de trabajo. Un d�a de trabajo se corresponde a 8 horas.
		int diasDeTrabajo=(int)(horasReal/8);
		//No necesareamente las horas de trabajos conicidir�n con d�as completos de trabajo, si hay un d�a fraccionado, este tambi�n se tiene en cuenta.
		if(horasReal%8 != 0){
			diasDeTrabajo++;
		}

		//Creamos unas variables de apoyo para realizar los c�lculos.
		int meses=0;
		int anyo=0;

		//Si los dias de trabajo produce que cambiemos de mes.
		if(diaFechaEntrada+diasDeTrabajo>30){

			diaFechaSalida=diasDeTrabajo+diaFechaEntrada;
			//Puesto que nos pasamos, reducimos "mes a mes" la cuenta total de d�as. Y contabilizamos los meses que equivalen esos d�as.
			while(diaFechaSalida>30){
				diaFechaSalida=diaFechaSalida-30;
				meses++;
				
			}	

			mesFechaSalida=mesFechaEntrada+meses;			
			//Comprobamos si cambiamos de a�o, y controlamos cuantas veces.
			while(mesFechaSalida>12){
				mesFechaSalida=mesFechaSalida-12;
				anyo++;
			}

			anyoFechaSalida=anyoFechaEntrada+anyo;
			return;
				
		}
			
		//Si los d�as de trabajo no produce cambio alguno en el mes (por lo tanto, tampoco de a�os).
		diaFechaSalida=diasDeTrabajo+diaFechaEntrada;
		mesFechaSalida=mesFechaEntrada;
		anyoFechaSalida=anyoFechaEntrada;
	}

	/**
	* M�todo para imprimir por pantalla los datos del parte no finalizado.
	*/

	public void mostrarParteNo(){

		//Se muestran por pantalla los datos de inter�s.
		System.out.println("\n\tParte de reparacion numero: "+codigo);
		System.out.println("El parte de reparacion esta a nombre de:  "+cliente.devolverNombre()+" "+cliente.devolverApellidos()+", con DNI:"+cliente.devolverDNI()+", y direccion: "+cliente.devolverDireccion()+".");
		System.out.println("El vehiculo a repararar es :"+vehiculo.devolverMatricula()+", Modelo y tipo :"+vehiculo.devolverModelo()+", "+vehiculo.devolverTipo()+", con fecha de compra: "+vehiculo.devolverFechaCompra()+".");
		System.out.println("La descripcion de la averia del vehiculo es: "+descripcionAveria+"\nEntro el dia: "+diaFechaEntrada+"/"+mesFechaEntrada+"/"+anyoFechaEntrada+"\nSe estima una reparacion en: "+horasEstimada+" horas.");
  	}

	/**
	* M�todo para imprimir por pantalla los datos del parte finalizado.
	*/

	public void mostrarParteSi(){

		//Se muestran por pantalla los datos de inter�s.
		System.out.println("\n\tParte de reparacion numero: "+codigo);
		System.out.println("El parte de reparacion esta a nombre de: "+cliente.devolverNombre()+" "+cliente.devolverApellidos()+", "+cliente.devolverDNI()+".");
		System.out.println("El vehiculo a repararar es :"+vehiculo.devolverMatricula()+", Modelo y tipo :"+vehiculo.devolverModelo()+", "+vehiculo.devolverTipo()+", con fecha de compra: "+vehiculo.devolverFechaCompra()+".");
		System.out.println("La descripcion de la averia del vehiculo es: "+descripcionAveria+"\nEntro el dia: "+diaFechaEntrada+"/"+mesFechaEntrada+"/"+anyoFechaEntrada);
  		System.out.println("Se estimo en "+horasEstimada+" horas la reparacion, pero finalmente fueron "+horasReal+" por lo que la fecha de salida fue: "+diaFechaSalida+"/"+mesFechaSalida+"/"+anyoFechaSalida+".");
		System.out.println("El coste de la reparacion fue de: "+precioReparacion+" euros.");
	}
  
	/**
	* M�todo que estima las cantidad de dinero que ha costado la reparaci�n dependiendo de las horas de Trabajo.
	* @param horasReales int
	* @return precioReparacion int
	*/
	public int calcularPrecio(int horasReales){

		//Suponemos que cada hora de trabajo se cobra a 7 euros la hora.
		//Adem�s a�adimos un plus de piezas que se genera aleatoriamente, para darle un toque m�s realista a la loter�a de llevar un coche al taller.
		//Seguro que los mec�nicos hacen algo parecido :P
		//Creamos un objeto de la clase Random con semilla basada en el tiempo actual.
		Random rnd = new Random();
		//nextDouble() es un m�todo que genera un aleatorio del tipo entero entre 0.0 y 1.0
		//Transformamos ese n�mero para tener un n�mero entre 0 y 1000
		int precioPiezas=(int)(rnd.nextDouble()*1000);	
		precioReparacion=(horasReales*7)+precioPiezas;
		return precioReparacion;
	}	

	/**
	* M�todo que modifica el n�mero de horas estimadas del parte.
	* @param nuevaHoraEstimada int
	*/
	public int estimarHora(){

		//Creamos un objeto de la clse Random con semilla basada en el tiempo actual.
		Random rnd = new Random();
		//nextDouble() es un m�todo que genera un aleatorio del tipo Double entre 0.0 y 1.0
		//Forzamos el n�mero generado para que sea un int entre 0 y 100.
		horasEstimada=(int)(rnd.nextDouble()*100);
		return horasEstimada;
	}

	/**
	* M�todo que asigna el coste de la reparacion del parte.
	* @param costeReparacion int
	*/
	public void asignarPrecioReparacion(int costeReparacion){
		precioReparacion=costeReparacion;
	}

	/**
	* M�todo que asigna las horas reales de la reparacion al parte.
	* @param lasHorasReal int
	*/
	public void asignarHoraReal(int lasHorasReal){
		horasReal=lasHorasReal;
	}

	/**
	* M�todo que finaliza el parte.
	*/
	public void finalizarEstadoParte(){
		finalizado=true;
	}

	/** 
	* M�todo para devolver el vehiculo asociado del parte.
	* @return vehiculo Vehiculo
	*/
	public Vehiculo devolverVehiculoParte(){
		return vehiculo;
	}
  
	/**
	* M�todo para devolver el cliente asociado del parte.
	* @return cliente Cliente
	*/
	public Cliente devolverClienteParte(){
		return cliente;
	}
  
	/**
	* M�todo para devolver el estado del parte.
	* @return estado boolean
	*/
	public boolean devolverFinalizado(){
		return finalizado;
	}
  
	/**
	* M�todo para devolver las horas estimadas de reparaci�n.
	* @return horasEstimada int
	*/
	public int devolverHorasEstimada(){
		return horasEstimada;
	}
  
	/**
	* M�todo para devolver las horas reales de reparaci�n.
	* @return horasReal int
	*/
	public int devolverHorasReal(){
		return horasReal;
	}	

	/**
	* M�todo para devolver el d�a de salida del vehiculo.
	*/
	public int devolverDiaSalida(){
		return diaFechaSalida;
	}

	/**
	* M�todo para devolver el mes de salida del vehiculo.
	*/
	public int devolverMesSalida(){
		return mesFechaSalida;
	}

	/**
	* M�todo para devolver el a�o de salida del vehiculo.
	*/
	public int devolverAnyoSalida(){
		return anyoFechaSalida;
	}
}