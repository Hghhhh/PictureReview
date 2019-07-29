import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        int[][] array1 = ImageUtil.getImgArray("D:\\png\\30-1.png",335,320);
        int[][] array2 = ImageUtil.getImgArray("D:\\png\\30-2.png",335,320);
        System.out.println(array1.length+"   "+array1[0].length);
        System.out.println(array2.length+"   "+array2[0].length);
        /*
        for(int i=0;i<array1.length;i++){
            for(int j=0;j<array1[0].length;j++){
                System.out.print(array1[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
        for(int i=0;i<array2.length;i++){
            for(int j=0;j<array2[0].length;j++){
                System.out.print(array2[i][j]);
            }
            System.out.println("");
        }
        */
        int[][] array3 = new int[array1.length][array1[0].length];
        for(int i=0;i<array1.length;i++){
            for(int j=0;j<array1[0].length;j++){
                int r = (array1[i][j] >> 16) & 0xff;//取出次高位（16-23）红色分量的信息
                int g = (array1[i][j] >> 8) & 0xff;//取出中位（8-15）绿色分量的信息
                int b = array1[i][j] & 0xff;//取出低位（0-7）蓝色分量的信息
                int  key = getRange(r)+getRange(g)+getRange(b);
                array1[i][j] = key;
            }
        }
        for(int i=0;i<array2.length;i++){
            for(int j=0;j<array2[0].length;j++){
                int r = (array2[i][j] >> 16) & 0xff;//取出次高位（16-23）红色分量的信息
                int g = (array2[i][j] >> 8) & 0xff;//取出中位（8-15）绿色分量的信息
                int b = array2[i][j] & 0xff;//取出低位（0-7）蓝色分量的信息
                int  key = getRange(r)+getRange(g)+getRange(b);
                array2[i][j] = key;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<array1.length;i++){
            for(int j=0;j<array1[0].length;j++){
                array3[i][j] = Math.abs(array1[i][j]-array2[i][j])>2?1:0;
                if(Math.abs(array1[i][j]-array2[i][j])>2){
                    stringBuilder.append(array1[i][j]+":"+array2[i][j]+" ");
                }
               System.out.print(array3[i][j]+" ");
            }
            System.out.println("");
        }
       // System.out.println(stringBuilder.toString());
       // FindDiff.printIsland(FindDiff.findIsland(array3));
        System.out.println("");
    }


    private static int getRange(int color) {
        if (color >= 0 && color <= 15) {
            return 0;
        } else if (color >= 16 && color <= 31) {
            return 1;
        } else if (color >= 32 && color <= 47) {
            return 2;
        } else if (color >= 48 && color <= 63) {
            return 3;
        } else if (color >= 64 && color <= 79) {
            return 4;
        } else if (color >= 80 && color <= 95) {
            return 5;
        } else if (color >= 96 && color <= 111) {
            return 6;
        } else if (color >= 112 && color <= 127) {
            return 7;
        }else if (color >= 128 && color <= 143) {
            return 8;
        } else if (color >= 144 && color <= 159) {
            return 9;
        }else if(color >= 160 && color <= 175){
            return 10;
        } else if (color >= 176 && color <= 191) {
            return 11;
        } else if (color >= 192 && color <= 207) {
            return 12;
        } else if (color >= 208 && color <= 223) {
            return 13;
        }  else if (color >= 224 && color <= 239) {
            return 14;
        } else if (color >= 240 && color <= 255) {
            return 15;
        }
        return 0;
    }
}
