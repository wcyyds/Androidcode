/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javasourcelearn;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public final class Progress {

  public void run() throws Exception {
    //对Request进行配置
    Request request = new Request.Builder()
        .url("https://images.pexels.com/photos/5177790/pexels-photo-5177790.jpeg")
        .build();


    //定义的接口,重写接口中方法
    final ProgressListener progressListener = new ProgressListener() {
      //判断是不是第一次更新进度信息
      boolean firstUpdate = true;

      //这是重写的方法
      /*
      bytesRead已经下载回来的响应数据
      contentLength响应数据的总长度
      done请求是否完成,如果完成直接表示completed
       */
      @Override public void update(long bytesRead, long contentLength, boolean done) {
        if (done) {
          System.out.println("completed");
        } else {
          if (firstUpdate) {
            firstUpdate = false;
            if (contentLength == -1) {
              //进入这里说明响应数据的长度未知
              System.out.println("content-length: unknown");
            } else {
              System.out.format("content-length: %d\n", contentLength);
            }
          }

          System.out.println(bytesRead);

          if (contentLength != -1) {
            System.out.format("%d%% done\n", (100 * bytesRead) / contentLength);
          }
        }
      }
    };

    //不使用lambda表达式进行网络拦截器的配置,在网络拦截器中添加ProgressInterceptor拦截器
    OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(new ProgressInterceptor(progressListener))
            .build();

    //使用OK发送一个同步请求
    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

      System.out.println(response.body().string());
    }
  }

  public static void main(String... args) throws Exception {
    new Progress().run();
  }

  //自定义拦截器
  public class ProgressInterceptor implements Interceptor{

    private final ProgressListener progressListener;

    public ProgressInterceptor(ProgressListener progressListener) {
      this.progressListener = progressListener;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
      Response originalResponse = chain.proceed(chain.request());
      Response.Builder builder  = originalResponse.newBuilder()
              .body(new ProgressResponseBody(originalResponse.body(),progressListener));
      return builder.build();
    }
  }

  private static class ProgressResponseBody extends ResponseBody {

    //原始的responseBody的对象
    private final ResponseBody responseBody;
    //进度监听器
    private final ProgressListener progressListener;
    //缓冲输入流对象
    private BufferedSource bufferedSource;

    ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
      this.responseBody = responseBody;
      this.progressListener = progressListener;
    }

    //响应的数据内容
    @Override public MediaType contentType() {
      return responseBody.contentType();
    }

    //响应的数据长度
    @Override public long contentLength() {
      return responseBody.contentLength();
    }

    //响应的数据来源
    @Override public BufferedSource source() {
      if (bufferedSource == null) {
        bufferedSource = Okio.buffer(source(responseBody.source()));
      }
      return bufferedSource;
    }

    private Source source(Source source) {
      return new ForwardingSource(source) {
        long totalBytesRead = 0L;

        @Override public long read(Buffer sink, long byteCount) throws IOException {
          //调用了父类的 read() 方法来读取数据，并将返回值（即实际读取的字节数）保存到 bytesRead 变量中。
          // 然后，通过判断 bytesRead 是否等于 -1，来判断是否已经读取完了所有的数据。
          // 如果 bytesRead 等于 -1，说明已经读取完了所有数据，此时将 bytesRead 设置为 0，
          // 否则就将 bytesRead 的值累加到 totalBytesRead 变量中。
          long bytesRead = super.read(sink, byteCount);
          totalBytesRead += bytesRead != -1 ? bytesRead : 0;
          //将已经读取的字节数、响应数据的总长度以及是否已经读取完毕的状态信息传递给监听器
          progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
          return bytesRead;
        }
      };
    }
  }

  interface ProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
  }
}
