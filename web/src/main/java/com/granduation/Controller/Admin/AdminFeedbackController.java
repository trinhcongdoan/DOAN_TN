package com.granduation.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.granduation.Controller.BaseController;
import com.granduation.Service.IFeedbackService;

@Controller
public class AdminFeedbackController extends BaseController {
	
	@Autowired
	private IFeedbackService feedbackService;

	@GetMapping(value="/admin-feedback")
	public String Feedback(Model model) {
		model.addAttribute("feedbacks", feedbackService.findAll());
		return "pages/admin/feedback";
	}
	@GetMapping(value="/admin-feedback-xoa")
	public String delete(Model model,@RequestParam(name = "id") int id) {
		feedbackService.delete(id);
		return "redirect:/admin-feedback";
	}
	
}
