package com.example.jajji.model

import java.io.Serializable

class User:Serializable {
    var id: String? = null
    var name: String? = null
    var surName: String? = null

    constructor(id: String?, name: String?, surName: String?) {
        this.id = id
        this.name = name
        this.surName = surName
    }
}