/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui;

import java.io.File;

/**
 *
 * @author Potter
 */
public class ExecutionInfo {
    
    static String PATHSEPARATOR = File.separator;
    private String path;
    private String numDesc;
    private String eva;

    public ExecutionInfo() {
    }

    public ExecutionInfo(String path, String numDesc, String eva) {
        this.path = path;
        this.numDesc = numDesc;
        this.eva = eva;
    }
    
    public ExecutionInfo(ExecutionInfo info) {
        this.path = info.getAbsolutePath();
        this.numDesc = info.getNumDesc();
        this.eva = info.getEva();
    }

    public String getAbsolutePath(){
        return path;
    }
    public String getPath() {
        if(!path.isEmpty()){
            String fi = path.substring(path.lastIndexOf(PATHSEPARATOR) + 1);
            if( fi.contains("csv") )
                return fi;
        }
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNumDesc() {
        return numDesc;
    }

    public void setNumDesc(String numDesc) {
        this.numDesc = numDesc;
    }

    public String getEva() {
        return eva;
    }

    public void setEva(String eva) {
        this.eva = eva;
    }

    
}
