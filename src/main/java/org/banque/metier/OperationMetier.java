package org.banque.metier;

public interface OperationMetier {
	
	public Boolean verser(String code,double montant,Long codeEmp);
	public boolean retirer(String code,double montant,Long codeEmp);
	public boolean virement(String cpte1,String cpte2,double montant,Long codeEmp);
	public PageOperation getOperations(String codeCompte,int page,int size);
}
