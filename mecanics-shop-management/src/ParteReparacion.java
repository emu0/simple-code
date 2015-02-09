import java.util.Random;

/**
* Clase ParteReparacion.
* @author Emilio Martínez Pérez.
*/

public class ParteReparacion{
  
	//Los acentos y caracteres especiales en las impresiones por pantalla han sido omitidos.
	
	/**
	* Datos miembro del parte de reparación.
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
  
	/**Método para calcular la fecha de salida a partir de las horas de trabajo empleadas para la reparación.
	* @param horasReal int
	*/
	
	public void calcularFechaSalida(int horasReal){

		//Suponemos todos los meses tienen 30 días y que no nos encontramos en un año bisiesto.
		//La fecha de salida será la fecha de entrada + días de trabajo. Un día de trabajo se corresponde a 8 horas.
		int diasDeTrabajo=(int)(horasReal/8);
		//No necesareamente las horas de trabajos conicidirán con días completos de trabajo, si hay un día fraccionado, este también se tiene en cuenta.
		if(horasReal%8 != 0){
			diasDeTrabajo++;
		}

		//Creamos unas variables de apoyo para realizar los cálculos.
		int meses=0;
		int anyo=0;

		//Si los dias de trabajo produce que cambiemos de mes.
		if(diaFechaEntrada+diasDeTrabajo>30){

			diaFechaSalida=diasDeTrabajo+diaFechaEntrada;
			//Puesto que nos pasamos, reducimos "mes a mes" la cuenta total de días. Y contabilizamos los meses que equivalen esos días.
			while(diaFechaSalida>30){
				diaFechaSalida=diaFechaSalida-30;
				meses++;
				
			}	

			mesFechaSalida=mesFechaEntrada+meses;			
			//Comprobamos si cambiamos de año, y controlamos cuantas veces.
			while(mesFechaSalida>12){
				mesFechaSalida=mesFechaSalida-12;
				anyo++;
			}

			anyoFechaSalida=anyoFechaEntrada+anyo;
			return;
				
		}
			
		//Si los días de trabajo no produce cambio alguno en el mes (por lo tanto, tampoco de años).
		diaFechaSalida=diasDeTrabajo+diaFechaEntrada;
		mesFechaSalida=mesFechaEntrada;
		anyoFechaSalida=anyoFechaEntrada;
	}

	/**
	* Método para imprimir por pantalla los datos del parte no finalizado.
	*/

	public void mostrarParteNo(){

		//Se muestran por pantalla los datos de interés.
		System.out.println("\n\tParte de reparacion numero: "+codigo);
		System.out.println("El parte de reparacion esta a nombre de:  "+cliente.devolverNombre()+" "+cliente.devolverApellidos()+", con DNI:"+cliente.devolverDNI()+", y direccion: "+cliente.devolverDireccion()+".");
		System.out.println("El vehiculo a repararar es :"+vehiculo.devolverMatricula()+", Modelo y tipo :"+vehiculo.devolverModelo()+", "+vehiculo.devolverTipo()+", con fecha de compra: "+vehiculo.devolverFechaCompra()+".");
		System.out.println("La descripcion de la averia del vehiculo es: "+descripcionAveria+"\nEntro el dia: "+diaFechaEntrada+"/"+mesFechaEntrada+"/"+anyoFechaEntrada+"\nSe estima una reparacion en: "+horasEstimada+" horas.");
  	}

	/**
	* Método para imprimir por pantalla los datos del parte finalizado.
	*/

