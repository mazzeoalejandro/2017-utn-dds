package model;

import java.math.BigDecimal;

import javax.persistence.*;

import org.uqbar.commons.utils.Observable;

@Observable
@Entity
@DiscriminatorValue("T")
public class ReglaTaxativa extends Regla {

	char comparador;
	BigDecimal valorAComparar;

	public ReglaTaxativa(){
	}
	
	public ReglaTaxativa(String nombre, Indicador indicador, char comparador, BigDecimal valorAComparar) {
		this.nombre = nombre;
		this.indicador = indicador;
		this.comparador = comparador;
		this.valorAComparar = valorAComparar;
	}

	public char getComparador() {
		return comparador;
	}
	
	public void setComparador(char _comparador) {
		comparador = _comparador;
	}

	public BigDecimal getValorAComparar() {
		return valorAComparar;
	}
	
	public void setValorAComparar(BigDecimal _valorAComparar) {
		valorAComparar = _valorAComparar;
	}

}
