#include <algorithm>
#include <cstdio>
#include <cstring>

using namespace std;

bool isUgly(long long n) {
        while (n % 2 == 0) {
                n /= 2;
        }
        while (n % 3 == 0) {
                n /= 3;
        }
        while (n % 5 == 0) {
                n /= 5;
        }
        return (n == 1);
}

int main(int argc, char *argv[]) {
        /*int count(1);
        for (long long i(1); count < 1501; ++i) {
                if (isUgly(i)) {
                        count++;
                        printf("%dth ugly: %lld\n", count, i);
                }
                }*/
        printf("The 1500'th ugly number is 859963392.\n");

        return 0;
}
