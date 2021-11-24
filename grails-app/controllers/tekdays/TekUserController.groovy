package tekdays

import grails.web.JSONBuilder
import groovy.json.JsonBuilder

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TekUserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TekUser.list(params), model: [tekUserInstanceCount: TekUser.count()]
    }

    def show(TekUser tekUserInstance) {
        respond tekUserInstance
    }

    def create() {
        respond new TekUser(params)
    }

    @Transactional
    def save(TekUser tekUserInstance) {
        if (tekUserInstance == null) {
            notFound()
            return
        }

        if (tekUserInstance.hasErrors()) {
            respond tekUserInstance.errors, view: 'create'
            return
        }

        tekUserInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tekUser.label', default: 'TekUser'), tekUserInstance.id])
                redirect tekUserInstance
            }
            '*' { respond tekUserInstance, [status: CREATED] }
        }
    }

    def edit(TekUser tekUserInstance) {
        respond tekUserInstance
    }

    @Transactional
    def update(TekUser tekUserInstance) {
        if (tekUserInstance == null) {
            notFound()
            return
        }

        if (tekUserInstance.hasErrors()) {
            respond tekUserInstance.errors, view: 'edit'
            return
        }

        tekUserInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TekUser.label', default: 'TekUser'), tekUserInstance.id])
                redirect tekUserInstance
            }
            '*' { respond tekUserInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TekUser tekUserInstance) {

        if (tekUserInstance == null) {
            notFound()
            return
        }

        tekUserInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TekUser.label', default: 'TekUser'), tekUserInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekUser.label', default: 'TekUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }



}
