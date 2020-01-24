/*
Name : Rishab Dhamija
Student no: 213223334
EECS 2031 Section M
Assignment : 2
Question: 1
*/
#include <stdio.h>
#include <stdlib.h>
 //basically, we achieve the desired output by dividing the figure
 //into 5 small rectangles and drawing them simultaneously as per the row number
int main()
{
    int rows;
    printf("Enter the size of the triangle\n");
    scanf("%d", &rows);
    //i behaves as the loop counter
    for(int i=0;i<rows-1;i++)   //9 times
    {
        //first, we print the number of sapces in a line
        //this loop is rows-1 times as the last row of the triangle will be printed separately
        for(int j=rows-1;j>i;j--)
        {
            printf(" ");
        }
        //it is then followed by one * symbol
        printf("*");
        //then we print another set of spaces included in the line triangle1
        for(int j=i;j>0;j--)
        {
            printf(" ");
        }
        // triangle 2 of spaces
        for(int j=i;j>1;j--)
        {
            printf(" ");
        }
        //finally, the last * to mark the end of the row
        if(i!=0)
        {
            printf("*");
        }
        printf("\n");
    }
    //last row has 2(row) -1 * to be printed
    for(int i=1;i<(2*rows);i++)
    {
        printf("*");
    }
    printf("\n");
    return 0;

}

