#include <cmath>
#include <iostream>
#include <vector>

using namespace std;

int main(int argc, char *argv[]) {
        for (int set(1); ; ++set) {
                int n;
                cin >> n;
                if (!n) {
                        break;
                }
                vector<int> ss(n);
                int total(0);
                for (int i(0); i < n; ++i) {
                        cin >> ss[i];
                        total += ss[i];
                }
                int avg(total / n);

                int moves(0);
                for (int i(0); i < n; ++i) {
                        moves += abs(ss[i] - avg);
                }

                cout << "Set #" << set << endl;
                cout << "The minimum number of moves is " << (moves / 2)
                     << "." << endl;
                cout << endl;
        }
        return 0;
}
