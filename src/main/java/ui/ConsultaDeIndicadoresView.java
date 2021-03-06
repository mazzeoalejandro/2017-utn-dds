package ui;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MessageBox;
import org.uqbar.arena.windows.WindowOwner;
import model.IndicadorCalculado;
import ui.vm.ConsultaDeIndicadoresViewModel;

@SuppressWarnings("serial")
public class ConsultaDeIndicadoresView extends Dialog<ConsultaDeIndicadoresViewModel> {

	public ConsultaDeIndicadoresView(WindowOwner owner) {
		super(owner, new ConsultaDeIndicadoresViewModel());
	}

	@Override
	protected void createFormPanel(Panel consultaPanel) {

		setTitle("Consulta de indicadores");
		consultaPanel.setLayout(new VerticalLayout());

		Panel empresaPanel = new Panel(consultaPanel);
		empresaPanel.setLayout(new HorizontalLayout());

		new Label(empresaPanel).setText("Empresa: ").setFontSize(11).setWidth(200);

		Selector<IndicadorCalculado> selectorEmpresa = new Selector<IndicadorCalculado>(empresaPanel)
			    .allowNull(false);
		selectorEmpresa.setWidth(150).bindValueToProperty("empresa");
		selectorEmpresa.bindItemsToProperty("empresas");
	
		Panel periodoPanel = new Panel(consultaPanel);
		periodoPanel.setLayout(new HorizontalLayout());

		new Label(periodoPanel).setText("Periodo: ").setFontSize(11).setWidth(200);
		
		Selector<IndicadorCalculado> selectorPeriodo = new Selector<IndicadorCalculado>(periodoPanel)
			    .allowNull(false);
		selectorPeriodo.setWidth(100).bindValueToProperty("periodo");
		selectorPeriodo.bindItemsToProperty("periodos");

		Panel tIndicadoresPanel = new Panel(consultaPanel);
		tIndicadoresPanel.setLayout(new VerticalLayout());

		new Label(tIndicadoresPanel).setText("Indicadores").setFontSize(11);
		this.tablaResultadoIndicadores(tIndicadoresPanel);

	}

	protected void tablaResultadoIndicadores(Panel consultaPanel) {
		Table<IndicadorCalculado> tableIndicador = new Table<IndicadorCalculado>(consultaPanel, IndicadorCalculado.class);
		tableIndicador.setHeight(200);
		tableIndicador.setWidth(800);
		tableIndicador.bindItemsToProperty("indicadores");

		this.tablaIndicadores(tableIndicador);
	}

	@Override
	protected void addActions(Panel actions) {
		
		new Button(actions).setCaption("Aplicar").onClick(() ->this.consultarIndicador()).setAsDefault();

		new Button(actions).setCaption("Salir").onClick(this::cancel);
	}
	
	private void consultarIndicador() {
		try {
			getModelObject().consultarIndicador();
		} catch (Exception e) {
			mostrarMensajeError(e.getMessage());
		}
	}
	
	protected void mostrarMensajeError(String message) {
		MessageBox messageBox = new MessageBox(this, MessageBox.Type.Error);
		messageBox.setMessage(message);
		messageBox.open();
	}
	protected void tablaIndicadores(Table<IndicadorCalculado> tableIndicador) {
		
		Column<IndicadorCalculado> columnaId = new Column<IndicadorCalculado>(tableIndicador);
		columnaId.setFont(11).setTitle("Id");
		columnaId.setFixedSize(40);
		columnaId.bindContentsToProperty("id");

		Column<IndicadorCalculado> columnaEmpresa = new Column<IndicadorCalculado>(tableIndicador);
		columnaEmpresa.setFont(11).setTitle("Empresa");
		columnaEmpresa.setFixedSize(150);
		columnaEmpresa.bindContentsToProperty("empresa");
		
		Column<IndicadorCalculado> columnaPeriodo = new Column<IndicadorCalculado>(tableIndicador);
		columnaPeriodo.setFont(11).setTitle("Período");
		columnaPeriodo.setFixedSize(100);
		columnaPeriodo.bindContentsToProperty("periodo");
		
		Column<IndicadorCalculado> columnaNombre = new Column<IndicadorCalculado>(tableIndicador);
		columnaNombre.setFont(11).setTitle("Indicador");
		columnaNombre.setFixedSize(180);
		columnaNombre.bindContentsToProperty("nombre");

		Column<IndicadorCalculado> columnaValor = new Column<IndicadorCalculado>(tableIndicador);
		columnaValor.setFont(11).setTitle("Valor");
		columnaValor.setFixedSize(100);
		columnaValor.bindContentsToProperty("valor");
		
		Column<IndicadorCalculado> columnaCuentas = new Column<IndicadorCalculado>(tableIndicador);
		columnaCuentas.setFont(11).setTitle("Cuentas utilizadas");
		columnaCuentas.setFixedSize(250);
		columnaCuentas.bindContentsToProperty("cuentas");
	}

}