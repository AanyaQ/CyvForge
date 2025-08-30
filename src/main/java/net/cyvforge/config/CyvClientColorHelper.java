package net.cyvforge.config;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;

public class CyvClientColorHelper {
    public static CyvClientColor color1;
    public static CyvClientColor color2;
    public static ArrayList<CyvClientColor> colors;
    public static String[] colorStrings;

    static {
        colors = new ArrayList<>();
        colors.add(new CyvClientColor("dark_red", EnumChatFormatting.DARK_RED));
        colors.add(new CyvClientColor("red", EnumChatFormatting.RED));
        colors.add(new CyvClientColor("gold", EnumChatFormatting.GOLD));
        colors.add(new CyvClientColor("yellow", EnumChatFormatting.YELLOW));
        colors.add(new CyvClientColor("dark_green", EnumChatFormatting.DARK_GREEN));
        colors.add(new CyvClientColor("green", EnumChatFormatting.GREEN));
        colors.add(new CyvClientColor("aqua", EnumChatFormatting.AQUA));
        colors.add(new CyvClientColor("dark_aqua", EnumChatFormatting.DARK_AQUA));
        colors.add(new CyvClientColor("dark_blue", EnumChatFormatting.DARK_BLUE));
        colors.add(new CyvClientColor("blue", EnumChatFormatting.BLUE));
        colors.add(new CyvClientColor("light_purple", EnumChatFormatting.LIGHT_PURPLE));
        colors.add(new CyvClientColor("dark_purple", EnumChatFormatting.DARK_PURPLE));
        colors.add(new CyvClientColor("white", EnumChatFormatting.WHITE));
        colors.add(new CyvClientColor("gray", EnumChatFormatting.GRAY));
        colors.add(new CyvClientColor("dark_gray", EnumChatFormatting.DARK_GRAY));
        colors.add(new CyvClientColor("black", EnumChatFormatting.BLACK));

        colorStrings = colors.stream().map(c -> c.name).toArray(String[]::new);

    }

    public static void checkColor(String c1, String c2) {
        color1 = colors.get(6);
        color2 = colors.get(12);

        for (CyvClientColor c : colors) {
            if (c.name.equals(c1)) color1 = c;
            if (c.name.equals(c2)) color2 = c;
        }

        CyvClientConfig.set("color1", color1.name);
        CyvClientConfig.set("color2", color2.name);
    }

    public static boolean setColor1(String s) {
        for (CyvClientColor c : colors) {
            if (c.name.equals(s)) {
                color1 = c;
                CyvClientConfig.set("color1", color1.name);
                return true;
            }
        }
        return false;
    }

    public static boolean setColor2(String s) {
        for (CyvClientColor c : colors) {
            if (c.name.equals(s)) {
                color2 = c;
                CyvClientConfig.set("color2", color2.name);
                return true;
            }
        }
        return false;
    }

    public static class CyvClientColor {
        public final String name;
        public final EnumChatFormatting formatting;

        CyvClientColor(String name, EnumChatFormatting formatting) {
            this.name = name;
            this.formatting = formatting;
        }

        public long getDrawColor() {
            int color = Minecraft.getMinecraft().fontRendererObj.getColorCode(formatting.toString().charAt(1));
            return color | 0xFF000000;
        }

        public String getChatFormatting() {
            return formatting.toString();
        }
    }

}