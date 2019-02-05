package com.softwood.domain

class CustomerFacingService extends RootEntity {

    static enum CfsCategory {
        AccessCircuit,
        ConnectionService,
        CorporateVpn,
        Unknown
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
        SubseaCable,
        Unknown
    }

    static enum CfsConnectionType {
        PointToPoint,
        PointToCloud,
        Unknown
    }

    String serviceProviderCircuitName
    String bandwidth
    String loopBackIpAddress
    CfsCategory category = CfsCategory.ConnectionService
    CfsTransportType transportType = CfsTransportType.Unknown
    CfsConnectionType connectionType = CfsConnectionType.PointToCloud
    Product product
    OrgRoleInstance customer
    Site owningSite
    Site remoteSite
    boolean aggregated = false

    static belongsTo = [customer:OrgRoleInstance]  //cascade delete on delete of customer


    static constraints = {
        serviceProviderCircuitName nullable:true
        bandwidth nullbale:true
        loopBackIpAddress nullable:true
        category nullable:true
        transportType nullable:true
        connectionType nullable:true
        product nullable:true
        customer nullable:true, validator: {cust, cfs->
            if (cust == null )return true
            if (cust.role == OrgRoleInstance.OrgRoleType.Customer)
                return true
            else
                false
        }
        owningSite nullable:true, validator: {site, cfs ->
            if (site == null) return true
            if (cfs.customer) {
                if (site.org != cfs.customer)
                    return false
                else
                    return true
            }
        }
        remoteSite nullable:true, validator :{site, cfs ->
            if (site == null) return true
            if (cfs.customer) {
                if (site.org != cfs.customer)
                    return false
                else
                    return true
            }
        }
    }

    boolean isAggregated (){
        aggregated
    }

}
