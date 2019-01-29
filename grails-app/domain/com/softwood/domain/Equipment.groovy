package com.softwood.domain

class Equipment extends PhysicalResource {

    static enum EquipmentCategory {
        Chasis,
        Module, //plugin parts for a chasis network or service
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
    Collection<Equipment> children
    Equipment parent
    EquipmentCategory category
    String serialNumber
    String assetTag
    RackUnit numberOfU
    Product product   // optional - can build equipment even if no product to link to
    Device device
    Location location  //unidirectional one to many FK - i.e. have to query for equipment as some location

    static hasMany = [children: Equipment]
    static belongsTo = [parent:Equipment]

    boolean isEquipmentContainer() {
        equipmentContainer
    }

    static constraints = {
        equipmentContainer nullable: false
        children nullable:true
        parent nullable:true
        category nullable:false
        assetTag nullable:true
        numberOfU nullable:true
        serialNumber nullable:true
        dimensions nullable:true
        product nullable:true
        device nullable:true
        location nullable:true
    }

    String toString() {
        "Equipment (category:$category, name:$name)[id:$id]"
    }

}
