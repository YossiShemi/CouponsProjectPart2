package com.jb.CouponsProjectPart2.repo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jb.CouponsProjectPart2.beans.Company;
import com.jb.CouponsProjectPart2.beans.Customer;
import com.jb.CouponsProjectPart2.exc.IDDoesntExistException;
import com.jb.CouponsProjectPart2.exc.InvalidAction;
import com.jb.CouponsProjectPart2.service.AdminService;

@Component
public class CouponsExpirationDaily {

	private static final int sleep = 24 * 60 * 60 * 100; // 24 hours
	@Autowired
	AdminService adminService;

	@Scheduled(fixedRate = sleep)
	public void deleteExpiere() {
		System.out.println();
		System.out.println("Start cleaner " + new Date().toLocaleString());

		List<Customer> customers = adminService.getAllCustomers();
		List<Company> companies = adminService.getAllCompanies();

		for (Customer customer : customers) {
			customer.deleteExpieredCoupons();
			try {
				adminService.updateCustomer(customer);
			} catch (IDDoesntExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (Company company : companies) {
			company.deleteExpieredCoupons();

			try {
				adminService.updateCompany(company);
			} catch (IDDoesntExistException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidAction e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.println("Cleaner is going to sleep " + (sleep / 100 / 60 / 60) + " hours");

	}

}
