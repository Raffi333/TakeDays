class UrlMappings {

    static mappings = {




        "/user/$action?/$id?"(controller: 'tekUser') {
            data = [1, 2, 3, 5, 9]
        }
        "/login"(controller: 'authorization', action: 'login') {
        }
        "/registration"(controller: 'authorization', action: 'registration') {
        }

        // or         "/user/$action?/$id?(.$format)?"(controller: 'tekUser')

//        or
//        "/grailsblogs/$year/$month/$day/$entry_name?" {
//            controller = 'authorization'
//            action = 'test'
//            constraints {
//            }
//        }
        "/authorization/confirm/$code?"(controller: "authorization",action: "confirm") {

        }

        "/api" {
            controller = 'authorization'
            action = [GET   : 'm1',
                      POST  : 'm2',
                      PUT   : 'm3',
                      DELETE: 'm4',
            ]

        }
        "/events/$nickname"{
            controller = "tekEvent"
            action = "show"

        }

        "/$controller/$action?/$id?(.$format)?" {
            constraints {
            }
        }



        "/"(view: "/index")
        "500"(view: '/error')
        "404"(view:'/notFound')
    }
}
