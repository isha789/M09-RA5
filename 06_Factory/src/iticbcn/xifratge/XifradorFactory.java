package iticbcn.xifratge;

public class XifradorFactory {

    public static Xifrador getXifrador(String tipus) {

        if (tipus == null) {
            return null;
        }

        if (tipus.equalsIgnoreCase("RotX")) {
            return new XifradorRotX();

        } else if (tipus.equalsIgnoreCase("Monoalfabetic")) {
            return new XifradorMonoalfabetic();

        } else if (tipus.equalsIgnoreCase("Polialfabetic")) {
            return new XifradorPolialfabetic();

        } else if (tipus.equalsIgnoreCase("AES")) {
            return new XifradorAES();
        }

        return null;
    }
}