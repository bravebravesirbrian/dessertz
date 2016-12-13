package org.launchcode.dessertz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ConversionController extends AbstractController {

	@RequestMapping(value = "/conversions", method = RequestMethod.GET)
	public String conversionForm() {
		return "conversions";
	}
}
