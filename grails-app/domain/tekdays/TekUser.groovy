package tekdays

import org.hibernate.envers.Audited


@Audited
class TekUser {

    String fullName
    String userName
    String password
    String email
    String website
    String bio





//    static def belongsTo = TekEvent
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

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        TekUser tekUser = (TekUser) o

        if (id != tekUser.id) return false


        return true
    }

    int hashCode() {
        int result
        result = (fullName != null ? fullName.hashCode() : 0)
        result = 31 * result + (userName != null ? userName.hashCode() : 0)
        result = 31 * result + (password != null ? password.hashCode() : 0)
        result = 31 * result + (email != null ? email.hashCode() : 0)
        result = 31 * result + (website != null ? website.hashCode() : 0)
        result = 31 * result + (bio != null ? bio.hashCode() : 0)
        result = 31 * result + (id != null ? id.hashCode() : 0)
        result = 31 * result + (version != null ? version.hashCode() : 0)
        return result
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
