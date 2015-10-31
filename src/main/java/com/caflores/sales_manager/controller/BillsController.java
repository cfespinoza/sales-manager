package com.caflores.sales_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caflores.sales_manager.service.SalesManagerService;
import com.caflores.sales_manager.utils.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/bills")
public class BillsController {
	
	@Autowired
	SalesManagerService salesManagerService;
	
	private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	@RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
    public String post(@RequestBody String params) {
		Response response = new Response();
		if(params.equals("")) {
			response.setStatus(500);
			response.setMessage("Check params");
		}
		if(salesManagerService.addBill(params)){
			response.setStatus(200);
			response.setMessage("Bill has been inserted");
		}
		return gson.toJson(response);
    }
}
