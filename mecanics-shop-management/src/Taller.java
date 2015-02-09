import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

/**
* Clase Taller.
* @author Emilio Mart�nez P�rez.
*/


public class Taller{
  
	//Los acentos y caracteres especiales en las impresiones por pantalla han sido omitidos.
	/**
   	* Datos miembro del Taller.
  	*/
  
	ArrayList<Cliente> listaClientes;
	ArrayList<Vehiculo> listaVehiculos;
	ArrayList<ParteReparacion> listaPartes;
  
  	/**
   	* Constructor.
	*/

	public Taller(){
	listaClientes=new ArrayList<Cliente>();
	listaVehiculos=new ArrayList<Vehiculo>();
	listaPartes=new ArrayList<ParteReparacion>();
	
	//Lista de Clientes, Vah�culos y Partes de reparaci�n a�adidas por defecto.

	listaClientes.add(new Cliente("Leton","Josechu","Calle Falsa 123","23457698K"));
	listaClientes.add(new Cliente("Coton","Carmelo","Avnd Estamelainvento 45","13789745F"));
	listaClientes.add(new Cliente("Vales","Encarna","Chabola La Mansion","20943218X"));
    
	listaVehiculos.add(new Vehiculo("GR9999XY","Seat Anda","turismo", "1/1/1990"));
	listaVehiculos.add(new Vehiculo("1234BCD","Ferrari Lentini","turismo", "12/12/2008"));
	listaVehiculos.add(new Vehiculo("4321DCB","Locar Gatodo","furgoneta", "4/1/2005"));
	listaVehiculos.add(new Vehiculo("GR0000A","Troncomovil Cicleta++","motocicleta","2/3/1834"));
    
	
	listaPartes.add(new ParteReparacion(listaClientes.get(0), listaVehiculos.get(1), 10, 1, 2009, 5, 5, "Embrague roto"));
	listaPartes.add(new ParteReparacion(listaClientes.get(0), listaVehiculos.get(3), 56, 2, 2009, 7, 1, "Chapa y pintura completo"));
	listaPartes.add(new ParteReparacion(listaClientes.get(1), listaVehiculos.get(0), 15, 3, 2009, 10, 22, "Fallo general de la electricidad. Se ha cambiado la dinamo Continuamos con las pruebas"));
	listaPartes.add(new ParteReparacion(listaClientes.get(2), listaVehiculos.get(2), 100, 4, 2010, 1, 5, "Este coche esta hecho polvo. Le fallan hasta los tapones de las valvulas de las ruedas. Yo que el due�o le pedia uno nuevo a los Reyes porque vale mas el arreglo que uno nuevo"));
    	
	//Los dos primeros partes han de ser reparados y se requiere un precio por defecto tambi�n.
	finalizarParteSILENT(8);
	finalizarParteSILENT(64);
	listaPartes.get(0).asignarPrecioReparacion(250);
	listaPartes.get(1).asignarPrecioReparacion(2500);
	}
  
  
	/**
	* M�todo para a�adir un cliente en el array (dar de alta) introduciendo sus apellidos, su nombre, la direcci�n y su dni. Evitando que se duplique el DNI.
	* @param apellidos String
	* @param nombre String
	* @param direccion String
	* @param dni String
	*/

	public void altaCliente(String apellidos, String nombre, String direccion, String dni){
		Cliente cliente=new Cliente(apellidos, nombre, direccion, dni);

		//Recorremos el Array cliente a cliente.
		for(int i=0; i<listaClientes.size(); i++){

			//Comaparamos en busca de un un dni igual, si existe nos aviso de ello y no se a�ade el cliente.
			if(listaClientes.get(i).compararDNI(dni)==true){
				System.out.println("El cliente con DNI "+dni+" ya existe en la base de datos, compruebe el DNI e intentelo de nuevo.");
				return;
			}
		}

		//Si no existe un dni igual, se a�ade el cliente.
		System.out.println("El cliente con DNI "+dni+" ha sido a�adido correctamente a la base de datos.");
		listaClientes.add(cliente);
	}
  
  
	/**
	* M�todo para elminar un cliente en el array (dar de baja) introduciendo su dni;
	* @param dni String
	*/ 

