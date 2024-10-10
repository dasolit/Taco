package com.exam.tacojava.controller;

import com.exam.tacojava.config.OrderProps;
import com.exam.tacojava.domain.Order;
import com.exam.tacojava.domain.User;
import com.exam.tacojava.repository.OrderRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Log4j2
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@RequiredArgsConstructor
public class OrderController {

  /*
  @Value("${taco.orders.pageSize}")
  private final int pageSize = 20;
  */
  private final OrderProps orderProps;
  private final OrderRepository orderRepository;

  @GetMapping("/current")
  public String orderForm(Model model) {
    model.addAttribute("order", new Order());
    return "orderForm";
  }

  @PostMapping
  public String processOrder(
      @Valid Order order, Errors errors,
      SessionStatus sessionStatus,
      @AuthenticationPrincipal User user
  ) {
    if (errors.hasErrors()){
      return "orderForm";
    }
    order.setUser(user);

    orderRepository.save(order);
    sessionStatus.setComplete();
    return "redirect:/";
  }

  @GetMapping
  public String ordersForUser(
      @AuthenticationPrincipal User user,
      Model model){
    Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
    orderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
    return "orderList";
  }
}
