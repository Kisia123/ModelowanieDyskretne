public class Neighbour {
    static int findNeighbours(int[][] tab, int x, int y) {
        int num = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                int indexI = i;
                int indexJ = j;
                if (i < 0) {
                    indexI = tab.length - 1;
                }
                if (j < 0) {
                    indexJ = tab.length - 1;
                }
                if (i > tab.length - 1) {
                    indexI = 0;
                }
                if (j > tab.length - 1) {
                    indexJ = 0;
                }
                if (indexI == x && indexJ == y) {
                    continue;
                }
                if (tab[indexI][indexJ] == 1) {
                    num++;
                }
            }
        }
        return num;
    }
}
