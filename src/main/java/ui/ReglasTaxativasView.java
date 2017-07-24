package ui;

import java.awt.Color;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.WindowOwner;

import model.Criterio;
import model.Indicador;
import model.IndicadorCalculado;
import ui.vm.ConsultaDeIndicadoresViewModel;
import ui.vm.ReglasTaxativasViewModel;

public class ReglasTaxativasView extends Dialog<ReglasTaxativasViewModel> {

	public ReglasTaxativasView(WindowOwner owner) {
		super(owner, new ReglasTaxativasViewModel());
	}
	
	@Override
	protected void createFormPanel(Panel taxativasPanel) {
		setTitle("Reglas taxativas");
		taxativasPanel.setLayout(new VerticalLayout());

		new Label(taxativasPanel).setText("Crear regla taxativa").setFontSize(13).setWidth(500);
		
		Panel nombrePanel = new Panel(taxativasPanel);
		
		nombrePanel.setLayout(new HorizontalLayout());		

		new Label(nombrePanel).setText("Nombre: ").setFontSize(11).setWidth(200);

		new TextBox(nombrePanel).setWidth(200).setHeigth(20).bindValueToProperty("nombreRegla");
		
		Panel descripcionPanel = new Panel(taxativasPanel);
		
		descripcionPanel.setLayout(new HorizontalLayout());
		
		new Label(descripcionPanel).setText("Indicador").setFontSize(11).setWidth(200);
		new Label(descripcionPanel).setText("Comparador").setFontSize(11).setWidth(200);
		new Label(descripcionPanel).setText("Valor").setFontSize(11).setWidth(200);
		
		Panel armadoPanel = new Panel(taxativasPanel);
		
		armadoPanel.setLayout(new HorizontalLayout());

		Selector<Indicador> selectorIndicador = new Selector<Indicador>(armadoPanel)
			    .allowNull(false);
		selectorIndicador.setHeigth(11).setWidth(200).bindValueToProperty("indicador");
		selectorIndicador.bindItemsToProperty("indicadores");
		
		Selector<Criterio> selectorCriterio = new Selector<Criterio>(armadoPanel)
			    .allowNull(false);
		selectorCriterio.setHeigth(11).setWidth(200).bindValueToProperty("comparador");
		selectorCriterio.bindItemsToProperty("comparadores");
		
		new TextBox(armadoPanel).setHeigth(11).setWidth(200).setHeigth(22).bindValueToProperty("valorAComparar");
		
		new Button(taxativasPanel).setCaption("Agregar").onClick(()-> this.agregarReglaTaxativa()).setFontSize(11)
		.setBackground(Color.cyan).setWidth(250);
		
		new Button(taxativasPanel).setCaption("Crear una nueva regla").setFontSize(11)
				.setBackground(Color.cyan).setWidth(250);	
	}

	private void agregarReglaTaxativa() {
		try {
			getModelObject().agregarRegla();
		} catch (Exception e) {
			e.printStackTrace();
			mostrarMensajeError(e.getMessage());
		}
	}

	protected void mostrarMensajeError(String message) {
		MessageBox messageBox = new MessageBox(this, MessageBox.Type.Error);
		messageBox.setMessage(message);
		messageBox.open();
	}
}