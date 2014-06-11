/**
 * @author:ztwgod
 * @2014/4/21
 * verify the request
 */

package weChat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Sign {
	//token
	private static String token = "1234";
	/**
     *  Function:checkSignature
     *  @author ztwgod
     *  @param signature signature sent by wechat
     *  @param timestamp time
     *  @param nonce     random number
     *  @param echostr   echostr sent by wechat 
     *  @return
     */
    public  boolean  checkSignature(String signature,String timestamp,String nonce) {
        String[] ArrTmp = { token, timestamp, nonce };
        Arrays.sort(ArrTmp);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ArrTmp.length; i++) {
            sb.append(ArrTmp[i]);
        }
        // SHA
        String pwd = Encrypt(sb.toString());
        if (pwd.equals(signature))
            return true;
        else
        	return false;
    }
    /**
     *Function: Encrypt use SHA-1
     * @param strSrc
     * @return
     */
    private  String Encrypt(String strSrc) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(bt);
            strDes = bytes2Hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("´íÎó");
            return null;
        }
        return strDes;
    }
    /**
     *Function: bytes2Hex change the byte[] to String(Hex) 
     * @param bts
     * @return
     */
    private  String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}

