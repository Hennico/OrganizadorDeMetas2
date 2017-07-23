package organizadordemetas

class Tarea extends SubMeta{

  public void CambiarEstado(Estado estado) {
		if (this.validarCambiarEstado(estado))
			this.estado == estado
	}

  static constraints = {
  }
}
