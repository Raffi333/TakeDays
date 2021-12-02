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
        bio maxSize: 5000,nullable: false
    }


    @Override
     String toString() {
        return "${fullName}"
    }


//    @Override
//    public String toString() {
//        return "TekUser{" +
//                "id=" + id +
//                ", version=" + version +
//                ", fullName='" + fullName + '\'' +
//                ", userName='" + userName + '\'' +
//                ", password='" + password + '\'' +
//                ", email='" + email + '\'' +
//                ", website='" + website + '\'' +
//                ", bio='" + bio + '\'' +
//                '}';
//    }
}
