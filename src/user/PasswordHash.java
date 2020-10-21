import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public final class PasswordHash {
	// This is used to prevent the Password Hash Object to ever be instantiated 
	private PasswordHash() {}
	
	static String hashPassword(String password, byte[] salt) {
        String generatedPassword = null;
        try {
            // Create MD instance for MD5
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //Adds the salt to the beginning of the MD algorithm (that way passwords will not come out the same)
            md5.update(salt);
            //Appends the password to the MD then Hashes the passwords bytes into DECIMAL BYTES (to be converted)
            byte[] bytes = md5.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            // Convert into hex for standard formats
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toHexString(bytes[i]).substring(1));	
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
	}
	
    //Add salt
    static byte[] getSalt()
    {
        //Uses a secure random generator, using the SHA1PRNG algorithm, as this is a common one.
    	SecureRandom sr;
        try {
    	sr = SecureRandom.getInstance("SHA1PRNG");
        }
        catch(NoSuchAlgorithmException e) {
        	return null;
        }
        //Create array for salt
        byte[] salt = new byte[32];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }
	
}
