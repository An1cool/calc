package cac;

import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static String[] s = new String[3];
    static Integer[] v = new Integer[3];

    static int resArab;
    static String resRim;

    static List<String> arabList = new ArrayList<>();
    final static List<String> rimList = new ArrayList<>() {{
        add("I");
        add("II");
        add("III");
        add("IV");
        add("V");
        add("VI");
        add("VII");
        add("VIII");
        add("IX");
        add("X");
    }};

    final static Map<String, Integer> rimMap = new LinkedHashMap<>() {{
        put("C", 100);
        put("XC", 90);
        put("LXXX", 80);
        put("LXX", 70);
        put("LX", 60);
        put("L", 50);
        put("XL", 40);
        put("XXX", 30);
        put("XX", 20);
        put("X", 10);
        put("IX", 9);
        put("VIII", 8);
        put("VII", 7);
        put("VI", 6);
        put("V", 5);
        put("IV", 4);
        put("III", 3);
        put("II", 2);
        put("I", 1);
    }};

    public static void main(String[] args) throws Exception {

        for (int i = 0; i <= 10; i++) {
            arabList.add(String.valueOf(i));
        }

        String input = sc.nextLine();

        System.out.println(calc(input));
    }

    public static void arabCalc() {
        for (int i = 0; i < 3; i++) {
            if (i == 0 || i == 2) {
                v[i] = Integer.parseInt(s[i]);
            }
        }

        switch (s[1]) {
            case "+" -> resArab = v[0] + v[2];
            case "-" -> resArab = v[0] - v[2];
            case "/" -> resArab = v[0] / v[2];
            case "*" -> resArab = v[0] * v[2];
        }

    }

    public static void rimCalc() throws Exception {
        if (!rimMap.containsKey(s[0]) || !rimMap.containsKey(s[2])) {
            throw new Exception("Допустимы значения от I до X");
        }
        for (int i = 0; i < 3; i++) {
            for (Map.Entry<String, Integer> entry : rimMap.entrySet()) {
                if (s[i].equals(entry.getKey())) {
                    v[i] =  entry.getValue();
                }
            }
        }

        switch (s[1]) {
            case "+" -> resArab = v[0] + v[2];
            case "-" -> resArab = v[0] - v[2];
            case "/" -> resArab = v[0] / v[2];
            case "*" -> resArab = v[0] * v[2];
        }
        for (Map.Entry<String, Integer> entry : rimMap.entrySet()) {
            if (resArab >= 10) {
                if (resArab - resArab % 10 == entry.getValue()) {
                    resRim = entry.getKey();
                }
                if (resArab % 10 == entry.getValue()) {
                    resRim += entry.getKey();
                }
            } else {
                if (resArab == entry.getValue()) {
                    resRim = entry.getKey();
                }
            }
        }
    }

    public static String calc(String input) throws Exception {
        s = input.split(" ");

        if (s.length > 3) {
            throw new Exception("Неверное выражение. Можно ввести только два числа. Пример: 1 + 4, X - II");
        }

        if (arabList.contains(s[0]) && arabList.contains(s[2])) {
            arabCalc();
            return String.valueOf(resArab);
        } else if ((arabList.contains(s[0]) && rimList.contains(s[2])) || (rimList.contains(s[0]) && arabList.contains(s[2]))) {
            throw new Exception("Неверное выражение. Числа/цифры должны находиться в одной системе счисления. Примеры: 1 + 4, X - II");
        } else {
            rimCalc();
            return resRim;
        }
    }
}

