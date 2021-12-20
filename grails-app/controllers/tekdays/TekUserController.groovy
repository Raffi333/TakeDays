package tekdays


import grails.transaction.Transactional
import jline.internal.ShutdownHooks
import org.grails.datastore.mapping.config.Entity
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.envers.AuditReader
import org.hibernate.envers.AuditReaderFactory

import javax.persistence.EntityManager
import javax.sound.sampled.AudioFileFormat

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class TekUserController {

    SessionFactory sessionFactory

    EnversService<TekUser> enversService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {

//      def d = sessionFactory.currentSession.createSQLQuery("SELECT *  from tek_user_aud")
//      def d = sessionFactory.currentSession.createCriteria().
//        println d


//        def l = AuditReaderFactory.get(sessionFactory.currentSession)
//                .createQuery()
//                .forRevisionsOfEntity(TekUser.class, false, false)
//                .resultList
        def l
     30.times {l = enversService.getAuditedByRevision(it+1);  println l}


        params.max = Math.min(max ?: 10, 100)
        respond TekUser.list(params), model: [tekUserInstanceCount: TekUser.count()]
    }

    def show(TekUser tekUserInstance) {
        if (params.id == null) {
            redirect(action: "index")
            return
        }

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
        if (session?.user?.id == tekUserInstance.id)
            session.user = tekUserInstance

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
