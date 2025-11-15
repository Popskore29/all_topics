#include <iostream>
#include <string>
using namespace std;

// Функция для определения, читается ли строка одинаково с обеих сторон
bool checkSymmetricString(const string& text, int leftIndex, int rightIndex) {
    // Условие завершения: когда левый индекс встречается с правым
    if (leftIndex >= rightIndex)
        return true;

    // Приводим символы к единому регистру для сравнения
    char leftChar = tolower(text[leftIndex]);
    char rightChar = tolower(text[rightIndex]);

    // Если крайние символы совпадают, проверяем внутреннюю часть
    if (leftChar == rightChar)
        return checkSymmetricString(text, leftIndex + 1, rightIndex - 1);
    else
        return false;
}

int main() {
    string userInput;
    cout << "Пожалуйста, введите текст для проверки: ";
    cin >> userInput;

    bool isSymmetric = checkSymmetricString(userInput, 0, userInput.length() - 1);

    if (isSymmetric)
        cout << "Да, это палиндром!" << endl;
    else
        cout << "Нет, это не палиндром." << endl;

    return 0;
}
