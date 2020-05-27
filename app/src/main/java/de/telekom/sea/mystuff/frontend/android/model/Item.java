package de.telekom.sea.mystuff.frontend.android.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class Item {

    private Long id;

    private String name;

    private int amount;

    private String location;

    private String description;

    private Date lastUsed;

}
