#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
using namespace std;

int num = 1;
class villager
{
public:
    int id;
    double salary;
    villager(double sal)
    {
        id = num;
        salary = sal;
        num++;
    }
};

int main()
{
    ifstream ifs;
    ofstream ofs;
    ifs.open("input.txt", ios::in);
    ofs.open("output.txt", ios::out);
    int n;
    ifs >> n;
    double temp;
    vector<villager> arr;
    for (int i = 0; i < n; i++)
    {
        ifs >> temp;
        arr.push_back(villager(temp)) ;
    }
    sort(arr.begin(), arr.end(),[](villager x,villager y){return x.salary<y.salary;});

    ofs<<arr[0].id<<' '<<arr[n/2].id<<' '<<arr[n-1].id<<endl;
    ifs.close();
    ofs.close();
    return 0;
}
