package com.softwood.controller

import com.softwood.domain.Product

class ProductRestController extends JsonApiRestfulController<Product> {
    static responseFormats = ['json', 'xml']

    ProductRestController() {
        super (Product)
    }
}
