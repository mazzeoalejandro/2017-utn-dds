package dtos;

public class CargaDeCuentasDTO implements DTO{

	private String pathFile="";
	
	public void setPathFile(String location) {
		pathFile = location;
	}
	
	public String getPathFile() {
		return pathFile;
	}
}