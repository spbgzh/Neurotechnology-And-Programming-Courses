# File Tree

## Description
Build a String representation of directory hierarchy under a given path  

## Details
Implement `tree` method in [FileTreeImpl](src/main/java/com/efimchick/ifmo/io/filetree/FileTreeImpl.java) class.

Input parameter is a path.

If a given path *does not exist*, return an empty Optional.

If a given path *refers to a file*, return a String with its name and size like this: 
    
    some-file.txt 128 bytes
    
IF a given path *refers to a directory*, return a String with its name, total size and its full hierarchy:

    some-dir 100 bytes
    ├─ some-inner-dir 50 bytes
    │  ├─ some-file.txt 20 bytes    
    │  └─ some-other-file.txt 30 bytes
    └─ some-one-more-file.txt 50 bytes
    
- Use pseudo graphic symbols to format output.
- Compute directory size as a sum of all its contents.
- Sort content in following way:
    - directories go first,
    - directories and files are sorted in lexicographic order (case-insensitive).
