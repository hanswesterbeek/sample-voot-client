package vootclient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  @RequestMapping("/")
  public ModelAndView index() {
    return new ModelAndView("index");
  }

  @RequestMapping("/myGroups")
  public ModelAndView myGroups() {

    ModelMap modelMap = new ModelMap();

    return new ModelAndView("myGroups", modelMap);
  }
}