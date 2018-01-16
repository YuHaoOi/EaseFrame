package com.jlkf.jdsaleside.other.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/***
 * * @description
 * * 由 马君 创建于 2016年01月23日 10:48
 ***/
public class DotView extends View {

    private boolean isCount=false;
    private int height;
    private int width;

    private boolean isChange=false;
    private int dotNum=0;
    private int normalColor=0x4dFFFFFF;
    private int chooseColor=0xFFff415b;
    private float dotRadius=2;
    private float dotGap=15;
    private float dotStoke=4f;
    private float dotChoosePadding=-3f;

    private int chooseWhich=0;

    private Paint paint;

    public DotView(Context context) {
        super(context);
        init();
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setNormalColor(int normalColor){
        this.normalColor=normalColor;
    }

    public void setChooseColor(int chooseColor){
        this.chooseColor=chooseColor;
    }

    public void setDotStoke(float dotStoke){
        this.dotStoke=dotStoke;
    }

    private void init(){
        paint=new Paint();
        paint.setAntiAlias(true);
        dotRadius= dip2px(getContext(), dotRadius);
        dotGap=dip2px(getContext(), dotGap);
        dotStoke=dip2px(getContext(),dotStoke);
        dotChoosePadding=dip2px(getContext(),dotChoosePadding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width=getWidth();
        height=getHeight();
        float startX=(width-(dotNum-1)*dotGap)/2;
        for(int i=0;i<dotNum;i++){
            drawNormalDot(canvas,startX+i*dotGap,height/2,normalColor);
            if(chooseWhich==i){
                drawChooseDot(canvas,startX+i*dotGap,height/2,chooseColor);
            }
        }
    }

    private void drawNormalDot(Canvas canvas, float x, float y, int color){
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(dotStoke);
        canvas.drawCircle(x, y, dotRadius, paint);
    }

    private void drawChooseDot(Canvas canvas, float x, float y, int color){
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, dotRadius-dotChoosePadding, paint);
    }

    public void setChecked(int chooseWhich){
        this.chooseWhich=chooseWhich;
        invalidate();
    }

    public void setDotChoosePadding(int dotChoosePadding){
        this.dotChoosePadding=dip2px(getContext(),dotChoosePadding);
    }

    public void setDotRadius(float dotRadius){
        this.dotRadius=dotRadius;
    }

    public void setDotGap(float dotGap){
        this.dotGap= dip2px(getContext(), dotGap) ;
    }

    public void setDotNum(int dotNum){
        this.dotNum=dotNum;
    }

    private int dip2px(Context var0, float var1) {
        float var2 = var0.getResources().getDisplayMetrics().density;
        return (int)(var1 * var2 + 0.5F);
    }
}
