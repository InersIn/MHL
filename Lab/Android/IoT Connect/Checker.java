import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public final class Checker {
    public static final Checker INSTANCE = new Checker();
    private static final String ALGORITHM = "AES";
    private static final String DS = "OSnaALIWUkpOziVAMycaZQ==";

    public static void main(String[] args) {
        for (int i = 0; i <= 999; i++) {
            int pin = i;
            boolean result = Checker.INSTANCE.check_key(pin);
            if (result) {
                System.out.printf("Found valid key: %03d%n", pin);
                break;
            }
        }
    }

    private Checker() {}

    public boolean check_key(int key) {
        try {
            return "master_on".equals(decrypt(DS, key));
        } catch (BadPaddingException e) {
            return false;
        }
    }

    public String decrypt(String ds, int key) throws BadPaddingException {
        try {
            SecretKeySpec secretKey = generateKey(key);
            Cipher cipher = Cipher.getInstance(ALGORITHM + "/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(ds);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (BadPaddingException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private SecretKeySpec generateKey(int staticKey) {
        byte[] keyBytes = new byte[16];
        byte[] staticKeyBytes = String.valueOf(staticKey).getBytes(StandardCharsets.UTF_8);
        System.arraycopy(staticKeyBytes, 0, keyBytes, 0, Math.min(staticKeyBytes.length, keyBytes.length));
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }
}
