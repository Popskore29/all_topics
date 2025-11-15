Бинарная куча (Минимальная куча)
  import java.util.*;

// Реализация минимальной бинарной кучи
public class MinHeap<T extends Comparable<T>> {
    private final List<T> elements;  // Храним элементы в списке
    
    public MinHeap() {
        elements = new ArrayList<>();
    }
    
    // Добавление нового элемента
    public void add(T element) {
        elements.add(element);           // Добавляем в конец
        moveUp(elements.size() - 1);     // Поднимаем на нужную позицию
    }
    
    // Извлечение минимального элемента
    public T removeMin() {
        if (isEmpty()) throw new RuntimeException("Куча пуста");
        
        T minElement = elements.get(0);  // Минимальный элемент - первый
        T lastElement = elements.remove(elements.size() - 1); // Удаляем последний
        
        if (!isEmpty()) {
            elements.set(0, lastElement); // Ставим последний элемент на первое место
            moveDown(0);                  // Опускаем на нужную позицию
        }
        
        return minElement;
    }
    
    // Проверка на пустоту
    public boolean isEmpty() {
        return elements.isEmpty();
    }
    
    // Поднимаем элемент вверх
    private void moveUp(int position) {
        while (position > 0) {
            int parentPosition = (position - 1) / 2;
            // Если текущий элемент меньше родителя - меняем местами
            if (elements.get(position).compareTo(elements.get(parentPosition)) < 0) {
                swap(position, parentPosition);
                position = parentPosition;
            } else {
                break;
            }
        }
    }
    
    // Опускаем элемент вниз
    private void moveDown(int position) {
        while (hasLeftChild(position)) {
            int smallestChildPosition = getLeftChildPosition(position);
            int rightChildPosition = getRightChildPosition(position);
            
            // Выбираем наименьшего из двух детей
            if (hasRightChild(position) && 
                elements.get(rightChildPosition).compareTo(elements.get(smallestChildPosition)) < 0) {
                smallestChildPosition = rightChildPosition;
            }
            
            // Если текущий элемент больше наименьшего ребенка - меняем местами
            if (elements.get(position).compareTo(elements.get(smallestChildPosition)) > 0) {
                swap(position, smallestChildPosition);
                position = smallestChildPosition;
            } else {
                break;
            }
        }
    }
    
    // Вспомогательные методы для работы с индексами
    private boolean hasLeftChild(int position) {
        return getLeftChildPosition(position) < elements.size();
    }
    
    private boolean hasRightChild(int position) {
        return getRightChildPosition(position) < elements.size();
    }
    
    private int getLeftChildPosition(int position) {
        return 2 * position + 1;
    }
    
    private int getRightChildPosition(int position) {
        return 2 * position + 2;
    }
    
    // Обмен элементов местами
    private void swap(int i, int j) {
        T temp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, temp);
    }
    
    @Override
    public String toString() {
        return elements.toString();
    }
    
    // Пример использования
    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(10);
        heap.add(5);
        heap.add(8);
        heap.add(3);
        
        System.out.println("Минимальный элемент: " + heap.removeMin()); // Выведет 3
        System.out.println("Оставшиеся элементы: " + heap);
    }
}
Хеш-таблица
  import java.util.*;

// Простая реализация хеш-таблицы
public class SimpleHashTable<K, V> {
    private final List<List<KeyValuePair<K, V>>> buckets;
    private final int bucketCount;
    
    // Пара ключ-значение
    private static class KeyValuePair<K, V> {
        K key;
        V value;
        
        KeyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    public SimpleHashTable(int bucketCount) {
        this.bucketCount = bucketCount;
        this.buckets = new ArrayList<>(bucketCount);
        
        // Инициализируем все корзины
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new LinkedList<>());
        }
    }
    
    // Добавление или обновление значения
    public void put(K key, V value) {
        int bucketIndex = calculateBucketIndex(key);
        List<KeyValuePair<K, V>> bucket = buckets.get(bucketIndex);
        
        // Проверяем, есть ли уже такой ключ
        for (KeyValuePair<K, V> pair : bucket) {
            if (pair.key.equals(key)) {
                pair.value = value;  // Обновляем значение
                return;
            }
        }
        
        // Добавляем новую пару
        bucket.add(new KeyValuePair<>(key, value));
    }
    
    // Получение значения по ключу
    public V get(K key) {
        int bucketIndex = calculateBucketIndex(key);
        List<KeyValuePair<K, V>> bucket = buckets.get(bucketIndex);
        
        for (KeyValuePair<K, V> pair : bucket) {
            if (pair.key.equals(key)) {
                return pair.value;
            }
        }
        return null;  // Ключ не найден
    }
    
    // Удаление элемента
    public void remove(K key) {
        int bucketIndex = calculateBucketIndex(key);
        List<KeyValuePair<K, V>> bucket = buckets.get(bucketIndex);
        
        Iterator<KeyValuePair<K, V>> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            KeyValuePair<K, V> pair = iterator.next();
            if (pair.key.equals(key)) {
                iterator.remove();
                return;
            }
        }
    }
    
    // Вычисление индекса корзины
    private int calculateBucketIndex(K key) {
        return Math.abs(key.hashCode()) % bucketCount;
    }
    
    // Показать все элементы
    public void showContents() {
        for (int i = 0; i < bucketCount; i++) {
            List<KeyValuePair<K, V>> bucket = buckets.get(i);
            if (!bucket.isEmpty()) {
                System.out.print("Корзина " + i + ": ");
                for (KeyValuePair<K, V> pair : bucket) {
                    System.out.print("[" + pair.key + "→" + pair.value + "] ");
                }
                System.out.println();
            }
        }
    }
    
    // Пример использования
    public static void main(String[] args) {
        SimpleHashTable<String, Integer> table = new SimpleHashTable<>(5);
        
        table.put("яблоки", 10);
        table.put("бананы", 5);
        table.put("апельсины", 8);
        
        table.showContents();
        System.out.println("Количество бананов: " + table.get("бананы"));
        
        table.remove("яблоки");
        System.out.println("После удаления:");
        table.showContents();
    }
}
Биномиальная куча
// Узел биномиального дерева
class BinomialTree<T extends Comparable<T>> {
    T value;
    BinomialTree<T> parent;
    BinomialTree<T> firstChild;
    BinomialTree<T> nextSibling;
    int rank;  // Количество детей
    
