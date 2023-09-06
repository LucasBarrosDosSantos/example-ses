package br.com.example.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;

import br.com.example.mail.model.MailRequestDTO;

@Service
public class MailService {

	

	@Autowired
	AmazonSimpleEmailService amazonSimpleEmailService;

	public void send(MailRequestDTO mailRequest) {
		String emailContent = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<body>\r\n" + "<p>Ola AWS SES</p>\r\n"
				+ "</body>\r\n" + "</html>";

		try {
			SendEmailRequest sendEmailRequest = new SendEmailRequest()
					.withDestination(new Destination().withToAddresses(mailRequest.getReceiver()))
					.withMessage(new Message()
							.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(emailContent)))
							.withSubject(new Content().withCharset("UTF-8").withData(mailRequest.getSubject())))
					.withSource(mailRequest.getSender());
			SendEmailResult result = amazonSimpleEmailService.sendEmail(sendEmailRequest);
			System.out.println(result.getMessageId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