	public void bajaCliente(String dni){
		
		//Recorremos y buscamos en en Array el DNI del cliente que queremos eliminar.
		for(int i=0; i<listaClientes.size(); i++){
	
			//Cuando se encuentra el cliente se elimina.
			if(listaClientes.get(i).compararDNI(dni)==true){
				listaClientes.remove(i);
				System.out.println("El cliente con DNI "+dni+" ha sido dado de baja correctamente.");
				return;
			}
		}
		//En el caso de que no se encontrara, se da un aviso de ello.
		System.out.println("El cliente con DNI "+dni+" no se encuentra en la base de datos, revise su DNI e intentelo de nuevo.");
	}
  

	/**
	* M�todo para mostrar en pantalla los datos de un cliente a partir de su dni.
	* @param dni String
	*/

	public void datosCliente(String dni){
	
		//Recoremos el array en busca del cliente deseado.
		for(int i=0; i<listaClientes.size(); i++){
			
			//Si este se encuentra en el array, mostramos sus datos.
			if(listaClientes.get(i).compararDNI(dni)==true){
				listaClientes.get(i).mostrarCliente();
				return;
			}
		}
		
		//En el caso de que no se encontrara, se da un aviso de ello.		
		System.out.println("El cliente con DNI "+dni+" no se encuentra en la base de datos, revise su DNI e intentelo de nuevo.");
	}
  
 
	/**
	* M�todo que muestra todos los datos de los clientes del array por pantalla.
	*/

	public void datosTodosClientes(){
	
		//Pudiera darse el caso que el array estuviera vac�o.
		if(listaClientes.size()==0){
			System.out.println("No existen clientes en la base de datos.");
			return;
		}

		//En el caso contrario.
		else{
			System.out.println("Los clientes registrados en la base de datos son:  ");
			
			//Recorremos el array y van mostrandose uno a uno los datos precisados.
			for(int i=0; i<listaClientes.size(); i++){
				listaClientes.get(i).mostrarCliente();
			}
		}
	}


	/**
	* M�todo para modificar los datos de un cliente a partir de su DNI.
	* @param dni String
	* @param nombre String
	* @param apellidos String
	* @param direccion String
	*/

	public void modificarCliente(String dni,String nombre,String apellidos,String direccion){
	
		//Recorremos el array.
		for(int i=0; i<listaClientes.size(); i++){

			//Buscamos al cliente deseado.
 			if(listaClientes.get(i).compararDNI(dni)==true){

				//Modificamos los datos deseados.
				listaClientes.get(i).modificarDatosCliente(nombre,apellidos,direccion);
				System.out.println("Los datos del cliente con DNI "+dni+" han sido modificados correctamente en la base de datos.");
				return;
			}
		}
		//Si no existe el cliente o hubiera un fallo en el DNI.
		System.out.println("El cliente con DNI "+dni+" no se encuentra en la base de datos, revise su DNI e intentelo de nuevo.");
	}
  
	/**
	* M�todo para a�adir un vehiculo al array (dar de alta) introduciendo su matricula, tipo, modelo y la fecha de la compra. Evitamos un duplicado de la matricula.
	* @param matricula String
	* @param modelo String
	* @param tipo int
	* @param fechaCompra String
	*/

