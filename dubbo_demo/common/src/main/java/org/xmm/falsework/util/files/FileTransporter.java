package org.xmm.falsework.util.files;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件传输器
 * 
 * @author xmm
 */
public abstract class FileTransporter {

    abstract void transferToLocal(MultipartFile[] remoteUploadFiles, FileInfo fileInfo);
    abstract void transferToLocal(MultipartFile remoteUploadFile, FileInfo fileInfo);
    abstract void transferToLocal(InputStream remoteIn,FileInfo fileInfo);
    abstract void transferToLocal(FileInfo fileInfo);
    
    abstract void transferToRemote(OutputStream remoteOut,FileInfo fileInfo);
    
    
    
}
