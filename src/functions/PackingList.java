package functions;

import java.util.ArrayList;
import java.util.Arrays;

public class PackingList {
    // List taken from https://www.rei.com/learn/expert-advice/ten-essentials.html
    private ArrayList<String> mpl = new ArrayList<>(Arrays.asList("Navigation Equipment", "Headlamp", "Sun Protection", "First Aid", "Knife", "First Starters", "Emergency Shelter", "Food", "Water", "Extra Clothes"));
    private ArrayList<String> pl = new ArrayList<>(Arrays.asList("Navigation Equipment", "Headlamp", "Sun Protection", "First Aid", "Knife", "First Starters", "Emergency Shelter", "Food", "Water", "Extra Clothes"));

    public void reset() {
        pl = (ArrayList<String>) mpl.clone();
    }

    public ArrayList<String> getPl() {
        return pl;
    }

    public void setPl(ArrayList<String> pl) {
        this.pl = pl;
    }

    public int getSize() {
        return pl.size();
    }
}
