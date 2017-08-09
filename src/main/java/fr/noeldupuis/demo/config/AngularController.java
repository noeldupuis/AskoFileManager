package fr.noeldupuis.demo.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularController {

    @RequestMapping({"login", "monCompte", "inscription"})
    public String index() {
        return "forward:/index.html";
    }
}
