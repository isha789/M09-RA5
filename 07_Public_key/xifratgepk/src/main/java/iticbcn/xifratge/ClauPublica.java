package iticbcn.xifratge;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;

public class ClauPublica {

    public KeyPair generaParellClausRSA() throws Exception {

        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");

        generador.initialize(2048);

        KeyPair parellClaus = generador.generateKeyPair();

        return parellClaus;
    }
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");
    
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
    
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);
    
        byte[] msgXifrat = cipher.doFinal(msgBytes);
    
        return msgXifrat;
    }
    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) {

        try {
            Cipher cipher = Cipher.getInstance("RSA");
    
            cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
    
            byte[] msgBytes = cipher.doFinal(msgXifrat);
    
            String msgDesxifrat = new String(msgBytes, StandardCharsets.UTF_8);
    
            return msgDesxifrat;
    
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting message", e);
        }
    }
}