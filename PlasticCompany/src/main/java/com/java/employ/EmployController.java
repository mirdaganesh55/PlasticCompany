package com.java.employ;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class EmployController {
	
	private Employ employe;
	private EmployDAOImpl employeDao;
	private final long MAX_FILE_SIZE = 100 * 1024;
	
	public Employ getEmploye() {
		return employe;
	}
	public void setEmploye(Employ employe) {
		this.employe = employe;
	}
	public EmployDAOImpl getEmployeDao() {
		return employeDao;
	}
	public void setEmployeDao(EmployDAOImpl employeDao) {
		this.employeDao = employeDao;
	}
	//below is the list of extensions to compare
    private final List<String> allowedExtensions = Arrays.asList(".jpg", ".jpeg", ".png");
    

	//Check Conditions
	public String addValidConditions(Employ employ,EmpLogin empLogin) throws IOException,NumberFormatException {
		if(addValid(employ,empLogin)) {
			return employeDao.saveEmpDetailsDao(employ,empLogin);
		}
		return "";
	}
	public String empLogin(EmpLogin login) {
		return employeDao.empLoginPage(login);
	}
	public boolean addValid(Employ employ,EmpLogin empLogin) {
		FacesContext context = FacesContext.getCurrentInstance();
		String userName = "^[^\\s]{8,16}$";

		boolean flag = true;
		
		if (!Pattern.matches(userName, empLogin.getUsername())) {
			context.addMessage("form:username", new FacesMessage("Username Contains min 8 characters."));
			flag = false;
		}
		
		if(employ.getFirstName().length() <= 0) {
			context.addMessage("form:firstName", new FacesMessage("First name is required."));
			flag = false;
		}
		if(employ.getLastName().length() <= 0) {
			context.addMessage("form:lastName", new FacesMessage("Last name is required."));
			flag = false;
		}
		if(employ.getGender().isEmpty()) {
			context.addMessage("form:gender", new FacesMessage("Please select a gender."));
			flag = false;
		}
		if (employ.getDateOfBirth() != null) {

			Date userDate = employ.getDateOfBirth();
			Date curreDate = new Date();
			if(userDate.after(curreDate)) {
				context.addMessage("form:dateOfBirth", new FacesMessage("Date of birth cannot be future date"));
				flag = false;
			}
		} else {
		    context.addMessage("form:dateOfBirth", new FacesMessage("Date of birth cannot be empty"));
		    flag = false;
		}
		if (employ.getAddress().isEmpty()) {
			context.addMessage("form:address", new FacesMessage("Please enter address."));
			flag = false;
		}
		if (employ.getPhoneNumber().isEmpty()) {
			context.addMessage("form:phoneNumber", new FacesMessage("Phone number is required"));
			flag = false;
		}
		if (employ.getEmail().isEmpty()) {
			context.addMessage("form:email", new FacesMessage("Email is required."));
			flag = false;
		}
		//Control for uploading the image
		 if (employe.getFile() == null || employe.getFile().getSize() == 0) {
	            context.addMessage("form:image", new FacesMessage("Image is required."));
	            flag = false;
	        }else {
		         // Check if the file extension is in the list of allowed extensions
	            // Get the file name
	            String fileName = employe.getFile().getSubmittedFileName();
	            // Extract the file extension
	            String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
	        	if (!allowedExtensions.contains(fileExtension.toLowerCase())) {
	                context.addMessage("form:image", new FacesMessage("Please upload a .jpg, .jpeg, or .png file."));
	                flag = false;
	            }
	        	// Get the file size
	            long fileSize = employe.getFile().getSize();
	            // Check if the file size exceeds the maximum allowed size
	            if (fileSize > MAX_FILE_SIZE) {
	                context.addMessage("form:image", new FacesMessage("Please upload a file smaller than 100 KB."));
	                flag = false;
	            }
			}
		return flag;
	}
}
