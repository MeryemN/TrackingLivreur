package com.safeDelivery.view.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.safeDelivery.MainApp;
import com.safeDelivery.exceptions.ListInitException;
import com.safeDelivery.model.Adresse;
import com.safeDelivery.model.Client;
import com.safeDelivery.model.Livreur;
import com.safeDelivery.model.Plat;
import com.safeDelivery.model.Restaurant;
import com.safeDelivery.model.Restaurateur;
import com.safeDelivery.model.User;
import com.safeDelivery.model.Ville;
import com.safeDelivery.model.Zone;
import com.safeDelivery.restaurant.MainResto;
import com.safeDelivery.restaurant.view.controller.HomeController;
import com.safeDelivery.service.impl.AdresseServiceImpl;
import com.safeDelivery.service.impl.ClientServiceImpl;
import com.safeDelivery.service.impl.LivreurServiceImpl;
import com.safeDelivery.service.impl.PlatServiceImpl;
import com.safeDelivery.service.impl.RestaurantServiceImpl;
import com.safeDelivery.service.impl.RestaurateurServiceImpl;
import com.safeDelivery.service.impl.UserServiceImpl;
import com.safeDelivery.service.impl.VilleServiceImpl;
import com.safeDelivery.service.impl.ZoneServiceimpl;

