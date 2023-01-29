import java.util.Objects;

public class Calc {
    public Calc(String value1, String value2, String oper)
    {
        a = value1;
        b = value2;
        operation = identifyNumOperation(oper);
    }

    private static int identifyNumOperation(String operator)
    {
        int oper = 0;
        if (Objects.equals(operator, "*")) oper = 1; // будет умножить  *
        else if (Objects.equals(operator, "/")) oper = 2; // будет деление    /
        else if (Objects.equals(operator, "+")) oper = 3; // будет плюс   +
        else if (Objects.equals(operator, "-")) oper = 4; // будет минус -
        try
        {
            if (oper == 0) throw new Exception("Ошибка. Не определено ни одного оператора (+, -, *, /)!\n");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.exit(0);
        }

        return oper;
    }

    public boolean verifyInputValues()
    {
        boolean result = false;
        try
        {

            if ((isRomanicNum(a)) && (isArabicNum(a)) ||
                    (isRomanicNum(b) && (isArabicNum(b))))
                throw new Exception("Ошибка. Допускаются только значения от 1 до 10!\n Программа завершает работу.\n");

            if (isRomanicNum(a) && isRomanicNum(b))
            {
                result = true;
            }
            else if (isArabicNum(a) && isArabicNum(b))
            {
                result = true;
            }
            else
                throw new Exception("Ошибка. Недопустимое значение или неизвестная система счисления (допускается только арабская или только римская)!\n Программа завершает работу.\n");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            System.exit(0);
        }
        return result;

    }

    public static boolean isRomanicNum(String number)
    {
        boolean result = false;
        for (int i = 0; i < 10; i++)
        {
            if (number.toLowerCase().equals(romanic[i]))
                result = true;
        }
        return result;

    }



    public static boolean isArabicNum(String number)
    {
        boolean result = false;
        for (int arabic = 1; arabic <= 10; arabic++)
        {
            try
            {
                if (Integer.parseInt(number) == arabic) result = true;

            }
            catch (Exception ex)
            {
                return result;
            }

        }
        return result;

    }


    public void calculate(boolean verifyValues)
    {
        String num1 = "";
        String num2 = "";
        boolean isRoman = false;
        if (verifyValues) verifyInputValues();
        isRoman = isRomanicNum(a) || isRomanicNum(b);
        if (isRoman)
        {
            num1 = toArabic(a);
            num2 = toArabic(b);
        }
        else
        {
            num1 = a;
            num2 = b;
        }

        int c = 0;
        System.out.println("Результат ");

        {
            switch (operation)
            {
                case 1:
                    c = Integer.parseInt(num1) * Integer.parseInt(num2);
                    break;
                case 2:
                    c = Integer.parseInt(num1) / Integer.parseInt(num2);
                    break;
                case 3:
                    c = Integer.parseInt(num1) + Integer.parseInt(num2);
                    break;
                case 4:
                    c = Integer.parseInt(num1) - Integer.parseInt(num2);
                    break;
            }
        }
        if (isRoman) System.out.println(toRoman(Integer.toString(c)).toUpperCase());
        else System.out.println(c);
        System.out.println("\n");
    }


    public static String toRoman(String number)
    {
        String result = "";
        String cyfr = "";
        for (int i = number.length(); i > 0; i--)
        {
            cyfr = number.substring(i - 1 , i);
            if (Integer.parseInt(cyfr) == 0)
                continue;
            switch (number.length() - i)
            {
                case 0:
                    result = romanic[Integer.parseInt(cyfr) - 1] + result;
                    break;
                case 1:
                    result = romanicDecs[Integer.parseInt(cyfr) - 1] + result;;
                    break;
                case 2:
                    result = "c";
                    break;

            }
        }
        return result;

    }
    public static String toArabic(String number)
    {
        String result = "0";
        if (isRomanicNum(number))
        {
            for (int i = 0; i < 10; i++)
            {
                if (number.toLowerCase().equals(romanic[i]))
                {
                    result = Integer.toString(i + 1);
                    break;
                }

            }

        }
        return result;
    }

    private static String a = "";
    private static String b = "";
    private static int operation = 0;
    private static String[] romanic = { "i", "ii", "iii", "iv", "v",
            "vi", "vii", "viii", "ix", "x" };
    private static String[] romanicDecs = { "x", "xx", "xxx", "xl",
            "l", "lx", "lxx", "lxxx", "xc"};
}
