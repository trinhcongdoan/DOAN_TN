package com.granduation.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.granduation.Controller.BaseController;

@Controller
public class AdminAdsController extends BaseController {

	@GetMapping(value="/admin-quang-cao")
	public String Ads() {
		return "pages/admin/ads";
	}
	
	@GetMapping(value="/admin-quang-cao-edit")
	public String editAds() {
		return "pages/admin/edit-ads";
	}
}
