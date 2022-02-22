#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
using namespace std;

void Merge(vector<int> &Array, int front, int mid, int end, ofstream *p)
{
    int i = front;
    int j = mid;
    vector<int> list;
    while (i < mid || j < end + 1)
    {
        if (j == end + 1 || (i < mid && Array[i] <= Array[j]))
        {
            list.push_back(Array[i]);
            i++;
        }
        else
        {
            list.push_back(Array[j]);
            j++;
        }
    }
    i = front;
    for (auto k : list)
    {
        Array[i] = k;
        i++;
    }

    *p << front + 1 << ' ' << end + 1 << ' ' << Array[front] << ' ' << Array[end] << endl;
}
void MergeSort(vector<int> &Array, int front, int end, ofstream *p)
{
    int flag = end - front + 1;
    if (flag <= 1)
        return;
    MergeSort(Array, front, front + flag / 2 - 1, p);
    MergeSort(Array, front + flag / 2, end, p);

    Merge(Array, front, front + flag / 2, end, p);
}

int main()
{
    ifstream ifs;
    ofstream ofs;
    ifs.open("input.txt", ios::in);
    ofs.open("output.txt", ios::out);
    int n;
    ifs >> n;
    vector<int> arr;
    int temp;
    for (int i = 0; i < n; i++)
    {
        ifs >> temp;
        arr.push_back(temp);
    }

    MergeSort(arr, 0, n - 1, &ofs);
    for (int i = 0; i < n; i++)
    {
        ofs << arr[i] << ' ';
    }

    ifs.close();
    ofs.close();
    return 0;
}
