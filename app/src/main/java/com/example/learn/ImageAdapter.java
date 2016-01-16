package com.example.learn;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;


public class ImageAdapter extends BaseAdapter {   
    
    public ImageAdapter(){}   
    private Context context;   
    public ImageAdapter(Context c)   
    {   
        context=c;   
    }   
    @Override   
    public int getCount() {   
        return image.length;   
    }   
                                                      
    @Override   
    public Object getItem(int position) {   
        return position;   
    }   
                                                      
    @Override   
    public long getItemId(int position) {   
        return position;   
    }   
                                                      
    @Override   
    public View getView(int position, View convertView, ViewGroup parent) {   
        ImageView imgview=new ImageView(context);   
        //通过资源ID设置该 ImageView 显示的内容   
        imgview.setImageResource(image[position]);   
        ///设置该属性为真可以在 ImageView 调整边界时保持图片的纵横比例   
        imgview.setAdjustViewBounds(true);   
        imgview.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));   
        return imgview;   
    }   
    //图片资源数组   
    public Integer[] image=   
        {   
            R.drawable.image1,R.drawable.kula   
        };   
}
