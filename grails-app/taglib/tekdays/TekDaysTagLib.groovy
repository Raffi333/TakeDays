package tekdays

import java.util.stream.Collectors

class TekDaysTagLib {
    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]


    def messageThread = { attrs ->
        List<TekMessage> messages = attrs.messages.findAll { msg -> !msg.parent }
        processMessages(messages, 0)
    }

    void processMessages(messages, indent) {
        messages.each { msg ->
            def body = "${msg?.author} - ${msg?.subject}"
            out << "<p style='height:35; margin-left:${indent * 20}px;color:red'>"
            out << "${remoteLink(action: 'showDetail', id: msg.id, update: 'details', body)}"
            out << "</p>"
            def children = TekMessage.findAllByParent(msg)
            if (children) {
                processMessages(children, indent + 1)
            }
        }
    }


    def loginToggle = { attrs ->

        if (request.getSession(false) && session.user) {

            out << "<a class='${attrs.class}' href='${createLink(controller: 'authorization', action: 'logout')}' >"
            out << "${message(code: 'logout')}</a>"
        } else {
            out << "<a class='${attrs.class}' href='${createLink(controller: 'authorization', action: 'login')}'>"
            out << "Login </a>"
        }

    }

    def register = { attrs ->

        if (!(request.getSession(false) && session.user)) {
            out << "<a class='${attrs.class}' href='${createLink(controller: 'authorization', action: 'registration')}'>"
            out << "Registeration </a>"

        }
    }

    def organizerEvents = {

        if (request?.getSession(false) && session?.user) {
            def events = TekEvent.findAllByOrganizer(session.user)
            if (events) {
                out << "<div style='margin-left:25px; margin-top:25px; width:85%'>"
                out << "<h3>Events you are organizing:</h3>"
                out << "<ol>"
                events.each {
                    out << "<li><a href='"
                    out << "${createLink(controller: 'tekEvent', action: 'show', id: it.id)}'>"
                    out << "${it}</a></li>"
                }
                out << "</ol>"
                out << "</div>"
            }
        }
    }

    def volunteerEvents = {
        if (request.getSession(false) && session.user) {
            def events = TekEvent.createCriteria().list {
                volunteers {
                    eq('id', session.user?.id)
                }
            }

            if (events) {
                out << "<div style='margin-left:25px; margin-top:25px; width:85%'>"
                out << "<h3>Events you volunteered for:</h3>"
                out << "<ul>"
                events.each {
                    out << "<li><a href='"
                    out << "${createLink(controller: 'tekEvent', action: 'show', id: it.id)}'>"
                    out << "${it}</a></li>"
                }
                out << "</ul>"
                out << "</div>"
            }
        }
    }


    def volunteerButton = { attrs ->
        println session.user
        if (request.getSession(false) && session.user) {
            def user = session.user.merge()
            def event = TekEvent.get(attrs.eventId)
            if (event && !event.volunteers.contains(user)) {
//                out << "<span id='volunteerSpan' class='btn btn-info'>"
                out << "<button id='volunteerButton' class='btn btn-outline-primary' type='button'>"
                out << "Volunteer For This Event"
                out << "</button>"
//                out << "</span>"
            }
        }
    }


}
