package is.ru.stringcalculator;

import static java.lang.System.out;
import java.util.Stack;

public class Calculator {

	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
        else {            
            if(text.startsWith("//")){
                if (text.charAt(2) == '[') {
                    int count = findInputStart(text);
                    Stack<String> delimiters = findStringDelimiters(text);
                    String subText = text.substring(count);
                    return sum(splitNumbers(subText, delimiters));    
                }
                else {
                    char delimiter = findDelimiter(text);
                    String subText = text.substring(4);
                    return sum(splitNumbers(subText, delimiter));
                }
            }
		    if(text.contains(",") || text.contains("\n")){
			    return sum(splitNumbers(text));
		    }
		return 1;
	    }
    }

    private static int findInputStart(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '\n') {
                return i + 1;
            }
        }
        return 0;
    }

	private static int toInt(String number){
	    return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
        return numbers.split(",|\n");
	}
    
	private static String[] splitNumbers(String numbers, char delimiter){
        String regSplit = ",|\n|" + delimiter;
        return numbers.split(regSplit);
	}

	private static String[] splitNumbers(String numbers, String delimiter){
        String regSplit = ",|\n|" + delimiter;
        return numbers.split(regSplit);
	}

    private static String[] splitNumbers(String numbers, Stack<String> delimiters){
        String regSplit = ",|\n";
        while (!delimiters.empty()) {
            regSplit = regSplit + "|" + delimiters.pop();
        }
        return numbers.split(regSplit);
    }

    private static char findDelimiter(String text){
        return text.charAt(2);
    }
 
    private static Stack<String> findStringDelimiters(String text){
        String delimiter = "";
        Stack<String> delimiters = new Stack<String>();
        boolean add = false;
        for(int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '[') {
                add = true;
                continue;
            }
            else if (text.charAt(i) == ']') {
                delimiters.push(delimiter);
                delimiter = "";
                add = false;
                continue;
            }
            else if (text.charAt(i) == '\n') {
                break;
            }
            if (add) {
                if (isLegal(text.charAt(i))) {
                    delimiter = delimiter + text.charAt(i);
                }
                else {
                    delimiter = delimiter + "\\" + text.charAt(i);
                }
            }
        }
        return delimiters;
    }

    public static boolean isLegal(char c){
        if  (c == '\\' || c == '.' || c == '[' || c == ']' ||
            c == '{' || c == '}' || c == '(' || c == ')' ||
            c == '*' || c == '+' || c == '-' || c == '?' ||
            c == '^' || c == '$' || c == '|'){
                return false;
        }
        return true;
    }

    private static int sum(String[] numbers){
 	    int total = 0;
        boolean neg = false;
        String negative = "Negatives not allowed: ";
        for(String number : numbers){
		    int intNumber = toInt(number);
            if (intNumber < 0) {
                negative = negative + number + ",";
                neg = true;
            }
            else if (intNumber < 1000) {
                total += toInt(number);
            }
		}
        if (neg) {
            negative = negative.substring(0, negative.length()-1);
            throw new IllegalArgumentException(negative);
        }
        return total;
    }
}
