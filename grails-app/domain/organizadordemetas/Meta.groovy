package organizadordemetas

class Meta {
	Objetivo objetivo

	public Meta (){
		objetivo = new Objetivo()
  }

	public void inicializar(String nombre, String descripcion){
		objetivo.inicializar(nombre, descripcion, Obligatoriedad.NECESARIO)
  }


  static constraints = {
  }
}
