package util.characterUtil;

import java.io.UnsupportedEncodingException;

public class StringUtil {
    /**
     * 将字符串每个字符后面追加一个字符串
     * @param str
     * @param ch
     * @return
     */
    public static String insertChar(String str, String ch) {
        StringBuffer buffStr = new StringBuffer();
        int strLen = str.length();
        for(int i = 0; i < strLen; i++) {
            buffStr.append(str.charAt(i));
            buffStr.append(ch);
        }
        return buffStr.toString();
    }
    public static String changeChar(String s) throws UnsupportedEncodingException {
        if (s == null){
            return new String("null");
        }
        byte b[] = s.getBytes("ISO-8859-1");
        return (new String(b, "utf-8"));
    }

}
