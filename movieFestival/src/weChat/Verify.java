/**
 * @author ztwgod
 * @Date 2014/4/21
 * Entrance of the web project
 */

package weChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Verify extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private String timeStamp;
	private String nonce;
	private String signature;
	private final static String REPLY="reply";	
	/**
     *  Function:execute
     *  @author:ztwgod
     *  @param 
     *  @return:String
     */
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		timeStamp=request.getParameter("timestamp");
		nonce=request.getParameter("nonce");
		signature=request.getParameter("signature");
		//verify the msg from wechat
		if (new Sign().checkSignature(signature,timeStamp,nonce)) {
			// judge if it is a verify message
			if (request.getParameter("echostr")!=null)
			{
				request.setAttribute("echostr", request.getParameter("echostr"));
				return SUCCESS;
			}
			else {	
				HttpSession session=request.getSession();
				ServletContext application= session.getServletContext();
				application.setAttribute("myFileReader", new MyFileReader());
				String postMsg = readStreamParameter(request.getInputStream());
				String resultMsg=new MsgDealer().msgDeal(postMsg);
				request.setAttribute("resMsg", resultMsg);
                return REPLY;
           }
       }
       return ERROR;
   }
    /**
     *  Function:readStreamParameter
     *  @author ztwgod
     *  @param ServletInputStream  postMsg
     *  @return String
    */
	public String readStreamParameter(ServletInputStream in) {  
       StringBuilder buffer = new StringBuilder();  
       BufferedReader reader=null;  
       try{  
           reader = new BufferedReader(new InputStreamReader(in,"utf-8"));  
           String line=null;  
           while((line = reader.readLine())!=null) {  
               buffer.append(line);  
           }  
       }catch(Exception e) {  
           e.printStackTrace();  
       }finally{  
           if(null!=reader) {  
               try {  
                   reader.close();  
               } catch (IOException e) {  
                   e.printStackTrace();  
               }  
           }  
       }  
       return buffer.toString();  
   } 
}
