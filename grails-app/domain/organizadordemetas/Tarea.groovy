package organizadordemetas

class Tarea extends Paso{

  	public void actualizar(String nombre, String descripcion){
    	this.nombre = nombre
   		this.descripcion = descripcion
 	}

 	public Tarea () {
	  	this.plan = []
  		this.listeners = []
     	this.estado = Estado.PENDIENTE
		this.obligatoriedad = Obligatoriedad.NECESARIO
  	}

  	public Tarea (String nombre, String descripcion, Obligatoriedad obligatoriedad) {
	  	this.plan = []
  		this.listeners = []
     	this.estado = Estado.PENDIENTE
  		this.nombre = nombre
  		this.descripcion = descripcion
		this.obligatoriedad = obligatoriedad
  	}


  	public void cambiarEstado(Estado estado) {
		if (this.validarCambiarEstado(estado)){
			this.estado = estado
			this.informarCambio()
		} else {
			throw new CambioEstadoInvalido("El no se puede cambiar del estado \""+this.estado.toString()+"\" al estado \""+estado.toString()+"\".")
		}
	}

	static constraints = {
	    nombre display: true
	    descripcion display: true
	    estado editable: false
	    listeners display: false
	  }
}
