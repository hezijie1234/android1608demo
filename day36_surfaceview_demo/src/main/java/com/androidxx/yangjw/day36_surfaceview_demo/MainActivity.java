package com.androidxx.yangjw.day36_surfaceview_demo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * SurfaceView的基本使用
 */
public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback{

    private SurfaceView mSurfaceView;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SurfaceView是显示画面的
        mSurfaceView = (SurfaceView) findViewById(R.id.main_surface_view);
        //呼叫秘书
        SurfaceHolder surfaceHolder = mSurfaceView.getHolder();
        //监听SurfaceHolder初始化（准备）完毕
        surfaceHolder.addCallback(this);

        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
    }

    /**
     * holder初始化完成
     * @param holder SurfaceHolder对象
     */
    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //锁定画布，获得一个Canvas对象
                    Canvas canvas = holder.lockCanvas();
                    canvas.drawCircle(200,200,180,mPaint);
                    //解锁画布，并且将画布传递给SurfaceView
                    holder.unlockCanvasAndPost(canvas);

                }
            }).start();
    }

    /**
     * surfaceview发生变化时
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * surfaceview被销毁时
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
