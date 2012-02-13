#include <cstring>
#include <iostream>

using namespace std;

int n;
int a[101][101];
int r[101][101];

void readData();
void precompute();
int rectSum(int, int, int, int);

int main(int argc, char *argv[]) {
        readData();
        precompute();

        int max(a[0][0]);
        for (int i(0); i < n; ++i) {
                for (int j(0); j < n; ++j) {
                        for (int ii(i); ii < n; ++ii) {
                                for (int jj(j); jj < n; ++jj) {
                                        int s = rectSum(i, j, ii, jj);
                                        // cout << i << " " << j << "; "
                                        //      << ii << " " << jj << " -> "
                                        //      << s << endl;
                                        if (s > max) {
                                                max = s;
                                        }
                                }
                        }
                }
        }

        // for (int i(0); i < n; ++i) {
        //         for (int j(0); j < n; ++j) {
        //                 cout << r[i][j] << " ";
        //         }
        //         cout << endl;
        // }

        cout << max << endl;

        return 0;
}

void readData() {
        cin >> n;
        for (int i(0); i < n; ++i) {
                for (int j(0); j < n; ++j) {
                        cin >> a[i][j];
                }
        }

}

void precompute() {
        for (int i(0); i < n; ++i) {
                int sofar(0);
                for (int j(0); j < n; ++j) {
                        sofar += a[i][j];
                        if (i == 0) {
                                r[i][j] = sofar;
                        } else {
                                r[i][j] = r[i-1][j] + sofar;
                        }
                }
        }
}

int rectSum(int x, int y, int x2, int y2) {
        int s(r[x2][y2]);
        if (x > 0) {
                s -= r[x-1][y2];
        }
        if (y > 0) {
                s -= r[x2][y-1];
        }
        if (x > 0 && y > 0) {
                s += r[x-1][y-1];
        }
        return s;
}
