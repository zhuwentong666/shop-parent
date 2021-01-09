package com.zwt.shop.upload;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zwt.shop.base.BaseApiService;
import com.zwt.shop.base.Result;
import com.zwt.shop.status.HTTPStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;


/**
 * @ClassName controller
 * @Description: TODO
 * @Author zhuwentong
 * @Date 2020/12/29
 * @Version V1.0
 **/
@RestController
@RequestMapping(value = "upload")
@Slf4j
public class controller extends BaseApiService {

    //linux系统的上传目录
    @Value(value = "${mingrui.upload.path.windows}")
    private String windowsPath;
    //window系统的上传目录
    @Value(value = "${mingrui.upload.path.linux}")
    private String linuxPath;
    //图片服务器的地址
    @Value(value = "${mingrui.upload.img.host}")
    private String imgHost;

    @Resource
    private FastFileStorageClient storageClient;
    @Resource
    private ThumbImageConfig thumbImageConfig;

    @PostMapping
    public Result<String> uploadImg(@RequestParam MultipartFile file) throws IOException {

//        if(file.isEmpty()) return this.setResultError("上传的文件为空");//判断上传的文件是否为空
//
//        String filename = file.getOriginalFilename();//获取文件名
//
//        //String path = "";
//        String os = System.getProperty("os.name").toLowerCase();
//        String path = os.indexOf("win") != -1 ? windowsPath : os.indexOf("lin") != -1 ? linuxPath : "";
//    //        if(os.indexOf("win") != -1){
//    //            path = windowsPath;
//    //        }else if(os.indexOf("lin") != -1){
//    //            path = linuxPath;
//    //        }
//
//        //UUID.randomUUID() + 1.jpg UUID.randomUUID() + 1.jpg
//        filename = UUID.randomUUID() + filename;//防止文件名重复
//
//        //创建文件 路径+分隔符(linux和window的目录分隔符不一样)+文件名
//        File dest = new File(path + File.separator + filename);
//
//        //判断文件夹是否存在,不存在的话就创建
//        if(!dest.getParentFile().exists()) dest.getParentFile().mkdirs();
//
//        try {
//            file.transferTo(dest);//上传
//        } catch (IllegalStateException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return this.setResult(HTTPStatus.OK,"upload success!!!",imgHost+"/"+ filename);//将文件名返回页面用于页面回显

        InputStream inputStream = file.getInputStream();//获取文件输入流
        String filename = file.getOriginalFilename();//文件名
        String ex = filename.substring(filename.lastIndexOf(".") + 1);//文件后缀名
        // 上传并且生成缩略图
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(
                inputStream, file.getSize(), ex, null);//上传
        // 带分组的路径
        log.info("上传图片全路径:{}", storePath.getFullPath());
        // 不带分组的路径
        log.info("上传图片路径:{}", storePath.getPath());
        // 获取缩略图路径
        String path =
                thumbImageConfig.getThumbImagePath(storePath.getFullPath());
        log.info("缩略图路径:{}", path);
        return new Result<String>(HTTPStatus.OK,"上传成功",imgHost + path);



    }


}
