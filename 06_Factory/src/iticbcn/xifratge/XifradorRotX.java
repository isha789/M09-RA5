package iticbcn.xifratge;
import java.nio.charset.StandardCharsets;

public class XifradorRotX implements Xifrador{

   char[] majuscules = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
        'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S',
        'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    char[] minuscules = {
        'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'è', 'é',
        'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l',
        'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's',
        't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
    };


    public  String xifraRotX(String cadena, int desplacament) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char lletra = cadena.charAt(i);
            boolean found = false;

            // Majúscules
            for (int j = 0; j < majuscules.length; j++) {

                if (lletra == majuscules[j]) {

                    int novaPosicio =
                    Math.floorMod(j + desplacament, majuscules.length);

                    resultat += majuscules[novaPosicio];

                    found = true;
                    break;
                }
            }

            // Minúscules
            for (int j = 0; j < minuscules.length; j++) {

                if (lletra == minuscules[j]) {

                    int novaPosicio =
                    Math.floorMod(j + desplacament, minuscules.length);

                    resultat += minuscules[novaPosicio];

                    found = true;
                    break;
                }
            }

            // Espais i signes de puntuació
            if (!found) {
                resultat += lletra;
            }
        }

        return resultat;
    }


    public  String desxifraRotX(String cadena, int desplacament) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char lletra = cadena.charAt(i);
            boolean found = false;

            // Majúscules
            for (int j = 0; j < majuscules.length; j++) {

                if (lletra == majuscules[j]) {

                    int novaPosicio =
                    Math.floorMod(j - desplacament, majuscules.length);
                    resultat += majuscules[novaPosicio];

                    found = true;
                    break;
                }
            }

            // Minúscules
            for (int j = 0; j < minuscules.length; j++) {

                if (lletra == minuscules[j]) {

                    int novaPosicio =
                    Math.floorMod(j - desplacament, minuscules.length);

                    resultat += minuscules[novaPosicio];

                    found = true;
                    break;
                }
            }

            // Espais i signes de puntuació
            if (!found) {
                resultat += lletra;
            }
        }

        return resultat;
    }


    public  void forcaBrutaRotX(String cadenaXifrada) {

        System.out.println("Missatge xifrat: " + cadenaXifrada);
        System.out.println("--------------------------------");

        for (int desplacament = 0;
             desplacament < minuscules.length;
             desplacament++) {

            System.out.println(
                "(" + desplacament + ")->"
                + desxifraRotX(cadenaXifrada, desplacament)
            );
        }
    }


   
    @Override
public TextXifrat xifra(String msg, String clau)
        throws ClauNoSuportada {

            int desplacament;

            try {
                desplacament = Integer.parseInt(clau);
            
                if (desplacament < 0 || desplacament > 40) {
                    throw new ClauNoSuportada(
                        "Clau de RotX ha de ser un sencer de 0 a 40"
                    );
                }
            
            } catch (NumberFormatException e) {
                throw new ClauNoSuportada(
                    "Clau de RotX ha de ser un sencer de 0 a 40"
                );
            }

    String resultat = xifraRotX(msg, desplacament);

    return new TextXifrat(
        resultat.getBytes(StandardCharsets.UTF_8)
    );
}

@Override
public String desxifra(TextXifrat xifrat, String clau)
        throws ClauNoSuportada {

    int desplacament;

    try {
        desplacament = Integer.parseInt(clau);

        if (desplacament < 0 || desplacament > 40) {
            throw new ClauNoSuportada(
                "Clau de RotX ha de ser un sencer de 0 a 40"
            );
        }

    } catch (NumberFormatException e) {
        throw new ClauNoSuportada(
            "Clau de RotX ha de ser un sencer de 0 a 40"
        );
    }

    // Check encrypted text after validating the key
    if (xifrat == null) {
        return null;
    }

    String cadena = new String(
        xifrat.getBytes(),
        StandardCharsets.UTF_8
    );

    return desxifraRotX(cadena, desplacament);
}

}



