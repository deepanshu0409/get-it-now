package com.getitnow.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.getitnow.model.Admin;
import com.getitnow.model.Card;
import com.getitnow.model.Cart;
import com.getitnow.model.Customer;
import com.getitnow.model.Email_password;
import com.getitnow.model.Feedback;
import com.getitnow.model.MapCardCustomer;
import com.getitnow.model.Map_Product_Orders;
import com.getitnow.model.Order;
import com.getitnow.model.Product;
import com.getitnow.model.SecurityQuestionAnswer;
import com.getitnow.model.Store;
import com.getitnow.service.AdminService;
import com.getitnow.service.CartService;
import com.getitnow.service.CustomerService;
import com.getitnow.service.OrderService;
import com.getitnow.service.ProductService;
import com.getitnow.service.SignUpService;
import com.getitnow.service.StoreService;
import com.getitnow.utility.ContextFactory;

@RestController
@CrossOrigin
@RequestMapping(value="api")
public class GetItNowAPI {
	
	@RequestMapping(value = "message")
	public String getMessage() {
		return "Hello And Welcome To GetItNow";
	}
	
	@RequestMapping(value = "adminLogIn",method=RequestMethod.POST)
	public ResponseEntity<Admin> adminLogIn(@RequestBody Admin admin){
		
		AdminService adminService = (AdminService) ContextFactory.getContext().getBean(AdminService.class);
		Environment environment = ContextFactory.getContext().getEnvironment();
		Admin adminReturned = null;
		
		try {
			adminReturned = adminService.logIn(admin);
			if(adminReturned != null){
				adminReturned.setMessage(environment.getProperty(adminReturned.getMessage()));
				return new ResponseEntity<Admin>(adminReturned, HttpStatus.OK);
			} else {
				adminReturned = new Admin();
				
				adminReturned.setMessage(environment.getProperty("ADMIN_API.INVALID_USERNAME"));
				return new ResponseEntity<Admin>(adminReturned, HttpStatus.BAD_REQUEST);
			}
		} catch(Exception e){
			String errorMessage = environment.getProperty("API.REGISTRATION_ERROR_MESSAGE")+e.getMessage();
			adminReturned = new Admin();
			
			adminReturned.setMessage(errorMessage);
			return new ResponseEntity<Admin>(adminReturned, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "updateCredentials",method=RequestMethod.POST)
	public ResponseEntity<Admin> updateAdminCredentials(@RequestBody Admin admin){
		AdminService adminService = (AdminService) ContextFactory.getContext().getBean(AdminService.class);
		Admin adminReturned = null;
		
		try {
			adminReturned = adminService.updateAdminCredentials(admin);
			if(adminReturned != null){
				return new ResponseEntity<Admin>(adminReturned, HttpStatus.OK);
			} else {
				adminReturned = new Admin();
				adminReturned.setMessage("Could not update Credentials.please try again with valid username");
				return new ResponseEntity<Admin>(adminReturned, HttpStatus.BAD_REQUEST);
			}
		} catch(Exception e){
			adminReturned = new Admin();
			adminReturned.setMessage("Some error occured while updating credentials");
			return new ResponseEntity<Admin>(adminReturned, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "signUp", method=RequestMethod.POST)
	public ResponseEntity<Customer> addNewCustomer(@RequestBody Customer customer) {
		SignUpService signUpService = (SignUpService) ContextFactory.getContext().getBean(SignUpService.class);
		Environment environment = ContextFactory.getContext().getEnvironment();
		
		try {
			Customer returnCustomer = null;
			returnCustomer = signUpService.addNewCustomer(customer);
			
			String successMessage = environment.getProperty("API.SUCCESS_MESSAGE")+returnCustomer.getCustomerId();
			returnCustomer.setMessage(successMessage);
			
			return new ResponseEntity<Customer>(returnCustomer, HttpStatus.OK);
			
		} catch(Exception e){
			String errorMessage = environment.getProperty("API.REGISTRATION_ERROR_MESSAGE")+e.getMessage();
			Customer registeredCustomer = new Customer();
			registeredCustomer.setMessage(errorMessage);
			
			return new ResponseEntity<Customer>(registeredCustomer, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "LogIn", method=RequestMethod.POST)
	public ResponseEntity<Customer> CustomerLogIn(@RequestBody Customer customer) {
		CustomerService logInService = (CustomerService) ContextFactory.getContext().getBean(CustomerService.class);
		Environment environment = ContextFactory.getContext().getEnvironment();
		
		try {
			Customer registeredCustomer = null;
			registeredCustomer = logInService.getCustomerDetails(customer.getUsername(),customer.getPassword());
			
			String successMessage = environment.getProperty("ADMIN_DAO.LOGIN_SUCCESS");
			registeredCustomer.setMessage(successMessage);
			
			return new ResponseEntity<Customer>(registeredCustomer, HttpStatus.OK);
			
		} catch(Exception e){
			String errorMessage = environment.getProperty("API.ERROR_LOGIN");
			Customer registeredCustomer = new Customer();
			registeredCustomer.setMessage(errorMessage);
			
			return new ResponseEntity<Customer>(registeredCustomer, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="listOfCustomers",method= RequestMethod.GET)
	public ResponseEntity<List<Customer>> listOfCustomers(){
		
		AdminService adminService = (AdminService) ContextFactory.getContext().getBean(AdminService.class);
		List<Customer> customerList = null;
		try{
			customerList = adminService.listOfCustomers();
			return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<List<Customer>>(customerList, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "updateCustomerDetails", method=RequestMethod.POST)
	public ResponseEntity<Customer> updateCustomerDetails(@RequestBody Customer customer) {
		CustomerService updateService = (CustomerService) ContextFactory.getContext().getBean(CustomerService.class);
		Customer customerReturned = null; 
		try{
			customerReturned = updateService.updateCustomerDetails(customer);
			return new ResponseEntity<Customer>(customerReturned,HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<Customer>(customerReturned,HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="deleteCustomer/{customerId}",method= RequestMethod.GET)
	public ResponseEntity<List<Customer>> deleteCustomer(@PathVariable Integer customerId){
		
		AdminService adminService = (AdminService) ContextFactory.getContext().getBean(AdminService.class);
		List<Customer> updatedCustomerList = null;
		try{
			updatedCustomerList = adminService.deleteCustomer(customerId);
			return new ResponseEntity<List<Customer>>(updatedCustomerList, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<List<Customer>>(updatedCustomerList, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "addNewProduct",method = RequestMethod.POST)
	public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
		ProductService productService = (ProductService)  ContextFactory.getContext().getBean(ProductService.class);
		Product productReturned = null;
		try {
			productReturned = productService.addNewProduct(product);
			
			return new ResponseEntity<Product>(productReturned, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Product>(productReturned,HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "products/{productId}",method = RequestMethod.GET)
	public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
		ProductService productService = (ProductService)  ContextFactory.getContext().getBean(ProductService.class);
		
		/*Environment environment = ContextFactory.getContext().getEnvironment();*/
		
		Product product = null;
		
		try {
			product = productService.getProductById(productId);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Product>(product,HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "products",method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAllProducts(){
		ProductService productService = (ProductService)  ContextFactory.getContext().getBean(ProductService.class);
		
		try {
			List<Product> productsList = productService.getAllProducts();
			
			return new ResponseEntity<List<Product>>(productsList, HttpStatus.OK);
		} catch (Exception e){
			List<Product> productsList = null;
			return new ResponseEntity<List<Product>>(productsList, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "updateProductDetails",method = RequestMethod.POST)
	public ResponseEntity<List<Product>> updateProductDetails(@RequestBody Product product){
		ProductService productService = (ProductService) ContextFactory.getContext().getBean(ProductService.class);
		
		try {
			List<Product> productsList = productService.updateProductDetails(product);
			
			return new ResponseEntity<List<Product>>(productsList, HttpStatus.OK);
		} catch (Exception e){
			List<Product> productsList = new ArrayList<>();
			return new ResponseEntity<List<Product>>(productsList, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "addCard", method=RequestMethod.POST)
	public ResponseEntity<String> addCard(@RequestBody MapCardCustomer mapcard ){
		CustomerService customerService = (CustomerService) ContextFactory.getContext().getBean(CustomerService.class);
		Environment environment = ContextFactory.getContext().getEnvironment();
		String message=null;
		
		System.out.println(mapcard.getCustomerId()+" "+mapcard.getCard());
		
		try{
			message=customerService.addCardtoCustomer(mapcard.getCustomerId(), mapcard.getCard());
			message=environment.getProperty(message);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}
		catch(Exception e){
			message=e.getMessage();
			return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "removeCard",method = RequestMethod.POST)
	public ResponseEntity<List<Card>> removeCard(@RequestBody Customer customer){
		System.out.println(customer.getCard().get(0).getCardNumber());
		CustomerService customerService = (CustomerService) ContextFactory.getContext().getBean(CustomerService.class);
		
		List<Card> newCards = null;
		
		try{
			newCards = customerService.removeCard(customer);
			return new ResponseEntity<List<Card>>(newCards, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<List<Card>>(newCards, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="getAllCards/{customerId}",method= RequestMethod.GET)
	public ResponseEntity<List<Card>> getAllCards(@PathVariable Integer customerId){
		CustomerService customerService = (CustomerService) ContextFactory.getContext().getBean(CustomerService.class);
		
		List<Card> newCards = null;
		
		try{
			newCards = customerService.getAllCards(customerId);
			return new ResponseEntity<List<Card>>(newCards, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<List<Card>>(newCards, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "addToCart", method=RequestMethod.POST)
	public ResponseEntity<String> addToCart(@RequestBody Cart cart)
	{
		CartService cartService=(CartService) ContextFactory.getContext().getBean(CartService.class);
		String message=null;
		try{
			message=cartService.addToCart(cart);
			return new ResponseEntity<String>(message, HttpStatus.OK); 
		}
		catch(Exception e){
			message=e.getMessage();
			return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "getProductsFromCart", method=RequestMethod.POST)
	public ResponseEntity<List<Cart>> getProductsFromCart(@RequestBody Customer customer)
	{
		CartService cartService=(CartService) ContextFactory.getContext().getBean(CartService.class);
		List<Cart> cartList = null;
		try {
			cartList = cartService.getProductsFromCart(customer.getCustomerId());
			return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK); 
		}
		catch(Exception e){
			return new ResponseEntity<List<Cart>>(cartList, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "getProductFromCart", method=RequestMethod.POST)
	public ResponseEntity<Cart> getProductFromCart(@RequestBody Cart cart)
	{
		CartService cartService=(CartService) ContextFactory.getContext().getBean(CartService.class);
		Cart cartReturned = null;
		try {
			cartReturned = cartService.getProductFromCart(cart);
			return new ResponseEntity<Cart>(cartReturned, HttpStatus.OK); 
		}
		catch(Exception e){
			return new ResponseEntity<Cart>(cartReturned, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "deleteProductFromCart", method=RequestMethod.POST)
	public ResponseEntity<String> deleteProductFromCart(@RequestBody Cart cart) {
		CartService cartService=(CartService) ContextFactory.getContext().getBean(CartService.class);
		String message = null;
		
		try {
			message = cartService.deleteProductFromCart(cart);
			return new ResponseEntity<String>(message, HttpStatus.OK); 
		}
		catch(Exception e){
			return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="getProductsFromList/{productList}",method=RequestMethod.GET)
	public ResponseEntity<List<Product>>  getProductsFromList(@PathVariable List<Integer> productList){
		System.out.println("here");
		ProductService productService=(ProductService) ContextFactory.getContext().getBean(ProductService.class);
		List<Product> productListRet=null;
		try {
			productListRet = productService.getProductsFromList(productList);
			return new ResponseEntity<List<Product>>(productListRet, HttpStatus.OK); 
		}
		catch(Exception e){
			return new ResponseEntity<List<Product>>(productListRet, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value="updateQuantity",method=RequestMethod.POST)
    public ResponseEntity<String> updateQuantity(@RequestBody Cart cart){
		CartService cartService=(CartService) ContextFactory.getContext().getBean(CartService.class);
		String message=null;
		try {
			message = cartService.updateQuantity(cart);
			return new ResponseEntity<String>(message, HttpStatus.OK); 
		}
		catch(Exception e){
			return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="getProductsSummary/{customerId}",method= RequestMethod.GET)
	public ResponseEntity<List<Order>> getProductsSummary(@PathVariable Integer customerId){
		OrderService orderService = (OrderService) ContextFactory.getContext().getBean(OrderService.class);
		
		List<Order> orderSummary = null;
		
		try{
			orderSummary = orderService.getProductsSummary(customerId);
			return new ResponseEntity<List<Order>>(orderSummary, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<List<Order>>(orderSummary, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="getListOfOrderIds/{customerId}",method= RequestMethod.GET)
	public ResponseEntity<List<String>> getListOfOrderIds(@PathVariable Integer customerId){
		OrderService orderService = (OrderService) ContextFactory.getContext().getBean(OrderService.class);
		
		List<String> orderSummary = null;
		
		try{
			orderSummary = orderService.getListOfOrderIds(customerId);
			return new ResponseEntity<List<String>>(orderSummary, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<List<String>>(orderSummary, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="getAllOrders/{customerId}",method= RequestMethod.GET)
	public ResponseEntity<List<Order>> getAllOrders(@PathVariable Integer customerId){
		OrderService orderService = (OrderService) ContextFactory.getContext().getBean(OrderService.class);
		
		List<Order> orderSummary = null;
		
		try{
			orderSummary = orderService.getAllOrders(customerId);
			return new ResponseEntity<List<Order>>(orderSummary, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<List<Order>>(orderSummary, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="getListOfProductByOrderId/{orderId}",method= RequestMethod.GET)
	public ResponseEntity<Map_Product_Orders> getListOfProductByOrderId(@PathVariable String orderId){
		OrderService orderService = (OrderService) ContextFactory.getContext().getBean(OrderService.class);
		
		Map_Product_Orders orderSummary = null;
		
		try{
			orderSummary = orderService.getListOfProductByOrderId(orderId);
			return new ResponseEntity<Map_Product_Orders>(orderSummary, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<Map_Product_Orders>(orderSummary, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="addFeedback",method= RequestMethod.POST)
	public ResponseEntity<String> addFeedback(@RequestBody Feedback feedback){
		System.out.println(feedback.getCustomerName());
		AdminService adminService = (AdminService) ContextFactory.getContext().getBean(AdminService.class);
		String message = null;
		try{
			message = adminService.addfeedback(feedback);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="getFeedbacks",method= RequestMethod.GET)
	public ResponseEntity<List<Feedback>> getFeedbacks(){
		AdminService adminService = (AdminService) ContextFactory.getContext().getBean(AdminService.class);
		List<Feedback> feedbacks= null;
		try{
			feedbacks = adminService.getFeedbacks();
			return new ResponseEntity<List<Feedback>>(feedbacks, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<List<Feedback>>(feedbacks, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="addNewStore",method= RequestMethod.POST)
	public ResponseEntity<Store> addNewStore(@RequestBody Store store) {
		StoreService storeService = (StoreService) ContextFactory.getContext().getBean(StoreService.class);
		Store StoreReturned = null;
		
		try{
			StoreReturned = storeService.addNewStore(store);
			return new ResponseEntity<Store>(StoreReturned, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<Store>(StoreReturned, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="addProductsInStore",method= RequestMethod.POST)
	public ResponseEntity<List<Product>> addProductsInStore(@RequestBody Store store){
		StoreService storeService = (StoreService) ContextFactory.getContext().getBean(StoreService.class);
		List<Product> products = null;
		try{
			products = storeService.addProductsInStore(store);
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<List<Product>>(products, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="updateStoreDetails",method= RequestMethod.POST)
	public ResponseEntity<String> updateStoreDetails(@RequestBody Store store){
		StoreService storeService = (StoreService) ContextFactory.getContext().getBean(StoreService.class);
		Environment environment = ContextFactory.getContext().getEnvironment();
		String message = null;
		
		try {
			message = storeService.updateStoreDetails(store);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch(Exception e){
			message = environment.getProperty(e.getMessage());
			return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="removeStore/{storeId}",method= RequestMethod.GET)
	public ResponseEntity<String> removeStore(@PathVariable Integer storeId){
		StoreService storeService = (StoreService) ContextFactory.getContext().getBean(StoreService.class);
		Environment environment = ContextFactory.getContext().getEnvironment();
		String message = null;
		
		try {
			message = storeService.removeStore(storeId);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch(Exception e){
			message = environment.getProperty(e.getMessage());
			return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="getAllStores",method= RequestMethod.GET)
	public ResponseEntity<List<Store>> getAllStores() {
		StoreService storeService = (StoreService) ContextFactory.getContext().getBean(StoreService.class);
		List<Store> StoreReturned = null;
		
		try{
			StoreReturned = storeService.getAllStores();
			return new ResponseEntity<List<Store>>(StoreReturned, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<List<Store>>(StoreReturned, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="getStoreByProduct/{productId}",method= RequestMethod.GET)
	public ResponseEntity<List<Store>> getStoreByProduct(@PathVariable Integer productId) {
		StoreService storeService = (StoreService) ContextFactory.getContext().getBean(StoreService.class);
		List<Store> StoreReturned = null;
		
		try{
			StoreReturned = storeService.getStoreByProduct(productId);
			return new ResponseEntity<List<Store>>(StoreReturned, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<List<Store>>(StoreReturned, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="deleteProductFromStore/{storeId}/{productId}",method= RequestMethod.GET)
	public ResponseEntity<List<Product>> deleteProductFromStore(@PathVariable Integer storeId,@PathVariable Integer productId) {
		StoreService storeService = (StoreService) ContextFactory.getContext().getBean(StoreService.class);
		List<Product> products = null;
		
		try{
			products = storeService.deleteProductFromStore(storeId,productId);
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<List<Product>>(products, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "addSecurityQuestion",method = RequestMethod.POST)
	public ResponseEntity<String> addSecurityQuestion(@RequestBody SecurityQuestionAnswer sqa){
		CustomerService customerService = (CustomerService) ContextFactory.getContext().getBean(CustomerService.class);
		System.out.println(sqa.getAnswer()+" "+sqa.getEmailId());
		String message = null;
		
		try{
			message = customerService.addSecurityQuestion(sqa);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="getSecurityQuestionAnswer",method= RequestMethod.POST)
	public ResponseEntity<SecurityQuestionAnswer> getSecurityQuestionAnswer(@RequestBody SecurityQuestionAnswer sqa) {
		CustomerService customerService = (CustomerService) ContextFactory.getContext().getBean(CustomerService.class);
		SecurityQuestionAnswer securityQuestionAnswer = null;
		
		try{
			securityQuestionAnswer = customerService.getSecurityQuestionAnswer(sqa.getEmailId());
			System.out.println(securityQuestionAnswer.getEmailId()+" "+securityQuestionAnswer.getSecurityQuestion()+" "+securityQuestionAnswer.getAnswer());
			return new ResponseEntity<SecurityQuestionAnswer>(securityQuestionAnswer, HttpStatus.OK);
		} catch(Exception e){
			System.out.println("Hello\n");
			return new ResponseEntity<SecurityQuestionAnswer>(securityQuestionAnswer, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="updateNewPassword",method= RequestMethod.POST)
	public ResponseEntity<Customer> updateNewPassword(@RequestBody Email_password email_password) {
		CustomerService customerService = (CustomerService) ContextFactory.getContext().getBean(CustomerService.class);
		Customer customer = null;
		System.out.println(email_password.getPassword()+ " "+ email_password.getEmailId());
		try{
			customer = customerService.updateNewPassword(email_password);
			//System.out.println(securityQuestionAnswer.getEmailId()+" "+securityQuestionAnswer.getSecurityQuestion()+" "+securityQuestionAnswer.getAnswer());
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<Customer>(customer, HttpStatus.BAD_REQUEST);
		}
	}
}
