package is.ru.stringcalculator;

import static java.lang.System.out;

public class Calculator {

	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
        else {            
            if(text.startsWith("//")){
                char delimiter = findDelimiter(text);
                String subText = text.substring(4);
                return sum(splitNumbers(subText, delimiter));
            }
		    if(text.contains(",") || text.contains("\n")){
			    return sum(splitNumbers(text));
		    }
		return 1;
	    }
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
    
    private static char findDelimiter(String text){
        // System.out.println("TEST : " + text.charAt(2));
        return text.charAt(2);
    }

    private static int sum(String[] numbers){
 	    int total = 0;
        for(String number : numbers){
		    total += toInt(number);
		}
		return total;
    }
}
