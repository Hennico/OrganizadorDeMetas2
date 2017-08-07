package organizadordemetas

class Paso {
	Estado estado
  	List<Paso> plan
  	List<Objetivo> listeners
  	String nombre
  	String descripcion
	Obligatoriedad obligatoriedad

  	public Paso (String nombre, String descripcion, Obligatoriedad obligatoriedad) {
	  	plan = []
  		listeners = []
     		estado = Estado.PENDIENTE
  		this.nombre = nombre
  		this.descripcion = descripcion
		this.obligatoriedad = obligatoriedad
  	}

  	public boolean esEstadoTerminal() {
		return (estado == Estado.FINALIZADA) || (estado == Estado.CANCELADA)
  	}

  	public void agregarPaso (Paso paso) {
  		if (this.esEstadoTerminal())
			throw new Exception("No se puede agregar tarea portque esta finalizada/cancelada")
  		if (estado != Estado.PENDIENTE && paso.obligatoriedad == NECESARIO)
  			throw new Exception("No se puede agregar tarea obligatoria cuando ya se comenzo")

  		plan.add(paso)
  	}

  	protected void informarCambio() {
  		for(Objetivo listener : listeners) {
  			listener.notificar()
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
				return (estado == Estado.EN_EJECUCION) && (!plan.any { !it.esEstadoTerminal()})

  			case Estado.EN_EJECUCION:
				return (estado == Estado.PENDIENTE) && (!plan.any { !it.esEstadoTerminal() && it.obligatoriedad == Obligatoriedad.NECESARIO})

  			case Estado.PENDIENTE:
  				return false
  		}
  		throw new Exception("El estado que se utilizo no es valido")
  	}

      static constraints = {
      }
}
