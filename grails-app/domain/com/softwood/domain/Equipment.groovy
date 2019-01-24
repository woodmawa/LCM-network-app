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

    boolean equipmentContainer = false
    EquipmentCategory category
    String serialNumber
    String assetTag
    ProductOffering productOffering
    Device device

    boolean isEquipmentContainer() {
        equipmentContainer
    }

    static constraints = {
        equipmentContainer nullable: false
        category nullable:false
        assetTag nullable:true
        serialNumber nullable:true
        productOffering nullable:true
        device nullable:true
    }

    String toString() {
        "Equipment (category:$category, name:$name)[id:$id]"
    }

}
