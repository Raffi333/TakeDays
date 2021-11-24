package tekdays



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SponsorshipController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Sponsorship.list(params), model:[sponsorshipInstanceCount: Sponsorship.count()]
    }

    def show(Sponsorship sponsorshipInstance) {
        respond sponsorshipInstance
    }

    def create() {
        respond new Sponsorship(params)
    }

    @Transactional
    def save(Sponsorship sponsorshipInstance) {
        if (sponsorshipInstance == null) {
            notFound()
            return
        }

        if (sponsorshipInstance.hasErrors()) {
            respond sponsorshipInstance.errors, view:'create'
            return
        }

        sponsorshipInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), sponsorshipInstance.id])
                redirect sponsorshipInstance
            }
            '*' { respond sponsorshipInstance, [status: CREATED] }
        }
    }

    def edit(Sponsorship sponsorshipInstance) {
        respond sponsorshipInstance
    }

    @Transactional
    def update(Sponsorship sponsorshipInstance) {
        if (sponsorshipInstance == null) {
            notFound()
            return
        }

        if (sponsorshipInstance.hasErrors()) {
            respond sponsorshipInstance.errors, view:'edit'
            return
        }

        sponsorshipInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Sponsorship.label', default: 'Sponsorship'), sponsorshipInstance.id])
                redirect sponsorshipInstance
            }
            '*'{ respond sponsorshipInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Sponsorship sponsorshipInstance) {

        if (sponsorshipInstance == null) {
            notFound()
            return
        }

        sponsorshipInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Sponsorship.label', default: 'Sponsorship'), sponsorshipInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sponsorship.label', default: 'Sponsorship'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
