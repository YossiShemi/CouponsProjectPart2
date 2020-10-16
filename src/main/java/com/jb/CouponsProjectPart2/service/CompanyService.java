package com.jb.CouponsProjectPart2.service;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jb.CouponsProjectPart2.beans.Category;
import com.jb.CouponsProjectPart2.beans.Company;
import com.jb.CouponsProjectPart2.beans.Coupon;
import com.jb.CouponsProjectPart2.exc.IDDoesntExistException;
import com.jb.CouponsProjectPart2.exc.ItemAlreadyExist;
import com.jb.CouponsProjectPart2.exc.LoginFailed;

@Service // Equals to facade
@Scope("prototype")
public class CompanyService extends ClientService {

	private int companyID=1; // Delete after testing web

	@Override
	public boolean login(String email, String password) throws LoginFailed {
		if (companyRepository.findByEmailAndPassword(email, password) == null)
			throw new LoginFailed();
		this.companyID = companyRepository.findByEmailAndPassword(email, password).getId();
		return true;
	}

	public Company getCompanyDetails() {
		return companyRepository.findById(companyID);
	}

	public void addCoupon(Coupon coupon) throws ItemAlreadyExist {
		if (couponRepository.findByTitle(coupon.getTitle()) != null) {
			throw new ItemAlreadyExist("The coupon " + coupon.getTitle() + " already exist");
		}
		coupon.setCompanyID(companyID);
		Company company = companyRepository.findById(this.companyID);
		company.addCoupon(coupon);
		companyRepository.saveAndFlush(company);
	}

	public void updateCoupon(Coupon coupon) throws IDDoesntExistException {
		coupon.setId(couponRepository.findByTitle(coupon.getTitle()).getId());
		if (couponRepository.findById(coupon.getId()) == null)
			throw new IDDoesntExistException();
		couponRepository.saveAndFlush(coupon);
	}

	public void deleteCoupon(Coupon coupon) throws IDDoesntExistException {
		coupon.setId(couponRepository.findByTitle(coupon.getTitle()).getId());
		if (couponRepository.findById(coupon.getId()) == null)
			throw new IDDoesntExistException();
		Company company = companyRepository.findById(this.companyID);
		company.deleteCoupon(coupon);
		companyRepository.saveAndFlush(company);
	}

	public List<Coupon> getAllCoupons() {

		return couponRepository.findByCompanyID(companyID);
	}

	public List<Coupon> getAllCouponsByCategory(Category category) {
		return couponRepository.findByCategoryAndCompanyID(category, companyID);
	}

	public List<Coupon> getAllCouponsUnderPrice(double price) {
		return couponRepository.findByPriceLessThanAndCompanyID(price, companyID);

	}

}
