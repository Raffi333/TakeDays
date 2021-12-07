package tekdays

class TekDaysTagLib {
    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]



    def messageThread = {attrs ->
        List<TekMessage> messages = attrs.messages.findAll{msg -> !msg.parent}
        processMessages(messages, 0)
    }

    void processMessages(messages, indent){
        messages.each{ msg ->
            def body = "${msg?.author} - ${msg?.subject}"
            out << "<p style='height:35; margin-left:${indent * 20}px;color:red'>"
            out << "${remoteLink(action:'showDetail', id:msg.id,update:'details', body)}"
            out << "</p>"
            def children = TekMessage.findAllByParent(msg)
            if (children){
                processMessages(children, indent + 1)
            }
        }
    }
}
