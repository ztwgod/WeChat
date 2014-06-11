package urlAction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class GetBoxInfo extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private final String url="http://210.13.107.146:8087/XB/WeChatServlet?applyCode=17"
			+ "&msg=";
	public String execute() throws Exception {
		HttpServletRequest request= ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		String telephone=request.getParameter("telephone");
		URL myUrl = new URL(url+telephone+"%201"); 
		System.out.println(myUrl.toString());
		HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection(); 
		connection.connect();
		BufferedReader reader = new BufferedReader
				(new InputStreamReader(connection.getInputStream(),"utf-8")); 
		String result=""; 
		String temp=null;
        while ((temp=reader.readLine())!= null) { 
        	result=result+temp;

        } 
        request.setAttribute("boxInfo", result);
        System.out.println(result);
		return SUCCESS;
		
	}
	

}
