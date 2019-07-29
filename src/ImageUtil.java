
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

public class ImageUtil {

    public static BufferedImage readImageFormLocal(String imageFile) throws IOException {

        BufferedImage bf = ImageEncode.readImage(new File(imageFile));

        return bf;
    }

    public static BufferedImage readImageFormWeb(String imgUrl) throws Exception {
        // 构造URL
        URL url = new URL(imgUrl);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bf;
    }

    public static int[][] convertImageToArray(BufferedImage bf) {
        // 获取图片宽度和高度
        int width = bf.getWidth();
        int height = bf.getHeight();
        // 将图片RGB数据写入一维数组
        int[] data = new int[width*height];
        bf.getRGB(0, 0, width, height, data, 0, width);
        // 将一维数组转换为二维数组
        int[][] rgbArray = new int[height][width];
        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++)
                rgbArray[i][j] = data[i*width + j];
        return rgbArray;
    }

    public static void writeImageFromArray(String imageFile, String type, int[][] rgbArray){
        // 获取数组宽度和高度
        int width = rgbArray[0].length;
        int height = rgbArray.length;
        // 将二维数组转换为一维数组
        int[] data = new int[width*height];
        for(int i = 0; i < height; i++)
            for(int j = 0; j < width; j++)
                data[i*width + j] = rgbArray[i][j];
        // 将数据写入BufferedImage
        BufferedImage bf = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        bf.setRGB(0, 0, width, height, data, 0, width);
        // 输出图片
        try {
            File file= new File(imageFile);
            ImageIO.write((RenderedImage)bf, type, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * java修改图片的宽高
     * */
    public static int[][] getImgArray(String inPath, int width, int height) throws Exception {
        BufferedImage bufferedImage = readImageFormLocal(inPath);
        BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_BGR);
        Graphics garphics = image.createGraphics();
        garphics.drawImage(bufferedImage, 0, 0, width, height, null);
        return convertImageToArray(image);
    }
}