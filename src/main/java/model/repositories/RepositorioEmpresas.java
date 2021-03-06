package model.repositories;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ExceptionsPackage.EmpresaNotFoundException;
import model.Empresa;

public class RepositorioEmpresas extends Repositorio<Empresa>{
	
	private static RepositorioEmpresas instance;
	
	public static synchronized RepositorioEmpresas getInstance() {
		if (instance == null)
			instance = new RepositorioEmpresas();
		return instance;
	}
	
	public RepositorioEmpresas() {
		super();
	}

	@Override
	protected Class<Empresa> getEntityType() {
		return Empresa.class;
	}

	public boolean existeEmpresa(Empresa empresa) {
		List<Empresa> empresas = this.searchByExample(empresa);
		if ( empresas.isEmpty())
		{
			return false;
		}
		else
		{
			empresa.setId(empresas.get(0).getId());
			return true;	
		}
	}
	
	public void agregarEmpresas(List<Empresa> _empresas) {
		Session session = sessionFactory.openSession();
		_empresas.forEach(emp -> this.saveOrUpdateWithSession(emp, session));
		session.close();
	}
	
	@Override
	protected void addCriteriaToSearchByExample(Criteria criteria, Empresa empresa) {
		if (empresa.getId() != null) {
			criteria.add(Restrictions.eq("id", empresa.getId()));
		}
		if (empresa.getNombre() != null) {
			criteria.add(Restrictions.eq("nombre", empresa.getNombre()));
		}
	}

	public Empresa getEmpresaPorNombre(String nombreEmpresa) {
		Empresa empresaEjemplo = new Empresa(null, nombreEmpresa);
		List<Empresa> resultadoBusqueda = RepositorioEmpresas.getInstance().searchByExample(empresaEjemplo);
		if (!resultadoBusqueda.isEmpty()) {
			return resultadoBusqueda.get(0);
		}
		
		throw new EmpresaNotFoundException("No se encuentra una Empresa con nombre: " + nombreEmpresa);
	}

	public Empresa obtenerPorNombreODevolverNueva(String nombre) {
		Empresa empresaEjemplo = new Empresa(null, nombre);
		List<Empresa> resultadoBusqueda = RepositorioEmpresas.getInstance().searchByExample(empresaEjemplo);
		if (!resultadoBusqueda.isEmpty()) {
			return resultadoBusqueda.get(0);
		} else {
			RepositorioEmpresas.getInstance().add(empresaEjemplo);
			return empresaEjemplo;
		}
	}
	
	public void limpiarRepositorio() {
		this.getAll().forEach(this::delete);
	}

	@Override
	protected List<Empresa> getAllFromUserId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
