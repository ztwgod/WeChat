package weChat;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;














import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import weChat.pojo.AccessToken;


public class Time {
	/*public static void main(String arg[])
	{
		try {
			URL url = new URL("http://myxbwechat.duapp.com/WeChat/verify");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			//发送域信息
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "8859_1");
			out.write("<xml>"+
      			  "<ToUserName><![CDATA[aaa]]></ToUserName>"+
      			  "<FromUserName><![CDATA[bbb]]></FromUserName>"+
      			  "<CreateTime><![CDATA["+new Date().getTime()+"]]></CreateTime>"+
      			  "<MsgType><![CDATA[text]]></MsgType>"+
      			  "<Content><![CDATA[你好]]></Content>"+
      			  "<MsgId>1234567890123456</MsgId>"+
				  "</xml>");//这里组织域信息
			out.flush();
			out.close();
			//获取返回数据	
			InputStream in=connection.getInputStream();
			BufferedReader reader = new BufferedReader(
	                    new InputStreamReader(in));
			String a;
			  while ((a=reader.readLine())!=null)
			  {
				  System.out.println(a);
			  }
		}
			catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}*/
	public static void main(String arg[]) throws UnsupportedEncodingException {
	String userName="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		File f = new File(userName + ".png");
		if (!f.exists()) {
			String imagePath = userName + ".png";
			File file = new File(imagePath);

			QRCodeWriter writer = new QRCodeWriter();
			try {
				// 生成二维码
				BitMatrix matrix = writer.encode(userName,
						BarcodeFormat.QR_CODE, 200, 200);
				MatrixToImageWriter.writeToFile(matrix, "png", file);
				// 读取二维码

			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		
	}

}
