package org.banque.metier;

import java.util.List;

import org.banque.dao.EmployeRepository;
import org.banque.entities.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmployeMetierImp implements EmployeMetier {

	@Autowired
	private EmployeRepository employeRepository;
	@Override
	public Employe saveEmploye( Employe e) {
		return employeRepository.save(e);
	}

	@Override
	public List<Employe> listEmploye() {
		return employeRepository.findAll();
	}

}
