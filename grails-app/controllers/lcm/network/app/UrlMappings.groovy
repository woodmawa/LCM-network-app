package lcm.network.app

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        //get "/api/device"(controller:"device", action:"index")
        //get "/api/test"(controller:"test", action:"index")
        //get "/api/test/$id"(controller:"test", action:"show")
        "/api/device"(resources:'device')

        "/api/test"(resources:'test')
        get "/api/test2/$id"(controller:"test2", action:"show")


        //"/api/device"(resource:'device')

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
