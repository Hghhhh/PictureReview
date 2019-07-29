import java.util.Map;

public class CosineSimilarityCalc {
    public static double calc(double[] a,double[] b) throws Exception {
        if(a==null || b==null || a.length!=b.length)throw new Exception("数组有问题，请检查图片");
        double fenzi = 0;
        double fenmuA = 0,fenmuB = 0;
        for(int i=0;i<a.length;i++){
            fenzi += a[i]*b[i];
            fenmuA += a[i]*a[i];
            fenmuB += b[i]*b[i];
        }
        fenmuA = Math.sqrt(fenmuA);
        fenmuB = Math.sqrt(fenmuB);
        return fenzi/(fenmuA*fenmuB);
    }

}
