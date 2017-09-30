package organizadordemetas

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TareaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Tarea.list(params), model:[tareaCount: Tarea.count()]
    }

    def show(Tarea tarea) {
        respond tarea
    }

    def create() {
        respond new Tarea(params)
    }

    @Transactional
    def save(Tarea tarea) {
        if (tarea == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tarea.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tarea.errors, view:'create'
            return
        }

        tarea.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tarea.label', default: 'Tarea'), tarea.id])
                redirect tarea
            }
            '*' { respond tarea, [status: CREATED] }
        }
    }

    def edit(Tarea tarea) {
        respond tarea
    }

    @Transactional
    def update(Tarea tarea) {
        if (tarea == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tarea.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tarea.errors, view:'edit'
            return
        }

        tarea.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tarea.label', default: 'Tarea'), tarea.id])
                redirect tarea
            }
            '*'{ respond tarea, [status: OK] }
        }
    }

    @Transactional
    def delete(Tarea tarea) {
        if (tarea == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tarea.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tarea.label', default: 'Tarea'), tarea.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

  @Transactional
  def cambiarEstado (Tarea tarea) {
        if (tarea == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

		switch(params.NuevoEstado) {
		  case "PENDIENTE": tarea.cambiarEstado(Estado.PENDIENTE); break;
		  case "EN_EJECUCION": tarea.cambiarEstado(Estado.EN_EJECUCION); break;
		  case "CANCELADA": tarea.cambiarEstado(Estado.CANCELADA); break;
		  case "FINALIZADA": tarea.cambiarEstado(Estado.FINALIZADA); break;
			default:
				respond tarea, view:'edit'
				return
		}

		tarea.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tarea.label', default: 'Tarea'), tarea.id])
                redirect tarea
            }
            '*'{ respond tarea, [status: OK] }
        }
	}

	@Transactional
	def agregarSubMeta(Tarea tarea) {
        if (tarea == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

		SubMeta subMetaNueva;
		switch(params.TipoSubMeta) {
			case "Objetivo": subMetaNueva = new Objetivo(params.smNombre, params.smDescripcion); break;
			case    "Tarea": subMetaNueva = new    Tarea(params.smNombre, params.smDescripcion); break;
			default:
				tarea.errors.add "Tipo no valido"
				respond tarea.errors, view:'edit'
				return
		}

		switch(params.TipoObligacion) {
			case "Obligatorio": tarea.agregarSubMetaObligatoria(subMetaNueva); break;
			case    "Opcional":    tarea.agregarSubMetaOpcional(subMetaNueva); break;
			default:
				respond tarea, view:'edit'
				return
		}

		tarea.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tarea.label', default: 'Tarea'), tarea.id])
                redirect tarea
            }
            '*'{ respond tarea, [status: OK] }
        }
	}

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tarea.label', default: 'Tarea'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
