package tekdays

import grails.transaction.Transactional


@Transactional(readOnly = true)
class AuthorizationController {


    static allowedMethods = [validate: "POST"]

    def index() {
        redirect(uri: '/')
    }

    def login() {

        if (params.cName)
            return [cName: params.cName, aName: params.aName]
    }

    def validate() {
        TekUser user = TekUser.findByUserName(params.username)

        if (user && user.password == params.password) {
            session.user = user
            if (params.cName)
                redirect controller: "${params.cName}", action: "${params.aName}"
            else
                redirect( controller: 'tekEvent', action: 'index')
        } else {
            flash.message = "OPSSS.. Invalid username and password."
            render (view: 'login', model: [cName: params.cName, aName: params.aName])
        }
    }

    def logout(){
        session.user = null
        redirect(uri: '/')

    }
}