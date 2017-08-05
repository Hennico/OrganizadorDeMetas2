package organizadordemetas

class Objetivo extends Paso {

	public void actualizar(String nombre, String descripcion){
		this.nombre = nombre
		this.descripcion = descripcion
	}


	public void inicializar(String nombre, String descripcion, Obligatoriedad obligatoriedad){
	  	plan = []
  		listeners = []
     		estado = Estado.PENDIENTE
  		this.nombre = nombre
  		this.descripcion = descripcion
		this.obligatoriedad = obligatoriedad
	}

	public Objetivo () {
	  	plan = []
  		listeners = []
     		estado = Estado.PENDIENTE
		this.obligatoriedad = Obligatoriedad.NECESARIO
	}

	public Objetivo (String nombre, String descripcion, Obligatoriedad obligatoriedad) {
		this()
		this.nombre = nombre
		this.descripcion = descripcion
  		this.nombre = nombre
  		this.descripcion = descripcion
		this.obligatoriedad = obligatoriedad
	}

	public void cancelar() {
		if (this.validarCambiarEstado(Estado.CANCELADA))
			this.estado = Estado.CANCELADA
	}

	public void agregarPaso(Paso paso, boolean obligatorio) {
		super.agregarPaso(paso, obligatorio)
		paso.agragarListener(this)
	}

	public void notificar(){
		if (!plan.any { !it.esEstadoTerminal() && it.obligatoriedad == Obligatoriedad.NECESARIO})
			estado  = Estado.EN_EJECUCION
		if (!plan.any { !it.esEstadoTerminal() && it.obligatoriedad == Obligatoriedad.OPCIONAL})
			estado  = Estado.FINALIZADA
	}
	
	static constraints = {
		nombre display: true
		descripcion display: true
		estado editable: false
		listeners display: false
	}
}
