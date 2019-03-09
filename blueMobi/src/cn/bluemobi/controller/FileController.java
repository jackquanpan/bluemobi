package cn.bluemobi.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import cn.bluemobi.constant.BlueMobiConstant;
import cn.bluemobi.util.config.Config;
import cn.bluemobi.util.file.FileHelper;
import cn.bluemobi.util.helper.ValidateHelper;

/**
 * 文件上传管理
 * 
 * @author xiazf
 * 
 */
@Controller
public class FileController extends BaseController {

    /**
     * 文件上传
     * 
     * @param fileType 上传文件的类型 由此判断存放位置
     * @param file 上传的文件
     */
    @RequestMapping(value = "admin/uploadFile", method = RequestMethod.POST)
    public void fileUploadF(MultipartFile file) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String type = null, fileName = null;
            File image = null;

            if (file == null || file.getSize() == 0) {
                map.put(STATUS, EMPTY);
                return;
            }

            type = file.getOriginalFilename().toLowerCase();
            type = type.substring(type.lastIndexOf("."));

            fileName = "upload/" + FileHelper.getTimeFileName() + type;
            image = new File(BlueMobiConstant.WEB_SITE_ROOT_PATH + fileName);
            File dir = image.getParentFile();
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }
            FileCopyUtils.copy(file.getBytes(), image);

            map.put("src", basePath + fileName);
            map.put(STATUS, SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(STATUS, FAIL);
        } finally {
            outJSON(map);
        }
    }

    public static Map<String, Object> uploadFile(MultipartFile file) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(STATUS, ERROR);
        try {
            if (!ValidateHelper.isNullOrEmpty(file)) {
                // if (!checkImg(file)) {
                // map.put(STATUS, "NOT_IMG");
                // return map;
                // }
                String suffix = file.getOriginalFilename().toLowerCase();
                if (!ValidateHelper.isNullOrEmpty(suffix)) {
                    suffix = suffix.substring(suffix.lastIndexOf("."));
                    String fileDir = "upload/";
                    String fileName = fileDir + FileHelper.getTimeFileName() + suffix;
                    File newFile = new File(BlueMobiConstant.WEB_SITE_ROOT_PATH + fileName);
                    File dir = newFile.getParentFile();
                    if (!dir.isDirectory()) {
                        dir.mkdirs();
                    }
                    FileCopyUtils.copy(file.getBytes(), newFile);
                    map.put(DATA, Config.get().getDomain() + fileName);
                    map.put(STATUS, SUCCESS);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 判断文件是不是有效的图片文件
     * 
     * @param file 源文件
     */
    public static boolean checkImg(MultipartFile file) {
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(file);
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if (!iter.hasNext()) {// 文件不是图片
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
