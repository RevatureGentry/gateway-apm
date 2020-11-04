package com.revature.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author William Gentry
 */
public class CommentDto {
  private int id;
  private String comment;
  private String commenter;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime createdOn;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
  private LocalDateTime lastModified;

  @JsonBackReference
  private CommentDto parentComment;

  private Set<CommentDto> comments;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getCommenter() {
    return commenter;
  }

  public void setCommenter(String commenter) {
    this.commenter = commenter;
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

  public CommentDto getParentComment() {
    return parentComment;
  }

  public void setParentComment(CommentDto parentComment) {
    this.parentComment = parentComment;
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
    CommentDto that = (CommentDto) o;
    return getId() == that.getId() &&
        Objects.equals(getComment(), that.getComment()) &&
        Objects.equals(getCommenter(), that.getCommenter()) &&
        Objects.equals(getCreatedOn(), that.getCreatedOn()) &&
        Objects.equals(getLastModified(), that.getLastModified());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getComment(), getCommenter(), getCreatedOn(), getLastModified());
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CommentDto.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("comment='" + comment + "'")
        .add("commenter='" + commenter + "'")
        .add("createdOn=" + createdOn)
        .add("lastModified=" + lastModified)
        .toString();
  }
}