import animatefx.animation.FadeInDown;
import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private MainApp mainApp;
	private Connection connection;
	private User userConnected = new User();
	private Client clientConnected = new Client();

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		if (connection == null)
			System.out.println("Connection nulle dans le controller");
		this.connection = connection;
		userService = new UserServiceImpl(connection);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}



	private int categorie; // 1 2 3
	User user = new User();
	Client client = new Client();
	Restaurateur restaurateur = new Restaurateur();
	Livreur livreur = new Livreur();
	Restaurant restaurant = new Restaurant();
	Ville ville = new Ville("pas de ville");
	Zone zone = new Zone("pas de zone", ville);
	Adresse adresse = new Adresse("pas d'adresse", zone);
	ObservableList<String> villes;
	ObservableList<String> zones;
	UserServiceImpl userService;

	@FXML
	private AnchorPane container;

	@FXML
	private Pane anchRoot;

	@FXML
	private Circle btnClose;

	@FXML
	private Circle btnMin;

	@FXML
	private Label lblForgot2;

	@FXML
	private StackPane pnlStack;

	@FXML
	private Pane pnlChoix;

	@FXML
	private ImageView btnBackChoix;

	@FXML
	private Button choixClient;

	@FXML
	private Button choixLivreur;

	@FXML
	private Button choixResto;

	@FXML
	private Pane addRestaurant;

	@FXML
	private TextField tNomResto;

	@FXML
	private Button btnValidResto;

	@FXML
	private ImageView btnBackResto;

	@FXML
	private Label lblForgot11;

	@FXML
	private TextField tAdresseResto;

	@FXML
	private Label lblForgot112;

	@FXML
	private Label lblForgot113;

	@FXML
	private ComboBox<String> cbVilleResto;

	@FXML
	private Label lblForgot1131;

	@FXML
	private ComboBox<String> cbZoneResto;

	@FXML
	private Label lblErrorNomResto;

	@FXML
	private Label lblErrorVilleResto;

	@FXML
	private Label lblErrorZoneResto;

	@FXML
	private Label lblErrorAdresseResto;

	@FXML
	private Label lblAnnulerResto;

	@FXML
	private Pane pnlSignUpNomPrenom;

	@FXML
	private ImageView btnBackNomPrenom;

	@FXML
	private TextField tprenom;

	@FXML
	private Button btnContinuerNomPrenom;

	@FXML
	private Label lblForgot1111;

	@FXML
	private TextField tnom;

	@FXML
	private Label lblForgot11111;

	@FXML
	private TextField ttel;

	@FXML
	private Label lblForgot111111;

	@FXML
	private Label lblErrorPrenom;

	@FXML
	private Label lblErrorNom;

	@FXML
	private Label lblErrorTel;

	@FXML
	private Label lblAnnulerNomPrenom;

	@FXML
	private Pane pnlSignUpEmailPass;

	@FXML
	private ImageView btnBackEmailPass;

	@FXML
	private TextField tEmailInsc;

	@FXML
	private Button btnContinuerEmailPass;

	@FXML
	private Label lblForgot111;

	@FXML
	private PasswordField tPassInsc;

	@FXML
	private PasswordField tpassConfirm;

	@FXML
	private Label lblErrorEmail;

	@FXML
	private Label lblErrorPass;

	@FXML
	private Label lblAnnulerEmailPass;

	@FXML
	private Pane pnlLogin;

	@FXML
	private TextField tEmail;
	
	@FXML
    private TextField tPassShowed;

	@FXML
	private PasswordField tPass;

	@FXML
	private Button btnConn;

	@FXML
	private Button btnInscr;

	@FXML
	private Label lblForgot;

	@FXML
	private Label lblForgot1;

	@FXML
	private Label lblErrorEmailConn;

	@FXML
	private Label lblErrorPassConn;
	
	@FXML
    private ImageView passShow;

    @FXML
    private ImageView passHide;

	void showResto() throws IOException {
//		System.out.println("User connected "+ userConnected);
		Restaurateur restaurateur = new Restaurateur(userConnected);
//		System.out.println("Restaurateur: "+restaurateur);
		RestaurantServiceImpl impl = new RestaurantServiceImpl(connection);
		Restaurant restaurant = impl.findByRestaurateur(restaurateur.getId());

		// load a borderPane

		try {
//			primaryStage = new Stage();
			this.primaryStage = new Stage();
//			this.primaryStage.setResizable(false);
//	        this.primaryStage
			this.primaryStage.setTitle("SafeDelivery - " + restaurant.getNom());
			this.primaryStage.getIcons().add(new Image("file:resources/images/logo_sans_titre.png"));

			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MainBorderDashboard.fxml"));
			rootLayout = (BorderPane) loader.load();
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.initOwner(mainApp.getPrimaryStage());
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// load the dashboard Anchorpane

		FXMLLoader restoLoader = new FXMLLoader();
		restoLoader.setLocation(MainResto.class.getResource("view/Home.fxml"));

		Parent root = (AnchorPane) restoLoader.load();

		HomeController controller = restoLoader.getController();
		controller.setRestaurant(restaurant);
		controller.setMain(this);
		controller.setStage(primaryStage);
		if (connection == null) {
			System.out.println("Connection nulle dans loginController");
		}
		controller.setConnection(connection);

//        primaryStage.setScene(new Scene(root));

		rootLayout.setCenter(root);
//        primaryStage.show();
		controller.loadAllCommandes(LocalDate.now());
		controller.setNomResto();

	}
	
	private void showClient() throws IOException {
		clientConnected = new Client(userConnected);
		
		
		this.primaryStage = new Stage();
//		this.primaryStage.setResizable(false);
//        this.primaryStage
		this.primaryStage.setTitle("SafeDelivery - " + clientConnected.getNom());
		this.primaryStage.getIcons().add(new Image("file:resources/images/logo_sans_titre.png"));
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/ClientHome.fxml"));
		Parent root = loader.load();
		ClientHomeController clientHomeController = loader.getController();
		clientHomeController.setConnection(connection);
		PlatServiceImpl platService = new PlatServiceImpl(this.connection);
		clientHomeController.setMain(this.mainApp);
		clientHomeController.setClient(clientConnected);
		List<Plat> plats = platService.getRandomPlat();
		if(plats == null) System.out.println("Les randoms plas sont nulls");
		clientHomeController.setRandomPlat(plats);
		clientHomeController.loadPlat();
		VilleServiceImpl villeService = new VilleServiceImpl(this.connection);
		List<String> findAllVille = villeService.findAll();
		findAllVille.add(0,"tous");
		clientHomeController.loadVilles(findAllVille);
		RestaurantServiceImpl restaurantService = new RestaurantServiceImpl(this.connection);
		List<String> findAllResto = restaurantService.findAll();
		findAllResto.add(0,"tous");
		clientHomeController.loadRestaurants(findAllResto);
		primaryStage.setScene(new Scene(root));
//		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.initOwner(mainApp.getPrimaryStage());
		clientHomeController.setPrimaryStage(primaryStage);
		primaryStage.show();
	}

	void showDashboard() throws IOException {
//		System.out.println("UserConnected id " + this.userConnected.getId());
		if (this.userConnected != null) {
//			System.out.println("User categorie " + this.userConnected.getCategorie());
			if (this.userConnected.getCategorie() == 2) {
				showResto();
			}
			else {
				showClient();
			}

		} else {
			System.out.println("UserConnected null");
		}

	}


	@FXML
	void handleButtonAction(ActionEvent event) throws IOException {
		// choix du type d'inscription

		if (event.getSource().equals(btnInscr)) {
//			new SlideInLeft(pnlChoix);
			new ZoomIn(pnlChoix).play();
			pnlChoix.toFront();
		}
		if (event.getSource().equals(choixClient)) {
			this.categorie = 1;
			user.setCategorie(categorie);
			new ZoomIn(pnlSignUpEmailPass).play();
			pnlSignUpEmailPass.toFront();
		}

		if (event.getSource().equals(choixResto)) {
			this.categorie = 2;
			user.setCategorie(categorie);
			System.out.println("categorie " + categorie);
			new ZoomIn(pnlSignUpEmailPass).play();
			pnlSignUpEmailPass.toFront();
		}

		if (event.getSource().equals(choixLivreur)) {
			this.categorie = 3;
			user.setCategorie(categorie);
			new ZoomIn(pnlSignUpEmailPass).play();
			pnlSignUpEmailPass.toFront();
		}
		// panel : Email and password
		if (event.getSource().equals(btnConn)) {
			lblErrorEmailConn.setText("");
			lblErrorPassConn.setText("");
			boolean error = false;
			if (tEmail.getText() == null || tEmail.getText().isEmpty() || !isValidEmail(tEmail.getText())) {
				error = true;
				tEmail.setText("");
				lblErrorEmailConn.setText("Veuillez saiser une adresse email vailde");
			}
			if(tPass.isVisible()) {
				if (tPass.getText() == null || tPass.getText().length() < 6) {
					error = true;
					tPass.setText("");
					lblErrorPassConn.setText("Mot de passe incorrecte. Au moins 6 caract�res.");
				}
			}
			else {
				if (tPassShowed.getText() == null || tPassShowed.getText().length() < 6) {
					error = true;
					tPassShowed.setText("");
					lblErrorPassConn.setText("Mot de passe incorrecte. Au moins 6 caract�res.");
				}
			}

			if (!error) {
				
				lblErrorEmailConn.setText("");
				lblErrorPassConn.setText("");
				
				userService = new UserServiceImpl(connection);
				String email = tEmail.getText();
				String pass;
				
				if(tPass.isVisible()) {
					pass = tPass.getText();
					tPass.setText("");
				}
				else {
					pass = tPassShowed.getText();
					tPassShowed.setText("");
				}
				long exist = userService.existByEmailAndPass(email, pass);
//				tEmail.setText("");
				if (exist <= 0) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.initOwner(mainApp.getPrimaryStage());
					alert.setTitle("Secure Delivery");
					alert.setHeaderText("Erreur lors de la connexion");
					alert.setContentText("Email ou mot de passe incorrect");
					alert.showAndWait();
				} else {
					this.userConnected = userService.getUserByEmail(email);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.initOwner(mainApp.getPrimaryStage());
					alert.setTitle("Secure Delivery");
					alert.setHeaderText("Succ�s");
					alert.setContentText("Votre �tes connect�");
					alert.showAndWait();
					showDashboard();
				}
			}
		}
		if (event.getSource().equals(btnContinuerEmailPass)) {
			lblErrorEmail.setText("");
			lblErrorPass.setText("");
			boolean error = false;
			if (tEmailInsc.getText() == null || tEmailInsc.getText().length() == 0
					|| !isValidEmail(tEmailInsc.getText())) {
				error = true;
//				tEmail.setStyle("-fx-border-color:red");
//				tEmail.setStyle("-fx-background-color:red");
//				tEmail.setStyle("-fx-border-color:#424242; -fx-border-width:1px;-fx-background-color:rgba(255, 255, 255, 0.87);");
				lblErrorEmail.setText("Adresse email incorrecte");
			}
			else {
				UserServiceImpl impl = new UserServiceImpl(connection);
				if(impl.getUserByEmail(tEmailInsc.getText()) != null) {
					error = true;
					lblErrorEmail.setText("Adresse email d�j� utilis�e.");
				}
			}
			if (tPassInsc.getText() == null || tpassConfirm.getText() == null || tPassInsc.getText().length() < 6) {
				error = true;
				tPassInsc.setText("");
				tpassConfirm.setText("");
				lblErrorPass.setText("Mot de passe incorrecte. Au moins 6 caract�res.");
			} else if (!tPassInsc.getText().equals(tpassConfirm.getText())) {
				error = true;
				tPassInsc.setText("");
				tpassConfirm.setText("");
				lblErrorPass.setText("Les 2 mots de passe doivent �tre conformes.");
			}
			if (!error) {
				user.setEmail(tEmailInsc.getText());
				user.setPass(tPassInsc.getText());
				new ZoomIn(pnlSignUpNomPrenom).play();
				pnlSignUpNomPrenom.toFront();
				if (this.categorie == 2) {
					btnContinuerNomPrenom.setText("Continuer");
				} else {
					btnContinuerNomPrenom.setText("Terminer");
				}
//				System.out.println(user);
			}
		}

		if (event.getSource().equals(btnContinuerNomPrenom)) {
			boolean error = false;
			if (tprenom.getText().isEmpty()) {
				error = true;
				tprenom.setText("");
				lblErrorPrenom.setText("veuillez remplir ce champ .");
			}
			if (tnom.getText().isEmpty()) {
				error = true;
				tnom.setText("");
				lblErrorNom.setText("veuillez remplir ce champ .");
			}
			if (ttel.getText().isEmpty() || !isValidTel(ttel.getText())) {
				error = true;
				ttel.setText("");
				lblErrorTel.setText("veuillez entrer un num�ro de t�l�phone valide.");
			}
			if (!error) {
				user.setNom(tnom.getText());
				user.setPrenom(tprenom.getText());
				user.setTel(ttel.getText());
				if (this.categorie == 2) {
					new ZoomIn(addRestaurant).play();
					addRestaurant.toFront();
				} else {
					long i;
					if (this.categorie == 1) {
						client = new Client(user, adresse);
						ClientServiceImpl clientservice = new ClientServiceImpl(connection);
						i = clientservice.addClient(client);
						
						
					} else {
						livreur = new Livreur(user);
						LivreurServiceImpl livreurService = new LivreurServiceImpl(connection);
						i = livreurService.addLivreur(livreur);
					}
					if (i <= 0) {
						
						System.out.println("Save client retourne " + i);
						
						Alert alert = new Alert(AlertType.WARNING);
						alert.initOwner(mainApp.getPrimaryStage());
						alert.setTitle("Secure Delivery");
						alert.setHeaderText("Erreur lors de l'enregistrement");
						alert.setContentText("Une erreur s'est produite lors de l'enregistrement");
						alert.showAndWait();
					} else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.initOwner(mainApp.getPrimaryStage());
						alert.setTitle("Secure Delivery");
						alert.setHeaderText("Succ�s");
						alert.setContentText("Votre compte a �t� cr��e avec succ�s. Connectez-vous.");
						alert.showAndWait();

					}
					clearInputs();
					new ZoomIn(pnlLogin).play();
					pnlLogin.toFront();

				}
			}
		}

		if (event.getSource().equals(btnValidResto)) {
			boolean error = false;
			if (tNomResto.getText().isEmpty()) {
				error = true;
				lblErrorNomResto.setText("veuillez remplir ce champ.");
			}
			if (cbVilleResto.getValue() == null || cbVilleResto.getValue().isEmpty()) {
				error = true;
				lblErrorVilleResto.setText("veuillez choisir une ville.");
			}
			if (cbZoneResto.getValue() == null || cbZoneResto.getValue().isEmpty()) {
				error = true;
				lblErrorZoneResto.setText("veuillez choisir une zone.");
			}
			if (tAdresseResto.getText().isEmpty()) {
				error = true;
				lblErrorAdresseResto.setText("veuillez saisir une adresse.");
			}

			if (!error) {
				restaurateur = new Restaurateur(user);
				RestaurateurServiceImpl restaurateurService = new RestaurateurServiceImpl(connection);
				restaurant.setNom(tNomResto.getText());
				restaurant.setRestaurateur(restaurateur);
				Adresse adresse = new Adresse();
				VilleServiceImpl villeServiceImpl = new VilleServiceImpl(connection);
				Ville ville = villeServiceImpl.findById(villeServiceImpl.existByName(cbVilleResto.getValue()));
				zone.setVille(ville);
				zone.setNom(cbZoneResto.getValue());
				adresse.setDetail(tAdresseResto.getText());
				ZoneServiceimpl zoneServiceimpl = new ZoneServiceimpl(connection);
				adresse.setZone(zoneServiceimpl.findById(zoneServiceimpl.existByName(cbZoneResto.getValue())));
				restaurant.setAdresse(adresse);
				boolean okay = true;
				
				AdresseServiceImpl adresseServiceImpl = new AdresseServiceImpl(connection);
				long res = adresseServiceImpl.saveAdresse(adresse);
				System.out.println("Id de l'adresse "+res);
				adresse.setId(res);
				if(res <= 0) {
					okay = false;
				}
				else {
					long result = restaurateurService.addRestaurateur(restaurateur);
					System.out.println("Id du restaurateur "+result);
					restaurateur.setId(result);
					if(result <= 0) {
						okay = false;
					}
					else {
						RestaurantServiceImpl restaurantServiceImpl = new RestaurantServiceImpl(connection);
						long result2 = restaurantServiceImpl.addRestaurant(restaurant);
						System.out.println("Id du restaurant "+result2);
						if(result2 <= 0) {
							okay = false;
						}
					}
				}
				
				
				if (!okay) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.initOwner(mainApp.getPrimaryStage());
					alert.setTitle("Secure Delivery");
					alert.setHeaderText("Erreur lors de l'enregistrement");
					alert.setContentText("Une erreur s'est produite lors de l'enregistrement");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.initOwner(mainApp.getPrimaryStage());
					alert.setTitle("Secure Delivery");
					alert.setHeaderText("Succ�s");
					alert.setContentText("Votre compte a �t� cr��e avec succ�s. Connectez-vous.");
					alert.showAndWait();

				}
				clearInputs();
				new ZoomIn(pnlLogin).play();
				pnlLogin.toFront();
			}

		}

	}

	@FXML
	void handleMouseEvent(MouseEvent event) {

		if (event.getSource().equals(lblAnnulerNomPrenom) || event.getSource().equals(lblAnnulerEmailPass)
				|| event.getSource().equals(lblAnnulerResto)) {
			clearInputs();
			new ZoomIn(pnlLogin).play();
			pnlLogin.toFront();
		}

		if (event.getSource().equals(btnBackChoix)) {
			new ZoomIn(pnlLogin).play();
			pnlLogin.toFront();
		}
		if (event.getSource().equals(btnBackEmailPass)) {
			new ZoomIn(pnlChoix).play();
			pnlChoix.toFront();
		}
		if (event.getSource().equals(btnBackNomPrenom)) {
			new ZoomIn(pnlSignUpEmailPass).play();
			pnlSignUpEmailPass.toFront();
		}
		if (event.getSource().equals(btnClose)) {
			new ZoomOut(container).play();
			Platform.exit();
//    		System.exit(0);
		}
		if (event.getSource().equals(btnMin)) {
			new FadeInDown(container).play();
//    		System.exit(0);
		}
	}

	public boolean isValidEmail(String emailAddress) {
//		return emailAddress.contains(" ") && emailAddress.matches(".+@.+\\.[az]+"); String regex = ^[a-zA-Z0-9_!#$%&�*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(emailAddress);
		return matcher.matches();
	}

	public boolean isValidTel(String tel) {
		String regex = "(\\+212|0)([ \\-_/]*)(\\d[ \\-_/]*){9}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(tel);
		return matcher.matches();

	}

