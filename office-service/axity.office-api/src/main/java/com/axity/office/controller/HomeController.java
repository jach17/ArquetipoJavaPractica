package com.axity.office.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Home controller class
 * 
 * @author username@axity.com
 *
 */
@Controller
public class HomeController
{
  @Value("${spring.application.name}")
  private String applicationName;
  
  /**
   * Default page
   */
  @GetMapping("/")
  public ModelAndView hello()
  {
    ModelAndView mv = new ModelAndView();
    mv.setViewName( "index" );
    mv.addObject( "message", applicationName );
    return mv;
  }
  
}
