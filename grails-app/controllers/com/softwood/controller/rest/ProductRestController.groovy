package com.softwood.controller.rest

import com.softwood.domain.Product

class ProductRestController extends JsonApiRestfulController<Product> {
    static responseFormats = ['json', 'xml']

    ProductRestController() {
        super (Product)
    }
}
