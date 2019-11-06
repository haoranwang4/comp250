//
// Created by  haoran wang on 2019-11-06.
//

#include "person.h"
#include <string.h>
struct PERSON array[10];//private
int nextSpot = 0;//private

char* getPersonName(int ID){
    return array[ID].name;
}
void addPerson (char* name,int a){
    strcpy(array[nextSpot].name,name);
    array[nextSpot].age = a;
    nextSpot++;
}
