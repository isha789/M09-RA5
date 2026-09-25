public class RotX {

    static char[] majuscules = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
        'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S',
        'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    static char[] minuscules = {
        'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'è', 'é',
        'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l',
        'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's',
        't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
    };


    public static String xifraRotX(String cadena, int desplacament) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char lletra = cadena.charAt(i);
            boolean found = false;

            // Majúscules
            for (int j = 0; j < majuscules.length; j++) {

                if (lletra == majuscules[j]) {

                    int novaPosicio =
                        (j + desplacament) % majuscules.length;

                    resultat += majuscules[novaPosicio];

                    found = true;
                    break;
                }
            }

            // Minúscules
            for (int j = 0; j < minuscules.length; j++) {

                if (lletra == minuscules[j]) {

                    int novaPosicio =
                        (j + desplacament) % minuscules.length;

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


    public static String desxifraRotX(String cadena, int desplacament) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char lletra = cadena.charAt(i);
            boolean found = false;

            // Majúscules
            for (int j = 0; j < majuscules.length; j++) {

                if (lletra == majuscules[j]) {

                    int novaPosicio =
                        (j - desplacament + majuscules.length)
                        % majuscules.length;

                    resultat += majuscules[novaPosicio];

                    found = true;
                    break;
                }
            }

            // Minúscules
            for (int j = 0; j < minuscules.length; j++) {

                if (lletra == minuscules[j]) {

                    int novaPosicio =
                        (j - desplacament + minuscules.length)
                        % minuscules.length;

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


    public static void forcaBrutaRotX(String cadenaXifrada) {

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


    public static void main(String[] args) {

        System.out.println("Xifrat");
        System.out.println("---------");

        System.out.println(
            "(0)-ABC                  => "
            + xifraRotX("ABC", 0)
        );

        System.out.println(
            "(2)-XYZ                  => "
            + xifraRotX("XYZ", 2)
        );

        System.out.println(
            "(4)-Hola, Mr. calçot     => "
            + xifraRotX("Hola, Mr. calçot", 4)
        );

        System.out.println(
            "(6)-Perdó, per tu què és? => "
            + xifraRotX("Perdó, per tu què és?", 6)
        );


        System.out.println();

        System.out.println("Desxifrat");
        System.out.println("---------");

        System.out.println(
            "(0)ABC                  => "
            + desxifraRotX("ABC", 0)
        );

        System.out.println(
            "(2)ZAÁ                  => "
            + desxifraRotX("ZAÁ", 2)
        );

        System.out.println(
            "(4)Ïqoc, Óú. ècoéqü     => "
            + desxifraRotX("Ïqoc, Óú. ècoéqü", 4)
        );

        System.out.println(
            "(6)Úiüht, úiü wx ùxí ìv? => "
            + desxifraRotX("Úiüht, úiü wx ùxí ìv?", 6)
        );


        System.out.println();

        forcaBrutaRotX("Úiüht, úiü wx ùxí ìv?");
    }
}
























