package ExceptionsPackage;

@SuppressWarnings("serial")
public class IndicadorNotFoundException extends RuntimeException {

	public IndicadorNotFoundException(String msg) {
		super(msg);
	}
}
