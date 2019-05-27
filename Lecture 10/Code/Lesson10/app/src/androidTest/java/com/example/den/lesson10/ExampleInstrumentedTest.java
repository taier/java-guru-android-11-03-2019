package com.example.den.lesson10;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.den.lesson10.DataSources.Giphy.NetworkingManagerGiphy;
import com.example.den.lesson10.DataSources.Unsplash.NetworkingManagerUnsplash;
import com.example.den.lesson10.Interfaces.NetworkingManager;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.den.lesson3", appContext.getPackageName());
    }

    @Test
    public void testUnsplashDownload() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = new CompletableFuture<>();

        NetworkingManager networkingManager = new NetworkingManagerUnsplash();
        networkingManager.getPhotoItems(photoItems -> {
            future.complete(photoItems.length);
        });

        assertTrue(future.get() > 0 && future.get() <= 20);
    }

    @Test
    public void testGiphyhDownload() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        NetworkingManager networkingManager = new NetworkingManagerGiphy();
        networkingManager.getPhotoItems(photoItems -> {
            future.complete(photoItems.length);
        });
        assertTrue(future.get() > 0);
    }
}
