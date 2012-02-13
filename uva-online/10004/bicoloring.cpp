#include <iostream>
#include <vector>

using namespace std;

vector<vector<int> > graph;
vector<int> colour;

bool walk(int u);

int main(int argc, char *argv[]) {
        int n;
        for (cin >> n; n != 0; cin >> n) {
                int m;
                cin >> m;
                graph.clear();
                graph.resize(n);
                colour.clear();
                colour.resize(m, 0);
                for (int i(0); i < m; ++i) {
                        int u, v;
                        cin >> u >> v;
                        graph[u].push_back(v);
                        graph[v].push_back(u);
                }

                colour[0] = 1;
                if (walk(0)) {
                        cout << "BICOLORABLE." << endl;
                } else {
                        cout << "NOT BICOLORABLE." << endl;
                }
        }

        return 0;
}

bool walk(int u) {
        for (vector<int>::const_iterator ii = graph[u].begin();
             ii != graph[u].end();
             ++ii) {
                if (colour[*ii] == colour[u]) {
                        return false;
                } else if (colour[*ii] == 0) {
                        colour[*ii] = 3 - colour[u];
                        if (!walk(*ii)) {
                                return false;
                        }
                }
        }
        return true;
}
