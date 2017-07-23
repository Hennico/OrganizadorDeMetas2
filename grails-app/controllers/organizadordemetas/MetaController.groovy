package organizadordemetas

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MetaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Meta.list(params), model:[metaCount: Meta.count()]
    }

    def show(Meta meta) {
        respond meta
    }

    def create() {
        respond new Meta(params)
    }

    @Transactional
    def save(Meta meta) {
        if (meta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (meta.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond meta.errors, view:'create'
            return
        }

        meta.inicializar(params.nombre, params.descripcion)
        meta.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'meta.label', default: 'Meta'), meta.id])
                redirect meta
            }
            '*' { respond meta, [status: CREATED] }
        }
    }

    def edit(Meta meta) {
        respond meta
    }

    @Transactional
    def update(Meta meta) {
        if (meta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (meta.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond meta.errors, view:'edit'
            return
        }

        meta.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'meta.label', default: 'Meta'), meta.id])
                redirect meta
            }
            '*'{ respond meta, [status: OK] }
        }
    }

    @Transactional
    def delete(Meta meta) {

        if (meta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        meta.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'meta.label', default: 'Meta'), meta.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'meta.label', default: 'Meta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
