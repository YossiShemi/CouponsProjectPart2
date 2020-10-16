package com.jb.CouponsProjectPart2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jb.CouponsProjectPart2.beans.Company;

//Equals to DAO+DBDAO- 50,40,10;
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Company findById(int id);

	Company findByName(String name); // Check for EX

	Company findByEmail(String email); // Check for EX

	Company findByEmailAndPassword(String email, String password); // Login

}