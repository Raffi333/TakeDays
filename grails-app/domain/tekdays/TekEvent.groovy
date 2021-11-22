package tekdays

class TekEvent {
    String city
    String name
    TekUser organizer
    String venue
    Date startDate
    Date endDate
    String description

    static hasMany = [volunteers: TekUser, respondents : String]

    static constraints = {
        name()
        city()
        description(maxSize: 5000)
        organizer()
        venue()
        startDate()
        endDate()

    }


//    @Override
//    String toString() {
//        "$name, $city"
//    }


    @Override
    public String toString() {
        return "TekEvent{" +
                ", city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", organizer='" + organizer + '\'' +
                ", venue='" + venue + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                '}';
    }

}
