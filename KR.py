Блочная сортировка
-
def bucket_sort(arr):
    if len(arr) == 0:
        return arr

    # 1. Создаем пустые корзины
    num_buckets = len(arr)
    buckets = [[] for _ in range(num_buckets)]

    # 2. Распределяем элементы по корзинам
    max_val, min_val = max(arr), min(arr)
    if max_val == min_val:
        return arr
    
    for num in arr:
        # Определяем индекс корзины
        index = int((num - min_val) / (max_val - min_val) * (num_buckets - 1))
        buckets[index].append(num)

    # 3. Сортируем каждую корзину (используем встроенную сортировку)
    for bucket in buckets:
        bucket.sort()

    # 4. Объединяем отсортированные корзины
    sorted_arr = []
    for bucket in buckets:
        sorted_arr.extend(bucket)
    
    return sorted_arr

# Пример использования
arr = [0.42, 0.32, 0.33, 0.52, 0.37, 0.47, 0.51]
print("Блочная сортировка:")
print("Исходный массив:", arr)
print("Отсортированный:", bucket_sort(arr))

Блинная сортировка
-
def pancake_sort(arr):
    def flip(subarray_end):
        """Переворачивает массив от начала до subarray_end"""
        start = 0
        while start < subarray_end:
            arr[start], arr[subarray_end] = arr[subarray_end], arr[start]
            start += 1
            subarray_end -= 1

    n = len(arr)
    for curr_size in range(n, 1, -1):
        # Находим индекс максимального элемента в несортированной части
        max_idx = arr.index(max(arr[:curr_size]))
        
        # Если максимальный элемент не на своем месте
        if max_idx != curr_size - 1:
            # Переворачиваем так, чтобы максимальный элемент оказался в начале
            if max_idx != 0:
                flip(max_idx)
            # Переворачиваем несортированную часть, ставя максимальный элемент на правильную позицию
            flip(curr_size - 1)
    
    return arr

# Пример использования
arr = [3, 1, 4, 2, 6, 5]
print("\nБлинная сортировка:")
print("Исходный массив:", arr)
print("Отсортированный:", pancake_sort(arr.copy()))

Сортировка бусинами
-
def bead_sort(arr):
    if not arr or min(arr) < 0:
        raise ValueError("Массив должен содержать только неотрицательные числа")
    
    if len(arr) == 0:
        return []
    
    # Создаем "абак" - матрицу бусин
    max_val = max(arr)
    abacus = [[0] * len(arr) for _ in range(max_val)]
    
    # Расставляем бусины согласно исходному массиву
    for i, num in enumerate(arr):
        for j in range(num):
            abacus[j][i] = 1
    
    # "Падаем" бусинами под действием гравитации
    for i in range(max_val):
        # Считаем количество бусин в ряду
        bead_count = sum(abacus[i])
        # Очищаем ряд
        for j in range(len(arr)):
            abacus[i][j] = 0
        # Перемещаем бусины вправо (под действием гравитации)
        for j in range(len(arr) - bead_count, len(arr)):
            abacus[i][j] = 1
    
    # Восстанавливаем отсортированный массив из абакa
    result = [0] * len(arr)
    for j in range(len(arr)):
        for i in range(max_val):
            result[j] += abacus[i][j]
    
    return result

# Пример использования
arr = [3, 1, 4, 2]
print("\nСортировка бусинами:")
print("Исходный массив:", arr)
print("Отсортированный:", bead_sort(arr))

Поиск скачками
-
import math

def jump_search(arr, target):
    n = len(arr)
    if n == 0:
        return -1
    
    # Определяем размер прыжка
    step = int(math.sqrt(n))
    
    # Поиск блока, содержащего target
    prev = 0
    while arr[min(step, n) - 1] < target:
        prev = step
        step += int(math.sqrt(n))
        if prev >= n:
            return -1
    
    # Линейный поиск в найденном блоке
    while arr[prev] < target:
        prev += 1
        if prev == min(step, n):
            return -1
    
    # Проверяем, найден ли элемент
    if arr[prev] == target:
        return prev
    
    return -1

# Пример использования
arr = [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144]
target = 34
print("\nПоиск скачками:")
print(f"Массив: {arr}")
print(f"Ищем {target}: индекс = {jump_search(arr, target)}")

Экспоненциальный Поиск 
-
def exponential_search(arr, target):
    n = len(arr)
    if n == 0:
        return -1
    
    # Если target на первом элементе
    if arr[0] == target:
        return 0
    
    # Находим диапазон для бинарного поиска
    i = 1
    while i < n and arr[i] <= target:
        i *= 2
    
    # Выполняем бинарный поиск в найденном диапазоне
    left = i // 2
    right = min(i, n - 1)
    
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    
    return -1

# Пример использования
arr = [2, 3, 4, 10, 15, 18, 20, 22, 25, 30, 40, 45, 50]
target = 18
print("\nЭкспоненциальный поиск:")
print(f"Массив: {arr}")
print(f"Ищем {target}: индекс = {exponential_search(arr, target)}")

Тернарный поиск
-
def ternary_search(arr, target):
    def recursive_search(left, right):
        if left > right:
            return -1
        
        # Разделяем диапазон на три части
        mid1 = left + (right - left) // 3
        mid2 = right - (right - left) // 3
        
        if arr[mid1] == target:
            return mid1
        if arr[mid2] == target:
            return mid2
        
        if target < arr[mid1]:
            return recursive_search(left, mid1 - 1)
        elif target > arr[mid2]:
            return recursive_search(mid2 + 1, right)
        else:
            return recursive_search(mid1 + 1, mid2 - 1)
    
    return recursive_search(0, len(arr) - 1)

# Пример использования
arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
target = 6
print("\nТернарный поиск:")
print(f"Массив: {arr}")
print(f"Ищем {target}: индекс = {ternary_search(arr, target)}")
