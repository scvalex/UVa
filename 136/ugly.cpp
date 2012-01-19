#include <algorithm>
#include <cstdio>
#include <cstring>

using namespace std;

int main(int argc, char *argv[]) {
        int n = 1000000;
        char ugly[n];
        char prime[n];
        memset(ugly, 1, sizeof(ugly));
        memset(prime, 1, sizeof(prime));

        int count(1);
        for (int i(2); i < n; ++i) {
                if (prime[i]) {
                        for (int j = i*2; j < n; j += i) {
                                prime[j] = 0;
                        }
                        if (i > 5) {
                                for (int j = i; j < n; j += i) {
                                        ugly[j] = 0;
                                }
                        }
                }
                if (ugly[i]) {
                        ++count;
                        printf("%dth ugly: %d\n", count, i);
                        if (count == 1) {
                                printf("%d\n", i);
                                break;
                        }
                }
        }

        return 0;
}
