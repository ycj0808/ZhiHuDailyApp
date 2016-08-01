package com.android.ice.zhihudaily.mvp.upload;

/**
 * Created by yangchj on 16/7/24.
 */
public class UploadResult {

    private String code;
    private String fileName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isSuccess(){
        if("0000".equals(code)){
            return true;
        }
        return false;
    }
}
