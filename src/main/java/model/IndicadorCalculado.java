package model;

import java.math.BigDecimal;

import ExceptionsPackage.CuentaNotFoundException;
import model.repositories.RepositorioCuentas;
import utils.CalculadorDeIndicadores;

public class IndicadorCalculado extends Indicador {

	private BigDecimal valor;
	private String cuentas;
	private String empresa;
	private String periodo;

	public IndicadorCalculado(Indicador indicador, String empresa, String periodo, int id) {
		super(indicador.getNombre(), indicador.getFormula());
		this.setId(id);
		this.empresa = empresa;
		this.periodo = periodo;
		cuentas = CalculadorDeIndicadores.getInstance().obtenerCuentasSeparadasPorComa(indicador);
		valor = CalculadorDeIndicadores.getInstance().calcularIndicador(indicador, empresa, periodo);
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getCuentas() {
		return cuentas;
	}

	public void setCuentas(String cuentas) {
		this.cuentas = cuentas;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
