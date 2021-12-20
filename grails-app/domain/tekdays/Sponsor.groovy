package tekdays

import org.hibernate.envers.Audited

@Audited
class Sponsor {
    String name
    String website
    String description
    byte[] logo


    static hasMany = [sponsorships: Sponsorship]
    static belongsTo = TekEvent

    static def constraints = {
        //name validator: {if (!it.equals("hello")) return "e"}
//        name inList: ["hayk", "aram"]
        name blank: false
        website blank: false, url: true
        description nullable: true, maxSize: 5000
        logo nullable: true, maxSize: 1000000
        sponsorships nullable: true

    }


    @Override
    String toString() {
        name
    }
}