package tp1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.uqbar.commons.model.UserException;

import ExceptionsPackage.RutaDeArchivoInvalidaExeption;
import utils.ManejoDeArchivos;

//No cambiar los archivos para test ya que los tests van a fallar

public class ManejoDeArchivosTest {
	String rutaDelArchivoAEscribir="./Archivos de prueba/ArchivoDePruebaParaTestsDeGrabacion.txt";
	String rutaDeArchivoMala = "./Ruta Invalida/Necronomicon.txt";
	String jsonMagico = "Un Json Magico";
	
	//Si el archivo no est� lo crea as� que no se prueba por archivo inexistente
	
	@After
	public void borrarArchivo(){
		ManejoDeArchivos.borrarArchivo(rutaDelArchivoAEscribir);
	}
	
	@Test
	public void funcionSobreescribirCreaArchivoSiNoExiste(){
		ManejoDeArchivos.sobreescribirArchivo(rutaDelArchivoAEscribir, jsonMagico);
		
		String contenidoDelArchivo = ManejoDeArchivos.leerArchivo(rutaDelArchivoAEscribir);
		
		assertTrue(contenidoDelArchivo.equals(jsonMagico));
	}
	
	@Test
	public void sobreescribirUnArchivo(){
		ManejoDeArchivos.sobreescribirArchivo(rutaDelArchivoAEscribir, "cualquier cosa a sobreescribir");
		ManejoDeArchivos.sobreescribirArchivo(rutaDelArchivoAEscribir, jsonMagico);
		
		String contenidoDelArchivo = ManejoDeArchivos.leerArchivo(rutaDelArchivoAEscribir);
		
		assertTrue(contenidoDelArchivo.equals(jsonMagico));
	}
	
	@Test
	public void agregarCosasAlFinalDeUnArchivo(){
		ManejoDeArchivos.sobreescribirArchivo(rutaDelArchivoAEscribir, jsonMagico);
		ManejoDeArchivos.agregarAlArchivo(rutaDelArchivoAEscribir, "210");
		
		String contenidoDelArchivo = ManejoDeArchivos.leerArchivo(rutaDelArchivoAEscribir);
		
		assertTrue(contenidoDelArchivo.equals(jsonMagico+"210"));
	}
	
	@Test
	public void funcionAgregarCreaElArchivoSiNoExiste(){
		ManejoDeArchivos.agregarAlArchivo(rutaDelArchivoAEscribir, jsonMagico);
		
		String contenidoDelArchivo = ManejoDeArchivos.leerArchivo(rutaDelArchivoAEscribir);
		
		assertTrue(contenidoDelArchivo.equals(jsonMagico));
	}
	
	@Test(expected = RutaDeArchivoInvalidaExeption.class)
	public void rutaDeArchivoMalaTiraExeptionEnSobreescribir(){
		
		ManejoDeArchivos.sobreescribirArchivo(rutaDeArchivoMala, jsonMagico);
	}
	
	@Test
	public void leerArchivoBueno(){
		String rutaDelArchivoBueno="./Archivos de prueba/ArchivoDeCuentasParaTestsBueno.txt";
		String contenidoDelArchivo = ManejoDeArchivos.leerArchivo(rutaDelArchivoBueno);
		String contenidoEsperado = "[{\"id\":1,\"tipo\":\"EBITDA\",\"empresa\":\"Facebook\",\"periodo\":\"2016\",\"valor\":10000},"
				+ "{\"id\":2,\"tipo\":\"EBITDA\",\"empresa\":\"Facebook\",\"periodo\":\"2017\",\"valor\":99999999},"
				+ "{\"id\":3,\"tipo\":\"EBITDA\",\"empresa\":\"Twitter\",\"periodo\":\"2017\",\"valor\":20}]";
		
		assertTrue(contenidoDelArchivo.equals(contenidoEsperado));
	}
	
	@Test(expected = UserException.class)
	public void FallarAlLeerArchivoIxensistente(){
		String rutaDeArchivoInexistente = "./Archivos de prueba/Necronomicon.txt";
		
		ManejoDeArchivos.leerArchivo(rutaDeArchivoInexistente);
	}
}