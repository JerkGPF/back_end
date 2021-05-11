package com.gpfei.recruit.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.gpfei.recruit.service.OssService;
import com.gpfei.recruit.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        try{
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String filename = file.getOriginalFilename();
            //1.用uuid在文件中随机生成唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            filename = uuid+filename;
            //2.把文件按日期近分类
            //  获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接
            filename = datePath+"/"+filename;
            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            // 3个参数，第一个bucketName，第二个文件名称，第三个文件输入流
            ossClient.putObject(bucketName, filename, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            // 把上传之后的文件返回,手动拼接
            //https://edu-gpfei.oss-cn-beijing.aliyuncs.com/
            String url = "https://"+bucketName+"."+endpoint+"/"+filename;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
