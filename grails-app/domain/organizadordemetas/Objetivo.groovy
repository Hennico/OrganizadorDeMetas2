package organizadordemetas

class Objetivo extends SubMeta {

	public Onjetivo () {
		subMetasOpocionales = new ArrayList<SubMeta>()
		subMetasObligatorias = new ArrayList<SubMeta>()
		listeners = new ArrayList<Objetivo>()
	}

	public Objetivo (String nombre, String descripcion) {
		subMetasOpocionales = new ArrayList<SubMeta>()
		subMetasObligatorias = new ArrayList<SubMeta>()
		listeners = new ArrayList<Objetivo>()

		this.nombre = nombre
		this.descripcion = descripcion
	}


	public void agregarSubMeta(SubMeta subMeta, boolean obligatorio) {
		super.agregarSubMeta(subMeta, obligatorio)
		subMeta.agragarListener(this)
	}

	public void Notify(){
		bool flag
		flag = true
		for(SubMeta opcional : subMetasOpocionales) {
			if (opcional.estado != Estado.Finalizado || opcional.estado != Estado.Cancelado)
			flag = false;
		}
		for(SubMeta opcional : subMetasObligatorias) {
			if (opcional.estado != Estado.Finalizado || opcional.estado != Estado.Cancelado)
			flag = false;
		}
		if(flag)
			estado = Estado.Finalizada
	}

  public void agregarSubMetaObligatoria(SubMeta subMetao) {
    this.agregarSubMeta(subMeta, true)
  }

  public void agregarSubMetaOpcional(SubMeta subMetao) {
    this.agregarSubMeta(subMeta, false)
  }

  static constraints = {
  }

}
