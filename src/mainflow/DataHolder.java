package mainflow;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public final class DataHolder {
    private static DataHolder INSTANCE = new DataHolder();
    private int metricSwitch = 3;
    private ArrayList<Integer> attributes;
    private double testingRange;
    private String directoryPath;
    private List<String> stopList;
    private int kParameter;

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        return INSTANCE;
    }
}
