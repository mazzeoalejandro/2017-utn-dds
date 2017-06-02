package utils;

import java.io.File;
import java.util.List;

public class Archivo {
	
	/* Se le env�a un objeto y lo a�ade al final de un archvo en formato json dejando el archivo
	   en el formato correcto para consultarlo despu�s  */
	public static <T> void archivarObjetos(List<T> objetos, String path){
		String jsonAGuardar = JsonCreator.getJson(objetos);
		
		ManejoDeArchivos.sobreescribirArchivo(path,jsonAGuardar);
	}
}
