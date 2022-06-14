package com.granduation.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.granduation.Entity.Province;

@Controller
public class AddressJsonController extends BaseController {

	@RequestMapping(value="/api/province")
	@ResponseBody
	public List<Province> Province(HttpServletRequest request){
		List<Province> listProvince = provinceService.findAllProvince();
		return listProvince;
	}
}
