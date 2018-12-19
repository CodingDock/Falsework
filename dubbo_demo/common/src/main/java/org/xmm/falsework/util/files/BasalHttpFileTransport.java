package org.xmm.falsework.util.files;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.*;


@Getter
@Setter
@Slf4j
public class BasalHttpFileTransport {
    
    private InputStream fis;
    private OutputStream fos;
    
    public void transfer() {

        try(BufferedInputStream in = new BufferedInputStream(fis);
            BufferedOutputStream out = new BufferedOutputStream(fos)) {
            
            IOUtils.copy(in, out);
        } catch (IOException e) {
            log.error("文件传输操作异常",e);
            throw new RuntimeException("文件传输操作异常",e);
        }


    }
    
    
}
