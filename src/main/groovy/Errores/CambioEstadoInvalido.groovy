package organizadordemetas

class CambioEstadoInvalido extends Exception {
	public CambioEstadoInvalido() {}
	public CambioEstadoInvalido(String message) {
		super(message)
	}
}