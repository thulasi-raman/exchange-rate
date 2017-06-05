package com.exrate.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.exrate.dto.ExchangeResponseDTO;
import com.exrate.dto.UserFormDTO;
import com.exrate.service.ExRateService;
import com.exrate.util.ExRateUtil;
import com.exrate.validator.HomeValidator;

@Controller
public class ExRateController {
	
	@Autowired
	private ExRateService exRateService;
	
	@Autowired HomeValidator homeValidator;
	
	@GetMapping(value ={"/home","/"})
    public String getHome(@RequestParam(required=false, name= "date") String date, @Valid @ModelAttribute("responseData") ExchangeResponseDTO responseDTO, 
    		BindingResult result, Model model) {
		if(null != date ) responseDTO = exRateService.getExchangeRateResponse("historical", date);
		else responseDTO = exRateService.getExchangeRateResponse("latest", null);
        model.addAttribute("responseData", responseDTO);
        return "home";
    }

	/*@GetMapping(value ={"/home","/"})
    public String getHome( @Valid @ModelAttribute("responseData") ExchangeResponseDTO responseDTO, BindingResult result, Model model) {
		responseDTO = exRateService.getExchangeRateResponse("latest", null);
		System.out.println("GET Response Form : " + responseDTO.toString());
		System.out.println("GET Chosen Base :" + responseDTO.getBase());
		System.out.println("GET Chosen Queried Date :" + responseDTO.getExchangeRateDate());
        model.addAttribute("responseData", responseDTO);
        return "home";
    }*/

	@PostMapping(value ={"/home"})
    public String getHistoricalData(@Valid @ModelAttribute("responseData") ExchangeResponseDTO responseDTO, BindingResult result, Model model) {
		homeValidator.validate(responseDTO, result);
		model.addAttribute("responseData", responseDTO);
		if(result.hasErrors()) return "home";
		model.addAttribute("responseData", exRateService.getExchangeRateResponse("historical", responseDTO.getExchangeRateDate()));
        return "home";
    }
	


}
