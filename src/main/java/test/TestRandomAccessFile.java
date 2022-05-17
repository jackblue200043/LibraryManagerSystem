package test;


import java.io.RandomAccessFile;

public class TestRandomAccessFile {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("C:\\Users\\liyul\\Desktop\\a.txt","rw");
//        System.out.println("file.getPointer:" + file.getFilePointer());
//        System.out.println("file.readLine:" + new String(file.readLine().getBytes("ISO-8859-1"),"utf-8"));
//        System.out.println("file.getPointer:" + file.getFilePointer());
//        byte[] b = new byte[6];
//        System.out.println(b.length);
//        file.read(b);
//        String str = new String(b,"utf-8");
//        System.out.println(str);
//        System.out.println("file.getPointer:" + file.getFilePointer());
        System.out.println("file.length：" + file.length());
    }
}
