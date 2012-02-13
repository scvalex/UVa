#include <cstdio>

using namespace std;

int numCarries(long long n, long long m);

int main(int argc, char *argv[]) {
        long long n(0), m(0);
        while (scanf("%lld %lld", &n, &m) != EOF) {
                if (n == 0 && m == 0) {
                        break;
                }

                int count = numCarries(n, m);
                if (count == 0) {
                        printf("No carry operation.\n");
                } else if (count == 1) {
                        printf("1 carry operation.\n");
                } else {
                        printf("%d carry operations.\n", count);
                }
        }

        return 0;
}

int numCarries(long long n, long long m) {
        int c(0);
        int count(0);
        while ((n && c) || (m && c) || (n && m)) {
                int x = n % 10 + m % 10 + c;
                if (x > 9) {
                        ++count;
                        c = 1;
                } else {
                        c = 0;
                }
                n /= 10;
                m /= 10;
        }
        return count;
}
