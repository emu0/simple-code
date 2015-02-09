import javax.swing.*;

/**
* Clase GestionTaller.
* @author Emilio Martínez Pérez.
*/

public class GestionTaller{

	//Los acentos y caracteres especiales en las impresiones por pantalla han sido omitidos. 
  	/**
	* Arrays que contienen las opciones del menu principal del programa  
	*/

  	private static final String menuPrincipal[]={
		"0.- Salir del programa.",
		"1.- Gestion de clientes.",
		"2.- Gestion de vehiculos.",
		"3.- Gestion de partes de reparacion.",
		"4.- Estado General del Taller."
	};
  
	private static final String submenuGestionClientes[]={
		"0. Volver al menu principal.",
		"1. Alta de cliente.",
		"2. Baja de cliente.",
		"3. Mostrar datos de cliente.",
		"4. Mostrar datos de todos los clientes.",
		"5. Modificar datos de cliente."
	};

	private static final String submenuGestionVehiculos[]={
		"0. Volver al menu principal.",
		"1. Alta de vehiculo.",
		"2. Baja de vehiculo.",
		"3. Mostrar datos de vehiculo.",
 		"4. Mostrar datos de todas los vehiculos.",
		"5. Modificar datos de vehiculo.",
		"6. Mostrar historial de reparacion de un vehiculo."
	};
  
	private static final String submenuGestionPartes[]={
		"0. Volver al menu principal.",
		"1. Alta Parte.",
		"2. Finalizar reparacion.",
		"3. Mostrar reparaciones pendientes.",
		"4. Mostrar partes asociados a un DNI.",
		"5. Calculo de horas medias reales de reparacion para partes finalizados.",
		"6. Modificar horas estimadas de una reparacion."
	};
  
	private static final String submenuGestionTaller[]={
		"0. Volver al menu principal.",
		"1. Mostrar inventario completo del taller."
   
	};

	/**
	* Datos miembros necesarios de la clase GestionTaller.
	*/

	Taller taller;
  
	/**
	* Constructor
	*/

	public GestionTaller(){

	taller=new Taller();
  	}
	
	/**
	* Metodo main
 	*/

	public static void main(String args[]){

		GestionTaller gestionTaller= new GestionTaller();
		gestionTaller.gestionarMenuPrincipal();  
  	}
  
	/**
	* Método para gestionar el menú principal.
	*/

	private void gestionarMenuPrincipal(){
		int opcion=-1;
		
		// Bucle de control
		do{
			opcion=leerOpcionMenu("Gestion Talleres Hermanos FP - Menu principal", menuPrincipal);
	      
			// Control de opciones
			switch(opcion){
				case 1: gestionarMenuClientes();
					break;
				case 2: gestionarMenuVehiculos();
					break;
				case 3: gestionarMenuPartes();
					break;
				case 4: gestionarMenuTaller();
					break;        
			}
		}while(opcion != 0);
	}
 
	/**
	* Método para gestionar el submenú de clientes.
	*/

	private void gestionarMenuClientes(){
		int opcion=-1;
    
		// Bucle de control
		do{
			opcion=leerOpcionMenu("Menu de gestion de clientes.",submenuGestionClientes);
      	
			// Control de opciones
			switch(opcion){
				case 1: altaClienteInteractivo();
					break;
				case 2: bajaClienteInteractivo();
 					break;
				case 3: datosClienteInteractivo();
					break;
				case 4: datosTodosClientesInteractivo();
					break;
				case 5: modificarClienteInteractivo();
					break;                      
			}
		}while(opcion != 0);
	}
  
  
	/**
	* Método para tomar los datos de una forma interactiva para dar de alta un cliente.
	*/

	public void altaClienteInteractivo(){

		String nombre=JOptionPane.showInputDialog("Introduzca el nombre del cliente");
		String apellidos=JOptionPane.showInputDialog("Introduzca los apellidos del cliente");
		String direccion=JOptionPane.showInputDialog("Introduzca la direccion del cliente");
		String dni=JOptionPane.showInputDialog("Introduzca el DNI del cliente");
		taller.altaCliente(apellidos, nombre, direccion, dni);
	}
  
	/**
	* Método para tomar los datos de una forma interactiva para dar de baja a un cliente.
	*/

	public void bajaClienteInteractivo(){

		String dni=JOptionPane.showInputDialog("Introduzca el DNI del cliente que desea dar de baja");
		taller.bajaCliente(dni);
	}
  
	/**
	* Método que muestra los datos del cliente de forma interactiva.
	*/

