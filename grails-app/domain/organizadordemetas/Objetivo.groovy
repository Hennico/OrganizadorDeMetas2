package organizadordemetas

class Objetivo extends SubMeta {

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

	public Objetivo () {
		subMetasOpocionales = new ArrayList<SubMeta>()
		subMetasObligatorias = new ArrayList<SubMeta>()
		listeners = new ArrayList<Objetivo>()
		estado = Estado.PENDIENTE
	}

	public Objetivo (String nombre, String descripcion) {
		this()
		this.nombre = nombre
		this.descripcion = descripcion
	}

	public void cancelar() {
		if (this.validarCambiarEstado(Estado.CANCELADA))
			this.estado = Estado.CANCELADA
	}

	public void agregarSubMeta(SubMeta subMeta, boolean obligatorio) {
		super.agregarSubMeta(subMeta, obligatorio)
		subMeta.agragarListener(this)
	}

	public void Notify(){
		estado = Estado.CANCELADA
		def operacionFinalizada
		operacionFinalizada = true
		for(SubMeta opcional : subMetasOpocionales) {
			if (opcional.estado != Estado.FINALIZADA || opcional.estado != Estado.CANCELADA)
			operacionFinalizada = false
		}
		for(SubMeta opcional : subMetasObligatorias) {
			if (opcional.estado != Estado.FINALIZADA || opcional.estado != Estado.CANCELADA)
			operacionFinalizada = false
		}
		if(operacionFinalizada)
			estado = Estado..FINALIZADA
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
