package tekdays

import grails.transaction.Transactional


@Transactional(readOnly = true)
class AuthorizationController {


    static allowedMethods = [validate: "POST", validate_register: "POST", m1: "get", m2: "POST", m3: "PUT", m4: "DELETE"]

    def m1() {
        println "GET"
        render("GET")
    }

    def m2() {
        println "POST"
        render("POST")
    }

    def m3() {
        println "PUT"
        render("PUT")
    }
    def m4() {
        println "DELETE"
        render("DELETE")
    }


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
                redirect(uri: '/')
        } else {
            flash.message = "OPSSS.. Invalid username and password."
//            redirect(action: 'login', model: [cName: params.cName, aName: params.aName])
            render(view: 'login', model: [cName: params.cName, aName: params.aName])
        }
    }

    def logout() {
        session.user = null
        redirect(uri: '/')

    }

    def registration() {
    }

    @Transactional
    def validate_register(TekUser user) {
        withForm {
            if (user?.password == null || user?.userName == null || user == null) {
                flash.message = "OPSSS.. Invalid username and password."
                render(view: "registration")
                return false
            }
            if (!TekUser.findByUserName(user.userName)) {
                user.save()
                session.user = user
                redirect(uri: '/')
            } else {
                flash.message = "OPSSS.. this user already exists"
                render(view: "registration")
            }
        }.invalidToken {
            redirect(uri: '/')

        }


    }


}
