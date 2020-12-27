#include <string>
#include <unordered_map>
#include "../klee_src/include/klee/klee.h"
using namespace std;
unordered_map<string, int> _agedb;

int must_count_score(pair<int,int> range, int  res) {
    for(auto k: _agedb){
        if(k.second >range.first && k.second <= range.first){
            res=res+1;
        }
        else{
            res = -100;
        }
    }
    if(range.second <=  range.first)
        res = 0;
    if(res==0){
        throw 1;
    }
    if(res<0){
        throw 2;
    }
    return res;
}

void add_people(int age, string name) {
    _agedb[name]  = age;
}
void init(){
    for(int i=0;i<100;i++){
        int age = random() % 100;
        add_people(age, string("name")+to_string(i));
    }
}


int main() {
    int res=0;
    klee_make_symbolic(&res, sizeof(res),"res");
    must_count_score(make_pair(0,50),res);
    init();
}

