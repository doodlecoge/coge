package com.szwx.yht.action;

import com.opensymphony.xwork2.ActionSupport;
import com.szwx.yht.bean.RegPeople;
import com.szwx.yht.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-24
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */

@Controller("query_by_id_name")
public class QueryByIdName extends ActionSupport {
    private String id;
    private String name;

    private Map<String, Object> jsonMap;


    @Autowired
    private IRegisterService registerService;

    @Override
    public String execute() throws Exception {
        RegPeople regPeople = registerService.getRegPeople(id);

        // type:
        // 0: id not found;
        // 1: name not match;
        // 2: normal

        jsonMap = new HashMap<String, Object>();

        if (regPeople == null || !regPeople.getTrueName().equals(name)) {
            jsonMap.put("success", false);

        } else {

            jsonMap.put("success", true);
            jsonMap.put("phone", regPeople.getMobile());
            jsonMap.put("name", regPeople.getTrueName());

        }

        return "json";
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getJsonMap() {
        return jsonMap;
    }
}
