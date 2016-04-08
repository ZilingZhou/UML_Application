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
import saf.components.AppWorkspaceComponent;
import saf.ui.AppGUI;

/**
 *
 * @author ziling
 */
public class AppEditController {
    AppTemplate app;
    private boolean selectionMode;
    Rectangle blend;
    Group blendg;
    private Pane currentSelect;
    public AppEditController(AppTemplate initApp){
        app = initApp;
        blend = new Rectangle();
        blend.setFill(Color.GREEN);
    }
    public void foolProof(){
        AppGUI gui = app.getGUI();
        unmark();
        selectionMode = false;
        gui.updateFoolProof();
    }
    public void handleSelectElementRequest(){
        selectionMode = true;
        
    }
    public void handleAddClassRequest(){
        foolProof();
        currentSelect = (Pane)app.getPageEditComponent().addClass();
        mark();
        
    }
    public void mark(){
        if(currentSelect != null){
            currentSelect.getStyleClass().add("uml_marked");
         //   currentSelect.setPrefSize(500, 500);
        }
    }
    public void unmark(){
        if(currentSelect != null){
            currentSelect.getStyleClass().add("uml_clean");
        //    currentSelect.getStyleClass().add("uml_container_pane");
        //    currentSelect.setPrefSize(200, 200);
            
        }
    }
    public void blend(){
        AppWorkspaceComponent comp = app.getWorkspaceComponent();
        blend.setLayoutX(currentSelect.getLayoutX()-10);
        blend.setLayoutY(currentSelect.getLayoutY()-10);
        blend.setWidth(currentSelect.getWidth()+20);
        blend.setHeight(currentSelect.getHeight()+20);
        comp.getWorkspace().getChildren().add(blend);
        
        /*      blendg = new Group();
        blendg.setBlendMode(BlendMode.MULTIPLY);
        blendg.getChildren().add(blend);
        blendg.getChildren().add(currentSelect);
      //  blendg.getChildren().add(blend);
       */ 
    }
    
}
