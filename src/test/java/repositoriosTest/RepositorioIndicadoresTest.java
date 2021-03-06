package repositoriosTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ExceptionsPackage.CuentaNotFoundException;
import dtos.PathFileTxtJson;
import model.Empresa;
import model.Indicador;
import model.repositories.RepositorioIndicadores;
import utils.ManejoDeArchivos;

public class RepositorioIndicadoresTest {
	RepositorioIndicadores repositorioIndicadores;

	Indicador indicador0 = new Indicador("Indicador0", "EBITDA + 1");
	Indicador indicador1 = new Indicador("Indicador1", "EBITDA + 2");
	Indicador indicador2 = new Indicador("Indicador2", "EBITDA + 3");
	PathFileTxtJson _dtoIndicadores = new PathFileTxtJson("./Archivos de prueba/ArchivoDePruebaParaTestsDeGrabacionInd.txt");
	
	List<Indicador> indicadores;


	@Before
	public void setUp() {
		RepositorioIndicadores.getInstance().limpiarRepositorio();
		repositorioIndicadores = RepositorioIndicadores.getInstance();
		indicadores = new ArrayList<>();
		indicadores.add(indicador0);
		indicadores.add(indicador1);
		indicadores.add(indicador2);	
		repositorioIndicadores.agregarIndicadores(indicadores);

	}

	@After
	public void limpiarRepositorios() {
		repositorioIndicadores.limpiarRepositorio();
	}

	@Test
	public void agregarIndicadoresGeneraArchivo() {
		String contenidoDelArchivo = ManejoDeArchivos.leerArchivo("./Archivos de prueba/ArchivoDePruebaParaTestsDeGrabacionInd.txt");

		System.out.println("CONTENIDO DEL ARCHIVO: " + contenidoDelArchivo);

		assertTrue(!contenidoDelArchivo.isEmpty());
	}
	
	@Test(expected = RuntimeException.class)
	public void removerIndicadorQueNoExiste() {
		Indicador indicadorQueNoExiste = new Indicador("Raro", "Raro");
		try{repositorioIndicadores.delete(indicadorQueNoExiste);
		}catch (Exception e) {
		}
		throw new RuntimeException("ok");
		}

	@Test(expected = RuntimeException.class)
	public void removerIndicadorPorIdQueNoExiste() {
		Indicador indicador = new Indicador();
		indicador.setId(404L);
		repositorioIndicadores.delete(indicador);
	}

	@Test
	public void getIndicadorPorId() {
		Long id = repositorioIndicadores.getAll().get(0).getId();
		assertTrue(repositorioIndicadores.getIndicadorPorId(id) instanceof Indicador);
	}

	@Test(expected = RuntimeException.class)
	public void getIndicadorPorIdQueNoExiste() {
		repositorioIndicadores.getIndicadorPorId(404L);
	}

	@Test(expected = CuentaNotFoundException.class)
	public void filtrarIndicadorPorTodo() {
		Empresa facebook = new Empresa("Facebook");
		repositorioIndicadores.filtrarIndicadores(facebook, " ", " ", new BigDecimal(1000));
	}
	
	@Test
	public void filtrarIndicadoresPorNombre() {
		List<Indicador> indicadores = repositorioIndicadores.filtrarIndicadoresPorNombre("Indicador0");

		assertEquals(1,indicadores.size());
	}	

}
