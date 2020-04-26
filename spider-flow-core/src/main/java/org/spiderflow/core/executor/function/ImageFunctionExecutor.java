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
    public static void imageSubimageFile(String rootPath,String replacePath,int subimageHeight,int subimageWidth,int limitHeight){

        File parent=new File(rootPath);
        try {
            imageFilePath(rootPath,parent,replacePath,subimageHeight,subimageWidth,limitHeight);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Comment("将文件内的图片进行裁剪")
    @Example("${image.imageSubimageOneFile('path',10,10)}")
    public static void imageSubimageOneFile(String rootPath,String fileName,String replacePath,int subimageHeight,int subimageWidth,int limitHeight){

        File parent=new File(rootPath,fileName);
        try {
            imageFilePath(rootPath,parent,replacePath,subimageHeight,subimageWidth,limitHeight);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static  void imageFilePath(String rootPath,File file,String replacePath,int subimageHeight,int subimageWidth,int limitHeight) throws IOException {
        File[] files=file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return !(pathname.getPath().endsWith(".*"));
            }
        });
        if(files==null||files.length<=0){
            BufferedImage img= ImageIO.read(new File(file.getPath()));
            int width=   img.getWidth();
            int height=img.getHeight();
            if(height>limitHeight){
                File imageFile= new File(file.getPath().replaceAll("\\\\","/").replace(rootPath,replacePath));
                if(!imageFile.exists()){
                    imageFile.mkdirs();
                }
                BufferedImage newImag=   img.getSubimage(0,0,width-subimageWidth,height-subimageHeight);
                ImageIO.write(newImag, "JPEG", imageFile);
            }
        }else{
            for(File childFile:files){
                imageFilePath(rootPath,childFile,replacePath,subimageHeight,subimageWidth,limitHeight);
            }

        }
    }

    @Comment("将url文件内的图片进行裁剪")
    @Example("${image.imageSubimage('path','savePath',10,10)}")
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
                    ImageIO.write(newImag, "JPEG", new File(savePath+"/"+fileName));
                }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