	public void altaVehiculo(String matricula, String modelo, String tipo, String fechaCompra){

		//Creamos el nuevo objeto.
		Vehiculo vehiculo=new Vehiculo(matricula, modelo, tipo, fechaCompra);
		//Tenemos que comprobar que la matricula del vehiculo no est� duplicada.

		//Recorremos el Array veh�culo a veh�culo en busca de una matr�cula igual.
		for(int i=0; i<listaVehiculos.size(); i++){

			//Comprobamos que no exista un vehiculo con la misma matricula.
			if(listaVehiculos.get(i).compararMatricula(matricula)==true){
				System.out.println("El vehiculo con matricula "+matricula+" ya se encuentra en la base de datos, compruebela e intentelo de nuevo.");
				return;
			}
		}
		

		//Si todo es correcto se pasa a a�adir el veh�culo a la base de datos.
		listaVehiculos.add(vehiculo);
		System.out.println("El vehiculo con matricula "+matricula+" ha sido de alta de forma correcta.");
		
	}

  
	/**
	* M�todo para eliminar el vah�culo del array (dar de baja) introduciendo la matr�cula del mismo.
	* @param matricula String
	*/

	public void bajaVehiculo(String matricula){
	
		for(int i=0; i<listaVehiculos.size(); i++){

			//Buscamos el vehiculo cuya matricula sea la deseada y lo eliminamos.
 			if(listaVehiculos.get(i).compararMatricula(matricula)==true){
				listaVehiculos.remove(i);
				System.out.println("El vehiculo con matricula "+matricula+" ha sido eliminado de la base de datos.");
				return;
			}
		}
			
		//Si no se encuentra la matricula.
		System.out.println("El vehiculo con matricula "+matricula+" no se encuentra en la base de datos, revise la matricula e intentelo de nuevo.");
			
	}

	/**
	* M�todo que muestra los datos de un veh�culo a partir de su matricula.
	* @param matricula String
	*/

	public void datosVehiculo(String matricula){
	
		for(int i=0;i<listaVehiculos.size();i++){

			//Busca el veh�culo deseado y si lo encuentra lo muestra por pantalla.
			if(listaVehiculos.get(i).compararMatricula(matricula)==true){
				listaVehiculos.get(i).mostrarVehiculo();
				return;
			}
		}
		//Si no lo encuentra.
		System.out.println("El vehiculo con matricula "+matricula+" no se encuentra en la base de datos, revise la matricula e intentelo de nuevo.");
		
	}
	
  
	/**
	* M�todo que muestra los datos de todos los vehiculos.
	*/

	public void datosTodosVehiculos(){

		//Pudiera ser que el array estuviera vac�o.
		if(listaVehiculos.size()==0){
	
			System.out.println("No existen Vehiculos en la base de datos.");
			return;

		}

		//En el caso contrario los muestra por pantalla.
		else{

			System.out.println("Los datos de todos los vehiculos son:  ");
			for(int i=0; i<listaVehiculos.size(); i++){
				listaVehiculos.get(i).mostrarVehiculo();
			}
		}
	}
  
  
	/**
	* M�todo para modificar los datos de un veh�culo a partir de su matr�cula.
	* @param matricula String
	* @param modelo String
	* @param fechaCompra String
	* @param tipo char
	*/

	public void modificarVehiculo(String matricula, String modelo, String fechaCompra, String tipo){
		
		//Buscamos la matricula del veh�culo a modificar los datos.
		for(int i=0; i<listaVehiculos.size(); i++){

			if(listaVehiculos.get(i).compararMatricula(matricula)==true){
	
				//Se modifican los datos y se avisa que todo ha ido correctamente.
				listaVehiculos.get(i).modificarDatosVehiculo(modelo, fechaCompra, tipo);
				System.out.println("Los datos del vehiculo con matricula "+matricula+" han sido modificados.");
				return;
			}
		}
		//Si hubiera alg�n problema.
		System.out.println("El vehiculo con matricula "+matricula+" no ha sido encontrado en la base de datos.");
	}

	/**
	* M�todo para observar el historial de reparaciones de un veh�culo insertando la matr�cula.
	* @param matricula String
	*/
	
