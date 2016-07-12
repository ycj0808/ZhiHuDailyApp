package com.android.ice.zhihudaily.mvp.download;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * 用于下载进度监听
 * Created by yangchj on 2016/7/12 0012.
 * email:yangchj@neusoft.com
 */
public class ProgressResponseBody extends ResponseBody {

    //实际的待包装响应体
    private final ResponseBody responseBody;
    //进度回调接口
    private final ProgressResponseListener progressListener;
    //包装完成的BufferedSource
    private BufferedSource bufferedSource;

    /**
     * 构造函数
     * @param responseBody      待包装的响应体
     * @param progressListener  回调接口
     */
    public ProgressResponseBody(ResponseBody responseBody, ProgressResponseListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }

    /**
     * 重写调用实际的响应体的contentType
     * @return
     */
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    /**
     * 重写调用实际的响应体的contentLength
     * @return
     */
    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    /**
     * 重写进行包装source
     * @return
     */
    @Override
    public BufferedSource source() {
        if(bufferedSource==null){
            //包装
            bufferedSource= Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    /**
     * 读取，回调进度接口
     * @param source
     * @return
     */
    private Source source(Source source){
        return new ForwardingSource(source) {
            //当前读取字节数
            long totalBytesRead=0L;
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead=super.read(sink,byteCount);
                //增加当前区区的字节数，如果读取完成了bytesRead会返回-1
                totalBytesRead+=bytesRead!=-1?bytesRead:0;
                //回调，如果contentlength()不知道长度，会返回-1
                if(progressListener!=null){
                    progressListener.onResponseProgress(totalBytesRead,responseBody.contentLength(),bytesRead==-1);
                }
                return bytesRead;
            }
        };
    }
}
