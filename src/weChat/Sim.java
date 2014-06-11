package weChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import net.sf.json.JSONObject;

public class Sim {
	public static String simTalk(String request) throws IOException
	{
		request=URLEncoder.encode(request, "UTF-8");
		final String str="http://sandbox.api.simsimi.com/request.p?"
				+ "key=6647595e-2ae7-4007-bc76-77f8976e9f38&lc=ch&ft=1.0&text=";
		URL url=new URL(str+request);
		//URL url=new URL(new String((str+request).getBytes("GB2312"), "UTF-8"));
		HttpURLConnection connection=(HttpURLConnection) url.openConnection();
		connection.connect();
		String response = null;
		
		InputStream inputStream = connection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String msg = null;
		StringBuffer buffer=new StringBuffer();
		while ((msg = bufferedReader.readLine()) != null) {
			buffer.append(msg);
		}
		bufferedReader.close();
		inputStreamReader.close();
		// ÊÍ·Å×ÊÔ´
		inputStream.close();
		inputStream = null;
		connection.disconnect();
		JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
		response =jsonObject.getString("response");
		//System.out.println(jsonObject.getString("result"));
		//System.out.println(jsonObject.getString("response"));
		return response;
	}

}
