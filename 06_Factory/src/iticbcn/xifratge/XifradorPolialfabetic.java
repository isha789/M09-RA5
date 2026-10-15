package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.nio.charset.StandardCharsets;

public class XifradorPolialfabetic implements Xifrador {

    char[] alfabet = {
            'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É',
            'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
            'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S',
            'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    char[] alfabetPermutat;

    Random rnd;

    // String clauSecreta = "ITICBCN";

    public void initRandom(String clauSecreta) {
        long llavor = Long.parseLong(clauSecreta);
        rnd = new Random(llavor);
    }

    public void permutaAlfabet() {

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

    public String xifraPoliAlfa(String msg) {

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
                        lletraXifrada = Character.toLowerCase(lletraXifrada);
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

    public String desxifraPoliAlfa(String msgXifrat) {

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
                        lletraOriginal = Character.toLowerCase(lletraOriginal);
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

    @Override
    public TextXifrat xifra(String msg, String clau)
            throws ClauNoSuportada {

        if (clau == null) {
            throw new ClauNoSuportada("La clau no pot ser null");
        }

        try {
            initRandom(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada(
                "La clau per xifrat Polialfabètic ha de ser un String convertible a long"
            );
        }

        String resultat = xifraPoliAlfa(msg);

        return new TextXifrat(
                resultat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau)
            throws ClauNoSuportada {

        if (clau == null) {
            throw new ClauNoSuportada("La clau no pot ser null");
        }

        try {
            initRandom(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada(
                "La clau de Polialfabètic ha de ser un String convertible a long"
            );
        }

        String cadena = new String(
                xifrat.getBytes(),
                StandardCharsets.UTF_8);

        return desxifraPoliAlfa(cadena);
    }
}