	public void historialVehiculo(String matricula){
		
		//Comprobamos que el array no est� vac�o.
		if(listaPartes.size()==0){
			System.out.println("No existen partes en la base de datos.");
		}
		
		//Creamos una variable de control
		int contador=0;

		//Buscamos el veh�culo deseado en todos los partes de reparaci�n.
		for(int i=0; i<listaPartes.size(); i++){
			
			//Si lo encontramos imprimimos el parte asociado ya finalizado.
			if(listaPartes.get(i).devolverVehiculoParte().compararMatricula(matricula)==true){
				listaPartes.get(i).mostrarParteSi();
				contador++;
			}
		}
		//Si no hay ning�n parte finalizado asociado nos avisa.
		if(contador==0){
			System.out.println("No existe ningun parte asociado al vehiculo con matricula "+matricula+".");
		
  		}
	}

	/**
	* M�todo para dar de alta un parte de reparaci�n introduciendo el DNI del cliente, la matricula del veh�culo y el tipo de aver�a.
	* @param matricula String
	* @param dni String
	* @param averia String
	*/ 

	public void altaParte(String dni, String matricula, String averia){
		
		//Buscamos la matricula y el dni introducidos.
		for(int i=0; i<listaClientes.size(); i++){
			//Localizamos el cliente.
			if(listaClientes.get(i).compararDNI(dni)==true){

				for(int j=0; j<listaVehiculos.size(); j++){
					//Localizamos el veh�culo.
					if(listaVehiculos.get(j).compararMatricula(matricula)==true){
						
						//Comprobamos si ese veh�culo ya est� en otro parte de reparaci�n no finalizado.
						for(int k=0; k<listaPartes.size(); k++){

							if(listaPartes.get(k).devolverVehiculoParte().compararMatricula(matricula)==true){
								if(listaPartes.get(k).devolverFinalizado()==false){
									System.out.println("El veh�culo con matricula "+matricula+" ya esta en fase de reparacion.");
									return;
								}
							}
						}

						//Si ambos existen creamos las n�meros de horas estimadas para la reparaci�n al azar.
						//Creamos un objeto de la clase Random con semilla basada en el tiempo actual.
						Random rnd = new Random();
						//nextDouble() es un m�todo que genera un aleatorio del tipo Double entre 0.0 y 1.0
						//Forzamos el n�mero generado para que sea tipo int entre 0 y 100.
						int horasEstimada=(int)(rnd.nextDouble()*100);

						//Generamos el c�digo del parte.
						int codigo=listaPartes.size()+1;

						//Generamos la fecha de entrada al taller.
						GregorianCalendar fechaEntrada=new GregorianCalendar();	
						int anyoFechaEntrada=fechaEntrada.get(fechaEntrada.YEAR);
						//Puesto que Enero se corresponde a la posicion 0, "corregimos" la fecha.
						int mesFechaEntrada=fechaEntrada.get(fechaEntrada.MONTH)+1;
						int diaFechaEntrada=fechaEntrada.get(fechaEntrada.DAY_OF_MONTH);

						//Creamos el nuevo parte y lo agregamos a la lista.
						listaPartes.add(new ParteReparacion(listaClientes.get(i), listaVehiculos.get(j), horasEstimada, codigo, anyoFechaEntrada, mesFechaEntrada, diaFechaEntrada, averia));
						System.out.println("El parte ha sido a�adido a la base de datos. Su codigo es: "+codigo);
						//Cambiamos el estado del veh�culo asociado.
						listaVehiculos.get(j).averiarVehiculo();
						return;
					}
				}
			}
		}
		//Si alguno de los datos introducidos fuera incorrecto.
		System.out.println("El parte no ha podido ser creado, revise el DNI y la Matricula e intentelo de nuevo.");
	}
  
	/**
	* M�todo para dar por finalizado un parte introduciendo las horas que ha necesitado la reparaci�n.
	* @param horasReal int
	*/
 
