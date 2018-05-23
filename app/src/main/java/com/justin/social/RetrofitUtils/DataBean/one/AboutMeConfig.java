package com.justin.social.RetrofitUtils.DataBean.one;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */

public class AboutMeConfig extends BaseConfig implements Serializable {
    private List<AboutMeConfig> data;
    private String id;
    private String name;
    private String value;
    private String module;

    public List<AboutMeConfig> getData() {
        return data;
    }

    public void setData(List<AboutMeConfig> data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getCommanyPhone(){
        if(data!=null&&!data.isEmpty()){
            for(AboutMeConfig config:data){
                if(config.getName().equals("commanyPhone")){
                    return config.getValue();
                }
            }
        }
        return "";
    }
    public String getCommanyEmail(){
        if(data!=null&&!data.isEmpty()){
            for(AboutMeConfig config:data){
                if(config.getName().equals("commanyEmail")){
                    return config.getValue();
                }
            }
        }
        return "";
    }
    public String getCommanyAdress(){
        if(data!=null&&!data.isEmpty()){
            for(AboutMeConfig config:data){
                if(config.getName().equals("commanyAddress")){
                    return config.getValue();
                }
            }
        }
        return "";
    }

}
