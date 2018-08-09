package ma.map.tm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
	
	@RequestMapping("/Dashboard")
	public String dashboard() {
		return "Dashboard";
	}
}
