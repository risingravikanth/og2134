package com.oganalysis.business.impl;

import static com.oganalysis.constants.ApplicationConstants.FAIL;
import static com.oganalysis.constants.ApplicationConstants.INCORRECT;
import static com.oganalysis.constants.ApplicationConstants.SUCCESS;

import java.io.InputStream;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.oganalysis.business.LoginBusinessService;
import com.oganalysis.constants.ApplicationConstants;
import com.oganalysis.dao.CustomerDao;
import com.oganalysis.dao.UserDao;
import com.oganalysis.entities.User;

public class LoginBusinessServiceImpl implements LoginBusinessService {
	private UserDao userDao;
	private CustomerDao customerDao;
	private String alphaCaps;
	private String alpha;
	private String numeric;
	private String specialChars;
	private String passwordLen;
//	private String mailTo;
	private String mailFrom;
	private String mailBody1;
	private String mailBody2;
	private String mailSubject;
	private String adminEmail;
	private String adminPwd;
	private String mailHost;
	private String mailPort;
	
	
	private SecureRandom random = new SecureRandom();
	@Override
	public User validateUser(String email,String password) {
		// TODO Auto-generated method stub			
		User user=userDao.getUser(email);
		if(null!=user && !password.equals(user.getPassword()))		
			user=null;		
		return user;
	}
	@Override
	public String saveCustomer(Map<String, String> customerMap) {
		// TODO Auto-generated method stub
		String status=FAIL;
		boolean flag=customerDao.saveCustomer(customerMap);
		if(flag==true)
			status=SUCCESS;
		return status;
	}
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}	
	public void setAlphaCaps(String alphaCaps) {
		this.alphaCaps = alphaCaps;
	}
	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}
	public void setNumeric(String numeric) {
		this.numeric = numeric;
	}
	public void setSpecialChars(String specialChars) {
		this.specialChars = specialChars;
	}	
	public void setPasswordLen(String passwordLen) {
		this.passwordLen = passwordLen;
	}
	
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public void setMailBody1(String mailBody1) {
		this.mailBody1 = mailBody1;
	}
	public void setMailBody2(String mailBody2) {
		this.mailBody2 = mailBody2;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}
	public void setMailPort(String mailPort) {
		this.mailPort = mailPort;
	}
	@Override
	public String forgotPassword(String email) {
		// TODO Auto-generated method stub
		String status=FAIL;
		User user=userDao.getUser(email);
		
		if(null!=user && email.equalsIgnoreCase(user.getEmail()))
		{
			try{
				int i=0;
				List<User> usersList=userDao.getPasswords();
				String tempPwd=generatePassword();
				do{
					if(tempPwd.equals(usersList.get(i).getPassword()))
					{
						tempPwd=generatePassword();
						i=0;
					}
					i++;
				}while(usersList.size()!=i);
				
				user.setPassword(tempPwd);
				user.setPasswordReset("Y");				
				boolean flag=userDao.updateUser(user);
				if(flag)
				{
					status=SUCCESS;
					status=sendMailToUser(email, tempPwd);
				}
				
			}
			catch(Exception e)
			{
				status=FAIL;
			}
			
		}			
		else
			status=INCORRECT;
		
		return status;
	}	
	@Override
	public String updatePassword(Map<String, String> pwdMap, String reset) {
		// TODO Auto-generated method stub
		String status=FAIL;
		String email=pwdMap.get("email");
		String currPwd=pwdMap.get("currPwd");
		String newPwd=pwdMap.get("newPwd");
		String confirmPwd=pwdMap.get("confirmPwd");	
		User user=userDao.getUser(email);
		if(null!=user && currPwd.equals(user.getPassword()))
		{
			
			if(reset.equals("Y"))
			{
				user.setPassword(confirmPwd);
				user.setPasswordReset("N");
			}
			else
				user.setPassword(confirmPwd);								
			boolean updated=userDao.updateUser(user);
			if(updated)
				status=SUCCESS;
			else
				status=FAIL;
		}
		else
			status=INCORRECT;
		
		return status;
	}
	private String generatePassword(){
		String result = "";
		StringBuffer pwdFormat=new StringBuffer();
		if(alphaCaps.length()>0)
			pwdFormat.append(alphaCaps);
		if(alpha.length()>0)
			pwdFormat.append(alpha);
		if(numeric.length()>0)
			pwdFormat.append(numeric);
		if(specialChars.length()>0)
			pwdFormat.append(specialChars);
		
		String dic=new String(pwdFormat);
		int len=Integer.parseInt(passwordLen);
	    for (int i = 0; i < len; i++) {
	        int index = random.nextInt(dic.length());
	        result += dic.charAt(index);
	    }
	    return result;
	}
		
	private String sendMailToUser(String emailTo, String resetpassword) {
		// TODO Auto-generated method stub
		String mailRes=null;						
	    
	     //compose the message  
	      try{  
//	    	  InputStream is=UserManagementServiceImpl.class.getClassLoader().getResourceAsStream(ApplicationConstants.APP_PROPERTIES);
//			  Properties properties=new Properties();
//			  properties.load(is);
			 	 
	    	String body=mailBody1+emailTo+mailBody2+resetpassword;
//			String from=mailFrom;
//			String to=email;
			
			 Session session=getMailSession();
	         MimeMessage message = new MimeMessage(session);  
	         message.setFrom(new InternetAddress(mailFrom));  
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailTo));  
	         message.setSubject(mailSubject);  
	         message.setText(body);  
	  
	         // Send message  
	         Transport.send(message);  
//	         logger.info("Mail message sent successfully....");  
	         mailRes=SUCCESS;
	  
	      }catch (MessagingException mex) {
	    	  mailRes=FAIL;		
//	    	  logger.error("Exception in MailServiceImpl - Method sendMailToUser:"+mex);
	      }  
	      catch(Exception e)
	      {
	    	  mailRes=FAIL;	
//	    	  logger.error("Exception in MailServiceImpl - Method sendMailToUser:"+e);
	      }	 
	      return mailRes;
	}  
private Session getMailSession()
{
	final String username = adminEmail;
	final String password = adminPwd;
	String port=mailPort;
	String host=mailHost;
	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", host);
	props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.port", port);

	Session session = Session.getInstance(props,
	  new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	  });
	return session;
}

}
