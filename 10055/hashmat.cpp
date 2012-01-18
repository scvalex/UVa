#include <cstdlib>
#include <cstdio>

using namespace std;

int main(int argc, char *argv[]) {
        long long unsigned n, m;
        while (scanf("%lld %lld", &n, &m) != EOF) {
                printf("%lld\n", llabs(n-m));
        }

        return 0;
}
