package org.banque.metier;

import java.util.List;

import org.banque.entities.Client;

public interface ClientMetier {
	
	public Client saveClient(Client c);
	public List<Client> listClient();

}
