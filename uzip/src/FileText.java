import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;



public class FileText {
    public static void main(String[] args) throws Exception{
        String path = "C:\\Users\\admin\\Desktop\\db";

        File file = new File(path);        //获取其file对象
        func(file);
    }

    public static void Inflater(String oriPath, String newPath) throws Exception{
        FileInputStream fis=new FileInputStream(new File(oriPath));
        FileOutputStream fos=new FileOutputStream(new File(newPath));
        InflaterOutputStream ios=new InflaterOutputStream(fos,new Inflater(true));
        byte[]b=new byte[1024];
        int len=0;
        while((len=fis.read(b))!=-1){
            ios.write(b,0,len);
        }
        fis.close();
        ios.close();
    }

    private static void func(File file) throws Exception{
        File[] fs = file.listFiles();
        for (File f : fs) {
            if (f.isDirectory())    //若是目录，则递归打印该目录下的文件
                func(f);
            if (f.isFile())        //若是文件，直接打印
            {
                String strOriName=f.getPath();
                if(strOriName.endsWith(".dataNew")) {
                    String strNewName = strOriName.replace(".dataNew", ".data");
                    //Inflater(strOriName, strNewName);
                    f.renameTo(new File(strNewName));
                    //f.delete();
                    System.out.println(f);
                }
            }
        }
    }
}
