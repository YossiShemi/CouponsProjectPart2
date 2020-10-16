package com.jb.CouponsProjectPart2.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.JoinFormula;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "customers")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@ManyToMany(cascade = CascadeType.REMOVE)
	private List<Coupon> coupons;

	public Customer(String firstName, String lastName, String email, String password, ArrayList<Coupon> coupons) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
		this.coupons = new ArrayList<>();

	}

	public void addCoupon(Coupon coupon) {
		coupons.add(coupon);
	}

	public void deleteCoupon(Coupon coupon) {
		for (int i = 0; i < coupons.size(); i++) {
			if (coupons.get(i).getId() == coupon.getId()) {
				coupons.remove(i);
				return;
			}
		}
	}

	public void deleteExpieredCoupons() {
		List<Coupon> validCoupons = new ArrayList<Coupon>();
		for (int i = 0; i < coupons.size(); i++) {
			if (coupons.get(i).getEndDate().before(new Date()))
				System.out
						.println("Coupon with id " + coupons.get(i).getId() + " was deleted from customer " + this.id);
			else
				validCoupons.add(coupons.get(i));
		}
		this.coupons = validCoupons;

	}

	public void deletCompanyCoupons(Company company) {
		List<Coupon> validCoupons = new ArrayList<Coupon>();
		for (int i = 0; i < coupons.size(); i++) {
			if (coupons.get(i).getCompanyID() == company.getId())
				System.out
						.println("Coupon with id " + coupons.get(i).getId() + " was deleted from customer " + this.id);
			else
				validCoupons.add(coupons.get(i));
		}
		this.coupons = validCoupons;

	}

}
