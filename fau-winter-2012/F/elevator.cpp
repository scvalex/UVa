#include <algorithm>
#include <cmath>
#include <iostream>
#include <limits.h>
#include <vector>

using namespace std;

int n;
vector<int> ts;
vector<int> fs;

void countFloors();
int leastWalks(int a, int b, int c);
int closestStop(int f, const vector<int> &stops);

int main(int argc, char *argv[]) {
        int numTests(0);
        cin >> numTests;

        while (numTests-- > 0) {
                cin >> n;
                ts.clear();
                ts.resize(n);

                for (int i(0); i < n; ++i) {
                        cin >> ts[i];
                }

                countFloors();

                int ms(INT_MAX);
                int mk(-2), mi(-2), mj(-2);
                for (int k(0); k < 14; ++k) {
                        for (int i(1); i < 14; ++i) {
                                for (int j(1); j < 14; ++j) {
                                        if (i != 4 && j != 4) {
                                                int c = leastWalks(i, j, k);
                                                if (c < ms) {
                                                        ms = c;
                                                        mk = k;
                                                        mi = i;
                                                        mj = j;
                                                }
                                        }
                                }
                        }
                }
                cout << ms << " " << (mi-1) << " " << (mj-1) << " " << (mk-1) << endl;
        }

        return 0;
}

void countFloors() {
        sort(ts.begin(), ts.end());
        fs.clear();
        fs.resize(14);
        for (vector<int>::const_iterator ii = ts.begin();
             ii != ts.end();
             ++ii) {
                ++fs[*ii+1];
        }
}

int leastWalks(int a, int b, int c) {
        vector<int> stops;
        stops.push_back(a);
        stops.push_back(b);
        stops.push_back(c);
        stops.push_back(1);
        sort(stops.begin(), stops.end());

        int walks(0);
        for (int i(0); i < 14; ++i) {
                if (fs[i] > 0) {
                        walks += abs(i - closestStop(i, stops)) * fs[i];
                }
        }
        return walks;
}

int closestStop(int f, const vector<int> &stops) {
        int m(INT_MAX);
        int cs(-1);
        for (vector<int>::const_iterator ii = stops.begin();
             ii != stops.end();
             ++ii) {
                if (abs(*ii - f) < m) {
                        m = abs(*ii - f);
                        cs = *ii;
                }
        }
        return cs;
}
