Типовая задача со структурой данных «приоритетная очередь» на ЯП Python
import heapq

# Инициализация пустой кучи
heap = []
# Добавление элементов с сохранением свойств кучи
heapq.heappush(heap, 10)
heapq.heappush(heap, 5)
heapq.heappush(heap, 15)
heapq.heappush(heap, 2)

# Последовательное извлечение элементов в порядке возрастания
while heap:
    print(heapq.heappop(heap))
# Результат: 2, 5, 10, 15
