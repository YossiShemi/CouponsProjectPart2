package com.jb.CouponsProjectPart2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jb.CouponsProjectPart2.beans.Company;
import com.jb.CouponsProjectPart2.beans.Coupon;
import com.jb.CouponsProjectPart2.beans.Customer;
import com.jb.CouponsProjectPart2.exc.IDDoesntExistException;
import com.jb.CouponsProjectPart2.exc.InvalidAction;
import com.jb.CouponsProjectPart2.exc.ItemAlreadyExist;

@Service
public class AdminService extends ClientService {

	@Override
	public boolean login(String email, String password) {
		if (email == "admin@admin.com" && password == "admin")
			return true;
		return false;
	}

	// Companies actions

	public void addCompany(Company company) throws ItemAlreadyExist {
		if (companyRepository.findByEmail(company.getEmail()) != null)
			throw new ItemAlreadyExist("The email" + company.getEmail() + " already exist");
		if (companyRepository.findByName(company.getName()) != null)
			throw new ItemAlreadyExist("The name" + company.getName() + " already exist");
		else
			companyRepository.save(company);
	};

	public void updateCompany(Company company) throws IDDoesntExistException, InvalidAction {
		if (companyRepository.findById(company.getId()) == null)
			throw new IDDoesntExistException();
		Company company2 = companyRepository.getOne(company.getId());
		if (!company.getName().equals(company2.getName())) {
			throw new InvalidAction("Cannot update company name");
		}
		companyRepository.saveAndFlush(company);
	}

	public void deleteCompany(Company c) throws IDDoesntExistException {
		if (companyRepository.findById(c.getId()) == null)
			throw new IDDoesntExistException();
		// Delete all purchases related to this company
		List<Customer> customers = this.getAllCustomers();
		for (Customer customer : customers) {
			customer.deletCompanyCoupons(c);
		}
		Company company = companyRepository.findById(c.getId());
		companyRepository.delete(company);
	}

	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	public Company getOneCompany(int companyID) throws IDDoesntExistException {
		if (companyRepository.findById(companyID) == null)
			throw new IDDoesntExistException();
		return companyRepository.findById(companyID);
	}

	// Customers actions

	public void addCustomer(Customer customer) throws ItemAlreadyExist {
		if (customerRepository.findByEmail(customer.getEmail()) != null)
			throw new ItemAlreadyExist("The email" + customer.getEmail() + " already exist");
		customerRepository.save(customer);
	}

	public void updateCustomer(Customer customer) throws IDDoesntExistException {
		if (customerRepository.findById(customer.getId()) == null)
			throw new IDDoesntExistException();
		customerRepository.saveAndFlush(customer);
	}

	public void deleteCustomer(Customer customer) throws IDDoesntExistException {
		if (customerRepository.findById(customer.getId()) == null)
			throw new IDDoesntExistException();
		customerRepository.delete(customer);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getOneCustomer(int customerID) throws IDDoesntExistException {
		if (customerRepository.findById(customerID) == null)
			throw new IDDoesntExistException();
		return customerRepository.findById(customerID);
	}

	// Coupons actions

	public List<Coupon> getAllCoupons() {
		return couponRepository.findAll();
	}

	public Coupon getCouponIDByTitle(Coupon coupon) { // For testing beans
		return couponRepository.findByTitle(coupon.getTitle());
	}

}
