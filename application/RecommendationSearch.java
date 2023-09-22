/**
 * This class represents the RecommendationSearch for the application.
 *
 * @author Sophia Atendido
 * @author Lizett Gonzalez
 * @author Paing Hein Kyaw
 * @author Tiffany Pham
 * @version 1.0
 * @since 2023-05-03
 */
package application;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles searching for recommendations.
 */
public class RecommendationSearch {
	private RecommendationManager recommendationManager;

	/**
	 * Constructor for the RecommendationSearch class.
	 *
	 * @param recommendationManager the RecommendationManager instance
	 */
	public RecommendationSearch(RecommendationManager recommendationManager) {
		this.recommendationManager = recommendationManager;
	}

	/**
	 * Searches for recommendations by last name.
	 *
	 * @param lastName the last name to search for
	 * @return a list of recommendations with the specified last name
	 */
	public List<Recommendation> searchByLastName(String lastName) {
		return recommendationManager.getRecommendations().stream()
				.filter(recommendation -> recommendation.getLastName().equalsIgnoreCase(lastName))
				.collect(Collectors.toList());
	}

	/**
	 * Edits a recommendation by replacing the old recommendation with the new one.
	 *
	 * @param oldRecommendation the old recommendation object
	 * @param newRecommendation the new recommendation object
	 */
	public void editRecommendation(Recommendation oldRecommendation, Recommendation newRecommendation) {
		recommendationManager.getRecommendations().remove(oldRecommendation);

		String fileName = newRecommendation.getFirstName() + "_" + newRecommendation.getLastName()
				+ "_recommendation.txt";
		recommendationManager.addRecommendation(newRecommendation, fileName);
	}

	/**
	 * Deletes a recommendation from the recommendation list.
	 *
	 * @param recommendation the recommendation object to delete
	 */
	public void deleteRecommendation(Recommendation recommendation) {
		recommendationManager.getRecommendations().remove(recommendation);
	}
}
