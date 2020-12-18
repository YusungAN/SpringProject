package dimigo.AnSon.SpringProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // home을 띄워준다..!
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
