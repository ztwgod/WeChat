package urlAction;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.opensymphony.xwork2.ActionSupport;

public class OpenBoxAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	private String pwd;
	private String msg;

	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String execute() throws Exception {
		
		String url = "http://210.13.107.146:8087/XB/WeChatServlet?applyCode=16&msg="+msg+"%20"+pwd;

		System.out.println(url);
		URL myUrl = new URL(url);

		HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
		connection.connect();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8")); 
		String result=""; 
		String temp=null;
        while ((temp=reader.readLine())!= null) { 
        	result=result+temp;
        }
        System.out.println(result);
        
		return SUCCESS;
	}

}
