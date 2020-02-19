package ale.mach.lightcurve.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

@Service
public class DataCourier {


    public static void fetchFile(String url, String fileName) {
        try (ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(url).openStream())) {
            File file = new File("src/main/resources/FitsFiles/" + fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unzip(String fileName) {
        String fileZip = "src/main/resources/FitsFiles/" + fileName;
        Path pathZipped = Paths.get(fileZip);
        File destDir = new File("src/main/resources/FitsFiles/");

        try (ReadableByteChannel readableByteChannel = Channels.newChannel(new GZIPInputStream(new FileInputStream(fileZip)))) {
            File file = new File("src/main/resources/FitsFiles/" + fileName.substring(0, fileName.length() - 3));
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            Files.delete(pathZipped);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
