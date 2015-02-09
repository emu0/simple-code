/**
* Clase Cliente.
* @author Emilio Mart�nez P�rez.
*/

public class Cliente {

	//Los acentos y caracteres especiales en las impresiones por pantalla han sido omitidos.  
	
	/**
	* Datos miembro del Cliente.
	*/

	private String apellidos, nombre, direccion, dni;
	

	/**
	* Constructor de la clase cliente.
	*/

	public Cliente(String losApellidos, String elNombre, String laDireccion, String elDni){

		apellidos=losApellidos;
		nombre=elNombre;
		direccion=laDireccion;    
		dni=elDni;
	}
 
	/**
	* Mediante el m�todo equals compara el dni de los clientes y detecta si es el mismo o no.
	* @param aComparar String
	* @return coincidencia boolean
	*/

	public boolean compararDNI(String aComparar){
		
		boolean coincidencia=false;
		if(dni.equals(aComparar)==true){
			coincidencia=true;
		} 
		return coincidencia;
	}
  
	/**
	* M�todo que muestra por pantalla los datos del cliente.
   	*/

  	public void mostrarCliente(){

    		System.out.println("\nDni: "+dni+",  Nombre: "+nombre+",  Apellidos: "+apellidos+",  Direccion: "+direccion);
  	}

	/**
   	* M�todo para modificar nombre, apellidos y/o direcci�n de un cliente.
   	* @param nombreNuevo String
   	* @param apellidosNuevo String
	* @param direccionNuevo
   	*/  	
	public void modificarDatosCliente(String nombreNuevo,String apellidosNuevo, String direccionNuevo){

  		nombre=nombreNuevo;
  		apellidos=apellidosNuevo;
		direccion=direccionNuevo;
  	}
  
  	/**
   	* M�todo para devolver el dni.
   	* @return dni String
   	*/
  	public String devolverDNI(){
    		return dni;
  	}
  
  	/**
   	* M�todo para devolver el nombre del cliente.
   	* @return nombre String
   	*/
  	public String devolverNombre(){
  		return nombre;
  	}
  
  	/**
	* M�todo para devolver los apellidos del cliente.
   	* @return apellidos String
   	*/
  	public String devolverApellidos(){
   		return apellidos;
  	}

	/**
	* M�todo para devolver la direcci�n del cliente.
   	* @return direccion String
   	*/
  	public String devolverDireccion(){
   		return direccion;
  	}
}

