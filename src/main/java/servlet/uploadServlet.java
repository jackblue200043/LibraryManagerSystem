package servlet;

import dao.doIt.FileDao;
import dao.factory.FactoryDao;
import povo.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class uploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
        * 以下的代码目的请求的实体部分存放到一个文件中。
        * 1）HTTP协议通过MIME支持将上传内容放入实体中。
        * 2）通过创建以当前SessionID为文件名的临时文件，并存放如当前工作路径的userFile目录中。
        * */
        //获取真实路径
        HttpSession session = req.getSession();
        String pathReal = this.getServletContext().getRealPath("/userFile");
        String userName = ((Users)session.getAttribute("userId")).getUserName();
        //创建一个存放上传文件的文件夹(也就是/userFile文件夹)
        File usersDir = new File(pathReal);
        if (!usersDir.exists()) {
            usersDir.mkdir();
        }
        //在根目录创建一个与用户名(userName)同名的文件夹;
        File userDir = new File(usersDir, userName);
        if (!userDir.exists()) {
            userDir.mkdir();
        }
        //获取请求报文实体部分的输入流（格式）
        InputStream input = req.getInputStream();
        //使用session的id为名字创建临时文件;
        String sessionid = session.getId();
        File userTempFile = new File(userDir, sessionid);
        //在推出后删除包含上传文件的实体主体文件
        userTempFile.deleteOnExit();
        userTempFile.createNewFile();
        //使用媒体输入流输入文件写入内容;
        RandomAccessFile tempRanFile = new RandomAccessFile(userTempFile, "rw");
        byte[] b = new byte[1024];
        long pos;
        while((pos = input.read(b)) > 0) {
            tempRanFile.write(b, 0, (int) pos);
        }
        input.close();
        /**
         * 分析保存下来的文件（包含文件内容和名称的实体文件）
         * 使用posList集合来保存所有上传文件信息开头行（Content-Disposition: form-data; name="file2"; filename="test.txt"）
         */
        //获取tempRanFile中的最后位置;
        long lastPos = tempRanFile.getFilePointer();
        //下面开始读取文件, 给文件定位, 找到以（------WebKitFormBoundarygpXape8UCbFAsQqb）开头行的pos位置;
        List<Long> posList = new ArrayList<Long>();
        //先读取第一行文件内容;
        tempRanFile.seek(0);
        String oneLine = tempRanFile.readLine();
        int firstLineLength = oneLine.length();
        //获取上传文件最后一个表单项的文件内容最后一个字符的后一个字符位置(\r\n------WebKitFormBoundarygpXape8UCbFAsQqb--\r\n)
        long lastPosUp = lastPos-firstLineLength-6;
        //使用onLine进行对比获得每一行的位置;
        String tempLine = null;
        //第一个表单项的内容的第一个字符进入
        posList.add(tempRanFile.getFilePointer());
        while(tempRanFile.getFilePointer() < lastPosUp) {
            //读取下一行;
            tempLine=tempRanFile.readLine();
            //如果下一行是文件与第一行文件相同, 那么位置指针就指向一个文件的开始位置;
            if (oneLine.equals(tempLine)) {
                posList.add(tempRanFile.getFilePointer());
            }
        }
        //上面的循环已经读取了文件所有开始位置, 然后将文件的结尾位置加入到集合;
        posList.add(lastPosUp);


        /**
         * 下面根据posList集合中标记的文件位置, 取出文件名, 并保存文件，将文件信息写入数据库;
         * 1）通过posList的长度，可以得知上传了多少个文件。
         * 2）通过posList得到的每个表单项开始和结束位置。
         */

        for (int i = 0; i < posList.size()-1; i++) {
            //将文件定位到指定posLine.get(i)行;
            tempRanFile.seek(posList.get(i));
            String fileName = tempRanFile.readLine();
            //使用字符串取出文件名;（Content-Disposition: form-data; name="file1"; filename="test.txt"）
            fileName = fileName.substring(fileName.lastIndexOf('=')+2, fileName.length()-1);
            System.out.println(fileName);
            //解决中文文件名乱码
            fileName = new String(fileName.getBytes("latin1"), "utf-8");
            //读取两行，为例移动文件指针到实际文件的位置。
            tempRanFile.readLine();
            tempRanFile.readLine();
            //如果上传表单包含文件名, 保存创建的文件，和更新文件信息到数据库
            if (!"".equals(fileName)) {
                File upFile = new File(userDir, fileName);
                //如果文件已经存在, 那么改变文件名
                if(upFile.exists()) {
                    int prefix = (int) (System.currentTimeMillis()%100);
                    fileName = prefix + fileName;
                    upFile = new File(userDir, fileName);
                }
                //标记当前表单上传项文件内容的末尾位置的后一个字符。
                // （${LastFileData}\r\n------WebKitFormBoundary3GPAjcHlwyVynypF\r\nC）
                long fileLastPos = posList.get(i+1)-firstLineLength-4;
                //如果上传的文件是表单项中最后一项，末尾位置直接在数组中，所以不需要计算直接获取。
                if (i == posList.size()-2){
                    fileLastPos = posList.get(posList.size()-1);
                }
                OutputStream upFileOutStr = new FileOutputStream(upFile);
                //根据文件在实体中的位置，保存文件。
                //j是文件开头的位置，
                long j = tempRanFile.getFilePointer();
                while(j < fileLastPos) {
                    pos = tempRanFile.read(b);
                    if (j+pos > fileLastPos){
                        pos = fileLastPos - (tempRanFile.getFilePointer()-pos);
                    }
                    if (pos < 0) {
                        break;
                    }
                    upFileOutStr.write(b, 0, (int) pos);
                    j = tempRanFile.getFilePointer();
                }
                //创建上传文件的实体JavaBean
                upFileOutStr.flush();
                upFileOutStr.close();
                povo.File dbFile = new povo.File();
                dbFile.setUserName(userName);
                dbFile.setFileName(upFile.getName());
                dbFile.setDirectory(upFile.getAbsolutePath());
                dbFile.setFileType(upFile.getName().substring(upFile.getName().lastIndexOf('.')+1));
                //获取大小
                dbFile.setSize(String.format("%.1e",(lastPos/(1024.0*1024))) +"MB");
                System.out.println(dbFile);
                //数据写入数据库
                FileDao fDao = FactoryDao.getFileDao();
                fDao.insert(dbFile);
            }
        }
        tempRanFile.close();
        PrintWriter out =  resp.getWriter();
        out.println("<script>window.opener.location.reload();window.close();</script>");
    }
}
