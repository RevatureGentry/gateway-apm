package com.revature.dto;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

/**
 * @author William Gentry
 */
public class BookDto {

  private int id;
  private String title;
  private String imageUrl;
  private int authorId;
  private Set<String> genres = new LinkedHashSet<>();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public int getAuthorId() {
    return authorId;
  }

  public void setAuthorId(int authorId) {
    this.authorId = authorId;
  }

  public Set<String> getGenres() {
    return genres;
  }

  public void setGenres(Set<String> genres) {
    this.genres = genres;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookDto bookDto = (BookDto) o;
    return getId() == bookDto.getId() &&
        getAuthorId() == bookDto.getAuthorId() &&
        Objects.equals(getTitle(), bookDto.getTitle()) &&
        Objects.equals(getImageUrl(), bookDto.getImageUrl()) &&
        Objects.equals(getGenres(), bookDto.getGenres());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTitle(), getImageUrl(), getAuthorId(), getGenres());
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", BookDto.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("title='" + title + "'")
        .add("imageUrl='" + imageUrl + "'")
        .add("authorId=" + authorId)
        .add("genres=" + genres)
        .toString();
  }
}
