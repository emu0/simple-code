/**
* Clase Vehiculo.
* @author Emilio Martínez Pérez.
*/

public class Vehiculo{
  
	//Los acentos y caracteres especiales en las impresiones por pantalla han sido omitidos.
	/**
   	* Datos miembro de los vehículos.
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
   	* Método que, mediante equals, compara las matrículas para evitar que haya 2 iguales.
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
   	* Método para imprimir por pantalla las características de un vehículo.
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
   	* Método para modificar el estado de reparación cuando el vehiculo se ha reparado por finalización del parte de trabajo.
   	*/
  	public void repararVehiculo(){
  		reparado=true;
  	}

	/**
   	* Método que modifica el estado de reparación cuando se entra en el taller (alta de parte de reparacion).
   	*/
  	public void averiarVehiculo(){
  		reparado=false;
  	}
  	
	/**
   	* Método que modifica los datos del vehículo.
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
   	* Método para devolver la matrícula del vehículo.
   	* @return matricula String
   	*/  	
	public String devolverMatricula(){
  		return matricula;
  	}
  
  	/**
   	* Método para devolver el modelo del vehículo.
   	* @return modelo String
   	*/
  	public String devolverModelo(){
  		return modelo;
  	}
  
  	/**
   	* Método para devolver el tipo del vehículo.
   	* @return tipo string
   	*/
  	public String devolverTipo(){
  		return tipo;
  	} 
	
	/**
   	* Método para devolver la fecha de compra del vehículo.
   	* @return fechaCompra string
   	*/
  	public String devolverFechaCompra(){
  		return fechaCompra;
  	} 
	
}
