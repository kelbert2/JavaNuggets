def f(x):
    x *= 2
    def fy(y):
        y *= x
        def fz(z):
            return z * y
        return fz
    return fy

# print(f(3)(4)(5))

def factorial(n):
     return 1 if (n <= 1) else n * factorial(n - 1)

print(factorial(4))

# setTimeout(0) event loop order