	public void finalizarParte(int horasReal){
	
		for(int i=0; i<listaPartes.size(); i++){

			//Buscamos el primer parte que no est� finalizado
			if(listaPartes.get(i).devolverFinalizado()==false){
				
				//Asignamos las horas reales al parte
				listaPartes.get(i).asignarHoraReal(horasReal);
				//Creamos la fecha de salida.
				listaPartes.get(i).calcularFechaSalida(horasReal);
				
				//Calculamos el precio de la reparaci�n.
				int total=listaPartes.get(i).calcularPrecio(horasReal);
				//Asignamos el precio de la reparaci�n.
				listaPartes.get(i).asignarPrecioReparacion(total);
				
				//reparamos el veh�culo.
				listaPartes.get(i).devolverVehiculoParte().repararVehiculo();
				//Damos por finalizado el parte.
				listaPartes.get(i).finalizarEstadoParte();

				//Indicamos por pantalla que se ha realizado correctamente e indicamos el precio de la reparaci�n y mostramos datos de inter�s.
				System.out.print("A dia de "+listaPartes.get(i).devolverDiaSalida()+"/"+listaPartes.get(i).devolverMesSalida()+"/"+listaPartes.get(i).devolverAnyoSalida());
				System.out.println(" se ha reparado correctamente el vehiculo con matricula: "+listaPartes.get(i).devolverVehiculoParte().devolverMatricula()+".");
				System.out.println("La cuantia de la reparacion es de: "+total+" euros.\nDonde la mano de obra corresponde a "+horasReal*7+" euros. \nEl precio de las piezas usadas es de "+(total-(horasReal*7))+" euros.");
				return;
			}	
		}
		//Si todos los partes est�n finalizados.
		System.out.println("No hay partes de reparacion pendientes.");
	}

	/**
	* M�todo para finalizar un parte introduciendo las hora queha necesitado la reparaci�n. Modo SILENCIOSO, s�lamente usado para el inicio del programa.
  	* @param horasReal int
	*/
	
	public void finalizarParteSILENT(int horasReal){
	
		for(int i=0; i<listaPartes.size(); i++){

			//Buscamos el primer parte que no est� finalizado
			if(listaPartes.get(i).devolverFinalizado()==false){
				
				//Asignamos las horas reales al parte
				listaPartes.get(i).asignarHoraReal(horasReal);
				//Creamos la fecha de salida.
				listaPartes.get(i).calcularFechaSalida(horasReal);
				
				//Calculamos el precio de la reparaci�n.
				int total=listaPartes.get(i).calcularPrecio(horasReal);
				//Asignamos el precio de la reparaci�n.
				listaPartes.get(i).asignarPrecioReparacion(total);
				
				//reparamos el veh�culo.
				listaPartes.get(i).devolverVehiculoParte().repararVehiculo();
				//Damos por finalizado el parte.
				listaPartes.get(i).finalizarEstadoParte();
				return;
			}
		}
	}

	/**
	* M�todo para mostrar los partes no finalizados.
	*/ 

	public void mostrarPartesPendientes(){
		
		//Creamos una variable de control auxiliar.
		int control=0;
		//Pudiera ser que no existieran partes en la base de datos.
		if(listaPartes.size()==0){
			System.out.println("No existen partes en la base de datos.");
			return;
		}

		//Recorremos el array en busca de partes.
		for(int i=0; i<listaPartes.size(); i++){
			
			//Comprobamos si est� finalizado o no y se muestra el parte no finalizado.
			if(listaPartes.get(i).devolverFinalizado()==false){
				listaPartes.get(i).mostrarParteNo();
				control++;
			}
		}

		//Si no hay partes sin finalizar se muestra un aviso.
		if(control==0){
			System.out.println("Todos los vehiculos han sido reparados ya.");
		}
	}
  
  
	/**
	* M�todo para proporcionar una lista de partes asociados a un DNI.
	* @param dni String
	*/ 
	public void partesCliente(String dni){
		
		//Creamos una variable auxiliar de control;
		int control=0;
		//Recorremos el array de partes en busca del cliente elegido.		
		for(int i=0; i<listaPartes.size(); i++){
		
			//Comprobamos si el parte corresponde al cliente.
			if(listaPartes.get(i).devolverClienteParte().compararDNI(dni)==true){
				
				//Vemos si est� finalizado o no e imprimimos por pantalla.
				if(listaPartes.get(i).devolverFinalizado()==false){
					listaPartes.get(i).mostrarParteNo();
					control++;
				}
				else{
					listaPartes.get(i).mostrarParteSi();
					control++;
				}
			}
		}
			
		if(control==0){
			System.out.println("El usuario con DNI "+dni+" no se encuentra en la base de datos o no tiene asociado todav�a ningun parte de reparacion.");
		}
	}

