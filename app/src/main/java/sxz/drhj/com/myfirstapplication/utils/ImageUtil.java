package sxz.drhj.com.myfirstapplication.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import sxz.drhj.com.myfirstapplication.httpProxy.JZBConstants;

public class ImageUtil {

    /**
     * 将当前屏幕截屏
     *
     * @param re 当前界面的布局。
     * @return bitmap类型的图片
     * @Author max.ma
     */
    public static Bitmap ScreenShort(ViewGroup layout) {
        View vv = layout.getRootView();
        vv.setDrawingCacheEnabled(true);
        return vv.getDrawingCache();
    }

    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap sourceToBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Config.RGB_565;//Bitmap.Config.ARGB_8888;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * 将图片内容解析成字节数组
     *
     * @param inStream
     * @return byte[]
     * @throws Exception
     * @Author max.ma
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }

    /**
     * 将assets中的图片存到sd卡上。
     *
     * @param c
     * @param path
     * @param fileName
     * @return path路径
     * @Author max.ma
     */
    public static String saveImage2SD(Context c, String path, String fileName) {
        InputStream in = null;
        OutputStream out = null;
        String sdpath = "";
        if ("".equals(path) || path == null)
            sdpath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        else
            sdpath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + path + "/";
        String filename = fileName;
        String path0 = sdpath + filename;
        File file = new File(path0);
        if (file.exists()) {
            file.delete();
//	    	return null;
        }
        try {
            in = c.getAssets().open(fileName);
            int lenght = in.available();
            byte[] buffer = new byte[lenght];
            in.read(buffer);
            out = new FileOutputStream(file);
            out.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path0;
    }

    /**
     * 将图片保存到图库中
     *
     * @param context
     * @param bmp
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(),
                "download");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://" + file.getAbsolutePath())));

        Toast.makeText(context, "图片已经保存在download文件夹中", Toast.LENGTH_SHORT).show();
    }

    /**
     * 彩色图片转黑白
     *
     * @param bmp
     * @return
     */
    public static Bitmap convertToBlackWhite(Bitmap bmp) {
        int width = bmp.getWidth(); // 获取位图的宽
        int height = bmp.getHeight(); // 获取位图的高
        int[] pixels = new int[width * height]; // 通过位图的大小创建像素点数组

        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        int alpha = 0xFF << 24;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];

                //分离三原色
                int red = ((grey & 0x00FF0000) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);

                //转化成灰度像素
                grey = (int) (red * 0.3 + green * 0.59 + blue * 0.11);
                grey = alpha | (grey << 16) | (grey << 8) | grey;
                pixels[width * i + j] = grey;
            }
        }
        //新建图片
        Bitmap newBmp = Bitmap.createBitmap(width, height, Config.RGB_565);
        //设置图片数据
        newBmp.setPixels(pixels, 0, width, 0, 0, width, height);

        Bitmap resizeBmp = ThumbnailUtils.extractThumbnail(newBmp, width, height);
        return resizeBmp;
    }

    /**
     * 将bitmap图片存到sd卡上。
     *
     * @param c
     * @param path
     * @param fileName
     * @param bitmap
     * @return 存储路径
     * @Author max.ma
     */
    public static String saveImage2SD(String path, String fileName, Bitmap bitmap) {
        FileOutputStream fOut = null;
        String result_path = "";
        String sdpath = "";
        if ("".equals(path) || path == null)
            sdpath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        else
            sdpath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + path + "/";
        result_path = sdpath + fileName;
        File f = new File(result_path);
        if (f.exists()) {
            f.delete();
        }
        try {
            f.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result_path;
    }

    /**
     * 创建目录
     *
     * @param path
     * @return
     */
    public static boolean createDir(String path) {
        boolean ret = false;
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        if (!file.isDirectory()) {
            ret = file.mkdirs();
        }
        return ret;
    }

    /**
     * 删掉文件
     *
     * @param path
     * @return
     */
    public static boolean deleteFile(String path, String filename) {
        String sdpath = "";
        if ("".equals(path) || path == null)
            sdpath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        else
            sdpath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + path + "/";
        File file = new File(sdpath + filename);
        return file.delete();
    }

    /**
     * 从SD卡读取图片
     *
     * @param path 图片存储文件夹
     * @return Bitmap
     * @Author max.ma
     */
    public static Bitmap readSDImage(String path, String filename) {
        String sdpath = "";
        if ("".equals(path) || path == null)
            sdpath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        else
            sdpath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + path + "/";
        Bitmap bm = BitmapFactory.decodeFile(sdpath + filename);
        return bm;
    }

    /**
     * 缩放图片
     *
     * @param context
     * @param p_width  缩放后图片的宽
     * @param p_height 缩放后图片的高
     * @Author max.ma
     */
    public static Bitmap zoomImage(Bitmap bitMap, float p_width, float p_height) {
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        float scaleWidth = ((float) p_width) / width;
        float scaleHeight = ((float) p_height) / height;
        float minScale = Math.min(scaleWidth, scaleHeight);
        Matrix matrix = new Matrix();
        matrix.postScale(minScale, minScale);
        return Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix, true);
    }

    /**
     * 图片按比例大小压缩(同时质量压缩)
     *
     * @param image    原图片
     * @param p_width  压缩宽
     * @param p_height 压缩高
     * @return
     */
    public static Bitmap zoomImage2(Bitmap image, float p_width, float p_height, int zoomSize) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = p_height;//这里设置高度为800f
        float ww = p_width;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        if (zoomSize > 0)
            return compressImage(bitmap, zoomSize);//压缩好比例大小后再进行质量压缩
        else
            return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    /**
     * 图片质量压缩
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        return compressImage(image, 1024);
    }

    /**
     * 图片质量压缩
     *
     * @param image
     * @param size  (kb)压缩后图片大小，最大值
     * @return
     */
    public static Bitmap compressImage(Bitmap image, int size) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        MyLog.d("xmg", "compress before bitmapsize = " + baos.toByteArray().length);
        int options = 100;
        while (baos.toByteArray().length / 1024 > size) {    //循环判断如果压缩后图片是否大于1Mb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            MyLog.d("xmg", "baos size " + (baos.toByteArray().length / 1024));
            options -= 10;//每次都减少10
        }
        MyLog.d("xmg", "compress after bitmapsize = " + baos.toByteArray().length);
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    public static Bitmap compressImage2(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * @param 将字节数组转换为Bitmap对象
     * @param bytes
     * @param opts
     * @return Bitmap
     * @Author max.ma
     */
    public static Bitmap getPicFromBytes(byte[] bytes, BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }

    /**
     * 把Bitmap转Byte
     *
     * @param bm Bitmap
     * @Author max.ma
     */
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 把字节数组保存为一个文件
     *
     * @param b          字节数组
     * @param outputFile 文件名
     * @Author max.ma
     */
    public static File getFileFromBytes(byte[] b, String outputFile) {
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * Drawable 转 Bitmap
     */
    public static Bitmap Drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
                                : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * Bitmap 转 Drawable
     */
    public static Drawable Bitmap2Drawable(Bitmap bitmap) {
        @SuppressWarnings("deprecation")
        BitmapDrawable bd = new BitmapDrawable(bitmap);
        // 因为BtimapDrawable是Drawable的子类，最终直接使用bd对象即可。
        return bd;
    }


    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 将Bitmap转换成字符串
     */
    public static String bitmaptoString(Bitmap bitmap) {
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT).trim();
        return string;
    }

    /**
     * 将字符串转换成Bitmap类型
     */
    public static Bitmap stringtoBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap convertPathToBitmap(String path) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为ture只获取图片大小
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Config.ARGB_8888;
        // 返回为空
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
//		float scaleWidth = 0.f, scaleHeight = 0.f;
//		if (width > w || height > h) {
//			// 缩放
//			scaleWidth = ((float) width) / w;
//			scaleHeight = ((float) height) / h;
//		}
        opts.inJustDecodeBounds = false;
//		float scale = Math.max(scaleWidth, scaleHeight);
        opts.inSampleSize = 1;
        WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
        return Bitmap.createScaledBitmap(weak.get(), width, height, true);
    }

    public static Bitmap getSamllBitMap(String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = calculateInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap getSmallBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }


    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    public static String saveScalePhoto(Bitmap bitmap, String name) {
        // 照片全路径  
        String fileName = "";
        // 文件夹路径  
        String pathUrl = Environment.getExternalStorageDirectory().getPath() + "/" + JZBConstants.CacheFolder + "/";
        String imageName = name + ".jpg";
        FileOutputStream fos = null;
        File file = new File(pathUrl);
        file.mkdirs();// 创建文件夹  
        fileName = pathUrl + imageName;
        try {
            fos = new FileOutputStream(fileName);
            bitmap.compress(CompressFormat.JPEG, 90, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileName;
    }
}
