package com.mahait.gov.in.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.aspectj.apache.bcel.classfile.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.repository.ForgotPasswordRepo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Autowired
	ForgotPasswordRepo forgotPasswordRepo;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public OrgUserMst initiatePasswordReset(String emailId, HttpServletRequest request) {
		List<Object[]> lstObject = forgotPasswordRepo.findUserByEmail(emailId);
		if (lstObject.size() > 0) {
			 String username=(String) lstObject.get(0)[1]; 
			OrgUserMst orgUserMst = forgotPasswordRepo.findUserObjectByUserName(username);
			if (orgUserMst != null) {
				String token = UUID.randomUUID().toString();
				orgUserMst.setResetToken(token);
				orgUserMst.setResetTokenExpiry(LocalDateTime.now().plusMinutes(5)); // Set expiry time
				forgotPasswordRepo.updateTokenDetails(orgUserMst);
				String resetPasswordLink = null;//Utility.getSiteURL(request) + "/reset_password?token=" + token;
				try {
					sendEmail(emailId, resetPasswordLink);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return orgUserMst;
			}

		} else {
			new OrgUserMst();
		}
		return null;
	}

	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);

	    helper.setFrom("sudhirshahare.egov@gmail.com", "HTESevaarth Support");
	    helper.setTo(recipientEmail);

	    String subject = "HTE Sevaarth Password Reset Link";

	    String content = "<html>"
	            + "<head>"
	            + "<style>"
	            + "body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }"
	            + ".container { background-color: #ffffff; padding: 30px; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }"
	            + ".header { text-align: center; }"
	            + ".button { display: inline-block; padding: 10px 20px; font-size: 16px; color: white; background-color: #007BFF; border-radius: 5px; text-decoration: none; }"
	            + ".footer { margin-top: 20px; font-size: 12px; color: #888; text-align: center; }"
	            + "</style>"
	            + "</head>"
	            + "<body>"
	            + "<div class='container'>"
	            + "<h2 class='header'>Hello,</h2>"
	            + "<p>You have requested to reset your password.</p>"
	            + "<p>Click the button below to change your password:</p>"
	            + "<p><a href=\"" + link + "\" class='button'>Change My Password</a></p>"
	            + "<p>Ignore this email if you remember your password or if you did not make this request.</p>"
	            + "</div>"
	            + "<div class='footer'>"
	            + "<p>&copy; 2024 HTESevaarth. All rights reserved.</p>"
	            + "</div>"
	            + "</body>"
	            + "</html>";

	    helper.setSubject(subject);
	    helper.setText(content, true);

	    mailSender.send(message);
	}


	@Override
	public OrgUserMst checkTokenIsValid(String token) {
		return forgotPasswordRepo.checkTokenIsValid(token);
	}

	@Override
	public void updatePassword(OrgUserMst orgUserMst) {
		forgotPasswordRepo.updatePassword(orgUserMst);
	}

}
