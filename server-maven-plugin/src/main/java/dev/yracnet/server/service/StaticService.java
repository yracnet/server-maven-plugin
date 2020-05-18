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
public class StaticService extends Service{
    private String resourceBase;
    private boolean directoriesListed;
    private String[] welcomeFiles;
    
}
