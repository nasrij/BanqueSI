package org.banque.metier;

import org.banque.entities.Compte;

public interface CompteMetier {
	public Compte saveCompte(Compte c);
	public Compte getCompte(String code);
}
