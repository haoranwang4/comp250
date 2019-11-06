#include <stdio.h>
#include <stdlib.h>
#include <string.h>




int main() {
    struct STUDENT{
        int age;
        float GPA;
        char name[10];
    };

int n;

printf("enter n:");

scanf("%d",&n);
 struct STUDENT*  students;
 students = malloc(n* sizeof(struct STUDENT));
 if(students == NULL) exit(1);

 for(int i =0;i<n;++i){
     printf("pls enter your name,age,GPA:\n");
     scanf("%s %d %f",(students+i)->name,&((students+i)->age),&((students+i)->GPA));
     printf("%s %d %f\n",(students+i)->name,(students+i)->age,(students+i)->GPA);

 }




    return 0;
}