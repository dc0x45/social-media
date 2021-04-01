package interactive;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDAO {
	
    private final String url;
    private final String username;
    private final String password;
	
	public ProfileDAO(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public Profile getProfile(String uName) throws SQLException {
		final String sql = "SELECT * FROM profiles WHERE username = ?";
		
		Profile Profile = null;
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, uName);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
		    Profile = new Profile();
		    Profile.setAttr("username", rs.getString("username"));
		    Profile.setAttr("hashedPassword", rs.getString("hashedPassword"));
		    Profile.setAttr("first", rs.getString("first"));
		    Profile.setAttr("last", rs.getString("last"));
		    Profile.setAttr("college", rs.getString("college"));
		    Profile.setAttr("highschool", rs.getString("highschool"));
		    Profile.setAttr("dob", rs.getString("dob"));
		    Profile.setAttr("email", rs.getString("email"));
		    Profile.setAttr("phone_number", rs.getString("phone_number"));   
		    Profile.setAttr("basedPic", new String(rs.getBlob("basedPic").getBytes(1l, (int) rs.getBlob("basedPic").length())));
		    Profile.setAttr("lastLogin", rs.getString("lastLogin"));
		    Profile.setAttr("lastEdit", rs.getString("lastEdit"));
		    Profile.setAttr("hometown", rs.getString("hometown"));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return Profile;
	}
	
//	public List<Profile> getProfiles() throws SQLException {
//		final String sql = "SELECT * FROM Profiles ORDER BY Profile_id ASC";
//		
//		List<Profile> Profiles = new ArrayList<>();
//		Connection conn = getConnection();
//		Statement stmt = conn.createStatement();
//		ResultSet rs = stmt.executeQuery(sql);
//		
//		while (rs.next()) {
//			int id = rs.getInt("id");
//			String profile_username = rs.getString("username");
//			String profile_password = rs.getString("password");
//			String first = rs.getString("first_name");
//			String last = rs.getString("first_name");
//			String college = rs.getString("first_name");
//			String highschool = rs.getString("first_name");
//			String dob = rs.getString("date_birth");
//			String email = rs.getString("email_address");
//			long phone_number = rs.getLong("phone_number");
//			String basedPic = rs.getString("base64_pic");
//			String lastLogin = rs.getString("last_log");
//			String lastMod = rs.getString("last_mod");
//		   
//		    Profiles.add(new Profile(id, profile_username, profile_password, first, last, college, highschool, dob, email, phone_number, basedPic, lastLogin, lastMod)); 
//		}
//		
//		rs.close();
//		stmt.close();
//		conn.close();
//		
//		return Profiles;
//	}
	
	public boolean updateProfile(Profile Profile) throws SQLException, NoSuchAlgorithmException {
		final String sql = "UPDATE profiles SET "
				+ "hashedPassword = ?, "
				+ "first = ?, "
				+ "last = ?, "
				+ "college = ?, "
				+ "highschool = ?, "
				+ "dob = ?, "
				+ "email = ?, "
				+ "phone_number = ?, "
				+ "basedPic = ?, "
				+ "lastLogin = ?, "
				+ "lastEdit = ?, " 
				+ "hometown = ? "
				+ "WHERE username = ?";
			
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
			
		pstmt.setString(1, Profile.hasher(Profile.getAttr("hashedPassword")));
		pstmt.setString(2, Profile.getAttr("first"));
		pstmt.setString(3, Profile.getAttr("last"));
		pstmt.setString(4, Profile.getAttr("college"));
		pstmt.setString(5, Profile.getAttr("highschool"));
		pstmt.setString(6, Profile.getAttr("dob"));
		pstmt.setString(7, Profile.getAttr("email"));
		pstmt.setString(8, Profile.getAttr("phone_number"));
		pstmt.setString(9, Profile.getAttr("basedPic"));
		pstmt.setString(10, Profile.getAttr("lastLogin"));
		pstmt.setString(11, Profile.getAttr("lastEdit"));
		pstmt.setString(12, Profile.getAttr("hometown"));
		pstmt.setString(13, Profile.getAttr("username"));
		int affected = pstmt.executeUpdate();
			
		pstmt.close();
		conn.close();
			
		return affected == 1;
	}
  
	private Connection getConnection() throws SQLException {
		final String driver = "com.mysql.cj.jdbc.Driver";
    
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(url, username, password);
	}
}