package tekdays

import grails.transaction.Transactional

@Transactional
class EmailService {

    void sendEmailForConfirmAccount(TekUser user) {

        try {

            sendMail {
                to(user.getEmail())
                subject('CONFIRM')
                html(view: "/authorization/confirm_email", model: [name: user.fullName, confirm: user.confirmCode])
            }


        } catch (Exception e) {
            println e.message + " OOPS"
        }
    }

}
