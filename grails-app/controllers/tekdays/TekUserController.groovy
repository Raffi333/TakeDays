package tekdays


import grails.transaction.Transactional
import org.hibernate.SessionFactory

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class TekUserController {

    EnversService enversService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
//        EnversHelper<TekUser> enversService = new EnversHelper<>(TekUser.class)

//      def d = sessionFactory.currentSession.createSQLQuery("SELECT *  from tek_user_aud")
//      def d = sessionFactory.currentSession.createCriteria().
//        println d


//        def l = AuditReaderFactory.get(sessionFactory.currentSession)
//                .createQuery()
//                .forRevisionsOfEntity(TekUser.class, false, false)
//                .resultList

        println enversService.getAuditedByEntityId(TekUser.class,6)
        println enversService.getAuditedByRevisionId(TekUser.class,6)
        println enversService.getAllOnlyExist(TekUser.class)
        println enversService.getAllAudited(TekUser.class)

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
