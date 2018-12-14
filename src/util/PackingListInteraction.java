package util;

import functions.PackingList;

public class PackingListInteraction {
    public static void removeFromPackingList(PackingList packingList, int removePos) {
        if (removePos <= packingList.getPl().size() && removePos >= 0) {
            packingList.getPl().remove(removePos - 1);
        }
    }

    public static String packingListDisplayHelper(PackingList packingList) {
        String plString = "Things you still need to pack:";
        for (int i = 0; i < packingList.getPl().size(); i++) {
            int num = i + 1;
            plString = plString + "\n" + num + ". " + packingList.getPl().get(i);
        }
        return plString;
    }

    public static void resetPackingList(PackingList packingList) {
        packingList.reset();
    }
}
