package tekdays


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin}
 * for usage instructions
 */
@TestFor(TaskService)
class TaskServiceIntegrationSpec extends Specification {
    def taskService

    def setup() {
        new TekUser(fullName: 'Tammy Tester', userName: 'tester',
                email: 'tester@test.com', website: 'test.com',
                bio: 'A test person').save()
    }

    def cleanup() {
    }

    void "test addDefaultTasks"() {
        when: "we pass an event to taskService.addDefaultTasks"
        def e = new TekEvent(name: 'Test Event',
                city: 'TestCity, USA',
                description: 'Test Description',
                organizer: TekUser.findByUserName('tester'),
                venue: 'TestCenter',
                startDate: new Date(),
                endDate: new Date() + 1)
        taskService.addDefaultTasks(e)
        then: "the event will have 6 default tasks"
        e.tasks.size() == 6
    }
}