    BinomialTree(T value) {
        this.value = value;
        this.rank = 0;
    }
}

// Биномиальная куча
public class BinomialHeap<T extends Comparable<T>> {
    private BinomialTree<T> firstTree;
    
    public BinomialHeap() {
        firstTree = null;
    }
    
    // Добавление нового элемента
    public void add(T value) {
        BinomialHeap<T> singleTreeHeap = new BinomialHeap<>();
        singleTreeHeap.firstTree = new BinomialTree<>(value);
        combineWith(singleTreeHeap);
    }
    
    // Поиск минимального значения
    public T findMin() {
        if (firstTree == null) return null;
        
        T minValue = firstTree.value;
        BinomialTree<T> current = firstTree.nextSibling;
        
        while (current != null) {
            if (current.value.compareTo(minValue) < 0) {
                minValue = current.value;
            }
            current = current.nextSibling;
        }
        return minValue;
    }
    
    // Объединение с другой биномиальной кучей
    public void combineWith(BinomialHeap<T> other) {
        firstTree = mergeTrees(firstTree, other.firstTree);
        other.firstTree = null;
    }
    
    // Слияние списков деревьев
    private BinomialTree<T> mergeTrees(BinomialTree<T> tree1, BinomialTree<T> tree2) {
        // Простое слияние двух отсортированных списков деревьев
        if (tree1 == null) return tree2;
        if (tree2 == null) return tree1;
        
        BinomialTree<T> result = null;
        BinomialTree<T> current = null;
        
        while (tree1 != null && tree2 != null) {
            BinomialTree<T> selected;
            if (tree1.rank <= tree2.rank) {
                selected = tree1;
                tree1 = tree1.nextSibling;
            } else {
                selected = tree2;
                tree2 = tree2.nextSibling;
            }
            
            if (result == null) {
                result = selected;
                current = result;
            } else {
                current.nextSibling = selected;
                current = current.nextSibling;
            }
        }
        
        // Добавляем оставшиеся деревья
        if (tree1 != null) current.nextSibling = tree1;
        if (tree2 != null) current.nextSibling = tree2;
        
        return result;
    }
    
    // Проверка на пустоту
    public boolean isEmpty() {
        return firstTree == null;
    }
    
    // Пример использования
    public static void main(String[] args) {
        BinomialHeap<Integer> heap = new BinomialHeap<>();
        heap.add(10);
        heap.add(5);
        heap.add(8);
        heap.add(3);
        
        System.out.println("Минимальный элемент: " + heap.findMin()); // Выведет 3
    }
}
Куча Фибоначчи
import java.util.*;

// Узел кучи Фибоначчи
class FibonacciNode<T extends Comparable<T>> {
    T value;
    FibonacciNode<T> parent;
    FibonacciNode<T> child;
    FibonacciNode<T> left;
    FibonacciNode<T> right;
    boolean isMarked;
    int childCount;
    
    FibonacciNode(T value) {
        this.value = value;
        this.left = this;
        this.right = this;  // Кольцевая связь
    }
}

// Куча Фибоначчи
public class FibonacciHeap<T extends Comparable<T>> {
    private FibonacciNode<T> minNode;
    private int totalNodes;
    
    public FibonacciHeap() {
        minNode = null;
        totalNodes = 0;
    }
    
    // Добавление элемента
    public void add(T value) {
        FibonacciNode<T> newNode = new FibonacciNode<>(value);
        
        if (minNode == null) {
            minNode = newNode;
        } else {
            // Добавляем новый узел в список корней
            addToRootList(newNode);
            
            // Обновляем минимальный узел если нужно
            if (newNode.value.compareTo(minNode.value) < 0) {
                minNode = newNode;
            }
        }
        totalNodes++;
    }
    
    // Поиск минимального элемента
    public T getMin() {
        if (minNode == null) throw new RuntimeException("Куча пуста");
        return minNode.value;
    }
    
    // Добавление узла в список корней
    private void addToRootList(FibonacciNode<T> node) {
        node.left = minNode;
        node.right = minNode.right;
        minNode.right.left = node;
        minNode.right = node;
    }
    
    // Проверка на пустоту
    public boolean isEmpty() {
        return minNode == null;
    }
    
    // Количество элементов
    public int size() {
        return totalNodes;
    }
    
    // Пример использования
    public static void main(String[] args) {
        FibonacciHeap<Integer> fibHeap = new FibonacciHeap<>();
        fibHeap.add(10);
        fibHeap.add(5);
        fibHeap.add(8);
        fibHeap.add(3);
        
        System.out.println("Минимальный элемент: " + fibHeap.getMin()); // Выведет 3
        System.out.println("Всего элементов: " + fibHeap.size());       // Выведет 4
    }
}
