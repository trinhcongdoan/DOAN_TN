package com.granduation.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.granduation.Controller.BaseController;
import com.granduation.Entity.Bill;

@Controller
public class AdminBillController extends BaseController {

	@GetMapping(value = "/admin-don-hang-moi")
	public String NewBill(Model model) {
		model.addAttribute("bills",billService.findByStatus("Chờ giao"));
		return "pages/admin/new-order-bill";
	}
	
	@GetMapping(value = "/admin-don-hang-da-giao")
	public String editBillR(@RequestParam(name = "id") int id,Model model) {
		Bill b = billService.getById(id);
		b.setStatus("Đã giao");
		billService.save(b);
		return "redirect:/admin-don-hang-moi";
	}

	@GetMapping(value = "/admin-don-hang-edit")
	public String editBill(@RequestParam(name = "id") int id,Model model) {
		model.addAttribute("bill",billService.getById(id));
		return "pages/admin/edit-new-order-bill";
	}

	@GetMapping(value = "/admin-don-hang-da-nhan")
	public String ReceivedBill(Model model) {
		model.addAttribute("bills",billService.findByStatus("Đã giao"));
		return "pages/admin/received-bill";
	}

	@GetMapping(value = "/admin-don-hang-doi-tra")
	public String ReturnBill(Model model) {
		model.addAttribute("bills",billService.findByStatus("Đổi trả"));
		return "pages/admin/return-bill";
	}
}
