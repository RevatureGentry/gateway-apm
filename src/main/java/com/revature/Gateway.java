package com.revature;

import java.util.Collection;
import java.util.List;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;
import com.revature.dto.AuthorDto;
import com.revature.dto.BookDto;
import com.revature.dto.CommentDto;
import com.revature.dto.CommentFormDto;
import com.revature.dto.ReviewDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author William Gentry
 */
@CrossOrigin(allowCredentials = "true", origins = "*")
@RestController
public class Gateway {

  @Value("${author.api:http://localhost:10000/author}")
  private String authorApi;

  @Value("${book.api:http://localhost:10001/book}")
  private String bookApi;

  @Value("${review.api:http://localhost:10002/reviews}")
  private String reviewsApi;

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final RestTemplate restTemplate;

  public Gateway(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  @GetMapping("/author")
  public ResponseEntity<List<AuthorDto>> findAuthors(@RequestHeader("Authorization") String bearer) {
    logger.info("Attempting to find all authors");
    ResponseEntity<List<AuthorDto>> authorsResponse = restTemplate.exchange(authorApi, HttpMethod.GET, getRequestWithAuthorizationHeader(bearer), new ParameterizedTypeReference<>(){});
    return handleResponse(authorsResponse);
  }

  @GetMapping("/author/{id}/books")
  public ResponseEntity<List<BookDto>> findBooksForAuthor(@RequestHeader("Authorization") String bearer, @PathVariable("id") int authorId) {
    String uri = String.format("%s/author/%d", bookApi, authorId);
    logger.info("Attempting to find books for author {}: {}", authorId, uri);
    ResponseEntity<List<BookDto>> booksResponse = restTemplate.exchange(uri, HttpMethod.GET, getRequestWithAuthorizationHeader(bearer), new ParameterizedTypeReference<>(){});
    return handleResponse(booksResponse);
  }

  @GetMapping("/reviews/{id}")
  public ResponseEntity<List<ReviewDto>> findReviewsForBook(@RequestHeader("Authorization") String bearer, @PathVariable("id") int bookId) {
    String uri = String.format("%s/%d", reviewsApi, bookId);
    logger.info("Attempting to find reviews for book {}", bookId);
    ResponseEntity<List<ReviewDto>> reviewsResponse = restTemplate.exchange(uri, HttpMethod.GET, getRequestWithAuthorizationHeader(bearer), new ParameterizedTypeReference<>(){});
    return handleResponse(reviewsResponse);
  }

  @PostMapping("/reviews")
  public ResponseEntity<ReviewDto> createReview(@RequestHeader("Authorization") String bearer, @RequestBody ReviewDto review) {
    logger.info("Attempting to create review");
    HttpEntity<ReviewDto> request = getRequestWithAuthorizationHeaderAndBody(bearer, review);
    ResponseEntity<ReviewDto> createdReviewsResponse = restTemplate.postForEntity(reviewsApi, request, ReviewDto.class);
    return handleResponse(createdReviewsResponse);
  }

  @PostMapping("/reviews/comment")
  public ResponseEntity<ReviewDto> createComment(@RequestHeader("Authorization") String bearer, @RequestBody CommentFormDto commentForm) {
    logger.info("Attempting to create comment");
    String uri = String.format("%s/comment", reviewsApi);
    HttpEntity<CommentFormDto> request = getRequestWithAuthorizationHeaderAndBody(bearer, commentForm);
    ResponseEntity<ReviewDto> createdReviewResponse = restTemplate.postForEntity(uri, request, ReviewDto.class);
    return handleResponse(createdReviewResponse);
  }

  private HttpEntity<?> getRequestWithAuthorizationHeader(String bearer) {
    Transaction transaction = ElasticApm.currentTransaction();
    String parentTransactionId = transaction.ensureParentId();
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(bearer.replace("Bearer ", ""));
    headers.set("traceparent", parentTransactionId);
    return new HttpEntity<>(headers);
  }

  private <T> HttpEntity<T> getRequestWithAuthorizationHeaderAndBody(String bearer, T body) {
    Transaction transaction = ElasticApm.currentTransaction();
    String parentTransactionId = transaction.ensureParentId();
    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(bearer.replace("Bearer ", ""));
    headers.set("traceparent", parentTransactionId);
    return new HttpEntity<T>(body, headers);
  }

  private <T> ResponseEntity<T> handleResponse(ResponseEntity<T> response) {
    Transaction transaction = ElasticApm.currentTransaction();
    try {
      if (response != null && response.hasBody()) {
        T data = response.getBody();
        if (data != null) {
          return ResponseEntity.ok(data);
        }
      }
      return ResponseEntity.notFound().build();
    } finally {
      transaction.end();
    }
  }
}
