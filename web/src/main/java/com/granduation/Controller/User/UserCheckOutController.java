package com.granduation.Controller.User;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.granduation.Controller.BaseController;
import com.granduation.Entity.Bill;
import com.granduation.Entity.BillInfo;
import com.granduation.Entity.Cart;
import com.granduation.Entity.Product;
import com.granduation.Entity.Users;
import com.sun.istack.Nullable;

@Controller
public class UserCheckOutController extends BaseController{

	@GetMapping(value = "/thanh-toan")
	public String CheckOut(Model model, Principal principal) {
		model.addAttribute("provinces", provinceService.findAllProvince());
		model.addAttribute("categories", cateService.findAll());
		Users user = userService.findByUserName(principal.getName());
		List<Cart> listcart = cartService.findByUser(user);
		model.addAttribute("listcart", listcart);
		Double total = 0.0;
		for (Cart cart : listcart) {
			if (cart.getProduct().getPrice_discount() > 0)
				total += cart.getQuantity() * cart.getProduct().getPrice_discount();
			else
				total += cart.getQuantity() * cart.getProduct().getPrice();
		}
		model.addAttribute("tax", taxService.findByType("VAT"));
		model.addAttribute("customer", user);
		model.addAttribute("total", total);
		model.addAttribute("bill", new Bill());
		return "pages/users/checkout";
	}

	@PostMapping(value = "/thanh-toan")
	public String CheckOutPost(RedirectAttributes redirectAttributes,HttpSession session, Model model, Principal principal,@ModelAttribute Bill bill,
			@RequestParam(name = "paypal", required = false) String paypal) {
	
		Users user = userService.findByUserName(principal.getName());
		List<Cart> listcart = cartService.findByUser(user);
		Double total = 0.0;
		BillInfo b;
		for (Cart cart : listcart) {
			if (cart.getProduct().getPrice_discount() > 0) {
				total += cart.getQuantity() * cart.getProduct().getPrice_discount();
			} else {
				total += cart.getQuantity() * cart.getProduct().getPrice();
			}
			Product p = productService.getById(cart.getProduct().getId());
			if(p.getQuantity() - cart.getQuantity() < 0) {
				redirectAttributes.addFlashAttribute("error","Số lượng "+p.getName()+" không đủ!");
				redirectAttributes.addFlashAttribute("message","Thanh toán không thành công!");
				return "redirect:/thanh-toan";
			}
		}
		if (paypal != null) {
			bill.setStatus("Chờ giao");
			bill.setPayment("PAYMENT");
			bill.setUser(user);
			bill.setTotal(total+ total*taxService.findByType("VAT").getFee()/100);
			bill.setTax(taxService.findByType("VAT"));
			session.setAttribute("bill", bill);
			model.addAttribute("listcart", listcart);
			return "pages/users/paypal";
		}
		bill.setStatus("Chờ giao");
		bill.setPayment("COD");
		bill.setUser(user);
		bill.setTotal(total+ total*taxService.findByType("VAT").getFee()/100);
		bill.setTax(taxService.findByType("VAT"));
		billService.save(bill);
		for (Cart cart : listcart) {
			if (cart.getProduct().getPrice_discount() > 0) {
				b = new BillInfo();
				b.setQuantity(cart.getQuantity());
				b.setTotal(cart.getProduct().getPrice_discount()*cart.getQuantity());
				b.setProduct(cart.getProduct());
				b.setBill(billService.getById(billService.getMaxId()));
				billInfoService.save(b);
				Product p = productService.getById(cart.getProduct().getId());
				p.setQuantity(p.getQuantity() - cart.getQuantity());
				productService.update(p);
			} else {
				b = new BillInfo();
				b.setQuantity(cart.getQuantity());
				b.setTotal(cart.getProduct().getPrice()*cart.getQuantity());
				b.setProduct(cart.getProduct());
				b.setBill(billService.getById(billService.getMaxId()));
				billInfoService.save(b);
				Product p = productService.getById(cart.getProduct().getId());
				p.setQuantity(p.getQuantity() - cart.getQuantity());
				productService.update(p);
			}
		}
		try {
			sendEmail(user.getEmail(), billService.getMaxId());
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
		redirectAttributes.addFlashAttribute("message","Thanh toán thành công!");
		return "redirect:/thanh-toan";
	}
	
	public void sendEmail(String recipientEmail, int id)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();              
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setFrom("congdoan27121999@gmail.com");
        helper.setTo(recipientEmail);
        String subject = "Mã theo dõi đơn hàng!";
        String content = "Mã theo dõi đơn hàng của bạn là,<br>" + "<h3>"+ id +"</h3>" + "<3,<br>"
				+ "Trịnh Công Đoàn";
         
        helper.setSubject(subject);
        helper.setText(content, true);
         
        mailSender.send(message);
    }
}
