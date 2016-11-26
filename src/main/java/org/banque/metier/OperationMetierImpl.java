package org.banque.metier;

import java.util.Date;



import org.banque.dao.CompteRepository;
import org.banque.dao.EmployeRepository;
import org.banque.dao.OperationRepository;
import org.banque.entities.Compte;
import org.banque.entities.Employe;
import org.banque.entities.Operation;
import org.banque.entities.Retrait;
import org.banque.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationMetierImpl implements OperationMetier {

	@Autowired 
	private OperationRepository operationRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private EmployeRepository employeRepository;
	@Override
	@Transactional
	public Boolean verser(String code, double montant, Long codeEmp) {
		Compte cp = compteRepository.findOne(code);
		Employe emp = employeRepository.findOne(codeEmp);
		Operation op = new Versement();
		op.setDateOperation(new Date());
		op.setMontant(montant);
		op.setCompte(cp);
		op.setEmploye(emp);
		operationRepository.save(op);
		cp.setSolde(cp.getSolde()+montant);
		return true;
	}

	@Override
	@Transactional
	public boolean retirer(String code, double montant, Long codeEmp) {
		Compte cp = compteRepository.findOne(code);
		if(cp.getSolde()<montant) throw new RuntimeException("Solde Insufisant");
		Employe emp = employeRepository.findOne(codeEmp);
		Operation op = new Retrait();
		op.setDateOperation(new Date());
		op.setMontant(montant);
		op.setCompte(cp);
		op.setEmploye(emp);
		operationRepository.save(op);
		cp.setSolde(cp.getSolde()-montant);
		
		return true;
	}

	@Override
	@Transactional
	public boolean virement(String cpte1, String cpte2, double montant, Long codeEmp) {
		retirer(cpte1, montant, codeEmp);
		verser(cpte2, montant, codeEmp);
		return true;
	}

	@Override
	public PageOperation getOperations(String codeCompte, int page, int size) {
		Page<Operation> ops = operationRepository.getoperations(codeCompte, new PageRequest(page, size));
		/*Compte cp = compteRepository.findOne(codeCompte);
		Page<Operation> ops = operationRepository.findByCompte(cp, new PageRequest(page, size));*/
		PageOperation pageOperation = new PageOperation();
		pageOperation.setOperations(ops.getContent());
		pageOperation.setNombreOperations(ops.getNumberOfElements());
		pageOperation.setPage(page);
		pageOperation.setTotalOperations(ops.getContent().size()*ops.getTotalPages());
		pageOperation.setTotalpages(ops.getTotalPages());
		return pageOperation;
	}
	
}
