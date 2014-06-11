package urlAction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GetCheckInfo extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private final String url="http://210.13.107.146:8087/XB/WeChatServlet?applyCode=17&msg=";
	
	public String execute() throws Exception {
		
		HttpServletRequest request= ServletActionContext.getRequest();
		Map<String, Object> application = ActionContext.getContext().getApplication();
		String telephone=request.getParameter("telephone");
		String type=(String)application.get("type");
		System.out.println(type);
		URL myUrl = new URL(url+telephone+"%20"+type); 
		System.out.println(myUrl.toString());
		
		HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection(); 
		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8")); 
		String result=""; 
		String temp=null;
        while ((temp=reader.readLine())!= null) { 
        	result=result+temp;
        }
        System.out.println(result);
        request.setAttribute("checkInfo",result);
		request.setAttribute("type",type);
		
		return SUCCESS;
	}
}
