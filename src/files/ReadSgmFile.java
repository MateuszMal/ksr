package files;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ReadSgmFile {
    private String PATH = "data";

    public List<List<String>> readFiles() {
        return getFilesFromDirectory().stream()
                .map(this::fileLineLoader)
                .collect(Collectors.toList());
//        List<List<String>> articles = new ArrayList<>();
//        List<File> filesFromDirectory = getFilesFromDirectory();
//        for (int i = 0; i < filesFromDirectory.size(); i++) {
//            articles.add(fileLineLoader(filesFromDirectory.get(0)));
//        }
//        return articles;
    }

    private List<File> getFilesFromDirectory() {
        try {
            return Files.walk(Paths.get(PATH))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        }
        catch (IOException e) {
            throw new RuntimeException("Error while reading files in directory" + PATH);
        }
    }


    private List<String> fileLineLoader(File file) {
        try {
            return Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new RuntimeException("Error while reading lines from file: " + file);
        }
    }
}
