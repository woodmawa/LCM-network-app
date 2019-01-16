package com.softwood.domain

class Equipment extends PhysicalResource {

    enum EquipmentCategory {
        Chasis,
        Card,
        PowerSupply,
        Memory,
        StorageUnit,
        InterfaceCard,
        Cable,
        SofwareInstallationDisk,
        Unspecified

    }

    boolean isEquipmentContainer = false
    EquipmentCategory category
    String serialNumber
    String assetTag
    ProductOffering productOffering
    Device device

    static constraints = {
        isEquipmentContainer nullable: false
        category nullable:false
        assetTag nullable:true
        serialNumber nullable:true
        productOffering nullable:true
        device nullable:true
    }


}
