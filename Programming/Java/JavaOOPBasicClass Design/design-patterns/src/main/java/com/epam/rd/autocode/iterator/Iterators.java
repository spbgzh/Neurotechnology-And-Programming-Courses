package com.epam.rd.autocode.iterator;

import java.util.*;

class Iterators {
    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array){
        return new Iterator<Integer>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < array.length * 2;

            }

            @Override
            public Integer next() {
                List<Integer> newarray = new ArrayList<>();
                for ( int i = 0; i< array.length * 2; i++){
                    newarray.add(array[i/2]);

                }
                if(this.hasNext())return newarray.get(index++);
                else throw new NoSuchElementException();
            }
        };
    }
    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
            return new Iterator<Integer>() {
                private int index = 0;
                @Override
                public boolean hasNext() {
                    return index < array.length * 3;

                }

                @Override
                public Integer next() {
                    List<Integer> newarray = new ArrayList<>();
                    for ( int i = 0; i< array.length * 3; i++){
                        newarray.add(array[i/3]);

                    }
                    if(this.hasNext())return newarray.get(index++);
                    else throw new NoSuchElementException();
                }
            };
        }



    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < array.length * 5;

            }

            @Override
            public Integer next() {
                List<Integer> newarray = new ArrayList<>();
                for ( int i = 0; i< array.length * 5; i++){
                    newarray.add(array[i/5]);

                }
                if(this.hasNext())return newarray.get(index++);
                else throw new NoSuchElementException();
            }
        };
    }





    public static Iterable<String> table(String[] columns, int[] rows){
        class It implements Iterable<String>{
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    int c = columns.length;
                    int r = rows.length;
                    int i = 0;
                    int j = 0;
                    @Override
                    public boolean hasNext() {
                        if((i+1 < c)||((i+1 == c)&&(j < r))){
                            return true;
                        }
                        return false;
                    }
                    @Override
                    public String next() {
                        if(j == r-1){
                            j = 0;
                            return (String) columns[i++] + rows[r-1];
                        } else{
                            return (String) columns[i] + rows[j++];
                        }
                    }
                };
            }
        }
        return new It();
    }






}
