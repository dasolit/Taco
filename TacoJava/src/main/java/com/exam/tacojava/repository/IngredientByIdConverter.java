package com.exam.tacojava.repository;

import com.exam.tacojava.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter
    implements Converter<String, Ingredient> {
  private final IngredientRepository ingredientRepository;

  @Autowired
  public IngredientByIdConverter(IngredientRepository ingredientRepo) {
    this.ingredientRepository = ingredientRepo;
  }

  @Override
  public Ingredient convert(String id) {
    Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);
    return optionalIngredient.orElse(null);
  }
}