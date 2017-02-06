package com.androidxx.yangjw.android1608demo;

import com.androidxx.yangjw.android1608demo.model.MainModel;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test_main() throws Exception {
        MainModel mainModel = new MainModel();
        mainModel.queryData();
    }
}