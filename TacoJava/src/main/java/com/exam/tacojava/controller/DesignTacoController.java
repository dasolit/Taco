package com.exam.tacojava.controller;

import com.exam.tacojava.domain.Taco;
import com.exam.tacojava.repository.TacoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;


//200쪽
@Log4j2
@RestController
@RequestMapping(value = "/design", produces = {"application/json", "text/xml"})
@SessionAttributes("order")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DesignTacoController {

  private final TacoRepository tacoRepository;

  @GetMapping("/recent")
  public Iterable<Taco> recentTacos(){
    PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
    return tacoRepository.findAll(page);
  }

//  @GetMapping("/{id}")
//  public Taco tacoById(@PathVariable Long id) {
//    Optional<Taco> taco = tacoRepository.findById(id);
//    return taco.orElseThrow(() ->
//        new IllegalStateException("Could not find taco with id: " + id));
//  }
}
