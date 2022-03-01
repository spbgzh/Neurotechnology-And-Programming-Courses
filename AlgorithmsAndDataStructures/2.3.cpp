#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
    ifstream ifs;
    ofstream ofs;
    ifs.open("input.txt", ios::in);
    ofs.open("output.txt", ios::out);
    int n;
    ifs >> n;
    vector<int> a;
    for (int i = 1; i <= n; i++)
    {
        a.push_back(i);
        int m = a.size() - 1;
        if (i > 2)
        {
            swap(a[m / 2], a[m]);
        }
    }
    for (auto i : a)
    {
        ofs << i << ' ';
    }
    ifs.close();
    ofs.close();
    return 0;
}