	public void mostrarParteSi(){

		//Se muestran por pantalla los datos de interés.
		System.out.println("\n\tParte de reparacion numero: "+codigo);
		System.out.println("El parte de reparacion esta a nombre de: "+cliente.devolverNombre()+" "+cliente.devolverApellidos()+", "+cliente.devolverDNI()+".");
		System.out.println("El vehiculo a repararar es :"+vehiculo.devolverMatricula()+", Modelo y tipo :"+vehiculo.devolverModelo()+", "+vehiculo.devolverTipo()+", con fecha de compra: "+vehiculo.devolverFechaCompra()+".");
		System.out.println("La descripcion de la averia del vehiculo es: "+descripcionAveria+"\nEntro el dia: "+diaFechaEntrada+"/"+mesFechaEntrada+"/"+anyoFechaEntrada);
  		System.out.println("Se estimo en "+horasEstimada+" horas la reparacion, pero finalmente fueron "+horasReal+" por lo que la fecha de salida fue: "+diaFechaSalida+"/"+mesFechaSalida+"/"+anyoFechaSalida+".");
		System.out.println("El coste de la reparacion fue de: "+precioReparacion+" euros.");
	}
  
	/**
	* Método que estima las cantidad de dinero que ha costado la reparación dependiendo de las horas de Trabajo.
	* @param horasReales int
	* @return precioReparacion int
	*/
	public int calcularPrecio(int horasReales){

		//Suponemos que cada hora de trabajo se cobra a 7 euros la hora.
		//Además añadimos un plus de piezas que se genera aleatoriamente, para darle un toque más realista a la lotería de llevar un coche al taller.
		//Seguro que los mecánicos hacen algo parecido :P
		//Creamos un objeto de la clase Random con semilla basada en el tiempo actual.
		Random rnd = new Random();
		//nextDouble() es un método que genera un aleatorio del tipo entero entre 0.0 y 1.0
		//Transformamos ese número para tener un número entre 0 y 1000
		int precioPiezas=(int)(rnd.nextDouble()*1000);	
		precioReparacion=(horasReales*7)+precioPiezas;
		return precioReparacion;
	}	

	/**
	* Método que modifica el número de horas estimadas del parte.
	* @param nuevaHoraEstimada int
	*/
	public int estimarHora(){

		//Creamos un objeto de la clse Random con semilla basada en el tiempo actual.
		Random rnd = new Random();
		//nextDouble() es un método que genera un aleatorio del tipo Double entre 0.0 y 1.0
		//Forzamos el número generado para que sea un int entre 0 y 100.
		horasEstimada=(int)(rnd.nextDouble()*100);
		return horasEstimada;
	}

	/**
	* Método que asigna el coste de la reparacion del parte.
	* @param costeReparacion int
	*/
	public void asignarPrecioReparacion(int costeReparacion){
		precioReparacion=costeReparacion;
	}

	/**
	* Método que asigna las horas reales de la reparacion al parte.
	* @param lasHorasReal int
	*/
	public void asignarHoraReal(int lasHorasReal){
		horasReal=lasHorasReal;
	}

	/**
	* Método que finaliza el parte.
	*/
	public void finalizarEstadoParte(){
		finalizado=true;
	}

	/** 
	* Método para devolver el vehiculo asociado del parte.
	* @return vehiculo Vehiculo
	*/
	public Vehiculo devolverVehiculoParte(){
		return vehiculo;
	}
  
	/**
	* Método para devolver el cliente asociado del parte.
	* @return cliente Cliente
	*/
	public Cliente devolverClienteParte(){
		return cliente;
	}
  
	/**
	* Método para devolver el estado del parte.
	* @return estado boolean
	*/
	public boolean devolverFinalizado(){
		return finalizado;
	}
  
	/**
	* Método para devolver las horas estimadas de reparación.
	* @return horasEstimada int
	*/
	public int devolverHorasEstimada(){
		return horasEstimada;
	}
  
	/**
	* Método para devolver las horas reales de reparación.
	* @return horasReal int
	*/
	public int devolverHorasReal(){
		return horasReal;
	}	

	/**
	* Método para devolver el día de salida del vehiculo.
	*/
	public int devolverDiaSalida(){
		return diaFechaSalida;
	}

	/**
	* Método para devolver el mes de salida del vehiculo.
	*/
	public int devolverMesSalida(){
		return mesFechaSalida;
	}

	/**
	* Método para devolver el año de salida del vehiculo.
	*/
	public int devolverAnyoSalida(){
		return anyoFechaSalida;
	}
}