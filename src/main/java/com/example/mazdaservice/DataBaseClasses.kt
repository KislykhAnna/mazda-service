package com.example.mazdaservice

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties

class User {
    var userName: String = ""
    var mobileNumber: String = ""
    var email: String = ""
    var password: String = ""
    var pointsAmount: Int = 0

    constructor(
        userName: String,
        mobileNumber: String,
        email: String,
        password: String,
        pointsAmount: Int
    ) {
        this.userName = userName
        this.mobileNumber = mobileNumber
        this.email = email
        this.password = password
        this.pointsAmount = pointsAmount
    }
}

class RepairRecords {
    var uniqueCode: String = ""
    var stockCode: String = ""
    var repairTopic: String = ""
    var carModel: String = ""
    var comments: String = ""
    var date: Any = "00.00.0000"
    var time: Any = "00:00"

    constructor(
        uniqueCode: String,
        stockCode: String,
        repairTopic: String,
        carModel: String,
        comments: String,
        date: Any,
        time: Any
    ) {
        this.uniqueCode = uniqueCode
        this.stockCode = stockCode
        this.repairTopic = repairTopic
        this.carModel = carModel
        this.comments = comments
        this.date = date
        this.time = time
    }
}

class RepairHistory {
    var uniqueCode: String = ""
    var status: String = ""
    var stockCode: String = ""
    var topic: String = ""
    var amount: Int = 0
    var carModel: String = ""
    var mileage: Int = 0
    var customerMaterials: String = ""
    var additionally: String = ""
    var date: Any = "00.00.0000"
    var finalCost : Int = 0

    constructor() {}

    constructor(
        uniqueCode: String,
        status: String,
        stockCode: String,
        topic: String,
        amount: Int,
        carModel: String,
        mileage: Int,
        customerMaterials: String,
        additionally: String,
        date: Any,
        finalCost: Int
    ) {
        this.uniqueCode = uniqueCode
        this.status = status
        this.stockCode = stockCode
        this.topic = topic
        this.amount = amount
        this.carModel = carModel
        this.mileage = mileage
        this.customerMaterials = customerMaterials
        this.additionally = additionally
        this.date = date
        this.finalCost = finalCost
    }
}

class PurchaseHistory {
    var uniqueCode: String = ""
    var productCode: String = ""
    var productName: String = ""
    var amount: Int = 0
    var unitCost: Int = 0
    var date: Any = "00.00.0000"
    var finalCost : Int = 0

    constructor() {}

    constructor(
        uniqueCode: String,
        productCode: String,
        productName: String,
        amount: Int,
        unitCost: Int,
        date: Any,
        finalCost: Int
    ) {
        this.uniqueCode = uniqueCode
        this.productCode = productCode
        this.productName = productName
        this.amount = amount
        this.unitCost = unitCost
        this.date = date
        this.finalCost = finalCost
    }
}

class Store {
    var productCode: String = ""
    var productName: String = ""
    var unitCost: Int = 0
    var remainingAmount : Int = 0

    constructor() {}

    constructor(
        productCode: String,
        productName: String,
        unitCost: Int,
        remainingAmount: Int
    ) {
        this.productCode = productCode
        this.productName = productName
        this.unitCost = unitCost
        this.remainingAmount = remainingAmount
    }
}

class NewsAndStocks {
    var stockText: String = ""
    var stockCode: String = ""
    var repairTopic: String = ""
    var imagine: Any = 0
    var pointsCost: Int = 0
    var pointsAmout: Int = 0
    var discount: Int = 0

    constructor() {}

    constructor(
        stockText: String,
        stockCode: String,
        repairTopic: String,
        imagine: Any,
        pointsCost: Int,
        pointsAmout: Int,
        discount: Int
    ) {
        this.stockText = stockText
        this.stockCode = stockCode
        this.repairTopic = repairTopic
        this.imagine = imagine
        this.pointsCost = pointsCost
        this.pointsAmout = pointsAmout
        this.discount = discount
    }
}

class HistoryElements {
    var type: String = ""
    var topic: String = ""
    var date: Any = "00.00.0000"
    var status: String = ""



    constructor() {}

    constructor(
        status: String,
        topic: String,
        date: Any,
        type: String
    ) {
        this.status = status
        this.topic = topic
        this.date = date
        this.type = type
    }
}