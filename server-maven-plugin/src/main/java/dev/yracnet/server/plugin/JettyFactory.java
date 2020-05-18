/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.yracnet.server.plugin;

import dev.yracnet.server.model.Service;
import dev.yracnet.server.model.Config;
import dev.yracnet.server.service.ProxyService;
import dev.yracnet.server.service.StaticService;
import dev.yracnet.server.jetty.ConnectHandler;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 *
 * @author yracnet
 */
public class JettyFactory {

    public static Server createServer(Config config) {
        Server server = new Server(config.getPort());
        //result.set
        return server;
    }

    static ContextHandler createHandler(String root, Service it) {
        ContextHandler ch = new ContextHandler(root + it.getPath());
        //ch.set
        if (it instanceof StaticService) {
            StaticService sh = (StaticService) it;
            ResourceHandler rh = new ResourceHandler();
            rh.setResourceBase(sh.getResourceBase());
            rh.setDirectoriesListed(sh.isDirectoriesListed());
            rh.setWelcomeFiles(sh.getWelcomeFiles());
            ch.setHandler(rh);
        } else if (it instanceof ProxyService) {
            ProxyService ph = (ProxyService) it;
            ConnectHandler ch1 = new ConnectHandler();
//            ch.setResourceBase(ph.getResourceBase());
//            ch.setDirectoriesListed(ph.isDirectoriesListed());
//            ch.setWelcomeFiles(ph.getWelcomeFiles());
            ch.setHandler(ch1);
        } else {
            DefaultHandler h = new DefaultHandler();
            ch.setHandler(h);
        }
        return ch;
    }
}
