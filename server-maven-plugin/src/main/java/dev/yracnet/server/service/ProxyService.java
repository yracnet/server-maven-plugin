/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.yracnet.server.service;

import dev.yracnet.server.model.Service;

/**
 *
 * @author yracnet
 */
@lombok.Getter
@lombok.Setter
@lombok.ToString
public class ProxyService extends Service{
    private String server;
    private String target;
    private String methods;
    
    
}
