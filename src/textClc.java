import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class textClc {
    private static String[] Splitmain(String mainText) throws IOException {
        mainText = mainText.replace("\"", "");
        Pattern pattern = Pattern.compile("([-+*/])");
        Matcher matcher = pattern.matcher(mainText);

        String[] words = pattern.split(mainText);
        String[] result = new String[words.length + 1];

        int i = 0;
        try {

            while (matcher.find()) {
                result[i++] = matcher.group();
            }


            for (String part : words) {
                result[i++] = part.trim();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка формата");
            System.exit(1);
        }
        if (result[1].equals("+") || result[1].equals("-") || result[1].equals("/") || result[1].equals("*")) {
            throw new IOException("Первое что подается это фраза, а не оператор");
        }
        return result;
    }

    private static String minus(String frase1, String frase2) {
        char[] charF1 = frase1.toCharArray();
        char[] charF2 = frase2.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < charF1.length; i++) {
            char count = charF1[i];
            boolean def = false;
            for (int j = 0; j < charF2.length; j++) {
                char count2 = charF2[j];
                if (count == count2) {
                    charF2[j] = '\0';
                    def = true;
                    break;
                }
            }
            if (!def)
                result.append(count);
        }
        return result.toString();
    }
    // хочу много - много пива


    private static String division(String frase, String count) {
        try {
            int countInt = Integer.parseInt(count);
            if (countInt >= 11) {
                System.out.println("Принимаются числа до 10");
                System.exit(1);
            }
            char[] words = frase.toCharArray();
            int wordsCount = words.length;
            int intresult = wordsCount / countInt;
            String result = "";
            for (int i = 0; i < intresult; i++)
                result += words[i];
            return result;
        } catch (NumberFormatException e) {
            return "Ошибка. запишите - (\"Фрааза\" / число)";
        }

    }


    private static String multiply(String frase, String count) {
        try {
            int countInt = Integer.parseInt(count);
            if (countInt >= 11) {
                System.out.println("Принимаются числа до 10");
                System.exit(1);
            }
            String[] arrayC = new String[countInt];
            String result = frase;
            if (countInt > 1) {
                for (int i = 0; i < arrayC.length; i++)
                    arrayC[i] = result;
                for (int i = 1; i < arrayC.length; i++)
                    result += arrayC[i];
            }
            return result;
        } catch (NumberFormatException e) {
            return "Ошибка. Запишите - (\"Фрааза\" * число)";
        }

    }


    public static void main(String[] args) throws IOException {
        System.out.println("Здраствуйте, это мой второй калькулятор. На этот раз текстовый");
        System.out.println("Введите выражение в формамте \"a\" + \"b\", \"a\" - \"b\", \"a\" * b, \"a\" / b.");
        System.out.println("Где (\"а\" или \"b\") в кавычка фраза, а переменная без кавычек это  число");
        System.out.println("Вы можете ввести фразу и без кавычек, будет работать и так, но в кавычки нужны по таску"); // здесь я возможно не верно понял условие
        Scanner scanner = new Scanner(System.in);                                                                      // и нахождение строк в кавычках является необходимостью
        String text = scanner.nextLine();                                                                              // но при выбрасывание исключений в программи при противном не было речи
        String[] textmain = Splitmain(text);                                                                           // и все работает с ними и без
        String frase1 = textmain[1];
        String oper = textmain[0];
        String frase2 = textmain[2];
        String result = "";
        char[] frase1Words = frase1.toCharArray();
        char[] frase2Words = frase2.toCharArray();
        if (frase1Words.length >= 11 || frase2Words.length >= 11) {
            System.out.println("Принимаются фразы до 10 символов");
            System.exit(2);
        }
        switch (oper) {
            case "+" -> result = (frase1 + frase2).replace("\"", "");
            case "-" -> result = minus(frase1, frase2);
            case "*" -> result = multiply(frase1, frase2);
            case "/" -> result = division(frase1, frase2);
        }
        char[] charCount = result.toCharArray();
        if (charCount.length > 40) {
            String resultAlt = "";
            for (int i = 0; i < 40; i++) {
                resultAlt += charCount[i];
            }
            System.out.println("\"" + resultAlt + "..." + "\"");
        } else {
            System.out.println("\"" + result + "\"");
        }


    }


}
