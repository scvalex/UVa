#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cctype>

using namespace std;

int main(int argc, char *argv[]) {
        char delims[128];
        int i(0);
        for (int c(1); c < 128; ++c) {
                if (!isalpha((char)c) && (char)c != '\n')
                        delims[i++] = c;
        }
        delims[i] = '\0';

        size_t n = 8096;
        char *line = (char*)malloc(n);
        while (getline(&line, &n, stdin) != -1) {
                char *old = NULL;
                char *tok = strtok(line, delims);
                int count(0);
                for ( ; tok != NULL; old = tok, tok = strtok(NULL, delims)) {
                        if (old && old != tok-1) {
                                ++count;
                        }
                }
                if (*(old + 1) != '\0') {
                        ++count;
                }
                printf("%d\n", count);
        }

        return 0;
}
