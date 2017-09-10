package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public abstract class Regla {
	
	@Id
	@GeneratedValue
	private int id;
	String nombre;
	
	@ManyToOne
	Indicador indicador;

	public String getNombre(){
		return nombre;
	}

	public Indicador getIndicador() {
		return indicador;
	}	

}
