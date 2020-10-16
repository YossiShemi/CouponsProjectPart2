package com.jb.CouponsProjectPart2.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jb.CouponsProjectPart2.beans.Category;
import com.jb.CouponsProjectPart2.beans.Company;
import com.jb.CouponsProjectPart2.beans.Coupon;
import com.jb.CouponsProjectPart2.beans.Customer;
import com.jb.CouponsProjectPart2.exc.IDDoesntExistException;
import com.jb.CouponsProjectPart2.exc.ItemAlreadyExist;
import com.jb.CouponsProjectPart2.exc.LoginFailed;
import com.jb.CouponsProjectPart2.service.AdminService;
import com.jb.CouponsProjectPart2.service.ClientType;
import com.jb.CouponsProjectPart2.service.CompanyService;

@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping("getCompanyDetails")
	public ResponseEntity<?> getCompanyDetails() {
		return new ResponseEntity<Company>(companyService.getCompanyDetails(), HttpStatus.OK);
	}

	@PostMapping("addCoupon")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon) throws ItemAlreadyExist {
		companyService.addCoupon(coupon);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@PutMapping("updateCoupon")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon) throws ItemAlreadyExist, IDDoesntExistException {
		companyService.updateCoupon(coupon);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("deleteCoupon")
	public ResponseEntity<?> deleteCoupon(@RequestBody Coupon coupon) throws IDDoesntExistException {
		companyService.deleteCoupon(coupon);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@GetMapping("getAllCoupons")
	public ResponseEntity<?> getAllCoupons() {
		return new ResponseEntity<List<Coupon>>(companyService.getAllCoupons(), HttpStatus.OK);
	}

	@GetMapping("getAllCouponsByCategory/{category}")
	public ResponseEntity<?> getAllCouponsByCategory(@PathVariable Category category) {
		return new ResponseEntity<List<Coupon>>(companyService.getAllCouponsByCategory(category), HttpStatus.OK);
	}

	@GetMapping("getAllCouponsUnderPrice/{price}")
	public ResponseEntity<?> getAllCouponsUnderPrice(@PathVariable double price) {
		return new ResponseEntity<List<Coupon>>(companyService.getAllCouponsUnderPrice(price), HttpStatus.OK);
	}

}
