package org.codestars.tenttalk_api.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Campground extends AbstractEntity{

    private String name;
    private String address;
    private String website;
    private String placeId;
    private Double rating;

    @OneToMany(mappedBy = "campground", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "campground_tags", joinColumns = @JoinColumn(name = "campground_id"), inverseJoinColumns  = @JoinColumn(name= "tag_id"))
    private List<Tag> tags;




    public Campground(String name, String address, String website, String placeId, Double rating, List<Review> reviews, List<Tag> tags) {
        this.name = name;
        this.address = address;
        this.website = website;
        this.placeId = placeId;
        this.rating = rating;
        this.reviews = reviews;
        this.tags = tags;
    }

    public Campground(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
