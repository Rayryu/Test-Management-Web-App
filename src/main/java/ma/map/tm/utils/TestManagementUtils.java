package ma.map.tm.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import ma.map.tm.dao.CampagneTestRepository;
import ma.map.tm.dao.CasTestRepository;
import ma.map.tm.dao.ProjetRepository;
import ma.map.tm.dao.RoleRepository;
import ma.map.tm.dao.ScenarioRepository;
import ma.map.tm.dao.TypeTestRepository;
import ma.map.tm.dao.UtilisateurRepository;
import ma.map.tm.entities.CampagneTest;
import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.Projet;
import ma.map.tm.entities.Role;
import ma.map.tm.entities.Scenario;
import ma.map.tm.entities.TypeTest;
import ma.map.tm.entities.Utilisateur;

public final class TestManagementUtils {
	
	private TestManagementUtils() {
	}
	
	public static List<String> getListOfPriorities() {
		List<String> listePriorite = new ArrayList<String>();
		listePriorite.add(Consts.FAIBLE);
		listePriorite.add(Consts.MOYENNE);
		listePriorite.add(Consts.HAUTE);
		return listePriorite;
	}
	
	public static List<String> getListOfStatus(){
		List<String> listeStatut = new ArrayList<String>();
		listeStatut.add(Consts.RÉUSSI);
		listeStatut.add(Consts.ECHOUÉ);
		listeStatut.add(Consts.BLOQUÉ);
		return listeStatut;
	}
	
	public static void start(Logger logger, ApplicationContext ac) {
		TypeTest t1 = new TypeTest(Consts.TEST_FONCTIONNEL);
		TypeTest t2 = new TypeTest(Consts.TEST_DE_STRESS);
		TypeTest t3 = new TypeTest(Consts.TEST_BOÎTE_NOIRE);
		TypeTest t4 = new TypeTest(Consts.TEST_D_UTILISABILITÉ);
		
		logger.info("Application context créé!");
		
		//Pour gérer les entités, on a créé les repositories 
		ProjetRepository projetRepository = ac.getBean(ProjetRepository.class);
		RoleRepository roleRepository = ac.getBean(RoleRepository.class);
		ScenarioRepository scenarioRepository = ac.getBean(ScenarioRepository.class);
		UtilisateurRepository utilisateurRepository = ac.getBean(UtilisateurRepository.class);
		CasTestRepository casTestRepository = ac.getBean(CasTestRepository.class);
		CampagneTestRepository campagneTestRepository = ac.getBean(CampagneTestRepository.class);
		TypeTestRepository typeTestRepository = ac.getBean(TypeTestRepository.class);
		
		logger.info("Création des objets : début");
		
		Projet p1 = new Projet("Projet 1", "Description du premier projet");
		Utilisateur u1 = new Utilisateur("El Bahaoui ", "Oussama");	
		Role r1 = new Role("Admin");
		Role r2 = new Role("Testeur");
		Role r3 = new Role("Visiteur");
		u1.setRole(r1);
//		u1.setMotDePass("$2a$10$3wCh1gu95hmi/CwMv8LLseTijaueEma.uFAFLCmvv7Nlm7O7J.gtC");
		u1.setMotDePass("dev");
		u1.setEmail("o.elbahaoui@gmail.com");
	
		Scenario s1 = new Scenario("Authentification", "L'utilisateur essaye de s'authentifier en entrant son login et mot de passe");
		CasTest c1 = new CasTest("Description du cas de test",t1, Consts.MOYENNE, "Préconditions...", "Connexion établie", "Connexion établie", "Remarques...", "Etape1... Etape2....", u1, s1);
		CasTest c12 = new CasTest("Description du cas de test2", t1, Consts.MOYENNE , "Préconditions...2", "Connexion établie2", "Connexion établie2", "Remarques...2", "Etape1... Etape2....2", u1, s1);
		CampagneTest ct1 = new CampagneTest("Campagne de test1", "Description de la campagne de tests", u1, p1); 
		
		logger.info("Création des objets : fin");
		
		Collection<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		Collection<Projet> listeProjets = new ArrayList<Projet>();
		Collection<CasTest> listeCasTests = new ArrayList<CasTest>();
		Collection<CampagneTest> listeCampagneTests = new ArrayList<CampagneTest>();
		Collection<Scenario> listeScenarios = new ArrayList<Scenario>();
		
		listeUtilisateurs.add(u1);
		listeProjets.add(p1);
		listeCasTests.add(c1);
		listeCasTests.add(c12);
		listeCampagneTests.add(ct1);
		listeScenarios.add(s1);
		
		u1.setListeCampagnes(listeCampagneTests);
		u1.setListeCasTests(listeCasTests);
		u1.setListeProjets(listeProjets);
		u1.setRole(r1);
		
		p1.setListeCampagne(listeCampagneTests);
		p1.setListeUtilisateurs(listeUtilisateurs);
		
		r1.setListeUtilisateurs(listeUtilisateurs);
		
		s1.setCampagneParent(ct1);
		s1.setListeCasTests(listeCasTests);
		
		c1.setTesteur(u1);
		c1.setScenarioParent(s1);
		
		ct1.setConcepteurTest(u1);
		ct1.setListeScenario(listeScenarios);
		ct1.setProjetParent(p1);
		
		logger.info("Persistance des données : début");
		
		typeTestRepository.save(t1);
		typeTestRepository.save(t2);
		typeTestRepository.save(t3);
		typeTestRepository.save(t4);
		roleRepository.save(r1);
		roleRepository.save(r2);
		roleRepository.save(r3);
		utilisateurRepository.save(u1);
		projetRepository.save(p1);
		projetRepository.save(p1);
		scenarioRepository.save(s1);
		campagneTestRepository.save(ct1);
		casTestRepository.save(c1);
		
		
//		utilisateurRepository.delete(u1);
//		campagneTestRepository.delete(ct1);
		
		List<Projet> pp = projetRepository.findByUtilisateurId(1L);
		
		Projet ppp = new Projet("NOM sans utilisateur", "DESCRIPTION");
		Utilisateur u3 = new Utilisateur();
		//u3.setId(2L);
		List<Utilisateur> lu = new ArrayList<Utilisateur>();
		lu.add(u3);
		lu.add(u1);
		
		
		Projet pppp = new Projet("NOM avec utilisateur", "DESCRIPTION", lu);
		
		Collection<Projet> lpu3 = u3.getListeProjets();
		lpu3.add(pppp);
		u3.setListeProjets(lpu3);
		
				
		//utilisateurRepository.save(u3);
		projetRepository.save(ppp);
		projetRepository.save(pppp);
		
		
		System.out.println(pp.get(0).getDescription());
		logger.info("Persistance des données : fin");
	}

}
