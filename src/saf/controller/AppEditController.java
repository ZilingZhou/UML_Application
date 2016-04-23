/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saf.controller;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import saf.AppTemplate;
import saf.components.AppPageEditComponent;
import saf.components.AppWorkspaceComponent;
import saf.ui.AppGUI;

/**
 *
 * @author ziling
 */
public class AppEditController {
    AppTemplate app;
    public AppEditController(AppTemplate initApp){
        app = initApp;

    }
    
    public void handleSelectElementRequest(){
        AppPageEditComponent comp = app.getPageEditComponent();
        comp.handleSelectElementRequest();
    }
    public void handleAddClassRequest(){
        AppPageEditComponent comp = app.getPageEditComponent();
        comp.handleAddClassRequest(false);
    }
    
   
    
}
