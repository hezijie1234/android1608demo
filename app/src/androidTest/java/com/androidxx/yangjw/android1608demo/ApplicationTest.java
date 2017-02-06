package com.androidxx.yangjw.android1608demo;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.androidxx.yangjw.android1608demo.model.MainModel;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testMain() {
        MainModel mainModel = new MainModel(null);
        mainModel.queryData();
        System.out.println("1111111111");
    }



}