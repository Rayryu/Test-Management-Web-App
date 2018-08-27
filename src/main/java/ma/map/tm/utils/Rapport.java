package ma.map.tm.utils;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.map.tm.entities.Projet;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Rapport {
	
	public static void genererRapport(List<Projet> listeProjets) throws JRException {
		
		InputStream inputStream = Rapport.class.getResourceAsStream("/reports/Projet.jrxml");

		System.err.println("Ressource loaded");
		JasperReport rapport = JasperCompileManager.compileReport(inputStream);

		System.err.println("Report compiled");
		JasperPrint print = JasperFillManager.fillReport(rapport, null, new JRBeanCollectionDataSource(listeProjets));
		System.err.println("Report filled");
		JasperExportManager.exportReportToPdfFile(print, "output/test.pdf");
		System.err.println("Report printed");
	}	
	
	public static void genererRapport(Projet projet) throws JRException {
		
		InputStream inputStream = Rapport.class.getResourceAsStream("/reports/Projet.jrxml");

		System.err.println("Template ressource loaded");
		JasperReport rapport = JasperCompileManager.compileReport(inputStream);

		System.err.println("Report compiled");
		List<Projet> liste = new ArrayList<>();
		liste.add(projet);
		JasperPrint print = JasperFillManager.fillReport(rapport, null, new JRBeanCollectionDataSource(liste));
		System.err.println("Report filled");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy-hhmm");
		String dateAsString = simpleDateFormat.format(new Date());
		JasperExportManager.exportReportToPdfFile(print, "output/plan de test "+ projet.getNom()+"-"+dateAsString+".pdf");
		System.err.println("Report printed");
	}	
}
