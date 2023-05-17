package services.metiersUser;

//import static GUI.User.FP_secondController.ACCOUNT_SID;
//import static GUI.User.FP_secondController.AUTH_TOKEN;
//import static GUI.User.FP_secondController.TWILIO_NUMBER;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author Ghof
 */
 
public class  sendSMS {
    public static final String ACCOUNT_SID = "AC996f5d67ff7718f75549161540ba1e32";     /// 
    public static final String AUTH_TOKEN = "57ae0c797a8253df22be61086378c3ba"; ///   
    public static final String TWILIO_NUMBER = "+13159037947";
	public static void sendSms(String code,String num) {
//		try {
//			// Construct data
//			String apiKey = "apikey=" + "Nzc2YzM5NDY2ZDU3NjQ1YTRhNmI3OTRlNmU3NDc3NjY=";
//			String message = "&message=" +code+ " est votre code de validation";
//			String sender = "&sender=" + "test";
//			String numbers = "&numbers=" + num;
//			
//			// Send data
//			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
//			String data = apiKey + numbers + message;
//			conn.setDoOutput(true);
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
//			conn.getOutputStream().write(data.getBytes("UTF-8"));
//			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			final StringBuffer stringBuffer = new StringBuffer();
//			String line;
//			while ((line = rd.readLine()) != null) {
//				stringBuffer.append(line);
//			}
//			rd.close();
//			
//			//return stringBuffer.toString();
//		} catch (Exception e) {
//			System.out.println("Error SMS "+e);
//			//return "Error "+e;
//		}
/*******************************************************************************************************************************/
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

       String phoneNumber = "+21653414061";
       String msg="Votre code Depanini est "+code;
      //  Message message = Message.creator(new PhoneNumber(phoneNumber),new PhoneNumber(TWILIO_NUMBER),"garage ajouter").create();
         Message message = Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(TWILIO_NUMBER), code).create();
        
        System.out.println(message.getSid());
	}
                

}