	public void datosClienteInteractivo(){

 		String dni=JOptionPane.showInputDialog("Introduzca el DNI del cliente que desea ver sus datos.");
		taller.datosCliente(dni);
	}
  
	/**
 	* Método para para mostrar todos los datos de los clientes.
	*/

	public void datosTodosClientesInteractivo(){
		
		taller.datosTodosClientes(); 
	}
  
	/**
	* Método para tomar los datos de una forma interctiva para modificar los datos de un cliente.
	*/

	public void modificarClienteInteractivo(){

		String dni=JOptionPane.showInputDialog("Introduzca el DNI del cliente que desea modificar los datos.");
		String nombre=JOptionPane.showInputDialog("Introduzca el nuevo nombre del cliente.");
		String apellidos=JOptionPane.showInputDialog("Introduzca los nuevos apellidos del cliente.");
		String direccion=JOptionPane.showInputDialog("Introduzcca la nueva direccion del cliente.");
		taller.modificarCliente(dni, nombre, apellidos, direccion);
	}
  
	/**
	* Método para gestionar el menú de Vehiculos.
	*/

	private void gestionarMenuVehiculos(){
		int opcion=-1;
    
		// Bucle de control
		do{
			opcion=leerOpcionMenu("Menu de gestion de vehiculos.",submenuGestionVehiculos);
      
			// Control de opciones
			switch(opcion){
				case 1: altaVehiculoInteractivo();
					break;
				case 2: bajaVehiculoInteractivo();
					break;
				case 3: datosVehiculoInteractivo();
					break;
				case 4: datosTodosVehiculosInteractivo();
					break; 
				case 5: modificarVehiculoInteractivo();
					break;  
       	         		case 6: historialVehiculoInteractivo();
					break;
			}
		}while(opcion != 0);
	}
  
  
	/**
	* Método para tomar los datos de forma interactiva para dar de alta un vehículo.
	*/

	public void altaVehiculoInteractivo(){

		String matricula=JOptionPane.showInputDialog("Introduzca la matricula del vehiculo.");
		String modelo=JOptionPane.showInputDialog("Introduzca el modelo del vehiculo.");
		String tipo=JOptionPane.showInputDialog("Introduzca el tipo del vehiculo.");
		String fechaCompra=JOptionPane.showInputDialog("Introduzca la fecha de compra del vehiculo.");
		taller.altaVehiculo(matricula, modelo, tipo, fechaCompra);
		
	}
  
	/**
	* Método para tomar los datos de forma interactiva para dar de baja un vehículo.
	*/

	public void bajaVehiculoInteractivo(){

		String matricula=JOptionPane.showInputDialog("Introduzca la matricula del vehiculo para darlo de baja.");
		taller.bajaVehiculo(matricula);
	}
  
	/**
	* Método para tomar los datos de una forma interactiva para mostrar un vehículo.
	*/

	public void datosVehiculoInteractivo(){

		String matricula=JOptionPane.showInputDialog("Introduzca la matricula del vehiculo que desea ver sus datos.");
		taller.datosVehiculo(matricula);
	}
  
	/**
	* Método para mostrar los datos de todos los vehículos.
	*/

	public void datosTodosVehiculosInteractivo(){

		taller.datosTodosVehiculos();
	}
  
	/**
	* Método para tomar los datos de forma interactiva para modificar los datos de un vehículo.
	*/

	public void modificarVehiculoInteractivo(){

		String matricula=JOptionPane.showInputDialog("Introduzca la matricula del vehiculo que desea modificar los datos.");
		String modelo=JOptionPane.showInputDialog("Introduzca el nuevo modelo del vehiculo.");
		String fechaCompra=JOptionPane.showInputDialog("Introduzca la nueva fecha de compra del vehiculo.");
		String tipo=JOptionPane.showInputDialog("Introduzca el nuevo tipo de vehiculo..");
		taller.modificarVehiculo(matricula, modelo, fechaCompra, tipo);
  	}

	/**
	* Método para tomar los datos de forma interactiva para observar el historial de reparaciones de un vehículo.
	*/

