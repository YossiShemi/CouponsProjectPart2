package com.jb.CouponsProjectPart2.tests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jb.CouponsProjectPart2.beans.Category;
import com.jb.CouponsProjectPart2.beans.Company;
import com.jb.CouponsProjectPart2.beans.Coupon;
import com.jb.CouponsProjectPart2.beans.Customer;
import com.jb.CouponsProjectPart2.exc.IDDoesntExistException;
import com.jb.CouponsProjectPart2.exc.InvalidAction;
import com.jb.CouponsProjectPart2.exc.LoginFailed;
import com.jb.CouponsProjectPart2.repo.CouponsExpirationDaily;
import com.jb.CouponsProjectPart2.repo.CouponsExpirationDaily;
import com.jb.CouponsProjectPart2.rest.LoginManager;
import com.jb.CouponsProjectPart2.exc.ItemAlreadyExist;
import com.jb.CouponsProjectPart2.service.AdminService;
import com.jb.CouponsProjectPart2.service.ClientType;
import com.jb.CouponsProjectPart2.service.CompanyService;
import com.jb.CouponsProjectPart2.service.CustomerService;
import com.jb.CouponsProjectPart2.utils.Utils;

@Component
@Order(value = 2)
public class Test implements CommandLineRunner {

	@Autowired
	private LoginManager loginManager;
	@Autowired
	private CouponsExpirationDaily cleaner;
	@Autowired
	private AdminService admin;

	public void printTables(String s) {
		System.out.println("\n");
		System.out.println("\n");
		Utils.printTestLine(s);
		Utils.printCompaniesTable(admin.getAllCompanies());
		Utils.printCustomersTable(admin.getAllCustomers());
		Utils.printCouponsTable(admin.getAllCoupons());
		System.out.println("\n");
		System.out.println("\n");

	}

