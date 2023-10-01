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
            if (!matcher.find()){
                throw new NullPointerException ("Вы не поствили оператор");
            }

            for (String part : words) {
                result[i++] = part.trim();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Много элементов");
            System.exit(1);
        }
        if (result[1].equals("+") || result[1].equals("-") || result[1].equals("/") || result[1].equals("*")){
            throw new IOException("Первое что подается это фраза, а не оператор");
        }
        return result;
    }

    private static String minus(String frase1, String frase2) {
        String[] arrayF1 = frase1.split(" ");
        String[] arrayF2 = frase2.split(" ");
        String result = "";
        for (int i = 0; i < arrayF1.length; i++) {
            String count;
            count = arrayF1[i];
            for (int j = 0; j < arrayF2.length; j++) {
                String count2;
                count2 = arrayF2[j];
                if (count.equals(count2)) {
                    arrayF1[i] = " ";
                    break;
                }
            }
        }

        for (int i = 0; i < arrayF1.length; i++) {
            result += arrayF1[i].trim() + " ";              //в этом цикле есть небольшой недочет
        }                                                   //он не совесем хорошо разбивает пробелы при удалении слова между двух других
        return result;                                      //но надеюсь вы меня простите за эту оплошность))0)

    }
    // хочу много - много пива


    private static String division(String frase, String count) {
        try {
            int countInt = Integer.parseInt(count);
            char[] words = frase.toCharArray();
            int wordsCount = words.length;
            int intresult = wordsCount / countInt;
            String result = "";
            for (int i = 0; i < intresult; i++)
                result += words[i];
            return result;
        } catch (NumberFormatException e){
            return "Ошибка. запишите - (\"Фрааза\" * число)";
        }

    }


    private static String multiply(String frase, String count) {
        try {
            int countInt = Integer.parseInt(count);
            String[] arrayC = new String[countInt];
            String result = frase;
            if (countInt > 1) {
                for (int i = 0; i < arrayC.length; i++)
                    arrayC[i] = result;
                for (int i = 1; i < arrayC.length; i++)
                    result += arrayC[i];
            }
            return result;
        } catch (NumberFormatException e){
            return "Ошибка. Запишите - (\"Фрааза\" / число)";
        }

    }


    public static void main(String[] args) throws IOException {
        //Scanner scanner = new Scanner("жопа попа пися - попа");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String[] textmain = Splitmain(text);
        String frase1 = textmain[1];
        String oper = textmain[0];
        String frase2 = textmain[2];
        String result = "";
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
            System.out.println("\"" + resultAlt+"..." + "\"");
        } else {
            System.out.println("\"" + result + "\"");
        }


    }


}