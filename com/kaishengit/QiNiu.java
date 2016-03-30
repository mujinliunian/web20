package com.kaishengit;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.util.UUID;

public class QiNiu {
    public static void main(String[] args) {
        final String ak = "gAIdPYosTsslBkLKmC51Q6v-tH3-fwNzmXqXEXPd";
        final String sk = "unV5e-DTJYpkup8e0I-QHuAhSAcz5SsZHVTXh8cm";
        final String buckName = "web20";
        //创建Auth 对象
        Auth auth =Auth.create(ak,sk);
        //获取上传Token
        String token = auth.uploadToken(buckName);
        //创建上传对象
        UploadManager uploadManager = new UploadManager();
        try {
            String name = UUID.randomUUID().toString();
            Response response = uploadManager.put("F:/2.jpg",name,token);

            if (response.isOK()){
                System.out.println("文件上传成功"+name);
            }
        /*    System.out.println(response.bodyString());
            StringMap map = response.jsonToMap();
            String value = (String) map.get("key");
            System.out.println("key:"+value);*/

        } catch (QiniuException e) {
            e.printStackTrace();
        }
    }
}
