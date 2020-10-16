package com.jb.CouponsProjectPart2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jb.CouponsProjectPart2.exc.LoginFailed;
import com.jb.CouponsProjectPart2.service.AdminService;
import com.jb.CouponsProjectPart2.service.ClientService;
import com.jb.CouponsProjectPart2.service.ClientType;
import com.jb.CouponsProjectPart2.service.CompanyService;
import com.jb.CouponsProjectPart2.service.CustomerService;

@Service
public class LoginManager {

	@Autowired
	AdminService admin;
	@Autowired
	CustomerService customerService;
	@Autowired
	CompanyService companyService;

	public ClientService login(String email, String password, ClientType clientType) throws LoginFailed {

		if (clientType == ClientType.Administrator && admin.login(email, password))
			return admin;
		else if (clientType == ClientType.Customer && customerService.login(email, password))
			return customerService;
		else if (clientType == ClientType.Company && companyService.login(email, password))
			return companyService;
		else {
			System.out.println("The Email and/or password are wrong. Please try again.");
			return null;
		}
	}
}
