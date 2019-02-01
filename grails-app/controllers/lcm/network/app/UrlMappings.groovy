package lcm.network.app

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        /*"/ui/sites/$action?/$id?" (controller:"siteUI") {
            constraints {
                // apply constraints here
            }
        }*/

        //get "/api/device"(controller:"device", action:"indexInvestigation")
        //get "/api/test"(controller:"test", action:"indexInvestigation")
        //get "/api/test/$id"(controller:"test", action:"show")
        /**
         * declare Rest endpoints
         */
        "/api/devices"(resources:'deviceRest') {
            "/equipment" (resources:"equipmentRest")
        }
        "/api/sites"(resources:'siteRest') {
            "/devices" (resources:'deviceRest')
        }
        "/api/orgs"(resources:'orgRoleInstanceRest') {
            "/sites" (resources:"siteRest")
        }
        "/api/products"(resources:'productRest') {
            "/equipment" (resources:"equipmentRest")
        }

        /*"/api/test"(resources:'test')
        get "/api/test2/$id"(controller:"test2", action:"show")
        get "/api/test2/$id"(controller:"test2", action:"thing")*/



        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
