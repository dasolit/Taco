package com.exam.tacojava.controller;

import com.exam.tacojava.domain.Ingredient;
import com.exam.tacojava.domain.Ingredient.Type;
import com.exam.tacojava.domain.Taco;

import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/design")
public class DesignTacoController {

  @GetMapping
  public String showDesignForm(Model model)
  {
    List<Ingredient> ingredients = Arrays.asList(
        new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
        new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
        new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
        new Ingredient("CARN", "Carnitas", Type.PROTEIN),
        new Ingredient("TMTO", "Diced Tomatos", Type.VEGGIES),
        new Ingredient("LETC", "Lettuce", Type.VEGGIES),
        new Ingredient("CHED", "Cheddar", Type.CHEESE),
        new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
        new Ingredient("SLSA", "Salsa", Type.SAUCE),
        new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
    );

    Type[] types = Ingredient.Type.values();
    for(Type type : types)
    {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }
    model.addAttribute("taco", new Taco());

    return "design";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
  }

  @PostMapping
  public String processDesign(@Valid Taco design, Errors errors) {
    if (errors.hasErrors()) {
      return "design";
    }
    log.info("Processing design: " + design);
    return "redirect:/orders/current";
  }


}
