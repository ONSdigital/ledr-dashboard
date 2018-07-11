package uk.gov.ons.lerp.poc.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  @RequestMapping("/logout")
  public String logout() {
    return "logout";
  }

}
