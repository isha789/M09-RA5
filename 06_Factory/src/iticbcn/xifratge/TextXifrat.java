
package iticbcn.xifratge;

import java.util.Arrays;


import java.nio.charset.StandardCharsets;

public class TextXifrat {

    private byte[] dades;

    public TextXifrat(byte[] dades) {
        this.dades = dades.clone();
    }

    public byte[] getBytes() {
        return dades.clone();
    }

    @Override
    public String toString() {
        return new String(dades, StandardCharsets.UTF_8);
    }
}
