package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];
    // private final String CLAU = "LaClauSecretaQueVulguis";

    public void generaIv() {

        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

    }

    public SecretKeySpec generaHash(String clau)
            throws Exception {

        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);

        byte[] hash = digest.digest(
                clau.getBytes(StandardCharsets.UTF_8));

        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }

    public byte[] xifraAES(String msg, String clau)
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
                msgXifrat.length);

        return resultat;
    }

    public byte[] extreureIv(byte[] bIvMsgXifrat) {

        return Arrays.copyOfRange(bIvMsgXifrat, 0, MIDA_IV);

    }

    public byte[] getBytesXifrats(byte[] bIvMsgXifrat) {

        return Arrays.copyOfRange(
                bIvMsgXifrat,
                MIDA_IV,
                bIvMsgXifrat.length);

    }

    public String desxifraAES(byte[] bIvMsgXifrat, String clau)
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

    @Override
    public TextXifrat xifra(String msg, String clau)
            throws ClauNoSuportada {

        if (clau == null) {
            throw new ClauNoSuportada("La clau no pot ser null");
        }

        try {

            byte[] resultat = xifraAES(msg, clau);

            return new TextXifrat(resultat);

        } catch (Exception e) {

            throw new ClauNoSuportada(
                    "Error en xifrar: " + e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau)
            throws ClauNoSuportada {

        if (clau == null) {
            throw new ClauNoSuportada("La clau no pot ser null");
        }

        try {

            byte[] dades = xifrat.getBytes();

            return desxifraAES(dades, clau);

        } catch (Exception e) {

            throw new ClauNoSuportada(
                    "Error en desxifrar: " + e.getMessage());
        }
    }
}
