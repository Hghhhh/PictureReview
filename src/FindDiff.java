import java.awt.*;
import java.util.*;
import java.util.List;

public class FindDiff {

    /**
     * 搜素岛屿
     *
     * @param islandMap
     * @return
     */
    public static Map<Integer, Point[]> findIsland(int[][] islandMap) {

        Map<Integer, Point[]> zoneMap = new HashMap<Integer, Point[]>();
        for (int i = 0; i < islandMap.length; i++) {
            int[] row = islandMap[i];
            for (int j = 0; j < row.length; j++) {
                int pointValue = row[j];
                if (pointValue == 1) {

                    int zoneID = computeIndexAdjustZone(zoneMap, i, j);
                    Point[] points = zoneMap.get(zoneID);
                    if (points == null) {
                        points = new Point[0];
                    }
                    Point[] tempPoints = new Point[points.length + 1];

                    for (int k = 0; k < points.length; k++) {
                        Point point = points[k];
                        tempPoints[k] = point;
                    }
                    tempPoints[points.length] = new Point(i, j);
                    zoneMap.put(zoneID, tempPoints);
                }

            }

        }
        return zoneMap;
    }

    /**
     * 打印岛屿
     *
     * @param zoneMap
     */
    public static void printIsland(Map<Integer, Point[]> zoneMap) {
        System.out.println(zoneMap.size());
        for (Map.Entry<Integer, Point[]> entry : zoneMap.entrySet()) {
            System.out.println("-----------" + entry.getKey());
            System.out.println(Arrays.asList(entry.getValue()));
        }
    }

    /**
     * 计算区域编号并调整map
     *
     * @param mapZone
     * @param i
     * @param j
     * @return
     */
    public static int computeIndexAdjustZone(Map<Integer, Point[]> mapZone, int i, int j) {

        if (mapZone.size() == 0) {
            return 0;
        }
        // 上下左右变化量，0位x变化量，1位y的变化量
        int[][] coording = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

        List<Integer> keylist = new ArrayList<Integer>();
        for (int x = 0; x < coording.length; x++) {
            Point p = new Point(coording[x][0] + i, coording[x][1] + j);
            for (Map.Entry<Integer, Point[]> entry : mapZone.entrySet()) {
                Point[] points = entry.getValue();
                for (int k = 0; k < points.length; k++) {
                    if (p.equals(points[k])) {
                        // 搜索到点可能属于多个区域，这个时候我们需要记录下来多个区域的 index
                        keylist.add(entry.getKey());
                    }
                }
            }
        }

        if (keylist.size() > 0) {
            // 将多个区域排序
            Collections.sort(keylist);
            // 取出最小的区域 index
            Integer minIndex = keylist.get(0);
            Point[] zonePoints = mapZone.get(minIndex);
            // 如果一个点位属于多个区，那么这个点就是连接点，那么需要将两个区域合并，并且按照最小索引合并
            for (Integer index : keylist) {
                if (index != minIndex) {
                    Point[] points = mapZone.remove(index);
                    if (points == null)
                        continue;

                    Point[] tempPoints = new Point[zonePoints.length + points.length];
                    System.arraycopy(zonePoints, 0, tempPoints, 0, zonePoints.length);
                    System.arraycopy(points, 0, tempPoints, zonePoints.length, points.length);
                    zonePoints = tempPoints;
                }
            }
            mapZone.put(minIndex, zonePoints);
            return minIndex;
        }

        return autoIncrementZoneIndex(mapZone);
    }

    /**
     * 自动递增zone
     *
     * @param mapZone
     * @return
     */
    public static int autoIncrementZoneIndex(Map<Integer, Point[]> mapZone) {

        // 如果没有找到所属区域，那么自动新增一个区域index
        Set<Integer> keys = mapZone.keySet();
        Iterator<Integer> iterator = keys.iterator();
        int index = 0;
        while (iterator != null && iterator.hasNext()) {
            Integer next = iterator.next();
            index = Math.max(index, next);
        }
        if (index == 0 && mapZone.size() == 1) {
            return 1;
        }

        return index + 1;
    }
}
