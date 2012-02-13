#include <cstdio>
#include <queue>

using namespace std;

const int dir[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

char landChar = 'l';

int m, n;
char map[21][21];

int fill(int i, int j);

int main(int argc, char *argv[]) {
        while (scanf("%d %d", &m, &n) != EOF) {
                for (int i(0); i < m; ++i) {
                        scanf("%s", map[i]);
                }
                int x, y;
                scanf("%d %d", &x, &y);
                landChar = map[x][y];
                fill(x, y);
                int max(0);
                for (int i(0); i < m; ++i) {
                        for (int j(0); j < n; ++j) {
                                if (map[i][j] != landChar) {
                                        continue;
                                }
                                int c = fill(i, j);
                                if (c > max) {
                                        max = c;
                                }
                        }
                }
                printf("%d\n", max);
        }

        return 0;
}

int fill(int x, int y) {
        queue<pair<int, int> > q;
        int area(0);
        for (q.push(make_pair(x, y)); !q.empty(); q.pop()) {
                pair<int, int> cur = q.front();
                if (map[cur.first][cur.second] != landChar) {
                        continue;
                }
                map[cur.first][cur.second] = landChar + 1;
                for (int i(0); i < 4; ++i) {
                        int nx = cur.first + dir[i][0];
                        if (nx < 0 || nx >= m) {
                                continue;
                        }
                        int ny = (cur.second + dir[i][1] + n) % n;
                        q.push(make_pair(nx, ny));
                }
                ++area;
        }
        return area;
}
