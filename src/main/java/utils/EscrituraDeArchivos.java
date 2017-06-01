package utils;

import java.io.*;

import ExceptionsPackage.RutaDeArchivoInvalidaExeption;

//Los m�todos se dejan p�blicos para poder testearlos

public class EscrituraDeArchivos {
	
	//Recibe un Json y sobreescribe (o crea) todo el archivo
	public static void sobreescribirArchivo(String path, String stringAGuardar){
		FileWriter file = null;
		
		try {
			file = new FileWriter(path);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RutaDeArchivoInvalidaExeption("Ruta de archivo invalida");
			//No les puse UserExeption porque esto lo har�a el sistema por dentro
		}
		realizarLaGrabacion(file, stringAGuardar);
	}
	
	//Agregar cosas al final del archivo
	public static void agregarAlArchivo(String path, String stringAGuardar){
		FileWriter file = null;
		
		try {
			file = new FileWriter(path,true);
		} catch (IOException e) {
			e.printStackTrace();
			new RutaDeArchivoInvalidaExeption("Archivo no encontrado");
		}
		realizarLaGrabacion(file, stringAGuardar);
	}
	
	//Borrar un archivo para los test
	
	public static void borrarArchivo(String path){
		File file = new File(path);
		file.delete();
	}
	
	private static void realizarLaGrabacion(FileWriter f, String stringAGuardar){
		PrintWriter pw = new PrintWriter(f);
		char caracteres[] = stringAGuardar.toCharArray();
		
		for(char caracter:caracteres)
			pw.print(caracter);
		
		try {
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error al guardar el archivo");
		}
	}
}
