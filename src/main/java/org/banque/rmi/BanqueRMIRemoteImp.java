package org.banque.rmi;

import java.rmi.RemoteException;

import org.banque.dao.CompteRepository;
import org.banque.dao.EmployeRepository;
import org.banque.dao.OperationRepository;
import org.banque.entities.Compte;
import org.banque.metier.CompteMetier;
import org.banque.metier.OperationMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("myRmiService")
public class BanqueRMIRemoteImp implements BanqueRMIRemote {

	@Autowired
	private CompteMetier compteMetier;

	@Autowired
	private OperationMetier operationMetier;
	
	
	@Override
	public Compte saveCompte(Compte cp) throws RemoteException {
		// TODO Auto-generated method stub
		return compteMetier.saveCompte(cp);
	}

	@Override
	public Compte getCompte(String code) throws RemoteException {
		
		return compteMetier.getCompte(code);
	}

	@Override
	public Boolean verser(String code, double montant, Long codeEmp) throws RemoteException {
		// TODO Auto-generated method stub
		return operationMetier.verser(code, montant, codeEmp);
	}

	@Override
	public boolean retirer(String code, double montant, Long codeEmp) throws RemoteException {
		
		return operationMetier.retirer(code, montant, codeEmp);
	}

	@Override
	public boolean virement(String cpte1, String cpte2, double montant, Long codeEmp) throws RemoteException {
		
		return operationMetier.virement(cpte1, cpte2, montant, codeEmp);
	}

}
