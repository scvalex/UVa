#include <iostream>

using namespace std;

int f[101];

int f91(int n);

int main(int argc, char *argv[]) {
        for (int i(0); i < 101; ++i) {
                f[i] = -1;
        }

        int n;
        for (cin >> n; n != 0; cin >> n) {
                cout << "f91(" << n << ") = " << f91(n) << endl;
        }

        return 0;
}

int f91(int n) {
        if (n >= 101)
                return n - 10;

        if (f[n] == -1) {
                f[n] = f91(f91(n + 11));
        }

        return f[n];
}
