package tp1;

import model.RepositorioDeCuentas;
import utils.PrepararRepositorio;

import org.junit.Test;
import static org.junit.Assert.*;

public class PrepararRepositorioTest {
	String rutaDelArchivoBueno="./Archivos de prueba/ArchivoDeCuentasParaTestsBueno.txt";
	String rutaDeArchivoMalo = "./Archivos de prueba/ArchivoDeCuentasParaTestsMalo.txt";
	String rutaDeArchivoInexistente = "./Archivos de prueba/Necronomicon.txt";
	
	@Test
	public void cargarCuentasDeArchivoBueno(){
		PrepararRepositorio.cargarCuentasDeArchivo(rutaDelArchivoBueno);
		RepositorioDeCuentas repositorio = PrepararRepositorio.getRepositorio();
		assertTrue(repositorio.size()==3);
	}
	@Test(expected = Error.class)
	public void cargarCuentasDeArchivoInexistente(){
		PrepararRepositorio.cargarCuentasDeArchivo(rutaDeArchivoInexistente);
	}
	@Test(expected = Error.class)
	public void cargarCuentasDeArchivoMalo(){
		PrepararRepositorio.cargarCuentasDeArchivo(rutaDeArchivoMalo);
	}
}
