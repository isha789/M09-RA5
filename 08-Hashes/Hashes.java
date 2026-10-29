import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class Hashes {
    
    int npass = 0;
    public String getSHA512AmbSalt(String pw, String salt){
        try {
            String text = pw + salt;
    
            MessageDigest md = MessageDigest.getInstance("SHA-512");
    
            byte[] bytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
    
            HexFormat hex = HexFormat.of();
    
            String hash = hex.formatHex(bytes);
    
            return hash;
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String getPBKDF2AmbSalt(String pw, String salt){
        try {
            PBEKeySpec spec = new PBEKeySpec(
                pw.toCharArray(),
                salt.getBytes(StandardCharsets.UTF_8),
                10000,
                128
            );
    
            SecretKeyFactory factory =
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    
            byte[] bytes = factory.generateSecret(spec).getEncoded();
    
            HexFormat hex = HexFormat.of();
    
            String hash = hex.formatHex(bytes);
    
            return hash;
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    
public String forcaBruta(String alg, String hash, String salt) {

    String charset = "abcdefABCDEF1234567890!";

    npass = 0;

    for (int i = 0; i < charset.length(); i++) {
        String p1 = "" + charset.charAt(i);

        if (comprovar(p1, alg, hash, salt)) {
            return p1;
        }

        for (int j = 0; j < charset.length(); j++) {
            String p2 = p1 + charset.charAt(j);

            if (comprovar(p2, alg, hash, salt)) {
                return p2;
            }

            for (int k = 0; k < charset.length(); k++) {
                String p3 = p2 + charset.charAt(k);

                if (comprovar(p3, alg, hash, salt)) {
                    return p3;
                }

                for (int l = 0; l < charset.length(); l++) {
                    String p4 = p3 + charset.charAt(l);

                    if (comprovar(p4, alg, hash, salt)) {
                        return p4;
                    }

                    for (int m = 0; m < charset.length(); m++) {
                        String p5 = p4 + charset.charAt(m);

                        if (comprovar(p5, alg, hash, salt)) {
                            return p5;
                        }

                        for (int n = 0; n < charset.length(); n++) {
                            String p6 = p5 + charset.charAt(n);

                            if (comprovar(p6, alg, hash, salt)) {
                                return p6;
                            }
                        }
                    }
                }
            }
        }
    }

    return null;
}

private boolean comprovar(String password, String alg,
                         String hash, String salt) {

    npass++;

    String hashIntent;

    if (alg.equals("SHA-512")) {
        hashIntent = getSHA512AmbSalt(password, salt);

    } else if (alg.equals("PBKDF2")) {
        hashIntent = getPBKDF2AmbSalt(password, salt);

    } else {
        return false;
    }

    return hashIntent.equals(hash);
}

  //getInterval method for getting days, hours, minutes, seconds and milliseconds
    public String getInterval(long t1, long t2){
        long diferencia = t2 - t1;

        long dies = diferencia / (1000 * 60 * 60 * 24);
        diferencia = diferencia % (1000 * 60 * 60 * 24);
    
        long hores = diferencia / (1000 * 60 * 60);
        diferencia = diferencia % (1000 * 60 * 60);
    
        long minuts = diferencia / (1000 * 60);
        diferencia = diferencia % (1000 * 60);
    
        long segons = diferencia / 1000;
        long millis = diferencia % 1000;
    
        return dies + " dies / " +
               hores + " hores / " +
               minuts + " minuts / " +
               segons + " segons / " +
               millis + " millis";
    }

    
public static void main(String[] args) throws Exception {

    String salt = "qpoweiruañslkdfjz";
    String pw = "aaabF!";

    Hashes h = new Hashes();

    String[] aHashes = {
        h.getSHA512AmbSalt(pw, salt),
        h.getPBKDF2AmbSalt(pw, salt)
    };

    String pwTrobat = null;

    String[] algorismes = {"SHA-512", "PBKDF2"};

    for (int i = 0; i < aHashes.length; i++) {

        System.out.printf("==============================%n");
        System.out.printf("Algorisme: %s%n", algorismes[i]);
        System.out.printf("Hash: %s%n", aHashes[i]);
        System.out.printf("------------------------%n");
        System.out.printf("-- Inici de força bruta --%n");

        long t1 = System.currentTimeMillis();

        pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);

        long t2 = System.currentTimeMillis();

        System.out.printf("Pass : %s%n", pwTrobat);
        System.out.printf("Provats: %d%n", h.npass);
        System.out.printf("Temps : %s%n", h.getInterval(t1, t2));
        System.out.printf("------------------------%n%n");
    }
}

}
