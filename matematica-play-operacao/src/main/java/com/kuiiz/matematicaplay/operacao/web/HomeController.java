package com.kuiiz.matematicaplay.operacao.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	
	@GetMapping("/")
	public String home() {
		return "<h1> Matemática Play (-: </h1>";
	}
	
}
