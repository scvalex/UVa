#include <iostream>

using namespace std;

int main(int argc, char *argv[]) {
        int numTests(0);
        cin >> numTests;
        while (numTests-- > 0) {
                int a, m, n;
                cin >> a >> m >> n;
                cout << (double)a * 2 / 3 << endl;
        }

        return 0;
}
