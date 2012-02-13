#include <algorithm>
#include <cstdio>
#include <cstdlib>
#include <set>

using namespace std;

char *parts[100000];
set<int> digs[20][128];

set<int> cur;

int main(int argc, char *argv[]) {
        int n, m;
        scanf("%d %d\n", &n, &m);

        for (int i(0); i < n; ++i) {
                parts[i] = (char*)malloc(21);
                size_t lineSize = 21;
                int end = getline(&parts[i], &lineSize, stdin);
                parts[i][end-1] = '\0';
                for (int j(0); j < end-1; ++j) {
                        digs[j][(int)parts[i][j]].insert(i);
                }
                //printf("part: '%s'\n", parts[i]);
        }

        char *line = (char*)malloc(30);
        size_t lineSize = 30;
        for (int i(0); i < m; ++i) {
                int end = getline(&line, &lineSize, stdin);
                line[end-1] = '\0';
                //printf("scratch: '%s'\n", line);
                cur.clear();
                bool beg(true);
                for (int j(0); j < end-1; ++j) {
                        if (line[j] != ' ') {
                                if (beg) {
                                        cur = digs[j][(int)line[j]];
                                        beg = false;
                                } else {
                                        set<int> temp;
                                        set_intersection(cur.begin(), cur.end(),
                                                         digs[j][(int)line[j]].begin(), digs[j][(int)line[j]].end(),
                                                         inserter(temp, temp.begin()));
                                        cur = temp;
                                }
                                //printf("Processed '%c'.  Size is %d big.\n", line[j], cur.size());
                        }
                }
                if (cur.size() == 0) {
                        printf("not found\n");
                } else if (cur.size() == 1) {
                        printf("%s\n", parts[*cur.begin()]);
                } else {
                        printf("not unique\n");
                }
                //printf("narrowed down to %d numbers\n", cur.size());
        }

        return 0;
}
