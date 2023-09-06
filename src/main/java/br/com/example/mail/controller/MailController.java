package br.com.example.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.mail.model.MailRequestDTO;
import br.com.example.mail.service.MailService;

@RequestMapping
@RestController
public class MailController {

	@Autowired
	private MailService mailService;

	@PostMapping("/mail")
	public void sendMail(@RequestBody MailRequestDTO mailRequestDTO) {
		mailService.send(mailRequestDTO);
	}

}
