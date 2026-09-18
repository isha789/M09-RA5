public class Rot13 {

    static char[] majuscules = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M',
        'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T',
        'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    static char[] minuscules = {
        'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'è', 'é',
        'f', 'g', 'h', 'i', 'í', 'í', 'ï', 'j', 'k', 'l', 'm',
        'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't',
        'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
    };

    public static String xifraRot13(String cadena) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            boolean found = false;
            for (int j = 0; j < majuscules.length; j++) {
                if (lletra == majuscules[j]) {
                    int novaPosicio = (j + 13) % majuscules.length;
                     resultat += majuscules[novaPosicio];
                     found = true;
                    break;
                }
            }
            for (int j = 0; j < minuscules.length; j++) {
                if (lletra == minuscules[j]) {
                    int novaPosicio = (j + 13) % minuscules.length;
                    resultat += minuscules[novaPosicio];
                    found = true;
                    break;
                }
            }
            if (!found){
                resultat += lletra;
            }
        }
    
        return resultat;
    }

    public static String desxifraRot13(String cadena) {

        String resultat = "";
    
        for (int i = 0; i < cadena.length(); i++) {
    
            char lletra = cadena.charAt(i);
            boolean found = false;
    
            // Majúscules
            for (int j = 0; j < majuscules.length; j++) {
    
                if (lletra == majuscules[j]) {
    
                    int novaPosicio = (j - 13 + majuscules.length) % majuscules.length;
    
                    resultat += majuscules[novaPosicio];
    
                    found = true;
                    break;
                }
            }
    
            // Minúscules
            for (int j = 0; j < minuscules.length; j++) {
    
                if (lletra == minuscules[j]) {
    
                    int novaPosicio = (j - 13 + minuscules.length) % minuscules.length;
    
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
    public static void main(String[] args) {

            System.out.println("Xifrat");
            System.out.println("--------");
        
            System.out.println("ABC                  => " + xifraRot13("ABC"));
            System.out.println("XYZ                  => " + xifraRot13("XYZ"));
            System.out.println("Hola, Mr. calçot     => " + xifraRot13("Hola, Mr. calçot"));
            System.out.println("Perdó, per tu què és? => " + xifraRot13("Perdó, per tu què és?"));
        
            System.out.println();
        
            System.out.println("Desxifrat");
            System.out.println("----------");
        
            System.out.println("IÏJ                  => " + desxifraRot13("IÏJ"));
            System.out.println("FGH                  => " + desxifraRot13("FGH"));
            System.out.println("Òwüi, Ùá. jiúkwb     => " + desxifraRot13("Òwüi, Ùá. jiúkwb"));
            System.out.println("Zmálx, zmá bc acñ nà? => " + desxifraRot13("Zmálx, zmá bc acñ nà?"));
        }
    }
