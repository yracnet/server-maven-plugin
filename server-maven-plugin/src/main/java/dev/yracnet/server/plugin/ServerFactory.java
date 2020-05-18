/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.yracnet.server.plugin;

import dev.yracnet.server.model.Config;
import dev.yracnet.server.model.Service;
import dev.yracnet.server.service.ProxyService;
import dev.yracnet.server.service.StaticService;

/**
 *
 * @author yracnet
 */
public class ServerFactory {
    public static Config createConfigDefault(){
        Config config = new Config();
        config.setHost("localhost");
        config.setPort(4000);
        return config;
    }
    public static Service createStaticServiceDefault(String webapp){
        StaticService config = new StaticService();
        config.setOrder(0);
        config.setPath("/");
        config.setResourceBase(webapp);
        config.setWelcomeFiles(new String[]{"index.html"});
        return config;
    }
    public static Service createProxyServiceDefault(){
        ProxyService config = new ProxyService();
        config.setOrder(0);
        config.setPath("/api");
        config.setServer("http://localhost:8080");
        config.setTarget("/api/server");
        config.setMethods("GET,POST,DELETE");
        return config;
    }
}
