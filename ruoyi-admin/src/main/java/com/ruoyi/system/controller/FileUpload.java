package com.ruoyi.system.controller;

import com.ruoyi.common.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FileUpload extends BaseController {
    @ResponseBody
    @RequestMapping("/upload")
    public Map<String, String> uploadaaa(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
        //String path = request.getSession().getServletContext().getRealPath("upload");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式  HH:mm:ss
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String path = "C:/var/uploaded_files/"+date+"/";
        UUID uuid= UUID.randomUUID();
        String originalFilename = file.getOriginalFilename();
        // String fileName = uuid.toString() + originalFilename;
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
        String fileName = uuid.toString() + extendName;
        File dir = new File(path, fileName);
        File filepath = new File(path);
        if(!filepath.exists()){
            filepath.mkdirs();
        }
        file.transferTo(dir);

        Map<String, String> map = new HashMap<>();
        map.put("filePath", path);
        map.put("fileName", fileName);

        return map;

    }
}
