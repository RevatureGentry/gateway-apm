package com.revature.dto;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author William Gentry
 */
public class CommentFormDto {

  private int reviewId;
  private Integer parentCommentId;
  private String content;

  public int getReviewId() {
    return reviewId;
  }

  public void setReviewId(int reviewId) {
    this.reviewId = reviewId;
  }

  public Integer getParentCommentId() {
    return parentCommentId;
  }

  public void setParentCommentId(Integer parentCommentId) {
    this.parentCommentId = parentCommentId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommentFormDto that = (CommentFormDto) o;
    return getReviewId() == that.getReviewId() &&
        Objects.equals(getParentCommentId(), that.getParentCommentId()) &&
        Objects.equals(getContent(), that.getContent());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getReviewId(), getParentCommentId(), getContent());
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CommentDto.class.getSimpleName() + "[", "]")
        .add("reviewId=" + reviewId)
        .add("parentCommentId=" + parentCommentId)
        .add("content='" + content + "'")
        .toString();
  }
}
