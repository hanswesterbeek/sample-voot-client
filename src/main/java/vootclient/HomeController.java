package vootclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  @Autowired
  private VootService vootService;

  @RequestMapping("/")
  public ModelAndView index() {
    return new ModelAndView("index");
  }

  @RequestMapping("/myGroups")
  public ModelAndView myGroups() {

    final String someGroupName = vootService.getSomeGroupName();
    ModelMap modelMap = new ModelMap();
    modelMap.put("name", someGroupName);
    return new ModelAndView("myGroups", modelMap);
  }
}
