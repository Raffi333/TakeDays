package tekdays

class TekUser {

    String fullName
    String userName
    String password
    String email
    String website
    String bio


    static def belongsTo = TekEvent
    static def constraints = {
        fullName()
        userName()
        email()
        website()
        bio maxSize: 5000
    }


    @Override
     String toString() {
        return "${fullName}"
    }
}
