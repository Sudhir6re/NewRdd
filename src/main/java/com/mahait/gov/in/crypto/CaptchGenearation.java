package com.mahait.gov.in.crypto;

import java.util.Random;


/* Added by Sudhir */
public class CaptchGenearation {
	
	 public static String generateRandomCaptchaText() {
	        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	        int length = 6;
	        Random random = new Random();
	        StringBuilder captchaText = new StringBuilder();
	        for (int i = 0; i < length; i++) {
	            int index = random.nextInt(characters.length());
	            captchaText.append(characters.charAt(index));
	        }
	        return captchaText.toString();
	    }

	 

}
