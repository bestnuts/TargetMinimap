package me.bestnuts.api.minimap.text;

public class MinimapSeparatorBuilder {

    private static final char[] NSF = {
            '\uF801', '\uF802', '\uF803', '\uF804',
            '\uF805', '\uF806', '\uF807', '\uF808'
    };

    private static final char[] PSF = {
            '\uF811', '\uF812', '\uF813', '\uF814',
            '\uF815', '\uF816', '\uF817', '\uF818'
    };

    private final int imageWidth;
    private final int xOffset;

    private MinimapSeparatorBuilder(int imageWidth, int xOffset) {
        this.imageWidth = imageWidth;
        this.xOffset = xOffset;
    }

    public static MinimapSeparatorBuilder of(int imageWidth, int xOffset) {
        return new MinimapSeparatorBuilder(imageWidth, xOffset);
    }

    public String pre() {
        return toSpaceFont(-(xOffset + 1));
    }

    public String post() {
        return toSpaceFont(-(imageWidth - xOffset));
    }

    public String wrap(String font) {
        return pre() + font + post();
    }

    static String toSpaceFont(int value) {
        if (value == 0) return "";

        char[] font = value < 0 ? NSF : PSF;
        int remaining = Math.abs(value);

        StringBuilder sb = new StringBuilder();

        while (remaining > 255) {
            sb.append(font[7]);
            remaining -= 128;
        }

        for (int i = 7; i >= 0; i--) {
            int bit = 1 << i;
            if (remaining >= bit) {
                sb.append(font[i]);
                remaining -= bit;
            }
        }

        return sb.toString();
    }
}