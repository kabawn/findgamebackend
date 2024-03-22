package com.example.findgame.emailService;

import com.example.findgame.entity.User;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    private final SendGrid sendGridClient;

    @Autowired
    public EmailService(@Value("${app.sendgrid.key}") String apiKey) {
        this.sendGridClient = new SendGrid(apiKey);
    }

    public void sendVerificationEmail(User user) {
        String emailAddress = user.getEmail();
        String verificationCode = user.getVerificationCode();

        System.out.println("Sending verification email to: " + emailAddress);

        // Generate the appropriate verification link
        String verificationLink = "http://192.168.1.99:8085/users/verify?code=" + verificationCode;

        Email from = new Email("osamahlasa@gmail.com");
        String subject = "Please verify your email address";
        Email to = new Email(emailAddress);

        String htmlContent = buildEmailContent(user, verificationLink);
        Content content = new Content("text/html", htmlContent);

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = this.sendGridClient;
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            // Enhanced logging
            System.out.println("Email sent with status code: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody());
            System.out.println("Response headers: " + response.getHeaders());
        } catch (IOException ex) {
            // Enhanced error logging
            System.err.println("Failed to send verification email to " + emailAddress);
            ex.printStackTrace();

            throw new RuntimeException("Failed to send verification email", ex);
        }
    }

    private String buildEmailContent(User user, String verificationLink) {
        return "<h1>Dear " + user.getFirstName() + "</h1>"
                + "<p>Thank you for registering. Please click the link below to verify your registration:</p>"
                + "<h3><a href=\"" + verificationLink + "\">VERIFY</a></h3>"
                + "<p>Thank you<br>The Team</p>";
    }
}

