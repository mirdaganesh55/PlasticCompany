package com.java.employ;
// Subadhiiiii
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class AppMail {

	public static void sendEmail(Employ employ,EmpLogin empLogin) {

		System.out.println("Preparing to send message ...");
		String subject = "Employee Registration Verification: OTP Sent for Activation";
		String to = employ.getEmail();
		String from = "professional.ganesh237@gmail.com";
		String imagePath = "C:\\Users\\ganeshmi\\Downloads\\MailImage.jpg"; // Replace with the actual path to your image file
		String body = "<html>" +
				"<head>"+
				"<style>" +
				"body {" +
				"font-family: 'Helvetica', 'Arial', sans-serif;" +
				"line-height: 1.6;" +
				"margin: 0;" +
				"padding: 0;" +
				"background-color: #f9f9f9;" +
				"}" +
				".container {" +
				"max-width: 600px;" +
				"margin: 20px auto;" +
				"padding: 30px;" +
				"background-color: #ffffff;" +
				"box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);" +
				"border-radius: 10px;" +
				"}" +
				"h1 {" +
				"color: #333333;" +
				"font-size: 24px;" +
				"}" +
				"p {" +
				"color: #555555;" +
				"font-size: 16px;" +
				"margin-bottom: 20px;" +
				"}" +
				"</style>" +
				"</head>" +
				"<body>" +
				"<div class='container'>" +
				"<h1>Welcome to Our Company!</h1>" +
				"<p>Dear <span style='color: #3196CF;'>" + employ.getFirstName() + " " + employ.getLastName() + "</span>,</p>" +
				"<p>An OTP has been sent to your registered email address for completing the verification process of your employee registration. Please do not share this OTP with anyone.</p>" +
				"<p>Your OTP is : " + empLogin.getOtp() + "</p>"+
				"<p>Once you receive the OTP, kindly complete the verification process to finalize your registration as an employee.</p>" +
				"<p>If you encounter any issues or have any questions, feel free to contact our support team.</p>" +
				"<p>Thank you for choosing to be a part of our team. We look forward to your successful verification!</p>" +
				"<p>Best regards,</p>" +
				"<p style=\"color: #09EAC8;\"> GANESH MIRDA </p>"+
				"</div>" +
				"</body>" +
				"</html>";
		sendEmail(subject, to, from, imagePath, body);
	}

	private static void sendEmail(String subject, String to, String from, String imagePath, String body) {

		String host = "smtp.gmail.com";

		// Get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES " + properties);

		// Setting important information to properties object

		// Host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step 1: Get the session object
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("professional.ganesh237@gmail.com", "yicauyiftohuphuw");
			}
		});

		session.setDebug(true);

		// Step 2: Compose the message [text, multimedia]
		MimeMessage mimeMessage = new MimeMessage(session);

		try {
			// Set the from email
			mimeMessage.setFrom(new InternetAddress(from));

			// Add recipient to the message
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Add subject to the message
			mimeMessage.setSubject(subject);

			// Create MimeMultipart for mixed content
			MimeMultipart mixedMultipart = new MimeMultipart("mixed");

			// Create MimeMultipart for related content (HTML + Image)
			MimeMultipart relatedMultipart = new MimeMultipart("related");

			// Create MimeBodyPart for the HTML content
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(body, "text/html");
			relatedMultipart.addBodyPart(htmlPart);

			// Create MimeBodyPart for the image
			MimeBodyPart imagePart = new MimeBodyPart();
			DataSource fds = new FileDataSource(imagePath);
			imagePart.setDataHandler(new DataHandler(fds));
			imagePart.setHeader("Content-ID", "<image>");
			relatedMultipart.addBodyPart(imagePart);

			// Create MimeBodyPart for the related content
			MimeBodyPart relatedBodyPart = new MimeBodyPart();
			relatedBodyPart.setContent(relatedMultipart);

			// Add relatedBodyPart to mixedMultipart
			mixedMultipart.addBodyPart(relatedBodyPart);

			// Set the content of the message
			mimeMessage.setContent(mixedMultipart);

			// Step 3: Send the message using Transport class
			Transport.send(mimeMessage);

			System.out.println("Sent successfully.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
