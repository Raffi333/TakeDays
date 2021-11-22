package tekdays

class Sponsor {
    String name
    String website
    String description
    byte[] logo


    static def hasMany = [events: TekEvent]
    static def belongsTo = TekEvent

    static def constraints = {
        name blank: false
        website blank: false, url: true
        description nullable: true, maxSize: 5000
        logo nullable: true, maxSize: 1000000
    }


    @Override
    String toString() {
        name
    }
}