	/**
	* M�todo para mostrar las horas reales medias de reparaci�n para partes finalizados.
	*/
	
	public void horaMedia(){

		//Creamos unas variables auxiliares de control.
		int horas=0;	
		int contador=0;

		//Recorremos el array en busca de partes finalizados.
		for(int i=0; i<listaPartes.size(); i++){

			if(listaPartes.get(i).devolverFinalizado()==true){
				//Sumamos el n�mero de horas de cada parte y contabilizamos cuantos partes llevamos.
				horas=+listaPartes.get(i).devolverHorasReal();
				contador++;
			}
		}
		//Si existian partes finalizados.
		if(contador !=0){
			System.out.println("Las horas totales medias de reparacion es de: "+horas/contador+" horas.");
			return;
		}
		//En el caso de que todos los partes est�n pendientes.
		else{
			System.out.println("No existen partes de reparacion finalizados en la base de datos.");
		}
	}
  
  
	/**
	* M�todo para cambiar el n�mero de horas estimadas de un parte generando un n�mero numero aleatorio.
	* @param matricula String
	* @param dni String
	*/
 
	public void nuevaHoraEstimadaAleatoria(String matricula, String dni){
		
		//Recorremos el array en busca del parte deseado
		for(int i=0; i<listaPartes.size(); i++){

			//Buscamos un parte en el que coincida matricula y DNI
			if(listaPartes.get(i).devolverVehiculoParte().compararMatricula(matricula)==true){
				if(listaPartes.get(i).devolverClienteParte().compararDNI(dni)==true){
					
					//Calculamos la nueva hora.
					int nuevaHoraEstimada=listaPartes.get(i).estimarHora();
					System.out.println("Se ha estimado para el parte deseado un total de "+nuevaHoraEstimada+" horas para ser reparado.");
					return;
				}
			}
		}
		//Si no se encuentra el parte requerido.
		System.out.println("No se ha encontrado en la base de datos el parte deseado.");
	}
  
  
	/**
	* M�todo para mostrar el estado general del taller.
	*/
 
	public void todoTaller(){
		
		//Mostramos todos los clientes.
		System.out.println("\n****************** \"Clientes\" *********************");
		for(int i=0; i<listaClientes.size(); i++){
			listaClientes.get(i).mostrarCliente();
		}
		System.out.println("\n------------------------------------------------------");
		//Mostramos todos los veh�culos.
		System.out.println("\n****************** \"Vehiculos\" *********************");
		for(int j=0; j<listaVehiculos.size(); j++){
			listaVehiculos.get(j).mostrarVehiculo();
		}
		System.out.println("\n------------------------------------------------------");
		//Mostramos todos los partes.
		System.out.println("\n************ \"Partes de Reparacion\" ****************");
		for(int k=0; k<listaPartes.size(); k++){
			if(listaPartes.get(k).devolverFinalizado()==true){
				listaPartes.get(k).mostrarParteSi();
			}
			else{	
				listaPartes.get(k).mostrarParteNo();
			}
		}
		System.out.println("\n------------------------------------------------------");
	}
}