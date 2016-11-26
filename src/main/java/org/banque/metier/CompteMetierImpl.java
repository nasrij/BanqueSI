package org.banque.metier;

import java.util.Date;

import org.banque.dao.CompteRepository;
import org.banque.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteMetierImpl implements CompteMetier {

	@Autowired
	private CompteRepository compteRepository;
	
	@Override
	public Compte saveCompte(Compte c) {
		c.setDateCreation(new Date());
		return compteRepository.save(c);
	}

	@Override
	public Compte getCompte(String code) {
		// TODO Auto-generated method stub
		Compte cp = compteRepository.findOne(code);
		if(cp == null) throw new RuntimeException("Compte Innexistant"); 
			
		return cp;
	}

}
