package com.northkorea.supremesuccession

import java.io.Serializable

data class ContenderModel( val imageResId: Int, val name: String, val description: String, val url: String, var text: String = "") : Serializable