package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.nio.charset.StandardCharsets;

public class XifradorMonoalfabetic implements Xifrador {

    char[] alfabet = {
            'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
            'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
            'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S',
            'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    List<Character> permutacio = new ArrayList<>();

    public XifradorMonoalfabetic() {

        char[] alfabetPermutat = permutaAlfabet(alfabet);

        for (int i = 0; i < alfabetPermutat.length; i++) {
            permutacio.add(alfabetPermutat[i]);
        }
    }

    public char[] permutaAlfabet(char[] alfabet) {

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

    public String xifraMonoAlfa(String cadena) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char lletra = cadena.charAt(i);

            char lletraMajuscula = Character.toUpperCase(lletra);

            boolean found = false;

            for (int j = 0; j < alfabet.length; j++) {

                if (lletraMajuscula == alfabet[j]) {

                    char lletraXifrada = permutacio.get(j);

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

    public String desxifraMonoAlfa(String cadena) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char lletra = cadena.charAt(i);

            char lletraMajuscula = Character.toUpperCase(lletra);

            boolean found = false;

            for (int j = 0; j < permutacio.size(); j++) {

                if (lletraMajuscula == permutacio.get(j)) {

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

                if (clau != null) {
                    throw new ClauNoSuportada(
                        "Xifratxe monoalfabètic no suporta clau != null"
                    );
                }

        String resultat = xifraMonoAlfa(msg);

        return new TextXifrat(
                resultat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau)
            throws ClauNoSuportada {

                if (clau != null) {
                    throw new ClauNoSuportada(
                        "Xifratxe monoalfabètic no suporta clau != null"
                    );
                }

        String cadena = new String(
                xifrat.getBytes(),
                StandardCharsets.UTF_8);

        return desxifraMonoAlfa(cadena);
    }

}