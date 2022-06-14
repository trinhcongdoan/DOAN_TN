package com.granduation.Controller.User;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.granduation.Config.Paypal.PaypalPaymentIntent;
import com.granduation.Config.Paypal.PaypalPaymentMethod;
import com.granduation.Config.Paypal.PaypalUtils;
import com.granduation.Controller.BaseController;
import com.granduation.Entity.Bill;
import com.granduation.Entity.BillInfo;
import com.granduation.Entity.Cart;
import com.granduation.Entity.Product;
import com.granduation.Entity.Users;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
public class PaymentController extends BaseController {
	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";
	private Logger log = LoggerFactory.getLogger(getClass());

	@PostMapping("/pay")
	public String pay(HttpServletRequest request, @RequestParam("price") double price) {
		String cancelUrl = PaypalUtils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = PaypalUtils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		try {
			Payment payment = paypalService.createPayment(price, "USD", PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale, "payment description", cancelUrl, successUrl);
			for (Links links : payment.getLinks()) {
				if (links.getRel().equals("approval_url")) {
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
			System.out.println(e.getMessage());
		}
		return "redirect:/thanh-toan";
	}

	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay(Model model,RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message","Thanh toán không thành công!");
		return "redirect:/thanh-toan";
	}

	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay(HttpSession session, Principal principal, Model model,
			@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,RedirectAttributes redirectAttributes) {
		Users user = userService.findByUserName(principal.getName());
		List<Cart> listcart = cartService.findByUser(user);
		Bill bill = (Bill) session.getAttribute("bill");
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {
				billService.save(bill);
				for (Cart cart : listcart) {
					if (cart.getProduct().getPrice_discount() > 0) {
						BillInfo b = new BillInfo();
						b.setQuantity(cart.getQuantity());
						b.setTotal(cart.getProduct().getPrice_discount());
						b.setProduct(cart.getProduct());
						b.setBill(billService.getById(billService.getMaxId()));
						billInfoService.save(b);
						Product p = productService.getById(cart.getProduct().getId());
						p.setQuantity(p.getQuantity() - cart.getQuantity());
						productService.update(p);
					} else {
						BillInfo b = new BillInfo();
						b.setQuantity(cart.getQuantity());
						b.setTotal(cart.getProduct().getPrice());
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
				session.removeAttribute("bill");
				redirectAttributes.addFlashAttribute("message","Thanh toán thành công!");
				return "redirect:/thanh-toan";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
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