	public void historialVehiculoInteractivo(){

		String matricula=JOptionPane.showInputDialog("Introduzca la matricula del vehiculo que desea ver su historial.");
		taller.historialVehiculo(matricula);
	}
		
  
	/**
	* Método para gestionar el menú de partes.
	*/
	private void gestionarMenuPartes(){
		int opcion=-1;
    
		// Bucle de control
		do{
			opcion=leerOpcionMenu("Menu de gestion de partes.",submenuGestionPartes);
      
			// Control de opciones
			switch(opcion){
			case 1: altaParteInteractivo();
				break;
			case 2: finalizarParteInteractivo();
				break;
			case 3: mostrarPartesPendientesInteractivo();
				break;
			case 4: partesClienteInteractivo();
				break; 
			case 5: horaMediaInteractivo();
				break;  
			case 6: nuevaHoraEstimadaAleatoriaInteractivo();
				break;            
                
			}
		}while(opcion != 0);
	}
  
	/**
	* Método para tomar los datos de forma interactiva para dar de alta un parte.
	*/

	public void altaParteInteractivo(){

		String dni=JOptionPane.showInputDialog("Introduzca el DNI del cliente asociado al parte de averia.");
		String matricula=JOptionPane.showInputDialog("Introduzca la matricula del vehiculo asociado al parte de averia.");
		String averia=JOptionPane.showInputDialog("Introduzca la descripcion de la averia.");
		taller.altaParte(dni, matricula, averia);  
	}
  
	/**
	* Método para tomar los datos de forma interactiva para finalizar un parte.
	*/

	public void finalizarParteInteractivo(){

		String horasStr=JOptionPane.showInputDialog("Introduzca las horas invertidas en la reparacion.");
		int horasInt=Integer.parseInt(horasStr);
		taller.finalizarParte(horasInt);  
	}
  
	/**
	* Método para mostrar todos los partes no finalizados.
	*/

	public void mostrarPartesPendientesInteractivo(){

		taller.mostrarPartesPendientes();
	}
  
	/**
	* Método para tomar los datos de forma interactiva para mostrar los partes asociados a un clente.
	*/

	public void partesClienteInteractivo(){

  		String dni=JOptionPane.showInputDialog("Introduzca el dni del cliente para ver sus partes de reparacion asociados.");
   		taller.partesCliente(dni);   
	}
  

	/**
	* Método para mostrar las horas medias reales de tiempo usado en la reparación de los vehiculos.
	*/

	public void horaMediaInteractivo(){

		taller.horaMedia();
	}
  
	/**
	* Método para los datos necesaros de forma interactiva para realizar una nueva estimación de horas para la reparación de un vehiculo.
	*/

	public void nuevaHoraEstimadaAleatoriaInteractivo(){

		String matricula=JOptionPane.showInputDialog("Introduzca la matricula asociada al parte de reparacion.");
		String dni=JOptionPane.showInputDialog("Introduzca el DNI del cliente asociado al parte de reparacion.");
   		taller.nuevaHoraEstimadaAleatoria(matricula, dni);
	}
    
 
	/**
	* Método para gestionar en menu del Taller.
	*/

	private void gestionarMenuTaller(){
		int opcion=-1;
    
		// Bucle de control
		do{
 			opcion=leerOpcionMenu("Menu del taller",submenuGestionTaller);
      
			// Control de opciones
			switch(opcion){
 				case 1: taller.todoTaller();
					break;
                
				}
			}while(opcion != 0);
	}
  
	/**
	* Metodo para imprimir un menu por pantalla.
	* @param titulo
	* @param menu
	*/

	private void presentarMenu(String titulo, String [] opciones){

		System.out.println("\n************** "+titulo+" *****************");
		System.out.println("");
		System.out.println("");
		for(int i=0; i < opciones.length; i++){
			System.out.println(opciones[i]);
		}

		System.out.println("");
		System.out.println("");
		System.out.println("---------------------------------------------------");
	}
  
	/**
	* Metodo para leer la opcion de un menu
	* @param titulo
	* @param menu
	* @return opcion leida
	*/

	private int leerOpcionMenu(String titulo, String [] menu){

		int maximo=menu.length-1;
		int opcion=-1;
    
		do{
			presentarMenu(titulo, menu);
			opcion=leerValorEntero("Elige opcion entre 0 y "+maximo);
		}while(opcion < 0 || opcion > maximo);
	
		// Devolver opcion
		return opcion;
	}
  
	/**
	* Metodo para reconocer un entero capturado con JOptionPane.
	* @param mensaje String
	*/

	private int leerValorEntero(String mensaje){
		String cadena=JOptionPane.showInputDialog(mensaje);
 		int valor=-1;
		try{
			valor=Integer.parseInt(cadena);
			}catch(Exception e){
				System.out.println("Valor tecleado no corresponde a entero");
			}
    	
			// Devolver valor
			return valor;
		}
		//Devolver taller.
		public Taller devolverTaller(){
			return taller;
	}
}