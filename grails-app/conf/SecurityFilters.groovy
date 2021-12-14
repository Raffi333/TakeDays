class SecurityFilters {

    def filters = {

        doLogin(controller: '*', action: '*') {


            before = {
                if (controllerName == null) {
                    return true
                }
                List<String> allowedActions = ['login','validate','registration','validate_register','m1',"m2","m3","m4"]
                if (!session.user && !allowedActions.contains(actionName)) {

                    redirect(controller: 'authorization', action: 'login', params: ['cName': controllerName, 'aName': actionName])
                    return false
                }
            }
        }
    }


    def logout = {
        session.user = null
        redirect(uri:'/')
    }

}
