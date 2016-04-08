package saf.ui;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import saf.controller.AppFileController;
import saf.AppTemplate;
import static saf.settings.AppPropertyType.*;
import static saf.settings.AppStartupConstants.FILE_PROTOCOL;
import static saf.settings.AppStartupConstants.PATH_IMAGES;
import saf.components.AppStyleArbiter;
import static saf.components.AppStyleArbiter.CLASS_BORDERED_PANE;
import static saf.components.AppStyleArbiter.CLASS_FILE_BUTTON;
import saf.controller.AppEditController;
import static saf.settings.AppStartupConstants.FILE_PROTOCOL;
import static saf.settings.AppStartupConstants.PATH_IMAGES;

/**
 * This class provides the basic user interface for this application,
 * including all the file controls, but not including the workspace,
 * which would be customly provided for each app.
 * 
 * @author Richard McKenna
 * @author ?
 * @version 1.0
 */
public class AppGUI implements AppStyleArbiter {
    // THIS HANDLES INTERACTIONS WITH FILE-RELATED CONTROLS
    protected AppFileController fileController;
    protected AppEditController appEditController;
    // THIS IS THE APPLICATION WINDOW
    protected Stage primaryStage;

    // THIS IS THE STAGE'S SCENE GRAPH
    protected Scene primaryScene;

    // THIS PANE ORGANIZES THE BIG PICTURE CONTAINERS FOR THE
    // APPLICATION AppGUI
    protected BorderPane appPane;
    
    // THIS IS THE TOP TOOLBAR AND ITS CONTROLS
    protected FlowPane fileToolbarPane;
    protected ToolBar bar1;
    protected ToolBar bar2;
    protected ToolBar bar3;
    protected HBox hb1;
    protected HBox hb1C;
    protected VBox vb1;
    protected Button newButton;
    protected Button loadButton;
    protected Button saveButton;
    protected Button exitButton;
    protected Button saveAs;
    protected Button photo;
    protected Button code;
    //----------------------
    protected HBox hb2;
    Button select;
    Button resize;
    protected Button addInterface;
    protected Button addClass;
    Button remove;
    Button undo;
    Button redo;
    //------------------------
    protected HBox hb3;
    protected VBox vb3;
    protected Button zoomIn;
    protected Button zoomOut;
    protected CheckBox grid;
    protected CheckBox snap;
    
    
    
    
    
    
    // HERE ARE OUR DIALOGS
    protected AppYesNoCancelDialogSingleton yesNoCancelDialog;
    
    protected String appTitle;
    
    /**
     * This constructor initializes the file toolbar for use.
     * 
     * @param initPrimaryStage The window for this application.
     * 
     * @param initAppTitle The title of this application, which
     * will appear in the window bar.
     * 
     * @param app The app within this gui is used.
     */
    public AppGUI(  Stage initPrimaryStage, 
		    String initAppTitle, 
		    AppTemplate app){
	// SAVE THESE FOR LATER
	primaryStage = initPrimaryStage;
	appTitle = initAppTitle;
	       
        // INIT THE TOOLBAR
        initFileToolbar(app);
        //INIT THE HANDLER
        initController(app);
        // AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
        initWindow();
    }
    
    /**
     * Accessor method for getting the application pane, within which all
     * user interface controls are ultimately placed.
     * 
     * @return This application GUI's app pane.
     */
    public BorderPane getAppPane() { return appPane; }
    
    /**
     * Accessor method for getting this application's primary stage's,
     * scene.
     * 
     * @return This application's window's scene.
     */
    public Scene getPrimaryScene() { return primaryScene; }
    
    /**
     * Accessor method for getting this application's window,
     * which is the primary stage within which the full GUI will be placed.
     * 
     * @return This application's primary stage (i.e. window).
     */    
    public Stage getWindow() { return primaryStage; }

    /**
     * This method is used to activate/deactivate toolbar buttons when
     * they can and cannot be used so as to provide foolproof design.
     * 
     * @param saved Describes whether the loaded Page has been saved or not.
     */
    public void updateToolbarControls(boolean saved) {
        // THIS TOGGLES WITH WHETHER THE CURRENT COURSE
        // HAS BEEN SAVED OR NOT
        saveButton.setDisable(saved);

        // ALL THE OTHER BUTTONS ARE ALWAYS ENABLED
        // ONCE EDITING THAT FIRST COURSE BEGINS
	newButton.setDisable(false);
        loadButton.setDisable(false);
	exitButton.setDisable(false);
        
        saveAs.setDisable(false);
        photo.setDisable(false);
        code.setDisable(false);
        
        addClass.setDisable(false);
        addInterface.setDisable(false);
        
        zoomIn.setDisable(false);
        zoomOut.setDisable(false);
        snap.setDisable(false);
        grid.setDisable(false);
        // NOTE THAT THE NEW, LOAD, AND EXIT BUTTONS
        // ARE NEVER DISABLED SO WE NEVER HAVE TO TOUCH THEM
    }
    public void updateFoolProof(){
        select.setDisable(true);
        resize.setDisable(false);
        remove.setDisable(true);
        undo.setDisable(true);
        redo.setDisable(true);
    }
    /****************************************************************************/
    /* BELOW ARE ALL THE PRIVATE HELPER METHODS WE USE FOR INITIALIZING OUR AppGUI */
    /****************************************************************************/
    
