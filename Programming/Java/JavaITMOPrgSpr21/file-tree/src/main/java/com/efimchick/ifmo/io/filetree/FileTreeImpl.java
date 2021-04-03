package com.efimchick.ifmo.io.filetree;

import java.io.File;
import java.nio.file.Path;
import java.util.*;

public class FileTreeImpl implements FileTree {

    private final List<String> stringList = new ArrayList<>();

    @Override
    public Optional<String> tree(Path path) {
        if (path == null) {
            return Optional.empty();
        }

        File file = new File(path.toString());
        StringBuilder s = new StringBuilder();
        if (file.exists() && file.isFile()) {
            s.append(file.getName()).append(" ").append(file.length()).append(" bytes");
            return Optional.of(s.toString());
        }

        if (file.exists() && file.isDirectory()) {
            method(file, 0, false);
            StringBuilder str = new StringBuilder();
            for (String str1 : stringList) {
                str.append(str1);
            }
            return Optional.of(str.toString());
        }

        return Optional.empty();
    }

    public long sizeFolder(File file, long n) {
        if (file.isFile())
            return n + file.length();

        File[] files = file.listFiles();
        List<File> fileList = new ArrayList<File>(Arrays.asList(files));
        fileList.sort(new Comparator<File>() {
            @Override
            public int compare(File file, File t1) {
                if (file.isDirectory() && t1.isFile())
                    return -1;
                if (file.isFile() && t1.isDirectory())
                    return 1;
                return file.getName().toLowerCase().compareTo(t1.getName().toLowerCase());
            }
        });

        for (File file1 : fileList) {
            n = sizeFolder(file1, n);
        }
        return n;
    }

    public void method(File file, int n, boolean last) {
        if (file.isFile()) {
            if (!last) {
                if (n > 1) {
                    stringList.add(strMethod(stringList.get(stringList.size() - 1), n) + "├─ " + file.getName() + " "
                            + file.length() + " bytes\n");
                } else {
                    stringList.add("├─ " + file.getName() + " " + file.length() + " bytes\n");
                }
            } else {
                if (n > 1) {
                    stringList.add(strMethod(stringList.get(stringList.size() - 1), n) + "└─ " + file.getName() + " "
                            + file.length() + " bytes\n");
                } else {
                    stringList.add("└─ " + file.getName() + " " + file.length() + " bytes\n");
                }
            }
        }
        // if (file.isDirectory() && Objects.requireNonNull(file.list()).length==0)
        // return "\t"+ file.getName()+"\n";
        if (file.isDirectory()) {
            if (n == 0)
                stringList.add(file.getName() + " " + sizeFolder(file, 0) + " bytes\n");
            else if (!last) {
                if (n > 1)
                    stringList.add(strMethod(stringList.get(stringList.size() - 1), n) + "├─ " + file.getName() + " "
                            + sizeFolder(file, 0) + " bytes\n");
                else
                    stringList.add("├─ " + file.getName() + " " + sizeFolder(file, 0) + " bytes\n");
            } else {
                if (n > 1)
                    stringList.add(strMethod(stringList.get(stringList.size() - 1), n) + "└─ " + file.getName() + " "
                            + sizeFolder(file, 0) + " bytes\n");
                else
                    stringList.add("└─ " + file.getName() + " " + sizeFolder(file, 0) + " bytes\n");
            }
            // str+= "\t" + file.getName() + " " + sizeFolder(file, 0) + " bytes\n";
            File[] files = file.listFiles();

            List<File> fileList = new ArrayList<File>(Arrays.asList(files));
            fileList.sort(new Comparator<File>() {
                @Override
                public int compare(File file, File t1) {
                    if (file.isDirectory() && t1.isFile())
                        return -1;
                    if (file.isFile() && t1.isDirectory())
                        return 1;
                    return file.getName().toLowerCase().compareTo(t1.getName().toLowerCase());
                }
            });
            for (int i = 0; i < fileList.size(); i++) {
                if (i == fileList.size() - 1)
                    method(fileList.get(i), n + 1, true);
                else
                    method(fileList.get(i), n + 1, false);
            }

        }

    }

    public String strMethod(String s, int n) {
        StringBuilder str = new StringBuilder();
        int i = 0;
        while (i < n - 1) {
            String s1 = s.substring(i * 3, i * 3 + 3);
            if (s1.equals("   ") || s1.equals("└─ "))
                str.append("   ");
            else
                str.append("│  ");
            i++;
        }
        return str.toString();
    }

}