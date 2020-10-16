package com.jb.CouponsProjectPart2.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "companies")
public class Company {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Coupon> coupons;

	public Company(String name, String email, String password, ArrayList<Coupon> coupons) {
		this.name = name;
		this.email = email;
		this.password = password;
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
				System.out.println("Coupon with id " + coupons.get(i).getId() + " was deleted from company " + this.id);
			else
				validCoupons.add(coupons.get(i));
		}
		this.coupons = validCoupons;

	}

}
