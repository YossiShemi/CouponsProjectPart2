package com.jb.CouponsProjectPart2.tests;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jb.CouponsProjectPart2.beans.Category;
import com.jb.CouponsProjectPart2.beans.Company;
import com.jb.CouponsProjectPart2.beans.Coupon;
import com.jb.CouponsProjectPart2.beans.Customer;
import com.jb.CouponsProjectPart2.service.AdminService;
import com.jb.CouponsProjectPart2.utils.Utils;

@Component
@Order(value = 1)
public class AddData implements CommandLineRunner {

	@Autowired
	private AdminService admin;

	@Override
	public void run(String... args) throws Exception {

		Customer customer = new Customer("Yossi", "Shemi", "yossi@gmail.com", "111", null);
		Customer customer1 = new Customer("Kobi", "Shshsa", "Kobi@gmail.com", "222", null);
		Customer customer2 = new Customer("Noam", "Marciano", "Noam@gmail.com", "333", null);
		Customer customer3 = new Customer("Eden", "Gal", "Eden@gmail.com", "444", null);

		admin.addCustomer(customer);
		admin.addCustomer(customer1);
		admin.addCustomer(customer2);
		admin.addCustomer(customer3);

		Company company = new Company("Coca-Cola", "Cola@gmail.com", "1111", null);
		Company company1 = new Company("Pepsi", "Pepso@gmail.com", "2222", null);
		Company company2 = new Company("Spring", "Spring@gmail.com", "3333", null);
		Company company3 = new Company("Prigat", "Prigat@gmail.com", "4444", null);

		admin.addCompany(company);
		admin.addCompany(company1);
		admin.addCompany(company2);
		admin.addCompany(company3);

		Coupon coupon = new Coupon(company.getId(), Category.Food, "couponOne", "50% discount",
				Utils.convertUtilDateToSQL(new Date(2020, 04, 04)), Utils.convertUtilDateToSQL(new Date(2020, 04, 04)),
				10, 50, "imageURL");
		company.addCoupon(coupon);
		Coupon coupon1 = new Coupon(company.getId(), Category.Electricity, "couponTwo", "50% discount",
				Utils.convertUtilDateToSQL(new Date(2020, 05, 05)), Utils.convertUtilDateToSQL(new Date(2020, 05, 05)),
				10, 50, "imageURL");
		company.addCoupon(coupon1);
		Coupon coupon2 = new Coupon(company1.getId(), Category.Vacation, "couponThree", "50% discount",
				Utils.convertUtilDateToSQL(new Date(2020, 06, 06)), Utils.convertUtilDateToSQL(new Date(2020, 06, 06)),
				10, 50, "imageURL");
		company1.addCoupon(coupon2);
		Coupon coupon3 = new Coupon(company1.getId(), Category.Sport, "couponFour", "50% discount",
				Utils.convertUtilDateToSQL(new Date(2020, 11, 11)), Utils.convertUtilDateToSQL(new Date(2020, 11, 11)),
				10, 50, "imageURL");
		company1.addCoupon(coupon3);
		Coupon coupon4 = new Coupon(company2.getId(), Category.Food, "couponFive", "1+1",
				Utils.convertUtilDateToSQL(new Date(2020, 11, 11)), Utils.convertUtilDateToSQL(new Date(2020, 11, 11)),
				10, 50, "imageURL");
		company2.addCoupon(coupon4);
		Coupon coupon5 = new Coupon(company2.getId(), Category.Electricity, "couponSix", "1+1",
				Utils.convertUtilDateToSQL(new Date(2020, 10, 10)), Utils.convertUtilDateToSQL(new Date(2020, 10, 10)),
				10, 50, "imageURL");
		company2.addCoupon(coupon5);
		Coupon coupon6 = new Coupon(company3.getId(), Category.Vacation, "couponSeven", "1+1",
				Utils.convertUtilDateToSQL(new Date(2020, 11, 11)), Utils.convertUtilDateToSQL(new Date(2020, 11, 11)),
				10, 50, "imageURL");
		company3.addCoupon(coupon6);
		Coupon coupon7 = new Coupon(company3.getId(), Category.Vacation, "couponEight", "1+1",
				Utils.convertUtilDateToSQL(new Date(2020, 12, 12)), Utils.convertUtilDateToSQL(new Date(2020, 12, 12)),
				10, 50, "imageURL");
		company3.addCoupon(coupon7);

		admin.updateCompany(company);
		admin.updateCompany(company1);
		admin.updateCompany(company2);
		admin.updateCompany(company3);

	}

}
