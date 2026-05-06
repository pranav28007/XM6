#include <stdio.h>
#include <stdlib.h>

#define MAX 50

int main()
{
    int disk[MAX], n, i, files, start, len;
    int allocated[MAX];

    // Initialize disk blocks as free (0)
    for(i = 0; i < MAX; i++)
        disk[i] = 0;

    printf("Enter number of files: ");
    scanf("%d", &files);

    for(int f = 1; f <= files; f++)
    {
        printf("\nEnter starting block for file %d: ", f);
        scanf("%d", &start);

        printf("Enter number of blocks needed for file %d: ", f);
        scanf("%d", &len);

        // Check if start is free
        if(disk[start] == 1)
        {
            printf("Starting block already allocated! File cannot be allocated.\n");
            continue;
        }

        // Mark all allocated blocks list
        int count = 0;
        allocated[count++] = start;

        disk[start] = 1;

        // Allocate remaining blocks
        for(i = 1; i < len; i++)
        {
            int b;
            printf("Enter next block for file %d: ", f);
            scanf("%d", &b);

            if(b < 0 || b >= MAX)
            {
                printf("Invalid block number!\n");
                i--;
                continue;
            }

            if(disk[b] == 1)
            {
                printf("Block %d already allocated! Choose another.\n", b);
                i--;
                continue;
            }

            disk[b] = 1;
            allocated[count++] = b;
        }

        // Display linked allocation chain
        printf("\nFile %d Allocated Blocks (Linked List Form):\n", f);
        for(i = 0; i < count; i++)
        {
            printf("%d", allocated[i]);
            if(i != count - 1)
                printf(" -> ");
        }
        printf(" -> NULL\n");
    }

    // Display Disk Status
    printf("\n\nDisk Allocation Status:\n");
    printf("Block No\tStatus\n");
    for(i = 0; i < MAX; i++)
    {
        printf("%d\t\t%s\n", i, disk[i] ? "Allocated" : "Free");
    }

    return 0;
}

