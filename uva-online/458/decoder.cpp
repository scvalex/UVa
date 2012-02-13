#include <cstdio>
#include <cstdlib>

using namespace std;

int main(int argc, char *argv[]) {
        char *line = (char*)malloc(1024);
        size_t n;
        while (getline((char**)&line, &n, stdin) != -1) {
                int i;
                for (i = 0;
                     i < (int)n - 1 && line[i] != '\n' && line[i] != '\0';
                     ++i)
                {
                        printf("%c", (line[i] - 7));
                }
                if (i < (int)n - 1) {
                        printf("\n");
                }
        }

        return 0;
}
