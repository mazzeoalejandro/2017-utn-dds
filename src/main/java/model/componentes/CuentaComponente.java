package model.componentes;

import java.math.BigDecimal;

import model.repositories.RepositorioCuentas;

public class CuentaComponente implements Componente{
	
	private String tipoDeCuenta;

	@Override
	public BigDecimal getValor(String periodo, String empresa) {
		return RepositorioCuentas.getInstance()
				   .getValorDeCuentaPorTipoEmpresaYPeriodo(tipoDeCuenta, empresa, periodo);
	}

	public CuentaComponente(String tipoCuenta) {
		super();
		this.tipoDeCuenta = tipoCuenta;
	}
}