	public void testAdminService() {
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("---------- Testing AdminService ----------");
		System.out.println();
		System.out.println("Admin logged in.");
		AdminService adminService = null;
		try {
			adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);
		} catch (LoginFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Company
		Company company = new Company("TestAdmim", "TestAdmim", "TestAdmim", null);
		System.out.println("Added company+ Get by Admin: ");
		try {
			adminService.addCompany(company);
		} catch (ItemAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(adminService.getOneCompany(company.getId()));
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		company.setEmail("AdminChanged");
		try {
			adminService.updateCompany(company);
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Updated company: ");
		try {
			System.out.println(adminService.getOneCompany(company.getId()));
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Customer
		Customer customer = new Customer("TestAdmim", "TestAdmim", "TestAdmim", "TestAdmim", null);
		System.out.println("Added customer+ Get by Admin: ");
		try {
			adminService.addCustomer(customer);
		} catch (ItemAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(adminService.getOneCustomer(customer.getId()));
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customer.setFirstName("AdminChanged");
		try {
			adminService.updateCustomer(customer);
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Updated customer:");
		try {
			System.out.println(adminService.getOneCustomer(customer.getId()));
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Coupon
		System.out.println("Get all tables by admin tested in the begining of test.");
		System.out.println();
		System.out.println("---------- End Testing AdminService ----------");
	}

	public void testCompanyService() {
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("---------- Testing CompanyService ----------");
		System.out.println();
		Company company = new Company("CompanyTest", "CompanyTest", "CompanyTest", null);
		try {
			admin.addCompany(company);
		} catch (ItemAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CompanyService companyService = null;
		try {
			companyService = (CompanyService) loginManager.login(company.getEmail(), company.getPassword(),
					ClientType.Company);
		} catch (LoginFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Logged in: ");
		System.out.println(companyService.getCompanyDetails());
		Coupon coupon1 = new Coupon(company.getId(), Category.Food, "CompanyTest1", "CompanyTest1",
				Utils.convertUtilDateToSQL(new Date(2020, 01, 01)), Utils.convertUtilDateToSQL(new Date(2020, 01, 01)),
				50, 50, "CompanyTest1");
		Coupon coupon2 = new Coupon(company.getId(), Category.Food, "CompanyTest2", "CompanyTest2",
				Utils.convertUtilDateToSQL(new Date(2020, 01, 01)), Utils.convertUtilDateToSQL(new Date(2020, 01, 01)),
				70, 70, "CompanyTest2");
		Coupon coupon3 = new Coupon(company.getId(), Category.Electricity, "CompanyTest3", "CompanyTest3",
				Utils.convertUtilDateToSQL(new Date(2020, 01, 01)), Utils.convertUtilDateToSQL(new Date(2020, 01, 01)),
				100, 100, "CompanyTest3");
		try {
			companyService.addCoupon(coupon1);
		} catch (ItemAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			companyService.addCoupon(coupon2);
		} catch (ItemAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			companyService.addCoupon(coupon3);
		} catch (ItemAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Company " + company.getId() + " coupons added: ");
		Utils.printCouponsTable(companyService.getAllCoupons());
		System.out.println("Coupons under 90$: ");
		Utils.printCouponsTable(companyService.getAllCouponsUnderPrice(90));
		System.out.println("Coupons from category food: ");
		Utils.printCouponsTable(companyService.getAllCouponsByCategory(Category.Food));
		System.out.println("Updated coupon1 description+ deleted coupon2  ");
		coupon1.setDescription("updatedCompanyTest");
		try {
			companyService.updateCoupon(coupon1);
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			companyService.deleteCoupon(coupon2);
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Updated company coupons: ");
		Utils.printCouponsTable(companyService.getAllCoupons());
		System.out.println();
		System.out.println("---------- End Testing CompanyService ----------");

	}

	public void testCustomerService() {
		System.out.println("\n");
		System.out.println("\n");

		System.out.println("---------- Testing CustomerService ----------");
		System.out.println();

		// Adding company and coupons for customer test
		System.out.println("Adding company and coupons for customer test.");
		Company company = new Company("CustomerTest", "CustomerTest", "CustomerTest", null);
		try {
			admin.addCompany(company);
		} catch (ItemAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CompanyService companyService = null;
		try {
			companyService = (CompanyService) loginManager.login(company.getEmail(), company.getPassword(),
					ClientType.Company);
		} catch (LoginFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Logged in company: ");
		System.out.println(companyService.getCompanyDetails());
		Coupon coupon1 = new Coupon(company.getId(), Category.Food, "CustomerTest1", "CustomerTest1",
				Utils.convertUtilDateToSQL(new Date(2020, 01, 01)), Utils.convertUtilDateToSQL(new Date(2020, 01, 01)),
				50, 50, "CustomerTest1");
		Coupon coupon2 = new Coupon(company.getId(), Category.Food, "CustomerTest2", "CustomerTest2",
				Utils.convertUtilDateToSQL(new Date(2020, 01, 01)), Utils.convertUtilDateToSQL(new Date(2020, 10, 10)),
				10, 10, "CustomerTest2");
		Coupon coupon3 = new Coupon(company.getId(), Category.Electricity, "CustomerTest3", "CustomerTest3",
				Utils.convertUtilDateToSQL(new Date(2020, 10, 10)), Utils.convertUtilDateToSQL(new Date(2020, 10, 10)),
				50, 50, "CustomerTest3");
		Coupon coupon4 = new Coupon(company.getId(), Category.Food, "CustomerTest4", "CustomerTest4",
				Utils.convertUtilDateToSQL(new Date(2020, 10, 10)), Utils.convertUtilDateToSQL(new Date(2020, 10, 10)),
				70, 70, "CustomerTest4");
		Coupon coupon5 = new Coupon(company.getId(), Category.Sport, "CustomerTest5", "CustomerTest5",
				Utils.convertUtilDateToSQL(new Date(2020, 10, 10)), Utils.convertUtilDateToSQL(new Date(2020, 10, 10)),
				95, 95, "CustomerTest5");

		try {
			companyService.addCoupon(coupon1);
		} catch (ItemAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		coupon1.setId(admin.getCouponIDByTitle(coupon1).getId());
		try {
			companyService.addCoupon(coupon2);
		} catch (ItemAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		coupon2.setId(admin.getCouponIDByTitle(coupon2).getId());
		try {
			companyService.addCoupon(coupon3);
		} catch (ItemAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		coupon3.setId(admin.getCouponIDByTitle(coupon3).getId());
		try {
			companyService.addCoupon(coupon4);
		} catch (ItemAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		coupon4.setId(admin.getCouponIDByTitle(coupon4).getId());
		try {
			companyService.addCoupon(coupon5);
		} catch (ItemAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		coupon5.setId(admin.getCouponIDByTitle(coupon5).getId());
		System.out.println("Company " + company.getId() + " coupons added: ");
		Utils.printCouponsTable(companyService.getAllCoupons());

		Customer customer = new Customer("CustomerTest", "CustomerTest", "CustomerTest", "CustomerTest", null);
		try {
			admin.addCustomer(customer);
		} catch (ItemAlreadyExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CustomerService customerService = null;
		try {
			customerService = (CustomerService) loginManager.login(customer.getEmail(), customer.getPassword(),
					ClientType.Customer);
		} catch (LoginFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("Logged in Customer: ");
		System.out.println(customerService.getCustomerDetails());
		System.out.println("Buy coupons: ");
		try {
			customerService.purchaseCoupon(coupon1);
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			customerService.purchaseCoupon(coupon2);
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			customerService.purchaseCoupon(coupon3);
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			customerService.purchaseCoupon(coupon4);
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			customerService.purchaseCoupon(coupon5);
		} catch (IDDoesntExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAction e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("All customer coupons: ");
		Utils.printCouponsTable(customerService.getAllCoupons());
		System.out.println("All coupons from category food: ");
		Utils.printCouponsTable(customerService.getAllCouponsByCategory(Category.Food));
		System.out.println("All coupons under 90$: ");
		Utils.printCouponsTable(customerService.getAllCouponsUnderPrice(90));

		System.out.println();
		System.out.println("---------- End Testing CustomerService ----------");
	}

	public void testCleaner() throws InterruptedException {
		System.out.println("\n");
		System.out.println("\n");
		System.out.println("---------- Testing Cleaner ----------");
		System.out.println();

		System.out.println("Coupons before: ");
		Utils.printCouponsTable(admin.getAllCoupons());

		cleaner.deleteExpiere();

		System.out.println();
		System.out.println("Coupons after: ");
		Utils.printCouponsTable(admin.getAllCoupons());

		System.out.println();
		System.out.println("---------- End Testing Cleaner ----------");
	}

	@Override
	public void run(String... args) throws Exception {
		printTables("Data in DB - START");

		testAdminService();
		testCompanyService();
		testCustomerService();
		testCleaner();

		printTables("Data in DB - End");

	}

}
