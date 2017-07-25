package ui.vm;

import org.uqbar.commons.model.UserException;

import model.Regla;

public class CargaDeMetodologiasViewModel {
	
	public String nombre = "";
	public Regla regla;
	
	// Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setRegla(Regla reglaNueva) {
		regla = reglaNueva;
	}
	
	// Getters

	public String getNombre() {
		return nombre;
	}
	
	public Regla getRegla() {
		return regla;
	}
	
	public void cargarMetodologia(){
		if (nombre.isEmpty())
			throw new UserException("Debe proveer un nombre para la metodologia");
		//if (formulaIngresada.isEmpty())
			//throw new UserException("Ingrese una formula");
		//else
			//TODO: guardar metodologia;
	}
}
