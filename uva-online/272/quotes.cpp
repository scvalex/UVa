#include <cstdio>
#include <cstdlib>

using namespace std;

int main(int argc, char *argv[]) {
        char *line = (char*)malloc(1024);
        size_t n(1024);
        int toggle(0);
        while (getline(&line, &n, stdin) != -1) {
                for (int i(0); i < (int)n-1 && line[i] != '\0'; ++i) {
                        if (line[i] == '\"') {
                                if (toggle) {
                                        printf("''");
                                } else {
                                        printf("``");
                                }
                                toggle = 1 - toggle;
                        } else {
                                printf("%c", line[i]);
                        }
                }
        }

        return 0;
}
