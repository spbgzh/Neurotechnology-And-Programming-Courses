#include "edx-io.hpp"
#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <random>
#include <sstream>
#include <stack>
#include <cstring>
#include <queue>
using namespace std;

int main()
{
    ifstream ifs;
    ofstream ofs;
    ifs.open("input.txt", ios::in);
    ofs.open("output.txt", ios::out);
    vector<char> stk;

    int n;
    string str;
    ifs >> n;
    ifs.get();
    bool ok = true;

    for (int i = 0; i < n; i++)
    {
        getline(ifs, str);
        ok = true;
        for (char j : str)
        {
            if (j == '(' || j == '[')
            {
                stk.push_back(j);
            }
            else if (
                (j == ')' && stk.back() == '(') || (j == ']' && stk.back() == '['))
            {
                stk.pop_back();
            }
            else
            {
                ok = false;
                break;
            }
        }
        if (!stk.empty())
            ok = false;
        if (ok)
            ofs << "YES\n";
        else
            ofs << "NO\n";
        stk.clear();
    }

    ifs.close();
    ofs.close();
    return 0;
}
