package files;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ReadSgmFile {
    private final String path;

    public ReadSgmFile(String path) {
        this.path = path;
    }

    public static List<String> fileReader(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error while reading lines from file %s", path));
        }
    }

    public List<List<String>> readFiles() {
        return getFilesFromDirectory().stream()
                .map(this::fileLineLoader)
                .collect(Collectors.toList());
    }

    private List<File> getFilesFromDirectory() {
        try {
            return Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new RuntimeException("Error while reading files in directory" + path);
        }
    }

    private List<String> fileLineLoader(File file) {
        try {
            return Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            log.info(e.getMessage());
            throw new RuntimeException(String.format("Error while reading lines from file %s", file));
        }
    }
}
