package ma.map.tm.services;

import java.util.Collection;

import ma.map.tm.entities.CasTest;
import ma.map.tm.entities.ExecutionTest;
import ma.map.tm.entities.Utilisateur;

public interface ExecutionCasService {
	public void ajouterResultat(Long idCasDeTest, ExecutionTest nouvelleExecution);

	public Collection<ExecutionTest> getResultatsPrecedents(CasTest casTestCourant, Utilisateur loggedInUser);

}
