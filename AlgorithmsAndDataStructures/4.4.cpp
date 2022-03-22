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


 class MinQueue {
    queue<int> data;
    deque<int> min;
public:
    MinQueue() {};
    
    void push(int x) {
        /** add element **/
        data.push(x);
        while (!min.empty() && x<min.back()) {
            min.pop_back();
        }
        min.push_back(x);
    }
    
    bool empty() {
        /** if queue is empty **/
        return data.empty();
    }
    
    void pop() {
        /** pop last element **/
        if (empty()) return;
        int res = data.front(); data.pop();
        if (res==min.front()) {
            min.pop_front();
        }
    }
    
    int front() {
        /** front of queue **/
        if (empty()) return -1;
        return data.front();
    }
    
    int getMin() {
        /** minimum element in queue **/
        if (empty()) return -1;
        return min.front();
    }
};


int main()
{
    ifstream ifs;
    ofstream ofs;
    ifs.open("input.txt", ios::in);
    ofs.open("output.txt", ios::out);

    MinQueue stk;

    int n;

    ifs >> n;
    ifs.get();
    string temp;
    for (int i = 0; i < n; i++)
    {
        getline(ifs, temp);
        switch (temp[0])
        {
        case '+':
        {
            int num = stoi(temp.substr(2));
            stk.push(num);
            break;
        }
        case '-':
        {
            stk.pop();
            break;
        }
        case '?':
        {
            ofs << stk.getMin() << "\n";
            break;
        }
        }
    }

    ifs.close();
    ofs.close();
    return 0;
}
