#include <algorithm>
#include <cstdio>
#include <cstdlib>
#include <cstring>

using namespace std;

int main(int argc, char *argv[]) {
        char *s = (char*)malloc(100);
        size_t n(100);

        int len(0);
        while ((len = getline(&s, &n, stdin)) != -1) {
                s[--len] = '\0';
                if (s[0] == '0' && s[1] == '\0')
                        break;

                reverse(s, s + len);

                int n(0);
                int p(2);
                for (char *c = s; *c; ++c) {
                        n += (p-1) * (*c - '0');
                        p = p << 1;
                }
                printf("%d\n", n);
        }

        return 0;
}
