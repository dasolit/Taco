package com.exam.tacokt.controller

import com.exam.tacokt.domain.Ingredient
import com.exam.tacokt.domain.Taco
import com.exam.tacokt.domain.Type
import lombok.extern.log4j.Log4j2
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@Log4j2
@Controller
@RequestMapping("/design")
class DesignTacoController {
    @GetMapping
    fun showDesignForm(model: Model): String {
        val ingredients = listOf(
            Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
            Ingredient("COTO", "Corn Tortilla", Type.WRAP),
            Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
            Ingredient("CARN", "Carnitas", Type.PROTEIN),
            Ingredient("TMTO", "Diced Tomatos", Type.VEGGIES),
            Ingredient("LETC", "Lettuce", Type.VEGGIES),
            Ingredient("CHED", "Cheddar", Type.CHEESE),
            Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
            Ingredient("SLSA", "Salsa", Type.SAUCE),
            Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        )

        val types = Type.values()
        for (type in types) {
            model.addAttribute(type.toString().lowercase(), filterByType(ingredients, type))
        }
        model.addAttribute("taco", Taco())

        return "design"
    }

    private fun filterByType(ingredients: List<Ingredient>, type: Type): List<Ingredient> {
        val filteredIngredients = mutableListOf<Ingredient>()
        for (ingredient in ingredients) {
            if (ingredient.type == type) {
                filteredIngredients.add(ingredient)
            }
        }
        return filteredIngredients
    }

}
