package com.autoecole.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autoecole.dao.AutoEcoleDAO;
import com.autoecole.dao.CondidatDAO;
import com.autoecole.dao.MoniteurDAO;
import com.autoecole.dao.RoleDAO;
import com.autoecole.dao.UserDAO;
import com.autoecole.entities.AutoEcole;
import com.autoecole.entities.Condidat;
import com.autoecole.entities.Moniteur;
import com.autoecole.entities.Roles;
import com.autoecole.entities.User;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServices  {
	
	
	@Autowired
	private UserDAO repository ;
	
	
	@Autowired
	private RoleDAO rolerepository ;
	
	
	@Autowired
	private AutoEcoleDAO autorepository ;
	
	
	@Autowired
	private CondidatDAO candidatrepository ;
	
	
	@Autowired
	private MoniteurDAO moniteurrepository ;
	

	
	public User getbyid(Long id) {
		User user = repository.getOne(id);
		return user;
	}

	
	public User create(String nom, String prenom, Date dateNaiss, String sexe,
			String adresse, String nrotel, String cin, String email, Long role,
			Long autoecole , String salaire) {
		
		String username = nom+prenom ;
		String password = username ;
		Roles roles = rolerepository.getOne(role);
		AutoEcole auto = autorepository.getOne(autoecole);
		
		User us = new User(nom, prenom, dateNaiss, sexe, adresse, nrotel, cin, email, username, password, roles, auto);
		User uscreate = repository.saveAndFlush(us);
		
		
		if(role == 3){
			Condidat cand = new Condidat(uscreate);
			candidatrepository.saveAndFlush(cand);
			
		}else if(role == 2){
			
			
			Moniteur monit = new Moniteur(uscreate, 123, Double.parseDouble(""+salaire));
			
			moniteurrepository.saveAndFlush(monit);
		}
		return us;
	}

	
	public User update(Long id,String nom, String prenom, Date dateNaiss, String sexe,
			String adresse, String nrotel, String cin, String email, Long role,
			Long autoecole, byte[] image) {
	User condidat =	repository.getOne(id);
	
	if((nom.length() > 0)&&(nom != null)){
		
		condidat.setNom(nom);
	}
	if((prenom.length() > 0)&&(prenom != null)){
		
		condidat.setPrenom(prenom);
	}
	if(dateNaiss != null){
		
		condidat.setDateNaiss(dateNaiss);
	}
	if((sexe.length() > 0)&&(sexe != null)){
		condidat.setSexe(sexe);
		
	}
	if((adresse.length() > 0)&&(adresse != null)){
		condidat.setAdresse(adresse);
		
	}
	
	if((nrotel.length() > 0)&&(nrotel != null)){
		condidat.setNrotel(nrotel);
		
	}
	
	
	if((cin.length() > 0)&&(cin != null)){
		condidat.setCin(cin);
		
	}
	
	
	if((email.length() > 0)&&(email != null)){
		condidat.setEmail(email);
		
	}
	
	


	
	
	
	
	
	
	
	
	
	
	repository.save(condidat);
	
		return null;
	}

	
	public void delete(Long id) {
		repository.deleteById(id);
		
	}

	
	public List<User> list() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public User findByUsernameLike(String username) {
		User us = repository.findByUsernameLike(username);
		return us;
	}

	
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	
	public User getOne(Long id) {
		// TODO Auto-generated method stub
		return repository.getOne(id);
	}

	
	public List<User> findByEmailAddress(String sexe) {
		
		return repository.findByEmailAddress(sexe);
	}

	
	public List<User> findByRole(Long role) {
		
		Roles rolee = rolerepository.getOne(role);
		
		return repository.findByRole(rolee);
	}

	
}
