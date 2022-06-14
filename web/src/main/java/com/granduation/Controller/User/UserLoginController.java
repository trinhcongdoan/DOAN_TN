package com.granduation.Controller.User;

import java.io.Reader;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.granduation.Controller.BaseController;
import com.granduation.Entity.Users;


@Controller
public class UserLoginController extends BaseController {

	@GetMapping(value ="/dang-nhap")
	public String FormLogin() {
		return "pages/users/login";
	}
	
	@GetMapping(value ="/register")
	public String FormRegister(Model model, @RequestParam(name ="error", defaultValue = "") String error) {
		if(!error.equals("")) {
			model.addAttribute("message","Đăng kí không thành công!");
		}
		Users user = new Users();
		user.setAuthorization(authService.getByName("USER"));
		user.setEnable(false);
		model.addAttribute("user", user);
		model.addAttribute("provinces", provinceService.findAllProvince());
		return "pages/users/register";
	}
	
	@PostMapping(value ="/register")
	public String Register(Model model,@ModelAttribute @Valid Users user,BindingResult result,HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("provinces", provinceService.findAllProvince());
			return "pages/users/register";
		}
		user.setPassword(passencoder.encode(user.getPassword()));
		user.setAuthorization(authService.getByName("USER"));
		user.setEnable(true);
		
		session.setAttribute("userSession", user);
		String code = RandomStringUtils.randomAlphanumeric(8);
		session.setAttribute("codeVerify", code);
		
		session.setMaxInactiveInterval(5*60);
		try {
			sendEmail(user.getEmail(), code);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
//		userService.save(user);
		return "redirect:/verify";
	}
	
	public void sendEmail(String recipientEmail, String code)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();              
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setFrom("congdoan27121999@gmail.com");
        helper.setTo(recipientEmail);
        String subject = "Mã xác nhận đăng kí tài khoản";
        String content = "Mã xác nhận đăng kí của bạn là,<br>" + "<h3>"+ code +"</h3>" + "<3,<br>"
				+ "Trịnh Công Đoàn";
         
        helper.setSubject(subject);
        helper.setText(content, true);
         
        mailSender.send(message);
    }
	
	@GetMapping(value = "/verify")
	public String Verify() {
		return "pages/verify";
	}
	
	@PostMapping(value = "/verify")
	public String verifyPost(Model model,@RequestParam("code") String code, HttpSession session) {
		Users user = (Users) session.getAttribute("userSession");
		String c = (String) session.getAttribute("codeVerify");
		
		if(code.equals(c)) {
			try {
				userService.save(user);
			} catch (Exception e) {
//				model.addAttribute("message","Failed");
//				model.addAttribute("user", new Users());
//				model.addAttribute("provinces", provinceService.findAllProvince());
//				return "pages/users/register";
				
				return "redirect:/register?error=failed";
			}
			session.removeAttribute("codeVerify");
			session.removeAttribute("userSession");
			return "redirect:/dang-nhap";
		}
		model.addAttribute("message","Mã xác nhận không đúng");
		return "pages/verify";
	}
}
