package tekdays

import grails.transaction.Transactional
import org.springframework.context.annotation.Scope

@Transactional
class TaskService {


    def serviceMethod() {
    }

    void addDefaultTasks(TekEvent tekEvent) {
        if (tekEvent.tasks?.size() > 0)
            return //We only want to add tasks to a new event

        tekEvent.addToTasks(new Task(title: 'Identify potential venues :1'))
        tekEvent.addToTasks(new Task(title: 'Identify potential venues :2'))
        tekEvent.addToTasks(new Task(title: 'Identify potential venues :3'))
        tekEvent.addToTasks(new Task(title: 'Identify potential venues :4'))
        tekEvent.addToTasks(new Task(title: 'Identify potential venues :5'))
        tekEvent.addToTasks(new Task(title: 'Identify potential venues :6'))

        tekEvent.save()

    }


}