    /**
     * This function initializes all the buttons in the toolbar at the top of
     * the application window. These are related to file management.
     */
    private void initFileToolbar(AppTemplate app) {
        fileToolbarPane = new FlowPane(20,0);
        fileToolbarPane.setPadding(new Insets(5));
        vb1 = new VBox(2);
        hb1 = new HBox(2);
        hb1.setPadding(new Insets(15,0,0,0));
        hb1C = new HBox();
        hb1C.setPadding(new Insets(10));
        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
        // START AS ENABLED (false), WHILE OTHERS DISABLED (true)
        newButton = initChildButton(hb1,	NEW_ICON.toString(),	    NEW_TOOLTIP.toString(),	false);
        loadButton = initChildButton(hb1,	LOAD_ICON.toString(),	    LOAD_TOOLTIP.toString(),	false);
        saveButton = initChildButton(hb1,	SAVE_ICON.toString(),	    SAVE_TOOLTIP.toString(),	true);
        saveAs = initChildButton(hb1, SAVE_AS_ICON.toString(), SAVE_AS_TOOLTIP.toString(), true);
        exitButton = initChildButton(hb1,	EXIT_ICON.toString(),	    EXIT_TOOLTIP.toString(),	false);
        
        photo = initChildButton(vb1, EXPORT_PHOTO_ICON.toString(), EXPORT_PHOTO_TOOLTIP.toString(), true);
        code = initChildButton(vb1, EXPORT_CODE_ICON.toString(), EXPORT_CODE_TOOLTIP.toString(), true);
        code.prefHeightProperty().bind(photo.prefHeightProperty());
        code.prefWidthProperty().bind(photo.prefWidthProperty());        
        hb1C.getChildren().addAll(hb1,vb1);
        //------------
        
        hb2 = new HBox(2);
        hb2.setPadding(new Insets(17));
    select = initChildButton(hb2, SELECT_ICON.toString(), SELECT_TOOLTIP.toString(), true);
    resize = initChildButton(hb2, RESIZE_ICON.toString(), RESIZE_TOOLTIP.toString(), true);
    addClass = initChildButton(hb2, ADD_CLASS_ICON.toString(), ADD_CLASS_TOOLTIP.toString(), true);
    addInterface = initChildButton(hb2, ADD_INTERFACE_ICON.toString(), ADD_INTERFACE_TOOLTIP.toString(), true);
    remove = initChildButton(hb2, REMOVE_ICON.toString(), REMOVE_TOOLTIP.toString(), true);
    undo = initChildButton(hb2, UNDO_ICON.toString(), UNDO_TOOLTIP.toString(), true);
    redo = initChildButton(hb2, REDO_ICON.toString(), REDO_TOOLTIP.toString(), true);
    
    hb3 = new HBox(2);
    hb3.setPadding(new Insets(16));
    vb3 = new VBox(2);
    zoomIn = initChildButton(hb3, ZOOM_IN_ICON.toString(), ZOOM_IN_TOOLTIP.toString(), true);
    zoomOut = initChildButton(hb3, ZOOM_OUT_ICON.toString(), ZOOM_OUT_TOOLTIP.toString(), true);
    snap = new CheckBox("snap");
    snap.setDisable(true);
    grid = new CheckBox("grid");
    grid.setDisable(true);
    vb3.getChildren().addAll(snap,grid);
    hb3.getChildren().add(vb3);
    
    fileToolbarPane.getChildren().addAll(hb1C,hb2,hb3);
    
	
    }
    public void initController(AppTemplate app){
        // AND NOW SETUP THEIR EVENT HANDLERS
        fileController = new AppFileController(app);
        appEditController = new AppEditController(app);
        
        newButton.setOnAction(e -> {
            fileController.handleNewRequest();
        });
        loadButton.setOnAction(e -> {
       //     fileController.handleLoadRequest();
        });
        saveButton.setOnAction(e -> {
            fileController.handleSaveRequest();
        });
        exitButton.setOnAction(e -> {
            fileController.handleExitRequest();
        });
        
        select.setOnAction(e -> {
            appEditController.handleSelectElementRequest();
        });
        
        addClass.setOnAction(e ->{
            appEditController.handleAddClassRequest();
        });
        
        
    }
    public Button initBarItem(ToolBar toolbar, String icon, String tooltip, boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
	
	// LOAD THE ICON FROM THE PROVIDED FILE
        String imagePath = FILE_PROTOCOL + PATH_IMAGES + props.getProperty(icon);
        Image buttonImage = new Image(imagePath);
	
	// NOW MAKE THE BUTTON
        Button button = new Button();
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip));
        button.setTooltip(buttonTooltip);
	
	// PUT THE BUTTON IN THE TOOLBAR
        toolbar.getItems().add(button);
	
	// AND RETURN THE COMPLETED BUTTON
        return button;
    }

    // INITIALIZE THE WINDOW (i.e. STAGE) PUTTING ALL THE CONTROLS
    // THERE EXCEPT THE WORKSPACE, WHICH WILL BE ADDED THE FIRST
    // TIME A NEW Page IS CREATED OR LOADED
    private void initWindow() {
        // SET THE WINDOW TITLE
        primaryStage.setTitle(appTitle);

        // GET THE SIZE OF THE SCREEN
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // AND USE IT TO SIZE THE WINDOW
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        // ADD THE TOOLBAR ONLY, NOTE THAT THE WORKSPACE
        // HAS BEEN CONSTRUCTED, BUT WON'T BE ADDED UNTIL
        // THE USER STARTS EDITING A COURSE
        appPane = new BorderPane();
        appPane.setTop(fileToolbarPane);
        primaryScene = new Scene(appPane);
        
        // SET THE APP ICON
	PropertiesManager props = PropertiesManager.getPropertiesManager();
        String appIcon = FILE_PROTOCOL + PATH_IMAGES + props.getProperty(APP_LOGO);
        primaryStage.getIcons().add(new Image(appIcon));
           
        // NOW TIE THE SCENE TO THE WINDOW AND OPEN THE WINDOW
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
    
    /**
     * This is a public helper method for initializing a simple button with
     * an icon and tooltip and placing it into a toolbar.
     * 
     * @param toolbar Toolbar pane into which to place this button.
     * 
     * @param icon Icon image file name for the button.
     * 
     * @param tooltip Tooltip to appear when the user mouses over the button.
     * 
     * @param disabled true if the button is to start off disabled, false otherwise.
     * 
     * @return A constructed, fully initialized button placed into its appropriate
     * pane container.
     */
    public Button initChildButton(Pane toolbar, String icon, String tooltip, boolean disabled) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
	
	// LOAD THE ICON FROM THE PROVIDED FILE
        String imagePath = FILE_PROTOCOL + PATH_IMAGES + props.getProperty(icon);
        Image buttonImage = new Image(imagePath);
	
	// NOW MAKE THE BUTTON
        Button button = new Button();
        button.setDisable(disabled);
        button.setGraphic(new ImageView(buttonImage));
        Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip));
        button.setTooltip(buttonTooltip);
	
	// PUT THE BUTTON IN THE TOOLBAR
        toolbar.getChildren().add(button);
	
	// AND RETURN THE COMPLETED BUTTON
        return button;
    }
    
    /**
     * This function specifies the CSS style classes for the controls managed
     * by this framework.
     */
    @Override
    public void initStyle() {
	fileToolbarPane.getStyleClass().add(CLASS_BORDERED_PANE);
        hb1C.getStyleClass().add(CLASS_BORDERED_PANE);
        hb2.getStyleClass().add(CLASS_BORDERED_PANE);
        hb3.getStyleClass().add(CLASS_BORDERED_PANE);
        newButton.getStyleClass().add(CLASS_FILE_BUTTON);
	loadButton.getStyleClass().add(CLASS_FILE_BUTTON);
	saveButton.getStyleClass().add(CLASS_FILE_BUTTON);
	exitButton.getStyleClass().add(CLASS_FILE_BUTTON);
    }

    /**
     * @return the select
     */
    public Button getSelect() {
        return select;
    }

    /**
     * @return the resize
     */
    public Button getResize() {
        return resize;
    }

    /**
     * @return the remove
     */
    public Button getRemove() {
        return remove;
    }

    /**
     * @return the undo
     */
    public Button getUndo() {
        return undo;
    }

    /**
     * @return the redo
     */
    public Button getRedo() {
        return redo;
    }
}
