Бинарная куча 
#include <iostream>
#include <vector>

class MinHeap {
private:
    std::vector<int> data;  // Храним элементы в массиве
    
    // Поднимаем элемент на нужное место
    void bubbleUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (data[index] < data[parent]) {
                std::swap(data[index], data[parent]);
                index = parent;
            } else break;
        }
    }
    
    // Опускаем элемент на нужное место
    void bubbleDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int smallest = index;
        
        if (leftChild < data.size() && data[leftChild] < data[smallest])
            smallest = leftChild;
            
        if (rightChild < data.size() && data[rightChild] < data[smallest])
            smallest = rightChild;
            
        if (smallest != index) {
            std::swap(data[index], data[smallest]);
            bubbleDown(smallest);
        }
    }

public:
    // Добавляем новый элемент
    void push(int value) {
        data.push_back(value);
        bubbleUp(data.size() - 1);
    }
    
    // Извлекаем минимальный элемент
    int pop() {
        if (empty()) throw std::runtime_error("Куча пуста");
        
        int minValue = data[0];
        data[0] = data.back();
        data.pop_back();
        if (!empty()) bubbleDown(0);
        
        return minValue;
    }
    
    // Смотрим минимальный элемент без удаления
    int top() const {
        if (empty()) throw std::runtime_error("Куча пуста");
        return data[0];
    }
    
    bool empty() const { return data.empty(); }
    size_t size() const { return data.size(); }
};

// Пример использования
int main() {
    MinHeap heap;
    heap.push(10);
    heap.push(5);
    heap.push(8);
    heap.push(3);
    
    std::cout << "Минимальный элемент: " << heap.top() << std::endl;
    std::cout << "Извлекаем: " << heap.pop() << std::endl;
    std::cout << "Новый минимальный: " << heap.top() << std::endl;
    
    return 0;
}
Хеш-таблицы
#include <iostream>
#include <vector>
#include <list>

template<typename Key, typename Value>
class HashTable {
private:
    std::vector<std::list<std::pair<Key, Value>>> buckets;
    size_t capacity;
    
    // Вычисляем номер ячейки для ключа
    size_t getBucketIndex(const Key& key) const {
        return std::hash<Key>{}(key) % capacity;
    }

public:
    HashTable(size_t size = 10) : capacity(size), buckets(size) {}
    
    // Добавляем или обновляем пару
    void put(const Key& key, const Value& value) {
        size_t index = getBucketIndex(key);
        auto& bucket = buckets[index];
        
        // Проверяем, есть ли уже такой ключ
        for (auto& pair : bucket) {
            if (pair.first == key) {
                pair.second = value;  // Обновляем значение
                return;
            }
        }
        
        // Добавляем новую пару
        bucket.emplace_back(key, value);
    }
    
    // Получаем значение по ключу
    Value* get(const Key& key) {
        size_t index = getBucketIndex(key);
        auto& bucket = buckets[index];
        
        for (auto& pair : bucket) {
            if (pair.first == key) {
                return &pair.second;
            }
        }
        return nullptr;  // Не нашли
    }
    
    // Удаляем пару по ключу
    void remove(const Key& key) {
        size_t index = getBucketIndex(key);
        auto& bucket = buckets[index];
        
        for (auto it = bucket.begin(); it != bucket.end(); ++it) {
            if (it->first == key) {
                bucket.erase(it);
                return;
            }
        }
    }
    
    // Показываем содержимое таблицы
    void display() const {
        for (size_t i = 0; i < capacity; ++i) {
            std::cout << "Ячейка " << i << ": ";
            for (const auto& pair : buckets[i]) {
                std::cout << "(" << pair.first << " -> " << pair.second << ") ";
            }
            std::cout << std::endl;
        }
    }
};

// Пример использования
int main() {
    HashTable<std::string, int> table;
    table.put("яблоко", 10);
    table.put("банан", 5);
    table.put("апельсин", 8);
    
    table.display();
    
    if (auto value = table.get("яблоко")) {
        std::cout << "Яблоко: " << *value << std::endl;
    }
    
    return 0;
}
Биноминальная куча
#include <iostream>
#include <vector>

struct BinomialNode {
    int value;
    BinomialNode* child;
    BinomialNode* next;
    int order;  // Размер дерева
    
    BinomialNode(int val) : value(val), child(nullptr), next(nullptr), order(0) {}
};

class BinomialHeap {
private:
    std::vector<BinomialNode*> trees;
    
    // Соединяем два дерева одинакового размера
    BinomialNode* combineTrees(BinomialNode* a, BinomialNode* b) {
        if (a->value > b->value) return combineTrees(b, a);
        
        b->next = a->child;
        a->child = b;
        a->order++;
        return a;
    }

public:
    BinomialHeap() = default;
    
    // Добавляем новый элемент
    void insert(int value) {
        BinomialHeap tempHeap;
        auto newNode = new BinomialNode(value);
        tempHeap.trees.push_back(newNode);
        merge(tempHeap);
    }
    
    // Объединяем с другой кучей
    void merge(BinomialHeap& other) {
        std::vector<BinomialNode*> merged(std::max(trees.size(), other.trees.size()) + 1, nullptr);
        
        // Объединяем деревья
        for (size_t i = 0; i < trees.size(); ++i) {
            if (trees[i]) merged[i] = trees[i];
        }
        
        for (size_t i = 0; i < other.trees.size(); ++i) {
            auto tree = other.trees[i];
            while (tree && i < merged.size()) {
                if (!merged[i]) {
                    merged[i] = tree;
                    break;
                } else {
                    tree = combineTrees(merged[i], tree);
                    merged[i] = nullptr;
                    i++;
                }
            }
        }
        
        trees = merged;
        other.trees.clear();
    }
    
    // Находим минимальный элемент
    int findMin() const {
        int minValue = INT_MAX;
        for (auto tree : trees) {
            if (tree && tree->value < minValue) {
                minValue = tree->value;
            }
        }
        return minValue;
    }
    
    // Показываем структуру кучи
    void print() const {
        std::cout << "Биномиальная куча:" << std::endl;
        for (size_t i = 0; i < trees.size(); ++i) {
            if (trees[i]) {
                std::cout << "Дерево порядка " << i << ": корень = " << trees[i]->value << std::endl;
            }
        }
    }
};

// Пример использования
int main() {
    BinomialHeap heap;
    heap.insert(10);
    heap.insert(5);
    heap.insert(8);
    heap.insert(3);
    
    heap.print();
    std::cout << "Минимальный элемент: " << heap.findMin() << std::endl;
    
    return 0;
}

