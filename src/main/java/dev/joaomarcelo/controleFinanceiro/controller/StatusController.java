package dev.joaomarcelo.controleFinanceiro.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/status")
public class StatusController {

	@GetMapping
	public Boolean status() {
		return true;
	}

}
