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

import com.jb.CouponsProjectPart2.beans.Company;
import com.jb.CouponsProjectPart2.beans.Coupon;
import com.jb.CouponsProjectPart2.beans.Customer;
import com.jb.CouponsProjectPart2.exc.IDDoesntExistException;
import com.jb.CouponsProjectPart2.exc.InvalidAction;
import com.jb.CouponsProjectPart2.exc.ItemAlreadyExist;
import com.jb.CouponsProjectPart2.exc.LoginFailed;
import com.jb.CouponsProjectPart2.service.AdminService;
import com.jb.CouponsProjectPart2.service.ClientType;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private AdminService admin;

	@PostMapping("addCompany")
	public ResponseEntity<?> addCompany(@RequestBody Company company) throws ItemAlreadyExist {
		admin.addCompany(company);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@PutMapping("updateCompany")
	public ResponseEntity<?> updateCompany(@RequestBody Company company) throws IDDoesntExistException, InvalidAction {
		admin.updateCompany(company);
		return new ResponseEntity(HttpStatus.NO_CONTENT);

	}

	@DeleteMapping("deleteCompany")
	public ResponseEntity<?> deleteCompany(@RequestBody Company company) throws IDDoesntExistException {
		admin.deleteCompany(company);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@GetMapping("getAllCompanies")
	public ResponseEntity<?> getAllCompanies() {
		return new ResponseEntity<List<Company>>(admin.getAllCompanies(), HttpStatus.OK);
	}

	@GetMapping("getOneCompany/{id}")
	public ResponseEntity<?> getOneCompany(@PathVariable int id) throws IDDoesntExistException {
		return new ResponseEntity<Company>(admin.getOneCompany(id), HttpStatus.OK);
	}
//	@GetMapping("getOneCompany")
//	public ResponseEntity<?> getOneCompany(@RequestParam int id) throws IDDoesntExistException {
//		return new ResponseEntity<Company>(admin.getOneCompany(id), HttpStatus.OK);
//	}

	@PostMapping("addCustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer cusotmer) throws ItemAlreadyExist {
		admin.addCustomer(cusotmer);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@PutMapping("updateCustomer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer cusotmer) throws IDDoesntExistException {
		admin.updateCustomer(cusotmer);
		return new ResponseEntity(HttpStatus.NO_CONTENT);

	}

	@DeleteMapping("deleteCustomer")
	public ResponseEntity<?> deleteCustomer(@RequestBody Customer cusotmer) throws IDDoesntExistException {
		admin.deleteCustomer(cusotmer);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@GetMapping("getAllCustomers")
	public ResponseEntity<?> getAllCustomers() {
		return new ResponseEntity<List<Customer>>(admin.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping("getOneCustomer/{id}")
	public ResponseEntity<?> getOneCustomer(@PathVariable int id) throws IDDoesntExistException {
		return new ResponseEntity<Customer>(admin.getOneCustomer(id), HttpStatus.OK);
	}
//	@GetMapping("getOneCustomer")
//	public ResponseEntity<?> getOneCustomer(@RequestParam int id) throws IDDoesntExistException {
//		return new ResponseEntity<Customer>(admin.getOneCustomer(id), HttpStatus.OK);
//	}

	@GetMapping("getAllCoupons")
	public ResponseEntity<?> getAllCoupons() {
		return new ResponseEntity<List<Coupon>>(admin.getAllCoupons(), HttpStatus.OK);
	}

}
