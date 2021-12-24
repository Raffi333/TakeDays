import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.GrailsApplication
import tekdays.Sponsor
import tekdays.Sponsorship
import tekdays.TekEvent
import tekdays.TekUser

class BootStrap {

    GrailsApplication grailsApplication

    def init = { servletContext ->
        println("Start")


//        grailsApplication.getAllArtefacts().each { println it.simpleName}

        if (!TekEvent.get(1)) {

            TekUser.withTransaction { status ->
                new TekUser(fullName: 'John Doe',
                        userName: 'admin',
                        password: 'admin',
                        email: 'jdoe@johnsgroovyshop.com',
                        website: 'blog.johnsgroovyshop.com',
                        bio: 'John has been programming for over 40 years. ...').save()
                new TekUser(fullName: 'John Deere',
                        userName: 'tractorman',
                        password: 't0ps3cr3t',
                        email: 'john.deere@porkproducers.org',
                        website: 'www.perl.porkproducers.org',
                        bio: 'John is a top notch Perl programmer and a ...').save()


                TekEvent event1 = new TekEvent(name: 'Gateway Code Camp',
                        city: 'Saint Louis, MO',
                        organizer: TekUser.findByFullName('John Doe'),
                        venue: 'TBD',
                        startDate: new Date('11/21/2013'),
                        endDate: new Date('11/21/2013'),
                        description: '''This conference will bring
                coders ...''').save()
                TekEvent event2 = new TekEvent(name: 'Perl Before Swine',
                        city: 'Austin, MN',
                        organizer: TekUser.findByFullName('John Deere'),
                        venue: 'SPAM Museum',
                        startDate: new Date('11/2/2013'),
                        endDate: new Date('11/2/2013'),
                        description: 'Join the Perl programmers of the ...').save()


                TekEvent g1 = TekEvent.findByName('Gateway Code Camp')
                g1.addToVolunteers(new TekUser(fullName: 'Sarah Martin',
                        userName: 'sarah',
                        password: '54321',
                        email: 'sarah@martinworld.com',
                        website: 'www.martinworld.com',
                        bio: 'Web designer and Grails afficianado.'))
                g1.addToVolunteers(new TekUser(fullName: 'Bill Smith',
                        userName: 'Mr_Bill',
                        password: '12345',
                        email: 'mrbill@email.com',
                        website: 'www.mrbillswebsite.com',
                        bio: 'Software developer, claymation artist.'))
                g1.save()


                g1.addToVolunteers(new TekUser(fullName: 'Bill Smith',
                        userName: 'Mr_Bill',
                        password: '12345',
                        email: 'mrbill@email.com',
                        website: 'www.mrbillswebsite.com',
                        bio: 'Software developer, claymation artist.'))
                g1.addToRespondents('ben@grailsmail.com')
                g1.addToRespondents('zachary@linuxgurus.org')
                g1.addToRespondents('solomon@bootstrapwelding.com')
                g1.save()


                def s1 = new Sponsor(name: 'Contegix',
                        website: 'http://www.contegix.com',
                        description: 'Beyond Managed Hosting for your Enterprise').save()


                def s2 = new Sponsor(name: 'Object Computing Incorporated',
                        website: 'http://ociweb.com',
                        description: 'An OO Software Engineering Company'
                ).save()

                def sp1 = new Sponsorship(
                        event: g1,
                        sponsor: s1,
                        contributionType: 'Other',
                        description: 'Cool T-Shirts').save()

                def sp2 = new Sponsorship(
                        event: g1,
                        sponsor: s2,
                        contributionType: 'Venue',
                        description: 'Will be paying for the Moscone').save()
            }


        }


    }


    def destroy = {
    }
}
