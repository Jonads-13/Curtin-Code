package edu.curtin.imageviewer;

import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene; 
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import java.io.File;

/**
 * Represents the main user interface for the Image Viewer application.
 */
public class MainWindow
{
    private Album album;
    private Stage stage;
    private int index;
    
    private BorderPane mainBox = new BorderPane();
    private ImageView imageWidget = new ImageView();
    private Label captionWidget = new Label();
    
    public MainWindow(Album album, Stage stage)
    {
        this.album = album;
        this.stage = stage;
        this.index = 0;
    }
    
    public File chooseAlbumFile()
    {
        FileChooser dialog = new FileChooser();
        dialog.setTitle("Select Album File");
        dialog.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        
        File currentDir = new File(System.getProperty("user.dir"));
        File resourcesDir = new File(currentDir, "resources");        
        if(resourcesDir.isDirectory())
        {
            dialog.setInitialDirectory(resourcesDir);
        }
        else
        {
            dialog.setInitialDirectory(currentDir);
        }
        return dialog.showOpenDialog(stage);
    }
    
    /**
     * Builds the main UI, based on an Album and a JavaFX 'Stage' (which is basically a 
     * pre-existing window).
     */
    public void show()
    {
        Platform.setImplicitExit(true);
        stage.setTitle("JavaFX Image Viewer");
        
        // *** Fix this code so that it loads the initial (first) image. ***
        ImageRecord image = album.getImage(index);
        String url = new File(image.getFilename()).toURI().toString();
        imageWidget.setImage(new Image(url));
        
        // Add 'mainBox' to the window. This is a container for holding the other bits: the toolbar, 
        // scroller (containing the image), and caption.
        Scene scene = new Scene(mainBox);
        stage.setScene(scene);
        
        Button prevBtn = new Button("Previous");
        Button nextBtn = new Button("Next");
        ToolBar toolBar = new ToolBar(prevBtn, nextBtn);
        mainBox.setTop(toolBar);
        
        // Set up nextBtnHandler() to be called when nextBtn is clicked, and similarly for prevBtn.
        // ('abc::xyz' is a method reference, essentially a tiny object that calls the specified 
        // method.)
        prevBtn.setOnAction(this::prevBtnHandler);
        nextBtn.setOnAction(this::nextBtnHandler);
        
        ScrollPane scroller = new ScrollPane();
        scroller.setContent(imageWidget);
        mainBox.setCenter(scroller);
        
        // FIXME: fix this code so that it displays the caption for the first image.
        captionWidget.setText(image.getCaption());
        mainBox.setBottom(captionWidget);
        
        stage.sizeToScene();
        stage.show();
    }
    
    /**
    * Retrieves the album.
    */
    public Album getAlbum()
    {
        return album;
    }

    /**
     * Called to handle "previous" button clicks.
     */
    private void prevBtnHandler(ActionEvent event)
    {
        index--;
        if(index < 0)
        {
            index = album.getSize() - 1;
        }
        ImageRecord image = album.getImage(index);
        String url = new File(image.getFilename()).toURI().toString();
        imageWidget.setImage(new Image(url));
        captionWidget.setText(image.getCaption());
    }

    /**
     * Called to handle "next" button clicks.
     */
    private void nextBtnHandler(ActionEvent event)
    {
        index++;
        if(index == album.getSize())
        {
            index = 0;
        }

        ImageRecord image = album.getImage(index);
        String url = new File(image.getFilename()).toURI().toString();
        imageWidget.setImage(new Image(url));
        captionWidget.setText(image.getCaption());
    }
}
