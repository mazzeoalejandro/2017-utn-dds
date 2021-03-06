package ui.vm;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.BasicConfigurator;
import org.uqbar.commons.utils.Observable;

import model.Cuenta;
import model.Empresa;
import model.repositories.RepositorioCuentas;
import model.repositories.RepositorioEmpresas;

@Observable
public class ConsultaDeCuentasViewModel {

	private String empresa = "", tipoCuenta = "", periodo = "", valor = "";
	private List<Cuenta> cuentas = RepositorioCuentas.getInstance().getCuentas();
	private List<String> empresas = RepositorioEmpresas.getInstance().getAll().stream().map(Empresa::getNombre).collect(Collectors.toList());
	private List<String> periodos = RepositorioCuentas.getInstance().getPeriodosDeCuenta();

	public void setUp() {
		BasicConfigurator.configure();
	}

	public void consultarCuenta() {
		Empresa empresaParaCalcular = null;
		if (!empresa.isEmpty()){
			empresaParaCalcular = RepositorioEmpresas.getInstance().getEmpresaPorNombre(empresa);
		}
		if (!valor.isEmpty()){
			cuentas = RepositorioCuentas.getInstance().filtrarCuentas(tipoCuenta, empresaParaCalcular, periodo, new BigDecimal(valor));
		} else {
			cuentas = RepositorioCuentas.getInstance().filtrarCuentas(tipoCuenta, empresaParaCalcular, periodo, null);
		}
		
	}

	public void ordenarCuentasPorTipo() {
		cuentas = RepositorioCuentas.getInstance().getCuentasPorTipo();
	}

	public void ordenarCuentasPorPeriodo() {
		cuentas = RepositorioCuentas.getInstance().getCuentasPorPeriodo();
	}

	public void ordenarCuentasPorValor() {
		cuentas = RepositorioCuentas.getInstance().getCuentasPorValor();
	}

	//GETTERS
	public List<String> getEmpresas() {
		
		return empresas;
	}

	public List<String> getPeriodos() {
		return periodos;
	}
	
	public String getEmpresa() {
		return empresa;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public String getValor() {
		return valor;
	}

	public String getPeriodo() {
		return periodo;
	}


	// Setters
	public void setEmpresas(List<String> empresas) {
		this.empresas = empresas;
	}
	
	public void setPeriodos(List<String> periodos) {
		this.periodos = periodos;
	}
	
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public List<Cuenta> getCuentas() {
		return this.cuentas;
	}
	
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
}
