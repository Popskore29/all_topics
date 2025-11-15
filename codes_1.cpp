обработка массива чисел 
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    // Создание и заполнение массива
    vector<int> numbers = {5, 2, 8, 1, 9, 3};
    
    // Вывод исходного массива
    cout << "Исходный массив: ";
    for (int num : numbers) {
        cout << num << " ";
    }
    cout << endl;
    
    // Сортировка массива
    sort(numbers.begin(), numbers.end());
    
    // Вывод отсортированного массива
    cout << "Отсортированный массив: ";
    for (int num : numbers) {
        cout << num << " ";
    }
    cout << endl;
    
    // Поиск максимального элемента
    int max_element = *max_element(numbers.begin(), numbers.end());
    cout << "Максимальный элемент: " << max_element << endl;
    
    // Вычисление суммы элементов
    int sum = 0;
    for (int num : numbers) {
        sum += num;
    }
    cout << "Сумма элементов: " << sum << endl;
    
    return 0;
}

Реализация стека
#include <iostream>
#include <vector>
#include <stdexcept>

using namespace std;

class Stack {
private:
    vector<int> data;

public:
    // Добавление элемента в стек
    void push(int value) {
        data.push_back(value);
    }
    
    // Удаление и возврат верхнего элемента
    int pop() {
        if (isEmpty()) {
            throw runtime_error("Стек пуст");
        }
        int top_value = data.back();
        data.pop_back();
        return top_value;
    }
    
    // Просмотр верхнего элемента без удаления
    int peek() {
        if (isEmpty()) {
            throw runtime_error("Стек пуст");
        }
        return data.back();
    }
    
    // Проверка на пустоту
    bool isEmpty() {
        return data.empty();
    }
    
    // Получение размера стека
    size_t size() {
        return data.size();
    }
    
    // Вывод содержимого стека
    void display() {
        cout << "Стек (сверху вниз): ";
        for (auto it = data.rbegin(); it != data.rend(); ++it) {
            cout << *it << " ";
        }
        cout << endl;
    }
};

int main() {
    Stack stack;
    
    // Добавление элементов в стек
    stack.push(10);
    stack.push(20);
    stack.push(30);
    
    // Вывод содержимого стека
    stack.display();
    
    // Просмотр верхнего элемента
    cout << "Верхний элемент: " << stack.peek() << endl;
    
    // Извлечение элементов
    cout << "Извлечен: " << stack.pop() << endl;
    cout << "Извлечен: " << stack.pop() << endl;
    
    // Вывод оставшегося стека
    stack.display();
    
    // Проверка размера
    cout << "Размер стека: " << stack.size() << endl;
    
    return 0;
}
