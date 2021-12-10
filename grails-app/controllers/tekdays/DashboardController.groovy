package tekdays

class DashboardController {

    def index() {}

    def dashboard() {

        TekEvent event = TekEvent.get(params.id)



        if (event) {

            if (event.organizer.userName == session.user.userName || event.volunteers.collect { it.userName }.contains(session.user.userName)) {

                List<Task> tasks = Task.findAllByEventAndCompleted(event, false, [max: 3, sort: 'dueDate', order: 'asc'])
                Set<TekUser> volunteers = event.volunteers
                List<TekMessage> messages = TekMessage.findAllByEventAndParentIsNull(event, [sort: 'id', order: 'desc'])
                Set<Sponsorship> sponsorships = event.sponsors

                return [event: event, tasks: tasks, volunteers: volunteers, messages: messages, sponsorships: sponsorships]

            } else {
                flash.message = "Access to dashboard for ${event.name} denied."
                redirect controller: 'tekEvent', action: 'index'
            }
        } else {
            flash.message = "No event was found with an id of ${params.id}"
            redirect controller: 'tekEvent', action: 'index'
        }
    }
}