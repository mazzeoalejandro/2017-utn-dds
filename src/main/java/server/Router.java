package server;

import controllers.*;
import dtos.PathFileTxtJson;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;
import utils.AppData;

public class Router {
	
	private static PathFileTxtJson dtoEmpresas = new PathFileTxtJson("./Archivos de la App/Database Empresas.txt");
	private static PathFileTxtJson dtoCuentas = new PathFileTxtJson("./Archivos de la App/Database Cuentas.txt");
	private static PathFileTxtJson dtoIndicadores = new PathFileTxtJson("./Archivos de la App/Database Indicadores.txt");
	
	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();

		Spark.staticFiles.location("/templates");
		
		inicializarDatos();
		
		LoginController proyectosController = new LoginController();
		
		Spark.get("/", LoginController::show, engine);
		Spark.post("/", LoginController::login);
		Spark.get("/home", HomeController::home, engine);
		//Cuentas		
		Spark.get("/cuentas", CuentasController::listar, engine); //Acá vamos al clickear en "consulta" en la tab "Cuentas"
		Spark.get("/cuentas/listado", CuentasController::mostrar, engine); //Acá vamos al filtrar la lista de cuentas en la vista
		Spark.get("/cuentas/new", CuentasController::nuevo, engine); //Acá vamos al clickear en "carga" en la tab "Cuentas"
		Spark.post("/cuentas", CuentasController::crear); //Acá vamos a entrar al cargar el archivo de cuentas
		//Indicadores
		Spark.get("/indicadores", IndicadoresController::listar, engine); //Acá vamos al clickear en "consulta" en la tab "Indicadores"
		Spark.get("/indicadores/listado", IndicadoresController::mostrar, engine); //Acá vamos al filtrar la lista de indicadores en la vista
		Spark.get("/indicadores/new", IndicadoresController::nuevo, engine); //Acá vamos al clickear en "carga" en la tab "Indicadores"
		Spark.post("/indicadores", IndicadoresController::crear); //Acá vamos a entrar al cargar el indicador en la vista
		//Empresas
		Spark.get("/empresas/new", EmpresasController::nuevo, engine); //Acá vamos al clickear en "carga" en la tab "Empresas"
		Spark.post("/empresas", EmpresasController::crear); //Acá vamos a entrar al cargar la empresa en la vista
		//Metodologias
		Spark.get("/metodologias", MetodologiasController::listar, engine); //Acá vamos al clickear en "consulta" en la tab "Metodologias"
		Spark.get("/metodologias/listado", MetodologiasController::mostrar, engine); //Acá vamos al filtrar la lista de metodologias en la vista
		Spark.get("/metodologias/new", MetodologiasController::nuevo, engine); //Acá vamos al clickear en "carga" en la tab "Metodologias"
		Spark.post("/metodologias", MetodologiasController::crear); //Acá vamos a entrar al cargar la metodologia (Click en Finalizar) en la vista
		//Reglas
		Spark.get("/metodologias/new/reglas", MetodologiasController::listarReglas, engine); //Acá vamos al clickear en "continuar" al ingresar el nombre de la metodología
		Spark.get("/metodologias/new/reglas/taxativa", MetodologiasController::nuevaTaxativa, engine); //Acá vamos al clickear en el botón "Taxativa"
		Spark.get("/metodologias/new/reglas/comparativa", MetodologiasController::nuevaComparativa, engine); //Acá vamos al clickear en el botón "Comparativa"
		Spark.post("/metodologias/new/reglas", MetodologiasController::mostrar, engine); //Acá vamos al crear una regla, la pasamos por post para que la agregue
	}

	private static void inicializarDatos() {
		AppData.getInstance().setInicializacionDeEmpresas(dtoEmpresas);
		AppData.getInstance().setInicializacionDeCuentas(dtoCuentas);
		AppData.getInstance().setInicializacionDeIndicadores(dtoIndicadores);
		AppData.getInstance().inicializarRepositorios();		
	}
}
