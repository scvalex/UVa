#include <cmath>
#include <iostream>
#include <vector>

using namespace std;

int main(int argc, char *argv[]) {
        int n;
        cin >> n;
        int l;
        for (; n > 0; --n) {
                cin >> l;
                vector<int> cs(l);
                for (int i(0); i < l; ++i) {
                        cin >> cs[i];
                }
                int swaps(0);
                for (int i(1); i <= l; ++i) {
                        int j(0);
                        for (; j < l; ++j) {
                                if (cs[j] == i)
                                        break;
                        }
                        for (; j >= i; --j) {
                                ++swaps;
                                int aux = cs[j];
                                cs[j] = cs[j-1];
                                cs[j-1] = aux;
                        }
                }
                cout << "Optimal train swapping takes " << swaps
                     << " swaps." << endl;
        }

        return 0;
}
