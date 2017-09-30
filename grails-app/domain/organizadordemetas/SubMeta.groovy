package organizadordemetas

class SubMeta {
  	Estado estado
  	List<SubMeta> subMetasOpocionales
  	List<SubMeta> subMetasObligatorias
  	List<Objetivo> listeners
  	String nombre
  	String descripcion

  	public SubMeta (String nombre, String descripcion) {
  		subMetasOpocionales = []
  		subMetasObligatorias = []
  		listeners = []
		estado = Estado.PENDIENTE

  		this.nombre = nombre
  		this.descripcion = descripcion
  	}

  	public void agregarSubMeta(SubMeta subMeta, boolean obligatorio) {
  		if (this.estaCompleta()) {
  			throw new CambioEstadoInvalido("No se puede agregar tarea portque esta finalizada/cancelada")
  		}
  		if (estado != Estado.PENDIENTE && obligatorio) {
  			throw new CambioEstadoInvalido("No se puede agregar tarea obligatoria cuando ya se comenzo")
  		}

  		if (obligatorio) {
  			subMetasObligatorias.add(subMeta)
  		} else {
  			subMetasOpocionales.add(subMeta)
  		}
  	}

  	public void agregarSubMetaObligatoria(SubMeta subMeta) {
  		this.agregarSubMeta(subMeta, true)
  	}

  	public void agregarSubMetaOpcional(SubMeta subMeta) {
  		this.agregarSubMeta(subMeta, false)
  	}

  	protected void informarCambioEstado() {
  		for(Objetivo listener : listeners) {
  			listener.informar()
  		}
  	}

  	protected void agragarListener(Objetivo listener) {
  		listeners.add(listener)
  	}

  	protected boolean validarCambiarEstado(Estado nuevoEstado) {
  		switch(nuevoEstado) {
  			case Estado.CANCELADA:
  				return estado != Estado.FINALIZADA;

  			case Estado.FINALIZADA:
  				if (estado != Estado.EN_EJECUCION) return false;
  				for(SubMeta opcional : subMetasOpocionales) {
  					if (!this.estaCompleta())
  						return false
  				}
  				return true

  			case Estado.EN_EJECUCION:
  				if (estado != Estado.PENDIENTE) return false;
  				for(SubMeta opcional : subMetasObligatorias) {
  					if (!this.estaCompleta())
  						return false
  				}
  				return true

  			case Estado.PENDIENTE:
  				return false
  		}
  		throw new CambioEstadoInvalido("El estado que se utilizo no es valido")
  	}
	
	protected boolean estaCompleta() {
		return ((estado == Estado.CANCELADA) || (estado == Estado.FINALIZADA))
	}

	static constraints = {
	}
}
