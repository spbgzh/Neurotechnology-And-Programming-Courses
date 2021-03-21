# Collections. Count words

## Description
Count words in a huge text and return statistics.

## Details
Implement `countWords` method in [Words](src/main/java/com/efimchick/ifmo/collections/countwords/Words.java) class.

Input parameter is a list of strings representing lines of text.

Count words - map lowercased words to its frequency in text.\
If the word *"котик"* occurred in text 23 times then its entry would be *"котик - 23\n"*.\
Omit any word with length less than **4** and frequency less than **10** (like too small or too rare words)
Return a String having all the entries.\
Entries in the resulting String should be also sorted by amount and then in alphabetical order if needed.

*You may not use streams or lambdas in your code*