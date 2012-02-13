#include <cstdio>
#include <cstdlib>

using namespace std;

int main(int argc, char *argv[]) {
        int v, t;
        while (scanf("%d %d", &v, &t) != EOF) {
                printf("%d\n", 2 * v * t);
        }

        return 0;
}
