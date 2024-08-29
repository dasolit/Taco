package com.exam.tacokt.domain

import lombok.Getter
import lombok.Setter

@Setter
@Getter
data class Ingredient (
    var id: String? = null,
    var name: String? = null,
    var type: Type? = null
)
