package com.idea.hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HubApplication {
	
//	@Autowired
//	private SendEmail sendEmail;

	public static void main(String[] args) {
		SpringApplication.run(HubApplication.class, args);
	}
	
//	@EventListener(ApplicationReadyEvent.class)
//	public void sendmail() {	
//		sendEmail.mailSend("kamal.chadha162@gmail.com", "JAVA MAIL", "<h1>HELLLO </h1>");
//	}

}
