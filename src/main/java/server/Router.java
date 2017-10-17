package server;

import controllers.LoginController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();

		Spark.staticFiles.location("/templates");
		
		LoginController proyectosController = new LoginController();
		
		Spark.get("/", LoginController::login, engine);
/*		Spark.get("/proyectos", proyectosController::listar, engine);
		Spark.get("/proyectos/new", proyectosController::nuevo, engine);
		Spark.get("/proyectos/:id", proyectosController::mostrar, engine);
		Spark.post("/proyectos", proyectosController::crear);
*/	}
}
