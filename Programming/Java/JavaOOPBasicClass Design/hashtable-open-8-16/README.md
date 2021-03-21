# Hashtable Open 8-16

## Description 
Implement [`com.epam.rd.autocode.hashtableopen816.HashtableOpen8to16`](src/main/java/com/epam/rd/autocode/hashtableopen816/HashtableOpen8to16.java) `getInstance` method.

It should return a `HashtableOpen8to16` instance.

There are several specifics of this hashtable implementation.
- Hashtable implementation must support open linear addressing.
- Initial capacity is 8.
- Maximum capacity is 16. Insertion of more elements must raise an `IllegalStateException`. 
- Capacity should be doubled each time a new element arrives and all the buckets are already occupied.\
    E.g. if there are 8 buckets that are already filled, and a new key is about to be added, capacity must be increased. 
- Capacity should be halved each time an element is removed and size is not zero and size to capacity ratio reaches 1/4 after the removal.\
    E.g. if there are 8 buckets and 3 elements, then after removing of one of them, capacity must be shrunk to 4.\
    Another example: if there are 4 buckets and 2 elements, removal of one of them leads to shrinking capacity to 2.
    Note, that in case of 2 buckets and 1 element removal of that element leads to zero size and in this case there is no shrinking, so minimum capacity of the implementation is effectively 2.
- Also, you must implement additional `keys()` method. It should return an int array representing distribution of keys in hashtable bucket array.