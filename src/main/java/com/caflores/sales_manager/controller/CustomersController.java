package com.caflores.sales_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caflores.sales_manager.service.SalesManagerService;
import com.caflores.sales_manager.utils.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/customers")
public class CustomersController {
	
	@Autowired
	SalesManagerService salesManagerService;
	
	private Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	
	@RequestMapping(value="/{idUser}/products", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
    public String getProducts(@PathVariable long idUser) {
		Response response = new Response();
		if(idUser < 1){
			response.setStatus(500);
			response.setMessage("Check the params");
		}
		else{
			response.setData(salesManagerService.getProducts(idUser));
			response.setMessage("Data retrieved");
			response.setStatus(200);
		}
		return gson.toJson(response);
    }

}
