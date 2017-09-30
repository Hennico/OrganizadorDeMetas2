class ErrorsController {
	def index() {
		def initialController = request.exception?.className
		if (initialController) {
			def controller = grailsApplication.getArtefact("Controller", initialController).getReferenceInstance()
			// do some rendering based on the annotations
			render "Controller: ${initialController}, annotations ${controller.getClass().getDeclaredAnnotations()}"
			return
		}
		render 'no initial controller'
	}
}