import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        System.out.print("Проверка текста на палиндром. Введите фразу: ");
        String userText = inputReader.nextLine();
        
        boolean isPalindromic = checkForPalindrome(userText);
        
        if (isPalindromic)
            System.out.println("✓ Введенная фраза читается одинаково с обеих сторон");
        else
            System.out.println("✗ Фраза не является палиндромом");
            
        inputReader.close();
    }

    // Рекурсивный метод проверки симметричности текста
    private static boolean checkForPalindrome(String text) {
        // Базовое условие: пустая строка или один символ всегда симметричны
        if (text.length() <= 1)
            return true;
        
        // Нормализуем регистр для корректного сравнения
        char firstSymbol = Character.toLowerCase(text.charAt(0));
        char lastSymbol = Character.toLowerCase(text.charAt(text.length() - 1));
        
        // Сравниваем крайние символы и рекурсивно проверяем оставшуюся часть
        if (firstSymbol == lastSymbol) {
            String innerText = text.substring(1, text.length() - 1);
            return checkForPalindrome(innerText);
        } else {
            return false;
        }
    }
}
