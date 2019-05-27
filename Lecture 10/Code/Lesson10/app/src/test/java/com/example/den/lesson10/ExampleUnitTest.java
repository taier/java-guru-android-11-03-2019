package com.example.den.lesson10;

import com.example.den.lesson10.DataSources.Unsplash.NetworkingManagerUnsplash;
import com.example.den.lesson10.Interfaces.NetworkingManager;
import com.example.den.lesson10.Interfaces.NetworkingResultListener;
import com.example.den.lesson10.Interfaces.PhotoItem;

import org.junit.jupiter.api.Test;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}