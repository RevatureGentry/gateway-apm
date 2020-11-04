package com.revature.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author William Gentry
 */
public class ReviewDto {

  private int id;
  private int bookId;
  private double rating;
  private String content;
  private String reviewer;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime createdOn;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime lastModified;
  private Set<CommentDto> comments;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getBookId() {
    return bookId;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public double getRating() {
    return rating;
  }

  public void setRating(double rating) {
    this.rating = rating;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getReviewer() {
    return reviewer;
  }

  public void setReviewer(String reviewer) {
    this.reviewer = reviewer;
  }

  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public LocalDateTime getLastModified() {
    return lastModified;
  }

  public void setLastModified(LocalDateTime lastModified) {
    this.lastModified = lastModified;
  }

  public Set<CommentDto> getComments() {
    return comments;
  }

  public void setComments(Set<CommentDto> comments) {
    this.comments = comments;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReviewDto reviewDto = (ReviewDto) o;
    return getId() == reviewDto.getId() &&
        getBookId() == reviewDto.getBookId() &&
        Double.compare(reviewDto.getRating(), getRating()) == 0 &&
        Objects.equals(getContent(), reviewDto.getContent()) &&
        Objects.equals(getReviewer(), reviewDto.getReviewer()) &&
        Objects.equals(getCreatedOn(), reviewDto.getCreatedOn()) &&
        Objects.equals(getLastModified(), reviewDto.getLastModified());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getBookId(), getRating(), getContent(), getReviewer(), getCreatedOn(), getLastModified());
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ReviewDto.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("bookId=" + bookId)
        .add("rating=" + rating)
        .add("content='" + content + "'")
        .add("reviewer='" + reviewer + "'")
        .add("createdOn=" + createdOn)
        .add("lastModified=" + lastModified)
        .toString();
  }
}
