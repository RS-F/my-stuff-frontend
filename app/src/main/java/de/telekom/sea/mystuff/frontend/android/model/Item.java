package de.telekom.sea.mystuff.frontend.android.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Item {

	private Long id;

	private String name;

	private int amount;

	private String location;

	private String description;

	private Date lastUsed;

}
