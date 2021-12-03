package tekdays

import com.google.gson.GsonBuilder
import grails.converters.JSON
import grails.converters.XML
import grails.web.JSONBuilder
import groovy.json.JsonBuilder

import java.util.logging.XMLFormatter

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TekMessageController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


//    def index(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
//        respond TekMessage.list(params), model:[tekMessageInstanceCount: TekMessage.count()]
//    }


    def ajaxIndex() {
        return [tekMessageInstanceList: TekMessage.list()]
    }


    def showDetail() {
        def tekMessageInstance = TekMessage.get(params.id)

        if (tekMessageInstance) {

            println tekMessageInstance
            return render(view: "_details", model: [tekMessageInstance: tekMessageInstance])
        } else {
            return render("No message found with id: ${params.id}")
        }
    }

    def index(Integer max) {

        params.max = Math.min(max ?: 10, 100)
        List<TekMessage> list
        int count
        TekEvent event = TekEvent.findById(params.id)


        if (event) {
            list = TekMessage.findAllByEvent(event, params)
            count = TekMessage.countByEvent(event)
        } else {
            list = TekMessage.list(params)
            count = TekMessage.count()
        }
        return [tekMessageInstanceList : list,
                tekMessageInstanceCount: count,
                event                  : event]
    }

    def show(TekMessage tekMessageInstance) {
        respond tekMessageInstance
    }

    def create() {
        respond new TekMessage(params)
    }

    @Transactional
    def save(TekMessage tekMessageInstance) {
        if (tekMessageInstance == null) {
            notFound()
            return
        }

        if (tekMessageInstance.hasErrors()) {
            respond tekMessageInstance.errors, view: 'create'
            return
        }

        tekMessageInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tekMessage.label', default: 'TekMessage'), tekMessageInstance.id])
                redirect tekMessageInstance
            }
            '*' { respond tekMessageInstance, [status: CREATED] }
        }
    }

    def edit(TekMessage tekMessageInstance) {
        respond tekMessageInstance
    }

    @Transactional
    def update(TekMessage tekMessageInstance) {
        if (tekMessageInstance == null) {
            notFound()
            return
        }

        if (tekMessageInstance.hasErrors()) {
            respond tekMessageInstance.errors, view: 'edit'
            return
        }

        tekMessageInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TekMessage.label', default: 'TekMessage'), tekMessageInstance.id])
                redirect tekMessageInstance
            }
            '*' { respond tekMessageInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TekMessage tekMessageInstance) {

        if (tekMessageInstance == null) {
            notFound()
            return
        }

        tekMessageInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TekMessage.label', default: 'TekMessage'), tekMessageInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekMessage.label', default: 'TekMessage'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
