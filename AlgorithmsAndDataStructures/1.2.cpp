#include <iostream>
#include <fstream>
using namespace std;

int main()
{
    ifstream ifs;
    ofstream ofs;
    ifs.open("input.txt",ios::in);
    ofs.open("output.txt",ios::out);
    long long a,b;
    ifs>>a>>b;
    ofs<<a+b*b<<endl;
    ifs.close();
    ofs.close();
    return 0;
}
