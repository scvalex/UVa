#include <iostream>
#include <cmath>
#include <vector>

using namespace std;

int main(int argc, char *argv[]) {

        unsigned n;
        for (cin >> n; n != 0; cin >> n) {
                if (floor(sqrt(n)) != ceil(sqrt(n))) {
                        cout << "no" << endl;
                } else {
                        cout << "yes" << endl;
                }
        }

        return 0;
}
