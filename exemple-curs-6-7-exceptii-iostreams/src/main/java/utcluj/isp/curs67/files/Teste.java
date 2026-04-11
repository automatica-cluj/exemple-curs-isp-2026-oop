/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utcluj.isp.curs67.files;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author mihai.hulea
 */
public class Teste {
    public static void main(String[] args) throws IOException {
        String folder = Paths.get("data", "files").toAbsolutePath().toString();

        // 1. Create folder
        FilesAndFoldersUtil.createFolder(folder);
        System.out.println("=== Folder created: " + folder);

        // 2. Write using OutputStream (raw bytes)
        String file1 = Paths.get(folder, "test_outputstream.txt").toString();
        FileWriteUtil.writeUsingOutputStream("Written with OutputStream", file1);
        System.out.println("=== writeUsingOutputStream -> " + file1);

        // 3. Write using Files (Java NIO)
        String file2 = Paths.get(folder, "test_files.txt").toString();
        FileWriteUtil.writeUsingFiles("Written with Files.write", file2);
        System.out.println("=== writeUsingFiles -> " + file2);

        // 4. Write using FileWriter
        String file3 = Paths.get(folder, "test_filewriter.txt").toString();
        FileWriteUtil.writeUsingFileWriter("Written with FileWriter", file3);
        System.out.println("=== writeUsingFileWriter -> " + file3);

        // 5. Write using BufferedWriter (multiple lines)
        String file4 = Paths.get(folder, "test_bufferedwriter.txt").toString();
        List<String> lines = Arrays.asList("Line 1 - BufferedWriter", "Line 2 - BufferedWriter", "Line 3 - BufferedWriter");
        FileWriteUtil.writeUsingBufferedWriter(lines, file4);
        System.out.println("=== writeUsingBufferedWriter -> " + file4);

        // 6. Read all lines at once
        System.out.println("\n=== readAllFileLines from " + file4);
        FileReadUtil.readAllFileLines(file4).stream().forEach(System.out::println);

        // 7. Read using BufferedReader (for large files)
        System.out.println("\n=== readUsingBufferedReader from " + file4);
        FileReadUtil.readUsingBufferedReader(file4, StandardCharsets.UTF_8).stream().forEach(System.out::println);

        // 8. List all files in folder
        System.out.println("\n=== Files in folder:");
        FilesAndFoldersUtil.getFilesInFolder(folder).stream().forEach(s -> System.out.println("  " + s));
    }
}
