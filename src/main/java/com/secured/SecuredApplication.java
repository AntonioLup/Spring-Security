package com.secured;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecuredApplication {

		public static void main(String[] args) {
			try {
				SpringApplication.run(SecuredApplication.class, args);

			}catch (Exception event) {
				event.printStackTrace();
			}

		}


}
