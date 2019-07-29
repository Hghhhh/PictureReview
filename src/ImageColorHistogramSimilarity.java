import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ImageColorHistogramSimilarity {


    public double colorHistogramSimilarity(BufferedImage src1, BufferedImage src2) throws Exception {
        double[] array1 = this.colorHistogramSimilarity(src1);
        double[] array2 = this.colorHistogramSimilarity(src2);
        for(int i=0;i<array1.length;i++){
            System.out.print(array1[i]+" ");
        }
        System.out.println("");
        for(int i=0;i<array2.length;i++){
            System.out.print(array2[i]+" ");
        }
        System.out.println("");
        System.out.println(PearsonCalc.calc(array1, array2));
        System.out.println(CosineSimilarityCalc.calc(array1,array2));
        return 0;
    }

    /**
     0-15
     16-31
     32-47
     48-63
     64-79
     80-95
     96-111
     112-127
     128-143
     144-159
     160-175
     176-191
     192-207
     208-223
     224-239
     240-255
     */
    private String getRange(int color) {
        if (color >= 0 && color <= 15) {
            return "0";
        } else if (color >= 16 && color <= 31) {
            return "1";
        } else if (color >= 32 && color <= 47) {
            return "2";
        } else if (color >= 48 && color <= 63) {
            return "3";
        } else if (color >= 64 && color <= 79) {
            return "4";
        } else if (color >= 80 && color <= 95) {
            return "5";
        } else if (color >= 96 && color <= 111) {
            return "6";
        } else if (color >= 112 && color <= 127) {
            return "7";
        }else if (color >= 128 && color <= 143) {
            return "8";
        } else if (color >= 144 && color <= 159) {
            return "9";
        }else if(color >= 160 && color <= 175){
            return "10";
        } else if (color >= 176 && color <= 191) {
            return "11";
        } else if (color >= 192 && color <= 207) {
            return "12";
        } else if (color >= 208 && color <= 223) {
            return "13";
        }  else if (color >= 224 && color <= 239) {
            return "14";
        } else if (color >= 240 && color <= 255) {
            return "15";
        }
        return null;
    }

    public double[] colorHistogramSimilarity(BufferedImage src) {
        int width = src.getWidth();
        int height = src.getHeight();
        Map<String, Integer> imageHistogram = new HashMap<String, Integer>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = src.getRGB(j, i);
                int r = (rgb >> 16) & 0xff;//取出次高位（16-23）红色分量的信息
                int g = (rgb >> 8) & 0xff;//取出中位（8-15）绿色分量的信息
                int b = rgb & 0xff;//取出低位（0-7）蓝色分量的信息
                String key = this.getRange(r) +" "+ this.getRange(g) +" "+ this.getRange(b);
                Integer count = imageHistogram.get(key);
                if (count == null) {
                    imageHistogram.put(key, 1);
                } else {
                    imageHistogram.put(key, count + 1);
                }
            }
        }
        imageHistogram.forEach((k,v)->{System.out.print(k+":"+v+" ");});
        System.out.println("");
        double[] result = new double[4096];
        String key = "";
        int idx = 0;
        for (int r = 0; r < 16; r++) {
            for (int g = 0; g < 16; g++) {
                for (int b = 0; b < 16; b++) {
                    key = String.valueOf(r) +" "+ String.valueOf(g) +" "+ String.valueOf(b);
                    Integer count = imageHistogram.get(key);
                    if (count == null) {
                        result[idx] = 0;
                    } else {
                        result[idx] = count;
                    }
                    idx++;
                }
            }
        }
        return result;
    }
}