package organizadordemetas

class Objetivo extends Paso {

	public void actualizar(String nombre, String descripcion){
		this.nombre = nombre
		this.descripcion = descripcion
	}

	public Objetivo () {
	  	this.plan = []
  		this.listeners = []
     	this.estado = Estado.PENDIENTE
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
	
	public void agregarPaso(Paso paso) {
		super.agregarPaso(paso)
		paso.agragarListener(this)
	}

	public void informar(){
		if(!plan.any { it -> !it.estaCompleta() })
			estado = Estado.FINALIZADA
	}
	
	static constraints = {
		nombre display: true
		descripcion display: true
		estado editable: false
		listeners display: false
	}
}
