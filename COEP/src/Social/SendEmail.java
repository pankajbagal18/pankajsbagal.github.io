package Social;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;
public class SendEmail {  
//	 public static void main(String [] args){ 
//		 SendEmail e = new SendEmail();
//		 String[] add = new String[2];
//		 add[0] = "gssalunke1999@gmail.com";
//		 add[1] = "chakrenitin020@gmail.com";
//		 boolean state = e.sendEmail("Hii Again",add);
//		 System.out.println(state);
//	 }
	
	 public boolean sendEmail(String messageContent,String[] emailadd) {
		 boolean state = false;
		 String username = "pankajsbagal@gmail.com";//replace to hostel email
		 String password = "Pankaj@18277840";//sender email password
		 Properties props = new Properties();
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.starttls.enable","true");
		 props.put("mail.smtp.host","smtp.gmail.com");
		 props.put("mail.smtp.port", "587");
		 Session session = Session.getInstance(props,new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			} 
		 });
		 try {
			 Message message = new MimeMessage(session);
			 message.setFrom(new InternetAddress(username));
			 String to="";
			 for(String e:emailadd) {
				 to+=e+",";
			 }
			 if (to != null && to.length() > 0 && to.charAt(to.length() - 1) == ',') {
			        to = to.substring(0, to.length() - 1);
		     }
			 InternetAddress[] add = InternetAddress.parse(to,true);
			 message.setRecipients(Message.RecipientType.TO,add);
			 message.setSubject("Seat Allocation Status");
			 message.setContent(messageContent,"text/html");
			 Transport.send(message);
			 System.out.println("done");
			 state = true;
			 }catch (Exception e) {
			// TODO: handle exception
			 System.out.println(e);
		}
		 return state;
	 }
}
/*
public class SendEmail{
	public static void main(String[] argc) {
		
	}
}*/
