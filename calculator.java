import java.util.Scanner;

import static java.util.logging.Level.parse;
class calculator {
        public static void main(String[] args) throws Exception {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите выражение в арабских или римских цифрах: ");
            String expression = scanner.nextLine();
            System.out.println(parse(expression));
        }

        public static String parse(String expression) throws Exception {
            int num1;
            int num2;
            String operation;
            String result;
            boolean isRoman;
            String[] operands = expression.split("[+\\-/*]");
            if (operands.length != 2) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            operation = detectOperation(expression);
            if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
                num1 = Roman.convertToArabian(operands[0]);
                num2 = Roman.convertToArabian(operands[1]);
                isRoman = true;
            } else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
                num1 = Integer.parseInt(operands[0]);
                num2 = Integer.parseInt(operands[1]);
                isRoman = false;
            } else {
                throw new Exception("Используются одновременно разные системы счисления");
            }
            if (num1 > 10 || num2 > 10) {
                throw new Exception("Числа должны быть от 1 до 10");
            }
            int arabian = calc(num1, num2, operation);
            if (isRoman) {
                if (arabian <= 0) {
                    throw new Exception("В римской системе нет отрицательных чисел");
                }
                result = Roman.convertToRoman(arabian);
            } else {
                result = String.valueOf(arabian);
            }
            return result;
        }

        static String detectOperation(String expression) {
            if (expression.contains("+")) return "+";
            else if (expression.contains("-")) return "-";
            else if (expression.contains("*")) return "*";
            else if (expression.contains("/")) return "/";
            else return null;
        }

        static int calc(int a, int b, String operation) {
            if (operation.equals("+")) return a + b;
            else if (operation.equals("-")) return a - b;
            else if (operation.equals("*")) return a * b;
            else return a / b;
        }

    }

    class Roman {
        static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI",
                "XXVII", "XXVIII", "XXIX", "XXX", "XXXII", "XXXV", "XXXVI", "XL", "XLII", "XLV", "XLVIII", "XLIX", "L", "LIV",
                "LVI", "LX", "LXIII", "LXIV", "LXX", "LXXII", "LXXX", "LXXXI", "XC", "C"};

        public static boolean isRoman(String val) {
            for (int i = 0; i < romanArray.length; i++) {
                if (val.equals(romanArray[i])) {
                    return true;
                }
            }
            return false;
        }

        public static int convertToArabian(String roman) {
            for (int i = 0; i < romanArray.length; i++) {
                if (roman.equals(romanArray[i])) {
                    return i;
                }
            }
            return -1;
        }

        public static String convertToRoman(int arabian) {
            return romanArray[arabian];
        }

}
