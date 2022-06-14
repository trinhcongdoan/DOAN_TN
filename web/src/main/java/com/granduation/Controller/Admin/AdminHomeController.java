package com.granduation.Controller.Admin;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.granduation.Controller.BaseController;
import com.granduation.Entity.Bill;
import com.granduation.Entity.BillInfo;
import com.granduation.Entity.Product;

@Controller
public class AdminHomeController extends BaseController {

	@GetMapping(value = { "/admin", "/admin-trang-chu" })
	public String Home(Model model) {
		List<Bill> lBills = billService.findByStatus("Đã giao");
		Double revenueDay = 0.0;
		Double revenueMonth = 0.0;
		Double revenueQuarter = 0.0;
		Double revenue = 0.0;
		Date now = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for (Bill bill : lBills) {
			if (df.format(now).equals(df.format(bill.getCreate_at().getTime()))) {
				revenueDay += bill.getTotal();
			}
			if (df.format(now).substring(0, 6).equals(df.format(bill.getCreate_at().getTime()).substring(0, 6))) {
				revenueMonth += bill.getTotal();
			}
			if (getQuarter(now) == getQuarter(new Date(bill.getCreate_at().getTime()))) {
				revenueQuarter += bill.getTotal();
			}
			revenue += bill.getTotal();
		}
		model.addAttribute("revenueDay", revenueDay);
		model.addAttribute("revenueMonth", revenueMonth);
		model.addAttribute("revenueQuarter", revenueQuarter);
		model.addAttribute("revenue", revenue);
		model.addAttribute("bills", lBills);
		model.addAttribute("products", productService.findAll());
		return "pages/admin/index";
	}

	public int getQuarter(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) / 3 + 1;
	}

	@PostMapping(value = "/api/doanh-thu")
	@ResponseBody
	public HashMap<String, Double> Revenue() {
		String arr[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		List<Bill> lBills = billService.findByStatus("Đã giao");
		Calendar cal = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		HashMap<String, Double> map = new HashMap<String, Double>();
		map.put("Jan", 0.0);
		map.put("Feb", 0.0);
		map.put("Mar", 0.0);
		map.put("Apr", 0.0);
		map.put("May", 0.0);
		map.put("Jun", 0.0);
		map.put("Jul", 0.0);
		map.put("Aug", 0.0);
		map.put("Sep", 0.0);
		map.put("Oct", 0.0);
		map.put("Nov", 0.0);
		map.put("Dec", 0.0);
		for (Bill bill : lBills) {
			cal.setTime(bill.getCreate_at());
			if (cal.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
				if (!map.containsKey(arr[cal.get(Calendar.MONTH)])) {
					map.put(arr[cal.get(Calendar.MONTH)], bill.getTotal());
				} else {
					map.put(arr[cal.get(Calendar.MONTH)], map.get(arr[cal.get(Calendar.MONTH)]) + bill.getTotal());
				}
			}
		}
		return map;
	}
	@PostMapping(value = "/api/thong-ke-san-pham")
	@ResponseBody
	public HashMap<String, Integer> statisticalProduct(){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int sumStock = 0;
		int sumSold = 0;
		String maxName = "Không sản phẩm";
		int maxValue = 0;
		String minName = "Không sản phẩm";
		int minValue = 0;
		List<Product> lProducts = productService.findAll();
		for (Product product : lProducts) {
			sumStock+=product.getQuantity();
			sumSold+=getQuantity(product.getBillInfo());
			if(maxValue < getQuantity(product.getBillInfo())) {
				maxValue = getQuantity(product.getBillInfo());
				maxName = product.getName();
			}
			
			if(minValue > getQuantity(product.getBillInfo())) {
				minValue = getQuantity(product.getBillInfo());
				minName = product.getName();
			}
		}
		map.put("Sản phẩm còn trong kho", sumStock);
		map.put("Sản phẩm đã bán", sumSold);
		map.put(maxName, maxValue);
		map.put(minName, minValue);
		return map;
	}
	
	public int getQuantity(List<BillInfo> list) {
		int sum=0;
		for (BillInfo billInfo : list) {
			sum+=billInfo.getQuantity();
		}
		return sum;
	}
}
