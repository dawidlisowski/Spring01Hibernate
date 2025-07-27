package pl.coderslab.Spring01Hibernate.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private int rating;
    private String description;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(name = "book_publisher",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private List<Publisher> publishers;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n📚 Book ID: ").append(id)
                .append("\n   Title: ").append(title)
                .append("\n   Rating: ").append(rating)
                .append("\n   Description: ").append(description)
                .append("\n   Author: ").append(
                        author != null ? author.getFirstName() + " " + author.getLastName() : "N/A")
                .append("\n   Category: ").append(
                        category != null ? category.getName() : "N/A")
                .append("\n   Publishers: ");

        if (publishers != null && !publishers.isEmpty()) {
            for (Publisher p : publishers) {
                sb.append("\n     - ").append(p.getName());
            }
        } else {
            sb.append("None");
        }

        return sb.toString();
    }

}
