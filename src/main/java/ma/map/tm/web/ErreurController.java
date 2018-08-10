package ma.map.tm.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErreurController implements ErrorController{
	
	@RequestMapping("/error")
	public String error() {
		return "404";
	}
	
	@RequestMapping("/403")
	public String forbidden() {
		return "403";
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
	
	
}
