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
    vector<int> arr(n);
    for (int i = 0; i < n; i++)
        ifs >> arr[i];
    for (int i = 0; i < n; i++)
    {
        int min_index=0;
        int min_value=arr[i];
        for(int j=i+1;j<n;j++)
        {
            if (min_value > arr[j])
                    {
                        min_value = arr[j];
                        min_index = j;
                    }
        }
        if(min_index!=0){
            swap(arr[i],arr[min_index]);
            ofs << "Swap elements at indices " << i + 1 << " and " << min_index + 1 << "." << endl;
        }
        
    }
    ofs << "No more swaps needed." << endl;
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
