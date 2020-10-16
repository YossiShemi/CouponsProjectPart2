package com.jb.CouponsProjectPart2.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jb.CouponsProjectPart2.beans.Category;
import com.jb.CouponsProjectPart2.beans.Coupon;
import com.jb.CouponsProjectPart2.beans.Customer;
import com.jb.CouponsProjectPart2.exc.IDDoesntExistException;
import com.jb.CouponsProjectPart2.exc.InvalidAction;
import com.jb.CouponsProjectPart2.exc.LoginFailed;

@Service // Equals to facade
@Scope("prototype")
public class CustomerService extends ClientService {

	private int customerID=1;// Delete after testing web

	@Override
	public boolean login(String email, String password) throws LoginFailed {
		if (customerRepository.findByEmailAndPassword(email, password) == null)
			throw new LoginFailed();
		this.customerID = customerRepository.findByEmailAndPassword(email, password).getId();
		return true;
	}

	public Customer getCustomerDetails() {

		return customerRepository.findById(customerID);
	}

	public void purchaseCoupon(Coupon coupon) throws IDDoesntExistException, InvalidAction {
		if (couponRepository.findById(coupon.getId()) == null)
			throw new IDDoesntExistException();

		Customer customer = customerRepository.findById(customerID);

		 // Did customer buy this coupon?
		List<Coupon> userCoupons = customer.getCoupons();
		for (Coupon c : userCoupons) {
			if (c.getId() == coupon.getId())
				throw new InvalidAction("Sorry, Cannoy buy the same coupon twice.");
		}
		

		if (couponRepository.findById(coupon.getId()).getAmount() == 0) { // Did coupon still in store?
			throw new InvalidAction("Sorry, this coupon is no longer avaliable.");

		} else if (couponRepository.findById(coupon.getId()).getEndDate()
				.compareTo(java.sql.Date.valueOf(LocalDate.now())) <= 0) {
			throw new InvalidAction("Sorry, cannot purchase. This coupon expiere today.");

		} else {
			customer.addCoupon(coupon);
			customerRepository.saveAndFlush(customer);
			System.out.println("Purchased coupon successfuly");

		}

	}

	public List<Coupon> getAllCoupons() {

		return customerRepository.findById(customerID).getCoupons();
	}

	public List<Coupon> getAllCouponsByCategory(Category category) {
		List<Coupon> coupons = customerRepository.findById(customerID).getCoupons();
		List<Coupon> filter = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getCategory() == category)
				filter.add(coupon);
		}
		return filter;

	}

	public List<Coupon> getAllCouponsUnderPrice(double price) {
		List<Coupon> coupons = customerRepository.findById(customerID).getCoupons();
		List<Coupon> filter = new ArrayList<Coupon>();
		for (Coupon coupon : coupons) {
			if (coupon.getPrice() < price)
				filter.add(coupon);
		}
		return filter;
	}

}
