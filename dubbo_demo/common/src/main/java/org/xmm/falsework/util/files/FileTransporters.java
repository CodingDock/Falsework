package org.xmm.falsework.util.files;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;



@Slf4j
public class FileTransporters {

    /**
     * 请求==》响应
     */
    public static BasalHttpFileTransport getBasalBHT_Original(
            HttpServletRequest request, HttpServletResponse response){
        try {
            BasalHttpFileTransport bht=new BasalHttpFileTransport();
            bht.setFis(request.getInputStream());
            bht.setFos(response.getOutputStream());
            return bht;
        } catch (IOException e) {
            log.error("获取IO流失败",e);
            throw new RuntimeException("获取IO流失败",e);
        }
    }

    /**
     * 本地==》远程
     * @param filePath 本地可访问的filepath
     */
    public static BasalHttpFileTransport getBHT_LocalToRemote(
            String filePath, HttpServletResponse response){
        try {
            BasalHttpFileTransport bht=new BasalHttpFileTransport();
            File file=new File(filePath);
            if(file!=null&&file.exists()&&file.isFile()&&file.canRead()){
                bht.setFis(new FileInputStream(file));
                bht.setFos(response.getOutputStream());
                return bht;
            }else{
                log.error("文件不存在或没有操作权限，filePath："+filePath);
                throw new RuntimeException("获取IO流失败");
            }
        } catch (IOException e) {
            log.error("获取IO流失败",e);
            throw new RuntimeException("获取IO流失败",e);
        }
    }

    /**
     * 远程url==》本地
     * @param url 文件下载url
     */
    public static BasalHttpFileTransport getBHT_RemoteUrlToLocal(
            String url, String filePath){
        try {
            File file = new File(filePath);
            if(!file.exists()){
                file.getParentFile().mkdirs();
            }
            
            BasalHttpFileTransport bht=new BasalHttpFileTransport();
            bht.setFis(getInputStreamFromURL(url));
            bht.setFos(new FileOutputStream(filePath));
            return bht;
        } catch (IOException e) {
            log.error("获取IO流失败",e);
            throw new RuntimeException("获取IO流失败",e);
        }
    }
    
    public static SpringmvcHttpFileTransport getSHFT(
            MultipartFile multipartFile,String filePath){
        SpringmvcHttpFileTransport shft =  new SpringmvcHttpFileTransport();
        shft.setMultipartFile(multipartFile);
        shft.setFilepath(filePath);
        return shft;
    }
    
    
    
    
    private static InputStream getInputStreamFromURL(String url){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpclient.execute(httpget);
            if(response.getStatusLine().getStatusCode()!=200){
                throw new RuntimeException("获取IO流失败,请求失败");
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return entity.getContent();
            }else{
                throw new RuntimeException("获取IO流失败,响应为空");
            }
        } catch (IOException e) {
            log.error("获取IO流失败",e);
            throw new RuntimeException("获取IO流失败",e);
        }
        
    }
    
    
}
