package com.softwood.controller

import com.softwood.domain.Product

class ProductController extends JsonApiRestfulController<Product> {
    static responseFormats = ['json', 'xml']

    ProductController() {
        super (Product)
    }
}
