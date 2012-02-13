#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int n;
vector<vector<int> > graph;

int walk(int start);

int main(int argc, char *argv[]) {
        int numTests;
        cin >> numTests;
        while (numTests-- > 0) {
                int m;
                cin >> n >> m;
                graph.clear();
                graph.resize(n);

                int u, v;
                for (int i(0); i < m; ++i) {
                        cin >> u >> v;
                        graph[u].push_back(v);
                }

                if (walk(0) == n) {
                        cout << "good" << endl;
                } else {
                        cout << "bad" << endl;
                }
        }

        return 0;
}

int walk(int start) {
        vector<bool> added(n, false);
        queue<int> q;
        q.push(start);
        added[start] = true;

        int count(0);
        while (!q.empty()) {
                int u = q.front();
                ++count;
                q.pop();

                for (vector<int>::const_iterator ii = graph[u].begin();
                     ii != graph[u].end();
                     ++ii) {
                        if (!added[*ii]) {
                                q.push(*ii);
                                added[*ii] = true;
                        }
                }
        }

        return count;
}
