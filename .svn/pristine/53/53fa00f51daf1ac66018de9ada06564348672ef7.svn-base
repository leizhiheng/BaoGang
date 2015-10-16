package com.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

/**
 * Bitmap工具类
 * @author 刘远祺
 *
 * @todo TODO
 *
 * @date 2015-10-13
 */
public class BitmapUtils {
    public static Bitmap getBmp(Context context, int resId) {
        Resources res = context.getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, resId);
        return bmp;
    }

    public static Bitmap getBmp(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        // Resources res = context.getResources();
        // Bitmap bmp = BitmapFactory.decodeResource(res, resId);
        return bitmap;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }

    // public void saveBitmap(Bitmap bm, String name) {
    // File f = new File(AppContext.getSDPath(), name);
    // if (f.exists()) {
    // f.delete();
    // }
    // try {
    // FileOutputStream out = new FileOutputStream(f);
    // bm.compress(Bitmap.CompressFormat.PNG, 90, out);
    // out.flush();
    // out.close();
    // } catch (FileNotFoundException e) {
    // e.printStackTrace();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    public static Bitmap showimageFull(String ImagePath, int w, int h) {
        Bitmap tempBitmap;
        int heightRatio = 0, widthRatio = 0;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(ImagePath, options);
        heightRatio = (int) Math.ceil(options.outHeight / (float) h);
        widthRatio = (int) Math.ceil(options.outWidth / (float) w);

        LogUtil.d("showimageFull", "h:" + heightRatio + " w:" + widthRatio);
        if ((heightRatio > 1) && (widthRatio > 1)) {
            if (heightRatio > widthRatio) {
                options.inSampleSize = heightRatio;
            } else {
                options.inSampleSize = widthRatio;
            }
        }
        options.inJustDecodeBounds = false;
        tempBitmap = BitmapFactory.decodeFile(ImagePath, options);
        try {
            ExifInterface exif = new ExifInterface(ImagePath);
            String sModel = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
            if (sModel.equals("6")) {
                Matrix matrix = new Matrix();
                matrix.setRotate(90);
                tempBitmap = Bitmap.createBitmap(tempBitmap, 0, 0, tempBitmap.getWidth(), tempBitmap.getHeight(), matrix, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempBitmap;
    }

    public static Bitmap showimage(String ImagePath, int target) {
        Bitmap tempBitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(ImagePath, options);
        options.inSampleSize = computeSampleSize(options, target);
        options.inJustDecodeBounds = false;
        tempBitmap = BitmapFactory.decodeFile(ImagePath, options);
        return tempBitmap;
    }

    public static Bitmap watermarkBitmap(Bitmap src, Bitmap watermark, String title, int TextSize) {
        if (src == null) {
            return null;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        // 需要处理图片太大造成的内存超过的问题,这里我的图片很小所以不写相应代码了
        Bitmap tempBitmap = Bitmap.createBitmap(w, h, Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
        Canvas cv = new Canvas(tempBitmap);
        cv.drawBitmap(src, 0, 0, null);// 在 0，0坐标开始画入src
        Paint paint = new Paint();
        // 加入图片
        if (watermark != null) {
            int ww = watermark.getWidth();
            int wh = watermark.getHeight();
            paint.setAlpha(50);
            cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, paint);// 在src的右下角画入水印
        }
        // 加入文字
        if (title != null) {
            String familyName = "黑体";
            Typeface font = Typeface.create(familyName, Typeface.BOLD);
            TextPaint textPaint = new TextPaint();
            textPaint.setColor(Color.RED);
            textPaint.setTypeface(font);
            textPaint.setTextSize(TextSize);
            // 这里是自动换行的
            StaticLayout layout = new StaticLayout(title, textPaint, w, Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
            layout.draw(cv);
            // 文字就加左上角算了
            // cv.drawText(title,50,50,paint);
        }
        cv.save(Canvas.ALL_SAVE_FLAG);// 保存
        cv.restore();// 存储
        return tempBitmap;
    }

    public static int computeSampleSize(BitmapFactory.Options options, int target) {
        int w = options.outWidth;
        int h = options.outHeight;
        int candidateW = w / target;
        int candidateH = h / target;
        int candidate = Math.max(candidateW, candidateH);
        if (candidate == 0)
            return 1;
        if (candidate > 1) {
            if ((w > target) && (w / candidate) > target)
                candidate += 1;
        }
        if (candidate > 1) {
            if ((h > target) && (h / candidate) > target)
                candidate += 1;
        }
        return candidate;
    }

    /**
     * 获得bitmap的大小，单位是byte
     * @param bitmap
     * @return
     */
    public static int getBitmapSize(Bitmap bitmap){

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){    //API 19
//
//            return bitmap.getAllocationByteCount();
//
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1){//API 12

            return bitmap.getByteCount();

        }

        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version

    }

    public static Bitmap getResizedBitmap(Context context, Uri uri, int widthLimit, int heightLimit) throws IOException {
        String path = null;
        Bitmap result = null;
        if(uri.getScheme().equals("file")) {
            path = uri.getPath();
        } else {
            if(!uri.getScheme().equals("content")) {
                return null;
            }

            Cursor exifInterface = context.getContentResolver().query(uri, new String[]{"_data"}, (String)null, (String[])null, (String)null);
            exifInterface.moveToFirst();
            path = exifInterface.getString(0);
            exifInterface.close();
        }

        ExifInterface exifInterface1 = new ExifInterface(path);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        int orientation = exifInterface1.getAttributeInt("Orientation", 0);
        int width;
        if(orientation == 6 || orientation == 8 || orientation == 5 || orientation == 7) {
            width = widthLimit;
            widthLimit = heightLimit;
            heightLimit = width;
        }

        width = options.outWidth;
        int height = options.outHeight;
        int sampleW = 1;

        int sampleH;
        for(sampleH = 1; width / 2 > widthLimit; sampleW <<= 1) {
            width /= 2;
        }

        while(height / 2 > heightLimit) {
            height /= 2;
            sampleH <<= 1;
        }

        boolean sampleSize = true;
        options = new BitmapFactory.Options();
        int sampleSize1;
        if(widthLimit != 2147483647 && heightLimit != 2147483647) {
            sampleSize1 = Math.max(sampleW, sampleH);
        } else {
            sampleSize1 = Math.max(sampleW, sampleH);
        }

        options.inSampleSize = sampleSize1;

        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeFile(path, options);
        } catch (OutOfMemoryError var22) {
            var22.printStackTrace();
            options.inSampleSize <<= 1;
            bitmap = BitmapFactory.decodeFile(path, options);
        }

        Matrix matrix = new Matrix();
        if(bitmap == null) {
            return bitmap;
        } else {
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            if(orientation == 6 || orientation == 8 || orientation == 5 || orientation == 7) {
                int xS = w;
                w = h;
                h = xS;
            }

            switch(orientation) {
                case 2:
                    matrix.preScale(-1.0F, 1.0F);
                    break;
                case 3:
                    matrix.setRotate(180.0F, (float)w / 2.0F, (float)h / 2.0F);
                    break;
                case 4:
                    matrix.preScale(1.0F, -1.0F);
                    break;
                case 5:
                    matrix.setRotate(90.0F, (float)w / 2.0F, (float)h / 2.0F);
                    matrix.preScale(1.0F, -1.0F);
                    break;
                case 6:
                    matrix.setRotate(90.0F, (float)w / 2.0F, (float)h / 2.0F);
                    break;
                case 7:
                    matrix.setRotate(270.0F, (float)w / 2.0F, (float)h / 2.0F);
                    matrix.preScale(1.0F, -1.0F);
                    break;
                case 8:
                    matrix.setRotate(270.0F, (float)w / 2.0F, (float)h / 2.0F);
            }

            float xS1 = (float)widthLimit / (float)bitmap.getWidth();
            float yS = (float)heightLimit / (float)bitmap.getHeight();
            matrix.postScale(Math.min(xS1, yS), Math.min(xS1, yS));

            try {
                result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                return result;
            } catch (OutOfMemoryError var21) {
                var21.printStackTrace();
                Log.d("ResourceCompressHandler", "OOMHeight:" + bitmap.getHeight() + "Width:" + bitmap.getHeight() + "matrix:" + xS1 + " " + yS);
                return null;
            }
        }
    }
    
    /**
     * 保存bitmap到文件
     * @param bitmap
     * @param file
     * @return
     */
    public static boolean saveBitmap(Bitmap bitmap, File file){
    	if(null == bitmap || null == file){
    		return false;
    	}
    	
    	FileOutputStream os = null;
		try {
			os = new FileOutputStream(file);
			bitmap.compress(CompressFormat.JPEG, 100, os);
			os.close();
			bitmap.recycle();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    	
    }
}
