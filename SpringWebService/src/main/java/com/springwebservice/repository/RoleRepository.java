package com.springwebservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springwebservice.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	 List<Role> findAll();
	    
	 List<Role> findByName(String name);
	
}
