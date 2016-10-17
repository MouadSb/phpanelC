package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;





import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
public class PharmaPanel extends Application {

	@FXML
	private Button btn_select;

	@FXML
	private Label labelConfirm;

	private File file;

	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("welcomePanel.fxml"));
			Scene scene = new Scene(root,478,200);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		launch(args);
	}

	@FXML
	protected void locateFile(ActionEvent event) throws IOException{
		Node node = (Node) event.getSource();
	    FileChooser chooser = new FileChooser();
	    chooser.setTitle("Open File");
	    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
	    chooser.getExtensionFilters().add(extFilter);
	    file = chooser.showOpenDialog(node.getScene().getWindow());
	    if (file != null) {

	    	String fileName = file.getName();
	    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	    	String fileDate = sdf.format(file.lastModified());
	    	String fileExtension = fileName.substring(fileName.indexOf(".") + 1, file.getName().length());
	    	System.out.println(">> fileExtension" + fileName);
	    	labelConfirm.setText("Vous avez choisie le fichier : "+fileName + " modifié le : "+fileDate);
	    	}

	    /* Code envoie fichier OK!

		    final Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();

		    final FileDataBodyPart filePart = new FileDataBodyPart("file", file);
		    FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
		    final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("foo", "bar").bodyPart(filePart);

		    final WebTarget target = client.target("http://localhost:8080/pharmaapanel/rest/venteService/sendFile");
		    final Response response = target.request().post(Entity.entity(multipart, multipart.getMediaType()));
		    System.out.println(response.toString());

		    formDataMultiPart.close();
		    multipart.close();

		*/


	}






}