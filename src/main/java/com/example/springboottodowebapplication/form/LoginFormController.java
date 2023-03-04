package com.example.springboottodowebapplication.form;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginFormController {
    private AuthenticationService authenticationService;

    public LoginFormController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value="login-form", method = RequestMethod.GET)
    public String gotoLoginPage() {
        return "loginform";
    }

    @RequestMapping(value="login-form", method = RequestMethod.POST)
    public String gotoWelcomePage( @RequestParam String name, @RequestParam String password, ModelMap model) {

        if(authenticationService.authenticate(name,password)) {
            model.addAttribute("name", name);
            return "welcome";
        }
        model.addAttribute("errorMsg","Invalid credentials");
        return "loginform";
    }
}