//	private void setRed(TextField tf) {
//		ObservableList<String> styleClass = tf.getStyleClass();
//		styleClass.add("error");
//
//	}
//
//	private void removeRed(TextField tf) {
//		ObservableList<String> styleClass = tf.getStyleClass();
//		styleClass.removeAll(Collections.singleton("error"));
//	}

	public void clearInputs() {
		tEmailInsc.setText("");
		tPassInsc.setText("");
		tpassConfirm.setText("");
		tprenom.setText("");
		tnom.setText("");
		ttel.setText("");

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	public void fillVillesAndZones() {
		VilleServiceImpl villeService = new VilleServiceImpl(connection);
		ZoneServiceimpl zoneService = new ZoneServiceimpl(connection);

		try {
			List<String> villeFindAll = new ArrayList<String>(villeService.findAll());
			List<String> zoneFindAll = new ArrayList<String>(zoneService.findAll());
			villes = FXCollections.observableArrayList(villeFindAll);
			zones = FXCollections.observableArrayList(zoneFindAll);
			if (villes == null || zones == null || villes.isEmpty() || zones.isEmpty()) {
				throw new ListInitException();
			}
//			System.out.println(villes);
			cbVilleResto.setItems(villes);
			cbZoneResto.setItems(zones);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
//			alert.initOwner(mainApp.getPrimaryStage());
//			System.out.println("hello world");
			alert.setTitle("Secure Delivery");
			alert.setHeaderText("Erreur lors du chargement de la page");
			alert.setContentText("V�rifier votre connexion");
			alert.showAndWait();
			Platform.exit();
		}

	}
	
	@FXML
	void showOrhide(MouseEvent event) {
		if(event.getSource().equals(passShow)) {
			passShow.setVisible(false);
			passHide.setVisible(true);
			tPassShowed.setVisible(true);
			tPassShowed.setText(tPass.getText());
			tPass.setVisible(false);
		}
		else {
			passHide.setVisible(false);
			passShow.setVisible(true);
			tPass.setVisible(true);
			tPass.setText(tPassShowed.getText());
			tPassShowed.setVisible(false);
		}
	}

}
