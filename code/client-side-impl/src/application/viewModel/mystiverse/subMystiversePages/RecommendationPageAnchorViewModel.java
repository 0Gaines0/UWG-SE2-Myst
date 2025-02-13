/*
 * 
 */
package application.viewModel.mystiverse.subMystiversePages;

import java.util.ArrayList;
import java.util.List;

import application.model.server_impl.GameRecommendationEngine;
import application.model.local_impl.game.Game;
import application.model.server_impl.profile.ActiveUser;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class RecommendationPageAnchorViewModel {

	private StringProperty titleProperty;
	private StringProperty descProperty;
	private Property<Image> imageProperty;
	private StringProperty genresProperty;
	
	private GameRecommendationEngine engine;
	private List<Game> recommendations;

	/**
	 * Instantiates a new recommendation page anchor view model.
	 */
	public RecommendationPageAnchorViewModel() {
		this.engine = new GameRecommendationEngine();
		this.recommendations = new ArrayList<Game>();
		this.titleProperty = new SimpleStringProperty();
		this.descProperty = new SimpleStringProperty();
		this.imageProperty = new SimpleObjectProperty<Image>();
		this.genresProperty = new SimpleStringProperty();
	}
	
	/**
	 * Sets the properties.
	 */
	public void setupInitialProperties() {
		this.titleProperty.set(this.recommendations.get(0).getName());
		this.descProperty.set(this.recommendations.get(0).getDescription());
		var link = this.recommendations.get(0).getGamePhoto();
		var image = new Image(link, true);
		this.imageProperty.setValue(image);
		this.genresProperty.set(this.recommendations.get(0).getGenres().toString());
	}
	
	/**
	 * Generate recommendations.
	 *
	 * @return the list
	 */
	public List<Game> generateRecommendations() {
		return this.engine.generateRecommendations(ActiveUser.getActiveUser());
	}
	
	/**
	 * Skip game.
	 *
	 * @param game the game
	 */
	public void skipGame(Game game) {
		this.recommendations.remove(game);
	}
	
	/**
	 * Interested in game.
	 *
	 * @param game the game
	 */
	public void interestedInGame(Game game) {
		var likedGames = ActiveUser.getActiveUser().getAllLikedGames();
		likedGames.add(game);
		ActiveUser.getActiveUser().setAllLikedGames(likedGames);
		this.recommendations.remove(game);
	}
	
	/**
	 * Not interested in game.
	 *
	 * @param game the game
	 */
	public void notInterestedInGame(Game game) {
		var dislikedGames = ActiveUser.getActiveUser().getAllDislikedGames();
		dislikedGames.add(game);
		ActiveUser.getActiveUser().setAllDislikedGames(dislikedGames);
		this.recommendations.remove(game);
	}
	
	/**
	 * Gets the recommendations.
	 *
	 * @return the recommendations
	 */
	public List<Game> getRecommendations() {
		return this.recommendations;
	}
	
	/**
	 * Sets the recommendations.
	 *
	 * @param recommendations the new recommendations
	 */
	public void setRecommendations(List<Game> recommendations) {
		this.recommendations = recommendations;
	}

	/**
	 * Gets the title property.
	 *
	 * @return the title property
	 */
	public StringProperty getTitleProperty() {
		return this.titleProperty;
	}

	/**
	 * Sets the title property.
	 *
	 * @param titleProperty the new title property
	 */
	public void setTitleProperty(StringProperty titleProperty) {
		this.titleProperty = titleProperty;
	}

	/**
	 * Gets the desc property.
	 *
	 * @return the desc property
	 */
	public StringProperty getDescProperty() {
		return this.descProperty;
	}

	/**
	 * Sets the desc property.
	 *
	 * @param descProperty the new desc property
	 */
	public void setDescProperty(StringProperty descProperty) {
		this.descProperty = descProperty;
	}

	/**
	 * Gets the image property.
	 *
	 * @return the image property
	 */
	public Property<Image> getImageProperty() {
		return this.imageProperty;
	}

	/**
	 * Sets the image property.
	 *
	 * @param imageProperty the new image property
	 */
	public void setImageProperty(Property<Image> imageProperty) {
		this.imageProperty = imageProperty;
	}

	/**
	 * Gets the genres property.
	 *
	 * @return the genres property
	 */
	public StringProperty getGenresProperty() {
		return this.genresProperty;
	}

	/**
	 * Sets the genres property.
	 *
	 * @param genresProperty the new genres property
	 */
	public void setGenresProperty(StringProperty genresProperty) {
		this.genresProperty = genresProperty;
	}
}
