package com.jb.CouponsProjectPart2.tests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jb.CouponsProjectPart2.beans.Category;
import com.jb.CouponsProjectPart2.beans.Company;
import com.jb.CouponsProjectPart2.beans.Coupon;
import com.jb.CouponsProjectPart2.beans.Customer;
import com.jb.CouponsProjectPart2.exc.IDDoesntExistException;
import com.jb.CouponsProjectPart2.exc.InvalidAction;
import com.jb.CouponsProjectPart2.exc.LoginFailed;
import com.jb.CouponsProjectPart2.repo.CompanyRepository;
import com.jb.CouponsProjectPart2.repo.CouponsExpirationDaily;
import com.jb.CouponsProjectPart2.rest.LoginManager;
import com.jb.CouponsProjectPart2.exc.ItemAlreadyExist;
import com.jb.CouponsProjectPart2.service.AdminService;
import com.jb.CouponsProjectPart2.service.ClientType;
import com.jb.CouponsProjectPart2.service.CompanyService;
import com.jb.CouponsProjectPart2.service.CustomerService;
import com.jb.CouponsProjectPart2.utils.Utils;

@Component
@Order(value = 3)
public class TestWeb implements CommandLineRunner {

	private static final String Base_URL = "http://localhost:8080";
	@Autowired
	private RestTemplate restTemplate;

	public void testWeb() {
		System.out.println("\n\n\n\n");
		System.out.println("---------- Testing Web ----------");
		System.out.println();

		System.out.println(restTemplate.getForObject(Base_URL + "/admin/getAllCoupons", List.class));
		System.out.println(restTemplate.getForObject(Base_URL + "/admin/getAllCompanies", List.class));
		System.out.println(restTemplate.getForObject(Base_URL + "/admin/getAllCustomers", List.class));

		
		System.out.println("---------- End Testing Web ----------");
	}

	@Override
	public void run(String... args) throws Exception {

		testWeb();

	}

}
