package com.nidaff.entity.entities;

public enum Rating {

	ONE_STAR(1), TWO_STARS(2), THREE_STARS(3), FOUR_STARS(4), FIVE_STARS(5);

	private int value;

	private Rating(int value) {
		this.value = value;
	}
}