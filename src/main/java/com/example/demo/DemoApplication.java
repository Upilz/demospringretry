 package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableRetry
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	@Retryable(value = {NumberFormatException.class, NullPointerException.class}, maxAttempts = 5)
	public String myApp()
	{
		System.out.println("myApp API is calling...");
		//Integer.parseInt("");
//		String str = null ;
//		str.length();
		return "success";
	}
	
	@Recover
	public String recover(NumberFormatException ex)
	{
		System.out.println("Recover Method - Number Format Exception");
		
		return "Recover method - Number Format Exception";
	}
	
	@Recover
	public String recover(NullPointerException ex)
	{
		System.out.println("Recover Method - Null Pointer   Exception");
		return "Recover method - Null Pointer Exception";
	}
}
