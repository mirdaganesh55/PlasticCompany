package com.java.employ;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class EmployDAOImpl implements EmployDAO {

	private String filePath;
	private String enteredOtp;
	private String enteredOtp1;
	private String enteredOtp2;
	private String enteredOtp3;
	private String enteredOtp4;
	public String getEnteredOtp() {
		return enteredOtp;
	}

	public void setEnteredOtp(String enteredOtp) {
		this.enteredOtp = enteredOtp;
	}

	public String getEnteredOtp1() {
		return enteredOtp1;
	}

	public void setEnteredOtp1(String enteredOtp1) {
		this.enteredOtp1 = enteredOtp1;
	}

	public String getEnteredOtp2() {
		return enteredOtp2;
	}

	public void setEnteredOtp2(String enteredOtp2) {
		this.enteredOtp2 = enteredOtp2;
	}

	public String getEnteredOtp3() {
		return enteredOtp3;
	}

	public void setEnteredOtp3(String enteredOtp3) {
		this.enteredOtp3 = enteredOtp3;
	}

	public String getEnteredOtp4() {
		return enteredOtp4;
	}

	public void setEnteredOtp4(String enteredOtp4) {
		this.enteredOtp4 = enteredOtp4;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public EmpLogin searchadmin(String username) {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(EmpLogin.class);
		cr.add(Restrictions.eq("username", username));
		EmpLogin empFound = (EmpLogin) cr.uniqueResult();
		return empFound;
	}
	
	public static String generateEmployID() {
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Query query = session.createQuery("SELECT MAX(e.empId) FROM Employ e");
		String lastUHID = (String) query.uniqueResult();

		if (lastUHID == null) {
			lastUHID = "PCE0"; // Set an initial value if the table is empty
		}

		int numericPart = Integer.parseInt(lastUHID.substring(3)) + 1;
		String newUHID = String.format("PCE%01d", numericPart);
		System.out.println(newUHID);

		session.close();
		return newUHID;
	}

	@Override
	public String saveEmpDetailsDao(Employ employ, EmpLogin empLogin) throws IOException {
		Map<String, Object> sessionMap2 = 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		String empId = generateEmployID();
		employ.setEmpId(empId);
		upload(employ.getFile());
		employ.setImgUrl(filePath);
		employ.setEmploymentStatus("Active");
		String encr = EncryptPassword.getCode(empLogin.getPassword());
		empLogin.setPassword(encr.trim());
		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();
		Transaction trans = session.beginTransaction();
		session.save(employ);
		trans.commit();
		sessionMap2.put("EmployData", employ);

		session.close();

		sf = SessionHelper.getConnection();
		session = sf.openSession();
		empLogin.setLastLoginTime(new Date());
		empLogin.setLoginId(empId);
		String getOtp = generateOtp(4);
		empLogin.setOtp(getOtp);
		Map<String, Object> sessionMap = 
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Transaction trans1 = session.beginTransaction();
		session.save(empLogin);
		trans1.commit();
		sessionMap.put("LoginData", empLogin);
		System.out.println("Emp "+empLogin);
		EmpLogin logDataMap = (EmpLogin) sessionMap.get("LoginData");
		System.out.println("Session Map Value : "+logDataMap);
		session.close();
		return "EmpLogin.jsf?faces-redirect=true";
	}

	public void upload(Part file) {
		if (file != null) {
			try (InputStream input = file.getInputStream()) {
				String fileName = getSubmittedFileName(file);
				filePath = "D:/EmploySaveImage/SaveImage/" + fileName;
				try (OutputStream output = new FileOutputStream(new File(filePath))) {
					int bytesRead;
					final byte[] CHUNK = new byte[1024];
					while ((bytesRead = input.read(CHUNK)) != -1) {
						output.write(CHUNK, 0, bytesRead);
					}
				}
				System.out.println("Upload done in "+filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("File is Empty");            
		}
	}

	private String getSubmittedFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				fileName =  fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
				fileName = fileName.replace(" ", "_");
				return fileName;
			}
		}
		return null;
	}

	@Override
	public String empLoginPage(EmpLogin login) {
		

		SessionFactory sf = SessionHelper.getConnection();
		Session session = sf.openSession();

		Criteria cr = session.createCriteria(EmpLogin.class);
		cr.add(Restrictions.eq("username", login.getUsername()));
		cr.add(Restrictions.eq("password", EncryptPassword.getCode(login.getPassword())));

		//		cr.add(Restrictions.eq("otp",login.getOtp()));
		cr.setProjection(Projections.rowCount());
		long count = (long) cr.uniqueResult();
		System.out.println("Count "+count);
		if (count == 1 ) {
			System.out.println("Inside if Block : " +count);
			System.out.println("Login Object Data from empLogin : "+login);
			return getUserVerifiedStatus(session,login);
		} else {
			return "EmployDetails.jsp?faces-redirect=true";
		}
	} 
	
	public static String generateOtp(int length) {
		String characters = "0123456789";
		SecureRandom secureRandom = new SecureRandom();
		StringBuilder otp = new StringBuilder(6);

		for (int i = 0; i < 4; i++) {
			int randomIndex = secureRandom.nextInt(characters.length());
			char randomChar = characters.charAt(randomIndex);
			otp.append(randomChar);
		}
		System.out.println("Generated Otp "+otp.toString());
		return otp.toString();
	}

	private String getLatestOtpForUser(Session session) {
		// Get the patient object from the session map
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		EmpLogin logDetails12 = (EmpLogin) sessionMap.get("EmpDbData");
		System.out.println("Log Details "+logDetails12);
		EmpLogin employlog = searchadmin(logDetails12.getUsername());
		System.out.println("Search Method getLatest "+employlog);
		if (employlog != null) {
			// Retrieve PID from the patient object
			String username = logDetails12.getUsername();
			System.out.println(username);
			Criteria criteria = session.createCriteria(EmpLogin.class);
			criteria.add(Restrictions.eq("username", username));
			criteria.setMaxResults(1);

			EmpLogin otp = (EmpLogin)criteria.uniqueResult();

			if (otp != null) {
				return otp.getOtp();
			} else {
				System.out.println("No OTP found for the provided cust_ID: " + username);
				return null;
			}
		} else {
			System.out.println("Admin object not found in the session.");
			return null;
		}
	}	
	private String getUserVerifiedStatus(Session session,EmpLogin login) {
		// Get the patient object from the session map
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		EmpLogin EmpLogDataDb = searchadmin(login.getUsername());
		sessionMap.put("EmpDbData", EmpLogDataDb);
		EmpLogin logDetails12 = (EmpLogin) sessionMap.get("EmpDbData");

		System.out.println("Employ data by search meth():  "+EmpLogDataDb);
		if (EmpLogDataDb.getUsername() != null) {
			// Retrieve PID from the patient object
			String userVerify = EmpLogDataDb.getOtpVerifyStatus();
			System.out.println("Inside emp User login otp status is: "+userVerify);
			Criteria criteria = session.createCriteria(EmpLogin.class);
			criteria.add(Restrictions.eq("otpVerifyStatus", userVerify));
			criteria.setMaxResults(1);
			EmpLogin verifyStatus = (EmpLogin)criteria.uniqueResult();
			System.out.println("Verify "+verifyStatus);
			if (verifyStatus == null ) {
				System.out.println("Hitted ");
				System.out.println("EmpLog Deatils from Session Map logDetails12 :"+logDetails12);
				Employ logDetails1 = (Employ) sessionMap.get("EmployData");
				AppMail.sendEmail(logDetails1,logDetails12);
				return "EmployVerification.jsp?faces-redirect=true";
			}
		}
		return "sucess.jsp?faces-redirect=true";
	}	
	public String validateResetOtp() {
	    String enteredOtp = enteredOtp1 + enteredOtp2 + enteredOtp3 + enteredOtp4;
	    SessionFactory sf = SessionHelper.getConnection();
	    Session session = sf.openSession();
	    Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	    EmpLogin logDetails12 = (EmpLogin) sessionMap.get("EmpDbData");

	    System.out.println("login Details came from empLogin meth():" + logDetails12);
	    String otpFromDb = getLatestOtpForUser(session);

	    EmpLogin employlog = searchadmin(logDetails12.getUsername());

	    if (enteredOtp.equals(otpFromDb)) {
	        employlog.setOtpVerifyStatus("Verified");
	        System.out.println("Validate " + employlog);
	        Transaction trans2 = session.beginTransaction();
	        session.evict(employlog); // Evicting the object from the session
	        session.merge(employlog);
	        System.out.println("After save " + employlog);
	        trans2.commit();
	        System.out.println("Successfully checked...");
	        return "sucess.jsp?faces-redirect=true";
	    } else {
	        System.out.println("Incorrect Otp Try again");
	        FacesContext.getCurrentInstance().addMessage("form:digit1",
	                new FacesMessage("Invalid Otp, Please Try again"));
	        return "invalid otp";
	    }
	}

}	