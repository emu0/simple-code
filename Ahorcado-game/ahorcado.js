var palabra;
var palabra_oculta;
var string_letras;
var letra;
var intentos_restantes;
var letras_restantes;
var array_palabras = ["telematica", "aplicada", "universidad", "granada", "ingenieria", "telecomunicaciones", "departamento", "teoria", "señal", "comunicacion"];

function iniciar(){
	$("#usuario").html("<p>Pulse el botón de inicio para generar una palabra</p><input value='Generar palabra.' type='submit' onclick='generarPalabra()'>");
}

function generarDibujo(){
	var dibujo;
	switch(intentos_restantes){
		case 0: {
			dibujo = " |/ | <br> | O <br> | /|( <br> | /(  <br> |_____";
			break;
		}
		case 1: {
			dibujo = " |/ | <br> | O <br> | /|( <br> | /  <br> |_____";
			break;
		}
		case 2: {
			dibujo = " |/ | <br> | O <br> | /|( <br> |  <br> |_____";
			break;
		}
		case 3: {
			dibujo = " |/ | <br> | O <br> | /| <br> | <br> |_____";
			break;
		}
		case 4: {
			dibujo = " |/ | <br> | O <br> | | <br> | <br> |_____";
			break;
		}
		case 5: {
			dibujo = " |/ | <br> | O <br> | <br> | <br> |_____";
			break;
		} 
		default: dibujo = " |/ | <br> | <br> | <br> |  <br> |_____";
	}
	$("#dibujo").html(dibujo);
}

function generarPalabra(){
	intentos_restantes = 6;
	palabra=array_palabras[Math.floor(9*Math.random())];
	palabra_oculta="";
	string_letras="";
	letras_restantes=palabra.length;
	for(i in palabra){
		palabra_oculta=palabra_oculta.concat("-");
	}
	actualizar();
}

function introducirDatos(){
	var html = "<p> Intentos restantes: " + intentos_restantes + "</p><input type='text' id='letra' autocomplete='on' title='Nueva Letra' placeholder='Letra'><input type='submit'  value='comprobar' onclick='buscarLetra()'>";
	return html;
}

function buscarLetra(){
	letra = document.getElementById('letra').value;
	var indice = 0;
	var indice_turno_anterior = 0;
	var repetidas = 0;
	if(letra == ""){
		return;
	}
	else if(letra.length > 1){
		return;	
	}
	else if(string_letras.indexOf(letra) != -1){
		return;	
	}
	else{	
		for (i in palabra){
			if(palabra[i]==letra){
				repetidas = repetidas +1;			
			}
		}
		letras_restantes=letras_restantes-repetidas;
		if(repetidas > 0){
			do{
				indice = palabra.slice(indice, palabra.length).indexOf(letra);
				indice = indice + indice_turno_anterior;
				palabra_aux=palabra_oculta;
				palabra_oculta=palabra_oculta.slice(0,indice);
				palabra_oculta=palabra_oculta.concat(letra);
				palabra_oculta=palabra_oculta.concat(palabra_aux.slice(indice+1, palabra_aux.length));
				repetidas = repetidas -1;
				indice=indice+1;
				indice_turno_anterior = indice;
			}while(repetidas > 0);
			string_letras=string_letras.concat(letra);
		}
		else{
			intentos_restantes=intentos_restantes-1;
		}		
		actualizar();
	}
}

function actualizar(){
	$("#dibujo").html(generarDibujo());
	$("#palabra").html("<p>"+ palabra_oculta + "</p>");
	$("#usuario").html(introducirDatos());
	if(intentos_restantes == 0){
		terminar();
	}
	else if(letras_restantes == 0){
		terminar();	
	}	
}

function terminar(){
	$("#dibujo").html(generarDibujo());
	$("#palabra").html("<p>"+ palabra + "</p>");
	$("#usuario").html("<p>Juego terminado</p><p>Pulse el boton de inicio para generar una nueva palabra</p><input value='Generar palabra.' type='submit' onclick='generarPalabra()'>");
}
