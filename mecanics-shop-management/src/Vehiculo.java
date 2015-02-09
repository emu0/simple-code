/**
* Clase Vehiculo.
* @author Emilio Mart�nez P�rez.
*/

public class Vehiculo{
  
	//Los acentos y caracteres especiales en las impresiones por pantalla han sido omitidos.
	/**
   	* Datos miembro de los veh�culos.
   	*/
  
  	private String matricula, modelo, fechaCompra, tipo;
  	private boolean reparado;

	/**
   	* Constructor.
   	*/
  	
	public Vehiculo(String laMatricula, String elModelo, String elTipo, String laFechaCompra){

    		matricula=laMatricula;
    		modelo=elModelo;
    		tipo=elTipo;
    		fechaCompra=laFechaCompra;
    		reparado=false;
  	}
  
  	/**
   	* M�todo que, mediante equals, compara las matr�culas para evitar que haya 2 iguales.
   	* @param aComparar String
   	* @return comparacion boolean
   	*/
  	
	public boolean compararMatricula(String aComparar){

    		boolean comparacion=false;
    		if(matricula.equals(aComparar)==true){
      			comparacion=true;
    		} 
    		return comparacion;
  	}	
  	
  	/**
   	* M�todo para imprimir por pantalla las caracter�sticas de un veh�culo.
   	*/
  	
	public void mostrarVehiculo(){

    		System.out.print("\nMatricula: "+matricula+",  Modelo: "+modelo+", Tipo: "+tipo+",  Fecha de Compra: "+fechaCompra);
		//Evitamos que en la impresion por pantalla aparezca como el estado "true" o "false"
		if(reparado==false){
			System.out.println(" ,Estado: Averiado.");
			return;
		}
		System.out.println(" ,Estado: Reparado.");
  	}

	/**
   	* M�todo para modificar el estado de reparaci�n cuando el vehiculo se ha reparado por finalizaci�n del parte de trabajo.
   	*/
  	public void repararVehiculo(){
  		reparado=true;
  	}

	/**
   	* M�todo que modifica el estado de reparaci�n cuando se entra en el taller (alta de parte de reparacion).
   	*/
  	public void averiarVehiculo(){
  		reparado=false;
  	}
  	
	/**
   	* M�todo que modifica los datos del veh�culo.
	* @param modeloCambio String
   	* @param fechaCompraCambio int
   	* @param tipoCambio String
   	*/
	public void modificarDatosVehiculo(String modeloCambio, String fechaCompraCambio, String tipoCambio){

  		modelo=modeloCambio;
  		fechaCompra=fechaCompraCambio;
  		tipo=tipoCambio;
  	} 
  	
	/*
   	* M�todo para devolver la matr�cula del veh�culo.
   	* @return matricula String
   	*/  	
	public String devolverMatricula(){
  		return matricula;
  	}
  
  	/**
   	* M�todo para devolver el modelo del veh�culo.
   	* @return modelo String
   	*/
  	public String devolverModelo(){
  		return modelo;
  	}
  
  	/**
   	* M�todo para devolver el tipo del veh�culo.
   	* @return tipo string
   	*/
  	public String devolverTipo(){
  		return tipo;
  	} 
	
	/**
   	* M�todo para devolver la fecha de compra del veh�culo.
   	* @return fechaCompra string
   	*/
  	public String devolverFechaCompra(){
  		return fechaCompra;
  	} 
	
}
