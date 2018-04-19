package com.kuiiz.matematicaplay.operacao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	
	@GetMapping("/")
	public String home() {
		return "<h1> Matemática Play (-: </h1>";
	}
	
}
