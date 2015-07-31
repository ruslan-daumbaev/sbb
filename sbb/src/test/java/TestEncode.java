import org.junit.Test;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import java.security.MessageDigest;

public class TestEncode {

    @Test
    public void encodeTest(){

        StandardPasswordEncoder encoder = new StandardPasswordEncoder();
        //String pass = encoder.encode("12345");
        String pass = sha256("12345"+"{user1}");
        System.out.println(pass);
    }

    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
