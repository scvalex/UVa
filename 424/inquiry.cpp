#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> total;

int main(int argc, char *argv[]) {
        string s;
        for (cin >> s; s != "0"; cin >> s) {
                int c(0);
                unsigned i(0);
                for (; i < s.size() || c != 0; ++i) {
                        if (i+1 > total.size()) {
                                total.push_back(0);
                        }
                        int a = 0;
                        if (i < s.size()) {
                                a = s[s.size() - i - 1] -'0';
                        }
                        total[i] += a + c;
                        c = 0;
                        if (total[i] > 9) {
                                c = total[i] / 10;
                                total[i] %= 10;
                        }
                }
        }
        bool start(true);
        for (vector<int>::reverse_iterator ii = total.rbegin();
             ii != total.rend();
             ++ii) {
                if (start && *ii == 0) {
                        continue;
                } else {
                        start = false;
                }
                cout << *ii;
        }
        if (start) {
                cout << 0;
        }
        cout << endl;

        return 0;
}
