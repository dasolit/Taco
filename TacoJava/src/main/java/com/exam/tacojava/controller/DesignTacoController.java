package com.exam.tacojava.controller;

import com.exam.tacojava.domain.Ingredient;
import com.exam.tacojava.domain.Ingredient.Type;
import com.exam.tacojava.domain.Order;
import com.exam.tacojava.domain.Taco;

import com.exam.tacojava.domain.User;
import com.exam.tacojava.repository.IngredientRepository;
import com.exam.tacojava.repository.TacoRepository;
import com.exam.tacojava.repository.UserRepository;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Log4j2
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@RequiredArgsConstructor
public class DesignTacoController {

  private final TacoRepository tacoRepository;
  private final IngredientRepository ingredientRepository;
  private final UserRepository userRepository;

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }
  @GetMapping
  public String showDesignForm(
      Model model,
      Principal principal
  )
  {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepository.findAll().forEach(ingredients::add);

    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(ingredients, type));
    }

    String username = principal.getName();
    User user = userRepository.findByUsername(username);
    model.addAttribute("user", user);

    return "design";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
  }

  @PostMapping
  public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
    if (errors.hasErrors()) {
      return "design";
    }
    Taco saved = tacoRepository.save(design);
    order.addDesign(saved);
    return "redirect:/orders/current";
  }


}
