package org.spiderflow.core.executor.function;

import org.spiderflow.annotation.Comment;
import org.spiderflow.annotation.Example;
import org.spiderflow.executor.FunctionExecutor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;

/**
 * @program: spider-flow
 * @description:
 * @author: zhendong.wu
 * @create: 2020-04-24 15:22
 **/
@Component
@Comment("图片常用方法")
public class ImageFunctionExecutor implements FunctionExecutor {
    @Override
    public String getFunctionPrefix() {
        return "image";
    }

    @Comment("将文件内的图片进行裁剪")
    @Example("${image.imageSubimage('path',10,10)}")
    public static void imageSubimageFile(String path,String savePath,int subimageHeight,int subimageWidth,int limitHeight){

        File parent=new File(path);
        File[] files= parent.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        });
        try {
            for(File file :files){
                BufferedImage img= ImageIO.read(file);
                int width=   img.getWidth();
                int height=img.getHeight();
                if(height>limitHeight){
                    BufferedImage newImag=   img.getSubimage(0,0,width-subimageWidth,height-subimageHeight);
                    ImageIO.write(newImag, "JPEG", new File(savePath+"/"+file.getName()+".jpg"));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Comment("将url文件内的图片进行裁剪")
    @Example("${image.imageSubimage('path',10,10)}")
    public static void imageSubimageUrl(String path,String savePath,int subimageHeight,int subimageWidth,int limitHeight){

        try {
               URL url=new URL(path);
             String urlPath = url.getPath();
               String fileName = urlPath.substring(urlPath.lastIndexOf("/") + 1);
                BufferedImage img= ImageIO.read(url);
                int width=   img.getWidth();
                int height=img.getHeight();
                if(height>limitHeight){
                    BufferedImage newImag=   img.getSubimage(0,0,width-subimageWidth,height-subimageHeight);
                    ImageIO.write(newImag, "JPEG", new File(savePath+"/"+fileName+".jpg"));
                }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
