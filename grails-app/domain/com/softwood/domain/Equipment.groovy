package com.softwood.domain

class Equipment extends PhysicalResource {

    enum EquipmentCategory {
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

    static constraints = {
        isEquipmentContainer nullable: false
        category nullable:false
        assetTag nullable:true
        serialNumber nullable:true
        productOffering nullable:true
    }


}
