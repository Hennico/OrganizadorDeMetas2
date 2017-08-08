package organizadordemetas

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ObjetivoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Objetivo.list(params), model:[objetivoCount: Objetivo.count()]
    }

    def show(Objetivo objetivo) {
        respond objetivo
    }

    def create() {
        respond new Objetivo(params)
    }

    @Transactional
    def save(Objetivo objetivo) {
        if (objetivo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        objetivo.inicializar(params.nombre, params.descripcion, Obligatoriedad.NECESARIO)

        if (objetivo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond objetivo.errors, view:'create'
            return
        }

        objetivo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'objetivo.label', default: 'Objetivo'), objetivo.id])
                redirect objetivo
            }
            '*' { respond objetivo, [status: CREATED] }
        }
    }

    def edit(Objetivo objetivo) {
        respond objetivo
    }

    @Transactional
    def update(Objetivo objetivo) {
        if (objetivo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (objetivo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond objetivo.errors, view:'edit'
            return
        }

        Objetivo aux = Objetivo.get(objetivo.id)
        aux.actualizar(objetivo.nombre, objetivo.descripcion)
        aux.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'objetivo.label', default: 'Objetivo'), objetivo.id])
                redirect objetivo
            }
            '*'{ respond objetivo, [status: OK] }
        }
    }

    @Transactional
    def delete(Objetivo objetivo) {

        if (objetivo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        objetivo.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'objetivo.label', default: 'Objetivo'), objetivo.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }


    def estadoCancelar(int id) {
  		Objetivo objetivo = Objetivo.get(id)
		objetivo.cancelar()
  		render(view: "show", model: [objetivo: objetivo])
  	}

	@Transactional
  def agregarSubMeta(Objetivo objetivo) {
        if (objetivo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

		Paso subMetaNueva;
		switch(params.TipoSubMeta) {
			case "Objetivo": subMetaNueva = new Objetivo(params.smNombre, params.smDescripcion, Obligatoriedad.convertirEnEnum(params.TipoObligacion)); break;
			case    "Tarea": subMetaNueva = new    Tarea(params.smNombre, params.smDescripcion, Obligatoriedad.convertirEnEnum(params.TipoObligacion)); break;
			default:
				tarea.errors.add "Tipo no valido"
				respond tarea.errors, view:'edit'
				return
		}
    objetivo.agregarPaso(subMetaNueva)


		objetivo.save flush:objetivo

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'objetivo.label', default: 'Objetivo'), objetivo.id])
                redirect objetivo
            }
            '*'{ respond objetivo, [status: OK] }
        }
	}

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'objetivo.label', default: 'Objetivo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
