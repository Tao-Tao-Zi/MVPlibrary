package com.example.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * 分类页面自定义首字母
 */
public class LetterView extends View {
    private List<String> datas;//数据源
    private Paint paint;
    private int sumHeight = 0;//总高度
    private int taWidth = 0;//宽
    private int textHeight = 20;//文字高度

    private String nomalColor = "#918B8B";//未选中颜色
    private String selColor = "#353232";//选中颜色

    private int index= -1;//当前选中的文字下吧
    private Point point;//指定ontouch上一次采集到的点

    private LetterSelListener letterSelListener;

    public LetterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        sumHeight = getMeasuredHeight();
        taWidth = getMeasuredWidth();
    }
    private void init(){
        paint = new Paint();
        paint.setColor(Color.parseColor(nomalColor));
        paint.setTextSize(35);
        paint.setTextAlign(Paint.Align.CENTER);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (datas == null){
            return;
        }
        if (letterSelListener == null){
            return;
        }
        //每个首字母所占的高度
        textHeight = sumHeight/datas.size();
        for (int i = 0; i < datas.size(); i++) {
            if (index == i){
                paint.setColor(Color.parseColor(selColor));
            }else{
                paint.setColor(Color.parseColor(nomalColor));
            }
            canvas.drawText(datas.get(i),taWidth/2,i*textHeight+textHeight/2,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            point = new Point((int)event.getX(),(int)event.getY());
            selIndex();
        }else if (event.getAction() == MotionEvent.ACTION_MOVE){
            //获取滑动距离防止频繁调用selIndex
            int distance = (int) Math.abs((event.getY())-point.y);
            if (distance > textHeight/2 && event.getX()>0&&event.getY()<sumHeight&&event.getY()>0){
                point = new Point((int)event.getX(),(int)event.getY());
                selIndex();
            }else{
                super.onTouchEvent(event);
            }
        }else if (event.getAction()== MotionEvent.ACTION_UP){
            point = new Point((int)event.getX(),(int)event.getY());
            selIndex();
            index = -1;
            invalidate();
        }
        return true;
    }
    //更据当前左边确定界面中所在那个字母的范围
    private void selIndex(){
        if (letterSelListener == null){
            return;
        }
        for (int i = 1; i<datas.size(); i++) {
            if (point.y>(i-1)*textHeight&&point.y<i*textHeight){
                index = i-1;
            }else if (point.y >= i*textHeight){
                index = i;
            }
        }
        invalidate();
        letterSelListener.selIndex(index);
    }
    public interface LetterSelListener{
        void selIndex(int index);
    }

    public LetterView setDatas(List<String> datas) {
        this.datas = datas;
        return this;
    }

    public LetterView setLetterSelListener(LetterSelListener letterSelListener) {
        this.letterSelListener = letterSelListener;
        return this;
    }
}
