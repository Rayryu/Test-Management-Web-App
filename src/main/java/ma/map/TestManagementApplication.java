package ma.map;


import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ma.map.tm.services.ScenarioService;
import ma.map.tm.utils.TestManagementUtils;
import net.sf.jasperreports.engine.JRException;

@SpringBootApplication
public class TestManagementApplication {

	public static void main(String[] args) throws JRException {
		
		final Logger logger = Logger.getLogger(TestManagementApplication.class);
		ApplicationContext ac =  SpringApplication.run(TestManagementApplication.class, args);
		//TestManagementUtils.start(logger, ac);
		TestManagementUtils.testJasperRepports(ac);
	}
}
