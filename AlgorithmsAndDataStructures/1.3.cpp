#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

int main()
{
    ifstream ifs;
    ofstream ofs;
    ifs.open("input.txt", ios::in);
    ofs.open("output.txt", ios::out);
    int n;
    ifs >> n;
    vector<int> arr(n);
    vector<int> pos(n);
    for (int i = 0; i < n; i++)
        ifs >> arr[i];
    pos[0] = 1;
    for (int i = 1; i < n; i++)
    {
        int key = arr[i];
        int j = i - 1;
        while ((j >= 0) && (key < arr[j]))
        {
            arr[j + 1] = arr[j];
            j--;
        }
        pos[i] = j + 2;
        arr[j + 1] = key;
    }
    for (size_t i = 0; i < n; i++)
    {
        ofs << pos[i];
        if (i != n - 1)
            ofs << ' ';
        else
            ofs << endl;
    }
    for (size_t i = 0; i < n; i++)
    {
        ofs << arr[i];
        if (i != n - 1)
            ofs << ' ';
        else
            ofs << endl;
    }

    ifs.close();
    ofs.close();
    return 0;
}
