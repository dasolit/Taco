package com.exam.tacokt.domain

import lombok.Getter
import lombok.Setter


@Getter
@Setter
data class Taco (
    var name: String? = null,
    var ingredients: List<Ingredient>? = null

)
