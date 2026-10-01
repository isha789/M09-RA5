import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {

    static char[] alfabet = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
        'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S',
        'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

   
    static List<Character> permutacio = new ArrayList<>();


    public static char[] permutaAlfabet(char[] alfabet) {

     
        List<Character> llista = new ArrayList<>();

        for (int i = 0; i < alfabet.length; i++) {
            llista.add(alfabet[i]);
        }

    
        Collections.shuffle(llista);

     
        char[] alfabetPermutat = new char[llista.size()];

        for (int i = 0; i < llista.size(); i++) {
            alfabetPermutat[i] = llista.get(i);
        }

        return alfabetPermutat;
    }


    public static String xifraMonoAlfa(String cadena) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char lletra = cadena.charAt(i);

           
            char lletraMajuscula = Character.toUpperCase(lletra);

            boolean found = false;

            for (int j = 0; j < alfabet.length; j++) {

                if (lletraMajuscula == alfabet[j]) {

                  
                    char lletraXifrada = permutacio.get(j);

           
                    if (Character.isLowerCase(lletra)) {
                        lletraXifrada =
                            Character.toLowerCase(lletraXifrada);
                    }

                    resultat += lletraXifrada;

                    found = true;
                    break;
                }
            }

          
            if (!found) {
                resultat += lletra;
            }
        }

        return resultat;
    }


    public static String desxifraMonoAlfa(String cadena) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char lletra = cadena.charAt(i);

            char lletraMajuscula = Character.toUpperCase(lletra);

            boolean found = false;

            for (int j = 0; j < permutacio.size(); j++) {

                if (lletraMajuscula == permutacio.get(j)) {

                 
                    char lletraOriginal = alfabet[j];

            
                    if (Character.isLowerCase(lletra)) {
                        lletraOriginal =
                            Character.toLowerCase(lletraOriginal);
                    }

                    resultat += lletraOriginal;

                    found = true;
                    break;
                }
            }

  
            if (!found) {
                resultat += lletra;
            }
        }

        return resultat;
    }


    public static void main(String[] args) {

 
        char[] alfabetPermutat = permutaAlfabet(alfabet);

     
        permutacio.clear();

        for (int i = 0; i < alfabetPermutat.length; i++) {
            permutacio.add(alfabetPermutat[i]);
        }



        for (int i = 0; i < alfabet.length; i++) {
            System.out.print(alfabet[i] + " ");
        }

        System.out.println();


        for (int i = 0; i < alfabetPermutat.length; i++) {
            System.out.print(alfabetPermutat[i] + " ");
        }

        System.out.println();
        System.out.println();


 

        String test1 = "Test 01 àrbitre, coixí, Perímetre";
        String test2 = "Test 02 Taüll, DÍA, año";
        String test3 = "Test 03 Peça, Òrrius, Bòvila";


        System.out.println("Xifratge:");
        System.out.println();

        String xifrat1 = xifraMonoAlfa(test1);
        String xifrat2 = xifraMonoAlfa(test2);
        String xifrat3 = xifraMonoAlfa(test3);

        System.out.println(test1 + " -> " + xifrat1);
        System.out.println(test2 + " -> " + xifrat2);
        System.out.println(test3 + " -> " + xifrat3);


        System.out.println();
        System.out.println("Desxifratge:");
        System.out.println();

        System.out.println(
            xifrat1 + " -> " + desxifraMonoAlfa(xifrat1)
        );

        System.out.println(
            xifrat2 + " -> " + desxifraMonoAlfa(xifrat2)
        );

        System.out.println(
            xifrat3 + " -> " + desxifraMonoAlfa(xifrat3)
        );
    }
}