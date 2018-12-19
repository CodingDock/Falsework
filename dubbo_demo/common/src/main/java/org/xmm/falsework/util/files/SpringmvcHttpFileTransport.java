package org.xmm.falsework.util.files;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Getter
@Setter
@Slf4j
public class SpringmvcHttpFileTransport {
    
    private MultipartFile multipartFile;
    private String filepath;

    public void transfer() {
            File localFile=new File(filepath);
            try {
                multipartFile.transferTo(localFile);
            } catch (IOException e) {
                log.error("文件传输操作异常",e);
                throw new RuntimeException("文件传输操作异常",e);
            }
    }
    
    
}
