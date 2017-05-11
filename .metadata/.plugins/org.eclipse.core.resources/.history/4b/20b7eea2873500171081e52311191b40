package com.mycompany.rankj;

/*
 * This bean represents a basic journal object
 * 
 */
public class Journal {
	
	private String name;
	private Double score;
	private Integer rank;
	private Boolean review;
	
	public Journal(final String name, final Double score) {
		init(name, score, false);
	}
	
	public Journal(final String name, final Double score, final Boolean review) {
		init(name, score, review);
	}
	
	private void init(final String name, final Double score, final Boolean review) {
		this.name = name;
		this.score = score;
		this.review = review;
	
	}

	public String getName() {
		return name;
	}

	public Double getScore() {
		return score;
	}
	
	@Override
	public String toString() {
		return "[" + this.getRank() + " -> " + this.name + " " + this.score + "]";
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		
		if(this.getClass().equals(obj.getClass()))
			return this.getScore().equals(((Journal)obj).getScore());
		
		return false;
				
	}

	public Boolean isReview() {
		return review;
	}
	
}
