package com.granduation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import com.granduation.Config.Paypal.PaypalService;
import com.granduation.Service.IAuthorizationService;
import com.granduation.Service.IBillInfoService;
import com.granduation.Service.IBillService;
import com.granduation.Service.IBlogService;
import com.granduation.Service.ICartService;
import com.granduation.Service.ICategoryService;
import com.granduation.Service.IDiscountService;
import com.granduation.Service.IFeedbackService;
import com.granduation.Service.IProductService;
import com.granduation.Service.IProvinceService;
import com.granduation.Service.ISupplierService;
import com.granduation.Service.ITaxService;
import com.granduation.Service.IUserService;
import com.granduation.Service.IWardService;

@Controller
public class BaseController {

	@Autowired
	protected JavaMailSender mailSender;
	
	@Autowired
	protected PaypalService paypalService;

	@Autowired
	protected IAuthorizationService authService;

	@Autowired
	protected IBillInfoService billInfoService;

	@Autowired
	protected IBillService billService;

	@Autowired
	protected
	IBlogService blogService;

	@Autowired
	protected ICartService cartService;

	@Autowired
	protected ICategoryService cateService;

	@Autowired
	protected IDiscountService discountService;

	@Autowired
	protected IDiscountService districtService;

	@Autowired
	protected IProductService productService;

	@Autowired
	protected ISupplierService supplierService;

	@Autowired
	protected ITaxService taxService;

	@Autowired
	protected IWardService wardService;

	@Autowired
	protected IUserService userService;

	@Autowired
	protected IProvinceService provinceService;
	
	@Autowired
	protected IFeedbackService feedbackService;
	
	@Autowired
	protected BCryptPasswordEncoder passencoder;
}
