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
    queue<string> stk;

    int n;
    string temp;
    ifs >> n;
    ifs.get();
    for (int i = 0; i < n; i++)
    {
        getline(ifs, temp);
        if (temp[0] == '-')
        {
            ofs << stk.front() << endl;
            stk.pop();
        }
        else
            stk.push(temp.substr(2));
    }
    ifs.close();
    ofs.close();
    return 0;
}
