package organizadordemetas

class Tarea extends Paso{

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

 	public Tarea () {
	  	plan = []
  		listeners = []
     		estado = Estado.PENDIENTE
		this.obligatoriedad = Obligatoriedad.NECESARIO
  	}

  	public Tarea (String nombre, String descripcion, Obligatoriedad obligatoriedad) {
	  	plan = []
  		listeners = []
     		estado = Estado.PENDIENTE
  		this.nombre = nombre
  		this.descripcion = descripcion
		this.obligatoriedad = obligatoriedad
  	}


  	public void cambiarEstado(Estado estado) {
		if (this.validarCambiarEstado(estado)){
			this.estado = estado
			this.informarCambio()
		}

	}

	static constraints = {
	    nombre display: true
	    descripcion display: true
	    estado editable: false
	    listeners display: false
	  }
}
