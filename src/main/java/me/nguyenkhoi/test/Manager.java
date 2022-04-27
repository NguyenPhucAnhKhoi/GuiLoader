package me.nguyenkhoi.test;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    public static String colorize(String input) {
        input = ChatColor.translateAlternateColorCodes('&', input);
        return input;
    }
    public static List<String> lorecolor(List<String> input) {
        List<String> output = new ArrayList<>();
        for (String string : input) {
            output.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        return output;
    }
    public static boolean isInt(String input) {
        String regex = "-?\\d+";
        if (input == null) {
            return false;
        }
        return input.matches(regex);
    }
}
