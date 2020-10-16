package com.jb.CouponsProjectPart2.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.jb.CouponsProjectPart2.beans.Company;
import com.jb.CouponsProjectPart2.beans.Coupon;
import com.jb.CouponsProjectPart2.beans.Customer;

public class Utils {

	// Converting java.util.Date to java.sql.Date
	public static Date convertUtilDateToSQL(java.util.Date date) {
		return new java.sql.Date(date.getYear() - 1900, date.getMonth() - 1, date.getDate() + 1);
	}

	public static void printTestLine(String s) {
		System.out.println();
		System.out.println(String.format("####################%s####################", s));
		System.out.println();
	}

	public static void separatorLine() {
		System.out.println("===========================================================================");
	}

	public static void printCompaniesTable(List<Company> companies) {
		System.out.println();
		System.out.printf("%10s %10s %20s %10s %10s", "id", "name", "email", "password", "coupons");
		System.out.println();
		Utils.separatorLine();
		for (int i = 0; i < companies.size(); i++) {
			ArrayList<Integer> numbers = null;
			if (companies.get(i).getCoupons() != null) {
				numbers = new ArrayList<Integer>();
				for (Coupon coupon : companies.get(i).getCoupons()) {
					numbers.add(coupon.getId());
				}
			}
			System.out.printf("%10s %10s %20s %10s %10s", (companies.get(i)).getId(), (companies.get(i)).getName(),
					(companies.get(i)).getEmail(), (companies.get(i)).getPassword(), (numbers));
			System.out.println();
		}
		System.out.println();

	}

	public static void printCustomersTable(List<Customer> customers) {
		System.out.println();
		System.out.printf("%10s %10s %10s %20s %10s %10s", "id", "first", "last", "email", "password", "coupons");
		System.out.println();
		Utils.separatorLine();
		for (int i = 0; i < customers.size(); i++) {
			ArrayList<Integer> numbers = null;
			if (customers.get(i).getCoupons() != null) {
				numbers = new ArrayList<Integer>();
				for (Coupon coupon : customers.get(i).getCoupons()) {
					numbers.add(coupon.getId());
				}
			}
			System.out.printf("%10s %10s %10s %20s %10s %10s", (customers.get(i)).getId(),
					(customers.get(i)).getFirstName(), (customers.get(i)).getLastName(), (customers.get(i)).getEmail(),
					(customers.get(i)).getPassword(), (numbers));
			System.out.println();
		}
	}

	public static void printCouponsTable(List<Coupon> coupons) {
		System.out.println();
		System.out.printf("%3s %10s %15s %15s %17s %23s %23s %7s %7s %10s", "id", "companyID", "category", "title",
				"description", "startDate", "endDate", "amount", "price", "img");
		System.out.println();
		System.out.print("==================================================================");
		Utils.separatorLine();
		for (int i = 0; i < coupons.size(); i++) {
			System.out.printf("%3s %10s %15s %15s %17s %23s %23s %7s %7s %10s", (coupons.get(i)).getId(),
					(coupons.get(i)).getCompanyID(), (coupons.get(i)).getCategory(), (coupons.get(i)).getTitle(),
					(coupons.get(i)).getDescription(), (coupons.get(i)).getStartDate(), (coupons.get(i)).getEndDate(),
					(coupons.get(i)).getAmount(), (coupons.get(i)).getPrice(), (coupons.get(i)).getImage());
			System.out.println();
		}
		System.out.println();
	}

}
