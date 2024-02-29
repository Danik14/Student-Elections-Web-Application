package gigachads.noenemies.diploma;

import gigachads.noenemies.diploma.domain.model.UserId;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.UUID;

@Slf4j
public class HelperClass {
    public static UserId getUserIdByOauth2Principal(Principal principal){
        return UserId.of(generateUUIDFromString(principal.getName()));
    }

    public static UUID generateUUIDFromString(String input) {
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Add the input string to the digest
            md.update(input.getBytes());
            // Get the hash's bytes
            byte[] bytes = md.digest();
            // Convert the hash to a BigInteger
            BigInteger bigInt = new BigInteger(1, bytes);
            // Create a UUID from the BigInteger
            return new UUID(bigInt.longValue(), 0);
        } catch (NoSuchAlgorithmException e) {
            log.error("Error generating uuid from string input: {}", e.toString());
        }
        return null;
    }
}
