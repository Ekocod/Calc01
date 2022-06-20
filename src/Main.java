import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println("Введите данные (первым аргументом выражения должна быть строка): ");
        String input = sc.nextLine();
        System.out.println("Результат:");
        if (CalcMain.calc(input).length() > 40) {
            System.out.println("\"" + CalcMain.calc(input).substring(0,40) + "..." + "\"");
        } else System.out.println("\"" + CalcMain.calc(input) + "\"");
    }
}

class CalcMain {
    public static String calc(String input) throws Exception {
        char oper;
        String res;
        String[] mas;
        if (input.contains(" + ")) {
            mas = input.split("[+]");
            oper = '+';
        } else if (input.contains(" - ")) {
            mas = input.split(" - ");
            oper = '-';
        } else if (input.contains(" * ")) {
            mas = input.split("[*]");
            oper = '*';
        } else if (input.contains(" / ")) {
            mas = input.split("/");
            oper = '/';
        } else throw new Exception("Не подходящие данные");
        String a = mas[0], num1 = a.trim();
        String b = mas[1], num2 = b.trim();
        if (!num1.startsWith("\"") && !num1.endsWith("\"") && num1.length() > 12) {
            throw new Exception("Первым аргументом выражения должна быть строка и должна выделяться двойными кавычками");
        }
        switch (oper) {
            case '+' -> res = CalcPlus.plus(num1,num2);
            case '-' -> res = CalcMinus.minus(num1,num2);
            case '*' -> res = CalcMult.mult(num1,num2);
            case '/' -> res = CalcDiv.div(num1,num2);
            default -> throw new Exception("Не верный знак операции");
        }
        return res;
    }
}

class CalcPlus {
    static String plus (String a, String b) throws Exception {
        String c;
        if (b.startsWith("\"") && b.endsWith("\"") && b.length() <= 12) {
            a = a.replace("\"", "");
            b = b.replace("\"", "");
            c = a.concat(b);
        } else throw new Exception("Значения строк в выражении должны выделяться двойными кавычками и длиной не более 10 символов");
        return c;
    }
}

class CalcMinus {
    static String minus (String a, String b) throws Exception {
        String c;
        if (b.startsWith("\"") && b.endsWith("\"") && b.length() <= 12) {
            a = a.replace("\"", "");
            b = b.replace("\"", "");
        } else throw new Exception("Значения строк в выражении должны выделяться двойными кавычками и длиной не более 10 символов");
        c = a.replace(b, "");
        return c;
    }
}

class CalcMult {
    static String mult (String a, String b) throws Exception {
        String c;
        int d;
        if (!b.startsWith("\"") && !b.endsWith("\"")) {
            d = Integer.parseInt(b);
        } else throw new Exception("Неподходящее выражение");
        a = a.replace("\"", "");
        if (d > 0 && d <= 10) {
            c = a.repeat(d);
        } else throw new Exception("Калькулятор принимает числа от 1 до 10 включительно, не более");
        return c;
    }
}

class CalcDiv {
    static String div (String a, String b) throws Exception {
        String c;
        int d, e;
        if (!b.startsWith("\"") && !b.endsWith("\"")) {
            d = Integer.parseInt(b);
        } else throw new Exception("Неподходящее выражение");
        a = a.replace("\"", "");
        if (d > 0 && d <= 10) {
            try {
                e = a.length() / d;
                c = a.substring(0, e);
            } catch (Exception f) {
                throw new Exception("Длина делимой строки меньше делителя");
            }
        } else throw new Exception("Калькулятор принимает числа от 1 до 10 включительно, не более");
        return c;
    }
}