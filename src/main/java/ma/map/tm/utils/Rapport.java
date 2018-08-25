package ma.map.tm.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.map.tm.entities.Projet;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Rapport {
	
	static void genererRapport(List<Projet> listeProjets) throws JRException {
		
		InputStream inputStream = Rapport.class.getResourceAsStream("/reports/Projet.jrxml");


		JasperReport rapport = JasperCompileManager.compileReport(inputStream);

		
		JasperPrint print = JasperFillManager.fillReport(rapport, null, new JRBeanCollectionDataSource(listeProjets));
		
		JasperExportManager.exportReportToPdfFile(print, "output/test.pdf");
		
	}	
}
