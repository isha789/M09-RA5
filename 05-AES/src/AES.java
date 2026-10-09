
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static void generaIv() {

        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

    }

    public static SecretKeySpec generaHash(String clau)
            throws Exception {

        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);

        byte[] hash = digest.digest(
                clau.getBytes(StandardCharsets.UTF_8)
        );

        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }


    public static byte[] xifraAES(String msg, String clau)
            throws Exception {

    
        byte[] bytesMsg = msg.getBytes(StandardCharsets.UTF_8);

        generaIv();
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        SecretKeySpec key = generaHash(clau);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

        byte[] msgXifrat = cipher.doFinal(bytesMsg);

        byte[] resultat = new byte[iv.length + msgXifrat.length];

        System.arraycopy(iv, 0, resultat, 0, iv.length);

        System.arraycopy(
                msgXifrat, 0,
                resultat, iv.length,
                msgXifrat.length
        );

        return resultat;
    }

    public static byte[] extreureIv(byte[] bIvMsgXifrat) {

        return Arrays.copyOfRange(bIvMsgXifrat, 0, MIDA_IV);

    }

    public static byte[] getBytesXifrats(byte[] bIvMsgXifrat) {

        return Arrays.copyOfRange(
                bIvMsgXifrat,
                MIDA_IV,
                bIvMsgXifrat.length
        );

    }

    public static String desxifraAES(byte[] bIvMsgXifrat, String clau)
            throws Exception {

    
        byte[] ivExtret = extreureIv(bIvMsgXifrat);
        IvParameterSpec ivSpec = new IvParameterSpec(ivExtret);

       
        byte[] msgXifrat = getBytesXifrats(bIvMsgXifrat);


        SecretKeySpec key = generaHash(clau);

 
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

        byte[] bytesDesxifrats = cipher.doFinal(msgXifrat);
  
        return new String(bytesDesxifrats, StandardCharsets.UTF_8);

    }
    public static void main(String[] args) {

        String msgs[] = {
                "Lorem ipsum dicet",
                "Hola Andrés cómo está tu cuñado",
                "Àgora illa Òtto"
        };

        for (int i = 0; i < msgs.length; i++) {

            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";

            try {

                bXifrats = xifraAES(msg, CLAU);

                desxifrat = desxifraAES(bXifrats, CLAU);

            } catch (Exception e) {

                System.err.println(
                        "Error de xifrat: " + e.getLocalizedMessage()
                );

            }

            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats, StandardCharsets.UTF_8));
            System.out.println("DEC: " + desxifrat);
        }
    }
}
