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
import com.jb.CouponsProjectPart2.exc.InvalidAction;
import com.jb.CouponsProjectPart2.exc.ItemAlreadyExist;
import com.jb.CouponsProjectPart2.exc.LoginFailed;
import com.jb.CouponsProjectPart2.service.AdminService;
import com.jb.CouponsProjectPart2.service.ClientType;
import com.jb.CouponsProjectPart2.service.CompanyService;
import com.jb.CouponsProjectPart2.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("getCustomerDetails")
	public ResponseEntity<?> getCustomerDetails() {
		return new ResponseEntity<Customer>(customerService.getCustomerDetails(), HttpStatus.OK);
	}

	@PostMapping("purchaseCoupon")
	public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon) throws IDDoesntExistException, InvalidAction {
		customerService.purchaseCoupon(coupon);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@GetMapping("getAllCoupons")
	public ResponseEntity<?> getAllCoupons() {
		return new ResponseEntity<List<Coupon>>(customerService.getAllCoupons(), HttpStatus.OK);
	}

	@GetMapping("getAllCouponsByCategory/{category}")
	public ResponseEntity<?> getAllCouponsByCategory(@PathVariable Category category) {
		return new ResponseEntity<List<Coupon>>(customerService.getAllCouponsByCategory(category), HttpStatus.OK);
	}

	@GetMapping("getAllCouponsUnderPrice/{price}")
	public ResponseEntity<?> getAllCouponsUnderPrice(@PathVariable double price) {
		return new ResponseEntity<List<Coupon>>(customerService.getAllCouponsUnderPrice(price), HttpStatus.OK);
	}

}
