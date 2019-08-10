package org.codesolt.mongokubernetesdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class HomeRedirectController {

    @GetMapping
    public ModelAndView swaggerUi() {
        return new ModelAndView("redirect:" + "/swagger-ui.html");
    }

}
