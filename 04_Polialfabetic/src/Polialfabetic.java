import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {

  
    static char[] alfabet = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
        'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S',
        'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

  
    static char[] alfabetPermutat;

    
    static Random rnd;


    static String clauSecreta = "ITICBCN";

    public static void initRandom(String clauSecreta) {

        rnd = new Random(clauSecreta.hashCode());
    }

    public static void permutaAlfabet() {

       
        List<Character> lista = new ArrayList<>();

        for (int i = 0; i < alfabet.length; i++) {
            lista.add(alfabet[i]);
        }
        
        Collections.shuffle(lista, rnd);

      
        alfabetPermutat = new char[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            alfabetPermutat[i] = lista.get(i);
        }
    }


    public static String xifraPoliAlfa(String msg) {

        String resultat = "";

        for (int i = 0; i < msg.length(); i++) {

            char lletra = msg.charAt(i);

            permutaAlfabet();

            char lletraMajuscula = Character.toUpperCase(lletra);

            boolean found = false;

            for (int j = 0; j < alfabet.length; j++) {

                if (lletraMajuscula == alfabet[j]) {

                   
                    char lletraXifrada = alfabetPermutat[j];

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

    public static String desxifraPoliAlfa(String msgXifrat) {

        String resultat = "";

        for (int i = 0; i < msgXifrat.length(); i++) {

            char lletra = msgXifrat.charAt(i);

          
            permutaAlfabet();

            char lletraMajuscula = Character.toUpperCase(lletra);

            boolean found = false;

           
            for (int j = 0; j < alfabetPermutat.length; j++) {

                if (lletraMajuscula == alfabetPermutat[j]) {

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

        String msgs[] = {
            "Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };

        String msgsXifrats[] = new String[msgs.length];


        System.out.println("Xifratge:\n---------");

        for (int i = 0; i < msgs.length; i++) {

           
            initRandom(clauSecreta);

            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);

            System.out.printf(
                "%-34s -> %s%n",
                msgs[i],
                msgsXifrats[i]
            );
        }


        System.out.println();
        System.out.println("Desxifratge:\n-----------");

        for (int i = 0; i < msgs.length; i++) {

    
            initRandom(clauSecreta);

            String msg =
                desxifraPoliAlfa(msgsXifrats[i]);

            System.out.printf(
                "%-34s -> %s%n",
                msgsXifrats[i],
                msg
            );
        }
    }
}