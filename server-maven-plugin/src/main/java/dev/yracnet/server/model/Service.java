/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.yracnet.server.model;

/**
 *
 * @author yracnet
 */
@lombok.Getter
@lombok.Setter
@lombok.ToString
public abstract class Service implements Comparable<Service>{
    private int order;
    private String path;
    private String filter[];
    private boolean skip;

    @Override
    public int compareTo(Service other) {
        return order - other.order;
    }
}
