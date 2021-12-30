package tekdays

import org.hibernate.envers.Audited
@Audited
class TekEvent {
    String city
    String name
    TekUser organizer
    String venue
    Date startDate
    Date endDate
    String description
    String nickname


    static mapping = {
//        organizer cascade: 'all-delete-orphan'
//        volunteers cascade: 'all-delete-orphan'
//        respondents cascade: 'all-delete-orphan'
//        sponsors cascade: 'all-delete-orphan'
//        tasks cascade: 'all-delete-orphan'
//        messages cascade: 'all-delete-orphan'

//        organizer(fetch:'join')
//        organizer(lazy: false)

//        cache true
//        organizer cache:'read-only'
    }

    static def hasMany = [volunteers: TekUser, respondents: String, sponsors: Sponsorship, tasks: Task, messages: TekMessage]
    //static belongsTo = TekEvent

    static def constraints = {
        name()
        city()
        description(maxSize: 5000)
        organizer nullable: true
        venue()
        startDate()
        endDate()
        tasks nullable: true
        messages nullable: true
        nickname nullable: true
    }


//    @Override
//    String toString() {
//        "$name, $city"
//    }


    @Override
    public String toString() {
        return city + " " + name
    }

}
