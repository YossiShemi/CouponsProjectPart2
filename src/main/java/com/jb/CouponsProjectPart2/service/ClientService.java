package com.jb.CouponsProjectPart2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jb.CouponsProjectPart2.exc.LoginFailed;
import com.jb.CouponsProjectPart2.repo.CompanyRepository;
import com.jb.CouponsProjectPart2.repo.CouponRepository;
import com.jb.CouponsProjectPart2.repo.CustomerRepository;

@Service
public abstract class ClientService {

	@Autowired
	protected CompanyRepository companyRepository;
	@Autowired
	protected CustomerRepository customerRepository;
	@Autowired
	protected CouponRepository couponRepository;

	abstract boolean login(String email, String password) throws LoginFailed;
}
