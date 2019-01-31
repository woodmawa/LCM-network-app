package com.softwood.domain

class CustomerFacingService extends RootEntity {

    static enum CfsCategory {
        AccessCircuit,
        ConnectionService
    }

    static enum CfsTransportType {
        SONET,
        WDM,
        OTN,
        MPLS,
        PDH,
        DSL,
        Fibre,
        Cable,
        SubseaCable
    }

    static enum CfsType {
        PointToPoint,
        PointToCloud
    }

    Product product

    boolean aggregated = false

    static constraints = {
        product nullable:true
    }

    boolean isAggregated (){
        aggregated
    }

}
