package interactive;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Profile {
	public HashMap<String, String> details;
	
	public Profile() {
    	this.details  = new HashMap<String, String>();
	}
	public String getAttr(String attr) {
		String returnMe = "";
		try {
			returnMe = details.get(attr);
		} catch(Exception e) {
			System.out.println(e);
		}
		return returnMe;
	}
	public boolean setAttr(String attr, String val) {
		try {
			details.put(attr, val);
			return true;
		} catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}
	public boolean loginUser(String username, String password) {
		System.out.println("login check");

		if(username.equals(this.details.get("username"))){
			try {
				System.out.println(hasher(password));
				if(hasher(password).equals(this.details.get("hashedPassword"))){
					return true;
				} else return false;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
    public String hasher(String input) throws NoSuchAlgorithmException {
        MessageDigest mD = MessageDigest.getInstance("SHA-256");
        byte[] result = mD.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}