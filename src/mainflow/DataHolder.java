package mainflow;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public final class DataHolder {
    private int metricSwitch = 3;
    private ArrayList<Integer> attributes;
    private double testingRange;
    private String directoryPath;
    private List<String> stopList; //todo add this to StopList class as a parameter
    private static DataHolder INSTANCE = new DataHolder();

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        return INSTANCE;
    }
}
