package com.revature.dto;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author William Gentry
 */
public class AuthorDto {

  private int id;
  private String firstName;
  private String lastName;
  private String imageUrl;
  private List<BookDto> books;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public List<BookDto> getBooks() {
    return books;
  }

  public void setBooks(List<BookDto> books) {
    this.books = books;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthorDto authorDto = (AuthorDto) o;
    return getId() == authorDto.getId() &&
        Objects.equals(getFirstName(), authorDto.getFirstName()) &&
        Objects.equals(getLastName(), authorDto.getLastName()) &&
        Objects.equals(getBooks(), authorDto.getBooks());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getFirstName(), getLastName(), getBooks());
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", AuthorDto.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("firstName='" + firstName + "'")
        .add("lastName='" + lastName + "'")
        .add("books=" + books)
        .toString();
  }
}
