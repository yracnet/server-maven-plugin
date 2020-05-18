/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.yracnet.server.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 *
 * @author yracnet
 */
public class ConnectHandler extends AbstractHandler {

    final String _greeting;
    final String _body;

    public ConnectHandler() {
        _greeting = "Hello World";
        _body = null;
    }

    public ConnectHandler(String greeting) {
        _greeting = greeting;
        _body = null;
    }

    public ConnectHandler(String greeting, String body) {
        _greeting = greeting;
        _body = body;
    }

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(": " + target);
        System.out.println(": " + baseRequest);
        System.out.println(": " + request);
        System.out.println(": " + response);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FOUND);
        baseRequest.setHandled(true);

        response.getWriter().println("<h1>" + _greeting + "</h1>");
        if (_body != null) {
            response.getWriter().println(_body);
        }
    }
}
