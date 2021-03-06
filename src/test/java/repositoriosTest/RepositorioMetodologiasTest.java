package repositoriosTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ExceptionsPackage.MetodologiaNotFoundException;
import model.Indicador;
import model.Metodologia;
import model.Criterio;
import model.Regla;
import model.ReglaComparativa;
import model.ReglaTaxativa;
import model.repositories.RepositorioCuentas;
import model.repositories.RepositorioEmpresas;
import model.repositories.RepositorioIndicadores;
import model.repositories.RepositorioMetodologias;

public class RepositorioMetodologiasTest {
	RepositorioCuentas repositorioCuentas;
	RepositorioIndicadores repositorioIndicadores;
	RepositorioEmpresas repositorioEmpresas;
	RepositorioMetodologias repositorioMetodologias;
	Indicador indicador0 = new Indicador("Indicador0", "EBITDA + 1");
	Indicador indicador1 = new Indicador("Indicador1", "EBITDA + 2");
	ReglaComparativa reglaComparativa = new ReglaComparativa("regla01",indicador0,Criterio.MAYOR);
	BigDecimal valor = new BigDecimal(100);
	ReglaTaxativa reglaTaxativa = new ReglaTaxativa("regla02", indicador1, '>', valor);
	List<Regla> reglas = new ArrayList<Regla>();
	Metodologia metodologia0 = new Metodologia("metodologia0",reglas);
	List<Indicador> indicadores = new ArrayList<>();
	
	@Before
	public void setUp() {
		repositorioIndicadores = RepositorioIndicadores.getInstance();
		indicadores.add(indicador0);
		indicadores.add(indicador1);
		repositorioIndicadores.agregarIndicadores(indicadores);
		repositorioMetodologias = RepositorioMetodologias.getInstance();
		reglas.add(reglaComparativa);
		reglas.add(reglaTaxativa);
		repositorioMetodologias.add(metodologia0);
	}
	
	@After	
	public void limpiarRepositorios() {
		repositorioMetodologias.limpiarRepositorio();
		repositorioIndicadores.limpiarRepositorio();
	}
	
	@Test
	public void agregarMetodologia() {
		assertEquals(repositorioMetodologias.getAll().size(),1);
	}
	
	@Test
	public void nombresDeMetodologiasOK() {
		List<String> nombres = new ArrayList<String>();
		nombres.add("metodologia0");
		assertEquals(repositorioMetodologias.getNombresDeMetodologias(),nombres);
	}
	
	@Test
	public void metodologiaPorNombreOK() {
		assertTrue(repositorioMetodologias.getMetodologiaPorNombre("metodologia0") instanceof Metodologia);
	}
	
	@Test(expected = MetodologiaNotFoundException.class)
	public void metodologiaPorNombreNoEncontrada() {
		repositorioMetodologias.getMetodologiaPorNombre("noExiste");
	}

	@Test
	public void crearMetodologiaConElMismoNombreQueUnaYaExistente() {
		assertTrue(repositorioMetodologias.existeNombreMetodologia("metodologia0"));
	}
}