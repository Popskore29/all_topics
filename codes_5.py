def generate_binary(n):
    if n == 0:
        return [""]
    
    smaller = generate_binary(n - 1)
    result = []
    for s in smaller:
        result.append(s + "0")
        result.append(s + "1")
    return result

# Пример использования
n = 3
binary_strings = generate_binary(n)
print(f"Все бинарные строки длины {n}:")
for s in binary_strings:
    print(s)
