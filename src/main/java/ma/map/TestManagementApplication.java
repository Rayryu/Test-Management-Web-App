package ma.map;


import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ma.map.tm.utils.TestManagementUtils;
import net.sf.jasperreports.engine.JRException;

@SpringBootApplication
public class TestManagementApplication {

	public static void main(String[] args) throws JRException {
		
		ApplicationContext ac =  SpringApplication.run(TestManagementApplication.class, args);
	}
}
