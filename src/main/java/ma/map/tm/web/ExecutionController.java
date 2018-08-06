package ma.map.tm.web;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ma.map.tm.dao.ExecutionTestRepository;
import ma.map.tm.entities.CampagneTest;
import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.ExecutionTest;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Scenario;
import ma.map.tm.entities.TypeTest;
import ma.map.tm.entities.Utilisateur;
import ma.map.tm.services.CampagneService;
import ma.map.tm.services.CasTestService;
import ma.map.tm.services.ExecutionCasService;
import ma.map.tm.services.ProjetService;
import ma.map.tm.services.ScenarioService;
import ma.map.tm.services.TypeTestService;
import ma.map.tm.services.UtilisateurService;
import ma.map.tm.utils.TestManagementUtils;

@Controller
public class ExecutionController {
	
	@Autowired
	private ScenarioService scenarioService;
	@Autowired
	private CasTestService casTestService;
	@Autowired
	private CampagneService campagneService;
	@Autowired
	private ProjetService projetService;
	@Autowired
	private TypeTestService typeTestService;
	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private ExecutionCasService executionCasService;
	
	@RequestMapping("/Execution")
	public String executionMain(Model model) {
		
		Utilisateur currentUser = utilisateurService.getLoggedInUser();
		
		List<CasTest> listeDesCas = casTestService.listeCasTestParUtilisateur(currentUser);
		
		model.addAttribute("listeDesCas", listeDesCas);
		
		return "Executions";
	}
	
	@RequestMapping("/ExecuterCasDeTest/{id}")
	public String executerCasDeTest(Model model, @PathVariable("id") Long id_casTest) {
		
		Scenario scenarioParent = scenarioService.getScenarioByCasTestId(id_casTest);
		CampagneTest campagneParente = campagneService.getCampagneById(scenarioParent.getCampagne().getId());
		Projet projetParent  = projetService.getProjetById(campagneParente.getProjetParent().getId());
		CasTest casTestCourant = casTestService.getCasTestById(id_casTest);
		casTestCourant.setScenario(scenarioParent);
		ExecutionTest nouvelleExecution = new ExecutionTest();
		Collection<ExecutionTest> listeDesResultats = executionCasService.getResultatsPrecedents(casTestCourant, utilisateurService.getLoggedInUser());
		
		List<TypeTest> listetypeTest = typeTestService.getAllTypeTest();
		List<String> listePriorite = TestManagementUtils.getListOfPriorities();
		List<String> listeStatut = TestManagementUtils.getListOfStatus();
		
		
		model.addAttribute("listetypeTest", listetypeTest);
		model.addAttribute("listePriorite", listePriorite);
		model.addAttribute("listeStatut", listeStatut);
		model.addAttribute("campagneParente", campagneParente);
		model.addAttribute("scenarioParent", scenarioParent);
		model.addAttribute("projetParent", projetParent);
		model.addAttribute("casTestCourant", casTestCourant);
		model.addAttribute("nouvelleExecution", nouvelleExecution);
		model.addAttribute("listeDesResultats", listeDesResultats);
		
		return "ExecuterCasDeTest";
	}
	
	@RequestMapping("/AjouterResultat/{id}")
	public ModelAndView ajouterResultat(@PathVariable("id") Long idCasDeTest, ExecutionTest nouvelleExecution) {
		
		executionCasService.ajouterResultat(idCasDeTest, nouvelleExecution);
		
		return new ModelAndView("redirect:/Execution/");
		
	}
}
