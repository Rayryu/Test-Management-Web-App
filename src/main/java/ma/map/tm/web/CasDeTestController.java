package ma.map.tm.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ma.map.tm.entities.CampagneTest;
import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Scenario;
import ma.map.tm.entities.TypeTest;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.CampagneService;
import ma.map.tm.services.CasTestService;
import ma.map.tm.services.ProjetService;
import ma.map.tm.services.ScenarioService;
import ma.map.tm.services.TypeTestService;
import ma.map.tm.services.UtilisateurService;
import ma.map.tm.utils.TestManagementUtils;

@Controller
public class CasDeTestController {
	
	@Autowired
	private ScenarioService scenarioService;
	@Autowired
	private CasTestService casTestService;
	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private CampagneService campagneService;
	@Autowired
	private ProjetService projetService;
	@Autowired
	private TypeTestService typeTestService;
	
	
	@RequestMapping(value= "/Scenario/{id}")
	public String Scenario(Model model, @PathVariable("id") Long id_scenarioParent) {
		
		Scenario scenarioParent = scenarioService.getScenarioById(id_scenarioParent);
		List<CasTest> listeDesCas = casTestService.listeCasTestParScenario(scenarioParent);
		CampagneTest campagneParente = campagneService.getCampagneById(scenarioParent.getCampagne().getId());
		Projet projetParent  = projetService.getProjetById(campagneParente.getProjetParent().getId());
		

		List<TypeTest> listetypeTest = typeTestService.getAllTypeTest();
		List<String> listePriorite = TestManagementUtils.getListOfPriorities();
		
		model.addAttribute("campagneParente", campagneParente);
		model.addAttribute("scenarioParent", scenarioParent);
		model.addAttribute("projetParent", projetParent);
		model.addAttribute("listePriorite", listePriorite);
		model.addAttribute("listeDesCas", listeDesCas);
		model.addAttribute("listetypeTest", listetypeTest);
		model.addAttribute("nouveauCasDeTest", new CasTest());
		model.addAttribute("nouveauScenario", new Scenario());
	
		return "Scenario";
	}
	
	
	
	@RequestMapping(value="/AjouterCasDeTest/{id}", method=RequestMethod.POST)
	public ModelAndView ajouterCasDeTest(Model model, CasTest nouveauCasDeTest, @PathVariable("id") Long id_scenarioParent) {
		
		Utilisateur currentUser = utilisateurService.getLoggedInUser();	
		
		nouveauCasDeTest.setTesteur(currentUser);
		Scenario scenarioParent = scenarioService.getScenarioById(id_scenarioParent);
		nouveauCasDeTest.setScenario(scenarioParent);
		
		casTestService.addCasDeTest(nouveauCasDeTest);

		return new ModelAndView("redirect:/Scenario/"+id_scenarioParent.toString());
	}
	
	@RequestMapping("/SelectedCasTest/{id}")
	public String selectedCasTest(Model model, @PathVariable("id") Long id_casTest) {
		
		Scenario scenarioParent = scenarioService.getScenarioByCasTestId(id_casTest);
		CampagneTest campagneParente = campagneService.getCampagneById(scenarioParent.getCampagne().getId());
		Projet projetParent  = projetService.getProjetById(campagneParente.getProjetParent().getId());
		CasTest casTestCourant = casTestService.getCasTestById(id_casTest);
		casTestCourant.setScenario(scenarioParent);

		
		List<TypeTest> listetypeTest = typeTestService.getAllTypeTest();
		List<String> listePriorite = TestManagementUtils.getListOfPriorities();
		
		model.addAttribute("listePriorite", listePriorite);
		model.addAttribute("campagneParente", campagneParente);
		model.addAttribute("scenarioParent", scenarioParent);
		model.addAttribute("casTestCourant", casTestCourant);
		model.addAttribute("projetParent", projetParent);
		model.addAttribute("listetypeTest", listetypeTest);
		
		return "SelectedCasDeTests";
	}
	
	@RequestMapping("/ModifierCasDeTest/{id}")
	public ModelAndView modifierCasTest(@PathVariable("id") Long idCasTest, CasTest casTestCourant) {
		
		casTestService.modifierCasTest(casTestCourant, idCasTest);
		
		return new ModelAndView("redirect:/Scenario/"+casTestCourant.getScenario().getId().toString());
	}
	
	@RequestMapping("/SupprimerCasTest/{id}")
	public ModelAndView supprimerCasTest(@PathVariable("id") Long idCasTest) {
		CasTest casTestCourant = casTestService.getCasTestById(idCasTest);
		
		casTestService.supprimerCasTest(idCasTest);
		
		return new ModelAndView("redirect:/Scenario/"+casTestCourant.getScenario().getId().toString());
	}
	
	@RequestMapping(value= "/AjouterCas/{id}")
	public String AjouterCas(Model model, @PathVariable("id") Long id_scenarioParent) {
		
		Scenario scenarioParent = scenarioService.getScenarioById(id_scenarioParent);
		List<CasTest> listeDesCas = casTestService.listeCasTestParScenario(scenarioParent);
		CampagneTest campagneParente = campagneService.getCampagneById(scenarioParent.getCampagne().getId());
		Projet projetParent  = projetService.getProjetById(campagneParente.getProjetParent().getId());
		List<TypeTest> listetypeTest = typeTestService.getAllTypeTest();
		List<String> listePriorite = TestManagementUtils.getListOfPriorities();

		model.addAttribute("listePriorite", listePriorite);
		model.addAttribute("campagneParente", campagneParente);
		model.addAttribute("scenarioParent", scenarioParent);
		model.addAttribute("projetParent", projetParent);
		model.addAttribute("listeDesCas", listeDesCas);
		model.addAttribute("listetypeTest", listetypeTest);
		model.addAttribute("nouveauCasDeTest", new CasTest());
		model.addAttribute("nouveauScenario", new Scenario());
		
		return "AjouterCasTest";
	}
	
}
