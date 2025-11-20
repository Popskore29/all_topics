Вариант 12. Поиск с имитацией отжига для раскраски графа
-
Задача: реализовать имитацию отжига для задачи раскраски графа с минимизацией числа
цветов.

Требования:

- Входные данные: неориентированный граф

- Выход: раскраска вершин и количество использованных цветов

- Вывести текущее число конфликтов при понижении температуры

Входные данные:

Граф с 10 вершинами и 15 ребрами


Инициализация:

Случайно присваиваем вершинам цвета.

Вычисляем начальное количество конфликтов (рёбер, соединяющих вершины одного цвета).

colors = [random.randint(0, k-1) for _ in range(n)]

conflicts = count_conflicts(adj_list, colors)

Генерация соседнего решения:

Случайно выбираем вершину и меняем её цвет.

Пересчитываем количество конфликтов.

vertex = random.randint(0, n-1)

old_color = colors[vertex]

new_color = random.choice([c for c in range(k) if c != old_color])

colors[vertex] = new_color

new_conflicts = count_conflicts(adj_list, colors)

Критерий принятия решения (Метрополис):

Если новое решение лучше (меньше конфликтов), принимаем его.

Иначе принимаем с вероятностью exp(-ΔE / T), где ΔE = new_conflicts - conflicts.

delta = new_conflicts - conflicts

if delta < 0 or random.random() < math.exp(-delta / temperature):

    conflicts = new_conflicts

else:

    colors[vertex] = old_color  # откат

Охлаждение:

Уменьшаем температуру по формуле: T = T * cooling_rate.

temperature *= cooling_rate

Критерий остановки:

Температура опускается ниже порога (например, 1e-6).
