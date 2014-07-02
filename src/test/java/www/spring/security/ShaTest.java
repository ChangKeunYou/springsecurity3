package www.spring.security;

public class ShaTest {
	
	public static void main(String[]args){
		
		String hashedPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex("secret{jd}");
		System.out.println(hashedPassword);
	}

}
