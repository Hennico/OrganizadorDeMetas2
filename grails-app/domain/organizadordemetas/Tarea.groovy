package organizadordemetas

<<<<<<< HEAD
class Tarea extends SubMeta{

  public void actualizar(String nombre, String descripcion){
    this.nombre = nombre
    this.descripcion = descripcion
  }


  public void inicializar(String nombre, String descripcion){
    subMetasOpocionales = new ArrayList<SubMeta>()
    subMetasObligatorias = new ArrayList<SubMeta>()
    listeners = new ArrayList<Objetivo>()
    estado = Estado.PENDIENTE

    this.nombre = nombre
    this.descripcion = descripcion
  }

  public Tarea () {
    subMetasOpocionales = new ArrayList<SubMeta>()
    subMetasObligatorias = new ArrayList<SubMeta>()
    listeners = new ArrayList<Objetivo>()
    estado = Estado.PENDIENTE
  }

  public Tarea (String nombre, String descripcion) {
    subMetasOpocionales = new ArrayList<SubMeta>()
    subMetasObligatorias = new ArrayList<SubMeta>()
    listeners = new ArrayList<Objetivo>()
    estado = Estado.PENDIENTE

    this.nombre = nombre
    this.descripcion = descripcion
  }

  public void cambiarEstado(Estado estado) {
		if (!this.validarCambiarEstado(estado))
			throw new CambioEstadoInvalido("El estado que se utilizo no es valido")
		
		this.estado = estado
		this.informarCambioEstado()
=======
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

>>>>>>> 97e177164d9ac25d5f86cd94cf02ac859e879128
	}

	static constraints = {
	    nombre display: true
	    descripcion display: true
	    estado editable: false
	    listeners display: false
	  }
}
