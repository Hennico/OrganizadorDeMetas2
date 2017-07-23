package organizadordemetas

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



  public void CambiarEstado(Estado estado) {
		if (this.validarCambiarEstado(estado))
			this.estado = estado
	}

  static constraints = {
    nombre display: true
    descripcion display: true
    estado editable: false
    subMetasOpocionales display: true
    subMetasObligatorias display: true
    listeners display: false
  }
}
